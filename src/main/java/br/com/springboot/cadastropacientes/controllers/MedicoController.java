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

    @PostMapping("")
    public ResponseEntity<Object>  create(@Valid @RequestBody Medico medico) {
        Medico objMedico = null;
        try {
            objMedico = medicoService.save(medico);
        } catch (RuntimeException ex) {
            return Retorno.generateResponse("Não foi possível salvar o medico! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Medico criado com sucesso!", HttpStatus.OK, objMedico);
    }

    @PutMapping("")
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
        return Retorno.generateResponse("Medico editado com sucesso!", HttpStatus.OK, medico);
    }

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

        return Retorno.generateResponse("Medico removido com sucesso!", HttpStatus.OK, objMedico);
    }
}
