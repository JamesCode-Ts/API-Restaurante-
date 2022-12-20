package james.developer.restaurante.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import james.developer.restaurante.model.Contato;
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
	
}