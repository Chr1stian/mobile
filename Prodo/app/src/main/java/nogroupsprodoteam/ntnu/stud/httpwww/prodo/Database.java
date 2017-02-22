package nogroupsprodoteam.ntnu.stud.httpwww.prodo;

/**
 * Created by Christian on 21.02.2017.
 */

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Database {
    private static String mysqlAddr = "jdbc:mysql://mysql.stud.ntnu.no:3306/prodoteam_db?allowMultiQueries=true";
    private static String mysqlUser = "chrisnyv_demo";
    private static String mysqlPass = "rM48DmzH";



    public static String test(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bruker");

            ResultSet rs = stmt.executeQuery();
            if(rs.next())
                return rs.getString(2);
            return null;
        }
        catch(SQLException e){
            return e.toString();
        }
    }

    public static ArrayList<String> courseCodes(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> course = new ArrayList<String>();
        try{
            Connection conn = DriverManager.getConnection(mysqlAddr, mysqlUser, mysqlPass);
            PreparedStatement stmt = conn.prepareStatement("SELECT code FROM course");

            ResultSet rs = stmt.executeQuery();
            int i = 0;
            while(rs.next()){
                course.add(rs.getString(1));
            }
            return course;
        }
        catch(SQLException e){
            System.out.println(e);
            return null;
        }
    }

}
