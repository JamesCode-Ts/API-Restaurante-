package james.developer.restaurante.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import james.developer.restaurante.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository <Reserva, Long>  {
	

	@Query(nativeQuery = true, value = "select count(id) as id  from reserva  ")
	Long buscarQuantDeReserva();

}
