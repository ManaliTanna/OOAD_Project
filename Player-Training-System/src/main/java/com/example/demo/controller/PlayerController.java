package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Player;
import com.example.demo.service.PlayerService;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PlayerController {
	
	@Autowired
	private PlayerService service;
	
	@GetMapping("/")
	public String home(Model m)
	{
		List<Player> players = service.getAllPlayers();
		m.addAttribute("players", players);
		return "index";
	}
	
	@GetMapping("/addplayer")
	public String addPlayerForm()
	{
		return "add_player";
	}
	
	@PostMapping("/register")
	public String playerRegister(@ModelAttribute Player p, HttpSession session)
	{
		System.out.println(p);
		service.addPlayer(p);
		session.setAttribute("msg", "Registered successfully !");
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String editPlayer(@PathVariable int id, Model m)
	{
		Player p = service.getPlayerById(id);
		m.addAttribute("players", p);
		return "edit";
	}
	
	@PostMapping("/update")
	public String playerUpdate(@ModelAttribute Player p, HttpSession session)
	{
		System.out.println(p);
		service.addPlayer(p);
		session.setAttribute("msg", "Changes saved successfully !");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String playerDelete(@PathVariable int id, HttpSession session)
	{
		service.deletePlayer(id);
		session.setAttribute("msg", "Record deleted successfully !");
		return "redirect:/";
	}

}
