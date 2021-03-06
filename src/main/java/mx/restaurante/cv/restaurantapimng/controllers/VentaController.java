package mx.restaurante.cv.restaurantapimng.controllers;

import java.util.*;

import mx.restaurante.cv.restaurantapimng.aggregate.VentaAbs;
import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;
import mx.restaurante.cv.restaurantapimng.services.VendedorServiceI;
import mx.restaurante.to.InfoVenta;
import mx.restaurante.to.PedidoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.to.InfoPedido;

import javax.xml.crypto.Data;

@RestController
@RequestMapping("/restcv/api")
@CrossOrigin(origins = {"http://127.0.0.1:5500"})
public class VentaController {

  @Autowired
  private VendedorServiceI service;

  @PostMapping("/venta")
  public ResponseEntity<?> save(@RequestBody InfoPedido infoPedido ) {
    Venta venta = null;
    Map<String, Object> response = new HashMap<>();
    try{
      venta = service.save(infoPedido);
    }catch (DataAccessException e) {
      response.put("mensaje", "Error no se logro guardar venta");
      response.put("error", e.getCause().toString().concat(" ").concat(e.getMostSpecificCause().toString()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    response.put("mensaje","Se guardo venta con éxito");
    response.put("venta",venta);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

  @GetMapping("/venta/{noEmpleado}")
  public ResponseEntity<?> findAllByNoEmployee(@PathVariable Long noEmpleado) {

    List<Venta> ventasEmpleado = new ArrayList<>(Collections.emptyList());
    Map<String, Object> response = new HashMap<>();

    try{
      ventasEmpleado = service.findVentaByVendedor_noEmpleado(noEmpleado);
    }catch (DataAccessException e) {
      response.put("mensaje","No se logró obtener ventas del empleado");
      response.put("error",e.getMostSpecificCause().toString().concat("" ).concat(e.getCause().toString()));
      return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    if(ventasEmpleado.isEmpty()) {
      response.put("mensaje", "El empleado no tiene ventas");
    }else{
      response.put("mensaje", "Se obtuvieron las ventas con éxito");
    }
    response.put("ventas",ventasEmpleado);
    return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
  }

  @PutMapping("/cerrar/venta")
  public ResponseEntity<?> cerrarCuenta(@RequestBody InfoVenta infoVenta ) {

    Map<String, Object> response = new HashMap<>();

    Venta actualizada = service.cerrarVenta( infoVenta );

    response.put("mensaje", "Se cerró venta con éxito" );
    response.put("venta", actualizada );

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

  @PutMapping("/venta")
  public ResponseEntity<?> actualizarPedido(@RequestBody PedidoTO pedido ) {

    Map<String, Object> response = new HashMap<>();

    Venta actualizada = service.agregarPlatillo( pedido );

    response.put("mensaje", "Se cerró venta con éxito" );
    response.put("venta", actualizada );

    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
  }

}