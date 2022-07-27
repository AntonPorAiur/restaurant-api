package mx.restaurante.to;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class InfoPedido {
  
  private long noEmpleado;
  private BigDecimal propina;
  private String observacion;
  private List<Pedido> pedido;
  private boolean cerrada;

}