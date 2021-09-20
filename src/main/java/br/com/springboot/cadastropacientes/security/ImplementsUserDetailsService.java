package br.com.springboot.cadastropacientes.security;

import br.com.springboot.cadastropacientes.models.Usuario;
import br.com.springboot.cadastropacientes.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Gloria Rayane
 * @since : 19/09/2021
 */

@Component
@Transactional
class ImplementsUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuarioModel = usuarioRepository.findByUsuarioLogin(login);

        if (usuarioModel == null) {
            throw new UsernameNotFoundException("O usuário: "+ login +" não foi encontrado!");
        }

        return new User(usuarioModel.getUsuarioLogin(), usuarioModel.getUsuarioSenha(), true, true, true, true, usuarioModel.getAuthorities());
    }
}
