package br.com.springboot.cadastropacientes.services;


import br.com.springboot.cadastropacientes.models.Pessoa;
import br.com.springboot.cadastropacientes.models.PessoaTipo;
import br.com.springboot.cadastropacientes.models.Usuario;
import br.com.springboot.cadastropacientes.repository.PessoaRepository;
import br.com.springboot.cadastropacientes.repository.UsuarioRepository;
import br.com.springboot.cadastropacientes.servicesInterface.PessoaServiceInterface;
import br.com.springboot.cadastropacientes.servicesInterface.UsuarioServiceInterface;
import javassist.NotFoundException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;


/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@ComponentScan
@EnableTransactionManagement
@Service
public class UsuarioService implements UsuarioServiceInterface {

    private EntityManager entityManager;
    private UsuarioRepository usuarioRepository;
    private PessoaServiceInterface pessoaService;

    public UsuarioService(EntityManagerFactory entityManagerFactory, UsuarioRepository usuarioRepository, PessoaServiceInterface pessoaService) {
        this.entityManager = entityManagerFactory.createEntityManager();;
        this.usuarioRepository = usuarioRepository;
        this.pessoaService = pessoaService;
    }

    public UsuarioRepository getUsuarioRepository(){
        return this.usuarioRepository;
    }

    public List<Usuario> getAll(){
        return this.getUsuarioRepository().findAll();
    }

    public Usuario getUsuario(Long pessoaId){
        return this.getUsuarioRepository().findById(pessoaId).orElse(null);
    }

    public Usuario save(Usuario usuario) throws RuntimeException {
        try {
            Pessoa pessoa = pessoaService.getPessoa(usuario.getPessoa().getPessoaId());

            if (pessoa == null) {
                throw new NotFoundException("A pessoa informada não existe!");
            }

            usuario.setPessoa(pessoa);

            this.entityManager.getTransaction().begin();
            this.entityManager.persist(usuario);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return usuario;
    }

    public Usuario edit(Usuario usuario) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            usuario.setUsuarioDataAlteracao(dataAtual);
            this.entityManager.persist(usuario);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return usuario;
    }

    public boolean remove(Usuario usuario){
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(this.entityManager.merge(usuario));
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return true;
    }
}
