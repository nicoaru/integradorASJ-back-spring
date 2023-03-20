package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.UsuarioServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioServiceInterface serviceUsuario;

    /** Constructor con DI **/
    UsuarioController(UsuarioServiceInterface usuarioService) {
        this.serviceUsuario = usuarioService;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    public ResponseEntity getAllOrByUsername(@RequestParam(required = false) String username){
        if(username != null) return serviceUsuario.getByUsername(username);
        else return serviceUsuario.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en /usuarios/{id} - getById");

        return serviceUsuario.getById(id);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Usuario usuarioReq){

        return serviceUsuario.create(usuarioReq);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){

        return serviceUsuario.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody Usuario updatedUser){
        System.out.println("Entró en PUT /usuarios/{id} - updateById");

        if(updatedUser.getUsername() == null || updatedUser.getPassword()== null) {
            System.out.println("Ni usuario ni contraseña pueden ser nulos");
            HttpErrorResponseBody errorBody = new HttpErrorResponseBody("Ni usuario ni contraseña pueden ser nulos", null);
            return new ResponseEntity(errorBody, HttpStatus.BAD_REQUEST);
        }

        else return serviceUsuario.updateById(id, updatedUser);
    }


}
