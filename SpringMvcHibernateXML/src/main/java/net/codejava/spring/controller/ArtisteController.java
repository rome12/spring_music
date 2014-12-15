package net.codejava.spring.controller;

import java.util.List;

import net.codejava.spring.dao.ArtisteDAO;
import net.codejava.spring.dao.MusiqueDAO;
import net.codejava.spring.model.Artiste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ArtisteController {
	
	@Autowired
	private ArtisteDAO artisteDao;
	@Autowired
	private MusiqueDAO musiqueDAO;
	
	@RequestMapping(value="/admin/artiste")
	public ModelAndView artiste() {
		List<Artiste> listArtiste = artisteDao.list();
		ModelAndView model = new ModelAndView("admin_artiste");
		model.addObject("artisteList", listArtiste);
		model.addObject("artiste", new Artiste());
		return model;
	}
	
	@RequestMapping(value = "/admin/artiste/add", method = RequestMethod.POST)
    public String addArtiste(@ModelAttribute(value="artiste") Artiste artiste, BindingResult result)
    {
        artisteDao.add(artiste);
        return "redirect:/admin/artiste";
    }
	
	@RequestMapping("/admin/artiste/delete/{artisteId}")
    public String deleteArtiste(@PathVariable("artisteId") Integer artisteId)
    {
		try {
			artisteDao.delete(artisteId);
		}
		catch(Exception e){
			return "redirect:/admin/artiste?error=02";
		}
        return "redirect:/admin/artiste";
    }
	
	@RequestMapping(value = "/admin/artiste/modif", method = RequestMethod.POST)
    public String modifArtiste(@ModelAttribute(value="artiste") Artiste artiste, BindingResult result)
    {
		artisteDao.modif(artiste);
        return "redirect:/admin/artiste";
    }
	
	@RequestMapping("/artiste/{artisteId}")
	public ModelAndView getArtiste(@PathVariable("artisteId") Integer artisteId)
    {
		ModelAndView model = new ModelAndView("public_artiste");
		model.addObject("listMusique",musiqueDAO.listArtiste(artisteId));
		model.addObject("artiste", artisteDao.getArtiste(artisteId));
		return model;
    }
	
	
	
}
