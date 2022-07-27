package mx.restaurante.cv.restaurantapimng.aggregate;

import mx.restaurante.cv.restaurantapimng.models.entities.DetalleVenta;
import mx.restaurante.cv.restaurantapimng.models.entities.Vendedor;
import mx.restaurante.cv.restaurantapimng.models.entities.Venta;
import mx.restaurante.to.InfoPedido;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public abstract class VentaAbs {

    private Long id;
    private String observacion;
    private BigDecimal propina;
    private Date fechaCreado;
    private Vendedor vendedor;
    private List<DetalleVenta> detalle;

    public VentaAbs() {
    }

    public void setPropina(BigDecimal propina) {
        this.propina = propina;
    }

    public void setFechaCreado(Date fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public void setDetalle(List<DetalleVenta> detalle) {
        this.detalle = detalle;
    }

    public VentaAbs(InfoPedido infoPedido) {}

    public VentaAbs(String observacion) {
        this.observacion = observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void addDetalleVenta(DetalleVenta venta) {
        this.detalle.add(venta);
    }
}
