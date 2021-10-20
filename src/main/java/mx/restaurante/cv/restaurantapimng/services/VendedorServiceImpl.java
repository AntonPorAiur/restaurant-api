package mx.restaurante.cv.restaurantapimng.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;
import mx.restaurante.cv.restaurantapimng.models.repository.VendedorDao;

@Service
public class VendedorServiceImpl implements VendedorServiceI {

  @Autowired
  VendedorDao vendedorDao;

  @Override
  @Transactional(readOnly = true)
  public List<Vendedor> findAll() {
    return (List<Vendedor>) vendedorDao.findAll();
  }
}
