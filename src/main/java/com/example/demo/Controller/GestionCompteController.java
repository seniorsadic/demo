package com.example.demo.Controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.AncienPCBRepository;
import com.example.demo.dao.NouveauPCBRepository;
import com.example.demo.entities.AncienPCB;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(value="/compte")
public class GestionCompteController {
	
	@Autowired
	public AncienPCBRepository repository;
	@Autowired
	public NouveauPCBRepository nouveaurepository;
	
	private static String UPLOADED_FOLDER = "C://Users//pc lenovo//Documents//Obertys//";
	
	@RequestMapping(value="/listancienpcb")
	public String index_ancien(Model model ) {
		model.addAttribute("listes", repository.listeParent());
		return "compte/list_ancien"; 
	}
	
	@RequestMapping(value="/ajoutancienpcb1")
	public String ajouter_ancien1(Model model ) {		
		model.addAttribute("liste", repository.findAll());
		model.addAttribute("ancien", new AncienPCB()); 
		return "compte/ajout_ancien1"; 
	}
	
	@RequestMapping(value="enregistrerancien", method=RequestMethod.POST)
	public String enregistrerancien(@Valid AncienPCB ancien, BindingResult bind, @RequestParam(name="parent",defaultValue="0")String values) {
		if(bind.hasErrors()) {
			return "ajoutancienpcb1";
		}
		ancien.setParent(repository.getOne(values));
		repository.save(ancien);
		return "redirect:/compte/listancienpcb";
	}
	
	@RequestMapping(value="/ajoutancienpcb")
	public String ajouter_ancien(Model model ,@RequestParam(name="compte_ancien",defaultValue="0")String compte_ancien) {
		AncienPCB anc=repository.getOne(compte_ancien);
		model.addAttribute("listes", repository.findByParent(anc));
		model.addAttribute("anc", anc);
		model.addAttribute("ancien", new AncienPCB()); 
		return "compte/ajout_ancien"; 
	}
	
	@RequestMapping(value="/listnouveaupcb")
	public String index_nouveau(Model model ) {
		model.addAttribute("listes", nouveaurepository.findAll());
		return "compte/list_nouveau"; 
	}
	
	@RequestMapping(value="/upload")
	public String upload(Model model ) {
		model.addAttribute("listes", nouveaurepository.findAll());
		return "compte/chargerpcb"; 
	}
	
	@RequestMapping(value="/enregistrerfichier")
	public String enregistrerfichier(Model model,@RequestParam("file") MultipartFile file ) {
		if (file.isEmpty()) {
            return "redirect:/compte/listnouveaupcb";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/compte/upload";
	}

}
