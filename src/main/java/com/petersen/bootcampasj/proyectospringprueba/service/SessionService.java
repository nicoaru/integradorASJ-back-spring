package com.petersen.bootcampasj.proyectospringprueba.service;


import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.UsuarioJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.SessionServiceInterface;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "app", name = "repository-type", havingValue = "mysql")
public class SessionService implements SessionServiceInterface {
    private final UsuarioJPARepository repositoryUsuario;

    /** Constructor con DI **/
    public SessionService(
            UsuarioJPARepository repository) {
        this.repositoryUsuario = repository;

    }


    /** Métodos **/
    /** Métodos **/

    @Override
    public Usuario login(Usuario credenciales) throws HttpClientErrorExceptionWithData {

        if(credenciales.getUsername() == null || credenciales.getPassword() == null) {
            throw new HttpClientErrorExceptionWithData("Credenciales incompletas", HttpStatus.BAD_REQUEST, "Bad request", null);
        }

        Usuario usuario = repositoryUsuario.findByUsername(credenciales.getUsername())
                                .orElse(null);
        if(usuario == null || !usuario.getPassword().equals(credenciales.getPassword())) {
            throw new HttpClientErrorExceptionWithData("Credenciales incorrectas", HttpStatus.UNAUTHORIZED, "Unauthorized", null);
        }
        else {

            return usuario;
        }

    }

    @Override
    public boolean logout() {
        // iria logica para eliminar la sessión
        return true;
    }
}
