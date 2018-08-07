
package Lib;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class jtable_modificado extends JTable {
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex){
        
      Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
      //component.setBackground(Color.WHITE);
      //component.setForeground(Color.BLACK);
      //System.out.println(""+getValueAt(rowIndex, 4));
     
     if(Double.parseDouble(""+getValueAt(rowIndex, 4))<=3){
         component.setBackground(Color.ORANGE);
         component.setVisible(false);
         
         //component.setForeground(Color.BLACK); 
      }
     
      if(Double.parseDouble(""+getValueAt(rowIndex, 4))<=0.1){
         component.setBackground(Color.GRAY);
         //component.setForeground(Color.BLACK); 
      }
      
      
      return component;
    }
}
