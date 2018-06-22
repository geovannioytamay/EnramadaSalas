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
                int cantidad = resta_cantidad(uc.getIds_producto()[i][0],uc.getIds_producto()[i][4]);
                ps = cn.prepareStatement("UPDATE producto SET cantidad = '"+cantidad+"' WHERE id_producto = '"+uc.getIds_producto()[i][0]+"'");
                ps.executeUpdate();
            }
            
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //System.out.println(sql);
        return rsu;
    }
 public static int resta_cantidad(String id, String venta) throws SQLException{
       Statement cs = cn.createStatement();
        ResultSet rs = cs.executeQuery("SELECT cantidad FROM producto where id_producto='"+id+"'");
              int can=0;
              //System.out.println(id);
              if(rs.next()){
               can = Integer.parseInt(rs.getString("cantidad"))-Integer.parseInt(venta); 
               }
              
              return can;
       
     
 }
    public static int eliminar(String id) {
        int rsu = 0;
        String sql = VentasCod.ELIMINAR;

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, id);
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static int eliminaTodos() {
        int rsu = 0;
        String sql = VentasCod.ELIMINAR_TODO;
        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
             OpcionesVen.listar("");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
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
    public static double optener_ventas(String fecha,int opcion) throws SQLException {
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
        
        
       
         int cantidad=0;
         double compra=0;
         double venta=0;
         double total=0;
       
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql2);
           while (rs.next()) {
               
              cantidad=Integer.parseInt(rs.getString("cantidad"));
              compra=Double.parseDouble(rs.getString("compra"));
              venta=Double.parseDouble(rs.getString("venta"));
              total=total+((venta-compra)*cantidad);
              
              //  System.out.println(""+id);
                
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

}
