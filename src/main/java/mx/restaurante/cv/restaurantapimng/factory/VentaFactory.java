package mx.restaurante.cv.restaurantapimng.factory;

import mx.restaurante.cv.restaurantapimng.aggregate.VentaAbs;
import mx.restaurante.cv.restaurantapimng.models.entities.DetalleVenta;
import mx.restaurante.cv.restaurantapimng.models.entities.Platillo;
import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;
import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.to.InfoPedido;
import mx.restaurante.to.InfoVenta;
import mx.restaurante.to.Pedido;


public abstract class VentaFactory {

    // proceso de construcción común
    public Venta crea(InfoPedido infoPedido) {

        Venta venta = crearVenta();
        // Definition Construction Común
        venta.setCerrada( infoPedido.isCerrada() );
        venta.setObservacion( infoPedido.getObservacion() );
        venta.setPropina( infoPedido.getPropina() );
        venta.setVendedor(new Vendedor( infoPedido.getNoEmpleado() ));

        for (Pedido pedido : infoPedido.getPedido()) {
            Platillo platillo = new Platillo( pedido.getId() );

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setPlatillo( platillo );
            detalleVenta.setCantidad( pedido.getCantidad() );
            venta.addDetalleVenta(detalleVenta);
        }

        return venta;
    }

    // Proceso de construccion especifico de cada tipo de lavadora
    // Permite que el tipo devuelto sea un subtipo de... (y por tanto,
    // las invocaciones al metodo crea() devuelvan tipos diferentes de...)
    protected abstract Venta crearVenta();

    protected abstract Venta recuperaVenta(long id);

}
