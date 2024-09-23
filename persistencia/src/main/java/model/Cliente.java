package main.java.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @Column(name="id_cliente")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="id_pedido", foreignKey=@ForeignKey(name="fkClientePedido"), nullable=false)
    private Pedido pedido;

    private String nombre;

    private LocalDate fechaNacimiento;

    public Cliente() {
        super();
    }

    public Cliente(Pedido pedido, String nombre, LocalDate fechaNacimiento) {
        super();
        this.pedido = pedido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
