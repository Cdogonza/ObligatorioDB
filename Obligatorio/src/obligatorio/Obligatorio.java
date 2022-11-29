/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package obligatorio;


import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


/**
 *
 * @author gpaz1
 */
public class Obligatorio {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion_MySql conexion = new Conexion_MySql();
        conexion.ConectarBasedeDatos();
        if(conexion.conectado){
            conexion.DesconectarBasedeDatos();
            Loggin cargaLoggin = new Loggin();
            cargaLoggin.setVisible(true);
            System.out.println("BIENVENIDO");
        }else{
            JOptionPane.showMessageDialog(null,"Ha surgido un error de conexion a la base de datos, contacte con el Administrador","Error de Conexion",JOptionPane.ERROR_MESSAGE);
         
        }
    }
}
