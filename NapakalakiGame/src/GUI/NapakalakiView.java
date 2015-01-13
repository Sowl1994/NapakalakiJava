/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.swing.JOptionPane;
import napakalakiGame.CombatResult;

/**
 *
 * @author fran
 */
public class NapakalakiView extends javax.swing.JFrame {
    private napakalakiGame.Napakalaki napakalakiModel;
    /**
     * Creates new form NapakalakiView
     */
    public NapakalakiView() {
        initComponents();
        
    }
    
    public void setNapakalaki(napakalakiGame.Napakalaki n){
        napakalakiModel = n;
        
        playerView1.setNapakalaki(n);
        playerView1.setPlayer(n.getCurrentPlayer());
        
        monsterView2.setMonster(n.getCurrentMonster());
        monsterView2.setVisible(false);
        
        repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        meetTheMonster = new javax.swing.JButton();
        combatirrr = new javax.swing.JButton();
        turnoo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        playerView1 = new GUI.PlayerView();
        monsterView2 = new GUI.MonsterView();
        resultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        meetTheMonster.setText("Meet the monster");
        meetTheMonster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meetTheMonsterActionPerformed(evt);
            }
        });

        combatirrr.setText("Combat");
        combatirrr.setEnabled(false);
        combatirrr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combatirrrActionPerformed(evt);
            }
        });

        turnoo.setText("Next Turn");
        turnoo.setEnabled(false);
        turnoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turnooActionPerformed(evt);
            }
        });

        jLabel1.setText("Resultado del combate");

        resultado.setText("      ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(29, 29, 29)
                        .addComponent(resultado))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(meetTheMonster)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combatirrr)
                        .addGap(18, 18, 18)
                        .addComponent(turnoo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(playerView1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monsterView2, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playerView1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monsterView2, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(resultado))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meetTheMonster)
                    .addComponent(combatirrr)
                    .addComponent(turnoo))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void meetTheMonsterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meetTheMonsterActionPerformed
        monsterView2.setVisible(true);
        combatirrr.setEnabled(true);
        meetTheMonster.setEnabled(false);
    }//GEN-LAST:event_meetTheMonsterActionPerformed

    private void combatirrrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combatirrrActionPerformed
        CombatResult result = napakalakiModel.getCurrentPlayer().combat(napakalakiModel.getCurrentMonster()); 
        
        switch (result) {
            case Win:  resultado.setText("Has derrotado al monstruo. Se te aplican los premio");
                playerView1.setPlayer(napakalakiModel.getCurrentPlayer());
                break;
            case WinAndWinGame: JOptionPane.showMessageDialog(null,playerView1.playerModel.getName()+", Has ganado el combate y el juego. ¡Enhorabuena!");
                System.exit(0);
                break;
            case Lose:  resultado.setText("Has sido derrotado. Ahora se te aplicará el mal rollo del monstruo.");
                playerView1.setPlayer(napakalakiModel.getCurrentPlayer());
                break;
            case LoseAndDie:  resultado.setText("Has sido derrotado y has muerto.");
                playerView1.setPlayer(napakalakiModel.getCurrentPlayer());
                break;
            case LoseAndEscape:  resultado.setText("Has logrado escapar del combate a salvo.");
                playerView1.setPlayer(napakalakiModel.getCurrentPlayer());
                break;
            
            default: resultado.setText("Error en el combate.");
                //playerView1.setPlayer(napakalakiModel.getCurrentPlayer());
                break;
        }
        
        turnoo.setEnabled(true);
        combatirrr.setEnabled(false);
        
        // Necesario para ver los tesoros que se ganan en el combate. 
//        if(napakalakiModel.nextTurn()){
//            playerView1.enableMakeVisible();
//        }
//        playerView.enableDiscard();
//        playerView.disableBuyLevels();
//        playerView.setPlayer(napakalakiModel.getCurrentPlayer());
//        
//        // Ajuste de botones. 
//        combatirrr.setEnabled(false);
//        if(result != CombatResult.WinAndWinGame){
//            turnoo.setEnabled(true);
//        }
//        else{
//            playerView.disableDiscard();
//            playerView.disableMakeVisible();
//        }
        repaint();
    }//GEN-LAST:event_combatirrrActionPerformed

    private void turnooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turnooActionPerformed
      if (napakalakiModel.nextTurn()){
            napakalakiModel.nextTurn();
            //Ajuste de botones
            turnoo.setEnabled(false);
            monsterView2.setVisible(false);
            
            this.resultado.setText("");
            monsterView2.setMonster(napakalakiModel.getCurrentMonster()); 
            playerView1.setPlayer(napakalakiModel.getCurrentPlayer());
            
            //Actualizando pantalla
            repaint();
            meetTheMonster.setEnabled(true);
      }else{
          JOptionPane.showMessageDialog(null,"No se puede pasar de turno. Revisa el mal rollo y el numero de tesoros.");
      }
            
    }//GEN-LAST:event_turnooActionPerformed

    /**
     * @param args the command line arguments
     */
    public void showView() {
        this.setVisible(true);
    }
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(NapakalakiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(NapakalakiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(NapakalakiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(NapakalakiView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new NapakalakiView().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton combatirrr;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton meetTheMonster;
    private GUI.MonsterView monsterView2;
    private GUI.PlayerView playerView1;
    private javax.swing.JLabel resultado;
    private javax.swing.JButton turnoo;
    // End of variables declaration//GEN-END:variables
}
