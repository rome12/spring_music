package net.codejava.spring.controller;

import java.util.List;

import net.codejava.spring.dao.GenreDAO;
import net.codejava.spring.model.Genre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GenreController {

	@Autowired
	private GenreDAO genreDao;
	
	@RequestMapping(value="/admin/genre")
	public ModelAndView genre() {
		List<Genre> listGenre = genreDao.list();
		ModelAndView model = new ModelAndView("admin_genre");
		model.addObject("genreList", listGenre);
		model.addObject("genre", new Genre());
		return model;
	}
	
	@RequestMapping(value = "/admin/genre/add", method = RequestMethod.POST)
    public String addGenre(@ModelAttribute(value="genre") Genre genre, BindingResult result)
    {
        genreDao.add(genre);
        return "redirect:/admin/genre";
    }
	
	@RequestMapping("/admin/genre/delete/{genreId}")
    public String deleteGenre(@PathVariable("genreId") Integer genreId)
    {
		try {
			genreDao.delete(genreId);
		}
		catch(Exception e){
			return "redirect:/admin/genre?error=02";
		}
        return "redirect:/admin/genre";
    }
	
	@RequestMapping(value = "/admin/genre/modif", method = RequestMethod.POST)
    public String modifGenre(@ModelAttribute(value="genre") Genre genre, BindingResult result)
    {
        genreDao.modif(genre);
        return "redirect:/admin/genre";
    }
	
}
