package com.petersen.bootcampasj.proyectospringprueba.datosTest;


import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDummy {

    // usuarios para POST
    public static Usuario getUsuarioNicoaruSinId() {
        Usuario usuario = new Usuario();
        usuario.setUsername("nicoaru");
        usuario.setPassword("1234");
        return usuario;
    }
    public static Usuario getUsuarioAnapradaSinId() {
        Usuario usuario = new Usuario();
        usuario.setUsername("anaprada");
        usuario.setPassword("2345");
        return usuario;
    }
    public static Usuario getUsuarioFulanitoNoExiste() {
        Usuario usuario = new Usuario();
        usuario.setUsername("fulanitoNoExiste");
        usuario.setPassword("2345");
        return usuario;
    }
    public static Usuario getUsuarioNicoaruWrongPassword() {
        Usuario usuario = new Usuario();
        usuario.setUsername("nicoaru");
        usuario.setPassword("0000");
        return usuario;
    }
    public static Usuario getUsuarioSinUnDato() {
        Usuario usuario = new Usuario();
        usuario.setPassword("1234");
        return usuario;
    }


    // usuarios para GET
    public static Usuario getUsuarioNicoaruConId() {
        Usuario usuario = new Usuario();
        usuario.setId(25);
        usuario.setUsername("nicoaru");
        usuario.setPassword("1234");
        return usuario;
    }
    public static Usuario getUsuarioAnapradaConId() {
        Usuario usuario = new Usuario();
        usuario.setId(26);
        usuario.setUsername("anaprada");
        usuario.setPassword("2345");
        return usuario;
    }

    public static Optional<Usuario> getOptionalUsuarioNicoaruConId() {
        Usuario usuario = new Usuario();
        usuario.setId(25);
        usuario.setUsername("nicoaru");
        usuario.setPassword("1234");
        Optional<Usuario> optUsuario = Optional.of(usuario);
        return optUsuario;
    }

    public static Optional<Usuario> getOptionalUsuarioVacio() {
        Optional<Usuario> optUsuario = Optional.empty();
        return optUsuario;
    }

    public static List<Usuario> getUserList() {
        List<Usuario> userList = new ArrayList<>();
        userList.add(getUsuarioAnapradaConId());
        userList.add(getUsuarioNicoaruConId());
        return userList;
    }

}
