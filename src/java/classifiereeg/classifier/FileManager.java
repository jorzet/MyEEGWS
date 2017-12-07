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
    
    
    public double[] readFile(){
        try {
            List<Double> list = new ArrayList<>();
            //FileInputStream fis2 = new FileInputStream(path.getAbsolutePath());
            DataInputStream in = new DataInputStream(new FileInputStream(path));
            
            System.out.println("tamaño: "+ path.length());
            double val;
            while(in.available()>0){
                list.add(in.readDouble());
            }

            //for(int i=list.size()-1;i>list.size()-50;i--)
            //    System.out.println(list.get(i));
            in.close();
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
