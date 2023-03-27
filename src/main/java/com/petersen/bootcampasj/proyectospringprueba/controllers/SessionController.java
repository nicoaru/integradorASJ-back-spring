package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.UsuarioMapper;
import com.petersen.bootcampasj.proyectospringprueba.DTO.usuarios.UsuarioResponseDTO;
import com.petersen.bootcampasj.proyectospringprueba.otros.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.SessionServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sessions")
@Api(value = "controller sessions", tags = "Sessions")
public class SessionController {
    private final SessionServiceInterface serviceSession;
    private final UsuarioMapper mapperUsuario;

    /** Constructor con DI **/
    SessionController(
            SessionServiceInterface sessionService,
            UsuarioMapper mapperUsuario) {
        this.serviceSession = sessionService;
        this.mapperUsuario = mapperUsuario;
    }


    /** Endpoints **/
    /** Endpoints **/

    @PostMapping
    @ApiOperation("Login")
    public ResponseEntity login(@RequestBody Usuario credenciales) {
        try {
            Usuario usuario = serviceSession.login(credenciales);
            UsuarioResponseDTO usuarioDTO = mapperUsuario.entityToDto(usuario);
            return new ResponseEntity(usuarioDTO, HttpStatus.OK);
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

    @ApiOperation("Logout")
    public ResponseEntity logout() {
        try {
            boolean result = serviceSession.logout();

            return new ResponseEntity(result, HttpStatus.OK);
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
