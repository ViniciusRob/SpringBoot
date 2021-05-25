package br.com.generation.exercicio2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercicio2")
public class Exercicio2Controller {
	
	@GetMapping
	public String exercicio2() {
		return "Objetivos de aprendizagem da semana: evoluir na programação Java, dominar a ferramenta Spring e adiantar o processo do projeto integrador!";
	}
}