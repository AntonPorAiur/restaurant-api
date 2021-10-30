package mx.restaurante.cv.restaurantapimng.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;
import mx.restaurante.cv.restaurantapimng.services.VendedorServiceI;

@RestController
@RequestMapping("/restcv/v1")
@CrossOrigin(origins = {"http://127.0.0.1:5500"})
public class VendedorController {

  @Autowired
  private VendedorServiceI service;

  @GetMapping("/empleados")
  public ResponseEntity<?> index() {

    List<Vendedor> vendedores = new ArrayList<>(Collections.emptyList());
    Map response = new HashMap();

      try{
        vendedores = service.findAll();
      }catch (DataAccessException e) {
        response.put("mensaje", "Error al realizar la consulta");
        response.put("error",e.getMessage().toString().concat(": ").concat(e.getMostSpecificCause().getMessage()));
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
      }

      if(vendedores.isEmpty()) {
        response.put("mensaje", "No hay vendedores en DB");
        response.put("vendedores",vendedores);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
      }else{
        response.put("mensaje", "Se obtuvieron los vendedores con éxito");
        response.put("vendedores",vendedores);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
      }
  }

  @PostMapping("/empleados")
  public ResponseEntity<?> guardar(@RequestBody Vendedor vendedor) {

    Vendedor vendedorNuevo = null;
    Map<String, Object> response = new HashMap();

    try {
      vendedorNuevo = service.save(vendedor);
    } catch (DataAccessException e) {
      response.put("mensaje","No se logro guardar al empleado");
      response.put("error",e.getMessage().toString().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje","El empleado se ha guardado con éxito!");
    response.put("vendedor", vendedorNuevo);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

  @PutMapping("/empleados/{id}")
  public ResponseEntity<?> actualizar(@RequestBody Vendedor vendedor, @PathVariable Long id) {

    Optional<Vendedor> vendedorActual = service.findVendedorById(id);
    Vendedor actualizado = null;
    Map<String, Object> response = new HashMap<>();

    if(vendedorActual.isPresent()) {
      vendedorActual.get().setNoEmpleado(vendedor.getNoEmpleado());
      vendedorActual.get().setNombre(vendedor.getNombre());
      vendedorActual.get().setApellido(vendedor.getApellido());
    }else {
      response.put("mensaje","No se logro editar empleado, no se encontró en DB");
      return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
    }

    try {
      actualizado = service.save(vendedorActual.get());
    }catch (DataAccessException e) {
      response.put("mensaje", "Error al actualizar cliente");
      response.put("error",e.getMessage().toString().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje","El empleado ha sido actualizado con éxito!");
    response.put("vendedor", actualizado);
    return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
  }

  @DeleteMapping("/empleados/{id}")
  public ResponseEntity<?> deleteVendedor(@PathVariable Long id) {

    Map<String, Object> response = new HashMap<>();
    try {

      service.deleteVendedorById(id); // En automático se valida que el usuario exista

    } catch (DataAccessException e) {
      response.put("mensaje", "Error al eliminar empleado de la base de datos!");
      response.put("error",e.getMessage().toString().concat(": ").concat(e.getMostSpecificCause().getMessage()));
      return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje","El empleado ha sido eliminado con éxito!");
    return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
  }

}