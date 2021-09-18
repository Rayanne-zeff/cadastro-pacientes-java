package br.com.springboot.cadastropacientes.repository;

import br.com.springboot.cadastropacientes.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
}
