package mx.restaurante.cv.restaurantapimng.services;

import java.util.List;

import mx.restaurante.cv.restaurantapimng.models.entities.Platillo;

public interface PlatilloServiceI {

  public List<Platillo> findAll();
  
}