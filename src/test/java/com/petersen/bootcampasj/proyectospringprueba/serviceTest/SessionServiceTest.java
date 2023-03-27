package com.petersen.bootcampasj.proyectospringprueba.serviceTest;


import com.petersen.bootcampasj.proyectospringprueba.exceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.datosTest.UsuarioDummy;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Usuario;
import com.petersen.bootcampasj.proyectospringprueba.model.repository.UsuarioJPARepository;
import com.petersen.bootcampasj.proyectospringprueba.service.SessionService;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.SessionServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
public class SessionServiceTest {


/*    String usernameToTestOk = "nicoaru";
    String passwordToTestOk = "1234";
    Usuario userToTestOk = new Usuario();

    String usernameToTestWrong = "FulanitoNoExiste";
    String passwordToTestWrong = "12345678989";
    Usuario userToTestWrongUsername = new Usuario();
    Usuario userToTestWrongPassword = new Usuario();
    Usuario userVacio = new Usuario();*/

   @Mock
    private UsuarioJPARepository repositoryUsuario;
    private SessionServiceInterface sessionService;


/*
    @MockBean
    private UsuarioJPARepository repository;
    @Autowired
    private SessionServiceInterface service;
*/


    @BeforeEach
    void setUp() {
        repositoryUsuario = mock(UsuarioJPARepository.class);
        sessionService = new SessionService(repositoryUsuario);
    }


    @Test
    @DisplayName("[sessionService] - Login")
    void login() {
        // GIVEN
        //usuario correcto
/*        userToTestOk.setUsername(usernameToTestOk);
        userToTestOk.setPassword(passwordToTestOk);
        // usuario con password mal
        userToTestWrongPassword.setUsername(usernameToTestOk);
        userToTestWrongPassword.setPassword(passwordToTestWrong);
        // usuario con username mal
        userToTestWrongUsername.setUsername(usernameToTestWrong);
        userToTestWrongUsername.setPassword(passwordToTestOk);*/

        when(repositoryUsuario.findByUsername("nicoaru"))
                .thenReturn(UsuarioDummy.getOptionalUsuarioNicoaruConId());

        when(repositoryUsuario.findByUsername("fulanitoNoExiste"))
                .thenReturn(UsuarioDummy.getOptionalUsuarioVacio());

        // WHEN
        Usuario loginResult = this.sessionService.login(UsuarioDummy.getUsuarioNicoaruSinId());

        // THEN
        assertThat(loginResult)
                .isNotNull();
        assertThat(loginResult.getUsername())
                .isEqualTo("nicoaru");
        assertThat(loginResult.getPassword())
                .isEqualTo("1234");

        assertThatThrownBy(() -> this.sessionService.login(UsuarioDummy.getUsuarioFulanitoNoExiste()))
                .isInstanceOf(HttpClientErrorExceptionWithData.class)
                .hasMessage("Credenciales incorrectas");

        assertThatThrownBy(() -> this.sessionService.login(UsuarioDummy.getUsuarioNicoaruWrongPassword()))
                .isInstanceOf(HttpClientErrorExceptionWithData.class)
                .hasMessage("Credenciales incorrectas");

        assertThatThrownBy(() -> this.sessionService.login(UsuarioDummy.getUsuarioSinUnDato()))
                .isInstanceOf(HttpClientErrorExceptionWithData.class)
                .hasMessage("Credenciales incompletas");

        verify(repositoryUsuario, times(2)).findByUsername("nicoaru");
        verify(repositoryUsuario).findByUsername("fulanitoNoExiste");

    }

    @Test
    @DisplayName("[sessionService] - Logout")
    void logout() {
        // GIVEN


        // WHEN
        boolean loginResult = this.sessionService.logout();

        // THEN
        assertThat(loginResult)
                .isTrue();

    }

}
