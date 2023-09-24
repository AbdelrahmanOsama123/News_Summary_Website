
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PremiumUser extends User{
    String name = "";
    String password = "";
    int age = 0;
//////////////////////////////////////////////////////////////////////////////////////////////
    public boolean logIn(String userName, String password) throws Exception{
        String line = "";
        Statement statm = null;
        ResultSet rs = null;
        Connection con = (Connection) this.dbconnection.connect();
        boolean check = false;
        try{
            con = (Connection)DriverManager.getConnection(this.dbconnection.url, this.dbconnection.userName, this.dbconnection.password);
            statm = (Statement) con.createStatement();
            line = ("select * from news_summarizer.user where username = '" + userName +"' and password = '" + password + "';");

            rs = statm.executeQuery(line);


        }catch(Exception cnfe){
            System.err.print("Exception: ma3mlsh login");
        }
        if(rs.next()){
            check = true;
        }
        return check;
    }
//////////////////////////////////////////////////////////////////////////////////////////////
    public boolean signUp(String userName, String password, int age) throws Exception{
        String line = "";
        Statement statm = null;
        int rs = 0;
        Connection con = (Connection) this.dbconnection.connect();
        boolean check = false;
        try{
            con = (Connection)DriverManager.getConnection(this.dbconnection.url, this.dbconnection.userName, this.dbconnection.password);
            statm = (Statement) con.createStatement();
            line = ("INSERT INTO user (username, password, age) VALUES ('" + userName +"', '" + password + "', " + age + ");");

            rs = statm.executeUpdate(line);

        }catch(Exception cnfe){
            System.err.print("Exception: ma3mlsh signup");
        }
        return true;
    }
}
