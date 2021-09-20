package br.com.springboot.cadastropacientes.controllers;


import br.com.springboot.cadastropacientes.models.Paciente;
import br.com.springboot.cadastropacientes.response.Retorno;
import br.com.springboot.cadastropacientes.servicesInterface.PacienteServiceInterface;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Controlador responsável por gerenciar as informações de pacientes
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */


@RestController
@RequestMapping("/api/paciente")
@ComponentScan
public class PacienteController extends AbstractController {

    private PacienteServiceInterface pacienteService;

    public PacienteController(PacienteServiceInterface pacienteService){
        this.pacienteService = pacienteService;
    }

    /**
     * Nesta função irá retornar todos os pacientes existentes na base de dados
     * @return ResponseEntity<Object>
     */
    @GetMapping("")
    public ResponseEntity<Object> getPaciente(){
        List<Paciente> pacientes = null;
        try {
            pacientes = pacienteService.getAll();
        } catch (RuntimeException ex){
            return Retorno.generateResponse("Não foi possível listar os pacientes! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }

        return Retorno.generateResponse("Sucesso ao buscar o paciente", HttpStatus.OK, pacientes);
    }

    /**
     * Nesta função irá retornar os dados de um paciente existente na base de dados
     * @return ResponseEntity<Object>
     * @param Long pacienteId
     */
    @GetMapping("/{pacienteId}")
    public ResponseEntity<Object>  getPaciente(@PathVariable Long pacienteId) {
        Paciente objPaciente = null;
        try {
            objPaciente = pacienteService.getPaciente(pacienteId);

            if (objPaciente == null) {
                return Retorno.generateResponse("Não foi possível encontrar o paciente informado!", HttpStatus.NOT_FOUND, null);
            }

        }catch (RuntimeException ex){
            return Retorno.generateResponse("Não foi possível listar o paciente! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Sucesso ao buscar o paciente", HttpStatus.OK, objPaciente);
    }

    /**
     * Está função é responsável por criar o registro de um paciente
     * @param Paciente paciente
     * @return ResponseEntity<Object>
     */
    @PostMapping("")
    public ResponseEntity<Object>  create(@Valid @RequestBody Paciente paciente) {
        Paciente objPaciente = null;
        try {
            objPaciente = pacienteService.save(paciente);
        } catch (RuntimeException ex) {
            return Retorno.generateResponse("Não foi possível salvar o paciente! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Paciente criado com sucesso!", HttpStatus.OK, objPaciente);
    }

    /**
     * Está função é responsável por editar o registro de um paciente
     * @param Paciente paciente
     * @return ResponseEntity<Object>
     */
    @PutMapping("/{pessoaId}")
    public ResponseEntity<Object>  update(@Valid @RequestBody Paciente paciente){
        Paciente objPaciente = null;
        try{
            objPaciente = pacienteService.getPaciente(paciente.getPessoaId());
            if (objPaciente == null) {
                return Retorno.generateResponse("Não foi possível encontrar o paciente informado!", HttpStatus.NOT_FOUND, null);
            }

            pacienteService.edit(objPaciente,paciente);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível alterar o paciente! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Paciente editado com sucesso!", HttpStatus.OK, paciente);
    }

    /**
     * Está função é responsável por remover o registro de um paciente
     * @param Long pacienteId
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/{pacienteId}")
    public ResponseEntity<Object> delete(@PathVariable Long pacienteId) throws Exception {
        Paciente objPaciente = null;
        try{

            objPaciente = pacienteService.getPaciente(pacienteId);
            if (objPaciente == null) {
                return Retorno.generateResponse("Não foi possível encontrar o paciente informado!", HttpStatus.NOT_FOUND, null);
            }

            pacienteService.remove(objPaciente);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível remover o paciente! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);

        }

        return Retorno.generateResponse("Paciente removido com sucesso!", HttpStatus.OK, objPaciente);
    }
}
