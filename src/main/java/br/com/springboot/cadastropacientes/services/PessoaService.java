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
import java.util.Date;
import java.util.List;

@ComponentScan
@EnableTransactionManagement
@Service
public class PessoaService implements PessoaServiceInterface {


    private EntityManager entityManager;
    private PessoaRepository pessoaRepository;

    public PessoaService(EntityManagerFactory entityManagerFactory, PessoaRepository pessoaRepository) {
        this.entityManager = entityManagerFactory.createEntityManager();;
        this.pessoaRepository = pessoaRepository;
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
                pessoa.setPessoaTipo(PessoaTipo.MEDICO);
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

    public Pessoa edit(Pessoa pessoa) throws RuntimeException{
        try {
            this.entityManager.getTransaction().begin();
            Date dataAtual = new Date();
            pessoa.setPessoaDataAlteracao(dataAtual);
            this.entityManager.flush();
        } catch (Exception ex) {
            this.entityManager.getTransaction().rollback();
            throw new RuntimeException(ex.getMessage());
        }
        this.entityManager.getTransaction().commit();

        return pessoa;
    }

    public boolean remove(Pessoa pessoa){
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
