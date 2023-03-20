package com.petersen.bootcampasj.proyectospringprueba.controllers;

import com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.MuebleDTOcreate;
import com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.MuebleDTOupdt;
import com.petersen.bootcampasj.proyectospringprueba.DTO.mappers.MuebleMapper;
import com.petersen.bootcampasj.proyectospringprueba.DTO.muebles.MuebleResponseDTO;
import com.petersen.bootcampasj.proyectospringprueba.HttpErrorResponseBody;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.HttpClientErrorExceptionWithData;
import com.petersen.bootcampasj.proyectospringprueba.customExceptions.ValidationException;
import com.petersen.bootcampasj.proyectospringprueba.model.domino.Mueble;
import com.petersen.bootcampasj.proyectospringprueba.service.interfaces.MuebleServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/muebles")
public class MuebleController {
    private final MuebleServiceInterface serviceMueble;
    private final MuebleMapper mapperMueble;
    /** Constructor con DI **/
    MuebleController(
            MuebleServiceInterface serviceMueble,
            MuebleMapper mapperMueble) {
        this.serviceMueble = serviceMueble;
        this.mapperMueble = mapperMueble;
    }


    /** Endpoints **/
    /** Endpoints **/

    @GetMapping
    public ResponseEntity getAll(){
        try{
            List<Mueble> muebles = serviceMueble.getAll();
            List<MuebleResponseDTO> mueblesDTO = mapperMueble.listEntityToListMueblesResponseDto(muebles);

            return new ResponseEntity(mueblesDTO, HttpStatus.OK);
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
    public ResponseEntity getById(@PathVariable Integer id){
        System.out.println("Entró en GET /muebles/{id} - getById");
        try {
            Mueble mueble = serviceMueble.getById(id);
            MuebleResponseDTO muebleDTO = mapperMueble.entityToMueblesResponseDto(mueble);

            return new ResponseEntity(muebleDTO, HttpStatus.OK);

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

    @PostMapping
    public ResponseEntity create(@RequestBody MuebleDTOcreate newMueble){
        System.out.println("Entró en CREATE /muebles/{id} - updateById");
        try{
            Mueble newMuebleEntidad = mapperMueble.dtoCreateToEntity(newMueble);


            Mueble mueble = serviceMueble.create(newMuebleEntidad);
            MuebleResponseDTO muebleDTO = mapperMueble.entityToMueblesResponseDto(mueble);

            return new ResponseEntity(muebleDTO, HttpStatus.CREATED);

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

    @PutMapping("/{id}")
    public ResponseEntity updateById(@PathVariable Integer id, @RequestBody MuebleDTOupdt updatedMueble){
        System.out.println("Entró en PUT /muebles/{id} - updateById");
        try{
            Mueble updatedMuebleEntidad = mapperMueble.dtoUpdtToEntity(updatedMueble);
            // Ver si existe el cliente a actualizar

            Mueble mueble = serviceMueble.updateById(id, updatedMuebleEntidad);
            MuebleResponseDTO muebleDTO = mapperMueble.entityToMueblesResponseDto(mueble);

            return new ResponseEntity(muebleDTO, HttpStatus.OK);

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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id){
        try{
            Mueble muebleEliminado = serviceMueble.deleteById(id);
            MuebleResponseDTO muebleEliminadoDTO = mapperMueble.entityToMueblesResponseDto(muebleEliminado);

            return new ResponseEntity(muebleEliminadoDTO, HttpStatus.OK);
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
