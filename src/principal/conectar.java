package principal;

import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Sony
 */

public class conectar {

    Connection conect = null;
   

    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/basededatos", "root", "root"); 
            
        } catch (Exception e) {
            
            
            
            
            try {
                
            // Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");     
            Statement st = conect.createStatement();
            SplashScreen.texto.setText("Creando La Base dedatos");
            String tabla_producto="CREATE TABLE `producto` (" +
                                    "  `id_producto` char(50) NOT NULL,\n" +
                                    "  `nombre` char(100) DEFAULT NULL,\n" +
                                    "  `costo_compra` decimal(10,2) DEFAULT NULL,\n" +
                                    "  `costo_venta` decimal(10,2) DEFAULT NULL,\n" +
                                    "  `cantidad` int(11) DEFAULT NULL,\n" +
                                    "  `tipo` char(100) DEFAULT NULL,\n" +
                                    "  PRIMARY KEY (`id_producto`)\n" +
                                    ") ";
            
             String tabla_tipo="CREATE TABLE `tipo_producto` (\n" +
                                "  `tipo` char(150) NOT NULL,\n" +
                                "  PRIMARY KEY (`tipo`)\n" +
                                ") ";
             
             String tabla_venta="CREATE TABLE `venta` (\n" +
                                "  `id_venta` int(11) NOT NULL,\n" +
                                "  `total` decimal(10,2) DEFAULT NULL,\n" +
                                "  `fecha` varchar(50) DEFAULT NULL,\n" +
                                "  PRIMARY KEY (`id_venta`)\n" +
                                ") ";
             
             String tabla_usuario="CREATE TABLE `usuarios` (\n" +
                                    "  `id_usuario` varchar(20) NOT NULL,\n" +
                                    "  `nombre` varchar(50) DEFAULT NULL,\n" +
                                    "  `sexo` varchar(10) DEFAULT NULL,\n" +
                                    "  `tipo` varchar(50) DEFAULT NULL,\n" +
                                    "  `contraseña` varchar(50) DEFAULT NULL,\n" +
                                    "  PRIMARY KEY (`id_usuario`)\n" +
                                    ") ";
             String usuario_default="INSERT INTO usuarios VALUES ('US0001', 'ADMIN', 'MASCULINO', 'ADMINISTRADOR', 'admin')" +
                                    ",('US0002', 'NORMAL', 'MASCULINO', 'NORMAL', 'normal') ";
             
             String tabla_venta_producto="CREATE TABLE `venta_producto` (\n" +
                                            "  `id_venta` int(10) NOT NULL,\n" +
                                            "  `id_producto` varchar(50) NOT NULL,\n" +
                                            "  `cantidad` int(11) DEFAULT NULL,\n" +
                                            "  `compra` decimal(10,2) DEFAULT NULL,\n" +
                                            "  `venta` decimal(10,2) DEFAULT NULL,\n" +
                                            "  PRIMARY KEY (`id_venta`,`id_producto`)\n" +
                                            ") ";
            String tabla_configuraciones=" CREATE TABLE `configuraciones` (\n" +
                                            "  `sesion` INT(1) NULL,\n" +
                                            "  `recibo` INT(1) NULL);";
            String configuraciones_default="INSERT INTO configuraciones"
                                            + " VALUES (1,0)";// 1 = true y 0= false
            

            
            st.execute("create database basededatos; ");
            st.execute("use basededatos;");
            st.execute(tabla_producto);
            st.execute(tabla_tipo);
            st.execute(tabla_venta);            
            st.execute(tabla_usuario);
            st.execute(usuario_default);
            st.execute(tabla_venta_producto);
            System.out.println("base de datos creado;");
            
            } catch (SQLException ex) {
                System.out.println("error: "+ ex);
                JOptionPane.showMessageDialog(null, "01) Error en la conexión en la Base de Datos" + ex);
            
        }
            
        }
        return conect;
    }   
}
