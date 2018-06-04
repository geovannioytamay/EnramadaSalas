/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import static principal.MenuPrincipalAd.escritorio;
import productos.OpcionesAl;
import productos.confirma_admin;

/**
 *
 * @author Rojeru San CL
 */
public class RegistroVentas extends javax.swing.JInternalFrame {

    /**
     * Creates new form Usuarios
     */
    public RegistroVentas() {
        initComponents();
        tablaVentas.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaVentas.setDefaultRenderer(Object.class, new principal.EstiloTablaRenderer());
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/caja/icono1.png")));
        tablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        limpiaCampos();
        jLabel2.setText("");
        this.setLocation(500, 3);
    }

    void limpiaCampos() {
        if (tablaVentas.getSelectedRow() > -1) {
            tablaVentas.removeRowSelectionInterval(tablaVentas.getSelectedRow(), tablaVentas.getSelectedRow());
        }
        fecha.setDate(null);
        buscar.setText("");
        OpcionesVen.listar("");
    }

   void grafica_ultima_semana(){//otiene 7 dias anstes desde hoy
     DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
     
     Date sistemaFech = new Date();
     SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
     
        String fecha_inicio="";
        String fecha_fin=formato.format(sistemaFech);
        try{   
         Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_fin);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(inputDate);    
         
         calendar.add(Calendar.DAY_OF_YEAR,-8);
         
         //fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
         double total=0;
            do { 
            
                  fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
                  //System.out.println(fecha_inicio); // Devuelve el objeto Date con los nuevos días añadidos
                 
                  //System.out.println(""+calendar.get(Calendar.DAY_OF_WEEK)); //     calcula el dia de la semana   
                  
                  total=OpcionesVen.optener_ventas(fecha_inicio);
                 
                  if(total>0){
                      dtsc.addValue(total,Dia_semana(calendar.get(Calendar.DAY_OF_WEEK))+" "+ fecha_inicio,  " ($"+total+")");                  
                  }
                   calendar.add(Calendar.DAY_OF_YEAR,1);
   }while(fecha_inicio.compareTo(fecha_fin)!=0);
            
  }catch(Exception e){
  System.out.printf(""+e);
  
   }
        grafica(dtsc,"Ultimos 7 dìas", "Dìa");
    }
   
   void graficar_por_dia(){
     
     DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
    
     
        String formato = fechaInicio.getDateFormatString();
        Date date =  fechaInicio.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        
        String formato2 = fechaFin.getDateFormatString();
        Date date2 =  fechaFin.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
        
         String fecha_inicio=String.valueOf(sdf.format(date));
        String fecha_fin=String.valueOf(sdf2.format(date2));
        
        try{   
         Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_inicio);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(inputDate);    
         
        
         
         //fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
         double total=0;
            do { 
            
           
                  
                  fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
                  //System.out.println(fecha_inicio); // Devuelve el objeto Date con los nuevos días añadidos
                 
                  //System.out.println("GRAFICAR"+calendar.get(Calendar.DAY_OF_WEEK)); //     calcula el dia de la semana   
                  
                  
                  
                  total=OpcionesVen.optener_ventas(fecha_inicio);
                  if(total>0 ){
                      total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));
                      dtsc.addValue(total,""+fecha_inicio,  " ($"+total+")");                  
                  }
                   calendar.add(Calendar.DAY_OF_YEAR,1);
   }while(fecha_inicio.compareTo(fecha_fin)!=0);
            
  }catch(Exception e){
  System.out.printf(""+e);
  
   }
        grafica(dtsc,"Grafica por dìa","Dìa");
   }
   
    void graficar_por_semana(){
     
     DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
    
     
        String formato = fechaInicio.getDateFormatString();
        Date date =  fechaInicio.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        
        String formato2 = fechaFin.getDateFormatString();
        Date date2 =  fechaFin.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
        
         String fecha_inicio=String.valueOf(sdf.format(date));
        String fecha_fin=String.valueOf(sdf2.format(date2));
        
        try{   
         Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_inicio);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(inputDate);    
         
        
         
         fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
         int numero_semana=calendar.get(Calendar.WEEK_OF_YEAR);
         int longitud_semana=numero_semana-1;
         double total=0;
            do { 
            
           
                  
                  fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
                 // System.out.println(fecha_inicio); // Devuelve el objeto Date con los nuevos días añadidos
                 
                  //System.out.println("SEMANA: "+calendar.get(Calendar.WEEK_OF_MONTH)); //     calcula el dia de la semana   
                  
                  
                  
                  total=total+OpcionesVen.optener_ventas(fecha_inicio);
                  calendar.add(Calendar.DAY_OF_YEAR,1);                  
                  if(numero_semana!=calendar.get(Calendar.WEEK_OF_YEAR) && total>0 ){
                      total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));
                      dtsc.addValue(total,""+(numero_semana-longitud_semana),  " ($"+total+")");
                      total=0;                 
                  }
                   numero_semana=calendar.get(Calendar.WEEK_OF_YEAR);
                  
   }while(fecha_inicio.compareTo(fecha_fin)!=0);
           if( total>0 ){
            total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));
            dtsc.addValue(total,""+(numero_semana-longitud_semana),  " ($"+total+")");
           
           }
            
  }catch(Exception e){
  System.out.printf(""+e);
  
   }
        grafica(dtsc,"Grafica por semana", "Semana");
   
  
   
    }
    
     void graficar_por_mes(){
     DefaultCategoryDataset dtsc = new DefaultCategoryDataset();       
     
        String formato = fechaInicio.getDateFormatString();
        Date date =  fechaInicio.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        
        String formato2 = fechaFin.getDateFormatString();
        Date date2 =  fechaFin.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
        
         String fecha_inicio=String.valueOf(sdf.format(date));
        String fecha_fin=String.valueOf(sdf2.format(date2));
        int anio=0;
        
        try{   
         Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_inicio);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(inputDate);    
         
        
         
         fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
         int numero_mes=calendar.get(Calendar.MONTH);
         double total=0;
            do { 
            
           
                  
                  fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
                 // System.out.println(fecha_inicio); // Devuelve el objeto Date con los nuevos días añadidos
                 
                 //System.out.println("Mes: "+calendar.get(Calendar.MONTH)); //     calcula el dia de la semana   
                  
                  
                  
                  total=total+OpcionesVen.optener_ventas(fecha_inicio);
                  anio=calendar.get(Calendar.YEAR);
                  calendar.add(Calendar.DAY_OF_YEAR,1);
                  if(numero_mes!=calendar.get(Calendar.MONTH) && total>0 ){
                     total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));
                    dtsc.addValue(total,Mes_anio(numero_mes)+" "+anio, " ($"+total+")");
                    total=0;                 
                  }
                   numero_mes=calendar.get(Calendar.MONTH);
                  
   }while(fecha_inicio.compareTo(fecha_fin)!=0);
           if( total>0 ){
            total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));
            dtsc.addValue(total,Mes_anio(calendar.get(Calendar.MONTH))+" "+anio,  " ($"+total+")");
            
           }
            
  }catch(Exception e){
  System.out.printf(""+e);
  
   }
        grafica(dtsc,"Grafica por mes", "Mes");
  }
     
   void graficar_por_anio(){
     DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
     
    
     
        String formato = fechaInicio.getDateFormatString();
        Date date =  fechaInicio.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        
        String formato2 = fechaFin.getDateFormatString();
        Date date2 =  fechaFin.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
        
         String fecha_inicio=String.valueOf(sdf.format(date));
        String fecha_fin=String.valueOf(sdf2.format(date2));
        
        try{   
         Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_inicio);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(inputDate);    
         
        
         
         fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
         int numero_anio=calendar.get(Calendar.YEAR);
         double total=0;
            do { 
            
           
                  
                  fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
                 // System.out.println(fecha_inicio); // Devuelve el objeto Date con los nuevos días añadidos
                 
                 //System.out.println("Mes: "+calendar.get(Calendar.YEAR)); //     calcula el dia de la semana   
                  
                  
                  
                  total=total+OpcionesVen.optener_ventas(fecha_inicio);
                  calendar.add(Calendar.DAY_OF_YEAR,1);
                  if(numero_anio!=calendar.get(Calendar.YEAR) && total>0 ){
                      total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));
                      dtsc.addValue(total, ""+numero_anio,  " ($"+total+")");                  
                      total=0;                 
                  }
                   numero_anio=calendar.get(Calendar.YEAR);
                  
   }while(fecha_inicio.compareTo(fecha_fin)!=0);
           if( total>0 ){
            total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));
            dtsc.addValue(total, ""+numero_anio,  " ($"+total+")");
           }
            
  }catch(Exception e){
  System.out.printf(""+e);
  
   }
        grafica(dtsc,"Grafica por año", "Año");
        
   }
    JFreeChart ch;
   ChartPanel cp;
   void grafica( DefaultCategoryDataset dtsc, String titulo, String etiqueta_y){
       try{
           
             ch = ChartFactory.createBarChart(titulo,etiqueta_y, "Ganancias", dtsc,PlotOrientation.VERTICAL, true, true, false);
             
             cp = new ChartPanel(ch);
            frame_grafica.removeAll();
            frame_grafica.add(cp);         
    
        
            
            //add(cp);
         int g= dtsc.getColumnCount();
            
            cp.setBounds(0,0,definir_dimeciones(g),300);
            
            
            
            
       }catch(Exception e){ System.out.println(""+e); }
       
   }
   
   int definir_dimeciones(int numero){
       int dimencion =0;
       System.out.println("numero: "+numero);
       if (numero<7){
         frame_grafica.setSize(700,300);         
         dimencion=frame_grafica.getWidth();
          return dimencion;     
       }
       
       if (numero<9){
         frame_grafica.setSize(900,300);         
         dimencion=frame_grafica.getWidth();
          return dimencion;           
       }
       
       if (numero<13){
         frame_grafica.setSize(1100,300);         
         dimencion=frame_grafica.getWidth();
         
          return dimencion;        
       }
       
       if (numero>=13){
         frame_grafica.setSize(1300,300);
         this.setLocation(150,  3);
         dimencion=frame_grafica.getWidth()+300;
         return dimencion;         
       }
               
               return dimencion;
       
          
   }
   String Dia_semana(int  day){
       String dia="";
        switch(day){
            
            case 1: dia="Domingo";
                break;
            case 2: dia="Lunes";
                break;
            case 3: dia="Martes";
                break;
            case 4: dia="Miercoles";
                break;
            case 5: dia="Jueves";
                break;
            case 6: dia="Viernes";
                break;    
            case 7: dia="Sabado";
                break;
            
                
               
            
        }
       
       return dia;
       
       
   }
   String Mes_anio(int  mes){
       String dia="";
        switch(mes){
            case 0: dia="Enero";
                break;
            case 1: dia="Febrero";
                break;
            case 2: dia="Marzo";
                break;
            case 3: dia="Abril";
                break;
            case 4: dia="Mayo";
                break;
            case 5: dia="Junio";
                break;
            case 6: dia="Julio";
                break;    
            case 7: dia="Agosto";
                break;
            case 8: dia="Septiembre";
                break;    
            case 9: dia="Octubre";
                break;
            case 10: dia="Nobiembre";
                break;    
            case 11: dia="Diciembre";
                break;
            
                
               
            
        }
       
       return dia;
       
       
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

        CONSULTAS = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        buscar = new app.bolivia.swing.JCTextField();
        codigoL1 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        eliminar = new javax.swing.JButton();
        eliminarT = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        buscF = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ventasH = new javax.swing.JButton();
        codigoL2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        Opciones = new org.bolivia.combo.SComboBoxBlue();
        jButton1 = new javax.swing.JButton();
        frame_scroll = new javax.swing.JScrollPane();
        frame_grafica = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("REGISTRO VENTAS");

        CONSULTAS.setBackground(new java.awt.Color(255, 255, 255));
        CONSULTAS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CONSULTASMouseClicked(evt);
            }
        });
        CONSULTAS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CONSULTASKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. VENTA", "TOTAL", "FECHA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(tablaVentas);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buscar.setBackground(new java.awt.Color(34, 102, 145));
        buscar.setBorder(null);
        buscar.setForeground(new java.awt.Color(255, 255, 255));
        buscar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar.setOpaque(false);
        buscar.setPhColor(new java.awt.Color(255, 255, 255));
        buscar.setPlaceholder("No. VENTA");
        buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarMouseClicked(evt);
            }
        });
        buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscarKeyTyped(evt);
            }
        });
        jPanel4.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 180, -1));

        codigoL1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/buscarL.png"))); // NOI18N
        jPanel4.add(codigoL1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 300, 52));

        fecha.setDateFormatString("dd/MM/yyyy");
        fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel4.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 150, 30));

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
        jPanel4.add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, -1));

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
        jPanel4.add(eliminarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

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
        jPanel4.add(limpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, -1));

        buscF.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buscF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/buscaF1.png"))); // NOI18N
        buscF.setToolTipText("Buscar");
        buscF.setBorder(null);
        buscF.setBorderPainted(false);
        buscF.setContentAreaFilled(false);
        buscF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buscF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscF.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/buscaF2.png"))); // NOI18N
        buscF.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buscF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscFActionPerformed(evt);
            }
        });
        jPanel4.add(buscF, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Fecha");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        ventasH.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ventasH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/ventasH1.png"))); // NOI18N
        ventasH.setToolTipText("Buscar");
        ventasH.setBorder(null);
        ventasH.setBorderPainted(false);
        ventasH.setContentAreaFilled(false);
        ventasH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ventasH.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventasH.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/ventasH2.png"))); // NOI18N
        ventasH.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventasH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasHActionPerformed(evt);
            }
        });
        jPanel4.add(ventasH, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        codigoL2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoL2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/buscarL.png"))); // NOI18N
        jPanel4.add(codigoL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 300, 52));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        CONSULTAS.addTab("CONSULTAS", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fechaInicio.setDateFormatString("dd/MM/yyyy");
        fechaInicio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel5.add(fechaInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 30));

        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Fecha inico");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        fechaFin.setDateFormatString("dd/MM/yyyy");
        fechaFin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel5.add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 150, 30));

        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Fecha fin");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, -1));

        Opciones.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ULTIMOS 7 DIAS", "POR DIA", "POR SEMANA", "POR MES", "POR AÑO" }));
        Opciones.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Opciones.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                OpcionesItemStateChanged(evt);
            }
        });
        Opciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpcionesActionPerformed(evt);
            }
        });
        jPanel5.add(Opciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 183, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, -1, -1));

        frame_grafica.setBackground(new java.awt.Color(255, 255, 255));
        frame_grafica.setPreferredSize(new java.awt.Dimension(3000, 300));

        javax.swing.GroupLayout frame_graficaLayout = new javax.swing.GroupLayout(frame_grafica);
        frame_grafica.setLayout(frame_graficaLayout);
        frame_graficaLayout.setHorizontalGroup(
            frame_graficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3000, Short.MAX_VALUE)
        );
        frame_graficaLayout.setVerticalGroup(
            frame_graficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );

        frame_scroll.setViewportView(frame_grafica);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 885, Short.MAX_VALUE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(frame_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 93, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(frame_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        CONSULTAS.addTab("GANANCIAS", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CONSULTAS)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CONSULTAS)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiaCampos();
    }//GEN-LAST:event_limpiarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (tablaVentas.getRowCount() > 0) {
            if (tablaVentas.getSelectedRowCount() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Esta a punto de eliminar\nun registro.\n¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                    int fila = tablaVentas.getSelectedRow();
                    String id = tablaVentas.getValueAt(fila, 0).toString();
                    int elimina = OpcionesVen.eliminar(id);
                    if (elimina != 0) {
                        limpiaCampos();
                        JOptionPane.showMessageDialog(this, "Registro eliminado.", "Registro Ventas", 0,
                                new ImageIcon(getClass().getResource("/imagenes/caja/borrado1.png")));
                        if (principal.MenuPrincipalAd.cerra) {
                            OpcionesVen.numeros();
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Registro Ventas", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
            }

        } else {
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Registro Ventas", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_eliminarActionPerformed
  productos.confirma_admin confirmar;
    private void eliminarTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTActionPerformed
        if (tablaVentas.getRowCount() > 0) {
            if (JOptionPane.showConfirmDialog(this, "Esta a punto de elimnar\ntodos los registros.\n¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/seguro.png"))) == JOptionPane.YES_OPTION) {
                 productos.confirma_admin.opcion=2;
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
            JOptionPane.showMessageDialog(this, "No hay registros\npara eliminar.", "Registro Ventas", 0,
                    new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
        }
    }//GEN-LAST:event_eliminarTActionPerformed

    private void buscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyTyped
        char num = evt.getKeyChar();
        if ((num < '0' || num > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_buscarKeyTyped

    private void buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarKeyReleased
        OpcionesVen.listar(buscar.getText());
    }//GEN-LAST:event_buscarKeyReleased

    private void buscFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscFActionPerformed
        if (fecha.getDate() == null) {
            OpcionesVen.listar("");
        } else {
            String formato = fecha.getDateFormatString();
            Date date = fecha.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            OpcionesVen.listar(String.valueOf(sdf.format(date)));
        }
    }//GEN-LAST:event_buscFActionPerformed

    private void ventasHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasHActionPerformed
        Date sistemaFech = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fecH = formato.format(sistemaFech);
        OpcionesVen.listar(fecH);
        fecha.setDate(null);
    }//GEN-LAST:event_ventasHActionPerformed

    private void buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarMouseClicked
        fecha.setDate(null);
        OpcionesVen.listar("");
    }//GEN-LAST:event_buscarMouseClicked

    private void OpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OpcionesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    graficar_por_anio();
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CONSULTASKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CONSULTASKeyPressed
        
    }//GEN-LAST:event_CONSULTASKeyPressed

    private void CONSULTASMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CONSULTASMouseClicked
      if(CONSULTAS.getSelectedIndex()==1){
        grafica_ultima_semana(); 
        this.setSize(1000,500);
        this.setLocation(150,  3);
      }
      else{
          this.setSize(1000,450);
          this.setLocation(225,  3);
      }
       
    }//GEN-LAST:event_CONSULTASMouseClicked

    private void OpcionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_OpcionesItemStateChanged
switch(Opciones.getSelectedIndex()){
                   case 0:
                       grafica_ultima_semana();
                       break;
                    case 1:
                        graficar_por_dia();
                       break;
                    case 2:
                        graficar_por_semana();
                       break;
                    case 3:
                        graficar_por_mes();
                       break;
                    case 4:
                        graficar_por_anio();
                       break;
               }        // TODO add your handling code here:
    }//GEN-LAST:event_OpcionesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane CONSULTAS;
    private org.bolivia.combo.SComboBoxBlue Opciones;
    private javax.swing.JButton buscF;
    private app.bolivia.swing.JCTextField buscar;
    private javax.swing.JLabel codigoL1;
    private javax.swing.JLabel codigoL2;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton eliminarT;
    private com.toedter.calendar.JDateChooser fecha;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JPanel frame_grafica;
    private javax.swing.JScrollPane frame_scroll;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiar;
    public static javax.swing.JTable tablaVentas;
    private javax.swing.JButton ventasH;
    // End of variables declaration//GEN-END:variables
}
