/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

import usuarios.*;
import com.sun.glass.events.KeyEvent;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Point;
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
import ventas.CajaAd;
import static ventas.CajaAd.cambio;
import static ventas.CajaAd.credito;
import static ventas.CajaAd.numFac;
import static ventas.CajaAd.tablaCaja;
import static ventas.CajaAd.total;
import ventas.OpcionesVen;
import ventas.VentasCod;

/**
 *
 * @author Rojeru San CL
 */
public class Cliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form Usuarios
     */
  public static  boolean esVenta=true;
    public Cliente() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        tablaCliente.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaCliente.setDefaultRenderer(Object.class, new principal.EstiloTablaRenderer());
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/usuarios/icono.png")));
        this.sexo.setCursor(new Cursor(12));          
        tablaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if(esVenta)jLabel1.setText(jLabel1.getText()+ "  $"+CajaAd.total.getText());
        limpiaCampos();        
        registrar.setMnemonic(KeyEvent.VK_G);
        actualizar.setMnemonic(KeyEvent.VK_M);

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

        
        
        tablaCliente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if (tablaCliente.getSelectedRow() != -1 && seleccion) {
                    cambiaDatos();
                    selecionRegistro = true;
                }
            }
        });
    }

    void cambiaDatos() {
        int fila = tablaCliente.getSelectedRow();
        codigo.setText(tablaCliente.getValueAt(fila, 0).toString());
        nombre.setText(tablaCliente.getValueAt(fila, 1).toString());
        sexo.setSelectedItem(tablaCliente.getValueAt(fila, 2).toString());
        telefono.setText(tablaCliente.getValueAt(fila, 3).toString());
        direccion.setText(tablaCliente.getValueAt(fila, 4).toString());
       if(esVenta) 
       credito.setVisible(true);
    }

    void limpiaCampos() {
        if (tablaCliente.getSelectedRow() > -1) {
            tablaCliente.removeRowSelectionInterval(tablaCliente.getSelectedRow(), tablaCliente.getSelectedRow());
        }
        credito.setVisible(false);
        codigo.setText("");
        nombre.setText("");
        sexo.setSelectedItem("SEXO");  
        telefono.setText("");
        direccion.setText("");
        buscar.setText("");
        selecionRegistro = false;
        OpcionesCli.listarCliente("",0);
        OpcionesCli.extraerID();
    }
boolean seleccion=true;// si es verdadero pone en los campos los valore de la tabla del dato seleccionado
    void selecionaFila(String id) {
        seleccion=false;
        for (int i = 0; i < tablaCliente.getRowCount(); i++) {
            if (id.equals(tablaCliente.getValueAt(i, 0))) {
                tablaCliente.setRowSelectionInterval(i, i);
                break;
            }
        }
seleccion=true;
    }
    boolean selecionRegistro = false;
void registrar(){
        if (selecionRegistro) {
            JOptionPane.showMessageDialog(this, "El CODIGO: " + this.codigo.getText() + "\nya existe en un registro.", "Cliente", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/impo.png")));
        } else {
           
            if (codigo.getText().equals("") || nombre.getText().equals("") || sexo.getSelectedItem().equals("SEXO")
                    || telefono.getText().equals("") || direccion.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Todos los campos\nson obligatorios.", "Cliente", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            } else {
                clientes.ClienteCod us = new ClienteCod();
                us.setPrimaryKey(codigo.getText());
                us.setNombre(nombre.getText());
                us.setSexo(sexo.getSelectedItem().toString());  
                us.setTelefono(telefono.getText());
                us.setDireccion(direccion.getText());
                us.setSaldo("0");
                int opcion = OpcionesCli.registrarCliente(us);
                if (opcion != 0) {
                    String id = codigo.getText();
                    //limpiaCampos();
                    //System.out.println(id);
                    
                    JOptionPane.showMessageDialog(this, "Registro éxitoso.", "Cliente", 0,
                            new ImageIcon(getClass().getResource("/imagenes/usuarios/registrado.png")));
                    credito.setVisible(true);
                    OpcionesCli.listarCliente("",0);
                    selecionaFila(id);
                    
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
public void registrar_venta(){
     VentasCod vc = new VentasCod();
                vc.setPrimaryKey(CajaAd.numFac.getText());
                vc.setTotal(total.getText());
                vc.setFecha(CajaAd.fecha.getText());
                vc.setIds_poductos(CajaAd.optener_id_costo_venta_cantidad());                
                OpcionesVen.registrar(vc);           
                credito.setVisible(false);
                if (principal.MenuPrincipalAd.cerra1) {
                OpcionesVen.listar("");
                }
                CajaAd.limpiaCampos();
                
            }

int posicion_Id(String id) {
       double saldo=0;
        for (int i = 0; i < tablaCliente.getRowCount(); i++) {
            if (id.equals(tablaCliente.getValueAt(i, 0))) {                
                return i;
            }
        }
return 0;
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
        sexo = new org.bolivia.combo.SComboBoxBlue();
        sexoL = new javax.swing.JLabel();
        telefono = new app.bolivia.swing.JCTextField();
        nombreL1 = new javax.swing.JLabel();
        direccion = new app.bolivia.swing.JCTextField();
        nombreL2 = new javax.swing.JLabel();
        credito = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        registrar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        eliminarT = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        buscar = new app.bolivia.swing.JCTextField();
        codigoL1 = new javax.swing.JLabel();
        barraDeTitulo = new javax.swing.JPanel();
        miminizar = new javax.swing.JButton();
        miminizar1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 37, 77), 4));
        setClosable(true);
        setIconifiable(true);
        setTitle("USUARIOS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        codigo.setEditable(false);
        codigo.setBackground(new java.awt.Color(34, 102, 145));
        codigo.setBorder(null);
        codigo.setForeground(new java.awt.Color(255, 255, 255));
        codigo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        codigo.setOpaque(false);
        codigo.setPhColor(new java.awt.Color(255, 255, 255));
        codigo.setPlaceholder("CÓDIGO");
        jPanel2.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 180, -1));

        codigoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/codigo.png"))); // NOI18N
        jPanel2.add(codigoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, 52));

        nombre.setBackground(new java.awt.Color(34, 102, 145));
        nombre.setBorder(null);
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nombre.setOpaque(false);
        nombre.setPhColor(new java.awt.Color(255, 255, 255));
        nombre.setPlaceholder("NOMBRE CLIENTE");
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 180, -1));

        nombreL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/nomUs.png"))); // NOI18N
        jPanel2.add(nombreL, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, 52));

        sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEXO", "MASCULINO", "FEMENINO" }));
        sexo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel2.add(sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 183, -1));

        sexoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/sexoL.png"))); // NOI18N
        jPanel2.add(sexoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, -1, 52));

        telefono.setBackground(new java.awt.Color(34, 102, 145));
        telefono.setBorder(null);
        telefono.setForeground(new java.awt.Color(255, 255, 255));
        telefono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        telefono.setOpaque(false);
        telefono.setPhColor(new java.awt.Color(255, 255, 255));
        telefono.setPlaceholder("TELEFONO");
        telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telefonoKeyTyped(evt);
            }
        });
        jPanel2.add(telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 180, -1));

        nombreL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/telefono.png"))); // NOI18N
        jPanel2.add(nombreL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 52));

        direccion.setBackground(new java.awt.Color(34, 102, 145));
        direccion.setBorder(null);
        direccion.setForeground(new java.awt.Color(255, 255, 255));
        direccion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        direccion.setOpaque(false);
        direccion.setPhColor(new java.awt.Color(255, 255, 255));
        direccion.setPlaceholder("DIRECCIÒN");
        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                direccionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                direccionKeyTyped(evt);
            }
        });
        jPanel2.add(direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 180, -1));

        nombreL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/direccion.png"))); // NOI18N
        jPanel2.add(nombreL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, -1, 52));

        credito.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        credito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/ok_Credito1.png"))); // NOI18N
        credito.setText("DAR CREDITO");
        credito.setBorder(null);
        credito.setBorderPainted(false);
        credito.setContentAreaFilled(false);
        credito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        credito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        credito.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/ok_Credito2.png"))); // NOI18N
        credito.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditoActionPerformed(evt);
            }
        });
        jPanel2.add(credito, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 60, -1, 100));

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOMBRE CLIENTE", "SEXO", "TELEFONO", "DIRECCIÒN", "SALDO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tablaCliente);

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
        jLabel1.setText("CREDITO A CLIENTE");
        jLabel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel1MouseDragged(evt);
            }
        });

        javax.swing.GroupLayout barraDeTituloLayout = new javax.swing.GroupLayout(barraDeTitulo);
        barraDeTitulo.setLayout(barraDeTituloLayout);
        barraDeTituloLayout.setHorizontalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraDeTituloLayout.createSequentialGroup()
                .addComponent(miminizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(miminizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        barraDeTituloLayout.setVerticalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(miminizar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(miminizar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraDeTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(barraDeTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
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
        if (tablaCliente.getRowCount() > 0) {
            if (tablaCliente.getSelectedRowCount() > 0) {
               
                if (codigo.getText().equals("") || nombre.getText().equals("") || sexo.getSelectedItem().equals("SEXO")
                        || telefono.getText().equals("") || direccion.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Es necesario completar\ntodos los campos.", "Cliente", 0,
                            new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                } else if (JOptionPane.showConfirmDialog(this, "Esta a punto de actualizar\nun registro.\n¿Desea continuar?", "Cliente", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                    clientes.ClienteCod us = new ClienteCod();
                    us.setPrimaryKey(codigo.getText());
                    us.setNombre(nombre.getText());
                    us.setSexo(sexo.getSelectedItem().toString());   
                    us.setTelefono(telefono.getText());
                    us.setDireccion(direccion.getText());
                  
                    int opcion = OpcionesCli.actualizarCliente(us);
                    if (opcion != 0) {
                        String id = codigo.getText();
                        limpiaCampos();
                        selecionaFila(id);
                        JOptionPane.showMessageDialog(this, "Registro actualizado.", "Cliente", 0,
                                new ImageIcon(getClass().getResource("/imagenes/usuarios/actualizado.png")));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Cliente", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara actualizar.", "Usuarios", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
       if (tablaCliente.getRowCount() > 0) {
            if (tablaCliente.getSelectedRowCount() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Esta a punto de eliminar\n un Cliente.\n¿Desea continuar?", "Cliente", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                    int fila = tablaCliente.getSelectedRow();
                    String id = tablaCliente.getValueAt(fila, 0).toString();
                    int elimina = OpcionesCli.eliminarCliente(id);
                    if (elimina != 0) {
                        limpiaCampos();
                        JOptionPane.showMessageDialog(this, "Cliente eliminado.", "Cliente", 0,
                                new ImageIcon(getClass().getResource("/imagenes/usuarios/borrado1.png")));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un Cliente.", "Cliente", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Cliente", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_eliminarActionPerformed

    productos.confirma_admin confirmar;
    private void eliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTActionPerformed
        if (tablaCliente.getRowCount() > 0) {
            if (JOptionPane.showConfirmDialog(this, "Esta a punto de elimnar\ntodos los Clientes.\n¿Desea continuar?", "Cliente", JOptionPane.YES_NO_OPTION, 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                productos.confirma_admin.opcion=3;
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
            JOptionPane.showMessageDialog(this, "No hay Clientes \n para eliminar.", "Cliente", 0,
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

    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased
        nombre.setText(nombre.getText().toUpperCase());
    }//GEN-LAST:event_nombreKeyReleased

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        buscar.setText(buscar.getText().toUpperCase());
        OpcionesCli.listarCliente(buscar.getText(),0);
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarMouseClicked
        limpiaCampos();
    }//GEN-LAST:event_buscarMouseClicked

    private void miminizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizarActionPerformed
        this.dispose();
    }//GEN-LAST:event_miminizarActionPerformed

    private void miminizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miminizar1ActionPerformed

    private void telefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoKeyReleased

    private void telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telefonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonoKeyTyped

    private void direccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionKeyReleased

    private void direccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_direccionKeyTyped

    private void creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditoActionPerformed
        String id_cliente = codigo.getText();
        String id_venta = CajaAd.numFac.getText();
        String credito=CajaAd.total.getText();
        double saldo=0;
        int pos=posicion_Id(id_cliente);
        String Cliente= tablaCliente.getValueAt(pos, 1).toString();
        String saldo_anterior=tablaCliente.getValueAt(pos, 5).toString();
        saldo=Double.parseDouble(credito)+Double.parseDouble(saldo_anterior);
        
        registrar_venta();
        clientes.ClienteCod us = new ClienteCod();        
        us.setPrimaryKey(id_cliente);
        us.setSaldo(""+saldo);
        us.setId_venta(id_venta);
        
        int opcion = OpcionesCli.actualizarSaldo(us, true);
                    if (opcion != 0) {
                       
                        limpiaCampos();
                        selecionaFila(id_cliente);                        
                        JOptionPane.showMessageDialog(this, "Saldo actualizado.\n\n"
                                + "Credito: "+credito
                                + "\nSaldo Anteriro: "+saldo_anterior
                                +"\nSaldo Actual: "+saldo, "Cliente:"+ Cliente, 0,
                                new  ImageIcon(getClass().getResource("/imagenes/usuarios/actualizado.png")));
                        
                        this.dispose();
                    }
    }//GEN-LAST:event_creditoActionPerformed

    private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseDragged
      Point punto=MouseInfo.getPointerInfo().getLocation();
    
    int x=punto.x-200;
    int y=punto.y-150;
    
    int max_punto_X=707; 
    int max_punto_Y=204; 
    
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
    }//GEN-LAST:event_jLabel1MouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JPanel barraDeTitulo;
    private app.bolivia.swing.JCTextField buscar;
    public static app.bolivia.swing.JCTextField codigo;
    private javax.swing.JLabel codigoL;
    private javax.swing.JLabel codigoL1;
    private javax.swing.JButton credito;
    private app.bolivia.swing.JCTextField direccion;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton eliminarT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton miminizar;
    private javax.swing.JButton miminizar1;
    private app.bolivia.swing.JCTextField nombre;
    private javax.swing.JLabel nombreL;
    private javax.swing.JLabel nombreL1;
    private javax.swing.JLabel nombreL2;
    private javax.swing.JButton registrar;
    private org.bolivia.combo.SComboBoxBlue sexo;
    private javax.swing.JLabel sexoL;
    public static javax.swing.JTable tablaCliente;
    private app.bolivia.swing.JCTextField telefono;
    // End of variables declaration//GEN-END:variables
}
