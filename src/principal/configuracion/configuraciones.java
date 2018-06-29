/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.configuracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import principal.conectar;




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
            }
                //System.out.println(""+rs.getString("sesion"));
               // num =num + Integer.parseInt(rs.getString("count(*)"));  
            
            } catch (SQLException ex) {
           System.out.println(""+ex.getMessage());
        }
     
 }
 void actualizar_datos(String NomConfig, int con){
      String consulta="UPDATE configuraciones SET "+NomConfig+" = "+con+" WHERE ("+NomConfig+" <> "+con+");";
      System.out.println(""+NomConfig+" "+ con);
      try {
            
            ps = cn.prepareStatement(consulta);           
            ps.executeUpdate();           
            
            
            } catch (SQLException ex) {
           System.out.println(""+ex.getMessage());
        }
     
 }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        config = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        sesion = new javax.swing.JCheckBox();
        recibo = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        lblregistrado = new javax.swing.JLabel();
        txtclabe = new javax.swing.JTextField();
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

        config.addTab("Configuraciones", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblregistrado.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblregistrado.setForeground(new java.awt.Color(255, 255, 255));
        lblregistrado.setText("EL PROGRAMA YA ESTA REGISTRADO ");
        jPanel4.add(lblregistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 470, 40));

        txtclabe.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtclabe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtclabe.setText("XXXX-XXXX-XXXX-XXXX-XXXX-XXXX");
        txtclabe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclabeActionPerformed(evt);
            }
        });
        jPanel4.add(txtclabe, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 290, 30));

        imregistrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imregistrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/registrado.png"))); // NOI18N
        imregistrado.setAlignmentY(0.0F);
        imregistrado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(14, 148, 193), 2));
        jPanel4.add(imregistrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 400, 330));

        lblbloqueado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblbloqueado.setForeground(new java.awt.Color(255, 255, 255));
        lblbloqueado.setText("Registrado en el dìa 02/33/2018 y Vence en el dìa  02/33/2018 ");
        jPanel4.add(lblbloqueado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        imbloqueado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imbloqueado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/cerradura.png"))); // NOI18N
        imbloqueado.setAlignmentY(0.0F);
        imbloqueado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(14, 148, 193), 2));
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
    }//GEN-LAST:event_reciboActionPerformed

    private void configKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_configKeyPressed
     
    }//GEN-LAST:event_configKeyPressed

    private void configMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configMouseClicked
      System.out.println(""+config.getSelectedIndex());
        if(config.getSelectedIndex()==1){
         if(principal.SplashScreen.isCompletado()){
             lblregistrado.setVisible(true);
             imregistrado.setVisible(true);
             lblbloqueado.setVisible(false);
             imbloqueado.setVisible(false);
              txtclabe.setVisible(false);
             
         }
         else{
              lblregistrado.setVisible(false);
             imregistrado.setVisible(false);
             lblbloqueado.setVisible(true);
             imbloqueado.setVisible(true);
              txtclabe.setVisible(true);
         }
     }
    }//GEN-LAST:event_configMouseClicked

    private void txtclabeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclabeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclabeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane config;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel imbloqueado;
    private javax.swing.JLabel imregistrado;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblbloqueado;
    private javax.swing.JLabel lblregistrado;
    private javax.swing.JCheckBox recibo;
    private javax.swing.JCheckBox sesion;
    private javax.swing.JTextField txtclabe;
    // End of variables declaration//GEN-END:variables
}
