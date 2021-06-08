package org.generation.lojadegames.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.generation.lojadegames.model.UserLogin;
import org.generation.lojadegames.model.Usuario;
import org.generation.lojadegames.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	//metodo para o usuario acessar o endpoint de login
	@PostMapping("/logar")
	public ResponseEntity<org.generation.lojadegames.model.UserLogin> Autentication(@RequestBody Optional<UserLogin> user){
		
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp))
									     .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	//metodo para o usuario acessar o endpoint de cadastrar
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario){
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.CadastrarUsuario(usuario));
	}
	
	
}
