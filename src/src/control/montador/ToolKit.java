package control.montador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class ToolKit {
   public static String DIRETORIO = System.getProperty("user.dir") +
		                            "/src/control/montador/";
   
   private static ToolKit tk;
   
   private BufferedReader moveStr, salaStr;
   private PrintWriter outputStr;
   
   public static ToolKit start(String nivelPath, String nomeArquivo) {
      tk = new ToolKit();
      String nivelFile = (nivelPath == null)
            ? DIRETORIO + nomeArquivo  + ".csv": nivelPath;
      try {
         tk.salaStr = new BufferedReader(
               new FileReader(nivelFile));
      } catch(IOException erro){
         erro.printStackTrace();
      }
      return tk;
   }
   
   public String[][] retrieveNivel() {
      Vector<String[]> v = new Vector<String[]>();
      try {
         String line = salaStr.readLine();
         while (line != null) {
            String ln[]  = line.split(",");
            v.add(ln);
            line = salaStr.readLine();
         }
         salaStr.close();
      } catch (Exception erro) {
         erro.printStackTrace();
      }
      return (String[][])v.toArray(new String[v.size()][]);
   }
   
   
   public void stop() {
      try {
         salaStr.close();
         outputStr.close();
         moveStr.close();
      } catch(Exception erro){
         erro.printStackTrace();
      }
   }
}
