package org.example;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Pedidos")
public class Pedidos {
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        usuario_id = usuario_id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        cantidad = cantidad;
    }

    public Date getFecha_Compra() {
        return fecha_compra;
    }

    public void setFecha_Compra(Date fecha_Compra) {
        fecha_compra = fecha_Compra;
    }

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    @Column(name = "usuario_id")
    private int usuario_id;
    @Column(name = "producto")
    private String producto;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "fecha_compra")
    private Date fecha_compra;
}
