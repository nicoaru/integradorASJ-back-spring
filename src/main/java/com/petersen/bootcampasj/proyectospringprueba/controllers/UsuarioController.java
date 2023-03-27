package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.UsuarioServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
@Api(value = "controller usuarios", tags = "Usuarios")
public class UsuarioController {
    private final UsuarioServiceInterface serviceUsuario;

    /** Constructor con DI **/
    UsuarioController(UsuarioServiceInterface usuarioService) {
        this.serviceUsuario = usuarioService;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    @ApiOperation(value = "Traer todos los Usuarios, o por username si se le pasa query param")
    public ResponseEntity getAllOrByUsername(@RequestParam(required = false) String username){
        try {
            if(username != null) {
                Usuario usuario = serviceUsuario.getByUsername(username);
                return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
            }
            else {
                List<Usuario> usuarios = serviceUsuario.getAll();
                return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
            }
        }
        catch (HttpClientErrorExceptionWithData err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), err.getData());
            return new ResponseEntity(errorBody, err.getStatusCode());
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar por id")
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en /usuarios/{id} - getById");

        try {
            Usuario usuario = serviceUsuario.getById(id);
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        }
        catch (HttpClientErrorExceptionWithData err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), err.getData());
            return new ResponseEntity(errorBody, err.getStatusCode());
        }
        catch (Exception err) {
            System.out.println(err.getMessage());
            err.printStackTrace();

            HttpErrorResponseBody errorBody = new HttpErrorResponseBody(err.getMessage(), null);
            return new ResponseEntity(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
