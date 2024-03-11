package james.developer.restaurante.controller;

import org.springframework.data.domain.Sort;

import java.util.Optional;

import javax.validation.Valid;
import james.developer.restaurante.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
import james.developer.restaurante.repository.UsuarioRepository;
import james.developer.restaurante.service.ImplementacaoUserDetailsSercice;
import java.io.*;
@RestController /* Arquitetura REST */
@RequestMapping(value = "/adm")

public class AdmController {

	@Autowired /* de fosse CDI seria @Inject */
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private ImplementacaoUserDetailsSercice implementacaoUserDetailsSercice;
	
	Usuario usuarioNome;
	
	@PostMapping(value = "/", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Usuario> salvar(@RequestBody @Valid Usuario usuario) {

		
		String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhacriptografada);
		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		implementacaoUserDetailsSercice.insereAcessoPadrao(usuarioSalvo.getId());

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);

	}
	
	@GetMapping(value = "/", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Page<Usuario>> usuario() throws InterruptedException {

		PageRequest page = PageRequest.of(0, 10, Sort.by("nome"));

		Page<Usuario> list = usuarioRepository.findAll(page);

		return new ResponseEntity<Page<Usuario>>(list, HttpStatus.OK);
	}

	
	
	@GetMapping(value = "/buscar", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Usuario> buscarUsuarioLogado() throws InterruptedException {
		
		
	System.out.println("okkkkkkkkkkkk");

	Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
	
	if(!(autenticado instanceof AnonymousAuthenticationToken)) {
		
		String login = autenticado.getName();
		usuarioNome = usuarioRepository.buscarUsuarioEmail(login).get(0);
		
		System.out.println("ohhhhhhhhh");
		
	System.out.println(usuarioNome.toString());
	
		
	}
	
	return new ResponseEntity<Usuario>(usuarioNome, HttpStatus.OK);
	}
	
	

	
	@GetMapping(value = "/qnt", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Long> qtdusuario() throws InterruptedException {

		

		Long quantdeUser =  usuarioRepository.buscarQuantDeUser();

		return new ResponseEntity<Long>(quantdeUser, HttpStatus.OK);
	}
	

	
	
	@PutMapping(value = "/update", produces = "application/json")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {

	System.out.println("Até aqui foi.");

		Usuario userTemporario = usuarioRepository.findById(usuario.getId()).get();

		if (!userTemporario.getSenha().equals(usuario.getSenha())) { /* Senhas diferentes */
			String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
			usuario.setSenha(senhacriptografada);
		}

		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);

	}
	
	@GetMapping(value = "/{id}", produces = "application/json")
	@CachePut("cacheuser")
	public ResponseEntity<Usuario> init(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete(@PathVariable("id") Long id) {

		usuarioRepository.deleteById(id);

		return "ok";
	}

	@GetMapping(value = "/page/{pagina}", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Page<Usuario>> usuarioPagina(@PathVariable("pagina") int pagina) throws InterruptedException {

		PageRequest page = PageRequest.of(pagina, 20, Sort.by("nome"));

		Page<Usuario> list = usuarioRepository.findAll(page);

		return new ResponseEntity<Page<Usuario>>(list, HttpStatus.OK);
	}
	
	

	
}
