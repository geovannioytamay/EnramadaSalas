/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.FileDialog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import productos.OpcionesAl;



/**
 *
 * @author Rojeru San CL
 */
public class respaldo extends javax.swing.JInternalFrame {

     static conectar cc = new conectar();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public respaldo() {
        initComponents();        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
         progreso.setVisible(false);
         txt_porsen.setVisible(false);   
         mensage.setVisible(false);
       
           

       
       
    }
    int velocidad=0;
    double cout_registros(){
        String count_pro="select count(*) from producto";
        String count_tipo_pro="select count(*) from tipo_producto";
        String count_ven="select count(*) from venta";
        String count_ven_pro="select count(*) from venta_producto";
        String count_us="select count(*) from usuarios";
        double num=0;
        try {
            
            Statement st = cn.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery(count_pro);            
            if(rs.next())                 
                num =num + Integer.parseInt(rs.getString("count(*)"));  
            
            rs = st.executeQuery(count_tipo_pro);            
            if(rs.next())                 
                num =num + Integer.parseInt(rs.getString("count(*)"));                     
           
            rs = st.executeQuery(count_ven);
            if(rs.next())                
                num =num + Integer.parseInt(rs.getString("count(*)"));                      
            
            rs = st.executeQuery(count_ven_pro);
            if(rs.next())                 
                num =num + Integer.parseInt(rs.getString("count(*)"));                      
           
            rs = st.executeQuery(count_us);           
            if(rs.next())               
                num =num + Integer.parseInt(rs.getString("count(*)"));                     
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesAl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(num<400)velocidad = 5;
        else if(num<400)velocidad = 4;
        else if(num<1000)velocidad = 3;
        else if(num<3000)velocidad = 2;
        else if(num<5000)velocidad = 1;
        else velocidad = 0;
                
       
        
        
       // System.out.println(num);
        return num;
    }
    int num;
    void cargar(double porsen){
          //System.out.println(porsen);
          txt_porsen.setText((int)porsen+"%"); 
          progreso.setValue((int)porsen);
          
          try {
            Thread.sleep(velocidad);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo ejecutar la presentación");
        }
        
    }
  void startThread() {
        Thread hi = new Thread(new Runnable() {
            @Override
            public void run() {
              respaldo(); 
            }
        });
        hi.start();
    }
     public void respaldo() { 
          
          buscar_ruta();
          if(ruta.equals("nullnull"))return;
          crear_txt();// crea y elimina si existe una
          progreso.setVisible(true);
          txt_porsen.setText("0%");
          txt_porsen.setVisible(true);
          
         double num=(100.00/cout_registros());
         double porsen = 0;
        //System.out.println("num: "+(100/cout_registros()));
         
        String sql_producto = "SELECT * FROM producto";
        String sql_tipo_producto = "SELECT * FROM tipo_producto";
        String sql_venta = "SELECT * FROM venta";
        String sql_venta_producto = "SELECT * FROM venta_producto";
        String sql_usuarios= "SELECT * FROM usuarios";
        String datos;
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql_producto);
            datos="INSERT INTO producto VALUES (";
            
                    
            while (rs.next()) {                
                datos =datos + "'"+rs.getString("id_producto")+"',";
                datos =datos + "'"+rs.getString("nombre")+"',";
                datos =datos + rs.getString("costo_compra")+",";
                datos =datos + rs.getString("costo_venta")+",";
                datos =datos + rs.getString("cantidad")+",";
                datos =datos + "'"+rs.getString("tipo")+"')";
                escritura(datos,1);
                datos="INSERT INTO producto VALUES (";
                
                cargar(porsen=porsen+num);
            }
           // datos=datos.substring(0, datos.length()-3);
            //System.out.print(datos);
            
             escritura("",3);
            
  //__________________________________________________________________________          
            rs = st.executeQuery(sql_tipo_producto);
            datos="INSERT INTO tipo_producto VALUES (";
            while (rs.next()) {
                datos= datos + "'"+ rs.getString("tipo")+"')";
                escritura(datos,1);
                datos="INSERT INTO tipo_producto VALUES (";
               cargar(porsen=porsen+num);
            }
           // datos=datos.substring(0, datos.length()-3);
            //System.out.print(datos);
            
             escritura("",3);
  //__________________________________________________________________________          
            rs = st.executeQuery(sql_venta);
            datos="INSERT INTO venta VALUES (";
            while (rs.next()) {
                datos = datos + "'"+ rs.getString("id_venta")+"', ";
                datos = datos + rs.getString("total")+", ";
                datos = datos + "'"+ rs.getString("fecha")+"')";
                              
                escritura(datos,1);
                datos="INSERT INTO venta VALUES (";
               cargar(porsen=porsen+num);
            }
           // datos=datos.substring(0, datos.length()-3);
            //System.out.print(datos);
            
             escritura("",3);
   //__________________________________________________________________________         
            rs = st.executeQuery(sql_venta_producto);
            datos="INSERT INTO venta_producto VALUES (";
            while (rs.next()) {
                datos =datos + "'"+ rs.getString("id_venta")+"', ";
                datos =datos + "'"+ rs.getString("id_producto")+"', ";
                datos =datos + rs.getString("cantidad")+", ";
                datos =datos + rs.getString("compra")+", ";
                datos =datos + rs.getString("venta")+") ";
                
               
                escritura(datos,1);
                datos="INSERT INTO venta_producto VALUES (";
                cargar(porsen=porsen+num);
            }
           // datos=datos.substring(0, datos.length()-3);
            //System.out.print(datos);
            
             escritura("",3);
   //__________________________________________________________________________         
            rs = st.executeQuery(sql_usuarios);
            datos="INSERT INTO usuarios VALUES (";
            while (rs.next()) {
                datos = datos + "'"+rs.getString("id_usuario")+"', ";
                datos = datos + "'"+rs.getString("nombre")+"', ";
                datos = datos + "'"+rs.getString("sexo")+"', ";
                datos = datos + "'"+rs.getString("tipo")+"', ";
                datos = datos + "'"+rs.getString("contraseña")+"') ";
                
               
                escritura(datos,1);
                datos="INSERT INTO usuarios VALUES (";
               cargar(porsen=porsen+num);
            }
           // datos=datos.substring(0, datos.length()-3);
            //System.out.print(datos);
            
           
             txt_porsen.setText("100%");
             progreso.setValue(100);
             registrar2.setVisible(false);
             mensage.setVisible(true);
             imagen.setIcon(new ImageIcon(getClass().getResource("/imagenes/principal/listo.png")));
            

             
        } catch (SQLException ex) {
            Logger.getLogger(OpcionesAl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     void crear_txt(){
        try{ 
          File archivo = new File(ruta);
          BufferedWriter bw;
         bw = new BufferedWriter(new FileWriter(archivo));
        }catch(Exception e){
         System.out.print("error: " + e);
        }
         
     }
     
     static void escritura(String linea, int saltos){
       
         BufferedWriter bw = null;
         FileWriter fw = null;

    try {
        //String data = "Hola stackoverflow.com...";
        File file = new File(ruta);
        // Si el archivo no existe, se crea!
        if (!file.exists()) {
            file.createNewFile();
        }
        // flag true, indica adjuntar información al archivo.
        fw = new FileWriter(file.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);
        for(int i=0;i<saltos;i++)
        bw.newLine();        
        bw.write(linea);
        //System.out.println("información agregada!");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
                        //Cierra instancias de FileWriter y BufferedWriter
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        
     }
     static String ruta="";
   void buscar_ruta(){
         FileDialog dialogoArchivo;
        dialogoArchivo = new FileDialog(new principal.MenuPrincipalAd(), "Lista de Archivos desde Frame",FileDialog.SAVE);
        dialogoArchivo.setVisible(true);
        
        ruta= dialogoArchivo.getDirectory() + dialogoArchivo.getFile();   
        System.out.println(ruta);       
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagen = new javax.swing.JLabel();
        registrar2 = new javax.swing.JButton();
        progreso = new org.edisoncor.gui.progressBar.ProgressBarRound();
        txt_porsen = new javax.swing.JLabel();
        mensage = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setIconifiable(true);
        setTitle("INFORMACION");

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/imagen_export.png"))); // NOI18N

        registrar2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/btn_exmport.png"))); // NOI18N
        registrar2.setBorder(null);
        registrar2.setBorderPainted(false);
        registrar2.setContentAreaFilled(false);
        registrar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registrar2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/btn_exmport2.png"))); // NOI18N
        registrar2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        registrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrar2ActionPerformed(evt);
            }
        });

        progreso.setForeground(new java.awt.Color(0, 102, 153));

        txt_porsen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_porsen.setText("100%");
        txt_porsen.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        txt_porsen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mensage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mensage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/mensgae_expor.png"))); // NOI18N
        mensage.setBorder(null);
        mensage.setBorderPainted(false);
        mensage.setContentAreaFilled(false);
        mensage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mensage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mensage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mensage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mensageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(txt_porsen, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(registrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mensage, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registrar2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mensage, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txt_porsen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progreso, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrar2ActionPerformed
     // respaldo();
        
 startThread();
      
    }//GEN-LAST:event_registrar2ActionPerformed

    private void mensageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mensageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mensageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imagen;
    private javax.swing.JButton mensage;
    private org.edisoncor.gui.progressBar.ProgressBarRound progreso;
    private javax.swing.JButton registrar2;
    public static javax.swing.JLabel txt_porsen;
    // End of variables declaration//GEN-END:variables
}