package br.com.springboot.cadastropacientes.servicesInterface;


import br.com.springboot.cadastropacientes.models.Pessoa;
import br.com.springboot.cadastropacientes.repository.PessoaRepository;

import java.util.List;

/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

public interface PessoaServiceInterface {

    public PessoaRepository getPessoaRepository();

    public Pessoa save(Pessoa pessoa);

    public Pessoa edit(Pessoa objPessoa,Pessoa pessoa);

    public boolean remove(Pessoa pessoa);

    public List<Pessoa> getAll();

    public Pessoa getPessoa(Long pessoaId);
}
