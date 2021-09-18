package br.com.springboot.cadastropacientes.repository;

import br.com.springboot.cadastropacientes.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
}
