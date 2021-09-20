package br.com.springboot.cadastropacientes.controllers;

import br.com.springboot.cadastropacientes.models.Pessoa;
import br.com.springboot.cadastropacientes.response.Retorno;
import br.com.springboot.cadastropacientes.servicesInterface.PessoaServiceInterface;
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
@RequestMapping("/api/pessoa")
@ComponentScan
public class PessoaController extends AbstractController {

    private PessoaServiceInterface pessoaService;

    public PessoaController(PessoaServiceInterface pessoaService){
        this.pessoaService = pessoaService;
    }

    @GetMapping("")
    public ResponseEntity<Object> getPessoa(){
        List<Pessoa> pessoas = null;
        try {
            pessoas = pessoaService.getAll();
        } catch (RuntimeException ex){
            return Retorno.generateResponse("Não foi possível listar as pessoas! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }

        return Retorno.generateResponse("Sucesso ao buscar a pessoa", HttpStatus.OK, pessoas);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<Object>  getPessoa(@PathVariable Long pessoaId) {
        Pessoa objPessoa = null;
        try {
            objPessoa = pessoaService.getPessoa(pessoaId);

            if (objPessoa == null) {
                return Retorno.generateResponse("Não foi possível encontrar a pessoa informado!", HttpStatus.NOT_FOUND, null);
            }

        }catch (RuntimeException ex){
            return Retorno.generateResponse("Não foi possível listar a pessoa! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Sucesso ao buscar a pessoa", HttpStatus.OK, objPessoa);
    }

    @PostMapping("")
    public ResponseEntity<Object>  create(@Valid @RequestBody Pessoa pessoa) {
        Pessoa objPessoa = null;
        try {
            objPessoa = pessoaService.save(pessoa);
        } catch (RuntimeException ex) {
            return Retorno.generateResponse("Não foi possível salvar a pessoa! Por favor tente novamente. " + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Pessoa criada com sucesso!", HttpStatus.OK, objPessoa);
    }

    @PutMapping("")
    public ResponseEntity<Object>  update(@Valid @RequestBody Pessoa pessoa){
        Pessoa objPessoa = null;
        try{
            objPessoa = pessoaService.getPessoa(pessoa.getPessoaId());
            if (objPessoa == null) {
                return Retorno.generateResponse("Não foi possível encontrar a pessoa informado!", HttpStatus.NOT_FOUND, null);
            }

            pessoaService.edit(objPessoa, pessoa);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível alterar a pessoa! Por favor tente novamente. " + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Pessoa editada com sucesso!", HttpStatus.OK, pessoa);
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Object> delete(@PathVariable Long pessoaId) throws Exception {
        Pessoa objPessoa = null;
        try{

            objPessoa = pessoaService.getPessoa(pessoaId);
            if (objPessoa == null) {
                return Retorno.generateResponse("Não foi possível encontrar a pessoa informado!", HttpStatus.NOT_FOUND, null);
            }

            pessoaService.remove(objPessoa);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível remover a pessoa! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);

        }

        return Retorno.generateResponse("Pessoa removida com sucesso!", HttpStatus.OK, objPessoa);
    }
}
