/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.awt.event.KeyEvent;
import productos.ListaProductosAd;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import static principal.MenuPrincipalAd.escritorio;
import static ventas.OpcionesVen.cn;

/**
 *
 * @author Rojeru San CL
 */
public class CajaAd extends javax.swing.JInternalFrame {

    /**
     * Creates new form Usuarios
     */
    public boolean estacerrado(Object obj) {
        JInternalFrame[] activos = escritorio.getAllFrames();
        boolean cerrado = true;
        int i = 0;
        while (i < activos.length && cerrado) {
            if (activos[i] == obj) {
                cerrado = false;
            }
            i++;
        }
        return cerrado;
    }

    // 

    public CajaAd() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        tablaCaja.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaCaja.setDefaultRenderer(Object.class, new principal.EstiloTablaRenderer());
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/caja/icono.png")));
        tablaCaja.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        limpiaCampos();
        productos.ListaProductosAd lista = new productos.ListaProductosAd();
        ListaProductos.add(lista);
        lista.toFront();
        lista.setVisible(true);

        ((javax.swing.plaf.basic.BasicInternalFrameUI) ListaProductos.getUI()).setNorthPane(null);
                
        borrar.setMnemonic(KeyEvent.VK_E);
        cancelar.setMnemonic(KeyEvent.VK_T);
//ListaProductos.setUI(null);
    }
 
    public static String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);

    }

    void limpiaCampos() {
        DefaultTableModel modelo = (DefaultTableModel) tablaCaja.getModel();

        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        recibi.setText("");
        cambio.setText("");
        total.setText("0.0");
        fecha.setText("");
        fecha.setText(fechaactual());
        fecha.setAction(null);
        OpcionesVen.numeros();
    }

    static public void total() {
        String pre;
        String can;
        double total = 0;
        double precio;
        int cantidad;
        double imp = 0.0;

        for (int i = 0; i < ventas.CajaAd.tablaCaja.getRowCount(); i++) {
            pre = ventas.CajaAd.tablaCaja.getValueAt(i, 3).toString();
            can = ventas.CajaAd.tablaCaja.getValueAt(i, 4).toString();
            precio = Double.parseDouble(pre);
            cantidad = Integer.parseInt(can);
            imp = precio * cantidad;
            total = total + imp;
            ventas.CajaAd.tablaCaja.setValueAt(Math.rint(imp * 100) / 100, i, 5);

        }
        ventas.CajaAd.total.setText("" + Math.rint(total * 100) / 100);

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
        recibi = new app.bolivia.swing.JCTextField();
        codigoL6 = new javax.swing.JLabel();
        cambio = new app.bolivia.swing.JCTextField();
        codigoL7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        borrar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();
        vender = new javax.swing.JButton();
        total = new app.bolivia.swing.JCTextField();
        codigoL3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCaja = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        numFac = new app.bolivia.swing.JCTextField();
        codigoL4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        ListaProductos = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        fecha = new app.bolivia.swing.JCTextField();
        codigoL5 = new javax.swing.JLabel();
        barraDeTitulo = new javax.swing.JPanel();
        miminizar = new javax.swing.JButton();
        miminizar1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 37, 77), 7));
        setClosable(true);
        setIconifiable(true);
        setTitle("CAJA DE CORBO");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        recibi.setBackground(new java.awt.Color(34, 102, 145));
        recibi.setBorder(null);
        recibi.setForeground(new java.awt.Color(255, 255, 255));
        recibi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        recibi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        recibi.setOpaque(false);
        recibi.setPhColor(new java.awt.Color(255, 255, 255));
        recibi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibiActionPerformed(evt);
            }
        });
        recibi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                recibiKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                recibiKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                recibiKeyTyped(evt);
            }
        });
        jPanel2.add(recibi, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 130, -1));

        codigoL6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/recibi.png"))); // NOI18N
        codigoL6.setToolTipText("RECIBI");
        jPanel2.add(codigoL6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, 52));

        cambio.setEditable(false);
        cambio.setBackground(new java.awt.Color(34, 102, 145));
        cambio.setBorder(null);
        cambio.setForeground(new java.awt.Color(255, 255, 255));
        cambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cambio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cambio.setOpaque(false);
        cambio.setPhColor(new java.awt.Color(255, 255, 255));
        cambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cambioKeyPressed(evt);
            }
        });
        jPanel2.add(cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 120, 30));

        codigoL7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/cambio.png"))); // NOI18N
        codigoL7.setToolTipText("CAMBIO");
        jPanel2.add(codigoL7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, 52));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, -1, -1));

        borrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/elimina2.png"))); // NOI18N
        borrar.setBorder(null);
        borrar.setContentAreaFilled(false);
        borrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        borrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        borrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/elimina1.png"))); // NOI18N
        borrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        jPanel2.add(borrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

        cancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/cancela2.png"))); // NOI18N
        cancelar.setBorder(null);
        cancelar.setContentAreaFilled(false);
        cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/cancela1.png"))); // NOI18N
        cancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        jPanel2.add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, -1, -1));

        vender.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        vender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/venta2.png"))); // NOI18N
        vender.setBorder(null);
        vender.setContentAreaFilled(false);
        vender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vender.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        vender.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/venta1.png"))); // NOI18N
        vender.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        vender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venderActionPerformed(evt);
            }
        });
        jPanel2.add(vender, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        total.setEditable(false);
        total.setBackground(new java.awt.Color(34, 102, 145));
        total.setBorder(null);
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        total.setOpaque(false);
        total.setPhColor(new java.awt.Color(255, 255, 255));
        jPanel2.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 68, 120, 30));

        codigoL3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/total2.png"))); // NOI18N
        jPanel2.add(codigoL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, -1, 50));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaCaja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "TIPO", "DESCRIPCIÓN", "PRECIO $", "CANTIDAD", "IMPORTE $"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tablaCaja);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 760, 270));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        numFac.setEditable(false);
        numFac.setBackground(new java.awt.Color(34, 102, 145));
        numFac.setBorder(null);
        numFac.setForeground(new java.awt.Color(255, 255, 255));
        numFac.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        numFac.setOpaque(false);
        numFac.setPhColor(new java.awt.Color(255, 255, 255));
        numFac.setPlaceholder("FACTURA");
        jPanel6.add(numFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 130, 40));

        codigoL4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/numFac.png"))); // NOI18N
        jPanel6.add(codigoL4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("NÚMERO DE VENTA");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, -1));

        ListaProductos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ListaProductos.setVisible(true);

        javax.swing.GroupLayout ListaProductosLayout = new javax.swing.GroupLayout(ListaProductos.getContentPane());
        ListaProductos.getContentPane().setLayout(ListaProductosLayout);
        ListaProductosLayout.setHorizontalGroup(
            ListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );
        ListaProductosLayout.setVerticalGroup(
            ListaProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 445, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fecha.setEditable(false);
        fecha.setBackground(new java.awt.Color(34, 102, 145));
        fecha.setBorder(null);
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fecha.setOpaque(false);
        fecha.setPhColor(new java.awt.Color(255, 255, 255));
        fecha.setPlaceholder("FECHA");
        fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaActionPerformed(evt);
            }
        });
        jPanel3.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 180, -1));

        codigoL5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/fecha.png"))); // NOI18N
        jPanel3.add(codigoL5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 52));

        barraDeTitulo.setBackground(new java.awt.Color(7, 37, 77));

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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CAJA DE COBRO");

        javax.swing.GroupLayout barraDeTituloLayout = new javax.swing.GroupLayout(barraDeTitulo);
        barraDeTitulo.setLayout(barraDeTituloLayout);
        barraDeTituloLayout.setHorizontalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraDeTituloLayout.createSequentialGroup()
                .addComponent(miminizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(miminizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(barraDeTituloLayout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE)))
        );
        barraDeTituloLayout.setVerticalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(miminizar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(miminizar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraDeTituloLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(barraDeTitulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ListaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(barraDeTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(ListaProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
      
    }//GEN-LAST:event_formKeyPressed

    private void miminizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miminizar1ActionPerformed

    private void miminizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizarActionPerformed
        this.dispose();
    }//GEN-LAST:event_miminizarActionPerformed

    private void fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaActionPerformed

    private void venderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venderActionPerformed
        vender();
    }//GEN-LAST:event_venderActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        limpiaCampos();
    }//GEN-LAST:event_cancelarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        if (tablaCaja.getRowCount() > 0) {
            DefaultTableModel modelo = (DefaultTableModel) tablaCaja.getModel();
            int fila = tablaCaja.getSelectedRow();
            if (fila >= 0) {
                modelo.removeRow(fila);
                total();
            } else {
                JOptionPane.showMessageDialog(tablaCaja, "Seleccione una fila.", "Caja de cobro", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));

            }
        } else {
            JOptionPane.showMessageDialog(tablaCaja, "No hay registros\npara eliminar.", "Caja de cobro", 0,
                new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            Locale locale = new Locale("pt", "BR");

        }
    }//GEN-LAST:event_borrarActionPerformed

    private void cambioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cambioKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            vender();
        }
    }//GEN-LAST:event_cambioKeyPressed

    private void recibiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recibiKeyTyped
        char num = evt.getKeyChar();

        if (num != '.' && (num < '0' || num > '9')) {
            evt.consume();
        }

        if (num == '.' && existes_punto(recibi.getText())) {
            evt.consume();
        }
    }//GEN-LAST:event_recibiKeyTyped

    private void recibiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recibiKeyReleased
        calcular();
    }//GEN-LAST:event_recibiKeyReleased

    private void recibiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_recibiKeyPressed
        System.out.println(" tecl: "+evt.getKeyCode());
        if (evt.getKeyCode() == evt.VK_ENTER) {
            if (JOptionPane.showConfirmDialog(tablaCaja, "¿Hacer venta?", "Caja", JOptionPane.YES_NO_OPTION, 0,
                new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
            vender();
        }
        }
    }//GEN-LAST:event_recibiKeyPressed

    private void recibiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recibiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recibiActionPerformed
    productos.ListaProductosAd la;

    void calcular() {
        if (recibi.getText().equals("")) {
            cambio.setText("");
        } else {
            double recibe = Double.parseDouble(recibi.getText());
            double tota = Double.parseDouble(total.getText());

            cambio.setText(String.format("%3.2f", (recibe - tota)).replace(".00", ""));

            //cambio.requestFocus(); 
        }

    }

    void vender() {

        if (tablaCaja.getRowCount() < 1) {
            JOptionPane.showMessageDialog(tablaCaja, "No hay producto a vender.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (recibi.getText().equals("")) {
            JOptionPane.showMessageDialog(tablaCaja, "Ingrese un valor a recibir", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            double recibe = Double.parseDouble(recibi.getText());
            double tota = Double.parseDouble(total.getText());
            if (recibe < tota) {
                JOptionPane.showMessageDialog(tablaCaja, "Cantidad recibido es menor al TOTAL", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                VentasCod vc = new VentasCod();
                vc.setPrimaryKey(numFac.getText());
                vc.setTotal(total.getText());
                vc.setFecha(fecha.getText());
                vc.setIds_poductos(optener_id_costo_venta_cantidad());
                int opcion = OpcionesVen.registrar(vc);
                if (opcion != 0) {
                    String nl = System.getProperty("line.separator");
                    JOptionPane.showMessageDialog(tablaCaja, "Venta realizada." + nl + " Cambio: $" + cambio.getText() + "", "Caja de cobro", 0,
                            new ImageIcon(getClass().getResource("/imagenes/caja/ralizada.png")));
                    if (principal.MenuPrincipalAd.cerra1) {
                        OpcionesVen.listar("");
                    }
                    limpiaCampos();
                }
            }
        }

    }

    String[][] optener_id_costo_venta_cantidad() {
        int cant = tablaCaja.getRowCount();
        String id_cantidad[][] = new String[cant][5];//[id][precio][costo][venta][cuantos productos a cender]

        for (int i = 0; i < cant; i++) {
            id_cantidad[i][0] = (String) tablaCaja.getValueAt(i, 0);//id
            id_cantidad[i][1] = (String) tablaCaja.getValueAt(i, 4);//precio
            id_cantidad[i][4] = (String) ""+tablaCaja.getValueAt(i, 4);//cantidad

            String SQL = "SELECT costo_compra, costo_venta FROM producto WHERE id_producto='" + id_cantidad[i][0] + "'";

            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQL);
                if (rs.next()) {
                    id_cantidad[i][2] = rs.getString("costo_compra");
                    id_cantidad[i][3] = rs.getString("costo_venta");
                }
            } catch (SQLException ex) {
                Logger.getLogger(OpcionesVen.class.getName()).log(Level.SEVERE, null, ex);
            }

            //System.out.println( (String) tablaCaja.getValueAt(i,0));
        }
        return id_cantidad;

    }

    boolean existes_punto(String numero) {

        for (int i = 0; i < numero.length(); i++) {
            if (numero.charAt(i) == '.') {
                return true;

            }

        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JInternalFrame ListaProductos;
    private javax.swing.JPanel barraDeTitulo;
    private javax.swing.JButton borrar;
    public static app.bolivia.swing.JCTextField cambio;
    private javax.swing.JButton cancelar;
    private javax.swing.JLabel codigoL3;
    private javax.swing.JLabel codigoL4;
    private javax.swing.JLabel codigoL5;
    private javax.swing.JLabel codigoL6;
    private javax.swing.JLabel codigoL7;
    private app.bolivia.swing.JCTextField fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton miminizar;
    private javax.swing.JButton miminizar1;
    public static app.bolivia.swing.JCTextField numFac;
    public static app.bolivia.swing.JCTextField recibi;
    public static javax.swing.JTable tablaCaja;
    public static app.bolivia.swing.JCTextField total;
    private javax.swing.JButton vender;
    // End of variables declaration//GEN-END:variables
}
