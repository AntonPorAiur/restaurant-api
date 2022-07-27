package mx.restaurante.to;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InfoVenta {

    private long idVenta;
    private BigDecimal propina;
    private String observacion;
    private boolean cerrada;

}
