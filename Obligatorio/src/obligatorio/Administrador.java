/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package obligatorio;

import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gpaz1
 */
public class Administrador extends javax.swing.JFrame {

    /**
     * Creates new form Adminstrador
     */
    public void cargarUsers() {
        String sql = "SELECT * FROM PERSONAS join PERMISOS on PERSONAS.user_id=PERMISOS.user_id;";

        String columnas[] = {"user_id", "nombres","apellidos","aplicacion","rol","estado"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas); 

        String[] registros = new String[6];
        try {
            Statement stat = conec.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("user_id");
                registros[1] = rs.getString("nombres");
                registros[2] = rs.getString("apellidos");
                registros[3] = rs.getString("PERMISOS.app_id");
                registros[4] = rs.getString("PERMISOS.rol_neg_id");
                registros[5] = rs.getString("PERMISOS.estado");

                modelo.addRow(registros);
            }
            tblUser.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Loggin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Administrador() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarUsers();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setText("USUARIOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        tblUser.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id_number", "Nombre", "Apellido", "Rol", "Fecha_Ingreso", "Contacto"
            }
        ));
        jScrollPane1.setViewportView(tblUser);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 660, 140));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel2.setText("Administradores");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo-login-web.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 400));
    }// </editor-fold>//GEN-END:initComponents
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblUser;
    // End of variables declaration//GEN-END:variables
    Conexion_MySql conexion = new Conexion_MySql();
    Connection conec = conexion.ConectarBasedeDatos();
}
