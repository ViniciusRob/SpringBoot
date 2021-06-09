package br.org.generation.agenda.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.org.generation.agenda.model.Contato;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContatoControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	private Contato contato;
	private Contato contatoupd;
	
	@BeforeAll
	public void start() {
		contato = new Contato(null, "Maria", "21", "44451198");
		contatoupd = new Contato(2L,"Maria da Silva", "23", "995467892");
	}
	
	@Test
	public void deveRealizarPostContatos() {
		
		HttpEntity<Contato> request = new HttpEntity<Contato>(contato);
		ResponseEntity<Contato> resposta = testRestTemplate
				.exchange("/contatos/inserir", HttpMethod.POST, request, Contato.class);
						Assert.assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Test
	public void deveMostrarTodosContatos() {
		ResponseEntity<String> resposta = testRestTemplate
				.exchange("/contatos/", HttpMethod.GET, null, String.class);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	public void deveRealizarPutContatos() {
		HttpEntity<Contato> request = new HttpEntity<Contato>(contatoupd);
		ResponseEntity<Contato> resposta = testRestTemplate
				.exchange("/contatos/alterar", HttpMethod.PUT, request, Contato.class);
						Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	public void deveRealizarDeleteContatos() {
		ResponseEntity<String> resposta = testRestTemplate
				.exchange("/contatos/3", HttpMethod.DELETE, null, String.class);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
}
