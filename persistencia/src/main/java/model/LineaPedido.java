package main.java.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="linea_pedido")
public class LineaPedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    @JoinColumn(name="id_pedido", foreignKey =  @ForeignKey(name="id_pedido"), nullable = false)
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(name="producto_id", foreignKey =  @ForeignKey(name="id_producto"), nullable = false)
    private Producto producto;
    
    private int cantidad;
    
    private LocalDate fechaCreacion;
    
    public LineaPedido() {
        super();
    }

    public LineaPedido(Pedido pedido, Producto producto, int cantidad) {
        super();
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.fechaCreacion = LocalDate.now();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }   
}