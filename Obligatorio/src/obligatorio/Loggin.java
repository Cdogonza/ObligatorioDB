/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package obligatorio;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gpaz1
 */
public class Loggin extends javax.swing.JFrame {

    /**
     * Creates new form Loggin
     */
    public static String user = "";
    public static String rolUser = "";

    public Loggin() {
        initComponents();
        this.setLocationRelativeTo(null);
        //this.hidePass.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUser = new javax.swing.JTextField();
        btnloggin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(548, 338));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUserMouseClicked(evt);
            }
        });
        getContentPane().add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 220, 40));

        btnloggin.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        btnloggin.setText("INICIAR SESION");
        btnloggin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlogginActionPerformed(evt);
            }
        });
        getContentPane().add(btnloggin, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 130, 40));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Crear Usuario Nuevo");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setText("Contraseña:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("Usuario:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));
        getContentPane().add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 220, 40));

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        jLabel4.setText("Bienvenido");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/kindpng_2005246.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 40, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/pngfind.com-user-icon-png-938537.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 40, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo-login-web.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static String hashPwd(String pass){
        String passwordToHash = pass;
        String generatedPassword = null;

        try 
        {
          // Create MessageDigest instance for MD5
          MessageDigest md = MessageDigest.getInstance("MD5");

          // Add password bytes to digest
          md.update(passwordToHash.getBytes());

          // Get the hash's bytes
          byte[] bytes = md.digest();

          // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
          }

          // Get complete hashed password in hex format
          generatedPassword = sb.toString();
          return generatedPassword;
        } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
        }
        return generatedPassword;
    }
    private void btnlogginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlogginActionPerformed
        if (txtUser.getText().isEmpty() || txtPass.getText().isEmpty()) {
            System.out.println("DEBE INGRESAR USUARIO Y PASSWORD");
        } else {
            String user = txtUser.getText().toUpperCase();
            String pass = hashPwd(txtPass.getText());
            try {
                Statement stat = conec.createStatement();
                ResultSet consulta = stat.executeQuery("select * from PERSONAS join PERMISOS on PERSONAS.user_id=PERMISOS.user_id where PERSONAS.user_id='" + user + "'");
                if (consulta.next()) {
                    do {
                        String pas = consulta.getString("hashpwd");
                        if (pass.equals(pas)) {
                            this.user = consulta.getString("nombres") + " " + consulta.getString("apellidos");
                            this.rolUser = consulta.getString("rol_neg_id");                            
                            this.dispose();
                            Principal ppal = new Principal();
                            ppal.setVisible(true);
                            
                        } else {
                            JOptionPane.showMessageDialog(null,  "LOS DATOS INGRESADOS NO SON CORRECTOS","ERROR", JOptionPane.ERROR_MESSAGE);
                            System.out.println(pas);
                            System.out.println(pass);
                        }

                    } while (consulta.next());

                } else {
                    JOptionPane.showMessageDialog(null, "USUARIO NO EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Loggin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_btnlogginActionPerformed

    private void txtUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       this.dispose();
        Formulario_Datos alta = new Formulario_Datos();
       alta.setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnloggin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
    Conexion_MySql conexion = new Conexion_MySql();
    Connection conec = conexion.ConectarBasedeDatos();
}
