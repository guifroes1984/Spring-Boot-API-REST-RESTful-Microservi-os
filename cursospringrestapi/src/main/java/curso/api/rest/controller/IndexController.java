package curso.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

@RestController/*Arquitetura REST*/
@RequestMapping(value = "/usuario")
public class IndexController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping(value = "/{id}/codigovenda/{venda}", produces = "application/json")
	public ResponseEntity<Usuario> relatorio(@PathVariable (value = "id") Long id
										   , @PathVariable (value = "venda") Long venda) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		/*O retorno seria um relatório*/
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}
	
	/*Serviço de RESTful*/
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Usuario> init(@PathVariable (value = "id") Long id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario() {
		
		List<Usuario> lista = (List<Usuario>) usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		
		/*Outras rotinas antes de atualizar*/
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
		
	}
	
	/*Estes 2 são só exemplos*/
	
	@PutMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json")
	public ResponseEntity<Usuario> updateVenda(@PathVariable Long iduser,
												  @PathVariable Long idvenda) {
		
		/*Outras rotinas antes de atualizar*/
		
		//Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity("Venda atualizada", HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/{iduser}/idvenda/{idvenda}", produces = "application/json")
	public ResponseEntity<Usuario> cadastrarvenda(@PathVariable Long iduser,
												  @PathVariable Long idvenda) {
		
		/*Aqui seria o processo de venda*/
		//Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return new ResponseEntity("id user: " + iduser + " idvenda: " + idvenda, HttpStatus.OK);
		
	}

}
