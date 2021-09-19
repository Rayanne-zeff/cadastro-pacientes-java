package br.com.springboot.cadastropacientes.services;


import br.com.springboot.cadastropacientes.models.Pessoa;
import br.com.springboot.cadastropacientes.models.PessoaTipo;
import br.com.springboot.cadastropacientes.repository.PessoaRepository;
import br.com.springboot.cadastropacientes.servicesInterface.PessoaServiceInterface;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;


/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

@ComponentScan
@EnableTransactionManagement
@Service
public class PessoaService implements PessoaServiceInterface {


    private EntityManager entityManager;
    private PessoaRepository pessoaRepository;
    private MedicoService medicoService;
    private PacienteService pacienteService;
    private EnfermeiroService enfermeiroService;

    public PessoaService(EntityManagerFactory entityManagerFactory, PessoaRepository pessoaRepository, MedicoService medicoService, PacienteService pacienteService, EnfermeiroService enfermeiroService) {
        this.entityManager = entityManagerFactory.createEntityManager();;
        this.pessoaRepository = pessoaRepository;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
        this.enfermeiroService = enfermeiroService;
    }

    public PessoaRepository getPessoaRepository(){
        return this.pessoaRepository;
    }

    public List<Pessoa> getAll(){
        return this.getPessoaRepository().findAll();
    }

    public Pessoa getPessoa(Long pessoaId){
        return this.getPessoaRepository().findById(pessoaId).orElse(null);
    }

    public Pessoa save(Pessoa pessoa) throws RuntimeException {
        try {
            this.entityManager.getTransaction().begin();

            if (pessoa.getPessoaTipo() == null) {
                pessoa.setPessoaTipo(PessoaTipo.Paciente);
            }

            this.entityManager.persist(pessoa);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return pessoa;
    }

    public Pessoa edit(Pessoa objPessoa, Pessoa pessoa) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            objPessoa.setPessoaDataAlteracao(dataAtual);

            if (pessoa.getPessoaTipo() != null) {
                objPessoa.setPessoaTipo(pessoa.getPessoaTipo());
            }

            if (pessoa.getPessoaCpf() != null) {
                objPessoa.setPessoaCpf(pessoa.getPessoaCpf());
            }

            if (pessoa.getPessoaName() != null) {
                objPessoa.setPessoaName(pessoa.getPessoaName());
            }

            this.entityManager.merge(objPessoa);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return pessoa;
    }

    public boolean remove(Pessoa pessoa){

        if (medicoService.getMedico(pessoa.getPessoaId()) != null || enfermeiroService.getEnfermeiro(pessoa.getPessoaId()) != null || pacienteService.getPaciente(pessoa.getPessoaId()) != null){
            throw new ValidationException("Não foi possível remover a pessoa, a mesma possuí vínculo em outras tabelas!");
        }

        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(this.entityManager.merge(pessoa));
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return true;
    }
}
