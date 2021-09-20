package br.com.springboot.cadastropacientes.controllers;

import br.com.springboot.cadastropacientes.models.Medico;
import br.com.springboot.cadastropacientes.response.Retorno;
import br.com.springboot.cadastropacientes.servicesInterface.MedicoServiceInterface;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Controlador responsável por gerenciar as informações de médicos
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */


@RestController
@RequestMapping("/api/medico")
@ComponentScan
public class MedicoController extends AbstractController {

    private MedicoServiceInterface medicoService;

    public MedicoController(MedicoServiceInterface medicoService){
        this.medicoService = medicoService;
    }

    /**
     * Nesta função irá retornar todos os médicos existentes na base de dados
     * @return ResponseEntity<Object>
     */
    @GetMapping("")
    public ResponseEntity<Object> getMedico(){
        List<Medico> medicos = null;
        try {
            medicos = medicoService.getAll();
        } catch (RuntimeException ex){
            return Retorno.generateResponse("Não foi possível listar os medicos! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }

        return Retorno.generateResponse("Sucesso ao buscar o medico", HttpStatus.OK, medicos);
    }

    /**
     * Nesta função irá retornar os dados de um médico existente na base de dados
     * @return ResponseEntity<Object>
     * @param Long medicoId
     */
    @GetMapping("/{medicoId}")
    public ResponseEntity<Object>  getMedico(@PathVariable Long medicoId) {
        Medico objMedico = null;
        try {
            objMedico = medicoService.getMedico(medicoId);

            if (objMedico == null) {
                return Retorno.generateResponse("Não foi possível encontrar o medico informado!", HttpStatus.NOT_FOUND, null);
            }

        }catch (RuntimeException ex){
            return Retorno.generateResponse("Não foi possível listar o medico! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Sucesso ao buscar o medico", HttpStatus.OK, objMedico);
    }

    /**
     * Está função é responsável por criar o registro de um médico
     * @param Medico medico
     * @return ResponseEntity<Object>
     */
    @PostMapping("")
    public ResponseEntity<Object>  create(@Valid @RequestBody Medico medico) {
        Medico objMedico = null;
        try {
            objMedico = medicoService.save(medico);
        } catch (RuntimeException ex) {
            return Retorno.generateResponse("Não foi possível salvar o medico! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Médico criado com sucesso!", HttpStatus.OK, objMedico);
    }

    /**
     * Está função é responsável por editar o registro de um médico
     * @param Medico medico
     * @return ResponseEntity<Object>
     */
    @PutMapping("/{pessoaId}")
    public ResponseEntity<Object>  update(@Valid @RequestBody Medico medico){
        Medico objMedico = null;
        try{
            objMedico = medicoService.getMedico(medico.getPessoaId());
            if (objMedico == null) {
                return Retorno.generateResponse("Não foi possível encontrar o medico informado!", HttpStatus.NOT_FOUND, null);
            }

            medicoService.edit(objMedico,medico);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível alterar o medico! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Médico editado com sucesso!", HttpStatus.OK, medico);
    }

    /**
     * Está função é responsável por remover o registro de um médico
     * @param Long pessoaId
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/{medicoId}")
    public ResponseEntity<Object> delete(@PathVariable Long medicoId) throws Exception {
        Medico objMedico = null;
        try{

            objMedico = medicoService.getMedico(medicoId);
            if (objMedico == null) {
                return Retorno.generateResponse("Não foi possível encontrar o medico informado!", HttpStatus.NOT_FOUND, null);
            }

            medicoService.remove(objMedico);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível remover o medico! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);

        }

        return Retorno.generateResponse("Médico removido com sucesso!", HttpStatus.OK, objMedico);
    }
}
