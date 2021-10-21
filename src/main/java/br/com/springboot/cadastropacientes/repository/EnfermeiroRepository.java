package br.com.springboot.cadastropacientes.repository;

import br.com.springboot.cadastropacientes.models.Enfermeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Interface responsável por realizar a busca e persistência dos dados
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@Repository
public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Long> {
}
