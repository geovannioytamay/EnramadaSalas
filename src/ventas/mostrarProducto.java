/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import ventas.CajaAd;

/**
 *
 * @author Rojeru San CL
 */
public class mostrarProducto extends javax.swing.JInternalFrame {

   public static boolean visible=false;
   public static String id;
   public static String total;
    public mostrarProducto() {
        initComponents();
        //System.out.println(id);
        tablaProductos_venta.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaProductos_venta.setDefaultRenderer(Object.class, new principal.EstiloTablaRenderer());
       visible=true;
        tablaProductos_venta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/icono.png")));
        OpcionesVen.optener_productos_vendidos(id);
        id_venta.setText("ID: "+id);
        //total_venta.setText("TOTAL: $"+total);
        total_venta.setText("$"+total);
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);//quitar la barra de titulo
        
       
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos_venta = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        id_venta = new app.bolivia.swing.JCTextField();
        codigoL1 = new javax.swing.JLabel();
        total_venta = new app.bolivia.swing.JCTextField();
        codigoL2 = new javax.swing.JLabel();
        barraDeTitulo = new javax.swing.JPanel();
        miminizar = new javax.swing.JButton();
        miminizar1 = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 37, 77), 4));
        setClosable(true);
        setIconifiable(true);
        setTitle("LISTA DE ALIMENTOS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tablaProductos_venta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "TIPO", "NOMBRE", "CANTIDAD", "PRECIO X UNID", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos_venta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaProductos_venta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductos_ventaMouseClicked(evt);
            }
        });
        tablaProductos_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaProductos_ventaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaProductos_venta);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id_venta.setEditable(false);
        id_venta.setBackground(new java.awt.Color(34, 102, 145));
        id_venta.setBorder(null);
        id_venta.setForeground(new java.awt.Color(255, 255, 255));
        id_venta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        id_venta.setText("id");
        id_venta.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        id_venta.setOpaque(false);
        id_venta.setPhColor(new java.awt.Color(255, 255, 255));
        id_venta.setPlaceholder("CÓDIGO/NOMBRE");
        id_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_ventaActionPerformed(evt);
            }
        });
        id_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                id_ventaKeyReleased(evt);
            }
        });
        jPanel4.add(id_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 180, -1));

        codigoL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/buscarL.png"))); // NOI18N
        jPanel4.add(codigoL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 52));

        total_venta.setEditable(false);
        total_venta.setBackground(new java.awt.Color(34, 102, 145));
        total_venta.setBorder(null);
        total_venta.setForeground(new java.awt.Color(255, 255, 255));
        total_venta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total_venta.setText("id");
        total_venta.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        total_venta.setOpaque(false);
        total_venta.setPhColor(new java.awt.Color(255, 255, 255));
        total_venta.setPlaceholder("CÓDIGO/NOMBRE");
        total_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_ventaActionPerformed(evt);
            }
        });
        total_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                total_ventaKeyReleased(evt);
            }
        });
        jPanel4.add(total_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 180, -1));

        codigoL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/buscarL.png"))); // NOI18N
        jPanel4.add(codigoL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 250, 52));

        barraDeTitulo.setBackground(new java.awt.Color(7, 37, 77));
        barraDeTitulo.setAlignmentX(0.0F);
        barraDeTitulo.setAlignmentY(0.0F);

        miminizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        miminizar.setForeground(new java.awt.Color(255, 255, 255));
        miminizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/close.png"))); // NOI18N
        miminizar.setBorder(null);
        miminizar.setContentAreaFilled(false);
        miminizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miminizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        miminizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/close2.png"))); // NOI18N
        miminizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        miminizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miminizarActionPerformed(evt);
            }
        });

        miminizar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        miminizar1.setForeground(new java.awt.Color(255, 255, 255));
        miminizar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/ico.png"))); // NOI18N
        miminizar1.setBorder(null);
        miminizar1.setContentAreaFilled(false);
        miminizar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miminizar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        miminizar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        miminizar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miminizar1ActionPerformed(evt);
            }
        });

        titulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        titulo.setForeground(new java.awt.Color(255, 255, 255));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("PRODUCTOS DE LA VENTA");
        titulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                tituloMouseDragged(evt);
            }
        });

        javax.swing.GroupLayout barraDeTituloLayout = new javax.swing.GroupLayout(barraDeTitulo);
        barraDeTitulo.setLayout(barraDeTituloLayout);
        barraDeTituloLayout.setHorizontalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraDeTituloLayout.createSequentialGroup()
                .addComponent(miminizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(miminizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(974, 974, 974))
        );
        barraDeTituloLayout.setVerticalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(miminizar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(miminizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraDeTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(barraDeTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
        );

        jPanel4.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void id_ventaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_ventaKeyReleased
       
    }//GEN-LAST:event_id_ventaKeyReleased
    
           
        
    
    
    private void tablaProductos_ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductos_ventaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaProductos_ventaKeyPressed

    private void tablaProductos_ventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductos_ventaMouseClicked
  
    }//GEN-LAST:event_tablaProductos_ventaMouseClicked

    private void id_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_ventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_id_ventaActionPerformed

    private void total_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_ventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_total_ventaActionPerformed

    private void total_ventaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_total_ventaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_total_ventaKeyReleased

    private void miminizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizarActionPerformed
        visible=false;
        this.dispose();
    }//GEN-LAST:event_miminizarActionPerformed

    private void miminizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miminizar1ActionPerformed

    private void tituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tituloMouseDragged
           // System.out.println(""+(i++));
    Point punto=MouseInfo.getPointerInfo().getLocation();
    
    int x=punto.x-200;
    int y=punto.y-150;
    
    int max_punto_X=909; 
    int max_punto_Y=464; 
    
    int max_pos_X=max_punto_X-200; 
    int max_pos_Y=max_punto_Y-150;
    
    if((punto.x>=200 && punto.x<max_punto_X)  )
      this.setLocation(x,this.getY()); 

    if( (punto.y>=150 && punto.y<max_punto_Y) )
        this.setLocation(this.getX(),y);

    if(punto.x<200 )this.setLocation(3,this.getY());
    if(punto.x>max_punto_X)this.setLocation(max_pos_X,this.getY());
    if(punto.y<150 )this.setLocation(this.getX(),0);
    if(punto.y>max_punto_Y )this.setLocation(this.getX(),max_pos_Y);

 //this.setLocation(x,y);
 //System.out.println("x:"+this.getHeight()+" y:"+this.getWidth());
//System.out.println("x2:"+punto.x+" y2:"+punto.y);



    }//GEN-LAST:event_tituloMouseDragged
    int i=0;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barraDeTitulo;
    private javax.swing.JLabel codigoL1;
    private javax.swing.JLabel codigoL2;
    public static app.bolivia.swing.JCTextField id_venta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton miminizar;
    private javax.swing.JButton miminizar1;
    public static javax.swing.JTable tablaProductos_venta;
    private javax.swing.JLabel titulo;
    public static app.bolivia.swing.JCTextField total_venta;
    // End of variables declaration//GEN-END:variables
}
