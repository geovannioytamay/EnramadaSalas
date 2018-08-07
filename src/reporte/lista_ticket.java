/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reporte;

/**
 *
 * @author OyTamay
 */
public class lista_ticket {
   String Codigo;
   String Producto;
   String Precio;
   String Cantidad;
   String Importe;

    public lista_ticket(String Codigo, String Producto, String Precio, String Cantidad, String Importe) {
        this.Codigo = Codigo;
        this.Producto = Producto;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Importe = Importe;
    }

    public String getCodigo() {
        return Codigo;
    }

    public String getProducto() {
        return Producto;
    }

    public String getPrecio() {
        return Precio;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public String getImporte() {
        return Importe;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public void setProducto(String Producto) {
        this.Producto = Producto;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public void setImporte(String Importe) {
        this.Importe = Importe;
    }
   
   
    
}
