package mx.restaurante.cv.restaurantapimng.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
@Entity
@Table(name = "CV_PLATILLO")
public class Platillo implements Serializable{
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CV_NOMBRE")
  private String nombre;

  @Column(name = "CV_PRECIO")
  private BigDecimal precio;

  @Column(name = "CV_CATEGORIA")
  private Short categoria;

  @Column(name = "CV_FECHA_CREADO")
  @Temporal(TemporalType.DATE)
  private Date fechaCreado;

  @PrePersist
  public void prePersist() {
    this.fechaCreado = new Date();
  }

  public Platillo(Long id) {
    this.id = id;
  }
  
  private static final long serialVersionUID = 1L;

}