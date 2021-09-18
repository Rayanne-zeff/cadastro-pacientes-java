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
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@ComponentScan
@EnableTransactionManagement
@Service
public class EnfermeiroService implements EnfermeiroServiceInterface {

    private EntityManager entityManager;
    private EnfermeiroRepository  enfermeiroRepository;

    public EnfermeiroService(EntityManagerFactory entityManagerFactory, EnfermeiroRepository enfermeiroRepository) {
        this.entityManager = entityManagerFactory.createEntityManager();;
        this.enfermeiroRepository = enfermeiroRepository;
    }

    public EnfermeiroRepository getEnfermeiroRepository(){
        return this.enfermeiroRepository;
    }

    public List<Enfermeiro> getAll(){
        return this.getEnfermeiroRepository().findAll();
    }

    public Enfermeiro getEnfermeiro(Long pessoaId){
        return this.getEnfermeiroRepository().findById(pessoaId).orElse(null);
    }

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

    public Enfermeiro edit(Enfermeiro enfermeiro) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            enfermeiro.setEnfermeiroDataAlteracao(dataAtual);
            this.entityManager.persist(enfermeiro);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return enfermeiro;
    }

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
