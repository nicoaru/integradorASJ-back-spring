package com.petersen.bootcampasj.proyectospringprueba.service;

import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.UsuarioMapper;
import com.petersen.bootcampasj.proyectospringprueba.DTO.usuarios.UsuarioResponseDTO;
import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.ValidationException;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Cliente;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.UsuarioJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.clasesAuxiliares.EntidadesHijasCliente;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.SessionServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class SessionService implements SessionServiceInterface {
    private final UsuarioJPARepository repositoryUsuario;
    private final UsuarioMapper mapperUsuario;

    /** Constructor con DI **/
    public SessionService(
            UsuarioJPARepository repository,
            UsuarioMapper mapperUsuario) {
        this.repositoryUsuario = repository;
        this.mapperUsuario = mapperUsuario;
    }


    /** Métodos **/
    /** Métodos **/

    @Override
    public ResponseEntity login(Usuario credenciales) {

        if(credenciales.getUsername() == null || credenciales.getPassword() == null) {
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Credenciales incompletas", null);
            return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = repositoryUsuario.findByUsername(credenciales.getUsername())
                                .orElse(null);
        if(usuario == null || !usuario.getPassword().equals(credenciales.getPassword())) {
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Credenciales incorrectas", null);
            return new ResponseEntity(errorBody, HttpStatus.UNAUTHORIZED);
        }
        else {
            UsuarioResponseDTO usuarioDTO = mapperUsuario.entityToDto(usuario);
            return new ResponseEntity(usuarioDTO, HttpStatus.OK);
        }

    }



    @Override
    public ResponseEntity logout(Usuario credenciales) {
        return null;
    }
}
