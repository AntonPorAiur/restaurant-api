package mx.restaurante.cv.restaurantapimng.services;

import java.util.List;
import java.util.Optional;

import mx.restaurante.cv.restaurantapimng.factory.VentaFactory;
import mx.restaurante.cv.restaurantapimng.factory.VentaPedidoFactory;
import mx.restaurante.cv.restaurantapimng.models.entities.Platillo;
import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.cv.restaurantapimng.models.repository.PlatilloDao;
import mx.restaurante.cv.restaurantapimng.models.repository.VentaDao;
import mx.restaurante.to.InfoPedido;
import mx.restaurante.to.InfoVenta;
import mx.restaurante.to.PedidoTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;
import mx.restaurante.cv.restaurantapimng.models.repository.VendedorDao;

@Service
public class VendedorServiceImpl implements VendedorServiceI {

    private Logger logger = LoggerFactory.getLogger(VendedorServiceImpl.class);

    @Autowired
    VendedorDao vendedorDao;

    @Autowired
    PlatilloDao platilloDao;

    @Autowired
    VentaDao ventaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Vendedor> findAll() {
        return (List<Vendedor>) vendedorDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vendedor> findVendedorById(Long id) {
        Optional<Vendedor> vendedor = vendedorDao.findById(id);
        return vendedor;
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteVendedorById(Long id) {
        vendedorDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Vendedor save(Vendedor vendedor) {
        return vendedorDao.save(vendedor);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Platillo> findAllPlatillos() {
        return (List<Platillo>) platilloDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Platillo findPlatilloById(Long id) {
        return platilloDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> findVentaById(Long id) {
        Optional<Venta> venta = ventaDao.findById(id);
        return venta;
    }

    @Override
    @Transactional
    public Venta save(InfoPedido infoPedido) {

        VentaFactory factory = new VentaPedidoFactory();

        Venta venta = factory.crea( infoPedido );

        return ventaDao.save( venta );
    }

    @Override
    public Venta agregarPlatillo(PedidoTO pedido) {

        Optional<Venta> ventaActual = findVentaById( pedido.getIdPedido() );
        Venta actualizado = null;

        if( ventaActual.isPresent() ) {
            try {

                // TODO buscar en la lista de pedidos si ya existe y decidir si se agrega o suma la cantidad


                actualizado = ventaDao.save( ventaActual.get() );

            }catch (DataAccessException e) {
                return null;
            }
        }else {
            return null;
        }

        return null;
    }

    @Override
    @Transactional
    public Venta cerrarVenta(InfoVenta infoVenta) {

        Optional<Venta> ventaActual = findVentaById( infoVenta.getIdVenta() );
        Venta actualizado = null;

        if( ventaActual.isPresent() ) {
            try {

                ventaActual.get().setCerrada( true );
                ventaActual.get().setPropina( infoVenta.getPropina() );
                ventaActual.get().setObservacion( infoVenta.getObservacion() );
                actualizado = ventaDao.save( ventaActual.get() );

            }catch (DataAccessException e) {
                return null;
            }
        }else {
            return null;
        }

        return actualizado;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Venta> findVentaByVendedor_noEmpleado(Long noEmpleado) {
        return ventaDao.findVentaByVendedor_noEmpleado(noEmpleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Venta fetchIdWithVendedorWithDetalleVentaWithPlatillo(Long id) {
        return  ventaDao.fetchByIdWithVendedorWithDetalleVentaWithPlatillo(id);
    }


}
