package br.com.springboot.cadastropacientes.repository;

import br.com.springboot.cadastropacientes.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
