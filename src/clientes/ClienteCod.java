/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;


import java.io.FileInputStream;
import java.util.Date;

/**
 *
 * @author Rojeru San CL
 */
public class ClienteCod {
    
    public static String LISTAR_US = "SELECT * FROM cliente ORDER BY nombre";
    
    public static String REGISTRAR = "INSERT INTO cliente(id_cliente, nombre, sexo, telefono, direccion, saldo) "
            + "VALUES(?,?,?,?,?,?)";
    
    public static String ACTUALIZAR = "UPDATE cliente SET "
                + "nombre=?, "
                + "sexo=?, "
                + "telefono=?, "
                + "direccion=?  WHERE id_cliente=?";
    public static String ACTUALIZAR_SALDO = "UPDATE cliente SET "
                + "saldo=?   WHERE id_cliente=?";
     public static String REGISTRAR_CLIENTE_VENTA =" INSERT INTO cliente_venta(id_cliente, id_venta) "
            + "VALUES(?,?)";
    
    public static String ELIMINAR = "DELETE FROM cliente WHERE id_cliente = ?";
    
    public static String ELIMINAR_TODO = "DELETE FROM cliente";
    
    private String primaryKey;
    private String nombre;
    private String sexo;
    private String telefono;
    private String direccion;
    private String saldo;
    private String id_cliente;
    private String id_venta;

    public ClienteCod(){
        
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
    
     public String getId_venta() {
        return id_venta;
    }

    public void setId_venta(String id_venta) {
        this.id_venta = id_venta;
    }
}
