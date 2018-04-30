package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dao.AncienPCBRepository;

@Controller
@RequestMapping(value="/compte")
public class GestionCompteController {
	
	@Autowired
	public AncienPCBRepository repository;
	
	@RequestMapping(value="/listancienpcb")
	public String index_ancien(Model model ) {
		model.addAttribute("listes", repository.findAll());
		return "list_ancien";
	}

}
