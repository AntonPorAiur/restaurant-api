package mx.restaurante.cv.restaurantapimng.factory;

import mx.restaurante.cv.restaurantapimng.models.entities.Venta;

public class VentaPedidoFactory extends VentaFactory {

    @Override
    public Venta crearVenta() {
        return new Venta();
    }

    @Override
    protected Venta recuperaVenta(long id) {
        return new Venta( id );
    }

}
