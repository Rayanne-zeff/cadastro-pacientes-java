package br.com.springboot.cadastropacientes.repository;

import br.com.springboot.cadastropacientes.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
