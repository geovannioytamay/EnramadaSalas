/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.configuracion;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import principal.conectar;
import ventas.CajaAd;





/**
 *
 * @author Rojeru San CL
 */
public class configuraciones extends javax.swing.JInternalFrame {

     static conectar cc = new conectar();
    static Connection cn = cc.conexion();
    static PreparedStatement ps;

    public configuraciones() {
        initComponents();        
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
       lblregistrado.setVisible(false);
       imregistrado.setVisible(false);
       lblbloqueado.setVisible(false);
       imbloqueado.setVisible(false);
       txtclabe.setVisible(false);
       combo_numero.setVisible(false);
       btn_aceptar.setVisible(false);
       incializar();    

       
       
    }
 void incializar(){
     
      
      String consulta="select* from configuraciones";
      
      try {
            
            Statement st = cn.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery(consulta);            
            if(rs.next()){
                if( rs.getString("sesion").equals("1"))sesion.setSelected(true);
                if( rs.getString("recibo").equals("1"))recibo.setSelected(true);
                if( rs.getString("credito").equals("1"))credito.setSelected(true);
            }
                //System.out.println(""+rs.getString("sesion"));
               // num =num + Integer.parseInt(rs.getString("count(*)"));  
            
            } catch (SQLException ex) {
           System.out.println(""+ex.getMessage());
        }
     
 }
 void actualizar_datos(String NomConfig, int con){
      String consulta="UPDATE configuraciones SET "+NomConfig+" = "+con+" WHERE ("+NomConfig+" <> "+con+");";
     // System.out.println(""+NomConfig+" "+ con);
      try {            
            ps = cn.prepareStatement(consulta);           
            ps.executeUpdate(); 
            } catch (SQLException ex) {
           System.out.println(""+ex.getMessage());
        }
     
 }
 
void isCompletado(){
     
     String consulta = "select* from licencias";

        try {

            Statement st = cn.createStatement();
            ResultSet rs;

            rs = st.executeQuery(consulta);
            if (rs.next()) {
                if(rs.getString("clave").equals("34ER45VD3-45DF45FDD-34GS46VB6-DF34GF45GDC")){
                  lblbloqueado.setText("Usted esta usando la version de prueba que vence el dìa "+rs.getString("fecha_inicio")+" selecione un nùmero de pago");              
                  lblbloqueado.setVisible(true);
                  combo_numero.setVisible(true);
                  btn_aceptar.setVisible(true);                  
                 
                }
                else{
                    lblbloqueado.setText("|"+rs.getString("numero")+"| Producto vence en el dia "+rs.getString("fecha_inicio"));                 
                    lblbloqueado.setVisible(true);
                    imbloqueado.setVisible(true);
                    txtclabe.setVisible(true);
                    
                }                 
                    
                return ;      
                
                
            }
            
            } catch (SQLException ex) {
           System.out.println(""+ex.getMessage());
            return ; 
        }
     
             lblregistrado.setVisible(true);
             imregistrado.setVisible(true);
             lblbloqueado.setVisible(false);
             imbloqueado.setVisible(false);
             txtclabe.setVisible(false);
 }
 
 void generar_claves(int numero){
     String ruta=buscar_ruta();
     if(ruta.equals("nullnull"))return;
     String codigo = JOptionPane.showInputDialog(null, "Ingrese la clave de seguridad:", "Seguridad" , JOptionPane.INFORMATION_MESSAGE);
     if((""+codigo).equals("null"))return ;     
     if(codigo.equals("34ER45VD3-45DF45FDD-34GS46VB6-DF34GF45GDC")){
         eliminar_prueba();
        for(int i=0;i<=numero;i++){   
         String clave=clave();         
         guardar_clave(clave,i);
         escritura(i+" "+clave, ruta);  
         
        }       
        combo_numero.setVisible(false);
        btn_aceptar.setVisible(false);
        isCompletado();
        
     }
     else JOptionPane.showMessageDialog(null, "Clave de registro icorrecto.\n Contactece con el administrador\n\n"+
                            "DESARROLADOR: Edwin Geovanni Oy Tamay.\n" +
                            "TELEFONO: 986 119 3106.\n" +
                            "FACEBOOK: ww.facebook.com/GeovanniOyTamay.7",
                            "Error", 0,  new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
  
 }
 void eliminar_prueba(){
    try{
        ps = cn.prepareStatement("DELETE FROM licencias WHERE (numero = '1');");
         ps.executeUpdate();
         }catch(Exception e){ 
            System.out.println(""+e.getMessage());
         }
 }
 void guardar_clave(String clave, int mes){
     java.util.Date sistemaFech = new java.util.Date();
     SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
     String fecha = formato.format(sistemaFech);  
     
     try{   
             java.util.Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
             Calendar calendar = Calendar.getInstance();
             calendar.setTime(inputDate);  
             calendar.add(Calendar.MONTH,mes);
             fecha=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
             
             String save="INSERT INTO licencias(fecha_inicio,clave) VALUES ('"+fecha+"','"+clave+"')";
             Statement st = cn.createStatement();
             st.execute(save);
             
         
         }catch(Exception e){ 
            System.out.println(""+e.getMessage());
         }
     
     
     
 }
 
 
String clave(){
    char [ ]digito={'1','2','3','4','5','6','7','8','9','0',
                    'A','B','C','D','E','F','G','H','I','J',
                    'K','L','M','N','Ñ','O','P','Q','R','S',
                    'T','U','V','W','X','Y','Z'};
    String clave="";
    for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                   int numero = (int) (Math.random() * digito.length);
                   clave=clave+digito[numero];
            }
            System.out.println(""+i);
            if(i<3)
            clave=clave+"-";
    }
            
    
     return clave;
 }
  static void escritura(String linea, String ruta ){
       
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
 String buscar_ruta(){
         FileDialog dialogoArchivo;
        dialogoArchivo = new FileDialog(new Frame(), "Configurar registro",FileDialog.SAVE);        
        dialogoArchivo.setVisible(true);
        return dialogoArchivo.getDirectory() + dialogoArchivo.getFile();   
       // System.out.println(ruta); 
    
            
        
    }
 
 void actulaizar(){
      String consulta = "select* from licencias";

        try {

            Statement st = cn.createStatement();
            ResultSet rs;

            rs = st.executeQuery(consulta);
            if (rs.next()) {
               // System.out.println(""+rs.getString("clave"));
                if(rs.getString("clave").equals(txtclabe.getText())){ 
                     ps = cn.prepareStatement("DELETE FROM licencias WHERE (numero = '"+rs.getString("numero")+"');");
                     ps.executeUpdate();                     
                     isCompletado();
                     JOptionPane.showMessageDialog(null, "Clave de registro correcto",
                            "Correcto", 0,  new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                    txtclabe.setText("");
                    
                } else{
                    JOptionPane.showMessageDialog(null, "Clave de registro icorrecto. \n Contactece con el administrador\n\n"+
                            "DESARROLADOR: Edwin Geovanni Oy Tamay.\n" +
                            "TELEFONO: 986 119 3106.\n" +
                            "FACEBOOK: www.facebook.com/GeovanniOyTamay.7",
                            "Error", 0,  new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                    txtclabe.setText("");
                }  
                
                
                return;                
            }
            
             }catch(Exception e){ 
            System.out.println(""+e.getMessage());
         }
 }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        config = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        sesion = new javax.swing.JCheckBox();
        recibo = new javax.swing.JCheckBox();
        credito = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        lblregistrado = new javax.swing.JLabel();
        combo_numero = new javax.swing.JComboBox();
        btn_aceptar = new javax.swing.JButton();
        txtclabe = new app.bolivia.swing.JCTextField();
        imregistrado = new javax.swing.JLabel();
        lblbloqueado = new javax.swing.JLabel();
        imbloqueado = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setClosable(true);
        setIconifiable(true);
        setTitle("INFORMACION");

        config.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                configMouseClicked(evt);
            }
        });
        config.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                configKeyPressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sesion.setText("Iniciar sesion con una cuenta.");
        sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sesionActionPerformed(evt);
            }
        });
        jPanel3.add(sesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        recibo.setText("Imprmir recibo de venta");
        recibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reciboActionPerformed(evt);
            }
        });
        jPanel3.add(recibo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 170, -1));

        credito.setText("Dar credito a clientes");
        credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditoActionPerformed(evt);
            }
        });
        jPanel3.add(credito, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 170, -1));

        config.addTab("Configuraciones", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblregistrado.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblregistrado.setForeground(new java.awt.Color(255, 255, 255));
        lblregistrado.setText("EL PROGRAMA YA ESTA REGISTRADO ");
        jPanel4.add(lblregistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 470, 40));

        combo_numero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        combo_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_numeroActionPerformed(evt);
            }
        });
        jPanel4.add(combo_numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 170, -1));

        btn_aceptar.setText("Aceptar");
        btn_aceptar.setToolTipText("");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        jPanel4.add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, -1, -1));

        txtclabe.setBorder(null);
        txtclabe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtclabe.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtclabe.setOpaque(false);
        txtclabe.setPhColor(new java.awt.Color(204, 204, 204));
        txtclabe.setPlaceholder("                  XXXX-XXXX-XXXX-XXXX");
        txtclabe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclabeActionPerformed(evt);
            }
        });
        txtclabe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtclabeKeyPressed(evt);
            }
        });
        jPanel4.add(txtclabe, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 290, 30));

        imregistrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imregistrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/registrado.png"))); // NOI18N
        imregistrado.setAlignmentY(0.0F);
        jPanel4.add(imregistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 400, 330));

        lblbloqueado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblbloqueado.setForeground(new java.awt.Color(255, 255, 255));
        lblbloqueado.setText("Registrado en el dìa 02/33/2018 y Vence en el dìa  02/33/2018 ");
        jPanel4.add(lblbloqueado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        imbloqueado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imbloqueado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/cerradura.png"))); // NOI18N
        imbloqueado.setAlignmentY(0.0F);
        jPanel4.add(imbloqueado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 340, 290));

        fondo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/fondo_config.png"))); // NOI18N
        fondo.setAlignmentY(0.0F);
        fondo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(14, 148, 193), 2));
        jPanel4.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, -1));

        config.addTab("Registro", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(config, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(config, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sesionActionPerformed
        actualizar_datos("sesion",(sesion.isSelected())? 1 : 0);// vonversio de bolleano a int
        principal.MenuPrincipalAd.cerrar_sesion.setVisible(sesion.isSelected());
        
        
    }//GEN-LAST:event_sesionActionPerformed

    private void reciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reciboActionPerformed
      actualizar_datos("recibo",(sesion.isSelected())? 1 : 0);// vonversio de bolleano a int
      CajaAd.hay_credito=sesion.isSelected();
    }//GEN-LAST:event_reciboActionPerformed

    private void configKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configKeyPressed
     
    }//GEN-LAST:event_configKeyPressed

    private void configMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configMouseClicked
      //System.out.println(""+config.getSelectedIndex());
        if(config.getSelectedIndex()==1){
         isCompletado();
        
     }
    }//GEN-LAST:event_configMouseClicked

    private void txtclabeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclabeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclabeActionPerformed

    private void txtclabeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclabeKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           actulaizar();
        }       
    }//GEN-LAST:event_txtclabeKeyPressed

    private void combo_numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_numeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_numeroActionPerformed

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
       int numero=combo_numero.getSelectedIndex();
       generar_claves(numero);       
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditoActionPerformed
      actualizar_datos("credito",(credito.isSelected())? 1 : 0);// vonversio de bolleano a int   
      CajaAd.hay_credito=credito.isSelected();
    }//GEN-LAST:event_creditoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JComboBox combo_numero;
    private javax.swing.JTabbedPane config;
    private javax.swing.JCheckBox credito;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel imbloqueado;
    private javax.swing.JLabel imregistrado;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblbloqueado;
    private javax.swing.JLabel lblregistrado;
    private javax.swing.JCheckBox recibo;
    private javax.swing.JCheckBox sesion;
    public static app.bolivia.swing.JCTextField txtclabe;
    // End of variables declaration//GEN-END:variables
}
