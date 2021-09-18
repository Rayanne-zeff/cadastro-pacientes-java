package br.com.springboot.cadastropacientes.controllers;

import br.com.springboot.cadastropacientes.models.Enfermeiro;
import br.com.springboot.cadastropacientes.response.Retorno;
import br.com.springboot.cadastropacientes.servicesInterface.EnfermeiroServiceInterface;
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
@RequestMapping("/api/enfermeiro")
@ComponentScan
public class EnfermeiroController extends AbstractController {

    private EnfermeiroServiceInterface enfermeiroService;

    public EnfermeiroController(EnfermeiroServiceInterface enfermeiroService){
        this.enfermeiroService = enfermeiroService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getEnfermeiro(){
        List<Enfermeiro> enfermeiros = null;
        try {
            enfermeiros = enfermeiroService.getAll();
        } catch (RuntimeException ex){
            return Retorno.generateResponse("Não foi possível listar os enfermeiros! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }

        return Retorno.generateResponse("Sucesso ao buscar o enfermeiro", HttpStatus.OK, enfermeiros);
    }

    @GetMapping("/{enfermeiroId}")
    public ResponseEntity<Object>  getEnfermeiro(@PathVariable Long enfermeiroId) {
        Enfermeiro objEnfermeiro = null;
        try {
            objEnfermeiro = enfermeiroService.getEnfermeiro(enfermeiroId);

            if (objEnfermeiro == null) {
                return Retorno.generateResponse("Não foi possível encontrar o enfermeiro informado!", HttpStatus.NOT_FOUND, null);
            }

        }catch (RuntimeException ex){
            return Retorno.generateResponse("Não foi possível listar o enfermeiro! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Sucesso ao buscar o enfermeiro", HttpStatus.OK, objEnfermeiro);
    }

    @PostMapping("")
    public ResponseEntity<Object>  create(@Valid @RequestBody Enfermeiro enfermeiro) {
        Enfermeiro objEnfermeiro = null;
        try {
            objEnfermeiro = enfermeiroService.save(enfermeiro);
        } catch (RuntimeException ex) {
            return Retorno.generateResponse("Não foi possível salvar o enfermeiro! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Enfermeiro criado com sucesso!", HttpStatus.OK, objEnfermeiro);
    }

    @PutMapping("")
    public ResponseEntity<Object>  update(@Valid @RequestBody Enfermeiro enfermeiro){
        Enfermeiro objEnfermeiro = null;
        try{
            objEnfermeiro = enfermeiroService.getEnfermeiro(enfermeiro.getPessoaId());
            if (objEnfermeiro == null) {
                return Retorno.generateResponse("Não foi possível encontrar o enfermeiro informado!", HttpStatus.NOT_FOUND, null);
            }

            enfermeiroService.edit(enfermeiro);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível alterar o enfermeiro! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Enfermeiro editado com sucesso!", HttpStatus.OK, enfermeiro);
    }

    @DeleteMapping("/{enfermeiroId}")
    public ResponseEntity<Object> delete(@PathVariable Long enfermeiroId) throws Exception {
        Enfermeiro objEnfermeiro = null;
        try{

            objEnfermeiro = enfermeiroService.getEnfermeiro(enfermeiroId);
            if (objEnfermeiro == null) {
                return Retorno.generateResponse("Não foi possível encontrar o enfermeiro informado!", HttpStatus.NOT_FOUND, null);
            }

            enfermeiroService.remove(objEnfermeiro);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível remover o enfermeiro! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);

        }

        return Retorno.generateResponse("Enfermeiro removido com sucesso!", HttpStatus.OK, objEnfermeiro);
    }
}
