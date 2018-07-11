package principal;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author Sony
 */

public class conectar {

    Connection conect = null;
   String usu="root";
   String cont="root";

    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/basededatos", usu, cont); 
            
        } catch (Exception e) {
            
            
            
            
            try {
                
            // Class.forName("com.mysql.jdbc.Driver");
            conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/", usu, cont);     
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
             
             String tabla_cliente="CREATE TABLE `cliente` (\n" +
                                    "  `id_cliente` CHAR(50) NOT NULL,\n" +
                                    "  `nombre` CHAR(100) NULL,\n" +
                                    "  `sexo` CHAR(20) NULL,\n" +
                                    "  `telefono` CHAR(20) NULL,\n" +
                                    "  `direccion` CHAR(100) NULL,\n" +
                                    "  `saldo` DECIMAL(10,2) NULL,\n" +                                   
                                    "  PRIMARY KEY (`id_cliente`));";
             String tabla_cliente_venta="CREATE TABLE `basededatos`.`cliente_venta` (\n" +
                                        " `id_cliente` CHAR(50) NOT NULL,\n" +
                                        " `id_venta` INT(11) NOT NULL,\n" +
                                        " PRIMARY KEY (`id_cliente`, `id_venta`),\n" +
                                        " INDEX `id_venta_idx` (`id_venta` ASC) VISIBLE,\n" +
                                        " CONSTRAINT `id_cliente`\n" +
                                        " FOREIGN KEY (`id_cliente`)\n" +
                                        " REFERENCES `basededatos`.`cliente` (`id_cliente`)\n" +
                                        " ON DELETE NO ACTION\n" +
                                        " ON UPDATE NO ACTION,\n" +
                                        " CONSTRAINT `id_venta`\n" +
                                        " FOREIGN KEY (`id_venta`)\n" +
                                        " REFERENCES `basededatos`.`venta` (`id_venta`)\n" +
                                        " ON DELETE NO ACTION\n" +
                                        " ON UPDATE NO ACTION);";
             
             
             
            String tabla_configuraciones=" CREATE TABLE `configuraciones` (\n" +
                                            "  `sesion` INT(1) PRIMARY KEY,\n" +
                                            "  `recibo` INT(1) ,\n" +
                                            "  `credito` INT(1) );";
            String configuraciones_default="INSERT INTO configuraciones"
                                            + " VALUES (1,0,0)";// 1 = true y 0= false
            
            String tabla_licencias=" CREATE TABLE `licencias` (\n" +
                                    "  `numero` int(2) NOT NULL AUTO_INCREMENT,\n" +
                                    "  `fecha_inicio` varchar(20) DEFAULT NULL,\n" +                                   
                                    "  `clave` varchar(50) DEFAULT NULL,\n" +
                                    "  PRIMARY KEY (`numero`)\n" +") ";
            String licencias_default="INSERT INTO `licencias` VALUES (0,'"+fecha_prueba()+"','34ER45VD3-45DF45FDD-34GS46VB6-DF34GF45GDC')";// 1 = true y 0= false
            

            
            st.execute("create database basededatos; ");
            st.execute("use basededatos;");
            st.execute(tabla_producto);
            st.execute(tabla_tipo);
            st.execute(tabla_venta);            
            st.execute(tabla_usuario);
            st.execute(usuario_default);
            st.execute(tabla_venta_producto);
            st.execute(tabla_cliente);            
            st.execute(tabla_cliente_venta);
            
            st.execute(tabla_configuraciones);            
            st.execute(configuraciones_default);
            st.execute(tabla_licencias);
            st.execute(licencias_default);
            
            System.out.println("base de datos creado;");
            
            } catch (SQLException ex) {
                System.out.println("error: "+ ex);
                JOptionPane.showMessageDialog(null, "01) Error en la conexión en la Base de Datos" + ex);
            
        }
            
        }
        return conect;
    }   
   
    
    String fecha_prueba(){      
         java.util.Date sistemaFech = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
       String fecha_hoy = formato.format(sistemaFech);   
        
        try{   
             java.util.Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_hoy);
             Calendar calendar = Calendar.getInstance();
             calendar.setTime(inputDate);  
             calendar.add(Calendar.DAY_OF_YEAR,1);
             fecha_hoy=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
         
         }catch(Exception e){ 
            System.out.println(""+e.getMessage());
         }
        
        
        return fecha_hoy;       
        
    }
}
