package generals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

//    String url = "maxmail.db";
//    Connection connect;
    Connection cnx = null;
    //edward
//    private String user = "root";
//    private String pass = "p0s31d0n";
//    private String db = "maxmail";
//    private String url = "jdbc:mysql://localhost:3307/"
     // servidor
            //    private String user = "maxuser";
            //    private String pass = "Maxmail123*";
            //    private String db = "maxmail";
            //    private String url = "jdbc:mysql://192.168.10.3:3306/"
    // poseidon
                private String user = "root";
                private String pass = "p0s31d0n";
                private String db = "maxmail";
                private String url = "jdbc:mysql://localhost:3306/"
            + db + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&"
            + "useLegacyDatetimeCode=false&serverTimezone=America/Mexico_City&useSSL=false&"
            + "allowPublicKeyRetrieval=true";

//    public Connection connection() {
//        try {
//            connect = DriverManager.getConnection("jdbc:sqlite:" + url);
//        } catch (SQLException ex) {
//            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return connect;
//    }
    public Connection connection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                cnx = DriverManager.getConnection(url, user, pass);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cnx;
    }
}
