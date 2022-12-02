/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package obligatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gpaz1
 */
public class Formulario_Datos extends javax.swing.JFrame {

    public String fechaActual() {

        String timestamp = ZonedDateTime.now(ZoneId.of("America/Montevideo"))
                .format(DateTimeFormatter.ofPattern("MM-dd-yyy"));
        String fecha = timestamp;
        return fecha;
    }

    /**
     * Creates new form Formulario_Datos
     */
    public void limpiar() {
        ci.setText("");
        name.setText("");
        apellido.setText("");
        addres.setText("");
        ciudades.setSelectedIndex(0);
        departamentos.setSelectedIndex(0);
        hashpwd.setText("");

    }

    public void ingresoFormilario() {
        try {

            PreparedStatement pst = conec.prepareStatement("INSERT INTO PERSONAS(user_id,nombres,apellidos,"
                    + "direccion,ciudad,departamento,hashpwd) VALUES(?,?,?,?,?,?,?)");

            pst.setString(1, ci.getText());
            pst.setString(2, name.getText());
            pst.setString(3, apellido.getText());
            pst.setString(4, addres.getText());
            pst.setString(5, departamentos.getSelectedItem().toString());
            pst.setString(6, ciudades.getSelectedItem().toString());
            pst.setString(7, Loggin.hashPwd(hashpwd.getText()));
            pst.executeUpdate();
            Statement stat = conec.createStatement();
            ResultSet consulta = stat.executeQuery("SELECT obligatorioDB.PREGUNTAS.preg_id, obligatorioDB.PERSONAS.user_id "
                    + "FROM obligatorioDB.PERSONAS INNER JOIN obligatorioDB.PREGUNTAS ON preguntas='" + preguntasSeguridad.getSelectedItem().toString() + "' AND obligatorioDB.PERSONAS.user_id='" + ci.getText() + "' ");
            PreparedStatement preg = conec.prepareStatement("INSERT INTO PERSONAS_PREGUNTAS(user_id,preg_id,respuesta) VALUES(?,?,?)");
            if (consulta.next()) {
                preg.setString(1, consulta.getString("user_id"));
                preg.setString(2, consulta.getString("preg_id"));
                preg.setString(3, respuestaPregunta.getText());
                preg.executeUpdate();

            }
            Statement obtenerRolSeleccionado = conec.createStatement();
            ResultSet rol = obtenerRolSeleccionado.executeQuery("SELECT obligatorioDB.ROLES_APLICATIVO.app_id FROM obligatorioDB.ROLES_APLICATIVO WHERE descripcion = '" + comboRoles.getSelectedItem().toString() + "';");
            String rolDato = "";
            if (rol.next()) {
                rolDato = rol.getString("app_id");
            }
            String rolCom = comboRoles.getSelectedItem().toString();
            PreparedStatement permiso = conec.prepareStatement("INSERT INTO obligatorioDB.PERMISOS (user_id,app_id,rol_neg_id,"
                    + "fecha_solicitud,fecha_autorizacion,estado) VALUES(?,?,?,?,?,?)");
            permiso.setString(1, ci.getText());
            permiso.setString(2, rolDato);
            permiso.setString(3, rolCom);
            permiso.setString(4, fechaActual());
            permiso.setString(5, "sinAutorizar");
            permiso.setString(6, "pendiente");
            permiso.executeUpdate();

            JOptionPane.showMessageDialog(null, "ALTA CORRECTA");
            limpiar();
            this.dispose();
            Loggin login = new Loggin();
            login.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Datos Erroneos Reintene", "", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
    }

    public void cargarPreguntas() {
        String sql = "SELECT * FROM PREGUNTAS ";

        try {
            Statement stat = conec.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                preguntasSeguridad.addItem(rs.getString("preguntas"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Loggin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarRoles() {
        String sql = "SELECT * FROM ROLES_APLICATIVO";

        try {
            Statement stat = conec.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                comboRoles.addItem(rs.getString("descripcion"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(Loggin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Formulario_Datos() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargarPreguntas();
        cargarRoles();
        System.out.println(fechaActual());
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
        name = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        addres = new javax.swing.JTextField();
        ci = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ciudades = new javax.swing.JComboBox<>();
        departamentos = new javax.swing.JComboBox<>();
        hashpwd = new javax.swing.JPasswordField();
        preguntasSeguridad = new javax.swing.JComboBox<>();
        respuestaPregunta = new javax.swing.JTextField();
        comboRoles = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(465, 711));
        setMinimumSize(new java.awt.Dimension(465, 711));
        setPreferredSize(new java.awt.Dimension(465, 711));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("FORMULARIO DE DATOS PERSONALES");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        getContentPane().add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 200, 30));
        getContentPane().add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 200, 30));
        getContentPane().add(addres, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 200, 30));
        getContentPane().add(ci, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 200, 30));

        jLabel2.setText("NOMBRE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel3.setText("APELLIDO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel4.setText("CEDULA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jLabel5.setText("DIRECCION");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, -1, -1));

        jLabel6.setText("CONTRASEÑA");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        jButton1.setText("ENVIAR DATOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 600, 290, 60));

        jLabel7.setText("ROL");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jLabel8.setText("DEPARTAMENTO");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        ciudades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Ciudad", "Artigas", "Bella Unión", "Baltasar Brum", "Bernabé Rivera", "Colonia Palma", "Montes Quintela", "Paso Campamento", "Sequeira", "Tomás Gomensoro", "Atlántida", "Barra de Carrasco", "Canelones", "Ciudad de la Costa", "Colonia Nicolich", "La Paz", "Las Piedras", "Pando", "Paso Carrasco", "Santa Lucía", "Aceguá", "Fraile Muerto", "Isidoro Noblía", "Lago Merín", "Melo", "Plácido Rosas", "Río Branco", "Tupambaé", "Carmelo", "Colonia de Sacramento", "Juan Lacaze", "Nueva Helvecia", "Nueva Palmira", "Rosario", "Tarariras", "Durazno", "Sarandí del Yí", "Villa del Carmen", "Andresito", "Área Rural", "Ismael Cortinas", "La Casilla", "Trinidad", "25 de Agosto", "25 de Mayo", "Cardal", "Casupá", "Cerro Colorado", "Florida", "Fray Marcos", "Nico Pérez", "Sarandí Grande", "Colón", "Illescas", "José Batlle y Ordóñez", "José Pedro Varela", "Mariscala", "Minas", "Pirarajá", "Solís de Mataojo", "Zapicán", "Aiguá", "Cerro Pelado", "Gregorio Aznárez", "Hipódromo", "José Ignacio", "La Barra", "Maldonado", "Pan de Azúcar", "Pinares-Las Delicias", "Piriápolis", "Portezuelo", "Punta del Este", "San Carlos", "San Rafael-El placer", "Abayubá", "Montevideo", "Montevideo Rural", "Pajas Blancas", "Santiago Vázquez", "Barrio Norte", "Guichón", "Nuevo Paysandú", "Paysandú", "Porvenir", "Piedras Coloradas", "Quebracho", "Sam Félix", "Tambores", "Algorta", "Bellaco", "Colonia Ofir", "El Ombú", "Gartenal", "Grecco", "General Borges", "Fray Bentos", "Los Arrayanes", "Las Cañas", "Menafra", "Nuevo Berlín", "Paso de los Mellizos", "San Javier", "Sarandí de Navarro", "Villa María", "YoungLa Pedrera", "Masoller", "Minas de Corrales", "Rivera", "Santa Teresa", "Tranqueras", "Vichadero", "Chuy", "Castillos", "Lascano", "La Paloma", "Albisu", "Belén", "Biassini", "Colonia 18 de Julio", "Colonia Itapebí", "Constitución", "Fernández", "Garibaldi", "Pueblo Lavalleja", "Rincón de Valentín", "Salto", "San Antonio", "Saucedo", "Sarandí del Arapey", "Termas del Daymán", "Ciudad de Plata", "Ecilda Paullier", "Libertad", "Puntas de Valdez", "Rafael Perazza", "Rodríguez", "San José de Mayo", "Cardona", "Chacras de Dolores", "Dolores", "José Enrique Rodó", "La Pedrera", "Mercedes", "Palmitas", "Villa Soriano", "Achar", "Balneario Iporá", "Cuchilla de Peralta", "Curtina", "Cuchilla del Ombú", "Clara", "Chamberlain", "Cardozo", "Las Toscas", "Laureles", "Paso de toros", "Paso Bonilla", "Paso del Cerro", "Piedra Sola", "Punta de Cinco Sauces", "Pueblo de Barro", "Rincón del Bonete", "Sauce de Batoví", "San Gregorio de Polanco", "Tacuarembó", "Tambores", "Villa Ansina", "Cerro Chato", "Ejido de Treinta y Tres", "General Enrique Martínez", "Santa Clara de Olimar", "Treinta y Tres", "Vergara", "Villa Sara" }));
        ciudades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ciudadesActionPerformed(evt);
            }
        });
        getContentPane().add(ciudades, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 200, 30));

        departamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Seleccione Departamento", "Artigas", " Canelones", " Cerro Largo", " Colonia", " Durazno", " Flores", " Florida", " Lavalleja", " Maldonado", " Montevido", " Paysandú", " Río Negro", " Rivera", " Rocha", " Salto", " San José", " Soriano", " Tacuarembo", " Treinta y Tres" }));
        departamentos.setToolTipText("");
        getContentPane().add(departamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 200, 30));
        getContentPane().add(hashpwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 200, 30));

        getContentPane().add(preguntasSeguridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 200, 30));
        getContentPane().add(respuestaPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 540, 210, 30));

        getContentPane().add(comboRoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 200, 30));

        jLabel9.setText("CIUDAD");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void ciudadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ciudadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ciudadesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        if (ci.getText().isEmpty() || name.getText().isEmpty() || apellido.getText().isEmpty() || addres.getText().isEmpty()
//                || hashpwd.getText().isEmpty() || respuestaPregunta.getText().isEmpty()) {
//            
//        } else {
        ///   System.out.println("algo salio mal");
        ///   }
        ingresoFormilario();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addres;
    private javax.swing.JTextField apellido;
    private javax.swing.JTextField ci;
    private javax.swing.JComboBox<String> ciudades;
    private javax.swing.JComboBox<String> comboRoles;
    private javax.swing.JComboBox<String> departamentos;
    private javax.swing.JPasswordField hashpwd;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField name;
    private javax.swing.JComboBox<String> preguntasSeguridad;
    private javax.swing.JTextField respuestaPregunta;
    // End of variables declaration//GEN-END:variables
Conexion_MySql conexion = new Conexion_MySql();
    Connection conec = conexion.ConectarBasedeDatos();
}
