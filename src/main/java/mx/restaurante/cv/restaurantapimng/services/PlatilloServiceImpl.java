package mx.restaurante.cv.restaurantapimng.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.restaurante.cv.restaurantapimng.models.entities.Platillo;
import mx.restaurante.cv.restaurantapimng.models.repository.PlatilloDao;

@Service
public class PlatilloServiceImpl implements PlatilloServiceI {

  @Autowired
  PlatilloDao platilloDao;

  @Override
  @Transactional(readOnly = true)
  public List<Platillo> findAll() {
    return (List<Platillo>) platilloDao.findAll();
  }
  
}
