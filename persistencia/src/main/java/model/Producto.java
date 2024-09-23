package main.java.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="producto")
public class Producto {
	
	@Id //PK
	@Column(name="id_producto")
	@GeneratedValue(strategy = GenerationType.AUTO) //Se genera automaticamente
	private long id;


	
	
	@Column
	@OneToMany(mappedBy = "producto")
	private List<LineaPedido> lineas;
	
	private String nombre;
	
	private String descripcion;
	
	private LocalDate fechaAlta;
	
	private LocalDate fechaBaja;
	
	public Producto() {
		super();
	}
	
	
	public Producto(String nombre, String descripcion, LocalDate fechaAlta, LocalDate fechaBaja	) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
	}




	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public LocalDate getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(LocalDate fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	
}
