package com.petersen.bootcampasj.proyectospringprueba.repositoryTest;

import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.UsuarioJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.datosTest.UsuarioDummy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UsuarioRepositoryTest {
    String usernameToTest = "nicoaru";
    String passwordToTest = "1234";
    @Autowired
    private UsuarioJPARepository repository;

    @Test
    @DisplayName("[repositoryUsuario] - findByUsername()")
    void findByUsername() {
        // GIVEN
        Usuario result = this.repository.save(UsuarioDummy.getUsuarioNicoaruSinId());
        // WHEN
        Optional<Usuario> usuarioOptional = this.repository.findByUsername(usernameToTest);
        Optional<Usuario> usuarioInexistenteOptional = this.repository.findByUsername("fulanitoNoExiste");
        // THEN
        assertThat(usuarioOptional.isPresent())
                .isTrue();

        assertThat(usuarioOptional.get().getUsername())
                .isEqualTo(usernameToTest);

        assertThat(usuarioOptional.get().getPassword())
                .isEqualTo(passwordToTest);

        assertThat(usuarioOptional.get().getId())
                .isNotNull();

        assertThat(usuarioInexistenteOptional.isPresent())
                .isFalse();
    }

    @Test
    @DisplayName("[repositoryUsuario] - existsByUsername()")
    void existsByUsername() {
        // GIVEN
        Usuario result = this.repository.save(UsuarioDummy.getUsuarioNicoaruSinId());
        // WHEN
        boolean existe = this.repository.existsByUsername(usernameToTest);
        boolean noExiste = this.repository.existsByUsername("fulanitoNoExiste");
        // THEN
        assertThat(existe)
                .isTrue();

        assertThat(noExiste)
                .isFalse();
    }






}
