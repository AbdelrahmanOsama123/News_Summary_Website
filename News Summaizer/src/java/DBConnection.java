
import java.sql.DriverManager;
import org.jsoup.Connection;

public class DBConnection {
    String url = "";
    String userName = "";
    String password = "";
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public DBConnection() {
        this.url = "jdbc:mysql://localhost:3306/news_summarizer";
        this.userName = "root";
        this.password = "root";
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public DBConnection(String url, String username, String password) {
        this.url = url;
        this.userName = username;
        this.password = password;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Connection connect () throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=(Connection) DriverManager.getConnection(this.url, this.userName, this.password);
            return con;

        } catch (Exception cnfe) {
            System.err.println("database not working");
        }
        
        return null;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    
}
