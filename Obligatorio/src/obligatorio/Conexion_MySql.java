/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package obligatorio;

/**
 *
 * @author gpaz1
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion_MySql {


    public Statement sentencia;
    public ResultSet resultado;
public boolean conectado=false;
private Connection conec;

    public Connection ConectarBasedeDatos() {
        try {

            final String Controlador = "com.mysql.cj.jdbc.Driver";
            Class.forName(Controlador);
            final String url_bd = "jdbc:mysql://localhost:3306/obligatorioBD?useUnicode=true&use"
                    + "JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
                    + "serverTimezone=UTC";
            conec = DriverManager.getConnection(url_bd, "root", "1234");

            sentencia = conec.createStatement();
            conectado = true;
            
        } catch (ClassNotFoundException | SQLException ex) {
            //   JOptionPane.showMessageDialog(null, ex.getMessage(), "Error ", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
        return conec;
    }

    public void DesconectarBasedeDatos() {
        try {
            if (conec != null) {
                if (sentencia != null) {
                    sentencia.close();
                }
                conec.close();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public Connection getConnection() {
        return conec;
    }
}
