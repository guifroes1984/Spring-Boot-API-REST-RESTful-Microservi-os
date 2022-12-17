package curso.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController/*Arquitetura REST*/
@RequestMapping(value = "/usuario")
public class IndexController {
	
	/*Serviço de RESTful*/
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity init(@RequestParam(value = "nome", required = true) String nome, @RequestParam(value = "salario") Long salario) {
		
		System.out.println("Parametro sendo recebido " + nome + "paramentro salario " + salario);
		
		return new ResponseEntity<>("Olá Usuário REST Spring Boot seu nome é: " + nome + " salario de R$: " + salario, HttpStatus.OK);
	}

}
