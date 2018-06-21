/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
public class importar extends javax.swing.JInternalFrame {

     static conectar cc = new conectar();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public importar() {
        initComponents();        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
         progreso.setVisible(false);
         txt_porsen.setVisible(false);   
         mensage.setVisible(false);
         txt_error.setVisible(false);
         btn_error.setVisible(false);
          txt_error.setBackground(new Color(1,1,1,0));// tranparente el fondo del label
          

       
       
    }
    int velocidad=0;
   
  
    void cargar(double porsen){
          //System.out.println(porsen);
          txt_porsen.setText((int)porsen+"%"); 
          progreso.setValue((int)porsen);
          
          try {
            Thread.sleep(0);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo ejecutar la presentación");
        }
        
    }
    boolean existe_txt=true;
  void startThread() {
        Thread hi = new Thread(new Runnable() {
            @Override
            public void run() {
              lectura();
            }
        });
        hi.start();
    }
  double incremento;
  int num=0;
  double contar_lineas(){
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      double  nun_linea =0;
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
         
         while((linea=br.readLine())!=null)
            nun_linea++;
         
          incremento=100/nun_linea;
          existe_txt=true;
          
      }
      
      catch(Exception e){
        // e.printStackTrace();
         existe_txt=false;
        // System.out.println("lectura de archivo: "+e);
        JOptionPane.showMessageDialog(this, "Archivo "+nombre_archivo+" no encontrado.", "Error", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
         
      }
      return nun_linea;
  }
     void lectura(){
      buscar_ruta();
      contar_lineas();
      if(ruta.equals("nullnull") || existe_txt==false)return;         
      progreso.setVisible(true);
      txt_porsen.setText("0%");
      txt_porsen.setVisible(true);
      
      
      
      
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      double porsen= 0;
      
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File (ruta);
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);

         // Lectura del fichero
         String linea;
          String sql="";
         txt_info.setText("");
         while((linea=br.readLine())!=null){
            
                ejecutar_sql(linea); 
                sql=sql+linea;
                cargar(porsen=porsen+incremento);
         }
         txt_info.setText("__________________EJECUTADO CON EXITO______________________\n"+txt_info.getText());
         txt_porsen.setText("100%");
         progreso.setValue(100);
         registrar2.setVisible(false);
         mensage.setVisible(true);
        imagen.setIcon(new ImageIcon(getClass().getResource("/imagenes/principal/listo.png")));
        
         if(errores.equals(""))txt_info.setText("**No hubo errores**\n\n"+txt_info.getText());
         else txt_info.setText(errores+"\n\n"+txt_info.getText());
         txt_info.setText("___________________________ERRORES_____________________\n"+txt_info.getText());
         
         if(cont_error>0){
         txt_error.setText(""+cont_error);   
         txt_error.setVisible(true);
         btn_error.setVisible(true);
         startAnim();
         }
         
      }
      catch(Exception e){
         e.printStackTrace();
           
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
           
         }
      }
     }
     String ruta;
     String errores="";
    int cont_error=0;
     void ejecutar_sql(String sql){
         if(sql.equals(""))return;     
         
        
        //sql= "INSERT INTO tipo_producto VALUES ('otros')";
        //  System.out.println("sql"+ sql);
         try {
            ps = cn.prepareStatement(sql);
            ps.executeUpdate();
            txt_info.setText(sql+"\n"+txt_info.getText());
         }catch(Exception e){
             //System.out.println("error354:"+ e.getMessage());
             cont_error++;
             errores=errores+"\n"+e.getMessage();
         }
         
     }
   String nombre_archivo="";
   boolean parar=true;
   void animacion(){
       String ani[]= new String[9];
       ani[0]="/imagenes/principal/animacion/Imagen1.png";
       ani[1]="/imagenes/principal/animacion/Imagen2.png";
       ani[2]="/imagenes/principal/animacion/Imagen3.png";
       ani[3]="/imagenes/principal/animacion/Imagen4.png";
       ani[4]="/imagenes/principal/animacion/Imagen5.png";
       ani[5]="/imagenes/principal/animacion/Imagen6.png";
       ani[6]="/imagenes/principal/animacion/Imagen7.png";
       ani[7]="/imagenes/principal/animacion/Imagen8.png";
       ani[8]="/imagenes/principal/animacion/Imagen9.png";
       int vel=150;
       while(parar){
       for(int i=0; i<ani.length;i++){
           btn_error.setIcon(new ImageIcon(getClass().getResource(ani[i])));           
            try {
            Thread.sleep(vel);
        } catch (InterruptedException ex) {
            //JOptionPane.showMessageDialog(this, "No se pudo ejecutar la presentación");
            System.out.println("error354:"+ ex.getMessage());
        }
       }
       
       for(int i=ani.length-1; i>=0;i--){
           btn_error.setIcon(new ImageIcon(getClass().getResource(ani[i])));           
            try {
            Thread.sleep(vel);
        } catch (InterruptedException ex) {
            //JOptionPane.showMessageDialog(this, "No se pudo ejecutar la presentación");
             System.out.println("error354:"+ ex.getMessage());
        }
       }
       
       }
       
       
       
       
       
   }
   
   void startAnim() {
        Thread hi = new Thread(new Runnable() {
            @Override
            public void run() {
              animacion();
            }
        });
        hi.start();
    }
   void buscar_ruta(){
         FileDialog dialogoArchivo;
        dialogoArchivo = new FileDialog(new Frame(), "Seleccione su respaldo",FileDialog.LOAD);
        dialogoArchivo.setVisible(true);
        nombre_archivo=dialogoArchivo.getFile();
        ruta= dialogoArchivo.getDirectory() + nombre_archivo;   
        //System.out.println(ruta);       
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paneles = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        txt_error = new app.bolivia.swing.JCTextField();
        btn_error = new javax.swing.JLabel();
        mensage = new javax.swing.JButton();
        txt_porsen = new javax.swing.JLabel();
        progreso = new org.edisoncor.gui.progressBar.ProgressBarRound();
        registrar2 = new javax.swing.JButton();
        imagen = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_info = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setIconifiable(true);
        setTitle("INFORMACION");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_error.setEditable(false);
        txt_error.setBorder(null);
        txt_error.setForeground(new java.awt.Color(255, 49, 49));
        txt_error.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_error.setText("2233");
        txt_error.setFocusable(false);
        txt_error.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txt_error.setOpaque(false);
        txt_error.setPhColor(new java.awt.Color(255, 255, 255));
        txt_error.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_errorMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_errorMousePressed(evt);
            }
        });
        txt_error.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_errorActionPerformed(evt);
            }
        });
        txt_error.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_errorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_errorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_errorKeyTyped(evt);
            }
        });
        jPanel3.add(txt_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 100, 50));

        btn_error.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/animacion/Imagen1.png"))); // NOI18N
        btn_error.setToolTipText("RECIBI");
        jPanel3.add(btn_error, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 250, 190));

        mensage.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        mensage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/mensgae_impor.png"))); // NOI18N
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
        jPanel3.add(mensage, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        txt_porsen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txt_porsen.setText("100%");
        txt_porsen.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        txt_porsen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel3.add(txt_porsen, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, -1, -1));

        progreso.setForeground(new java.awt.Color(0, 102, 153));
        jPanel3.add(progreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 500, -1));

        registrar2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/btn_import.png"))); // NOI18N
        registrar2.setBorder(null);
        registrar2.setBorderPainted(false);
        registrar2.setContentAreaFilled(false);
        registrar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registrar2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/btn_import2.png"))); // NOI18N
        registrar2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        registrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrar2ActionPerformed(evt);
            }
        });
        jPanel3.add(registrar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/imagen_import.png"))); // NOI18N
        jPanel3.add(imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, -1));

        paneles.addTab("Importar", jPanel3);

        txt_info.setEditable(false);
        txt_info.setColumns(20);
        txt_info.setRows(5);
        txt_info.setText("***AQUÌ SE MOTRARA LA INDORMACION DE LA IMPORTACIÒN***");
        jScrollPane1.setViewportView(txt_info);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paneles.addTab("Informaciòn", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(paneles, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(paneles)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrar2ActionPerformed
     // respaldo();
        
 startThread();
 //   ejecuta_sql("");   
    }//GEN-LAST:event_registrar2ActionPerformed

    private void mensageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mensageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mensageActionPerformed

    private void txt_errorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_errorKeyTyped
       
    }//GEN-LAST:event_txt_errorKeyTyped

    private void txt_errorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_errorKeyReleased
    
    }//GEN-LAST:event_txt_errorKeyReleased

    private void txt_errorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_errorKeyPressed
        
    }//GEN-LAST:event_txt_errorKeyPressed

    private void txt_errorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_errorActionPerformed
    
    }//GEN-LAST:event_txt_errorActionPerformed

    private void txt_errorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_errorMouseClicked
 paneles.setSelectedIndex(1);          // TODO add your handling code here:
    }//GEN-LAST:event_txt_errorMouseClicked

    private void txt_errorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_errorMousePressed
         
    }//GEN-LAST:event_txt_errorMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel btn_error;
    private javax.swing.JLabel imagen;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mensage;
    private javax.swing.JTabbedPane paneles;
    private org.edisoncor.gui.progressBar.ProgressBarRound progreso;
    private javax.swing.JButton registrar2;
    public static app.bolivia.swing.JCTextField txt_error;
    private javax.swing.JTextArea txt_info;
    public static javax.swing.JLabel txt_porsen;
    // End of variables declaration//GEN-END:variables
}
