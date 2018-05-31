/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.util.Date;

/**
 *
 * @author Rojeru San CL
 */
public class VentasCod {
    
    public static String LISTAR = "SELECT * FROM venta ORDER BY fecha";   

    
    public static String REGISTRAR = "INSERT INTO venta(id_venta, total, fecha) "
            + "VALUES(?,?,?)";
    
    public static String VETA_PRODUTOS = "INSERT INTO venta_producto(id_venta,id_producto, cantidad,compra,venta) "
            + "VALUES(?,?,?,?,?)";
    
    public static String ELIMINAR = "DELETE FROM venta WHERE id_venta = ?";
    
    public static String ELIMINAR_TODO = "DELETE FROM venta";
    
    private String primaryKey;
    private String total;
    private String fecha;
     private String ids_productos[][];

    public VentasCod(){
        
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public String[][] getIds_producto() {
        return ids_productos;
    }

    public void setIds_poductos(String ids_productos[][]) {
        
        this.ids_productos = ids_productos;
    }

}
