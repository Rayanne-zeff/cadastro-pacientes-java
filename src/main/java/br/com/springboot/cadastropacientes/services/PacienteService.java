package br.com.springboot.cadastropacientes.services;

import br.com.springboot.cadastropacientes.models.Paciente;
import br.com.springboot.cadastropacientes.repository.PacienteRepository;
import br.com.springboot.cadastropacientes.servicesInterface.PacienteServiceInterface;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;


@ComponentScan
@EnableTransactionManagement
@Service
public class PacienteService  implements PacienteServiceInterface {

    private EntityManager entityManager;
    private PacienteRepository pacienteRepository;

    public PacienteService(EntityManagerFactory entityManagerFactory, PacienteRepository pacienteRepository) {
        this.entityManager = entityManagerFactory.createEntityManager();;
        this.pacienteRepository = pacienteRepository;
    }

    public PacienteRepository getPacienteRepository(){
        return this.pacienteRepository;
    }

    public List<Paciente> getAll(){
        return this.getPacienteRepository().findAll();
    }

    public Paciente getPaciente(Long pessoaId){
        return this.getPacienteRepository().findById(pessoaId).orElse(null);
    }

    public Paciente save(Paciente paciente) throws RuntimeException {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(paciente);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return paciente;
    }

    public Paciente edit(Paciente paciente) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            paciente.setPacienteDataAlteracao(dataAtual);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return paciente;
    }

    public boolean remove(Paciente paciente){
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(this.entityManager.merge(paciente));
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return true;
    }
}
