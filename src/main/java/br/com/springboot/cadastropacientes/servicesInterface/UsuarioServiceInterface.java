package br.com.springboot.cadastropacientes.servicesInterface;

import br.com.springboot.cadastropacientes.models.Paciente;
import br.com.springboot.cadastropacientes.models.Usuario;
import br.com.springboot.cadastropacientes.repository.PacienteRepository;
import br.com.springboot.cadastropacientes.repository.UsuarioRepository;

import java.util.List;

/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

public interface UsuarioServiceInterface {

    public UsuarioRepository getUsuarioRepository();

    public Usuario save(Usuario usuario);

    public Usuario edit(Usuario usuario);

    public boolean remove(Usuario usuario);

    public List<Usuario> getAll();

    public Usuario getUsuario(Long usuarioId);
}
