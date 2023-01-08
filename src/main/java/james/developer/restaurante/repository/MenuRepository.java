package james.developer.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import james.developer.restaurante.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository <Menu, Long> {
	
	
//	@Transactional
//	@Modifying
//	@Query("select * from Menu order by title")
  //  Menu findMenu();
	
	@Query(nativeQuery = true, value = "select count(id) as id  from menu")
	Long buscarQuantDeMenu();

}
