package br.com.springboot.cadastropacientes.services;


import br.com.springboot.cadastropacientes.models.Pessoa;
import br.com.springboot.cadastropacientes.models.PessoaTipo;
import br.com.springboot.cadastropacientes.repository.PessoaRepository;
import br.com.springboot.cadastropacientes.servicesInterface.PessoaServiceInterface;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
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

            String pessoaCpf = pessoa.getPessoaCpfBase().replaceAll("[^\\d ]", "");
            pessoa.setPessoaCpf(this.criptografarCpf(pessoaCpf));

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

            if (pessoa.getPessoaCpfBase() != null) {
                String pessoaCpf = pessoa.getPessoaCpfBase().replaceAll("[^\\d ]", "");
                objPessoa.setPessoaCpf(this.criptografarCpf(pessoaCpf));
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

        return objPessoa;
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

    private String criptografarCpf(String pessoaCpf) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("9592f001-7c7a-4182-8aa5-04301cc41f9b");
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        return encryptor.encrypt(pessoaCpf.replaceAll("[^\\d ]", ""));
    }

    public String descriptografarCpf(String pessoaCpf) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("9592f001-7c7a-4182-8aa5-04301cc41f9b");
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        return encryptor.decrypt(pessoaCpf);
    }
}
