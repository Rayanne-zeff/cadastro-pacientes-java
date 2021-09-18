package br.com.springboot.cadastropacientes.servicesInterface;

import br.com.springboot.cadastropacientes.models.Paciente;
import br.com.springboot.cadastropacientes.repository.PacienteRepository;

import java.util.List;

/**
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */

public interface PacienteServiceInterface {

    public PacienteRepository getPacienteRepository();

    public Paciente save(Paciente paciente);

    public Paciente edit(Paciente paciente);

    public boolean remove(Paciente paciente);

    public List<Paciente> getAll();

    public Paciente getPaciente(Long pessoaId);
}
