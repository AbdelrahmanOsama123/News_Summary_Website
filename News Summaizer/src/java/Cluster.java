/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nasef Abd El-Hamid
 */
public class Cluster {
    String path = "C:\\Users\\Nasef Abd El-Hamid\\Documents\\NetBeansProjects\\try final gp code";
        
    public void sentenceEncoder()throws Exception{
        Runtime r = Runtime.getRuntime();
        String command = "cmd /k cd " + path + " && Vectorizing_Sentence.py";
        System.err.println(command);
        Process p = r.exec(command);
    }
/////////////////////////////////////////////////////////////////////////////////////////
    public void newsCluster()throws Exception{
        Runtime r = Runtime.getRuntime();
        String command = "cmd /k cd " + path + " && Cluster_News.py";
        Process p = r.exec(command);
    }
}
