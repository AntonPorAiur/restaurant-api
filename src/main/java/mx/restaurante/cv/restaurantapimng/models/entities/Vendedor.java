package mx.restaurante.cv.restaurantapimng.models.entities;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "CV_VENDEDOR")
public class Vendedor implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "CV_NUM_EMPLEADO",unique = true)
  private Long noEmpleado;
  
  @Column(name = "CV_NOMBRE")
  private String nombre;
  
  @Column(name = "CV_APELLIDO")
  private String apellido;
  
  @Column(name = "CV_FECHA_CREADO")
  private Date fechaCreado;

  @JsonIgnoreProperties({"vendedor", "hibernateLazyInitializer", "handler"})
  @OneToMany(mappedBy = "vendedor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Venta> venta;

  @PrePersist
  public void prePersist() {
    this.fechaCreado = new Date();
  } 

  private static final long serialVersionUID = 1L;
}