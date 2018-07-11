/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import usuarios.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import principal.GenerarCodigos;
import principal.conectar;
import static productos.ListaTipo.nuevo_tipo;

/**
 *
 * @author Rojeru San CL
 */
public class OpcionesAl {

    static conectar cc = new conectar();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public static int registrar(ProductosCod uc) {
        int rsu = 0;
        String sql = ProductosCod.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getPrimaryKey());           
            ps.setString(2, uc.getNombre());
            ps.setString(3, uc.getCosto_compra());
            ps.setString(4, uc.getCosto_venta());
            ps.setString(5, uc.getCantidad());             
            ps.setString(6, uc.getTipo());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }
    
    public static void llenar_combo() {// productos
        
         Productos.tipoAl1.removeAllItems();
        
        String SQL = "SELECT* FROM tipo_producto";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                Productos.tipoAl1.addItem(rs.getString("tipo"));
                
                
            }
           productos.Productos.tipoAl1.addItem("OTROS");
           productos.Productos.tipoAl1.setSelectedIndex( productos.Productos.tipoAl1.getItemCount()-1);
            
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesAl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void llenar_combo_caja() {// productos
        
        ListaProductosAd.tipoAl.removeAllItems();
        
        String SQL = "SELECT* FROM tipo_producto";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                ListaProductosAd.tipoAl.addItem(rs.getString("tipo"));
                
                
            }
           ListaProductosAd.tipoAl.addItem("TODOS");
           ListaProductosAd.tipoAl.setSelectedIndex( ListaProductosAd.tipoAl.getItemCount()-1);
            
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesAl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public static int actualizar(ProductosCod uc) {
        int rsu = 0;
        String sql = ProductosCod.ACTUALIZAR;
        try {
            ps = cn.prepareStatement(sql);           
            ps.setString(1, uc.getNombre());
            ps.setString(2, uc.getCosto_compra());
            ps.setString(3, uc.getCosto_venta());
            ps.setString(4, uc.getCantidad());
            ps.setString(5, uc.getTipo());
            ps.setString(6, uc.getPrimaryKey());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("002 error"+ex);
        }
      //  System.out.println(sql);
        return rsu;
    }

    public static int eliminar(String id) {
        int rsu = 0;
        String sql = ProductosCod.ELIMINAR;

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
        String sql = ProductosCod.ELIMINAR_TODO;
        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
            listar("",0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       // System.out.println(sql);
        return rsu;
    }
   static boolean generar_codigo=false;
    public static void extraerID() {
        if(generar_codigo==false)return;
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "SELECT MAX(id_producto) FROM producto";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                c = rs.getString(1);
            }

            if (c == null) {
                productos.Productos.codigo.setText("AL0001");
            } else {
                char r1 = c.charAt(2);
                char r2 = c.charAt(3);
                char r3 = c.charAt(4);
                char r4 = c.charAt(5);
                String r = "";
                r = "" + r1 + r2 + r3 + r4;
                j = Integer.parseInt(r);
                GenerarCodigos gen = new GenerarCodigos();
                gen.generar(j);
                productos.Productos.codigo.setText("AL" + gen.serie());

            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesAl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void listar(String busca, int opcion) {//opcion= 0 id/nombre, opcion=1 menor a..,  opcion=3 tipo
        DefaultTableModel modelo = (DefaultTableModel) productos.Productos.tablaProductos.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        
        
        if (busca.equals("")) {
            sql = ProductosCod.LISTAR;
        } else {
            if(opcion==0)
            sql = "SELECT * FROM producto WHERE (id_producto like'" + busca + "%' or nombre like'" + busca + "%') "
                    + " ORDER BY nombre";
            else
                if(opcion==1)
                    sql = "SELECT * FROM producto WHERE (cantidad <=" + Integer.parseInt(busca) + ") "
                    + " ORDER BY nombre";
                else sql = "SELECT * FROM producto WHERE (tipo like'" + busca + "%') "
                    + " ORDER BY nombre";
        }
        String datos[] = new String[6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("id_producto");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("costo_compra");
                datos[3] = rs.getString("costo_venta");
                datos[4] = rs.getString("cantidad");
                datos[5] = rs.getString("tipo");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesAl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public static void listar2(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) productos.ListaProductosAd.tablaProductos.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql ="SELECT * FROM producto ORDER BY nombre";
        } else {
            sql = "SELECT * FROM producto WHERE (id_producto like'" + busca + "%' or nombre like'" + busca + "%') "
                    + "or tipo='" + busca + "' ORDER BY nombre";
        }
        String datos[] = new String[5];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("id_producto");
                datos[1] = rs.getString("tipo");
                datos[2] = rs.getString("nombre");               
                datos[3] = rs.getString("costo_venta");
                datos[4] = rs.getString("cantidad");
               // System.out.println(datos[0]);
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesAl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(""+ex);
        }
       // System.out.println("____________________________________________________");
    }

    public static boolean isNumber(String n) {
        try {
            if (Integer.parseInt(n) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    
    
    //___CREDITO//________________________________________
    
    
    
    
    
    
    
    
    
    
    
    
    public static void CREDITOS(String busca, int opcion) {//opcion= 0 id/nombre, opcion=1 menor a..,  opcion=3 tipo
        DefaultTableModel modelo = (DefaultTableModel) productos.Productos.tablaProductos.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        switch(opcion){
            case 1: sql = "SELECT * FROM producto WHERE (id_producto like'" + busca + "%' or nombre like'" + busca + "%') "
                    + " ORDER BY nombre";
                break;
        }
        
        if (busca.equals("")) {
            sql = ProductosCod.LISTAR;
        } else {
            if(opcion==0)
            sql = "SELECT * FROM producto WHERE (id_producto like'" + busca + "%' or nombre like'" + busca + "%') "
                    + " ORDER BY nombre";
            else
                if(opcion==1)
                    sql = "SELECT * FROM producto WHERE (cantidad <=" + Integer.parseInt(busca) + ") "
                    + " ORDER BY nombre";
                else sql = "SELECT * FROM producto WHERE (tipo like'" + busca + "%') "
                    + " ORDER BY nombre";
        }
        String datos[] = new String[6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("id_producto");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("costo_compra");
                datos[3] = rs.getString("costo_venta");
                datos[4] = rs.getString("cantidad");
                datos[5] = rs.getString("tipo");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesAl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
