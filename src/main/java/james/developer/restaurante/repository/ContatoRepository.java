package james.developer.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import james.developer.restaurante.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository <Contato, Long> {
	

}
