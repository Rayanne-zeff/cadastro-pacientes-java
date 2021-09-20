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
 * Serviço responsável por gerenciar as informações de médicos
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@ComponentScan
@EnableTransactionManagement
@Service
public class MedicoService implements MedicoServiceInterface {


    private EntityManager entityManager;
    private MedicoRepository medicoRepository;

    /**
     * Construtor da classe
     * @param EntityManagerFactory entityManagerFactory
     * @param MedicoRepository enfermeiroRepository
     */
    public MedicoService(EntityManagerFactory entityManagerFactory, MedicoRepository medicoRepository) {
        this.entityManager = entityManagerFactory.createEntityManager();;
        this.medicoRepository = medicoRepository;
    }

    /**
     * Retornar o repositório
     * @return MedicoRepository
     */
    public MedicoRepository getMedicoRepository(){
        return this.medicoRepository;
    }

    /**
     * Retornar todos registros
     * @return List<Medico>
     */
    public List<Medico> getAll(){
        return this.getMedicoRepository().findAll();
    }

    /**
     * Retornar apenas um registro
     * @param Long pessoaId
     * @return Medico
     */
    public Medico getMedico(Long pessoaId){
        return this.getMedicoRepository().findById(pessoaId).orElse(null);
    }

    /**
     * Função responsável por salvar um registro
     * @param Medico enfermeiro
     * @return Medico
     */
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

    /**
     * Função responsável por editar um registro
     * @param Medico objEnfermeiro
     * @param Medico enfermeiro
     * @return Medico
     */
    public Medico edit(Medico objMedico, Medico medico) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            objMedico.setMedicoDataAlteracao(dataAtual);

            if (medico.getMedicoCrm() != null) {
                objMedico.setMedicoCrm(medico.getMedicoCrm());
            }

            this.entityManager.merge(objMedico);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return medico;
    }

    /**
     * Função responsável por remover um registro
     * @param Medico enfermeiro
     * @return boolean
     */
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
