/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import com.sun.awt.AWTUtilities;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import ventas.ImageResizer;

/**
 *
 * @author Rojeru San CL
 */
public class SplashScreen extends javax.swing.JFrame {

    SplashScreen spl = this;

    /**
     * Creates new form SplashScreen
     */
    public SplashScreen() {
        initComponents();
        AWTUtilities.setWindowOpaque(this, false);
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/principal/logo.png")).getImage());
        this.setLocationRelativeTo(null);
        this.progreso.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        startThread();
    }

    void startThread() {
        Thread hi = new Thread(new Runnable() {
            @Override
            public void run() {
                AccesoLogin ven = new AccesoLogin(spl);
                ven.setLocationRelativeTo(null);
                if (isCompletado()) {
                    if (iniciarSesion()) {
                        ven.setVisible(true);
                    } else {
                        MenuPrincipalAd menu = new MenuPrincipalAd();
                        menu.setVisible(true);
                        ven.dispose();
                    }
                    dispose();
                } else {
                    System.exit(0);
                }
            }
        });
        hi.start();
    }

    public  boolean isCompletado() {
        conectar cc = new conectar();
        Connection cn = cc.conexion();
        PreparedStatement ps;
        String consulta = "select* from licencias";

        try {

            Statement st = cn.createStatement();
            ResultSet rs;

            rs = st.executeQuery(consulta);
            if (rs.next()) {
                //System.out.println("" + rs.getString("numero"));
                if(esFechaValida(rs.getString("fecha_inicio")))return true;
                String titulo;
                if(rs.getString("clave").equals("34ER45VD3-45DF45FDD-34GS46VB6-DF34GF45GDC"))
                    titulo="Version de prueba a vencido";
                else titulo="Clave vencida";
                String clave = JOptionPane.showInputDialog(null, "Ingrese la clave :"+ rs.getString("numero"), titulo , JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("" + clave);
                if((""+clave).equals("null"))return false;
                if (clave.equals(rs.getString("clave"))) {
                     if(rs.getString("clave").equals("34ER45VD3-45DF45FDD-34GS46VB6-DF34GF45GDC")) return true;
                     consulta = "DELETE FROM licencias WHERE (numero = '"+rs.getString("numero")+"');";
                     ps = cn.prepareStatement(consulta);
                     ps.executeUpdate();
                     JOptionPane.showMessageDialog(this, "La clave fue correcta.", "Registro", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                    return true;
                } else {

                    JOptionPane.showMessageDialog(null, "Clave de registro icorrecto.\n Contactece con el administrador\n\n"+
                            "DESARROLADOR: Edwin Geovanni Oy Tamay.\n" +
                            "TELEFONO: 986 119 3106.\n" +
                            "FACEBOOK: www.facebook.com/GeovanniOyTamay.7",
                            "Error", 0,  icono());
                   
                    return false;
                }
            }
                //System.out.println(""+rs.getString("sesion"));
            // num =num + Integer.parseInt(rs.getString("count(*)"));  

        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }

        return true;
    }
   boolean esFechaValida(String fecha_maxima){
       
        try{
           
        //fecha de hoy
        Date sistemaFech = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_fin=form.format(sistemaFech);        
         Date fehca_hoy = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_fin);
        // fecha de maxima
         Date fin = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_maxima);
         
          if( fehca_hoy.after(fin))  {
              System.out.println("fech venciada"); 
              return false;
           }
        }catch(Exception e){  
           System.out.println(""+e.getMessage()); 
        }
        System.out.println("fech valida"); 
        return true;
   }
    boolean iniciarSesion() {

        conectar cc = new conectar();
        Connection cn = cc.conexion();
        PreparedStatement ps;

        String consulta = "select sesion from configuraciones";

        try {

            Statement st = cn.createStatement();
            ResultSet rs;

            rs = st.executeQuery(consulta);
            if (rs.next() && rs.getString("sesion").equals("1")) {
                return true;
            }
                //System.out.println(""+rs.getString("sesion"));
            // num =num + Integer.parseInt(rs.getString("count(*)"));  

        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());
        }

        return false;
    }

        ImageIcon icono() {
        ImageResizer.MAX_HEIGHT=60;
        ImageResizer.MAX_WIDTH=60;
        ImageIcon ico = new ImageIcon(getClass().getResource("/imagenes/principal/cerradura.png"));
        Image im = ico.getImage();//convertimos la imagen icono a imagen
        BufferedImage bi = new BufferedImage//traformamomos la imge para la edicon
                (im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics bg = bi.getGraphics();
        bg.drawImage(im, 0, 0, null);
        bg.dispose();
        return ImageResizer.copyImage(bi);
    }

    public JProgressBar getJProgressBar() {
        return progreso;
    }

    public JLabel getJLabel() {
        return texto;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        texto = new javax.swing.JLabel();
        progreso = new org.edisoncor.gui.progressBar.ProgressBarRound();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        texto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        texto.setForeground(new java.awt.Color(255, 255, 255));
        texto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        texto.setText("Cargando componentes...");
        texto.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        texto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 640, -1));

        progreso.setForeground(new java.awt.Color(0, 102, 153));
        getContentPane().add(progreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 640, 20));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/splash1.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SplashScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private org.edisoncor.gui.progressBar.ProgressBarRound progreso;
    public static javax.swing.JLabel texto;
    // End of variables declaration//GEN-END:variables
}
