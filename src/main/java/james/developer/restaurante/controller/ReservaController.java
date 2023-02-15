package james.developer.restaurante.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import james.developer.restaurante.model.Reserva;
import james.developer.restaurante.repository.ReservaRepository;

@RestController /* Arquitetura REST */
@RequestMapping(value = "/reserva")
public class ReservaController {

	@Autowired
	private ReservaRepository reservaRepository;
	
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
	
	
	@GetMapping(value = "/", produces = "application/json")
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
	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {

		 reservaRepository.deleteById(id);

		return "ok";
	}
	
	
}
