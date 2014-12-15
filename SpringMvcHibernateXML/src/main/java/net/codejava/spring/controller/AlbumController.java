package net.codejava.spring.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.codejava.spring.dao.AlbumDAO;
import net.codejava.spring.dao.ArtisteDAO;
import net.codejava.spring.dao.CommentAlbumDAO;
import net.codejava.spring.dao.CommentMusiqueDAO;
import net.codejava.spring.dao.GenreDAO;
import net.codejava.spring.dao.MusiqueDAO;
import net.codejava.spring.model.Album;
import net.codejava.spring.model.Artiste;
import net.codejava.spring.model.Genre;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.HibernateException;
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
public class AlbumController {
	
	@Autowired
	private AlbumDAO albumDao;
	@Autowired
	private ArtisteDAO artisteDao;
	@Autowired
	private GenreDAO genreDao;
	@Autowired
	private MusiqueDAO musiqueDao;
	@Autowired
	private CommentAlbumDAO commentAlbumDao;
	
	@RequestMapping(value="/admin/album")
	public ModelAndView album() {
		List<Album> listAlbum = albumDao.list();
		List<Artiste> listArtiste = artisteDao.list();
		List<Genre> listGenre = genreDao.list();
		ModelAndView model = new ModelAndView("admin_album");
		model.addObject("albumList", listAlbum);
		model.addObject("artisteList", listArtiste);
		model.addObject("genreList", listGenre);
		model.addObject("album", new Album());
		return model;
	}
	
	@RequestMapping(value = "/admin/album/add", method = RequestMethod.POST)
    public String addAlbum(@RequestParam("file") MultipartFile file, @ModelAttribute(value="album") Album album, BindingResult result)
    {
		if (!file.isEmpty()) {
			File dossierMus = new File("images");
			if (!dossierMus.exists()) {
			    try{
			    	dossierMus.mkdir();
			     } catch(SecurityException se){
			        //handle it
			     }        
			}
			
			try {
				String nom = album.getTitre()+"."+FilenameUtils.getExtension(file.getOriginalFilename());
				file.transferTo(new File("images/"+nom));
				album.setFichier(nom);
			}
			catch(Exception e){
				//Erreur lors de la création du fichier
				return "redirect:/admin/album?error=04";
			}
		}
		else {
			//Il manque le fichier
			return "redirect:/admin/album?error=03";
		}
        albumDao.add(album);
        return "redirect:/admin/album";
    }
	
	@RequestMapping(value="/admin/album/delete/{albumId}", method = RequestMethod.GET)
    public String deleteAlbum(@PathVariable("albumId") Integer albumId)
    {
		try {
			albumDao.delete(albumId);
		}
		catch(Exception e){
			return "redirect:/admin/album?error=02";
		}
		return "redirect:/admin/album";
    }
	
	@RequestMapping(value = "/admin/album/modif", method = RequestMethod.POST)
    public String modifAlbum(@RequestParam("file") MultipartFile file, @ModelAttribute(value="album") Album album, BindingResult result)
    {
		
		if (!file.isEmpty()) {
			File dossierMus = new File("images");
			if (!dossierMus.exists()) {
			    try{
			    	dossierMus.mkdir();
			     } catch(SecurityException se){
			        //handle it
			     }        
			}
			try {
				file.transferTo(new File("images/"+file.getOriginalFilename()));
	            album.setFichier(file.getOriginalFilename());
			}
			catch(Exception e){
				e.printStackTrace();
				//Erreur lors de la création du fichier
				return "redirect:/admin/album?error=04";
			}	
			albumDao.modif(album,true);
		}
		albumDao.modif(album,false);
        return "redirect:/admin/album";
    }
	
	@RequestMapping("/album/{albumId}")
	public ModelAndView getAlbum(@PathVariable("albumId") Integer albumId)
    {
		ModelAndView model = new ModelAndView("public_album");
		model.addObject("listMusique",musiqueDao.listAlbum(albumId));
		model.addObject("listComment", commentAlbumDao.list(albumId));
		return model;
    }
	
	@RequestMapping(value="/album/comment/add/{albumId}", method = RequestMethod.POST)
	public String addComment(@RequestParam("commentaireSaisi") String commentaireSaisi, @RequestParam("noteSaisi") String noteSaisi, @PathVariable("albumId") Integer albumId, HttpServletRequest request)
    {
		if(request.getSession().getAttribute("cpt_comm")==null){
			request.getSession().setAttribute("cpt_comm", 0);
		}
		int cpt = (Integer) request.getSession().getAttribute("cpt_comm");
		if(cpt<3){
			commentAlbumDao.add(albumDao.getAlbum(albumId), commentaireSaisi, Integer.parseInt(noteSaisi));
			request.getSession().setAttribute("cpt_comm", cpt+1);
			return "redirect:/album/{albumId}";
		}
		return "redirect:/album/{albumId}?error=1";
    }

}
