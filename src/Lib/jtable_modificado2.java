
package Lib;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;


public class jtable_modificado2 extends JTable {
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex){
        
      Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
      //component.setBackground(Color.WHITE);
      //component.setForeground(Color.BLACK);
      //System.out.println(""+getValueAt(rowIndex, 4));
     
     
      if(getValueAt(rowIndex, 4).equals("0")){
         component.setBackground(Color.GRAY);
         //component.setForeground(Color.BLACK); 
      }
      
      return component;
    }
}
