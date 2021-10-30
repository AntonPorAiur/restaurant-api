package mx.restaurante.cv.restaurantapimng.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "CV_VENTA")
public class Venta implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String observacion;

  private BigDecimal propina;

  @Column(name = "CV_FECHA_CREADO")
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private Date fechaCreado;

  @JsonIgnore
  @JsonIgnoreProperties({"venta", "hibernateLazyInitializer", "handler"})
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CV_FK_EMPLEADO", referencedColumnName = "CV_NUM_EMPLEADO")
  private Vendedor vendedor;

  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "CV_FK_DETALLE")
  private List<DetalleVenta> detalle;

  public Venta() {
    this.detalle = new ArrayList<>();
  }

  @PrePersist
  public void prePersist() {
    this.fechaCreado = new Date();
  } 

  public void addDetalleVenta(DetalleVenta venta) {
    this.detalle.add(venta);
}

  private static final long serialVersionUID = 1L;

}