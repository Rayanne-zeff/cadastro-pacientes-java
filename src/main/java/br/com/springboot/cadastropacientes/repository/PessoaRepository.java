package br.com.springboot.cadastropacientes.repository;

import br.com.springboot.cadastropacientes.models.Pessoa;
import br.com.springboot.cadastropacientes.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    public Pessoa findByPessoaCpf(String pessoaCpf);
}
