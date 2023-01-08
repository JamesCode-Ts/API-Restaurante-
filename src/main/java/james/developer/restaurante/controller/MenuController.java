package james.developer.restaurante.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import james.developer.restaurante.model.Menu;
import james.developer.restaurante.model.Usuario;
import james.developer.restaurante.repository.MenuRepository;

@RestController /* Arquitetura REST */
@RequestMapping(value = "/menu")
public class MenuController {
	
	
	@Autowired
	private MenuRepository menuRepository;
	
	@PostMapping(value = "/", produces = "application/json")
	@CachePut("cachereservas")
	public ResponseEntity<Menu> cadastrarMenu(@RequestBody @Valid Menu menu) {

		


		Menu menuSalvo = menuRepository.save(menu);



		return new ResponseEntity<Menu>(menuSalvo, HttpStatus.OK);

	}

	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cachemenu")
	public ResponseEntity<Page<Menu>> menu() throws InterruptedException {

		PageRequest page = PageRequest.of(0, 5);

		Page<Menu> list = menuRepository.findAll(page);

		return new ResponseEntity<Page<Menu>>(list, HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/qnt", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Long> qtdMenu() throws InterruptedException {

		

		Long quantdeMenu =  menuRepository.buscarQuantDeMenu();

		return new ResponseEntity<Long>(quantdeMenu, HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Menu> atualizar(@RequestBody Menu menu) {

	


		Menu menuSalvo = menuRepository.save(menu);

		return new ResponseEntity<Menu>(menuSalvo, HttpStatus.OK);

	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	@CachePut("cacheuser")
	public ResponseEntity<Menu> init(@PathVariable(value = "id") Long id) {

		Optional<Menu> menu = menuRepository.findById(id);

		return new ResponseEntity<Menu>(menu.get(), HttpStatus.OK);
	}

	
	

	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {

		menuRepository.deleteById(id);

		return "ok";
	}
	
	

}
