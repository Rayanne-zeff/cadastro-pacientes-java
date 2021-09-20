package br.com.springboot.cadastropacientes.controllers;

import br.com.springboot.cadastropacientes.models.Usuario;
import br.com.springboot.cadastropacientes.response.Retorno;
import br.com.springboot.cadastropacientes.servicesInterface.UsuarioServiceInterface;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



/**
 * Controlador responsável por gerenciar as informações de usuários
 * @author : Gloria Rayane
 * @since : 17/09/2021
 */


@RestController
@RequestMapping("/api/usuario")
@ComponentScan
public class UsuarioController extends AbstractController {
    
    private UsuarioServiceInterface usuarioService;

    public UsuarioController(UsuarioServiceInterface usuarioService){
        this.usuarioService = usuarioService;
    }

    /**
     * Nesta função irá retornar todos os usuários existentes na base de dados
     * @return ResponseEntity<Object>
     */
    @GetMapping("")
    public ResponseEntity<Object> getUsuario(){
        List<Usuario> usuarios = null;
        try {
            usuarios = usuarioService.getAll();
        } catch (RuntimeException ex){
            return Retorno.generateResponse("Não foi possível listar os usuarios! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }

        return Retorno.generateResponse("Sucesso ao buscar o usuario", HttpStatus.OK, usuarios);
    }

    /**
     * Nesta função irá retornar os dados de um usuário existente na base de dados
     * @return ResponseEntity<Object>
     * @param Long usuarioId
     */
    @GetMapping("/{usuarioId}")
    public ResponseEntity<Object>  getUsuario(@PathVariable Long usuarioId) {
        Usuario objUsuario = null;
        try {
            objUsuario = usuarioService.getUsuario(usuarioId);

            if (objUsuario == null) {
                return Retorno.generateResponse("Não foi possível encontrar o usuario informado!", HttpStatus.NOT_FOUND, null);
            }

        }catch (RuntimeException ex){
            return Retorno.generateResponse("Não foi possível listar o usuario! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Sucesso ao buscar o usuario", HttpStatus.OK, objUsuario);
    }

    /**
     * Está função é responsável por criar o registro de um usuário
     * @param Usuario usuario
     * @return ResponseEntity<Object>
     */
    @PostMapping("")
    public ResponseEntity<Object>  create(@Valid @RequestBody Usuario usuario) {
        Usuario objUsuario = null;
        try {
            objUsuario = usuarioService.save(usuario);
        } catch (RuntimeException ex) {
            return Retorno.generateResponse("Não foi possível salvar o usuario! Por favor tente novamente. " + ex.getMessage(), HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Usuario criado com sucesso!", HttpStatus.OK, objUsuario);
    }

    /**
     * Está função é responsável por editar o registro de um usuário
     * @param Usuario usuario
     * @return ResponseEntity<Object>
     */
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Object>  update(@Valid @RequestBody Usuario usuario){
        Usuario objUsuario = null;
        try{
            objUsuario = usuarioService.getUsuario(usuario.getUsuarioId());
            if (objUsuario == null) {
                return Retorno.generateResponse("Não foi possível encontrar o usuario informado!", HttpStatus.NOT_FOUND, null);
            }

            usuarioService.edit(objUsuario, usuario);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível alterar o usuario! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);
        }
        return Retorno.generateResponse("Usuario editado com sucesso!", HttpStatus.OK, usuario);
    }

    /**
     * Está função é responsável por remover o registro de um usuário
     * @param Long pessoaId
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Object> delete(@PathVariable Long usuarioId) throws Exception {
        Usuario objUsuario = null;
        try{

            objUsuario = usuarioService.getUsuario(usuarioId);
            if (objUsuario == null) {
                return Retorno.generateResponse("Não foi possível encontrar o usuario informado!", HttpStatus.NOT_FOUND, null);
            }

            usuarioService.remove(objUsuario);
        } catch (Exception ex) {
            return Retorno.generateResponse("Não foi possível remover o usuario! Por favor tente novamente.", HttpStatus.BAD_REQUEST, null);

        }

        return Retorno.generateResponse("Usuario removido com sucesso!", HttpStatus.OK, objUsuario);
    }
}
