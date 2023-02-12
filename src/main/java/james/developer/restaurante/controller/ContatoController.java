package james.developer.restaurante.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import james.developer.restaurante.model.Contato;
import james.developer.restaurante.model.Menu;
import james.developer.restaurante.repository.ContatoRepository;

@RestController /* Arquitetura REST */
@RequestMapping(value = "/contato")
public class ContatoController {
	


	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@PostMapping(value = "/", produces = "application/json")
	@CachePut("cachecontato")
	public ResponseEntity<Contato> salvarContato(@RequestBody @Valid Contato contato) {

		


		Contato contatoSalvo = contatoRepository.save(contato);



		return new ResponseEntity<Contato>(contatoSalvo, HttpStatus.OK);

}
	
	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cachemenu")
	public ResponseEntity<Page<Contato>> menu() throws InterruptedException {

		PageRequest page = PageRequest.of(0, 5);

		Page<Contato> list = contatoRepository.findAll(page);

		return new ResponseEntity<Page<Contato>>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/qnt", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Long> qtdMenu() throws InterruptedException {

		

		Long quantdeContato =  contatoRepository.buscarQuantDeContato();

		return new ResponseEntity<Long>(quantdeContato, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Contato> atualizar(@RequestBody Contato contato) {

	


		Contato contatoSalvo = contatoRepository.save(contato);

		return new ResponseEntity<Contato>(contatoSalvo, HttpStatus.OK);

	}
	
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {

		contatoRepository.deleteById(id);

		return "ok";
	}
}