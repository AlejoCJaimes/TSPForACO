package Frames.tsp.ACO;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Frame_Grafo extends javax.swing.JFrame {

    private static final int SIZE = 400;
    private int a = SIZE / 2;
    private int b = a;
    private int r = 4 * SIZE / 5;
    private String[] nodos;
    private String[] distancias;
    private ArrayList<coordenada_aux_grafo> ubicacionNodos;

    public Frame_Grafo() {
        initComponents();
        jLabel5.setOpaque(true);
        jLabel5.setBackground(new java.awt.Color(59, 68, 113));
        this.setLocationRelativeTo(null);
    }
    
    public void setNodos( String[] ciudades){
        nodos = new String[ciudades.length + 1];
        for (int i = 0; i < ciudades.length; i++) {
            nodos[i] = ciudades[i];
        }
        nodos[nodos.length - 1] = ciudades[0];
    }
    public void setDistancias( ArrayList<String> distancia){
        distancias = new String[distancia.size() + 1];
        for (int i = 0; i < distancia.size(); i++) {
            distancias[i] = distancia.get(i);
        }
        distancias[distancias.length - 1] = distancia.get(0);
    }
    public void setDistanciaTotal( double distancia){
        jLabel1.setText(jLabel1.getText() + String.valueOf(distancia));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 51));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Multimedia/tsp/ACO/minimizar.png"))); // NOI18N
        jLabel5.setToolTipText("Minimizar");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel5MouseMoved(evt);
            }
        });
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Distancia Total Recorrida: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 645, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 373, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseMoved
        jLabel5.setOpaque(true);
        jLabel5.setBackground(new java.awt.Color(59, 68, 113));
    }//GEN-LAST:event_jLabel5MouseMoved

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        jLabel5.setOpaque(true);
        jLabel5.setBackground(new java.awt.Color(27, 236, 10));
    }//GEN-LAST:event_jLabel5MouseExited
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Frame_Grafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Grafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Grafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Grafo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Frame_Grafo().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    public void paint(Graphics g) {
        super.paint(g);
        ubicacionNodos = new ArrayList<>();
        a = getWidth() / 2;
        b = getHeight() / 2;
        int m = Math.min(a, b);
        r = 4 * m / 5;
        int r2 = Math.abs(m - r) / 2;
        int aux1, aux2;
        //g2d.drawOval(a - r, b - r, 2 * r, 2 * r);        
        for (int i = 0; i < nodos.length - 1; i++) {          
            double t = 2 * Math.PI * i / nodos.length - 1;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t));
            g.setColor(new java.awt.Color(82, 250, 57));
            g.fillOval(x - r2, y - r2, 2 * r2, 2 * r2);            
            g.setColor(Color.white); 
            g.drawString((nodos[i] + " -> " + distancias[i]), x - r2, y - r2);
            aux1 = x;
            aux2 = y;
            ubicacionNodos.add(new coordenada_aux_grafo(aux1, aux2, nodos[i]));
            
            g.fillOval(aux1, aux2, 5, 5);
        }
        g.setColor(Color.white);
        graficarAristas(g);
    }

    private int posicionLetra(String letter) {
        for (int i = 0; i < nodos.length; i++) {
            if (ubicacionNodos.get(i).getLetter().equals(letter)) {
                return (i);
            }
        }
        return (-1);
    }

    private void graficarAristas(Graphics g) {
        int posA;
        int posB;
        for (int i = 0; i < (nodos.length-1); i++) {
            posA = posicionLetra(nodos[i]);
            posB = posicionLetra(nodos[i + 1]);
            g.drawLine(ubicacionNodos.get(posA).getX(), ubicacionNodos.get(posA).getY(),
                    ubicacionNodos.get(posB).getX(), ubicacionNodos.get(posB).getY());
        }

    }
}
