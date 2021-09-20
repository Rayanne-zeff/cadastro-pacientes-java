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


/**
 * Serviço responsável por gerenciar as informações de pacientes
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@ComponentScan
@EnableTransactionManagement
@Service
public class PacienteService  implements PacienteServiceInterface {

    private EntityManager entityManager;
    private PacienteRepository pacienteRepository;

    /**
     * Construtor da classe
     * @param EntityManagerFactory entityManagerFactory
     * @param PacienteRepository enfermeiroRepository
     */
    public PacienteService(EntityManagerFactory entityManagerFactory, PacienteRepository pacienteRepository) {
        this.entityManager = entityManagerFactory.createEntityManager();;
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Retornar o repositório
     * @return PacienteRepository
     */
    public PacienteRepository getPacienteRepository(){
        return this.pacienteRepository;
    }

    /**
     * Retornar todos registros
     * @return List<Paciente>
     */
    public List<Paciente> getAll(){
        return this.getPacienteRepository().findAll();
    }

    /**
     * Retornar apenas um registro
     * @param Long pessoaId
     * @return Paciente
     */
    public Paciente getPaciente(Long pessoaId){
        return this.getPacienteRepository().findById(pessoaId).orElse(null);
    }

    /**
     * Função responsável por salvar um registro
     * @param Paciente enfermeiro
     * @return Paciente
     */
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

    /**
     * Função responsável por editar um registro
     * @param Paciente objEnfermeiro
     * @param Paciente enfermeiro
     * @return Paciente
     */
    public Paciente edit(Paciente objPaciente, Paciente paciente) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            objPaciente.setPacienteDataAlteracao(dataAtual);

            if (paciente.getPacienteUF() != null) {
                objPaciente.setPacienteUF(paciente.getPacienteUF());
            }

            if (paciente.getPacienteAltura() != null) {
                objPaciente.setPacienteAltura(paciente.getPacienteAltura());
            }

            if (paciente.getPacienteAltura() != null) {
                objPaciente.setPacienteAltura(paciente.getPacienteAltura());
            }

            if (paciente.getPacienteDataNascimento() != null) {
                objPaciente.setPacienteDataNascimento(paciente.getPacienteDataNascimento());
            }

            this.entityManager.merge(objPaciente);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return paciente;
    }

    /**
     * Função responsável por remover um registro
     * @param Paciente enfermeiro
     * @return boolean
     */
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
