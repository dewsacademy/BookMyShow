/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmyshowui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.*;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Sanjib
 */
public class BookSeat extends javax.swing.JFrame {

    /**
     * Creates new form BookSeat
     */
    
    JFrame jFrame;
    public static String screenId;
    
    public BookSeat() throws ClassNotFoundException {
        initComponents();
        
        BookSeats bookSeats = new BookSeats();
        
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JPanel jpSeatPrice = new JPanel();
        jpSeatPrice.setSize(200, 200);
        c.gridx = 0;
        c.gridy = 0;
        getContentPane().add(jpSeatPrice, c);
        
        JPanel jpAllSeats = new JPanel();
        jpSeatPrice.setSize(500, 500);
        c.gridx = 1;
        c.gridy = 0;
        getContentPane().add(jpAllSeats, c);
        
        UUID sId = UUID.fromString(screenId);
        
        ResultSet getSeatPrice = bookSeats.showSeatPrice(sId);
        
        ResultSet allSeats = bookSeats.getAllSeats(sId);
        
        try{
            
            while(getSeatPrice.next())
            {
                JLabel seatPrice = new JLabel(getSeatPrice.getString("SeatType") + ": Rs." + getSeatPrice.getString("Price"));
//                c.gridx = 1;
//                c.gridy = 0;
                jpSeatPrice.add(seatPrice);
            }
            
            int i = 0;
            int j = 1;
            int count = 0;
            while(allSeats.next())
            {  
                count++;
                JButton jb = new JButton(String.valueOf(allSeats.getString("SeatNumber")));
                if(count % 5 == 0)
                {
                    c.gridx = j++;
                    i = 0;
                    c.gridy = i++;
                }
                else
                {
                    c.gridx = j;
                    c.gridy = i++;
                }
                //c.gridx = 0;
                //c.gridy = i++;
                jpAllSeats.add(jb, c);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 824, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BookSeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookSeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookSeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookSeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new BookSeat().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(BookSeat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
                
        
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
