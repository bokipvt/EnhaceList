/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enhancelist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Bojan
 */
public class main {
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         EnhanceList<String> eL=new EnhanceList<>(5);
         String c=br.readLine();
         while(!c.equals("x")){
           eL.add(c);
           System.out.println(eL.toString());
           c=br.readLine();
         }
   }
}
