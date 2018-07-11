/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;



import clientes.OpcionesCli;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import org.jfree.data.category.DefaultCategoryDataset;
import static principal.MenuPrincipalAd.escritorio;
import static productos.Productos.existes_punto;
import productos.confirma_admin;

public class RegistroVentas extends javax.swing.JInternalFrame {

    /**
     * Creates new form Usuarios
     */
    public RegistroVentas() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        barraDeTitulo1.setVisible(false);
        panel_credito.setVisible(false);
        CajaAd.Credito();
        if(CajaAd.hay_credito)opciones.remove(2);
        tablaVentas.getTableHeader().setDefaultRenderer(new principal.EstiloTablaHeader());
        tablaVentas.setDefaultRenderer(Object.class, new principal.EstiloTablaRenderer());
        this.setFrameIcon(new ImageIcon(getClass().getResource("/imagenes/caja/icono1.png")));
        tablaVentas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cantidad.setVisible(false);
        fondo_cantidad.setVisible(false);
        btn_abonar.setVisible(false);
        
       
       
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
                 if(total>0){
                 ganacia.add(total);
                 anio.add(""+numero_anio);
                 }
                 
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
      frame_grafica.removeAll();
      
       int alto=250;
       int ancho=30;
       int posx=50;
       int posy=50;       
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
                    // cargamos la imagen en un icono
                    ImageIcon ico=new ImageIcon(getClass().getResource("/imagenes/Productos/grafica"+imagen+".png"));
                    Image im = ico.getImage();//convertimos la imagen icono a imagen
                     BufferedImage bi = new BufferedImage//traformamomos la imge para la edicon
                    (im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
                    Graphics bg = bi.getGraphics();
                    bg.drawImage(im, 0, 0, null);
                     bg.dispose();
                    Icon icono = ImageResizer.copyImage(bi);    
                    //System.out.println("i: "+i+"altura: "+imagen );
       
                    JLabel graf=  new  JLabel();
                    graf.setIcon( icono);
                    graf.setLocation(posx, posy);
                    graf.setSize(ancho+10,alto);
                    //graf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))); 
                    graf.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
                    frame_grafica.add(graf);
                    
                }
       
            JLabel cantidad=  new  JLabel();
            cantidad.setText(""+ganancia.get(i));
            cantidad.setLocation(posx, posy+alto+5);
            cantidad.setSize(40,10);       
            //cantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))); 
            frame_grafica.add(cantidad);
       
            JLabel datos=  new  JLabel();
            datos.setText(dia_semana.get(i));
            labels.add(datos);
      
            datos.setIcon(new ImageIcon(getClass().getResource("/imagenes/Productos/punto"+imagen+".png")));
            //datos..Color(0, 0, 0))); setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))); 
            //System.out.println("dia: "+dia_semana.get(i));
       
            posx=posx+40;
       }
     frame_grafica.repaint();
   }
      
    void fondoGrafica(int alto,int ancho,int posx, int posy,double ganacia,int cantidadElementos){
       int alttura=1;//altura de la linea a dibuhar
       int anchura=cantidadElementos*ancho+ancho; 
       cantidad_lineas(ganacia);
       
       
        //System.out.println("lineas: "+lineas);     
       int sumaDato=0;
       double puntoX=0;
       int lineas =(int)(ganacia/proporsion);
       if((ganacia%proporsion)>0)lineas++;
       for(double i=0;i<lineas+1;i++){           
            JLabel linea=  new  JLabel();
            linea.setLocation(posx-30, (int)Math.round(alto-puntoX+posy)); 
            linea.setSize(anchura,alttura);
            linea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));
            frame_grafica.add(linea);
            
            puntoX =puntoX + (proporsion*alto*1.0)/ganacia;
            //System.out.println("puntoX: "+puntoX); 
            
            JLabel dato=  new  JLabel();            
            dato.setLocation(5, (int)Math.round(alto-puntoX+posy));            
            dato.setSize(40,(int)Math.ceil((proporsion*alto*1.0)/ganacia));
            //dato.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))); 
            dato.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
            dato.setText(""+sumaDato);
            sumaDato=sumaDato+proporsion;            
            frame_grafica.add(dato);
            
            
      
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

        menu = new javax.swing.JPopupMenu();
        mostrar_productos = new javax.swing.JMenuItem();
        barraDeTitulo1 = new javax.swing.JPanel();
        miminizar2 = new javax.swing.JButton();
        miminizar3 = new javax.swing.JButton();
        titulo1 = new javax.swing.JLabel();
        barraDeTitulo = new javax.swing.JPanel();
        miminizar = new javax.swing.JButton();
        miminizar1 = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        opciones = new javax.swing.JTabbedPane();
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
        frame_grafica = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        labels = new javax.swing.JPanel();
        panel_credito = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        buscar1 = new app.bolivia.swing.JCTextField();
        btn_abonar = new javax.swing.JButton();
        codigoL4 = new javax.swing.JLabel();
        txt_adeudo = new app.bolivia.swing.JCTextField();
        codigoL5 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cantidad = new app.bolivia.swing.JCTextField();
        fondo_cantidad = new javax.swing.JLabel();
        btn_abonar1 = new javax.swing.JButton();
        normal = new javax.swing.JRadioButton();
        detallado = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        panelNormal = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        panelDetallado = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaCredito = new javax.swing.JTable();

        mostrar_productos.setText("Mostrar Compra");
        mostrar_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrar_productosActionPerformed(evt);
            }
        });
        menu.add(mostrar_productos);

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
        titulo1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                titulo1MouseDragged(evt);
            }
        });

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
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(miminizar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(793, 793, 793))
        );
        barraDeTituloLayout.setVerticalGroup(
            barraDeTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(miminizar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(miminizar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
            .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        opciones.setBackground(new java.awt.Color(255, 255, 255));
        opciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                opcionesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                opcionesMouseEntered(evt);
            }
        });
        opciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                opcionesKeyPressed(evt);
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
        tablaVentas.setComponentPopupMenu(menu);
        tablaVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaVentasMouseClicked(evt);
            }
        });
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
        jLabel1.setText("TOTAL DE VENTAS DE HOY");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 200, -1));

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
                .addContainerGap(376, Short.MAX_VALUE))
        );

        opciones.addTab("CONSULTAS", jPanel1);

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
            .addGap(0, 340, Short.MAX_VALUE)
        );

        frame_scroll1.setViewportView(frame_grafica);

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
                .addContainerGap(381, Short.MAX_VALUE))
        );

        opciones.addTab("GANANCIAS", jPanel2);

        panel_credito.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "OPCIONES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buscar1.setBackground(new java.awt.Color(34, 102, 145));
        buscar1.setBorder(null);
        buscar1.setForeground(new java.awt.Color(255, 255, 255));
        buscar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscar1.setOpaque(false);
        buscar1.setPhColor(new java.awt.Color(255, 255, 255));
        buscar1.setPlaceholder("NOMBRE");
        buscar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscar1MouseClicked(evt);
            }
        });
        buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscar1ActionPerformed(evt);
            }
        });
        buscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscar1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                buscar1KeyTyped(evt);
            }
        });
        jPanel6.add(buscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 180, -1));

        btn_abonar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_abonar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/abonar1.png"))); // NOI18N
        btn_abonar.setBorder(null);
        btn_abonar.setBorderPainted(false);
        btn_abonar.setContentAreaFilled(false);
        btn_abonar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_abonar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_abonar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/abonar2.png"))); // NOI18N
        btn_abonar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_abonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abonarActionPerformed(evt);
            }
        });
        jPanel6.add(btn_abonar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 100, 90));

        codigoL4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoL4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios/buscarL.png"))); // NOI18N
        jPanel6.add(codigoL4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 300, 52));

        txt_adeudo.setEditable(false);
        txt_adeudo.setBackground(new java.awt.Color(34, 102, 145));
        txt_adeudo.setBorder(null);
        txt_adeudo.setForeground(new java.awt.Color(255, 255, 255));
        txt_adeudo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_adeudo.setText("$69.00");
        txt_adeudo.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txt_adeudo.setOpaque(false);
        txt_adeudo.setPhColor(new java.awt.Color(255, 255, 255));
        txt_adeudo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_adeudoMouseClicked(evt);
            }
        });
        txt_adeudo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_adeudoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_adeudoKeyTyped(evt);
            }
        });
        jPanel6.add(txt_adeudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 220, -1));

        codigoL5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codigoL5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caja/vacio.png"))); // NOI18N
        jPanel6.add(codigoL5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 300, 52));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("TOTAL DE ADEUDO");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        cantidad.setBackground(new java.awt.Color(34, 102, 145));
        cantidad.setBorder(null);
        cantidad.setForeground(new java.awt.Color(255, 255, 255));
        cantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cantidad.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        cantidad.setOpaque(false);
        cantidad.setPhColor(new java.awt.Color(255, 255, 255));
        cantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cantidadMouseClicked(evt);
            }
        });
        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });
        cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cantidadKeyTyped(evt);
            }
        });
        jPanel6.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 180, -1));

        fondo_cantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondo_cantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/dinero.png"))); // NOI18N
        jPanel6.add(fondo_cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 260, 52));

        btn_abonar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_abonar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/cliente1.png"))); // NOI18N
        btn_abonar1.setBorder(null);
        btn_abonar1.setBorderPainted(false);
        btn_abonar1.setContentAreaFilled(false);
        btn_abonar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_abonar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_abonar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/principal/cliente2.png"))); // NOI18N
        btn_abonar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_abonar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abonar1ActionPerformed(evt);
            }
        });
        jPanel6.add(btn_abonar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 280, 90));

        normal.setSelected(true);
        normal.setText("Normal");
        normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                normalActionPerformed(evt);
            }
        });
        jPanel6.add(normal, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, 70, -1));

        detallado.setText("Detallado");
        detallado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalladoActionPerformed(evt);
            }
        });
        jPanel6.add(detallado, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, -1, -1));

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
        tablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClienteMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablaCliente);

        javax.swing.GroupLayout panelNormalLayout = new javax.swing.GroupLayout(panelNormal);
        panelNormal.setLayout(panelNormalLayout);
        panelNormalLayout.setHorizontalGroup(
            panelNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelNormalLayout.setVerticalGroup(
            panelNormalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
        );

        tablaCredito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID CLIENTE", "NOMBRE", "No. VENTA", "FECHA", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaCredito.setComponentPopupMenu(menu);
        tablaCredito.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tablaCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCreditoMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tablaCredito);

        javax.swing.GroupLayout panelDetalladoLayout = new javax.swing.GroupLayout(panelDetallado);
        panelDetallado.setLayout(panelDetalladoLayout);
        panelDetalladoLayout.setHorizontalGroup(
            panelDetalladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
        );
        panelDetalladoLayout.setVerticalGroup(
            panelDetalladoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelDetallado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelDetallado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelNormal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panel_creditoLayout = new javax.swing.GroupLayout(panel_credito);
        panel_credito.setLayout(panel_creditoLayout);
        panel_creditoLayout.setHorizontalGroup(
            panel_creditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_creditoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_creditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        panel_creditoLayout.setVerticalGroup(
            panel_creditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_creditoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(420, Short.MAX_VALUE))
        );

        opciones.addTab("CREDITOS", panel_credito);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraDeTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(barraDeTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(barraDeTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(barraDeTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(opciones, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiaCampos();
    }//GEN-LAST:event_limpiarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (tablaVentas.getRowCount() > 0) {
            if (tablaVentas.getSelectedRowCount() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Esta a punto de eliminar un registro.\n"
                        + "A eliminar este registro tambien se eliminara \n"
                        + "el cliente que esten asociados con la venta.\n"
                        + "¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
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
            if (JOptionPane.showConfirmDialog(this, 
                    "Esta a punto de elimnar todos los registros.\n"
                    + "A eliminar todos los registros tambien se eliminara \n"
                    + "todos los cliente que esten asociados con las ventas.\n"
                    + "¿Desea continuar?", "Registro Ventas", JOptionPane.YES_NO_OPTION, 0,
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

    private void opcionesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_opcionesKeyPressed
        
    }//GEN-LAST:event_opcionesKeyPressed

    private void opcionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcionesMouseClicked
      if(opciones.getSelectedIndex()==1){
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
          if(opciones.getSelectedIndex()==2){
              if(detallado.isSelected()){
             
                panelNormal.setVisible(false);
                panelDetallado.setVisible(true);
                OpcionesVen.optener_creditos("");
                OpcionesVen.optener_adeudo("");
              }else{
                    panelNormal.setVisible(true);
                    panelDetallado.setVisible(false);
                    OpcionesCli.listarCliente("",1);
                    OpcionesVen.optener_adeudo("");
              }
              
          }      
            
                barraDeTitulo.setVisible(true);
                barraDeTitulo1.setVisible(false);
                this.setSize(800,550);
                this.setLocation(225,  3);
           
      }
       
    }//GEN-LAST:event_opcionesMouseClicked
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
String id_cliente;
String Cliente;
void abonar(){
    try{
                    
        String abono=cantidad.getText();        
        String saldo_anterior=txt_adeudo.getText();
        double saldo=Double.parseDouble(saldo_anterior)-Double.parseDouble(abono);
        if(saldo<0)saldo=0;
       
        clientes.ClienteCod us = new clientes.ClienteCod();        
        us.setPrimaryKey(id_cliente);
        us.setSaldo(""+saldo);
       
        
        int opcion = clientes.OpcionesCli.actualizarSaldo(us,false);
                    if (opcion != 0) {
                       
                        txt_adeudo.setText(""+saldo);
                        OpcionesVen.optener_creditos("");
                        
                        //tablaCredito.setRowSelectionInterval(pos, pos);                       
                        JOptionPane.showMessageDialog(this, "Saldo actualizado.\n\n"
                                + "Abono: "+abono
                                + "\nSaldo Anteriro: "+saldo_anterior
                                +"\nSaldo Actual: "+saldo, "Cliente:"+ Cliente, 0,
                                new  ImageIcon(getClass().getResource("/imagenes/usuarios/actualizado.png")));
                        if(saldo<0){
                            OpcionesVen.eliminar_deudas(id_cliente);
                            JOptionPane.showMessageDialog(this, "La cuenta ha sida saldado", "Cliente:"+ Cliente, 0,
                                new  ImageIcon(getClass().getResource("/imagenes/caja/ralizada.png")));
                        }
                        if(normal.isSelected())OpcionesCli.listarCliente("",1);
                        
                    }
       }
       catch(Exception e){
                    
               JOptionPane.showMessageDialog(this, "Cantidad Erronea","Error", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
           System.out.println(""+e.getMessage());
       }
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
        this.dispose();
    }//GEN-LAST:event_miminizar2ActionPerformed

    private void miminizar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miminizar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miminizar3ActionPerformed

    private void buscar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscar1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buscar1MouseClicked
 
    private void buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscar1ActionPerformed
       
    }//GEN-LAST:event_buscar1ActionPerformed

    private void buscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar1KeyReleased
      buscar1.setText(buscar1.getText().toUpperCase());
      OpcionesVen.optener_creditos( buscar1.getText());
      OpcionesVen.optener_adeudo("");
    }//GEN-LAST:event_buscar1KeyReleased

    private void buscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscar1KeyTyped
       
    }//GEN-LAST:event_buscar1KeyTyped

    private void btn_abonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abonarActionPerformed
      int fila;
      if(normal.isSelected())fila=tablaCliente.getSelectedRow();
      else fila=tablaCredito.getSelectedRow();
        if(fila>=0)   {
            
             if(!cantidad.getText().equals(""))
                abonar();
            else
                  JOptionPane.showMessageDialog(this, "Ingrese la cantidad a abonar.","Error", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));
               
       }
       
       else     
            JOptionPane.showMessageDialog(this, "Seleccione un registro.","Error", 0,
                        new ImageIcon(getClass().getResource("/imagenes/usuarios/info.png")));      
          
    }//GEN-LAST:event_btn_abonarActionPerformed

    private void txt_adeudoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_adeudoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adeudoMouseClicked

    private void txt_adeudoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_adeudoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adeudoKeyReleased

    private void txt_adeudoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_adeudoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_adeudoKeyTyped
   
    private void cantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cantidadMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadMouseClicked

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void cantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadKeyReleased

    private void cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cantidadKeyTyped
        char num = evt.getKeyChar();
                
        if (num!='.' && (num < '0' || num > '9')) {
            evt.consume();
        }
       
     
        if(num=='.'  && existes_punto(cantidad.getText())){
            evt.consume();
        }
    }//GEN-LAST:event_cantidadKeyTyped
   
    mostrarProducto  mostP;
    String id_venta="";
    String total_venta="";
            
    private void mostrar_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrar_productosActionPerformed
        int fila = tablaCredito.getSelectedRow();       
        mostrarProducto.id=id_venta;
        mostrarProducto.total=total_venta;
        
        
        if (estacerrado(mostP)) {
            mostP = new mostrarProducto();
            escritorio.add(mostP).setLocation(250, 3);
            mostP.show();
        } else {
            mostP.toFront();
        }
        
    }//GEN-LAST:event_mostrar_productosActionPerformed

    private void tablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaVentasMouseClicked
        int fila = tablaVentas.getSelectedRow();
        if(fila>=0){        
        if(mostrarProducto.visible) {
           
            OpcionesVen.optener_productos_vendidos(tablaVentas.getValueAt(fila, 0).toString());
            mostrarProducto.id_venta.setText("ID: "+tablaVentas.getValueAt(fila, 0).toString());
            mostrarProducto.total_venta.setText("TOTAL: $"+tablaVentas.getValueAt(fila, 1).toString());
            mostP.toFront();
            
        }
        else{
            id_venta=tablaVentas.getValueAt(fila, 0).toString();
            total_venta=tablaVentas.getValueAt(fila, 1).toString();
        }
        }
   
        
      
    }//GEN-LAST:event_tablaVentasMouseClicked

    private void titulo1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_titulo1MouseDragged
    mov(548,222);
    }//GEN-LAST:event_titulo1MouseDragged

    private void tituloMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tituloMouseDragged
        mov(754,225);
    }//GEN-LAST:event_tituloMouseDragged
   clientes.Cliente cli;
    private void btn_abonar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abonar1ActionPerformed
        clientes.Cliente.esVenta=false;
        if (estacerrado(cli)) {
            cli = new clientes.Cliente();
            escritorio.add(cli).setLocation(250, 3);
            cli.show();
        } else {
            cli.toFront();
        }
    }//GEN-LAST:event_btn_abonar1ActionPerformed

    private void detalladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalladoActionPerformed
       normal.setSelected(false); 
       detallado.setSelected(true);
       panelNormal.setVisible(false);
       panelDetallado.setVisible(true);
       OpcionesVen.optener_creditos("");
       OpcionesVen.optener_adeudo("");
       
         
    }//GEN-LAST:event_detalladoActionPerformed

    private void normalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_normalActionPerformed
        detallado.setSelected(false); 
        normal.setSelected(true); 
        panelNormal.setVisible(true);
        panelDetallado.setVisible(false);
        OpcionesCli.listarCliente("",1);
        OpcionesVen.optener_adeudo("");
        
        
       
    }//GEN-LAST:event_normalActionPerformed

    private void tablaCreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCreditoMouseClicked
       int fila = tablaCredito.getSelectedRow();
        if(fila>=0){        
        if(mostrarProducto.visible) {
           
            OpcionesVen.optener_productos_vendidos(tablaCredito.getValueAt(fila, 2).toString());
            mostrarProducto.id_venta.setText("ID: "+tablaCredito.getValueAt(fila, 2).toString());
            mostrarProducto.total_venta.setText("TOTAL: $"+tablaCredito.getValueAt(fila, 4).toString());
            mostP.toFront();
            
        }
        else{
            id_venta=tablaCredito.getValueAt(fila, 2).toString();
            total_venta=tablaCredito.getValueAt(fila, 4).toString();
        }
        OpcionesVen.optener_adeudo(tablaCredito.getValueAt(fila, 0).toString());
        cantidad.setVisible(true);
        fondo_cantidad.setVisible(true);
        btn_abonar.setVisible(true);
        
        id_cliente =tablaCredito.getValueAt(fila, 0).toString(); 
        Cliente= tablaCredito.getValueAt(fila, 1).toString();
        }
    }//GEN-LAST:event_tablaCreditoMouseClicked

    private void opcionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_opcionesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionesMouseEntered

    private void tablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClienteMouseClicked
       int fila = tablaCliente.getSelectedRow();
        if(fila>=0){       
                OpcionesVen.optener_adeudo(tablaCliente.getValueAt(fila, 0).toString());
                cantidad.setVisible(true);
                fondo_cantidad.setVisible(true);
                btn_abonar.setVisible(true);
                
                id_cliente =tablaCliente.getValueAt(fila, 0).toString(); 
                Cliente= tablaCliente.getValueAt(fila, 1).toString();
        }
    }//GEN-LAST:event_tablaClienteMouseClicked
 void mov(int px, int py){
     Point punto=MouseInfo.getPointerInfo().getLocation();
    
    int x=punto.x-200;
    int y=punto.y-150;
    
    int max_punto_X=px; 
    int max_punto_Y=py; 
    
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
 }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.bolivia.combo.SComboBoxBlue Opciones;
    private javax.swing.JPanel barraDeTitulo;
    private javax.swing.JPanel barraDeTitulo1;
    private javax.swing.JButton btn_abonar;
    private javax.swing.JButton btn_abonar1;
    private javax.swing.JButton buscF;
    private app.bolivia.swing.JCTextField buscar;
    private app.bolivia.swing.JCTextField buscar1;
    private app.bolivia.swing.JCTextField cantidad;
    private javax.swing.JLabel codigoL2;
    private javax.swing.JLabel codigoL3;
    private javax.swing.JLabel codigoL4;
    private javax.swing.JLabel codigoL5;
    private javax.swing.JRadioButton detallado;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton eliminarT;
    private com.toedter.calendar.JDateChooser fecha;
    private com.toedter.calendar.JDateChooser fechaFin;
    private com.toedter.calendar.JDateChooser fechaInicio;
    private javax.swing.JLabel fondo_cantidad;
    private javax.swing.JPanel frame_grafica;
    private javax.swing.JScrollPane frame_scroll1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel labels;
    private javax.swing.JButton limpiar;
    private javax.swing.JPopupMenu menu;
    private javax.swing.JButton miminizar;
    private javax.swing.JButton miminizar1;
    private javax.swing.JButton miminizar2;
    private javax.swing.JButton miminizar3;
    private javax.swing.JMenuItem mostrar_productos;
    private javax.swing.JRadioButton normal;
    private javax.swing.JTabbedPane opciones;
    private javax.swing.JPanel panelDetallado;
    private javax.swing.JPanel panelNormal;
    private javax.swing.JPanel panel_credito;
    public static javax.swing.JTable tablaCliente;
    public static javax.swing.JTable tablaCredito;
    public static javax.swing.JTable tablaVentas;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo1;
    private app.bolivia.swing.JCTextField total;
    public static app.bolivia.swing.JCTextField txt_adeudo;
    private javax.swing.JButton ventasH;
    // End of variables declaration//GEN-END:variables
}
