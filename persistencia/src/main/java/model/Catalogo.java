package main.java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="catalogo")
public class Catalogo {
@Id
@Column(name="id_catalogo")
@GeneratedValue(strategy=GenerationType.AUTO)
private long id;
@ManyToOne
@JoinColumn(name="id",foreignKey=@ForeignKey(name="fkCatalogoProducto"),nullable=false)
private Producto producto;

private String nombre;

private String descripcion;

public Catalogo() {
super();
}

public Catalogo(Producto producto,String nombre,String descripcion) {
super();
this.producto=producto;
this.nombre=nombre;
this.descripcion=descripcion;
}

public Producto getProducto() {
return producto;
}

public void setProducto(Producto producto) {
this.producto = producto;
}

public String getNombre() {
return nombre;
}

public void setNombre(String nombre) {
this.nombre = nombre;
}

public String getDescripcion() {
return descripcion;
}

public void setDescripcion(String descripcion) {
this.descripcion = descripcion;
}

public long getId() {
return id;
}

public void setId(long id) {
this.id = id;
}


}