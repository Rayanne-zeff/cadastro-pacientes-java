package br.com.springboot.cadastropacientes.repository;

import br.com.springboot.cadastropacientes.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, Long> {
}
