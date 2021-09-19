package br.com.springboot.cadastropacientes.servicesInterface;


import br.com.springboot.cadastropacientes.models.Medico;
import br.com.springboot.cadastropacientes.repository.MedicoRepository;

import java.util.List;

/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

public interface MedicoServiceInterface {
    public MedicoRepository getMedicoRepository();

    public Medico save(Medico medico);

    public Medico edit(Medico objMedico,Medico medico);

    public boolean remove(Medico medico);

    public List<Medico> getAll();

    public Medico getMedico(Long pessoaId);
}
