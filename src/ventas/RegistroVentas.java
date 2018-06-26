/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;


import java.awt.Image;
import java.awt.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
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
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        barraDeTitulo1.setVisible(false);
        tablaVentas.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaVentas.setDefaultRenderer(Object.class, new principal.EstiloTablaRenderer());
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/caja/icono1.png")));
        tablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        limpiaCampos();           
        poner_fechas();// en el apartado de las graficas
        this.setLocation(500, 3);
        //graficar();
    }

    void limpiaCampos() {
        if (tablaVentas.getSelectedRow() > -1) {
            tablaVentas.removeRowSelectionInterval(tablaVentas.getSelectedRow(), tablaVentas.getSelectedRow());
        }        
        buscar.setText("");
        
        Date sistemaFech = new Date();//fecha de hoy               
        fecha.setDate(sistemaFech);
        
        String formato = fecha.getDateFormatString();
        Date date = fecha.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        OpcionesVen.listar(String.valueOf(sdf.format(date)));
        total.setText(OpcionesVen.suma_total(String.valueOf(sdf.format(date))));
        
       
    }

   void grafica_ultima_semana(){//otiene 7 dias anstes desde hoy
    
     ArrayList<Double> ganacia = new ArrayList<Double>();
     ArrayList<String> dia_semana = new ArrayList<String>();
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
                  
                  total=OpcionesVen.optener_ventas(fecha_inicio,1);
                 
                  if(total>0){
                      ganacia.add(total);
                      dia_semana.add(Dia_semana(calendar.get(Calendar.DAY_OF_WEEK))+" "+ fecha_inicio);
                      //dtsc.addValue(total,Dia_semana(calendar.get(Calendar.DAY_OF_WEEK))+" "+ fecha_inicio,  " ($"+total+")");                  
                  }
                   calendar.add(Calendar.DAY_OF_YEAR,1);
   }while(fecha_inicio.compareTo(fecha_fin)!=0);
    graficar(ganacia,dia_semana) ;       
  }catch(Exception e){
 // System.out.printf(""+e);
  System.out.println(""+e.getMessage());
   }
       
    }
   
   void graficar_por_dia(){
       
       ArrayList<Double> ganacia = new ArrayList<Double>();
       ArrayList<String> dia_semana = new ArrayList<String>();
        try{
       
        
          
     
        String formato = fechaInicio.getDateFormatString();
        Date date =  fechaInicio.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
               
       
        String formato2 = fechaFin.getDateFormatString();
        Date date2 =  fechaFin.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
        
         String fecha_inicio=String.valueOf(sdf.format(date));
        String fecha_fin=String.valueOf(sdf2.format(date2));
        
        
         Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_inicio);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(inputDate);    
        
        
         
         //fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
         double total=0;
            do { 
              fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
              
                  //System.out.println(fecha_inicio); // Devuelve el objeto Date con los nuevos días añadidos
                 
                  //System.out.println("GRAFICAR"+ fecha_inicio); //     calcula el dia de la semana   
                  
                  
                  
                  total=OpcionesVen.optener_ventas(fecha_inicio,1);
                  if(total>0 ){
                      total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));                     
                      ganacia.add(total);
                      dia_semana.add(""+fecha_inicio);
                  }
                   calendar.add(Calendar.DAY_OF_YEAR,1);
   }while(fecha_inicio.compareTo(fecha_fin)!=0);
  graficar(ganacia,dia_semana);  
  }catch(Exception e){
 JOptionPane.showMessageDialog(this, "Verifique que las fechas esten correctamente.", "Ventas", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
  System.out.println(""+e.getMessage());
   }
       
   }
   
    void graficar_por_semana(){
        ArrayList<Double> ganacia = new ArrayList<Double>();
        ArrayList<String> semana = new ArrayList<String>();
    try{  
      
     
        String formato = fechaInicio.getDateFormatString();
        Date date =  fechaInicio.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        
        String formato2 = fechaFin.getDateFormatString();
        Date date2 =  fechaFin.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
        
         String fecha_inicio=String.valueOf(sdf.format(date));
        String fecha_fin=String.valueOf(sdf2.format(date2));
        
          
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
                  
                  
                  
                  total=total+OpcionesVen.optener_ventas(fecha_inicio,1);
                  calendar.add(Calendar.DAY_OF_YEAR,1);                  
                  if(numero_semana!=calendar.get(Calendar.WEEK_OF_YEAR) && total>0 ){
                      total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));                      
                      ganacia.add(total);
                      semana.add(""+(numero_semana-longitud_semana));
                      
                      total=0;                 
                  }
                   numero_semana=calendar.get(Calendar.WEEK_OF_YEAR);
                  
   }while(fecha_inicio.compareTo(fecha_fin)!=0);
           if( total>0 ){
            total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));
            ganacia.add(total);
            semana.add(""+(numero_semana-longitud_semana));
           
           }
     graficar(ganacia,semana);    
  }catch(Exception e){
  JOptionPane.showMessageDialog(this, "Verifique que las fechas esten correctamente.", "Ventas", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
  System.out.println(""+e.getMessage());
   }
       
   
  
   
    }
    
     void graficar_por_mes(){
         ArrayList<Double> ganacia = new ArrayList<Double>();
         ArrayList<String> mes = new ArrayList<String>();
     
       try{    
            
     
        String formato = fechaInicio.getDateFormatString();
        Date date =  fechaInicio.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        
        String formato2 = fechaFin.getDateFormatString();
        Date date2 =  fechaFin.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
        
         String fecha_inicio=String.valueOf(sdf.format(date));
        String fecha_fin=String.valueOf(sdf2.format(date2));
        int anio=0;
        
         
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
                  
                  
                  
                  total=total+OpcionesVen.optener_ventas(fecha_inicio,1);
                  anio=calendar.get(Calendar.YEAR);
                  calendar.add(Calendar.DAY_OF_YEAR,1);
                  if(numero_mes!=calendar.get(Calendar.MONTH) && total>0 ){
                     total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));
                    ganacia.add(total);
                    mes.add(Mes_anio(numero_mes)+" "+anio);
                    total=0;                 
                  }
                   numero_mes=calendar.get(Calendar.MONTH);
                  
   }while(fecha_inicio.compareTo(fecha_fin)!=0);
           if( total>0 ){
            total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));
            ganacia.add(total);
            mes.add(Mes_anio(numero_mes)+" "+anio);
            
           }
    graficar(ganacia,mes);         
  }catch(Exception e){
  JOptionPane.showMessageDialog(this, "Verifique que las fechas esten correctamente.", "Ventas", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
  System.out.println(""+e.getMessage());
   }
        
  }
     
   void graficar_por_anio(){
       ArrayList<Double> ganacia = new ArrayList<Double>();
       ArrayList<String> anio = new ArrayList<String>();
     try{ 
      
       String fecha_inicio="01/01/2015";
        
        Date sistemaFech = new Date();
        SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
        String fecha_fin=form.format(sistemaFech);
        
        Date inputDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_fin);
         Calendar calendar2 = Calendar.getInstance();
         calendar2.setTime(inputDate2); 
      
        
         
         Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_inicio);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(inputDate);    
         
        
         
         fecha_inicio=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
         int numero_anio=calendar.get(Calendar.YEAR);
         double total=0;
            do { 
            
           
                  
                  fecha_inicio=String.format("%1$tY",calendar.getTime());
                 //System.out.println(fecha_inicio); // Devuelve el objeto Date con los nuevos días añadidos
                 
                 //System.out.println("Mes: "+calendar.get(Calendar.YEAR)); //     calcula el dia de la semana   
                  
                  
                  
                total=total+OpcionesVen.optener_ventas(fecha_inicio,1);
                calendar.add(Calendar.YEAR,1);
                  
                 total=Double.parseDouble(String.format("%3.2f", (total)).replace(".00",""));                        
                 ganacia.add(total);
                 anio.add(""+numero_anio);
                 total=0;                 
                  
                   numero_anio=calendar.get(Calendar.YEAR);
                  
   }while(calendar.get(Calendar.YEAR)!= calendar2.get(Calendar.YEAR)+1);
          
   graficar(ganacia,anio);         
  }catch(Exception e){
  JOptionPane.showMessageDialog(this, "Verifique que las fechas esten correctamente.", "Ventas", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
  System.out.println(""+e.getMessage());
  
   }
        
        
   }
   
   
   void graficar( ArrayList<Double> ganancia,  ArrayList<String> dia_semana){
      labels.removeAll();
      frame_grafica1.removeAll();
      
       int alto=250;
       int ancho=30;
       int posx=50;
       int posy=40;
       
       double mayor = mayor(ganancia);
       
      fondoGrafica(alto,ancho+10,posx,posy,mayor,ganancia.size()); 
       
       
       
       
       //System.out.println("mayor: "+mayor);
       double altura;
       for(int i=0; i<ganancia.size();i++){
            altura=(ganancia.get(i)*alto)/mayor;
            
            //System.out.println("altura: "+(int)Math.ceil(altura) );
            int imagen=(i%12);
                if((int)altura>0){
                    ImageResizer.MAX_HEIGHT=(int)Math.ceil(altura);
                    ImageResizer.MAX_WIDTH=ancho;       
                    Icon icono = ImageResizer.copyImage("src/imagenes/Productos/grafica"+imagen+".png");    
                    System.out.println("i: "+i+"altura: "+imagen );
       
                    JLabel graf=  new  JLabel();
                    graf.setIcon( icono);
                    graf.setLocation(posx, posy);
                    graf.setSize(ancho+10,alto);
                    //graf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))); 
                    graf.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    frame_grafica1.add(graf);
                    
                }
       
            JLabel cantidad=  new  JLabel();
            cantidad.setText(""+ganancia.get(i));
            cantidad.setLocation(posx, 45+alto);
            cantidad.setSize(40,10);       
            //cantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))); 
            frame_grafica1.add(cantidad);
       
            JLabel datos=  new  JLabel();
            datos.setText(dia_semana.get(i));
            labels.add(datos);
      
            datos.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/punto"+imagen+".png")));
            //datos..Color(0, 0, 0))); setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))); 
            //System.out.println("dia: "+dia_semana.get(i));
       
            posx=posx+40;
       }
     frame_grafica1.repaint();
   }
    void fondoGrafica(int alto,int ancho,int posx, int posy,double ganacia,int cantidadElementos){
       int alttura=1;//altura de la linea a dibuhar
       int anchura=cantidadElementos*ancho+ancho;
       //ImageResizer.MAX_HEIGHT=alttura;
       //ImageResizer.MAX_WIDTH= anchura;     
       //Icon icono = ImageResizer.copyImage("src/imagenes/Productos/fondoGrafica.png");    
       int lineas=cantidad_lineas(ganacia);
       int decY=(int)Math.ceil((alto/(lineas+1))+1);//decremento de Y
       int posicionY=alto;
       int sumaDato=0;
       for(int i=0;i<=lineas+1;i++){
            JLabel graf=  new  JLabel();
            //graf.setIcon( icono);
            graf.setLocation(posx, posicionY+posy-1);
            posicionY=posicionY-decY;
            graf.setSize(anchura,alttura);
            graf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255))); 
            graf.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            frame_grafica1.add(graf);
            
            
            JLabel dato=  new  JLabel();
            //graf.setIcon( icono);
            dato.setLocation(5, posicionY+posy);            
            dato.setSize(40,decY);
            //dato.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))); 
            dato.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            dato.setText(""+sumaDato);
            sumaDato=sumaDato+proporsion;
            
            frame_grafica1.add(dato);
            
            
      
        }
        
    }
     int proporsion=0;
    int cantidad_lineas(double ganancia){
        int cant=0;
        if(ganancia<10){ 
            proporsion=1;
            cant=(int)(ganancia/1);
            return cant;            
        }        
        if(ganancia<30){  
            proporsion=5;
            cant=(int)(ganancia/5);
            return cant;            
        }        
        if(ganancia<100){  
            proporsion=10;
            cant=(int)(ganancia/10);            
            return cant;
        }        
        if(ganancia<200){            
            proporsion=20;
            cant=(int)(ganancia/20);            
            return cant;
        }
        if(ganancia<300){
            proporsion=30;
            cant=(int)(ganancia/30);            
            return cant;
        }
        
        if(ganancia<500){            
            proporsion=50;
            cant=(int)(ganancia/50);            
            return cant;
        }
        
        if(ganancia<1000){
            proporsion=100;          
            cant=(int)(ganancia/100);            
            return cant;
        }
        
        if(ganancia<5000){ 
            proporsion=500;         
            cant=(int)(ganancia/500);            
            return cant;
        }
        
        if(ganancia<10000){ 
            proporsion=1000;         
            cant=(int)(ganancia/1000);            
            return cant;
        }
        
        if(ganancia<30000){ 
            proporsion=500;           
            cant=(int)(ganancia/5000);            
            return cant;
        }
        if(ganancia<100000){ 
            proporsion=10000;           
            cant=(int)(ganancia/10000);            
            return cant;
        }
        
        if(ganancia<500000){ 
            proporsion=50000;          
            cant=(int)(ganancia/50000);            
            return cant;
        }
        
        if(ganancia<1000000){ 
            proporsion=100000;           
            cant=(int)(ganancia/100000);            
            return cant;
        }
        
        cant=(int)(ganancia/1000000);  
        proporsion=1000000;
        return cant;
    }
   double mayor(ArrayList<Double> ganacia){
       double mayor =0;
        for(int i=0; i<ganacia.size();i++){
            
            if(ganacia.get(i)>mayor)mayor=ganacia.get(i);
            
        }
        return mayor;
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
       
       if (numero<15){
         frame_grafica.setSize(1100,300);         
         dimencion=frame_grafica.getWidth();
         
          return dimencion;        
       }
       
        if (numero<17){
         frame_grafica.setSize(1400,300);         
         dimencion=frame_grafica.getWidth();         
          return dimencion;        
       }
       
       if (numero>=17){
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
   
 boolean fecha_valida(){
     try{
        String formato = fechaInicio.getDateFormatString();
        Date date =  fechaInicio.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
               
       
        String formato2 = fechaFin.getDateFormatString();
        Date date2 =  fechaFin.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat(formato2);
        
        String fecha_inicio=String.valueOf(sdf.format(date));
        String fecha_fin=String.valueOf(sdf2.format(date2));
        
        
         Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_inicio);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(inputDate);
         
         Date inputDate2 = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_fin);
         Calendar calendar2 = Calendar.getInstance();
         calendar2.setTime(inputDate2);
         //System.out.println("fecha1: "+calendar.get(Calendar.YEAR));
         //System.out.println("fecha2: "+calendar2.get(Calendar.YEAR));
         if(calendar.get(Calendar.YEAR)<2017 || calendar2.get(Calendar.YEAR)<2018 ){
             //System.out.println(calendar.get(Calendar.YEAR));
             JOptionPane.showMessageDialog(this, "Año de inicio establecido es incorecto: AÑO mayor a 2017 y AÑO menor a 2025", "Error  de fecha", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
             return false;
         }
         if(calendar.get(Calendar.YEAR)>2025 || calendar2.get(Calendar.YEAR)>2025){
             //System.out.println(calendar.get(Calendar.YEAR));
             JOptionPane.showMessageDialog(this, "Año de final establecido es incorecto: AÑO mayor a 2017 y AÑO menor a 2025", "Error  de fecha", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
             return false;
         }
          if(inputDate2.before(inputDate)) {
                 JOptionPane.showMessageDialog(this, "Fecha inicio es mayor a la fecha final", "Error de fecha", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
              return false;   
          } 
         
          //System.out.println(calendar2.get(Calendar.YEAR));
          // System.out.println(calendar.get(Calendar.YEAR));
        
         
        
  }catch(Exception e){
 JOptionPane.showMessageDialog(this, "Verifique que las fechas esten correctamente.","Error  de fecha", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
 return false;
  
   }
     
     return true;
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

        barraDeTitulo1 = new javax.swing.JPanel();
        miminizar2 = new javax.swing.JButton();
        miminizar3 = new javax.swing.JButton();
        titulo1 = new javax.swing.JLabel();
        barraDeTitulo = new javax.swing.JPanel();
        miminizar = new javax.swing.JButton();
        miminizar1 = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        CONSULTAS = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        buscar = new app.bolivia.swing.JCTextField();
        fecha = new com.toedter.calendar.JDateChooser();
        eliminar = new javax.swing.JButton();
        eliminarT = new javax.swing.JButton();
        limpiar = new javax.swing.JButton();
        buscF = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ventasH = new javax.swing.JButton();
        codigoL2 = new javax.swing.JLabel();
        total = new app.bolivia.swing.JCTextField();
        codigoL3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        fechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        fechaFin = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        Opciones = new org.bolivia.combo.SComboBoxBlue();
        frame_scroll1 = new javax.swing.JScrollPane();
        frame_grafica1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        labels = new javax.swing.JPanel();
        frame_scroll = new javax.swing.JScrollPane();
        frame_grafica = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(7, 37, 77), 4));
        setClosable(true);
        setIconifiable(true);
        setTitle("REGISTRO VENTAS");

        barraDeTitulo1.setBackground(new java.awt.Color(7, 37, 77));

        miminizar2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        miminizar2.setForeground(new java.awt.Color(255, 255, 255));
        miminizar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/close.png"))); // NOI18N
        miminizar2.setBorder(null);
        miminizar2.setContentAreaFilled(false);
        miminizar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miminizar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        miminizar2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/close2.png"))); // NOI18N
        miminizar2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        miminizar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miminizar2ActionPerformed(evt);
            }
        });

        miminizar3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        miminizar3.setForeground(new java.awt.Color(255, 255, 255));
        miminizar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Productos/ico.png"))); // NOI18N
        miminizar3.setBorder(null);
        miminizar3.setContentAreaFilled(false);
        miminizar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        miminizar3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        miminizar3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        miminizar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miminizar3ActionPerformed(evt);
            }
        });

        titulo1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        titulo1.setForeground(new java.awt.Color(255, 255, 255));
        titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo1.setText("REGISTROS DE VENTAS");
        titulo1.setAlignmentY(0.0F);

        javax.swing.GroupLayout barraDeTitulo1Layout = new javax.swing.GroupLayout(barraDeTitulo1);
        barraDeTitulo1.setLayout(barraDeTitulo1Layout);
        barraDeTitulo1Layout.setHorizontalGroup(
            barraDeTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barraDeTitulo1Layout.createSequentialGroup()
                .addComponent(miminizar3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(miminizar2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(596, 596, 596))
        );
        barraDeTitulo1Layout.setVerticalGroup(
            barraDeTitulo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(miminizar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(titulo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(miminizar2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

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
        titulo.setText("REGISTROS DE VENTAS");

        javax.swing.GroupLayout barraDeTituloLayout = new javax.swing.GroupLayout(barraDeTitulo);
        barraDeTitulo.setLayout(barraDeTituloLayout);
        barraDeTituloLayout.setHorizontalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraDeTituloLayout.createSequentialGroup()
                .addComponent(miminizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(miminizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(793, 793, 793))
        );
        barraDeTituloLayout.setVerticalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(miminizar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(miminizar, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

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
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
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
        jPanel4.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 180, -1));

        fecha.setDateFormatString("dd/MM/yyyy");
        fecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel4.add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 180, 30));

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
        jPanel4.add(buscF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("TOTAL");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 50, -1));

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
        jPanel4.add(codigoL2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 300, 52));

        total.setEditable(false);
        total.setBackground(new java.awt.Color(34, 102, 145));
        total.setBorder(null);
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        total.setText("$69.00");
        total.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        total.setOpaque(false);
        total.setPhColor(new java.awt.Color(255, 255, 255));
        total.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalMouseClicked(evt);
            }
        });
        total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totalKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                totalKeyTyped(evt);
            }
        });
        jPanel4.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 220, -1));

        codigoL3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoL3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/vacio.png"))); // NOI18N
        jPanel4.add(codigoL3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 300, 52));

        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Fecha");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
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
        jLabel2.setText("Fecha inicio");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, -1));

        fechaFin.setDateFormatString("dd/MM/yyyy");
        fechaFin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel5.add(fechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 150, 30));

        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Fecha fin");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

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

        frame_grafica1.setBackground(new java.awt.Color(255, 255, 255));
        frame_grafica1.setPreferredSize(new java.awt.Dimension(3000, 300));

        javax.swing.GroupLayout frame_grafica1Layout = new javax.swing.GroupLayout(frame_grafica1);
        frame_grafica1.setLayout(frame_grafica1Layout);
        frame_grafica1Layout.setHorizontalGroup(
            frame_grafica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3000, Short.MAX_VALUE)
        );
        frame_grafica1Layout.setVerticalGroup(
            frame_grafica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        frame_scroll1.setViewportView(frame_grafica1);

        labels.setLayout(new java.awt.GridLayout(9, 0));
        jScrollPane2.setViewportView(labels);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 969, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(frame_scroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(frame_scroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        CONSULTAS.addTab("GANANCIAS", jPanel2);

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
            .addGap(0, 300, Short.MAX_VALUE)
        );

        frame_scroll.setViewportView(frame_grafica);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraDeTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(barraDeTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(CONSULTAS, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(frame_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(barraDeTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraDeTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CONSULTAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(frame_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
           total.setText(OpcionesVen.suma_total(String.valueOf(sdf.format(date))));
        }
    }//GEN-LAST:event_buscFActionPerformed

    private void ventasHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasHActionPerformed
         limpiaCampos();
    }//GEN-LAST:event_ventasHActionPerformed

    private void buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarMouseClicked
        fecha.setDate(null);
        OpcionesVen.listar("");
    }//GEN-LAST:event_buscarMouseClicked

    private void OpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpcionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OpcionesActionPerformed

    private void CONSULTASKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CONSULTASKeyPressed
        
    }//GEN-LAST:event_CONSULTASKeyPressed

    private void CONSULTASMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CONSULTASMouseClicked
      if(CONSULTAS.getSelectedIndex()==1){
        grafica_ultima_semana(); 
        this.setSize(1000,550);
       
        titulo.setSize(1200, 40);
        miminizar.setAlignmentX(2000);
        miminizar.setLocation(1000, 10000);
        miminizar.repaint();
        this.setLocation(150,  3);
        barraDeTitulo.setVisible(false);
        barraDeTitulo1.setVisible(true);
      }
      else{
           barraDeTitulo.setVisible(true);
           barraDeTitulo1.setVisible(false);
            this.setSize(800,550);
          this.setLocation(225,  3);
           
      }
       
    }//GEN-LAST:event_CONSULTASMouseClicked
void poner_fechas(){
    DefaultCategoryDataset dtsc = new DefaultCategoryDataset();
     
     Date sistemaFech = new Date();
     SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");   
       
        String fecha_inicio=formato.format(sistemaFech);
        fechaFin.setDate(sistemaFech);
        try{   
         Date inputDate = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_inicio);
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(inputDate);         
         calendar.add(Calendar.DAY_OF_YEAR,-8);
         
         String fecha=String.format("%1$td/%1$tm/%1$tY",calendar.getTime());
         
         DateFormat fechae = new SimpleDateFormat("dd/MM/yyyy");
         Date convertido = fechae.parse(fecha);			
         
         fechaInicio.setDate(convertido);
              // System.out.println(fecha);
            }catch(Exception e){}
}
    int cont=0;
    private void OpcionesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_OpcionesItemStateChanged
cont++;
if(cont%2==0 && (Opciones.getSelectedIndex()==0 || Opciones.getSelectedIndex()==4 || fecha_valida()) ){
//System.out.println("fecha valida"+cont);    
        switch(Opciones.getSelectedIndex() ){
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
               }        
}
    }//GEN-LAST:event_OpcionesItemStateChanged

    private void totalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_totalMouseClicked

    private void totalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_totalKeyReleased

    private void totalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_totalKeyTyped

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarActionPerformed

    private void miminizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizarActionPerformed
        this.dispose();
    }//GEN-LAST:event_miminizarActionPerformed

    private void miminizar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miminizar1ActionPerformed

    private void miminizar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miminizar2ActionPerformed

    private void miminizar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miminizar3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane CONSULTAS;
    private org.bolivia.combo.SComboBoxBlue Opciones;
    private javax.swing.JPanel barraDeTitulo;
    private javax.swing.JPanel barraDeTitulo1;
    private javax.swing.JButton buscF;
    private app.bolivia.swing.JCTextField buscar;
    private javax.swing.JLabel codigoL2;
    private javax.swing.JLabel codigoL3;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton eliminarT;
    private com.toedter.calendar.JDateChooser fecha;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JPanel frame_grafica;
    private javax.swing.JPanel frame_grafica1;
    private javax.swing.JScrollPane frame_scroll;
    private javax.swing.JScrollPane frame_scroll1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel labels;
    private javax.swing.JButton limpiar;
    private javax.swing.JButton miminizar;
    private javax.swing.JButton miminizar1;
    private javax.swing.JButton miminizar2;
    private javax.swing.JButton miminizar3;
    public static javax.swing.JTable tablaVentas;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo1;
    private app.bolivia.swing.JCTextField total;
    private javax.swing.JButton ventasH;
    // End of variables declaration//GEN-END:variables
}
