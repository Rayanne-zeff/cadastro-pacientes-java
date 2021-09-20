package br.com.springboot.cadastropacientes.services;


import br.com.springboot.cadastropacientes.models.Pessoa;
import br.com.springboot.cadastropacientes.models.Usuario;
import br.com.springboot.cadastropacientes.repository.UsuarioRepository;
import br.com.springboot.cadastropacientes.servicesInterface.PessoaServiceInterface;
import br.com.springboot.cadastropacientes.servicesInterface.UsuarioServiceInterface;
import javassist.NotFoundException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;


/**
 * Serviço responsável por gerenciar as informações de usuários
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

    /**
     * Construtor da classe
     * @param EntityManagerFactory entityManagerFactory
     * @param UsuarioRepository usuarioRepository
     * @param PessoaServiceInterface pessoaService
     */
    public UsuarioService(EntityManagerFactory entityManagerFactory, UsuarioRepository usuarioRepository, PessoaServiceInterface pessoaService) {
        this.entityManager = entityManagerFactory.createEntityManager();;
        this.usuarioRepository = usuarioRepository;
        this.pessoaService = pessoaService;
    }

    /**
     * Retornar o repositório
     * @return UsuarioRepository
     */
    public UsuarioRepository getUsuarioRepository(){
        return this.usuarioRepository;
    }

    /**
     * Retornar todos registros
     * @return List<Usuario>
     */
    public List<Usuario> getAll(){
        return this.getUsuarioRepository().findAll();
    }

    /**
     * Retornar apenas um registro
     * @param Long pessoaId
     * @return Usuario
     */
    public Usuario getUsuario(Long pessoaId){
        return this.getUsuarioRepository().findById(pessoaId).orElse(null);
    }

    /**
     * Função responsável por salvar um registro
     * @param Usuario enfermeiro
     * @return Usuario
     */
    public Usuario save(Usuario usuario) throws RuntimeException {
        try {
            Pessoa pessoa = pessoaService.getPessoa(usuario.getPessoa().getPessoaId());

            if (pessoa == null) {
                throw new NotFoundException("A pessoa informada não existe!");
            }

            usuario.setPessoa(pessoa);

            usuario.setUsuarioSenha(new BCryptPasswordEncoder().encode(usuario.getUsuarioSenha()));

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

    /**
     * Função responsável por editar um registro
     * @param Usuario objEnfermeiro
     * @param Usuario enfermeiro
     * @return Usuario
     */
    public Usuario edit(Usuario objUsuario, Usuario usuario) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            objUsuario.setUsuarioDataAlteracao(dataAtual);

            if (usuario.getUsuarioLogin() != null) {
                objUsuario.setUsuarioLogin(usuario.getUsuarioLogin());
            }

            if (usuario.getUsuarioSenha() != null) {
                objUsuario.setUsuarioSenha(new BCryptPasswordEncoder().encode(usuario.getUsuarioSenha()));
            }


            this.entityManager.merge(objUsuario);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return usuario;
    }

    /**
     * Função responsável por remover um registro
     * @param Usuario enfermeiro
     * @return boolean
     */
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
