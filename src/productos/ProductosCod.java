/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import usuarios.*;
import java.io.FileInputStream;
import java.util.Date;

/**
 *
 * @author Rojeru San CL
 */
public class ProductosCod {
    
    public static String LISTAR = "SELECT * FROM producto ORDER BY nombre";
    
    public static String LISTAR_CAJA = "SELECT id_producto, nombre, tipo, costo_venta FROM producto ORDER BY nombre";
    
    public static String REGISTRAR = "INSERT INTO producto(id_producto,nombre, costo_compra, costo_venta, cantidad, tipo,medida) "
            + "VALUES(?,?,?,?,?,?,?)";
    
    public static String ACTUALIZAR = "UPDATE producto SET "
                + "nombre=?, "
                + "costo_compra=?, "
                + "costo_venta=?, "
                + "cantidad=?, "
                + "tipo=?, "
                + "medida=? "
                + "WHERE id_producto=?";
    
    public static String ELIMINAR = "DELETE FROM producto WHERE id_producto = ?";
    
    public static String ELIMINAR_TODO = "DELETE FROM producto";
    
    private String primaryKey;    
    private String nombre;
    private String costo_compra;
    private String costo_venta;
    private String cantidad;
    private String tipo;
    private String medida;

   

    public ProductosCod(){
        
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCosto_compra() {
        return costo_compra;
    }

    public void setCosto_compra(String compra) {
        this.costo_compra = compra;
    }
    
    public String getCosto_venta() {
        return costo_venta;
    }

    public void setCosto_venta(String venta) {
        this.costo_venta= venta;
    }
    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad= cantidad;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
     public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getMedida() {
        return medida;
    }

    
}
