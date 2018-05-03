package com.example.demo.Controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.OperationRepository;
import com.example.demo.entities.Operation;

@Controller
@RequestMapping(value="/operation")
public class OperationController {
	
	@Autowired
	OperationRepository repository;
	
	@RequestMapping(value="/index")
	public String index(Model model, @RequestParam(name="page",defaultValue="0")int p,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		
		Page<Operation> listes=repository.findAll(new PageRequest(p, 6));
		int page=listes.getTotalPages();
		int[] pages=new int[page];
		for(int i=0; i<page;i++) {
			pages[i]=i;
		}

		model.addAttribute("pages",pages);
		model.addAttribute("current",p);
		model.addAttribute("motCle",mc);
		model.addAttribute("operations", listes.getContent());
		return "listeOperation";
	}
	
	@RequestMapping(value="/ajouter", method=RequestMethod.GET)
	public String ajouter(Model model) {
		model.addAttribute("operation", new Operation());
		return "ajouteroperation";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@Valid Operation operation, BindingResult bind) {
		if(bind.hasErrors()) {
			return "ajouter";
		}
		repository.save(operation);
		return "redirect:/operation/index";
	}

}
