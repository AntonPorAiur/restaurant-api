package mx.restaurante.cv.restaurantapimng.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.cv.restaurantapimng.services.VentaServiceI;
import mx.restaurante.to.InfoPedido;
import mx.restaurante.to.Pedido;

@RestController
@RequestMapping("/restcv/api")
public class VentaController {
  
  @Autowired
  VentaServiceI ventaService;

  @PostMapping("/venta")
  public Venta save(@RequestBody InfoPedido infoPedido ) {
    return ventaService.save(infoPedido);
  }

  @GetMapping("/venta/{noEmpleado}")
  public List<Venta> findAllByNoEmployee(@PathVariable Long noEmpleado) {
      return ventaService.findVentaByVendedor_noEmpleado(noEmpleado);
  }

}