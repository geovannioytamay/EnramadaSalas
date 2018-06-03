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
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static principal.MenuPrincipalAd.escritorio;
import usuarios.OpcionesUs;
import static usuarios.Usuarios.codigo;
import usuarios.UsuariosCod;

/**
 *
 * @author Rojeru San CL
 */
public class Productos extends javax.swing.JInternalFrame {

    /**
     * Creates new form Usuarios
     */
    public Productos() {
        initComponents();
        OpcionesAl.llenar_combo();
        tablaAlimentos.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaAlimentos.setDefaultRenderer(Object.class, new principal.EstiloTablaRenderer());
        this.tipoAl1.setCursor(new Cursor(12));       
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/icono.png")));
        limpiaCampos();
        
        tipoAl1.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (tipoAl1.getSelectedIndex() == 0) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/tipoAlL.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/nombreL.png")));
                }
                if (tipoAl1.getSelectedIndex() == 1) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/bebida.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/bebida1.png")));
                }
                
                if (tipoAl1.getSelectedIndex() == 2) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/botana.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/botana1.png")));
                }
                if (tipoAl1.getSelectedIndex() == 3) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/caldo.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/caldo1.png")));
                }
                if (tipoAl1.getSelectedIndex() == 4) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/camaron.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/camaron1.png")));
                }
                if (tipoAl1.getSelectedIndex() == 5) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/coctel.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/coctel1.png")));
                }
                if (tipoAl1.getSelectedIndex() == 6) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/desayuno.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/desayuno1.png")));
                }
                if (tipoAl1.getSelectedIndex() == 7) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/filete.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/filete1.png")));
                }
                if (tipoAl1.getSelectedIndex() == 8) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/langosta.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/langosta1.png")));
                }
                if (tipoAl1.getSelectedIndex() == 9) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/langostino.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/langostino1.png")));
                }
                if (tipoAl1.getSelectedIndex() == 10) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/pescado.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/pescado1.png")));
                }
                if (tipoAl1.getSelectedIndex() == 11) {
                    tipoL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/pulpo.png")));
                    nombreL.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/pulpo1.png")));
                }
            }
        });
        

        tablaAlimentos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
               
                if (tablaAlimentos.getSelectedRow() != -1) {
                    cambiaDatos();
                    selecionRegistro = true;
                }
            }
        });
    }

    void cambiaDatos() {
        int fila = tablaAlimentos.getSelectedRow();
        codigo.setText(tablaAlimentos.getValueAt(fila, 0).toString());        
        nombre.setText(tablaAlimentos.getValueAt(fila, 1).toString());        
        costo_compra.setText(tablaAlimentos.getValueAt(fila, 2).toString());
        costo_venta.setText(tablaAlimentos.getValueAt(fila, 3).toString());
        cantidad.setText(tablaAlimentos.getValueAt(fila, 4).toString());
        tipoAl1.setSelectedItem(tablaAlimentos.getValueAt(fila, 5).toString());
    }
    boolean selecionRegistro = false;

    void limpiaCampos() {
        if (tablaAlimentos.getSelectedRow() > -1) {
            tablaAlimentos.removeRowSelectionInterval(tablaAlimentos.getSelectedRow(), tablaAlimentos.getSelectedRow());
        }
        codigo.setText("");
        nombre.setText("");
        tipoAl1.setSelectedItem("TIPO DE PRODUCTO");
        costo_compra.setText("");
        costo_venta.setText("");
        cantidad.setText("");        
        buscar.setText("");
        
        selecionRegistro = false;
        OpcionesAl.listar("",0);
        OpcionesAl.extraerID();
    }

    void selecionaFila(String id) {
        for (int i = 0; i < tablaAlimentos.getRowCount(); i++) {
            if (id.equals(tablaAlimentos.getValueAt(i, 0))) {
                tablaAlimentos.setRowSelectionInterval(i, i);
                break;
            }
        }

    }
    
    boolean existes_punto(String numero){
   
     for(int i=0; i<numero.length();i++) {
                  if(numero.charAt(i)=='.'){
                      return true;
                 
                  }
                  
     
 }
     return false;
 }
    
    void Registrar(){
        if (selecionRegistro) {
            JOptionPane.showMessageDialog(this, "El CODIGO: " + this.codigo.getText() + "\nya existe en un registro.", "Almacen", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/impo.png")));
        } else if (codigo.getText().equals("") || nombre.getText().equals("") || tipoAl1.getSelectedItem().equals("TIPO DE PRODUCTO")
                || costo_compra.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Todos los campos\nson obligatorios.", "Almacen", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        } else {
            productos.ProductosCod us = new ProductosCod();
            us.setPrimaryKey(codigo.getText());
            us.setNombre(nombre.getText());
            us.setCosto_compra(costo_compra.getText());
            us.setCosto_venta(costo_venta.getText());
            us.setCantidad(cantidad.getText());
            us.setTipo(tipoAl1.getSelectedItem().toString());
            int opcion = OpcionesAl.registrar(us);
            if (opcion != 0) {
                String id = codigo.getText();
                limpiaCampos();
                selecionaFila(id);
                JOptionPane.showMessageDialog(this, "Registro éxitoso.", "Alimentos", 0,
                        new ImageIcon(getClass().getResource("/imagenes/Productos/registrado.png")));
                limpiaCampos();
            }
        }
    }
    
    void abrir_ListaTipo(int opcion){
        ListaTipo.opcion =opcion;       
        
        if (estacerrado(la)) {
            la = new ListaTipo();
            principal.MenuPrincipalAd.escritorio.add(la);
            la.toFront();
            la.setVisible(true);
        }else{        
            JOptionPane.showMessageDialog(this, "La ventana esta abierta !!!", "Aviso", 0,
                    new ImageIcon(getClass().getResource("/imagenes/principal/adver.png")));
           
        }    
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
        costo_compra = new app.bolivia.swing.JCTextField();
        nombreL1 = new javax.swing.JLabel();
        costo_venta = new app.bolivia.swing.JCTextField();
        nombreL3 = new javax.swing.JLabel();
        cantidad = new app.bolivia.swing.JCTextField();
        nombreL4 = new javax.swing.JLabel();
        tipoAl1 = new org.bolivia.combo.SComboBoxBlue();
        tipoL = new javax.swing.JLabel();
        agregar_tipo = new javax.swing.JButton();
        actualizar_tipo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlimentos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        registrar = new javax.swing.JButton();
        actualizar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        eliminarT = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        buscar = new app.bolivia.swing.JCTextField();
        codigoL1 = new javax.swing.JLabel();
        opcion_busqueda = new org.bolivia.combo.SComboBoxBlue();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("ALMACEN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "REGISTRO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        codigo.setBackground(new java.awt.Color(34, 102, 145));
        codigo.setBorder(null);
        codigo.setForeground(new java.awt.Color(255, 255, 255));
        codigo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        codigo.setOpaque(false);
        codigo.setPhColor(new java.awt.Color(255, 255, 255));
        codigo.setPlaceholder("CÓDIGO");
        codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoActionPerformed(evt);
            }
        });
        codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoKeyPressed(evt);
            }
        });
        jPanel2.add(codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 180, -1));

        codigoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/codigoL.png"))); // NOI18N
        jPanel2.add(codigoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, 52));

        nombre.setBackground(new java.awt.Color(34, 102, 145));
        nombre.setBorder(null);
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nombre.setOpaque(false);
        nombre.setPhColor(new java.awt.Color(255, 255, 255));
        nombre.setPlaceholder("NOMBRE DEL PRODUCTO");
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        jPanel2.add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 180, -1));

        nombreL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/nombreL.png"))); // NOI18N
        jPanel2.add(nombreL, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, -1, 52));

        costo_compra.setBackground(new java.awt.Color(34, 102, 145));
        costo_compra.setBorder(null);
        costo_compra.setForeground(new java.awt.Color(255, 255, 255));
        costo_compra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        costo_compra.setOpaque(false);
        costo_compra.setPhColor(new java.awt.Color(255, 255, 255));
        costo_compra.setPlaceholder("COSTO COMPRA");
        costo_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costo_compraActionPerformed(evt);
            }
        });
        costo_compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                costo_compraKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                costo_compraKeyTyped(evt);
            }
        });
        jPanel2.add(costo_compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 180, -1));

        nombreL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/precioL.png"))); // NOI18N
        jPanel2.add(nombreL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, 52));

        costo_venta.setBackground(new java.awt.Color(34, 102, 145));
        costo_venta.setBorder(null);
        costo_venta.setForeground(new java.awt.Color(255, 255, 255));
        costo_venta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        costo_venta.setOpaque(false);
        costo_venta.setPhColor(new java.awt.Color(255, 255, 255));
        costo_venta.setPlaceholder("COSTO VENTA");
        costo_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costo_ventaActionPerformed(evt);
            }
        });
        costo_venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                costo_ventaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                costo_ventaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                costo_ventaKeyTyped(evt);
            }
        });
        jPanel2.add(costo_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 180, -1));

        nombreL3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/nombreL.png"))); // NOI18N
        jPanel2.add(nombreL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, -1, 52));

        cantidad.setBackground(new java.awt.Color(34, 102, 145));
        cantidad.setBorder(null);
        cantidad.setForeground(new java.awt.Color(255, 255, 255));
        cantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cantidad.setOpaque(false);
        cantidad.setPhColor(new java.awt.Color(255, 255, 255));
        cantidad.setPlaceholder("CANTIDAD");
        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadKeyTyped(evt);
            }
        });
        jPanel2.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 180, -1));

        nombreL4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/nombreL.png"))); // NOI18N
        jPanel2.add(nombreL4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, -1, 52));

        tipoAl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TIPO DE PRODUCTO" }));
        tipoAl1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tipoAl1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoAl1ActionPerformed(evt);
            }
        });
        jPanel2.add(tipoAl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 183, -1));

        tipoL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/tipoAlL.png"))); // NOI18N
        jPanel2.add(tipoL, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 30, -1, 52));

        agregar_tipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        agregar_tipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/mas1.png"))); // NOI18N
        agregar_tipo.setBorder(null);
        agregar_tipo.setBorderPainted(false);
        agregar_tipo.setContentAreaFilled(false);
        agregar_tipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        agregar_tipo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregar_tipo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/mas2.png"))); // NOI18N
        agregar_tipo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregar_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_tipoActionPerformed(evt);
            }
        });
        jPanel2.add(agregar_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 30, -1, -1));

        actualizar_tipo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        actualizar_tipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/actualizar1.png"))); // NOI18N
        actualizar_tipo.setBorder(null);
        actualizar_tipo.setBorderPainted(false);
        actualizar_tipo.setContentAreaFilled(false);
        actualizar_tipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        actualizar_tipo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actualizar_tipo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/actualizar2.png"))); // NOI18N
        actualizar_tipo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actualizar_tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizar_tipoActionPerformed(evt);
            }
        });
        jPanel2.add(actualizar_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(759, 30, 60, -1));

        tablaAlimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOMBRE", "COSTO", "VENTA", "CANTIDAD", "TIPO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaAlimentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tablaAlimentos);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        registrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        registrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/regis1.png"))); // NOI18N
        registrar.setText("Registrar");
        registrar.setBorder(null);
        registrar.setBorderPainted(false);
        registrar.setContentAreaFilled(false);
        registrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        registrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/regis2.png"))); // NOI18N
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
        buscar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscar.setOpaque(false);
        buscar.setPhColor(new java.awt.Color(255, 255, 255));
        buscar.setPlaceholder("CÓDIGO/NOMBRE");
        buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarMouseClicked(evt);
            }
        });
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
        });
        jPanel4.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, -1));

        codigoL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/buscarL.png"))); // NOI18N
        jPanel4.add(codigoL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 250, 52));

        opcion_busqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CÓDIGO/NOMBRE", "BUSCAR MENOR A", "BUSCAR POR TIPO" }));
        opcion_busqueda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        opcion_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcion_busquedaActionPerformed(evt);
            }
        });
        jPanel4.add(opcion_busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 240, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        Registrar();
    }//GEN-LAST:event_registrarActionPerformed

    private void actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarActionPerformed
        if (tablaAlimentos.getRowCount() > 0) {
            if (tablaAlimentos.getSelectedRowCount() > 0) {
                if (codigo.getText().equals("") || nombre.getText().equals("") || tipoAl1.getSelectedItem().equals("TIPO DE PRODUCTO")
                        || costo_compra.getText().equals("")|| costo_venta.getText().equals("")|| cantidad.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Es necesario completar\ntodos los campos.", "Alimentos", 0,
                            new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
                } else if (JOptionPane.showConfirmDialog(this, "Esta a punto de actualizar\nun registro.\n¿Desea continuar?", "Almacen", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                    productos.ProductosCod us = new ProductosCod();
                    us.setPrimaryKey(codigo.getText());
                    us.setNombre(nombre.getText());                   
                    us.setCosto_compra(costo_compra.getText());
                    us.setCosto_venta(costo_venta.getText());
                    us.setCantidad(cantidad.getText());
                     us.setTipo(tipoAl1.getSelectedItem().toString());
                    int opcion = OpcionesAl.actualizar(us);
                    if (opcion != 0) {
                        String id = codigo.getText();
                        limpiaCampos();
                        selecionaFila(id);
                        JOptionPane.showMessageDialog(this, "Registro actualizado.", "Alimentos", 0,
                                new ImageIcon(getClass().getResource("/imagenes/Productos/actualizado.png")));
                        
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Alimentos", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara actualizar.", "Alimentos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_actualizarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (tablaAlimentos.getRowCount() > 0) {
            if (tablaAlimentos.getSelectedRowCount() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Esta a punto de eliminar\nun registro.\n¿Desea continuar?", "Alimentos", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                    int fila = tablaAlimentos.getSelectedRow();
                    String id = tablaAlimentos.getValueAt(fila, 0).toString();
                    int elimina = OpcionesAl.eliminar(id);
                    if (elimina != 0) {
                        limpiaCampos();
                        JOptionPane.showMessageDialog(this, "Registro eliminado.", "Alimentos", 0,
                                new ImageIcon(getClass().getResource("/imagenes/Productos/borrado1.png")));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Alimentos", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Alimentos", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void eliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTActionPerformed
        if (tablaAlimentos.getRowCount() > 0) {
            if (JOptionPane.showConfirmDialog(this, "Esta a punto de elimnar\ntodos los registros.\n¿Desea continuar?", "Alimentos", JOptionPane.YES_NO_OPTION, 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                int eliminaT = OpcionesAl.eliminaTodos();
                if (eliminaT != 0) {
                    limpiaCampos();
                    JOptionPane.showMessageDialog(this, "Registros eliminados.", "Alimentos", 0,
                            new ImageIcon(getClass().getResource("/imagenes/Productos/borrado.png")));
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Alimentos", 0,
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

    private void costo_compraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costo_compraKeyTyped
        char num = evt.getKeyChar();
                
        if (num!='.' && (num < '0' || num > '9')) {
            evt.consume();
        }
       
     
        if(num=='.'  && existes_punto(costo_compra.getText())){
            evt.consume();
        }
      
    }//GEN-LAST:event_costo_compraKeyTyped
   
  
    
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
    private void nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyReleased
        nombre.setText(nombre.getText().toUpperCase());        
    }//GEN-LAST:event_nombreKeyReleased

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        buscar.setText(buscar.getText().toUpperCase());
        int opcion =opcion_busqueda.getSelectedIndex();
        OpcionesAl.listar(buscar.getText(), opcion);
    }//GEN-LAST:event_buscarKeyReleased

    private void buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarMouseClicked
        limpiaCampos();
    }//GEN-LAST:event_buscarMouseClicked

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiaCampos();
    }//GEN-LAST:event_limpiarActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            costo_compra.requestFocus();
        }
    }//GEN-LAST:event_nombreKeyPressed

    private void costo_compraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costo_compraKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            costo_venta.requestFocus();
            
        }
      
       //System.out.println(evt.getKeyCode());
    }//GEN-LAST:event_costo_compraKeyPressed

    private void costo_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costo_compraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costo_compraActionPerformed

    private void costo_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costo_ventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_costo_ventaActionPerformed

    private void costo_ventaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costo_ventaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            cantidad.requestFocus();
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_costo_ventaKeyPressed

    private void costo_ventaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costo_ventaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_costo_ventaKeyReleased

    private void costo_ventaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_costo_ventaKeyTyped
        char num = evt.getKeyChar();
                
        if (num!='.' && (num < '0' || num > '9')) {
            evt.consume();
        }
       
     
        if(num=='.'  && existes_punto(costo_venta.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_costo_ventaKeyTyped

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void cantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
         Registrar();            
        }
    }//GEN-LAST:event_cantidadKeyPressed

    private void cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadKeyReleased

    private void cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyTyped
       char num = evt.getKeyChar();
                
        if ((num < '0' || num > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_cantidadKeyTyped

    private void codigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            nombre.requestFocus();
            
        }        // TODO add your handling code here:
    }//GEN-LAST:event_codigoKeyPressed

    private void opcion_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcion_busquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcion_busquedaActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigoActionPerformed
productos.ListaTipo la;
    private void agregar_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_tipoActionPerformed
    abrir_ListaTipo(0);
        
    }//GEN-LAST:event_agregar_tipoActionPerformed

    private void tipoAl1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAl1ActionPerformed
  
        
        
    }//GEN-LAST:event_tipoAl1ActionPerformed

    private void actualizar_tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizar_tipoActionPerformed
        abrir_ListaTipo(1);
    }//GEN-LAST:event_actualizar_tipoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizar;
    private javax.swing.JButton actualizar_tipo;
    private javax.swing.JButton agregar_tipo;
    private app.bolivia.swing.JCTextField buscar;
    private app.bolivia.swing.JCTextField cantidad;
    public static app.bolivia.swing.JCTextField codigo;
    private javax.swing.JLabel codigoL;
    private javax.swing.JLabel codigoL1;
    private app.bolivia.swing.JCTextField costo_compra;
    private app.bolivia.swing.JCTextField costo_venta;
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
    private javax.swing.JLabel nombreL1;
    private javax.swing.JLabel nombreL3;
    private javax.swing.JLabel nombreL4;
    private org.bolivia.combo.SComboBoxBlue opcion_busqueda;
    private javax.swing.JButton registrar;
    public static javax.swing.JTable tablaAlimentos;
    public static org.bolivia.combo.SComboBoxBlue tipoAl1;
    private javax.swing.JLabel tipoL;
    // End of variables declaration//GEN-END:variables
}
