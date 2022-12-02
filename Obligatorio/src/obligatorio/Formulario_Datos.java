/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package obligatorio;

import java.awt.event.ItemEvent;
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
import javax.swing.DefaultComboBoxModel;
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

        name = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        addres = new javax.swing.JTextField();
        ci = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ciudades = new javax.swing.JComboBox<>();
        departamentos = new javax.swing.JComboBox<>();
        hashpwd = new javax.swing.JPasswordField();
        preguntasSeguridad = new javax.swing.JComboBox<>();
        respuestaPregunta = new javax.swing.JTextField();
        comboRoles = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(465, 711));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        getContentPane().add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 200, 30));
        getContentPane().add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 200, 30));
        getContentPane().add(addres, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 200, 30));
        getContentPane().add(ci, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 200, 30));

        jButton1.setText("ENVIAR DATOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 600, 290, 60));

        ciudades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Ciudad", "Artigas", "Bella Unión", "Baltasar Brum", "Bernabé Rivera", "Colonia Palma", "Montes Quintela", "Paso Campamento", "Sequeira", "Tomás Gomensoro", "Atlántida", "Barra de Carrasco", "Canelones", "Ciudad de la Costa", "Colonia Nicolich", "La Paz", "Las Piedras", "Pando", "Paso Carrasco", "Santa Lucía", "Aceguá", "Fraile Muerto", "Isidoro Noblía", "Lago Merín", "Melo", "Plácido Rosas", "Río Branco", "Tupambaé", "Carmelo", "Colonia de Sacramento", "Juan Lacaze", "Nueva Helvecia", "Nueva Palmira", "Rosario", "Tarariras", "Durazno", "Sarandí del Yí", "Villa del Carmen", "Andresito", "Área Rural", "Ismael Cortinas", "La Casilla", "Trinidad", "25 de Agosto", "25 de Mayo", "Cardal", "Casupá", "Cerro Colorado", "Florida", "Fray Marcos", "Nico Pérez", "Sarandí Grande", "Colón", "Illescas", "José Batlle y Ordóñez", "José Pedro Varela", "Mariscala", "Minas", "Pirarajá", "Solís de Mataojo", "Zapicán", "Aiguá", "Cerro Pelado", "Gregorio Aznárez", "Hipódromo", "José Ignacio", "La Barra", "Maldonado", "Pan de Azúcar", "Pinares-Las Delicias", "Piriápolis", "Portezuelo", "Punta del Este", "San Carlos", "San Rafael-El placer", "Abayubá", "Montevideo", "Montevideo Rural", "Pajas Blancas", "Santiago Vázquez", "Barrio Norte", "Guichón", "Nuevo Paysandú", "Paysandú", "Porvenir", "Piedras Coloradas", "Quebracho", "Sam Félix", "Tambores", "Algorta", "Bellaco", "Colonia Ofir", "El Ombú", "Gartenal", "Grecco", "General Borges", "Fray Bentos", "Los Arrayanes", "Las Cañas", "Menafra", "Nuevo Berlín", "Paso de los Mellizos", "San Javier", "Sarandí de Navarro", "Villa María", "YoungLa Pedrera", "Masoller", "Minas de Corrales", "Rivera", "Santa Teresa", "Tranqueras", "Vichadero", "Chuy", "Castillos", "Lascano", "La Paloma", "Albisu", "Belén", "Biassini", "Colonia 18 de Julio", "Colonia Itapebí", "Constitución", "Fernández", "Garibaldi", "Pueblo Lavalleja", "Rincón de Valentín", "Salto", "San Antonio", "Saucedo", "Sarandí del Arapey", "Termas del Daymán", "Ciudad de Plata", "Ecilda Paullier", "Libertad", "Puntas de Valdez", "Rafael Perazza", "Rodríguez", "San José de Mayo", "Cardona", "Chacras de Dolores", "Dolores", "José Enrique Rodó", "La Pedrera", "Mercedes", "Palmitas", "Villa Soriano", "Achar", "Balneario Iporá", "Cuchilla de Peralta", "Curtina", "Cuchilla del Ombú", "Clara", "Chamberlain", "Cardozo", "Las Toscas", "Laureles", "Paso de toros", "Paso Bonilla", "Paso del Cerro", "Piedra Sola", "Punta de Cinco Sauces", "Pueblo de Barro", "Rincón del Bonete", "Sauce de Batoví", "San Gregorio de Polanco", "Tacuarembó", "Tambores", "Villa Ansina", "Cerro Chato", "Ejido de Treinta y Tres", "General Enrique Martínez", "Santa Clara de Olimar", "Treinta y Tres", "Vergara", "Villa Sara" }));
        ciudades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ciudadesActionPerformed(evt);
            }
        });
        getContentPane().add(ciudades, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 200, 30));

        departamentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione Departamento", "Artigas", "Canelones", "Cerro Largo", "Colonia", "Durazno", "Flores", "Florida", "Lavalleja", "Maldonado", "Montevideo", "Paysandú", "Río Negro", "Rivera", "Rocha", "Salto", "San José", "Soriano", "Tacuarembó", "Treinta y Tres" }));
        departamentos.setToolTipText("");
        departamentos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                departamentosItemStateChanged(evt);
            }
        });
        getContentPane().add(departamentos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 200, 30));
        getContentPane().add(hashpwd, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 200, 30));

        getContentPane().add(preguntasSeguridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 490, 200, 30));
        getContentPane().add(respuestaPregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 380, 30));

        getContentPane().add(comboRoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, 200, 30));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setText("FORMULARIO DE DATOS PERSONALES");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setText("CÉDULA");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, -1, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setText("NOMBRE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 125, -1, -1));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("APELLIDO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 175, -1, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel5.setText("DIRECCION");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 225, -1, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel8.setText("DEPARTAMENTO");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 275, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel9.setText("CIUDAD");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 325, -1, -1));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel7.setText("ROL");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 375, -1, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel6.setText("CONTRASEÑA");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 435, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/856f31d9f475501c7552c97dbe727319.jpg"))); // NOI18N
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 710));

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

    private void departamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_departamentosItemStateChanged
         if (evt.getStateChange()== ItemEvent.SELECTED){
           if (this.departamentos.getSelectedIndex()>0){
               this.ciudades.setModel(new DefaultComboBoxModel(this.getCiudades(this.departamentos.getSelectedItem().toString())));
           }
       }
    }//GEN-LAST:event_departamentosItemStateChanged

     public String [] getCiudades (String departamento){
        String [] ciudades = new String[10]; 
        if (departamento.equalsIgnoreCase("Artigas")){
            ciudades[0]= "Artigas"; 
            ciudades[1]= "Bella Unión"; 
            ciudades[2]= "Baltasar Brum"; 
            ciudades[3]= "Bernabé Rivera"; 
            ciudades[4]= "Colonia Palma"; 
            ciudades[5]= "Montes Quintela"; 
            ciudades[6]= "Paso Campamento"; 
            ciudades[7]= "Sequeira"; 
            ciudades[8]= "Tomás Gomensoro"; 
        }
        else if (departamento.equalsIgnoreCase("Canelones")){
            ciudades[0]= "Atlántida"; 
            ciudades[1]= "Barra de Carrasco"; 
            ciudades[2]= "Canelones"; 
            ciudades[3]= "Ciudad de la Costa"; 
            ciudades[4]= "Colonia Nicolich"; 
            ciudades[5]= "La Paz"; 
            ciudades[6]= "Las Piedras"; 
            ciudades[7]= "Pando"; 
            ciudades[8]= "Paso Carrasco"; 
            ciudades[9]= "Santa Lucía"; 
        }
         if (departamento.equalsIgnoreCase("Cerro Largo")){
            ciudades[0]= "Aceguá"; 
            ciudades[1]= "Fraile Muerto"; 
            ciudades[2]= "Isidoro Noblía"; 
            ciudades[3]= "Lago Merín"; 
            ciudades[4]= "Melo"; 
            ciudades[5]= "Plácido Rosas"; 
            ciudades[6]= "Río Branco"; 
            ciudades[7]= "Tupambaé"; 
            
        } if (departamento.equalsIgnoreCase("Colonia")){
            ciudades[0]= "Carmelo"; 
            ciudades[1]= "Colonia de Sacramento"; 
            ciudades[2]= "Juan Lacaze"; 
            ciudades[3]= "Nueva Helvecia";
            ciudades[4]= "Nueva Palmira"; 
            ciudades[5]= "Rosario";
            ciudades[6]= "Tarariras";
            
        } if (departamento.equalsIgnoreCase("Durazno")){
            ciudades[0]= "Durazno"; 
            ciudades[1]= "Sarandí del Yí"; 
            ciudades[2]= "Villa del Carmen"; 
            
            
        } if (departamento.equalsIgnoreCase("Flores")){
            ciudades[0]= "Andresito"; 
            ciudades[1]= "Área Rural"; 
            ciudades[2]= "Ismael Cortinas"; 
            ciudades[3]= "La Casilla"; 
            ciudades[4]= "Trinidad"; 
          
        } if (departamento.equalsIgnoreCase("Florida")){
            ciudades[0]= "25 de Agosto"; 
            ciudades[1]= "25 de Mayo"; 
            ciudades[2]= "Cardal"; 
            ciudades[3]= "Casupá"; 
            ciudades[4]= "Cerro Colorado"; 
            ciudades[5]= "Florida"; 
            ciudades[6]= "Fray Marcos"; 
            ciudades[7]= "Nico Pérez"; 
            ciudades[8]= "Sarandí Grande"; 
  
        } if (departamento.equalsIgnoreCase("Lavalleja")){
            ciudades[0]= "Colón"; 
            ciudades[1]= "Illescas"; 
            ciudades[2]= "José Batlle y Ordóñez"; 
            ciudades[3]= "José Pedro Varela"; 
            ciudades[4]= "Mariscala"; 
            ciudades[5]= "Minas"; 
            ciudades[6]= "Pirarajá"; 
            ciudades[7]= "Solís de Mataojo"; 
            ciudades[8]= "Zapicán"; 
           
        } if (departamento.equalsIgnoreCase("Maldonado")){
            ciudades[0]= " Aiguá"; 
            ciudades[1]= "Cerro Pelado"; 
            ciudades[2]= "Gregorio Aznárez"; 
            ciudades[3]= "Hipódromo"; 
            ciudades[4]= "José Ignacio"; 
            ciudades[5]= "La Barra"; 
            ciudades[6]= "Maldonado"; 
            ciudades[7]= "Pan de Azúcar"; 
            ciudades[8]= "Pinares-Las Delicias"; 
            ciudades[9]= "Piriápolis"; 
        } if (departamento.equalsIgnoreCase("Montevideo")){
            ciudades[0]= "Aguada"; 
            ciudades[1]= "Belvedere"; 
            ciudades[2]= "Carrasco"; 
            ciudades[3]= "Ciudad Vieja"; 
            ciudades[4]= "Flor de Maroñas"; 
            ciudades[5]= "La Blanqueada"; 
            ciudades[6]= "Manga"; 
            ciudades[7]= "Peñarol"; 
            ciudades[8]= "Punta Carretas"; 
            ciudades[9]= "Sayago"; 
        } if (departamento.equalsIgnoreCase("Paysandú")){
            ciudades[0]= "Barrio Norte"; 
            ciudades[1]= "Guichón"; 
            ciudades[2]= "Nuevo Paysandú"; 
            ciudades[3]= "Paysandú"; 
            ciudades[4]= "Porvenir"; 
            ciudades[5]= "Piedras Coloradas"; 
            ciudades[6]= "Quebracho"; 
            ciudades[7]= "Sam Félix"; 
            ciudades[8]= "Tambores"; 
  
        } if (departamento.equalsIgnoreCase("Río Negro")){
            ciudades[0]= "Algorta"; 
            ciudades[1]= "Bellaco"; 
            ciudades[2]= "Gartenal"; 
            ciudades[3]= "Grecco";
            ciudades[4]= "Menafra"; 
            ciudades[5]= "Young";
        } 
        if (departamento.equalsIgnoreCase("Rivera")){
            ciudades[0]= "Masoller"; 
            ciudades[1]= "Minas de Corrales"; 
            ciudades[2]= "Rivera"; 
            ciudades[3]= "Santa Teresa";
            ciudades[4]= "Tranqueras"; 
            ciudades[5]= "Vichadero";
            
        } if (departamento.equalsIgnoreCase("Rocha")){
            ciudades[0]= "Chuy"; 
            ciudades[1]= "Castillos"; 
            ciudades[2]= "Lascano"; 
            ciudades[3]= "La Paloma";
            
        } if (departamento.equalsIgnoreCase("Salto")){
            ciudades[0]= "Albisu"; 
            ciudades[1]= "Belén"; 
            ciudades[2]= "Biassini"; 
            ciudades[3]= "Constitución";
            ciudades[4]= "Fernández"; 
            ciudades[5]= "Garibaldi";
            ciudades[6]= "Salto";
            ciudades[7]= "Saucedo"; 
            ciudades[8]= "Sarandí del Arapey";
            ciudades[9]= "Termas del Daymán";
        } if (departamento.equalsIgnoreCase("San José")){
            ciudades[0]= "Ciudad de Plata"; 
            ciudades[1]= "Ecilda Paullier"; 
            ciudades[2]= "Libertad"; 
            ciudades[3]= "Puntas de Valdez";
            ciudades[4]= "Rafael Perazza"; 
            ciudades[5]= "Rodríguez";
            ciudades[6]= "San José de Mayo";
          
        } if (departamento.equalsIgnoreCase("Soriano")){
            ciudades[0]= "Cardona"; 
            ciudades[1]= "Chacras de Dolores"; 
            ciudades[2]= "Dolores"; 
            ciudades[3]= "José Enrique Rodó";
            ciudades[4]= "La Pedrera"; 
            ciudades[5]= "Mercedes";
            ciudades[6]= "Palmitas";
            ciudades[7]= "Villa Soriano"; 
       } 
        if (departamento.equalsIgnoreCase("Tacuarembó")){
            ciudades[0]= "Achar"; 
            ciudades[1]= "Balneario Iporá"; 
            ciudades[2]= "Cuchilla de Peralta"; 
            ciudades[3]= "Curtina";
            ciudades[4]= "Cuchilla del Ombú"; 
            ciudades[5]= "Clara";
            ciudades[6]= "Chamberlain";
            ciudades[7]= "Cardozo"; 
            ciudades[8]= "Laureles";
            ciudades[9]= "Tacuarembó";
        } if (departamento.equalsIgnoreCase("Treinta y tres")){
            ciudades[0]= "Cerro Chato"; 
            ciudades[1]= "Ejido de Treinta y Tres"; 
            ciudades[2]= "General Enrique Martínez"; 
            ciudades[3]= "Santa Clara de Olimar";
            ciudades[4]= "Treinta y Tres"; 
            ciudades[5]= "Vergara";
            ciudades[6]= "Villa Sara";
          
        } 
        return ciudades; 
    }
    
    
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
    private javax.swing.JLabel jLabel10;
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
