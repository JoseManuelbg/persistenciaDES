package main.java.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="pedido")
public class Pedido {

    @Id
    @Column(name="id_pedido")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.MERGE)
    private List<LineaPedido> lineas = new ArrayList<>();

    private String codigo;

    private LocalDate fechaCreacion;

    // Constructor por defecto requerido por Hibernate
    public Pedido() {
        // Puede estar vacío o inicializar algunos valores por defecto
        this.fechaCreacion = LocalDate.now(); // Inicialización si es necesario
    }

    // Constructor con parámetros
    public Pedido(String codigo) {
        super();
        this.codigo = codigo;
        this.fechaCreacion = LocalDate.now();
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedido> lineas) {
        this.lineas = lineas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
