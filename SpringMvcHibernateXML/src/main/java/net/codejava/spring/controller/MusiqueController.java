package net.codejava.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.codejava.spring.dao.AlbumDAO;
import net.codejava.spring.dao.CommentMusiqueDAO;
import net.codejava.spring.dao.MusiqueDAO;
import net.codejava.spring.dao.ArtisteDAO;
import net.codejava.spring.dao.GenreDAO;
import net.codejava.spring.model.Album;
import net.codejava.spring.model.Musique;
import net.codejava.spring.model.Artiste;
import net.codejava.spring.model.Genre;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MusiqueController {
	
	@Autowired
	private MusiqueDAO musiqueDao;
	@Autowired
	private ArtisteDAO artisteDao;
	@Autowired
	private AlbumDAO albumDao;
	@Autowired
	private GenreDAO genreDao;
	@Autowired
	private CommentMusiqueDAO commentMusiqueDao;
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value="/admin/musique")
	public ModelAndView musique() {
		List<Musique> listMusique = musiqueDao.list();
		List<Artiste> listArtiste = artisteDao.list();
		List<Album> listAlbum = albumDao.list();
		List<Genre> listGenre = genreDao.list();
		ModelAndView model = new ModelAndView("admin_musique");
		model.addObject("musiqueList", listMusique);
		model.addObject("artisteList", listArtiste);
		model.addObject("genreList", listGenre);
		model.addObject("albumList", listAlbum);
		model.addObject("musique", new Musique());
		return model;
	}
	
	@RequestMapping(value = "/admin/musique/add", method = RequestMethod.POST)
    public String addMusique(@RequestParam("file") MultipartFile file, @ModelAttribute(value="musique") Musique musique, BindingResult result)
    {
		if (!file.isEmpty()) {
			File dossierMus = new File("musiques");
			if (!dossierMus.exists()) {
			    try{
			    	dossierMus.mkdir();
			     } catch(SecurityException se){
			        //handle it
			     }        
			}
			
			try {
				String nom = "1:"+musique.getTitre()+"."+FilenameUtils.getExtension(file.getOriginalFilename());
				file.transferTo(new File("musiques/"+nom));
	            musique.setFichier(nom);
			}
			catch(Exception e){
				//Erreur lors de la création du fichier
				return "redirect:/admin/musique?error=04";
			}
		}
		else {
			//Il manque le fichier
			return "redirect:/admin/musique?error=03";
		}
        musiqueDao.add(musique);
        return "redirect:/admin/musique";
    }
	
	
	@RequestMapping("/admin/musique/delete/{musiqueId}")
    public String deleteMusique(@PathVariable("musiqueId") Integer musiqueId)
    {
        musiqueDao.delete(musiqueId);
        return "redirect:/admin/musique";
    }
	
	@RequestMapping(value = "/admin/musique/modif", method = RequestMethod.POST)
    public String modifMusique(@RequestParam("file") MultipartFile file, @ModelAttribute(value="musique") Musique musique, BindingResult result)
    {
		if (!file.isEmpty()) {
			File dossierMus = new File("musiques");
			if (!dossierMus.exists()) {
			    try{
			    	dossierMus.mkdir();
			     } catch(SecurityException se){
			    	 se.printStackTrace();
			     }        
			}
			Musique ancienne = musiqueDao.getMusique(musique.getId());
			//if(true)
			//return "redirect:/admin/musique?print="+ancienne.getFichier();
			String[] decoup = ancienne.getFichier().split(":");
			String nom;
			if(decoup.length>1){
				int num = Integer.parseInt(decoup[0])+1;
				nom = num+":"+decoup[1];
			}
			else {
				nom = "1:"+decoup[0];
			}
			try {
				file.transferTo(new File("musiques/"+nom));
	            musique.setFichier(nom);
			}
			catch(Exception e){
				e.printStackTrace();
				//Erreur lors de la création du fichier
				return "redirect:/admin/musique?error=04"+nom;
			}	
			musiqueDao.modif(musique,true);
		}
		musiqueDao.modif(musique,false);
        return "redirect:/admin/musique";
    }
	
	@RequestMapping("/musique/{musiqueId}")
	public ModelAndView getMusique(@PathVariable("musiqueId") Integer musiqueId)
    {
		
		ModelAndView model = new ModelAndView("public_musique");
		model.addObject("musique", musiqueDao.getMusique(musiqueId));
		model.addObject("listComment", commentMusiqueDao.list(musiqueId));
		model.addObject("avg",commentMusiqueDao.avg(musiqueId));
		return model;
    }
	
	@RequestMapping(value="/musique/comment/add/{musiqueId}", method = RequestMethod.POST)
	public String addComment(@RequestParam("commentaireSaisi") String commentaireSaisi, @RequestParam("noteSaisi") String noteSaisi, @PathVariable("musiqueId") Integer musiqueId, HttpServletRequest request)
    {
		if(commentaireSaisi.length()<1 || noteSaisi.equals("0")){
			return "redirect:/musique/{musiqueId}?error=1";
		}
		if(request.getSession().getAttribute("cpt_comm")==null){
			request.getSession().setAttribute("cpt_comm", 0);
		}
		int cpt = (Integer) request.getSession().getAttribute("cpt_comm");
		if(commentaireSaisi.length()>120)commentaireSaisi=commentaireSaisi.substring(0, 120);
		if(cpt<3){
			commentMusiqueDao.add(musiqueDao.getMusique(musiqueId), commentaireSaisi, Integer.parseInt(noteSaisi));
			request.getSession().setAttribute("cpt_comm", cpt+1);
			return "redirect:/musique/{musiqueId}";
		}
		return "redirect:/musique/{musiqueId}?error=2";
    }
	
}
