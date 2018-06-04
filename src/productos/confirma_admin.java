/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import com.sun.glass.events.KeyEvent;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import static productos.OpcionesAl.cn;
import static productos.OpcionesAl.ps;
import usuarios.*;
import ventas.CajaAd;
import ventas.OpcionesVen;

/**
 *
 * @author Rojeru San CL
 */


public class confirma_admin extends javax.swing.JInternalFrame {

  static public int opcion =0;
    
    public confirma_admin() {
        initComponents();
        this.setLocation(500,150);
        
        
             
    }
    
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel3 = new javax.swing.JPanel();
        contraseña = new jpass.JRPasswordField();
        usuario = new app.bolivia.swing.JCTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("LOGIN PARA ELIMINAR");
        setName("d"); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        contraseña.setBackground(new java.awt.Color(34, 102, 145));
        contraseña.setBorder(null);
        contraseña.setForeground(new java.awt.Color(255, 255, 255));
        contraseña.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        contraseña.setOpaque(false);
        contraseña.setPhColor(new java.awt.Color(255, 255, 255));
        contraseña.setPlaceholder("CONTRSEÑA");
        contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contraseñaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                contraseñaKeyReleased(evt);
            }
        });
        jPanel3.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 180, -1));

        usuario.setBackground(new java.awt.Color(34, 102, 145));
        usuario.setBorder(null);
        usuario.setForeground(new java.awt.Color(255, 255, 255));
        usuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        usuario.setOpaque(false);
        usuario.setPhColor(new java.awt.Color(255, 255, 255));
        usuario.setPlaceholder("USUARIO");
        usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usuarioKeyReleased(evt);
            }
        });
        jPanel3.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/campoLoginUs.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 52));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/campoLoginPass.png"))); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 52));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("LOGIN  PARA ELIMINAR");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contraseñaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contraseñaKeyReleased
        usuario.setText(usuario.getText().toUpperCase());
    }//GEN-LAST:event_contraseñaKeyReleased

    private void usuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyReleased
 usuario.setText(usuario.getText().toUpperCase());
         
    }//GEN-LAST:event_usuarioKeyReleased

    private void usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            contraseña.requestFocus();
        }
    }//GEN-LAST:event_usuarioKeyPressed

    private void contraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contraseñaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             String us = usuario.getText();
             String pas = contraseña.getText();
                
             principal.AccesoLogin login= new  principal.AccesoLogin();
             if(login.Entrar(us,pas).equals("ADMINISTRADOR")) {  
                 System.out.print("paso");
                  int eliminaT=0;
                  String objeto ="";
                 switch(opcion){
                     case 0:eliminaT = OpcionesUs.eliminaTodos();
                            objeto="Usuarios";
                         break;
                     case 1:eliminaT = OpcionesAl.eliminaTodos();
                            objeto="Productos";
                         break;
                     case 2:eliminaT = OpcionesVen.eliminaTodos();
                            objeto="Ventas";
                         break;
                 }
               
                    if (eliminaT != 0) {
                        this.dispose();
                        JOptionPane.showMessageDialog(this, "Registros eliminados.",objeto, 0,
                            new ImageIcon(getClass().getResource("/imagenes/Productos/borrado.png")));
                        
                } 
             }else{
                 this.dispose();
                 JOptionPane.showMessageDialog(this, "No eres un administrador", "Acceso", 0,
                            new ImageIcon(getClass().getResource("/imagenes/principal/passLogin.png")));           
             }
        }
    }//GEN-LAST:event_contraseñaKeyPressed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static jpass.JRPasswordField contraseña;
    public static javax.swing.JDesktopPane jDesktopPane1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JPanel jPanel3;
    public static app.bolivia.swing.JCTextField usuario;
    // End of variables declaration//GEN-END:variables
}
