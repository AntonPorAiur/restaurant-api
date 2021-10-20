package mx.restaurante.cv.restaurantapimng.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "CV_DETALLE_VENTA")
public class DetalleVenta implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY)
  private Platillo platillo;

  private Integer cantidad;

  // Subtotal
  public BigDecimal CalcularImporte() {
    return platillo.getPrecio().multiply(new BigDecimal(cantidad));
  }

  private static final long serialVersionUID = 1L;

}