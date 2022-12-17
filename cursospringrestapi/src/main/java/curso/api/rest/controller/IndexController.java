package curso.api.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;

@RestController/*Arquitetura REST*/
@RequestMapping(value = "/usuario")
public class IndexController {
	
	/*Serviço de RESTful*/
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> init() {
		
		Usuario usuario = new Usuario();
		usuario.setId(50L);
		usuario.setLogin("guilhermefroes26@gmail.com");
		usuario.setNome("Guilherme Froes");
		usuario.setSenha("123456");
		
		Usuario usuario2 = new Usuario();
		usuario2.setId(8L);
		usuario2.setLogin("francioliveira@gmail.com");
		usuario2.setNome("Fran Froes");
		usuario2.setSenha("654321");
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario);
		usuarios.add(usuario2);
		
		return new ResponseEntity(usuarios, HttpStatus.OK);
	}

}
