/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import principal.GenerarNumero;
import principal.conectar;
//
/**
 *
 * @author Rojeru San CL
 */
public class OpcionesVen {

    static conectar cc = new conectar();
    static Connection cn = cc.conexion();
    static PreparedStatement ps,ps2;

    public static int registrar(VentasCod uc) {
        int rsu = 0;
        String sql = VentasCod.REGISTRAR;
        String sql2 = VentasCod.VETA_PRODUTOS;
        try {
            ps = cn.prepareStatement(sql);         
            //guardar venta
            ps.setString(1, uc.getPrimaryKey());
            ps.setString(2, uc.getTotal());
            ps.setString(3, uc.getFecha());
             rsu = ps.executeUpdate();
             
            //__guardar id de venta_producto
            ps2 = cn.prepareStatement(sql2);
          
            int cant= uc.getIds_producto().length;        
             
            for(int i=0;i<cant;i++){
               ps2.setString(1,  uc.getPrimaryKey());
               ps2.setString(2, uc.getIds_producto()[i][0]);
               ps2.setString(3, uc.getIds_producto()[i][1]);
               ps2.setString(4, uc.getIds_producto()[i][2]);
               ps2.setString(5, uc.getIds_producto()[i][3]);
               ps2.executeUpdate();
                
              // actulizar la cantidad de los productos catidad de productos menos cantidad vendida
                double cantidad = resta_cantidad(uc.getIds_producto()[i][0],Double.parseDouble(uc.getIds_producto()[i][1]));
                ps = cn.prepareStatement("UPDATE producto SET cantidad = '"+cantidad+"' WHERE id_producto = '"+uc.getIds_producto()[i][0]+"'");
                ps.executeUpdate();
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //System.out.println(sql);
        return rsu;
    }
 public static double resta_cantidad(String id, double venta){
      double can=0;
     try {
          Statement cs = cn.createStatement();
      
        ResultSet rs = cs.executeQuery("SELECT cantidad FROM producto where id_producto='"+id+"'");
             
              //System.out.println(id);
              if(rs.next()){
               can = Double.parseDouble(rs.getString("cantidad"))-venta; 
               }
             
              } catch (SQLException ex) {
             System.out.println("813 Error: "+ex.getMessage());
        }
        return can;
     
 }
    public static int eliminar(String id) {
        int rsu = 0;
        String sql_venta = VentasCod.ELIMINAR;
        String sql_cliente_venta = "DELETE FROM cliente_venta WHERE id_venta ="+id+"";
        String sql_venta_producto = "DELETE FROM venta_producto WHERE id_venta ="+id+"";

        try {
             ps = cn.prepareStatement(sql_venta_producto);           
            rsu = ps.executeUpdate();
            
            ps = cn.prepareStatement(sql_cliente_venta);           
            rsu = ps.executeUpdate();
            
            ps = cn.prepareStatement(sql_venta);
            ps.setString(1, id);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
             System.out.println("Error: "+ex.getMessage());
        }
       
        return rsu;
    }

    public static int eliminaTodos() {
        int rsu = 0;
        String sql_ventas = VentasCod.ELIMINAR_TODO;
        String sql_venta_cliente = "DELETE FROM cliente_venta";
        String sql_venta_producto = "DELETE FROM venta_producto";
        try {
            ps = cn.prepareStatement(sql_venta_producto);
            rsu = ps.executeUpdate();
            ps = cn.prepareStatement(sql_venta_cliente);
            rsu = ps.executeUpdate();
             ps = cn.prepareStatement(sql_ventas);
            rsu = ps.executeUpdate();
             OpcionesVen.listar("");
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        //System.out.println(sql);
        return rsu;
    }

    public static void listar(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) ventas.RegistroVentas.tablaVentas.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = VentasCod.LISTAR;
        } else {
            sql = "SELECT * FROM venta WHERE (id_venta like'" + busca + "%' or fecha='" + busca + "')"
                    + " ORDER BY id_venta DESC";
        }
        String datos[] = new String[4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("id_venta");
                datos[1] = rs.getString("total");
                //datos[1] = rs.getString("pago");
                //datos[1] = rs.getString("cambio");
                datos[2] = rs.getString("fecha");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void numeros() {
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "SELECT MAX(id_venta) FROM venta";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            if (rs.next()) {
                c = rs.getString(1);
            }

            if (c == null) {
                ventas.CajaAd.numFac.setText("00000001");
            } else {
                j = Integer.parseInt(c);
                GenerarNumero gen = new GenerarNumero();
                gen.generar(j);
                ventas.CajaAd.numFac.setText(gen.serie());

            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    /// consultas para graficar 
    public static double optener_ventas(String fecha,int opcion) {
       String sql="";
       String sql2="";
  
         if(opcion==0){
               sql = "SELECT id_venta FROM venta WHERE fecha='"+fecha+"'";
               sql2= "SELECT cantidad,compra,venta FROM venta_producto WHERE id_venta= ANY ("+sql+")";
         }
         else if(opcion==1){
               sql = "SELECT id_venta FROM venta WHERE fecha like '%"+fecha+"'";
               sql2= "SELECT cantidad,compra,venta FROM venta_producto WHERE id_venta= ANY ("+sql+")";
         }
        
        
       
         double cantidad=0;
         double compra=0;
         double venta=0;
         double total=0;
       try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql2);
           while (rs.next()) {
               
              cantidad=Double.parseDouble(rs.getString("cantidad"));
              compra=Double.parseDouble(rs.getString("compra"));
              venta=Double.parseDouble(rs.getString("venta"));
              total=total+((venta-compra)*cantidad);
              
              //  System.out.println(""+id);
                
            }            
          
         }catch (Exception ex) {
           System.out.println("1414 Error: " +ex.getMessage());
        }    
        return total;
    }
    
     public static String suma_total(String fecha) {
         String sql = "SELECT SUM(total) FROM venta WHERE fecha='"+fecha+"'";
         String total="";
         try{
         
         Statement st = cn.createStatement();
         ResultSet rs = st.executeQuery(sql);
         if(rs.next())
         total=""+rs.getString("SUM(total)");   
         }catch(Exception e){}
         // System.out.println(""+total);
         if(total.equals("null"))return "$0";  
        
        return "$"+total;
    }
     
     
 //______________________________________credito________________
 
     public static void optener_creditos(String nombre) {
       DefaultTableModel modelo = (DefaultTableModel) ventas.RegistroVentas.tablaCredito.getModel();
       String sql="";
       String sql2="";
      while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

         if(nombre.equals("")){
               sql = "SELECT cliente.id_cliente, cliente.nombre, venta.id_venta, venta.fecha, venta.total "
                       + "FROM (cliente INNER JOIN cliente_venta INNER JOIN venta) "
                       + "WHERE cliente.id_cliente=cliente_venta.id_cliente and venta.id_venta= cliente_venta.id_venta ";
               
              
         }
         else {
                 sql = "SELECT cliente.id_cliente, cliente.nombre, venta.id_venta, venta.fecha, venta.total "
                       + "FROM (cliente INNER JOIN cliente_venta INNER JOIN venta) "
                       + "WHERE cliente.id_cliente=cliente_venta.id_cliente and venta.id_venta= cliente_venta.id_venta and nombre like'"+nombre+"%'";
                 
         }
        
        
       
              
            
        try {
           Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String datos[] = new String[5];
            while (rs.next()) {
                datos[0] = rs.getString("id_cliente");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("id_venta");
                datos[3] = rs.getString("fecha");
                datos[4] = rs.getString("total");
                modelo.addRow(datos);
            }
            
           
           
        }catch (Exception ex) {
           System.out.println("111 Error: " +ex.getMessage());
        }        
     }      
     public static void optener_adeudo(String id) {
       
       String sql="";
         if(id.equals("")){              
               sql="SELECT SUM(saldo) AS total from cliente";              
         }
         else {
                 sql ="SELECT saldo as total from cliente where id_cliente='"+id+"'";      
         }
        try {
           Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
             RegistroVentas.txt_adeudo.setText("loco"); 
           while (rs.next()) {
                RegistroVentas.txt_adeudo.setText(rs.getString("total"));              
             
            }
           
        }catch (Exception ex) {
           System.out.println(ex.getMessage());
        }       
   
        
     }   
     
     public static void eliminar_deudas(String id) {
        int rsu = 0;
        String sql = "DELETE FROM cliente_venta WHERE id_cliente = '"+id+"'";

        try {
            ps = cn.prepareStatement(sql);            
            rsu = ps.executeUpdate();
        } catch (Exception ex) {
           System.out.println("error: "+ex.getMessage());
        }
        
        
    }
     
     
       public static void optener_productos_vendidos(String id) {
       DefaultTableModel modelo = (DefaultTableModel) ventas.mostrarProducto.tablaProductos_venta.getModel();
       String sql="";
     
      while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        
               sql = "SELECT producto.id_producto, producto.tipo, producto.nombre,venta_producto.cantidad,venta_producto.venta "
                       + "FROM (producto INNER JOIN venta_producto) "
                       + "WHERE  venta_producto.id_venta ="+id+" AND producto.id_producto= venta_producto.id_producto ";
              
            
        try {
           Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String datos[] = new String[6];
            while (rs.next()) {
                datos[0] = rs.getString("id_producto");
                datos[1] = rs.getString("tipo");
                datos[2] = rs.getString("nombre");
                datos[3] = rs.getString("cantidad");
                datos[4] = rs.getString("venta");
                datos[5]=""+(Double.parseDouble(datos[3]))*(Double.parseDouble(datos[4]));
                
                
                modelo.addRow(datos);
            }
            
           
           
        }catch (Exception ex) {
           System.out.println("Error: " +ex.getMessage());
        }        
     }    
     
 //___________________________________________________________________

}
