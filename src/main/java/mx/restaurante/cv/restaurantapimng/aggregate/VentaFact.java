package mx.restaurante.cv.restaurantapimng.aggregate;


import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.cv.restaurantapimng.models.repository.VendedorDao;
import mx.restaurante.cv.restaurantapimng.services.VendedorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class VentaFact implements VentaFactI {

    private Logger logger = LoggerFactory.getLogger(VentaFact.class);

    @Autowired
    VendedorDao vendedorDao;

    public static VentaAbs buildVenta(String observacion) {

        return new Venta(observacion);
    }

}

