package net.codejava.spring.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.codejava.spring.dao.CommentMusiqueDAO;
import net.codejava.spring.dao.MusiqueDAO;
import net.codejava.spring.model.CommentaireMusique;
import net.codejava.spring.model.Musique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@Autowired
	private MusiqueDAO musiqueDao;
	@Autowired
	private CommentMusiqueDAO commentMusiqueDao;

	@RequestMapping(value="/")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("home");
		model.addObject("listMusiques",musiqueDao.list());
		model.addObject("lastMusiques", musiqueDao.listLatest());
		model.addObject("populaireList", musiqueDao.listPopulaire(commentMusiqueDao));
		model.addObject("listLatestComment", commentMusiqueDao.listLastest());
		model.addObject("listPubs", MainController.readPub());
		
		return model;
	}
	
	@RequestMapping(value="/admin/")
	public ModelAndView admin() {
		ModelAndView model = new ModelAndView("admin");
		return model;
	}
	
	@RequestMapping(value="/webservice")
	public ModelAndView webservice() {
		ModelAndView model = new ModelAndView("webservice");
		List<Musique> listeMusiques = musiqueDao.list();
		List<String> listeToPrint = new ArrayList<String>();
		for(int i=0;i<listeMusiques.size();i++){
			List<CommentaireMusique> listComments = commentMusiqueDao.list(listeMusiques.get(i).getId());
			String chaine = listeMusiques.get(i).getArtiste()+"/"+listeMusiques.get(i).getTitre()+" : ";
			for(int j = 0;j<listComments.size();j++){
				chaine += listComments.get(j).getNote()+" ";
			}
			listeToPrint.add(chaine);
		}
		model.addObject("listeToPrint", listeToPrint);
		System.out.println("!!!"+listeToPrint);
		return model;
	}

	public static List<String> readPub(){
		try{
			List<String> maListe = new ArrayList<String>();
			String fichier = "resources/pub.txt" ;
			
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				/*String[] splitLine = ligne.split(" ");
				System.out.println("!!!!!"+splitLine[1]);
				if(MainController.exists(splitLine[1]))maListe.add(ligne);*/
				maListe.add(ligne);
			}
			br.close(); 
			return maListe;
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		return null;
	}
	
	public static boolean exists(String URLName){
	    try {
	      HttpURLConnection.setFollowRedirects(false);
	      // note : you may also need
	      //        HttpURLConnection.setInstanceFollowRedirects(false)
	      HttpURLConnection con =
	         (HttpURLConnection) new URL(URLName).openConnection();
	      con.setRequestMethod("HEAD");
	      return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
	    }
	    catch (Exception e) {
	       e.printStackTrace();
	       return false;
	    }
	  }
}
