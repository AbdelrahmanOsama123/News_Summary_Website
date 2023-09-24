
import java.io.File;
import static java.lang.System.err;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class NewsModel extends Model{
    
    //////////////////////////////////////////////////////////////////////////////
    public void search(String searchQuery) throws Exception{
        Scraper scraper = new Scraper();
        Cluster cluster = new Cluster();
        Summary sum = new Summary();
        ArrayList<String> links = new ArrayList<>();
        String[] files = {"\\check_files\\scrape.txt", "\\check_files\\vectorization.txt", "\\check_files\\cluster_news.txt",
            "\\check_files\\finish.txt"};
        
        
        // deleting file of previous run    
        for (int i = 0; i < files.length; i++){
            File f = new File(path+files[i]);
            f.delete();
        }
        
        //  Calling getlinks function (crawler) to be excuted
        links = scraper.getLinks(searchQuery);
        
        //calling scrape function to be excuted and wait until it finish
        scraper.scrape(links);
        while(!(new File(path+files[0])).exists()){
            Thread.sleep(1000);
            err.println("\t\tstill doing scraping!!");
        }
        err.println("\t\tScraping for news is Finished.");
        
        //  Calling vectorization Function to be excuted and wait until it finish
        cluster.sentenceEncoder();
        while(!(new File(path+files[1])).exists()){
            Thread.sleep(2000);
            err.println("\t\tstill doing vectorization!!");
        }
        err.println("\t\tVectorizing sentences is Finished.");

        //  Calling news cluster Function to be excuted and wait until it finish
        cluster.newsCluster();
        while(!(new File(path+files[2])).exists()){
            Thread.sleep(1000);
            err.println("\t\tstill doing clustering!!");
        }
        System.err.println("\t\tClustering News is Finished.");

        //  Calling news summerizing Function to be excuted and wait until it finish
        sum.doSummarize();
        while(!(new File(path+files[3])).exists()){
            Thread.sleep(2000);
            err.println("\t\tstill doing summary!!");
        }
        System.err.println("\t\tSummarizing News is Finished.");

        System.err.println("\t\tEverything is been  completed.");
    }
    //////////////////////////////////////////////////////////////////////////////
    public void rateNews(int rate, String username, String newstitle) throws Exception{
        String line = "";
        Statement statm = null;
        int rs = 0;
        Connection con = (Connection) this.dbconnection.connect();
        boolean check = false;
        try{
            con = (Connection)DriverManager.getConnection(this.dbconnection.url, this.dbconnection.userName, this.dbconnection.password);
            statm = (Statement) con.createStatement();
            line = ("insert into news_summarizer.news (username, searchtitle, rate) "
                    + "value('" + username + "', '" + newstitle + "', " + rate + ");");

            rs = statm.executeUpdate(line);


        }catch(Exception cnfe){
            System.err.print("Exception: ma3mlsh rate");
        }
        
    }
    //////////////////////////////////////////////////////////////////////////////
    public ArrayList<String> getsearchHistory(String username) throws Exception{
        ArrayList<String> result = new ArrayList<>();
        String line = "";
        Statement statm = null;
        ResultSet rs = null;
        Connection con = (Connection) this.dbconnection.connect();
        try{
            con = (Connection)DriverManager.getConnection(this.dbconnection.url, this.dbconnection.userName, this.dbconnection.password);
            statm = (Statement) con.createStatement();
            line = ("SELECT * FROM news_summarizer.history where username = '" + username + "';");

            rs = statm.executeQuery(line);
            
            while(rs.next()){
                result.add(rs.getString("searchtitle"));
            }
        }catch(Exception cnfe){
            System.err.print("Exception: ma3mlsh get history");
        }
        return result;
    }
    //////////////////////////////////////////////////////////////////////////////
    public void storesearchHistory(String username, String searchHistory) throws Exception{
        String line = "";
        Statement statm = null;
        int rs = 0;
        Connection con = (Connection) this.dbconnection.connect();
        try{
            con = (Connection)DriverManager.getConnection(this.dbconnection.url, this.dbconnection.userName, this.dbconnection.password);
            statm = (Statement) con.createStatement();
            line = ("insert into news_summarizer.history (username, searchtitle) "
                    + "value ('" + username + "', '" + searchHistory + "');");

            rs = statm.executeUpdate(line);


        }catch(Exception cnfe){
            System.err.print("Exception: ma3mlsh store history");
        }
    }
}
