package james.developer.restaurante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import james.developer.restaurante.model.Usuario;
import james.developer.restaurante.repository.UsuarioRepository;


@Service
public class ImplementacaoUserDetailsSercice implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		/*Consulta no banco o usuario*/
		
		Usuario usuario = usuarioRepository.findUserByLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado");
		}
		
		return new User(usuario.getLogin(),
				usuario.getPassword(),
				usuario.getAuthorities());
	}

	 public void insereAcessoPadrao(Long id) {
		  
		  //Descobre qual a constraint de restricao
		  String constraint = usuarioRepository.consultaConstraintRole();
		  
		  if (constraint != null) {
			  //Remove a constraint
			  jdbcTemplate.execute(" alter table usuarios_role  DROP CONSTRAINT " + constraint);
		  }
		  
	
		  usuarioRepository.insereAcessoRolePadrao(id);
		  

	}
}
