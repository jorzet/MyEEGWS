/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifiereeg.classifier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class FileManager {
    
    private File path;
    
    public FileManager(File path){
        this.path = path;
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException{
        int idUser = 42;
        String name = "jorge Zepeda Tinoco";
        int idSpetialist = 34;
        String spetialist = "Hugo Fernandez Perez";
        double[] one = {1.2, 3.45, 6.435, 2.54};
        double[] two = {0.5, 0.35, 0.567, 0.67};
        
        //String path = "C:\\Users\\Jorge\\Documents\\UPIITA\\PT1\\Clasificador-EEG\\1-Jorge_Zepeda_Tinoco.bin";
        
        /*FileInputStream fstream = new FileInputStream("C:\\Users\\Jorge\\Documents\\UPIITA\\PT1\\Clasificador-EEG\\1. GOMEZ TELLO ALAN.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        FileOutputStream fis = new FileOutputStream(path);
        DataOutputStream dis = new DataOutputStream(fis);
            
        
        String strLine;

        //Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
          // Print the content on the console
          String[] doubles = strLine.split(" ");
          for(int i=0;i<doubles.length;i++){
              try{
                dis.writeDouble(Double.parseDouble(doubles[i]));
              }catch(NumberFormatException e){
                  
              }
          }
        }
       fis.close();
        //Close the input stream
        br.close();*/
       
        /*FileOutputStream fos = new FileOutputStream(new File("recordingFile.bin"));
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(idUser); 
        bos.write(idSpetialist);
        bos.write(name.getBytes());
        bos.write(spetialist.getBytes());*/

        
        //System.out.println( ois.read()+"-"+ois.readUTF());
    }
    
    public double[] readFile(){
        try {
            List<Double> list = new ArrayList<>();
            FileInputStream fis2 = new FileInputStream(path.getAbsolutePath());
            DataInputStream dis2 = new DataInputStream(fis2);
            
            System.out.println("tamaño: "+ path.length());
            double val;
            while(fis2.available()>0){
                list.add(dis2.readDouble());
            }

            //for(int i=list.size()-1;i>list.size()-50;i--)
            //    System.out.println(list.get(i));
            fis2.close();
            double[] arr = list.stream().mapToDouble(Double::doubleValue).toArray();
            return arr;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
