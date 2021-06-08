package org.generation.lojadegames.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.generation.lojadegames.model.UserLogin;
import org.generation.lojadegames.model.Usuario;
import org.generation.lojadegames.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	//Injetando a classe de repository
	@Autowired
	private UsuarioRepository repository;
	
	//Método para mandar a senha do usuario encriptada quando for cadastrar
	public Usuario CadastrarUsuario(Usuario usuario) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		
		return repository.save(usuario);
	}
	
	//metodo para autenticar o login
	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario.isPresent()) { //verifica se existe o nome de usuario no banco
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) { //verifica se a senha digitada combina com a armazenada (ambas encriptadas)
				
				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				
				user.get().setToken(authHeader);
				user.get().setNome(usuario.get().getNome());
				user.get().setAdministrador(usuario.get().isAdministrador());
				return user;
			}
		}
		
		return null;
	}
}
