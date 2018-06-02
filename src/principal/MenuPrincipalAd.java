/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import productos.Productos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import usuarios.Usuarios;
import ventas.CajaAd;
import ventas.RegistroVentas;

/**
 *
 * @author Rojeru San CL
 */
public class MenuPrincipalAd extends javax.swing.JFrame {

    public static boolean cerra = false;
    public static boolean cerra1 = false;
    public static String tipo_usuario="AdMINISTRADOR";

    public boolean estacerrado(Object obj) {
        JInternalFrame[] activos = escritorio.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == obj) {
                cerrado = false;
                cerra = false;
                cerra1 = false;
            }
            i++;
        }
        return cerrado;
    }

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipalAd() {
        this.setResizable(false);
        initComponents();
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal/logo.png")).getImage());
        this.setTitle("MENÚ PRINCIPAL ADMINISTRADOR - SISTEMA PUNTO DE VENTA");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        info = new javax.swing.JButton();
        caja = new javax.swing.JButton();
        hora = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        userConect = new javax.swing.JLabel();
        logoSexo1 = new javax.swing.JLabel();
        logoSexo = new javax.swing.JLabel();
        desc = new javax.swing.JLabel();
        usuarios = new javax.swing.JButton();
        alimentos = new javax.swing.JButton();
        ventas = new javax.swing.JButton();
        escritorio = new principal.Escritorio();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(60, 101, 182));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        info.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        info.setForeground(new java.awt.Color(255, 255, 255));
        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/informacion1.png"))); // NOI18N
        info.setText("INFORMACIÓN");
        info.setBorder(null);
        info.setContentAreaFilled(false);
        info.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        info.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        info.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/informacion2.png"))); // NOI18N
        info.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoActionPerformed(evt);
            }
        });

        caja.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        caja.setForeground(new java.awt.Color(255, 255, 255));
        caja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/caja1.png"))); // NOI18N
        caja.setText("CAJA DE COBRO");
        caja.setBorder(null);
        caja.setContentAreaFilled(false);
        caja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        caja.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        caja.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/caja2.png"))); // NOI18N
        caja.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        caja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaActionPerformed(evt);
            }
        });

        hora.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        hora.setForeground(new java.awt.Color(255, 255, 255));
        hora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hora.setText("HORA");

        fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fecha.setText("DIA - MES - AÑO");

        userConect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        userConect.setForeground(new java.awt.Color(255, 255, 255));
        userConect.setText("USUARIO");

        logoSexo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoSexo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/conectado.png"))); // NOI18N

        logoSexo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoSexo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/descotectado1.png"))); // NOI18N

        desc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        desc.setForeground(new java.awt.Color(255, 255, 255));
        desc.setText("DESCONECTAR...");
        desc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        desc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                descMouseClicked(evt);
            }
        });

        usuarios.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        usuarios.setForeground(new java.awt.Color(255, 255, 255));
        usuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/usuario1.png"))); // NOI18N
        usuarios.setText("USUARIOS");
        usuarios.setBorder(null);
        usuarios.setContentAreaFilled(false);
        usuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        usuarios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        usuarios.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/usuario2.png"))); // NOI18N
        usuarios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariosActionPerformed(evt);
            }
        });

        alimentos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        alimentos.setForeground(new java.awt.Color(255, 255, 255));
        alimentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/alimentos1.png"))); // NOI18N
        alimentos.setText("ALMACEN");
        alimentos.setBorder(null);
        alimentos.setContentAreaFilled(false);
        alimentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        alimentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        alimentos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/alimentos2.png"))); // NOI18N
        alimentos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        alimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alimentosActionPerformed(evt);
            }
        });

        ventas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ventas.setForeground(new java.awt.Color(255, 255, 255));
        ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/ventas1.png"))); // NOI18N
        ventas.setText("REGISTRO VENTAS");
        ventas.setBorder(null);
        ventas.setContentAreaFilled(false);
        ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ventas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/ventas2.png"))); // NOI18N
        ventas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(logoSexo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(desc))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(logoSexo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userConect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(hora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(alimentos, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(caja, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(alimentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ventas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(hora)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fecha)
                            .addGap(2, 2, 2)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(userConect)
                                    .addGap(21, 21, 21)
                                    .addComponent(desc))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(logoSexo1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(logoSexo))))
                        .addComponent(info))
                    .addComponent(caja, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Fehca del sistema
        Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd  MMMM yyyy");
        fecha.setText(formato.format(sistemaFech));
        
        

        //Hora del sistema
        Timer tiempo = new Timer(100, new MenuPrincipalAd.horas());
        tiempo.start();
    }//GEN-LAST:event_formWindowOpened
    usuarios.Usuarios us;
    private void usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariosActionPerformed
       if(tipo_usuario.equals("NORMAL")){
            JOptionPane.showMessageDialog(this, "Acceso denegado.", "Error", 0,
                new ImageIcon(getClass().getResource("/imagenes/usuarios/impo.png")));
       }
       else
        if (estacerrado(us)) {
            us = new Usuarios();
            escritorio.add(us).setLocation(250, 3);
            us.show();
             } else {
                    JOptionPane.showMessageDialog(this, "La ventana USUARIOS\nya esta abierta !!!", "Aviso", 0,
                    new ImageIcon(getClass().getResource("/imagenes/principal/adver.png")));
            }
    }//GEN-LAST:event_usuariosActionPerformed
    productos.Productos al;
    private void alimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alimentosActionPerformed
       if(tipo_usuario.equals("NORMAL")){
            JOptionPane.showMessageDialog(this, "Acceso denegado.", "Error", 0,
                new ImageIcon(getClass().getResource("/imagenes/usuarios/impo.png")));
       }
       else
           if (estacerrado(al)) {
            al = new Productos();
            escritorio.add(al).setLocation(250, 3);
            al.show();
        } else {
            JOptionPane.showMessageDialog(this, "La ventana ALIMENTOS\nya esta abierta !!!", "Aviso", 0,
                    new ImageIcon(getClass().getResource("/imagenes/principal/adver.png")));
        }
    }//GEN-LAST:event_alimentosActionPerformed
    CajaAd ca;
    RegistroVentas rv;
    private void ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasActionPerformed
        if(tipo_usuario.equals("NORMAL")){
            JOptionPane.showMessageDialog(this, "Acceso denegado.", "Error", 0,
                new ImageIcon(getClass().getResource("/imagenes/usuarios/impo.png")));
       }
       else
            if (estacerrado(rv)) {
            rv = new RegistroVentas();
            escritorio.add(rv).setLocation(250, 3);
            rv.setSize(1000,450);
            rv.show();
            cerra1 = true;
        } else {
            JOptionPane.showMessageDialog(this, "La ventana REGISTRO VENTAS\nya esta abierta !!!", "Aviso", 0,
                    new ImageIcon(getClass().getResource("/imagenes/principal/adver.png")));
        }
    }//GEN-LAST:event_ventasActionPerformed

    private void cajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cajaActionPerformed
        if (estacerrado(ca)) {
            ca = new CajaAd();
            escritorio.add(ca).setLocation(160, 3);
            ca.show();
            cerra = true;
        } else {
            JOptionPane.showMessageDialog(this, "La ventana CAJA DE COBRO\nya esta abierta !!!", "Aviso", 0,
                    new ImageIcon(getClass().getResource("/imagenes/principal/adver.png")));
        }
    }//GEN-LAST:event_cajaActionPerformed

    private void descMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_descMouseClicked
        if (JOptionPane.showConfirmDialog(this, "Esta a punto de\ncerrar la sesión\n¿Desea continuar?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION, 0,
                new ImageIcon(getClass().getResource("/imagenes/principal/preg2.png"))) == JOptionPane.YES_OPTION) {
            this.dispose();
            new AccesoLogin().setVisible(true);
        }
    }//GEN-LAST:event_descMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(this, "Esta a punto de salir\nde la aplicación.\n¿Desea continuar?", "Cerrar", JOptionPane.YES_NO_OPTION, 0,
                new ImageIcon(getClass().getResource("/imagenes/principal/adver1.png"))) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }else{
            this.setDefaultCloseOperation(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoActionPerformed
        JOptionPane.showMessageDialog(this, "El Sistema Punto De Venta cuenta con una serie de opciones para el manejo del mismo.\n\n" +
                                            "*USUARIOS: este apartado registra usuarios para que puedan acceder al sistema,\n"
                                          + "el usuario administrador es el único con todos los privilegios sobre el sistema,\n"
                                          + "y el usuario normal solo cuenta con algunos.\n\n" +
                                            "*ALIMENTOS: este apartado registra los diferentes tipos de alimentos que se\n"
                                          + "venderán con su respectiva información.\n\n" +
                                            "*REGISTRO VENTAS: en este apartado se muestran todas las ventas realizadas\n"
                                          + "durante determinada fecha y se pueden borrar los registros así como hacer\n"
                                          + "búsquedas de los mismos.\n\n" +
                                            "*CAJA DE COBRO: en este apartado es donde se llevan a cabo las ventas de los\n"
                                          + "alimentos y se cobra al cliente.\n" +
                                            "________________________________________________________________\n"+
                                            "DESARROLADOR: Edwin Geovanni Oy Tamay.\n" +
                                            "TELEFONO: 986 119 3106.\n\n\n\n\n"
                                              + "                                           Version 1.0", "Información", 0,
                    new ImageIcon(getClass().getResource("/imagenes/principal/desarrollador.png")));
    }//GEN-LAST:event_infoActionPerformed
    class horas implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Date sistemaHora = new Date();
            String pmAm = "hh:mm:ss a";
            SimpleDateFormat formato = new SimpleDateFormat(pmAm);
            Calendar now = Calendar.getInstance();
            hora.setText(String.format(formato.format(sistemaHora), now));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalAd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalAd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalAd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipalAd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipalAd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alimentos;
    private javax.swing.JButton caja;
    private javax.swing.JLabel desc;
    public static principal.Escritorio escritorio;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel hora;
    private javax.swing.JButton info;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel logoSexo;
    private javax.swing.JLabel logoSexo1;
    public static javax.swing.JLabel userConect;
    private javax.swing.JButton usuarios;
    private javax.swing.JButton ventas;
    // End of variables declaration//GEN-END:variables
}
