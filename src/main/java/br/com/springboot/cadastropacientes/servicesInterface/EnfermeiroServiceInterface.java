package br.com.springboot.cadastropacientes.servicesInterface;

import br.com.springboot.cadastropacientes.models.Enfermeiro;
import br.com.springboot.cadastropacientes.repository.EnfermeiroRepository;

import java.util.List;

/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

public interface EnfermeiroServiceInterface {

    public EnfermeiroRepository getEnfermeiroRepository();

    public Enfermeiro save(Enfermeiro enfermeiro);

    public Enfermeiro edit(Enfermeiro enfermeiro);

    public boolean remove(Enfermeiro enfermeiro);

    public List<Enfermeiro> getAll();

    public Enfermeiro getEnfermeiro(Long pessoaId);
}
