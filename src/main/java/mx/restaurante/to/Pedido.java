package mx.restaurante.to;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Pedido {
  
  private Long id; // Id platillo
  private String nombre; // Nombre platillo
  private String fechaCreado; // Fecha creado
  private BigDecimal precio;
  private Integer cantidad;

}
