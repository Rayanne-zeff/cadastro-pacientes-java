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
import java.util.Base64;
import java.util.Date;
import java.util.List;


/**
 * Serviço responsável por gerenciar as informações de pessoas
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

    /**
     * Construtor da classe
     * @param EntityManagerFactory entityManagerFactory
     * @param PessoaRepository pessoaRepository
     * @param  PacienteService pacienteService
     * @param EnfermeiroService enfermeiroService
     */
    public PessoaService(EntityManagerFactory entityManagerFactory, PessoaRepository pessoaRepository, MedicoService medicoService, PacienteService pacienteService, EnfermeiroService enfermeiroService) {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.pessoaRepository = pessoaRepository;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
        this.enfermeiroService = enfermeiroService;
    }

    /**
     * Retornar o repositório
     * @return PessoaRepository
     */
    public PessoaRepository getPessoaRepository(){
        return this.pessoaRepository;
    }

    /**
     * Retornar todos registros
     * @return List<Pessoa>
     */
    public List<Pessoa> getAll(){
        return this.getPessoaRepository().findAll();
    }

    /**
     * Retornar apenas um registro
     * @param Long pessoaId
     * @return Pessoa
     */
    public Pessoa getPessoa(Long pessoaId){
        return this.getPessoaRepository().findById(pessoaId).orElse(null);
    }

    /**
     * Função responsável por salvar um registro
     * @param Pessoa enfermeiro
     * @return Pessoa
     */
    public Pessoa save(Pessoa pessoa) throws RuntimeException {
        try {
            this.entityManager.getTransaction().begin();

            if (pessoa.getPessoaTipo() == null) {
                pessoa.setPessoaTipo(PessoaTipo.Paciente);
            }

            //Remove letras e caracteres, deixando apenas números no cpf
            String pessoaCpf = pessoa.getPessoaCpf().replaceAll("[^\\d ]", "");
            pessoa.setPessoaCpf(this.criptografarCpf(pessoaCpf));

            //Condição responsável por validar se já existem pessoas com o mesmo cpf
            Pessoa pessoaEncontrada = pessoaRepository.findByPessoaCpf(pessoa.getPessoaCpf());
            if (pessoaEncontrada != null) {
                throw new ValidationException("O cpf informado já existe!");
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

    /**
     * Função responsável por editar um registro
     * @param Pessoa objEnfermeiro
     * @param Pessoa enfermeiro
     * @return Pessoa
     */
    public Pessoa edit(Pessoa objPessoa, Pessoa pessoa) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            objPessoa.setPessoaDataAlteracao(dataAtual);

            if (pessoa.getPessoaTipo() != null) {
                objPessoa.setPessoaTipo(pessoa.getPessoaTipo());
            }

            if (pessoa.getPessoaCpf() != null) {
                //Remove letras e caracteres, deixando apenas números no cpf
                String pessoaCpf = pessoa.getPessoaCpf().replaceAll("[^\\d ]", "");
                objPessoa.setPessoaCpf(this.criptografarCpf(pessoaCpf));

                //Condição responsável por validar se já existem pessoas com o mesmo cpf
                Pessoa pessoaEncontrada = pessoaRepository.findByPessoaCpf(objPessoa.getPessoaCpf());
                if (pessoaEncontrada != null && pessoaEncontrada.getPessoaId() != pessoa.getPessoaId()) {
                    throw new ValidationException("O cpf informado já existe!");
                }

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

    /**
     * Função responsável por remover um registro
     * @param Pessoa enfermeiro
     * @return boolean
     */
    public boolean remove(Pessoa pessoa){
        //Condição para validar se o registro de pessoa está sendo utilizado em outras tabelas
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

    /**
     * Função responsável por criptografar o cpf
     * @param String pessoaCpf
     * @return String
     */
    private String criptografarCpf(String pessoaCpf) {
        String originalInput = pessoaCpf.replaceAll("[^\\d ]", "");
        return Base64.getEncoder().encodeToString(originalInput.getBytes());
    }

    /**
     * Função responsável por descriptografar o cpf
     * @param String pessoaCpf
     * @return String
     */
    public String descriptografarCpf(String pessoaCpf) {
        byte[] decodedBytes = Base64.getDecoder().decode(pessoaCpf);
        return new String(decodedBytes);
    }
}
