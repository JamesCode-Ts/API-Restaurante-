package james.developer.restaurante.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import james.developer.restaurante.model.Menu;
import james.developer.restaurante.model.Reserva;
import james.developer.restaurante.model.Usuario;
import james.developer.restaurante.repository.MenuRepository;
import james.developer.restaurante.repository.ReservaRepository;
import james.developer.restaurante.repository.UsuarioRepository;
import james.developer.restaurante.service.ImplementacaoUserDetailsSercice;


@RestController /* Arquitetura REST */
@RequestMapping(value = "/home")
public class HomeController {
	
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired /* de fosse CDI seria @Inject */
	private UsuarioRepository usuarioRepository;
	
	
	
	
	@GetMapping(value = "/reserva/{id}", produces = "application/json")
	@CachePut("cachereserva")
	public ResponseEntity<Reserva> init(@PathVariable(value = "id") Long id) {
		
		Optional<Reserva> reserva = reservaRepository.findById(id);
		
		return new ResponseEntity<Reserva>(reserva.get(), HttpStatus.OK);
		
	}
	

	
	@PostMapping(value = "/", produces = "application/json")
	@CachePut("cachereservas")
	public ResponseEntity<Reserva> cadastrar(@RequestBody @Valid Reserva reserva) {

		


		Reserva reservaSalvo = reservaRepository.save(reserva);



		return new ResponseEntity<Reserva>(reservaSalvo, HttpStatus.OK);

	}
	
	
	@GetMapping(value = "/qnt", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Long> qntReserva() throws InterruptedException {

		

		Long quantdeReserva =  reservaRepository.buscarQuantDeReserva();

		return new ResponseEntity<Long>(quantdeReserva, HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cachemenu")
	public ResponseEntity<Page<Menu>> menu() throws InterruptedException {

		PageRequest page = PageRequest.of(0, 5);

		Page<Menu> list = menuRepository.findAll(page);

		return new ResponseEntity<Page<Menu>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/reserva", produces = "application/json")
	@CachePut("cachemenu")
	public ResponseEntity<Page<Reserva>> reserva() throws InterruptedException {

		PageRequest page = PageRequest.of(0, 10, Sort.by("nome"));

		Page<Reserva> list = reservaRepository.findAll(page);

		return new ResponseEntity<Page<Reserva>>(list, HttpStatus.OK);
	}
	

	@GetMapping(value = "/page/{pagina}", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Page<Reserva>> reservaPagina(@PathVariable("pagina") int pagina) throws InterruptedException {

		PageRequest page = PageRequest.of(pagina, 5, Sort.by("nome"));

		Page<Reserva> list = reservaRepository.findAll(page);

		return new ResponseEntity<Page<Reserva>>(list, HttpStatus.OK);
	}


	
}



	
	
	


