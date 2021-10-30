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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CV_VENDEDOR")
@AllArgsConstructor
@NoArgsConstructor
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
  @Temporal(TemporalType.DATE)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date fechaCreado;

  @JsonIgnore
  @JsonIgnoreProperties({"vendedor", "hibernateLazyInitializer", "handler"})
  @OneToMany(mappedBy = "vendedor", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Venta> venta;

  @PrePersist
  public void prePersist() {
    this.fechaCreado = new Date();
  } 

  private static final long serialVersionUID = 1L;
}