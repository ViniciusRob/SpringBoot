package br.org.generation.agenda.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ContatoTest {
	
	public Contato contato;
	
	@Autowired
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	@BeforeEach
	public void start() {
		contato = new Contato(null, "Gabriel", "011y", "9xxxxxxx9");
	}
	
	@Test
	public void testValidationAtributos(){
		contato.setNome("João");
		contato.setDdd("011");
		contato.setTelefone("21837559");
		Set<ConstraintViolation<Contato>> violations = validator
				.validate(contato);
		System.out.println(violations.toString());
		assertTrue(violations.isEmpty());
	}
}
