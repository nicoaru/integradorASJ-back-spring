package com.petersen.bootcampasj.proyectospringprueba.serviceTest;


import com.petersen.bootcampasj.proyectospringprueba.datosTest.UsuarioDummy;
import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.UsuarioJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.SessionService;
import com.petersen.bootcampasj.proyectospringprueba.service.UsuarioService;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.SessionServiceInterface;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.UsuarioServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioServiceTest {

   @Mock
    private UsuarioJPARepository repositoryUsuario;
    private UsuarioServiceInterface usuarioService;


/*
    @MockBean
    private UsuarioJPARepository repository;
    @Autowired
    private SessionServiceInterface service;
*/


    @BeforeEach
    void setUp() {
        repositoryUsuario = mock(UsuarioJPARepository.class);
        usuarioService = new UsuarioService(repositoryUsuario);
    }


    @Test
    @DisplayName("[usuarioService] - getAll()")
    void getAll() {
        // GIVEN
        when(repositoryUsuario.findAll())
            .thenReturn(UsuarioDummy.getUserList());

        // WHEN
        List<Usuario> usuarios = this.usuarioService.getAll();

        // THEN
        assertThat(usuarios)
            .isNotNull()
            .hasSize(2);
        assertThat(usuarios.get(0).getUsername())
                .isEqualTo("anaprada");
        assertThat(usuarios.get(1).getUsername())
                .isEqualTo("nicoaru");

        verify(repositoryUsuario).findAll();
    }

    @Test
    @DisplayName("[usuarioService] - getById()")
    void getById() {
        // GIVEN
        when(repositoryUsuario.findById(25))
                .thenReturn(UsuarioDummy.getOptionalUsuarioNicoaruConId());
        when(repositoryUsuario.findById(55))
                .thenReturn(UsuarioDummy.getOptionalUsuarioVacio());
        // WHEN
        Usuario usuario = this.usuarioService.getById(25);

        // THEN
        assertThat(usuario)
                .isNotNull();
        assertThat(usuario.getUsername())
                .isEqualTo("nicoaru");
        assertThat(usuario.getId())
                .isEqualTo(25);
        verify(repositoryUsuario).findById(25);

        assertThatThrownBy(() -> this.usuarioService.getById(55))
                .isInstanceOf(HttpClientErrorExceptionWithData.class)
                .hasMessage("Not found");

    }

    @Test
    @DisplayName("[usuarioService] - getByUsername()")
    void getByUsername() {
        // GIVEN
        when(repositoryUsuario.findByUsername("nicoaru"))
                .thenReturn(UsuarioDummy.getOptionalUsuarioNicoaruConId());
        when(repositoryUsuario.findByUsername("fulanitoNoExiste"))
                .thenReturn(UsuarioDummy.getOptionalUsuarioVacio());
        // WHEN
        Usuario usuario = this.usuarioService.getByUsername("nicoaru");

        // THEN
        assertThat(usuario)
                .isNotNull();
        assertThat(usuario.getUsername())
                .isEqualTo("nicoaru");
        assertThat(usuario.getId())
                .isEqualTo(25);
        verify(repositoryUsuario).findByUsername("nicoaru");

        assertThatThrownBy(() -> this.usuarioService.getByUsername("fulanitoNoExiste"))
                .isInstanceOf(HttpClientErrorExceptionWithData.class)
                .hasMessage("Not found");

    }

}
