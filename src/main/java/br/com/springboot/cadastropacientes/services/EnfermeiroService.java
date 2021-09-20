package br.com.springboot.cadastropacientes.services;

import br.com.springboot.cadastropacientes.models.Enfermeiro;
import br.com.springboot.cadastropacientes.repository.EnfermeiroRepository;
import br.com.springboot.cadastropacientes.servicesInterface.EnfermeiroServiceInterface;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;


/**
 * Serviço responsável por gerenciar as informações de enfermeiros
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@ComponentScan
@EnableTransactionManagement
@Service
public class EnfermeiroService implements EnfermeiroServiceInterface {

    private EntityManager entityManager;
    private EnfermeiroRepository  enfermeiroRepository;

    /**
     * Construtor da classe
     * @param EntityManagerFactory entityManagerFactory
     * @param EnfermeiroRepository enfermeiroRepository
     */
    public EnfermeiroService(EntityManagerFactory entityManagerFactory, EnfermeiroRepository enfermeiroRepository) {
        this.entityManager = entityManagerFactory.createEntityManager();;
        this.enfermeiroRepository = enfermeiroRepository;
    }

    /**
     * Retornar o repositório
     * @return EnfermeiroRepository
     */
    public EnfermeiroRepository getEnfermeiroRepository(){
        return this.enfermeiroRepository;
    }

    /**
     * Retornar todos registros
     * @return List<Enfermeiro>
     */
    public List<Enfermeiro> getAll(){
        return this.getEnfermeiroRepository().findAll();
    }

    /**
     * Retornar apenas um registro
     * @param Long pessoaId
     * @return Enfermeiro
     */
    public Enfermeiro getEnfermeiro(Long pessoaId){
        return this.getEnfermeiroRepository().findById(pessoaId).orElse(null);
    }

    /**
     * Função responsável por salvar um registro
     * @param Enfermeiro enfermeiro
     * @return Enfermeiro
     */
    public Enfermeiro save(Enfermeiro enfermeiro) throws RuntimeException {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(enfermeiro);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return enfermeiro;
    }

    /**
     * Função responsável por editar um registro
     * @param Enfermeiro objEnfermeiro
     * @param Enfermeiro enfermeiro
     * @return Enfermeiro
     */
    public Enfermeiro edit(Enfermeiro objEnfermeiro, Enfermeiro enfermeiro) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            objEnfermeiro.setEnfermeiroDataAlteracao(dataAtual);

            if (enfermeiro.getEnfermeiroCre() != null) {
                objEnfermeiro.setEnfermeiroCre(enfermeiro.getEnfermeiroCre());
            }

            this.entityManager.merge(objEnfermeiro);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return enfermeiro;
    }

    /**
     * Função responsável por remover um registro
     * @param Enfermeiro enfermeiro
     * @return boolean
     */
    public boolean remove(Enfermeiro enfermeiro){
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(this.entityManager.merge(enfermeiro));
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return true;
    }
}
