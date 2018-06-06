/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import com.sun.glass.events.KeyEvent;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static principal.MenuPrincipalAd.escritorio;
import productos.confirma_admin;

/**
 *
 * @author Rojeru San CL
 */
public class Usuarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form Usuarios
     */
  
    public Usuarios() {
        initComponents();
        tablaUsuarios.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaUsuarios.setDefaultRenderer(Object.class, new principal.EstiloTablaRenderer());
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/icono.png")));
        this.sexo.setCursor(new Cursor(12));
        this.tipoUs.setCursor(new Cursor(12));       
        tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        limpiaCampos();

        sexo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (sexo.getSelectedItem().equals("SEXO")) {
                    sexoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/sexoL.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/nomUs.png")));
                }
                if (sexo.getSelectedItem().equals("MASCULINO")) {
                    sexoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/masL.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/usMas.png")));
                }
                if (sexo.getSelectedItem().equals("FEMENINO")) {
                    sexoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/femL.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/usFem.png")));
                }
            }
        });

        tipoUs.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (tipoUs.getSelectedItem().equals("TIPO USUARIO")) {
                    tipoUsL.setIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/tipousL.png")));
                }
                if (tipoUs.getSelectedItem().equals("ADMINISTRADOR")) {
                    tipoUsL.setIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/administrador.png")));
                }
                if (tipoUs.getSelectedItem().equals("NORMAL")) {
                    tipoUsL.setIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/normal.png")));
                }
            }
        });
        
        tablaUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tablaUsuarios.getSelectedRow() != -1 && seleccion) {
                    cambiaDatos();
                    selecionRegistro = true;
                }
            }
        });
    }

    void cambiaDatos() {
        int fila = tablaUsuarios.getSelectedRow();
        codigo.setText(tablaUsuarios.getValueAt(fila, 0).toString());
        nombre.setText(tablaUsuarios.getValueAt(fila, 1).toString());
        sexo.setSelectedItem(tablaUsuarios.getValueAt(fila, 2).toString());
        tipoUs.setSelectedItem(tablaUsuarios.getValueAt(fila, 3).toString());
        pass.setText(tablaUsuarios.getValueAt(fila, 4).toString());
    }

    void limpiaCampos() {
        if (tablaUsuarios.getSelectedRow() > -1) {
            tablaUsuarios.removeRowSelectionInterval(tablaUsuarios.getSelectedRow(), tablaUsuarios.getSelectedRow());
        }
        codigo.setText("");
        nombre.setText("");
        sexo.setSelectedItem("SEXO");
        tipoUs.setSelectedItem("TIPO USUARIO");
        pass.setText("");
        buscar.setText("");
        selecionRegistro = false;
        OpcionesUs.listarUsuario("");
        OpcionesUs.extraerID();
    }
boolean seleccion=true;// si es verdadero pone en los campos los valore de la tabla del dato seleccionado
    void selecionaFila(String id) {
        seleccion=false;
        for (int i = 0; i < tablaUsuarios.getRowCount(); i++) {
            if (id.equals(tablaUsuarios.getValueAt(i, 0))) {
                tablaUsuarios.setRowSelectionInterval(i, i);
                break;
            }
        }
seleccion=true;
    }
    boolean selecionRegistro = false;
void registrar(){
        if (selecionRegistro) {
            JOptionPane.showMessageDialog(this, "El CODIGO: " + this.codigo.getText() + "\nya existe en un registro.", "Usuarios", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/impo.png")));
        } else {
            String pas = new String(pass.getPassword());
            if (codigo.getText().equals("") || nombre.getText().equals("") || sexo.getSelectedItem().equals("SEXO")
                    || tipoUs.getSelectedItem().equals("TIPO USUARIO") || pas.equals("")) {
                JOptionPane.showMessageDialog(this, "Todos los campos\nson obligatorios.", "Usuarios", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            } else {
                usuarios.UsuariosCod us = new UsuariosCod();
                us.setPrimaryKey(codigo.getText());
                us.setNombre(nombre.getText());
                us.setSexo(sexo.getSelectedItem().toString());
                us.setTipouser(tipoUs.getSelectedItem().toString());
                us.setContraseña(pas);
                int opcion = OpcionesUs.registrarUsuario(us);
                if (opcion != 0) {
                    String id = codigo.getText();
                    limpiaCampos();
                    System.out.println(id);
                    selecionaFila(id);
                    JOptionPane.showMessageDialog(this, "Registro éxitoso.", "Usuarios", 0,
                            new ImageIcon(getClass().getResource("/imagenes/usuarios/registrado.png")));
                }
            }
        }
}

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
        codigo = new app.bolivia.swing.JCTextField();
        codigoL = new javax.swing.JLabel();
        nombre = new app.bolivia.swing.JCTextField();
        nombreL = new javax.swing.JLabel();
        tipoUs = new org.bolivia.combo.SComboBoxBlue();
        pass = new jpass.JRPasswordField();
        passL = new javax.swing.JLabel();
        sexo = new org.bolivia.combo.SComboBoxBlue();
        sexoL = new javax.swing.JLabel();
        tipoUsL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        registrar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        eliminarT = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        buscar = new app.bolivia.swing.JCTextField();
        codigoL1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("USUARIOS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "REGISTRO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        codigo.setEditable(false);
        codigo.setBackground(new java.awt.Color(34, 102, 145));
        codigo.setBorder(null);
        codigo.setForeground(new java.awt.Color(255, 255, 255));
        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        codigo.setOpaque(false);
        codigo.setPhColor(new java.awt.Color(255, 255, 255));
        codigo.setPlaceholder("CÓDIGO");
        jPanel2.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 180, -1));

        codigoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/codigo.png"))); // NOI18N
        jPanel2.add(codigoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 52));

        nombre.setBackground(new java.awt.Color(34, 102, 145));
        nombre.setBorder(null);
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nombre.setOpaque(false);
        nombre.setPhColor(new java.awt.Color(255, 255, 255));
        nombre.setPlaceholder("NOMBRE USUARIO");
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 180, -1));

        nombreL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/nomUs.png"))); // NOI18N
        jPanel2.add(nombreL, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, 52));

        tipoUs.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TIPO USUARIO", "ADMINISTRADOR", "NORMAL" }));
        tipoUs.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tipoUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoUsActionPerformed(evt);
            }
        });
        jPanel2.add(tipoUs, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 183, -1));

        pass.setBackground(new java.awt.Color(34, 102, 145));
        pass.setBorder(null);
        pass.setForeground(new java.awt.Color(255, 255, 255));
        pass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pass.setOpaque(false);
        pass.setPhColor(new java.awt.Color(255, 255, 255));
        pass.setPlaceholder("CONTRSEÑA");
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passKeyTyped(evt);
            }
        });
        jPanel2.add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 180, -1));

        passL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/contraseña.png"))); // NOI18N
        jPanel2.add(passL, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, 52));

        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEXO", "MASCULINO", "FEMENINO" }));
        sexo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 183, -1));

        sexoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/sexoL.png"))); // NOI18N
        jPanel2.add(sexoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, -1, 52));

        tipoUsL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/tipousL.png"))); // NOI18N
        jPanel2.add(tipoUsL, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, 52));

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOMBRE USUARIO", "SEXO", "TIPO USUARIO", "CONTRASEÑA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tablaUsuarios);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        registrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/regis1.png"))); // NOI18N
        registrar.setText("Registrar");
        registrar.setBorder(null);
        registrar.setBorderPainted(false);
        registrar.setContentAreaFilled(false);
        registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/regis2.png"))); // NOI18N
        registrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        actualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/actualizar.png"))); // NOI18N
        actualizar.setText("Actualizar");
        actualizar.setBorder(null);
        actualizar.setBorderPainted(false);
        actualizar.setContentAreaFilled(false);
        actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actualizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/actualizar1.png"))); // NOI18N
        actualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarActionPerformed(evt);
            }
        });

        eliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrar1.png"))); // NOI18N
        eliminar.setText("Eliminar");
        eliminar.setBorder(null);
        eliminar.setBorderPainted(false);
        eliminar.setContentAreaFilled(false);
        eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrar2.png"))); // NOI18N
        eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        eliminarT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        eliminarT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrarT1.png"))); // NOI18N
        eliminarT.setText("Eliminar Todo");
        eliminarT.setBorder(null);
        eliminarT.setBorderPainted(false);
        eliminarT.setContentAreaFilled(false);
        eliminarT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminarT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminarT.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/borrarT2.png"))); // NOI18N
        eliminarT.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarTActionPerformed(evt);
            }
        });

        limpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/limpiar1.png"))); // NOI18N
        limpiar.setText("Limpiar Campos");
        limpiar.setBorder(null);
        limpiar.setBorderPainted(false);
        limpiar.setContentAreaFilled(false);
        limpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        limpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        limpiar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/limpiar2.png"))); // NOI18N
        limpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(actualizar)
                .addGap(10, 10, 10)
                .addComponent(eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eliminarT)
                .addGap(18, 18, 18)
                .addComponent(limpiar)
                .addGap(4, 4, 4))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {actualizar, eliminar, eliminarT, limpiar});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registrar)
            .addComponent(actualizar)
            .addComponent(eliminar)
            .addComponent(eliminarT)
            .addComponent(limpiar)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "BUSQUEDA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buscar.setBackground(new java.awt.Color(34, 102, 145));
        buscar.setBorder(null);
        buscar.setForeground(new java.awt.Color(255, 255, 255));
        buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar.setOpaque(false);
        buscar.setPhColor(new java.awt.Color(255, 255, 255));
        buscar.setPlaceholder("CÓDIGO/NOMBRE");
        buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarMouseClicked(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });
        jPanel4.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 180, -1));

        codigoL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/buscarL.png"))); // NOI18N
        jPanel4.add(codigoL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 250, 52));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
    registrar();
    }//GEN-LAST:event_registrarActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiaCampos();
    }//GEN-LAST:event_limpiarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        if (tablaUsuarios.getRowCount() > 0) {
            if (tablaUsuarios.getSelectedRowCount() > 0) {
                String pas = new String(pass.getPassword());
                if (codigo.getText().equals("") || nombre.getText().equals("") || sexo.getSelectedItem().equals("SEXO")
                        || tipoUs.getSelectedItem().equals("TIPO USUARIO") || pas.equals("")) {
                    JOptionPane.showMessageDialog(this, "Es necesario completar\ntodos los campos.", "Usuarios", 0,
                            new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                } else if (JOptionPane.showConfirmDialog(this, "Esta a punto de actualizar\nun registro.\n¿Desea continuar?", "Usuarios", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                    usuarios.UsuariosCod us = new UsuariosCod();
                    us.setPrimaryKey(codigo.getText());
                    us.setNombre(nombre.getText());
                    us.setSexo(sexo.getSelectedItem().toString());
                    us.setTipouser(tipoUs.getSelectedItem().toString());
                    us.setContraseña(pas);
                    int opcion = OpcionesUs.actualizarUsuario(us);
                    if (opcion != 0) {
                        String id = codigo.getText();
                        limpiaCampos();
                        selecionaFila(id);
                        JOptionPane.showMessageDialog(this, "Registro actualizado.", "Usuarios", 0,
                                new ImageIcon(getClass().getResource("/imagenes/usuarios/actualizado.png")));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Usuarios", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara actualizar.", "Usuarios", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
       if (tablaUsuarios.getRowCount() > 0) {
            if (tablaUsuarios.getSelectedRowCount() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Esta a punto de eliminar\nun registro.\n¿Desea continuar?", "Usuarios", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                    int fila = tablaUsuarios.getSelectedRow();
                    String id = tablaUsuarios.getValueAt(fila, 0).toString();
                    int elimina = OpcionesUs.eliminarUsuario(id);
                    if (elimina != 0) {
                        limpiaCampos();
                        JOptionPane.showMessageDialog(this, "Registro eliminado.", "Usuarios", 0,
                                new ImageIcon(getClass().getResource("/imagenes/usuarios/borrado1.png")));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Usuarios", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Usuarios", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_eliminarActionPerformed

    productos.confirma_admin confirmar;
    private void eliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTActionPerformed
        if (tablaUsuarios.getRowCount() > 0) {
            if (JOptionPane.showConfirmDialog(this, "Esta a punto de elimnar\ntodos los registros.\n¿Desea continuar?", "Usuarios", JOptionPane.YES_NO_OPTION, 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                productos.confirma_admin.opcion=0;
                if (estacerrado(confirmar)) {
                    confirmar = new confirma_admin();
                    principal.MenuPrincipalAd.escritorio.add(confirmar);
                    confirmar.toFront();
                    confirmar.setVisible(true);
                }else{        
                    confirmar.toFront();
                    confirmar.setVisible(true);        
              }   
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Usuarios", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_eliminarTActionPerformed

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
        char letras = evt.getKeyChar();
        if ((letras < 'a' || letras > 'z') && (letras < 'A' | letras > 'Z')) {
            if ((letras != 'ñ') && (letras != 'Ñ') && (letras != 'á') && (letras != 'Á') && (letras != 'é') && (letras != 'É') && (letras != 'í')
                    && (letras != 'Í') && (letras != 'ó') && (letras != 'Ó') && (letras != 'ú') && (letras != 'Ú') && (letras != ' ')) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_nombreKeyTyped

    private void passKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyTyped
        char num = evt.getKeyChar();
        char letras = evt.getKeyChar();
        if ((num < '0' || num > '9') && ((letras < 'a' || letras > 'z') && (letras < 'A' | letras > 'Z'))) {
            if ((letras != 'ñ') && (letras != 'Ñ') && (letras != '.') && (letras != ',')) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_passKeyTyped

    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased
        nombre.setText(nombre.getText().toUpperCase());
    }//GEN-LAST:event_nombreKeyReleased

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        buscar.setText(buscar.getText().toUpperCase());
        OpcionesUs.listarUsuario(buscar.getText());
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarMouseClicked
        limpiaCampos();
    }//GEN-LAST:event_buscarMouseClicked

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
 if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            registrar();            
        }          // TODO add your handling code here:
    }//GEN-LAST:event_passKeyPressed

    private void tipoUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoUsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoUsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private app.bolivia.swing.JCTextField buscar;
    public static app.bolivia.swing.JCTextField codigo;
    private javax.swing.JLabel codigoL;
    private javax.swing.JLabel codigoL1;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton eliminarT;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiar;
    private app.bolivia.swing.JCTextField nombre;
    private javax.swing.JLabel nombreL;
    private jpass.JRPasswordField pass;
    private javax.swing.JLabel passL;
    private javax.swing.JButton registrar;
    private org.bolivia.combo.SComboBoxBlue sexo;
    private javax.swing.JLabel sexoL;
    public static javax.swing.JTable tablaUsuarios;
    private org.bolivia.combo.SComboBoxBlue tipoUs;
    private javax.swing.JLabel tipoUsL;
    // End of variables declaration//GEN-END:variables
}
