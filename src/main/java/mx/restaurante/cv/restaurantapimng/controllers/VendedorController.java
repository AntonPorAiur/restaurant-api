package mx.restaurante.cv.restaurantapimng.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;
import mx.restaurante.cv.restaurantapimng.services.VendedorServiceI;

@RestController
@RequestMapping("/restcv/v1")
@CrossOrigin(origins = {"http://127.0.0.1:5500"})
public class VendedorController {
  
  @Autowired
  private VendedorServiceI vendedorService;

  @GetMapping("/empleados")
  public List<Vendedor> index() {
      return vendedorService.findAll();
  }    

}
