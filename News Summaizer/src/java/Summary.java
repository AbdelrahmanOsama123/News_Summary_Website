

public class Summary {
    
    
    public static void doSummarize()throws Exception{
        String path = "C:\\Users\\Nasef Abd El-Hamid\\Documents\\NetBeansProjects\\try final gp code";
        String command = "cmd /k cd " + path + " && Summarize_Clusters.py";
        Runtime r = Runtime.getRuntime();
        Process p = r.exec(command);
    }
}
