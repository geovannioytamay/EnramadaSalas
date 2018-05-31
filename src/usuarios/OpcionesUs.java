/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

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
public class OpcionesUs {

    static conectar cc = new conectar();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public static int registrarUsuario(UsuariosCod uc) {
        int rsu = 0;
        String sql = UsuariosCod.REGISTRAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getPrimaryKey());
            ps.setString(2, uc.getNombre());
            ps.setString(3, uc.getSexo());
            ps.setString(4, uc.getTipouser());
            ps.setString(5, uc.getContraseña());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return rsu;
    }

    public static int actualizarUsuario(UsuariosCod uc) {
        int rsu = 0;
        String sql = UsuariosCod.ACTUALIZAR;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, uc.getNombre());
            ps.setString(2, uc.getSexo());
            ps.setString(3, uc.getTipouser());
            ps.setString(4, uc.getContraseña());
            ps.setString(5, uc.getPrimaryKey());
            rsu = ps.executeUpdate();
        } catch (SQLException ex) {
        }
        System.out.println(sql);
        return rsu;
    }

    public static int eliminarUsuario(String id) {
        int rsu = 0;
        String sql = UsuariosCod.ELIMINAR;

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
        String sql = UsuariosCod.ELIMINAR_TODO;
        try {
            ps = cn.prepareStatement(sql);
            rsu = ps.executeUpdate();
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
        String SQL = "SELECT MAX(id_usuario) FROM usuarios";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                c = rs.getString(1);
            }
       System.out.printf(""+c);
            if (c == null) {
                usuarios.Usuarios.codigo.setText("US0001");
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
                usuarios.Usuarios.codigo.setText("US" + gen.serie());

            }

        } catch (SQLException ex) {
            Logger.getLogger(OpcionesUs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void listarUsuario(String busca) {
        DefaultTableModel modelo = (DefaultTableModel) usuarios.Usuarios.tablaUsuarios.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        String sql = "";
        if (busca.equals("")) {
            sql = UsuariosCod.LISTAR_US;
        } else {
            sql = "SELECT * FROM usuarios WHERE (id_usuario like'" + busca + "%' or nombre like'" + busca + "%') "
                    + " order by nombre";
        }
        String datos[] = new String[5];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString("id_usuario");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("sexo");
                datos[3] = rs.getString("tipo");
                datos[4] = rs.getString("contraseña");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesUs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
