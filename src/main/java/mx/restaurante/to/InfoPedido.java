package mx.restaurante.to;

import lombok.Data;

@Data
public class InfoPedido {
  
  private String noEmpleado;
  private String propina;
  private String observacion;
  private Pedido pedido; 

}