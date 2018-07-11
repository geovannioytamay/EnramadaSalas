/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;


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

/**
 *
 * @author Rojeru San CL
 */
public class OpcionesCli {

    static conectar cc = new conectar();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public static int registrarCliente(ClienteCod uc) {
        int rsu = 0;
        String sql = ClienteCod.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getPrimaryKey());
            ps.setString(2, uc.getNombre());
            ps.setString(3, uc.getSexo());
            ps.setString(4, uc.getTelefono());
            ps.setString(5, uc.getDireccion());
            ps.setString(6, uc.getSaldo());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            
            System.out.println(ex.getMessage());
        }
        //System.out.println(sql);
        return rsu;
    }

    public static int actualizarCliente(ClienteCod uc) {
        int rsu = 0;
        String sql = ClienteCod.ACTUALIZAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getNombre());
            ps.setString(2, uc.getSexo());
            ps.setString(3, uc.getTelefono());
            ps.setString(4, uc.getDireccion());
            ps.setString(5, uc.getPrimaryKey());
            rsu = ps.executeUpdate();
        } catch (Exception ex) {
             System.out.println("error+ "+ex.getMessage());
        }
       
        return rsu;
    }
    
     public static int actualizarSaldo(ClienteCod uc, boolean esVenta) {
        int rsu = 0;
        String sql_saldo = ClienteCod.ACTUALIZAR_SALDO;
        String sql_venta_cliente= ClienteCod.REGISTRAR_CLIENTE_VENTA;
        try {
            ps = cn.prepareStatement(sql_saldo);
            ps.setString(1, uc.getSaldo());  
            ps.setString(2, uc.getPrimaryKey());
            rsu = ps.executeUpdate();
            if(esVenta){
            ps = cn.prepareStatement(sql_venta_cliente);
            ps.setString(1, uc.getPrimaryKey());  
            ps.setString(2,uc.getId_venta() );
            rsu = ps.executeUpdate();
            }
        } catch (SQLException ex) {
                System.out.println("error+ "+ex.getMessage());
        }
        
       
        return rsu;
    }

    public static int eliminarCliente(String id) {
        int rsu = 0;
        String sql = ClienteCod.ELIMINAR;

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
    static int  opcion=0;
    public static int eliminaTodos() {
        int rsu = 0;
        String sql = ClienteCod.ELIMINAR_TODO;
        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
            OpcionesCli.listarCliente("",opcion);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static void extraerID() {
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "SELECT MAX(id_cliente) FROM cliente";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                c = rs.getString(1);
            }
       System.out.printf(""+c);
            if (c == null) {
                clientes.Cliente.codigo.setText("CL0001");
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
                clientes.Cliente.codigo.setText("CL" + gen.serie());

            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void listarCliente(String busca, int cli) {//cli de donde lo esta llamando
        DefaultTableModel modelo;
        opcion=cli;        
       
        if(cli==0) modelo = (DefaultTableModel) clientes.Cliente.tablaCliente.getModel();       
        else    modelo = (DefaultTableModel) ventas.RegistroVentas.tablaCliente.getModel();
       
       

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        String datos[] =  datos = new String[6];
        if (busca.equals("")) {
            sql = ClienteCod.LISTAR_US;
        } else {
            sql = "SELECT * FROM cliente WHERE (id_cliente like'" + busca + "%' or nombre like'" + busca + "%') "
                    + " order by nombre";
        }
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("id_cliente");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("sexo");               
                datos[3] = rs.getString("telefono");
                datos[4] = rs.getString("direccion");
                datos[5] = rs.getString("saldo");
               
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
           System.out.println("323 ERROR: "+ex.getMessage());
        }
    }
}
