package br.com.springboot.cadastropacientes.repository;

import br.com.springboot.cadastropacientes.models.Enfermeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnfermeiroRepository extends JpaRepository<Enfermeiro, Long> {

}
