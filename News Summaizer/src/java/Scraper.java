import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Scraper {
    String path = "C:\\Users\\Nasef Abd El-Hamid\\Documents\\NetBeansProjects\\try final gp code";
    
    //////////////////////////////////////////////////////////////////////////////////////////
    public String getNewsPageLink(String googleSearchQuery) throws Exception {
        String Url = "https://www.google.com/search?q=" + googleSearchQuery + "&num=10";
        Document doc;
        String result = " ";
       try {
           doc = Jsoup.connect(Url).get();
           String data = "hdtb-mitem";
           Elements productElements = doc.getElementsByClass(data);
           org.jsoup.select.Elements links = productElements.select("a");
           for(Element e: links) {
               if(e.attr("abs:href").contains("nws&sa")){
                   result = e.attr("abs:href");
               }  
           }  

       } catch (IOException e) {
           throw new RuntimeException(e);
       }
        return result;
    }
 //////////////////////// Get News Links  ///////////////////////////////////////////// 
    public ArrayList<String> getLinks (String googleSearchQuery) throws Exception{
        ArrayList<String> result = new ArrayList<>();    
        BufferedReader bufReader = new BufferedReader(new FileReader(path + "\\black_list.txt")); 
        ArrayList<String> black_list = new ArrayList<>(); 
        String line = bufReader.readLine(); 
        boolean blackListCheck = true;
        while (line != null) { 
            black_list.add(line); 
            line = bufReader.readLine(); 
        } 
        bufReader.close();        
        try {
           Document doc =  Jsoup.connect(getNewsPageLink(googleSearchQuery)).get();
           org.jsoup.select.Elements links = doc.select("a");
           for(Element e: links) {
               for(int i = 0 ; i < black_list.size() ; i++){
                   if(e.attr("abs:href").contains(black_list.get(i))){
                       blackListCheck = false;
                   }
               }
               if(blackListCheck){
                   result.add(e.attr("abs:href"));
               }
               blackListCheck = true;
           }
           result.removeAll(Collections.singleton(""));
       }catch (IOException ex) {
           System.err.println("getlinks is not working");
       }
        return result;
    }
 //////////////////////////////// put in file ///////////////////////////////////////////
    public void scrape(ArrayList<String> links) throws Exception{
        FileWriter writer = new FileWriter(path + "\\links.txt"); 
        for(String str: links) {
          writer.write(str + System.lineSeparator());
        }
        writer.close();
        Runtime r = Runtime.getRuntime();
        String command = "cmd /k cd " + path +" && Scrape_News.py";
        Process p = Runtime.getRuntime().exec(command);

    }
//////////////////////////////////////////////////////////////////////////////////////////
}
