package james.developer.restaurante.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import james.developer.restaurante.model.Usuario;
import james.developer.restaurante.repository.UsuarioRepository;

@RestController /* Arquitetura REST */
@RequestMapping(value = "/adm")

public class AdmController {

	@Autowired /* de fosse CDI seria @Inject */
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping(value = "/", produces = "application/json")
	@CachePut("cacheusuarios")
	public ResponseEntity<Usuario> salvar(@RequestBody @Valid Usuario usuario) {

		
		String senhacriptografada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(senhacriptografada);
		Usuario usuarioSalvo = usuarioRepository.save(usuario);

	//	implementacaoUserDetailsSercice.insereAcessoPadrao(usuarioSalvo.getId());

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);

	}

}
