package mx.restaurante.cv.restaurantapimng.controllers;

import java.util.*;

import mx.restaurante.cv.restaurantapimng.services.VendedorServiceI;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.restaurante.cv.restaurantapimng.models.entities.Platillo;

@RestController
@RequestMapping("/restcv/v1")
@CrossOrigin(origins = {"http://127.0.0.1:5500","http://localhost:4200"})
public class PlatilloController {

  @Autowired
  private VendedorServiceI service;

  @GetMapping("/platillos")
  public ResponseEntity<?> index() {

    Map<String,Object> response = new HashMap<>();
    List<Platillo> platillos = new ArrayList<>(Collections.emptyList());

    try{
      platillos = service.findAllPlatillos();
    }catch (DataAccessException e){
      response.put("mensaje","Error al obtener platillos");
      response.put("error",e.getMostSpecificCause().toString().concat(e.getRootCause().toString()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    if(platillos.isEmpty()){
      response.put("mensaje","No existen platillos en DB");
    }else {
      response.put("mensaje","Se obtuvieron platillos con éxito");
    }
    response.put("platillos",platillos);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
  }

}