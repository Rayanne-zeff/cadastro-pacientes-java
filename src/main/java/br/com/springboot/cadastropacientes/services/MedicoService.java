package br.com.springboot.cadastropacientes.services;

import br.com.springboot.cadastropacientes.models.Medico;
import br.com.springboot.cadastropacientes.repository.MedicoRepository;
import br.com.springboot.cadastropacientes.servicesInterface.MedicoServiceInterface;
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
public class MedicoService implements MedicoServiceInterface {


    private EntityManager entityManager;
    private MedicoRepository medicoRepository;

    public MedicoService(EntityManagerFactory entityManagerFactory, MedicoRepository medicoRepository) {
        this.entityManager = entityManagerFactory.createEntityManager();;
        this.medicoRepository = medicoRepository;
    }

    public MedicoRepository getMedicoRepository(){
        return this.medicoRepository;
    }

    public List<Medico> getAll(){
        return this.getMedicoRepository().findAll();
    }

    public Medico getMedico(Long pessoaId){
        return this.getMedicoRepository().findById(pessoaId).orElse(null);
    }

    public Medico save(Medico medico) throws RuntimeException {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(medico);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return medico;
    }

    public Medico edit(Medico medico) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            medico.setMedicoDataAlteracao(dataAtual);
            this.entityManager.persist(medico);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return medico;
    }

    public boolean remove(Medico medico){
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(this.entityManager.merge(medico));
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return true;
    }
}
