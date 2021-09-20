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
 * Controlador responsável por gerenciar as informações de enfermeiros
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

    /**
     * Nesta função irá retornar todos os enfermeiros existentes na base de dados
     * @return ResponseEntity<Object>
     */
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

    /**
     * Nesta função irá retornar os dados de um enfermeiro existente na base de dados
     * @return ResponseEntity<Object>
     * @param Long enfermeiroId
     */
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

    /**
     * Está função é responsável por criar o registro de um enfermeiro
     * @param Enfermeiro enfermeiro
     * @return ResponseEntity<Object>
     */
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

    /**
     * Está função é responsável por editar o registro de um enfermeiro
     * @param Enfermeiro enfermeiro
     * @return ResponseEntity<Object>
     */
    @PutMapping("")
    public ResponseEntity<Object>  update(@Valid @RequestBody Enfermeiro enfermeiro){
        Enfermeiro objEnfermeiro = null;
        try{
            objEnfermeiro = enfermeiroService.getEnfermeiro(enfermeiro.getPessoaId());
            if (objEnfermeiro == null) {
                return Retorno.generateResponse("Não foi possível encontrar o enfermeiro informado!", HttpStatus.NOT_FOUND, null);
            }

            enfermeiroService.edit(objEnfermeiro, enfermeiro);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível alterar o enfermeiro! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Enfermeiro editado com sucesso!", HttpStatus.OK, enfermeiro);
    }

    /**
     * Está função é responsável por remover o registro de um enfermeiro
     * @param Long pessoaId
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Object> delete(@PathVariable Long pessoaId) throws Exception {
        Enfermeiro objEnfermeiro = null;
        try{

            objEnfermeiro = enfermeiroService.getEnfermeiro(pessoaId);
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
