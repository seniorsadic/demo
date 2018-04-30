package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ModuleRepository;
import com.example.demo.dao.PrivilegeRepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.entities.Module;
import com.example.demo.entities.Privilege;
import com.example.demo.entities.Role;
import com.example.demo.entities.Users;



@Controller
@RequestMapping(value="/users")
public class GestionUsersController {
	
	@Autowired
	private UsersRepository repo;
	@Autowired
	private RoleRepository rolerepository;
	@Autowired
	private PrivilegeRepository privilegerepository;
	@Autowired
	private ModuleRepository repo3;
	
	@RequestMapping(value="/listemodules")
	public String indexModule(Model model) {
		model.addAttribute("listes",repo3.findAll());
		return "gestionutilisateur/listemodules";
	}
	
	@RequestMapping(value="/listeprivilegesbymodule")
	public String indexPrivilegeByModule(Model model,@RequestParam(name="id",defaultValue="0")Long id) {
		Optional<Module> module=repo3.findById(id);
		List<Privilege> listes=privilegerepository.findByModule(module.get());
		System.out.println(module.getClass().getName());
		System.out.println(module.get().toString());
		model.addAttribute("listes",listes);  
		model.addAttribute("module",module.get()); 
		return "gestionutilisateur/listeprivilegesbymodule";
	}
	
	@RequestMapping(value="/ajoutermodule", method=RequestMethod.GET)
	public String ajoutermodule(Model model) {
		model.addAttribute("module",new Module());
		return "gestionutilisateur/ajoutermodule";
	}
	
	@RequestMapping(value="/supprimermodule", method=RequestMethod.GET)
	public String supprimermodule(Model model,@RequestParam(name="id",defaultValue="0")Long id) {
		repo3.deleteById(id);
		return "redirect:/users/listemodules";
	}
	
	@RequestMapping(value="savemodule", method=RequestMethod.POST)
	public String savemodule(@Valid Module m, BindingResult bind, @RequestParam(name="privileges",defaultValue="0")List<String> values) {
		if(bind.hasErrors()) {
			return "ajoutermodule";
		}
		repo3.save(m);
		for (int i=0; i<values.size();i++) {
			System.out.println("Taille de la liste : "+values.size());
			Privilege privilege=new Privilege(values.get(i), m);
			privilegerepository.save(privilege);
			System.out.println("Bien ajouter");
		}
		
		return "redirect:/users/listemodules";
	}
	
	@RequestMapping(value="/listeprivileges")
	public String indexPrivilege(Model model) {
		model.addAttribute("listes",privilegerepository.findAll());
		model.addAttribute("privilege",new Privilege());
		return "gestionutilisateur/listeprivileges";
	}
	
	
	
	@RequestMapping(value="saveprivilege", method=RequestMethod.POST)
	public String saveprivilege(@Valid Privilege r, BindingResult bind) {
		if(bind.hasErrors()) {
			return "ajouterrole";
		}
		privilegerepository.save(r);
		return "redirect:/users/listeprivileges";
	}
	
	@RequestMapping(value="/listeusers")
	public String index(Model model, @RequestParam(name="page",defaultValue="0")int p,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		
		@SuppressWarnings("deprecation")
		Page<Users> listes=repo.findByUsername("%"+mc+"%", new PageRequest(p, 5));
		int page=listes.getTotalPages();
		int[] pages=new int[page];
		for(int i=0; i<page;i++) {
			pages[i]=i;
		}
		model.addAttribute("users",listes);
		model.addAttribute("pages",pages);
		model.addAttribute("current",p);
		model.addAttribute("motCle",mc);
		return "gestionutilisateur/listeusers";
	}
	
	@RequestMapping(value="ajouterusers", method=RequestMethod.GET)
	public String ajouterusers(Model model) {
		model.addAttribute("roles", rolerepository.findAll());
		model.addAttribute("users", new Users());
		return "gestionutilisateur/ajouterusers";
	}
	
	@RequestMapping(value="saveusers", method=RequestMethod.POST)
	public String saveusers(@Valid Users u, BindingResult bind,@RequestParam(name="choix")String choix) {
		if(bind.hasErrors()) {
			return "ajouter";
		}
		
		u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
		repo.save(u);
		Role role=rolerepository.getOne(choix);	
		System.out.println("Voir le role : "+choix);
		repo.save(u);
		u=repo.getOne(u.getUsername());
		Collection<Role> collections=new ArrayList<Role>();
		collections.add(role);
		u.setRoles(collections);
		repo.save(u);
		return "redirect:/users/listeusers";
	}
	
	@RequestMapping(value="/activer")
	public String activer(String id, Model model) {
		Users user=repo.getOne(id);
		System.out.println("Voir ancienne valeur "+user.isActived());
		if(user.isActived())
			user.setActived(false);
		else
			user.setActived(true);
		
		System.out.println("Voir nouvelle valeur "+user.isActived());
		repo.save(user);
		return "redirect:/users/listeusers";
	}
	
	@RequestMapping(value="/listeroles")
	public String index1(Model model, @RequestParam(name="page",defaultValue="0")int p) {
		
		Page<Role> listes=rolerepository.findAll( new PageRequest(p, 5));
		int page=listes.getTotalPages();
		int[] pages=new int[page];
		for(int i=0; i<page;i++) {
			pages[i]=i;
		}
		model.addAttribute("roles",listes);
		model.addAttribute("pages",pages);
		model.addAttribute("current",p);
		return "gestionutilisateur/listeroles";
	}
	
	@RequestMapping(value="ajouterrole", method=RequestMethod.GET)
	public String ajouterrole(Model model) {	
		model.addAttribute("role", new Role());
		model.addAttribute("listeprivileges", privilegerepository.findAll()); 
		return "gestionutilisateur/ajouterrole";
	}
	
	@RequestMapping(value="saverole", method=RequestMethod.POST)
	public String saverole(@Valid Role r, BindingResult bind, @RequestParam("valeur") List<Long> values) {
		if(bind.hasErrors()) {
			return "ajouterrole";
		}
		rolerepository.save(r);
		System.out.println("La liste des privileges");
		Collection<Privilege> collections=new ArrayList<Privilege>();
		for (int i=0; i<values.size();i++) {
	        Privilege privilege=privilegerepository.getOne(values.get(i));
	        collections.add(privilege);
			System.out.println(values.get(i));
		}
		r.setPrivileges(collections);
		rolerepository.save(r);
		System.out.println("Fin de la liste");
		
		return "redirect:/users/listeroles";
	}
	
	@RequestMapping(value="supprimerrole", method=RequestMethod.GET)
	public String supprimerrole(Model model, @RequestParam("role")String role) {	
		rolerepository.deleteById(role);
		return "redirect:/users/listeroles"; 
	}
	
	@RequestMapping(value="listeprivilegebyrole", method=RequestMethod.GET)
	public String listeprivilegebyrole(Model model, @RequestParam("role")String role) {		
		model.addAttribute("role", rolerepository.getOne(role));
		return "gestionutilisateur/listeprivilegebyrole";
	}

}
