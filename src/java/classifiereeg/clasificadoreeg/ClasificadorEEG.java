/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifiereeg.clasificadoreeg;

import classifiereeg.classifier.DetectionAlgorithm;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class ClasificadorEEG {

    /**
     * @param args the command line arguments
     */
    public static void classifierEEG(String[] args) {
        // TODO code application logic here
        String path = args[0];
        System.out.println("lista canales: "+ args[1]);
        String[] listFiles = args[1].split(",");
        for(int i = 0;i<listFiles.length; i++){
            DataInputStream in = null;
            DataOutputStream out = null;
            try {
                in = new DataInputStream(new FileInputStream(path + File.separator + listFiles[i] +".bin"));
                File aux = new File(path + File.separator + listFiles[i] + "-double" + ".bin");
                if(!aux.exists())
                    aux.createNewFile();
                while (true) {
                        int val = in.readInt();
                        double doubleVal = ((val * (1.8/4096))/2000)*1000000;
                        
                        
                        out = new DataOutputStream(new FileOutputStream(path + File.separator + listFiles[i] + "-double" + ".bin"));
                        out.writeDouble(doubleVal);
                        
                }
                    
            } catch (EOFException | FileNotFoundException ignored) {
                ignored.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ClasificadorEEG.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        for(int i = 0;i< listFiles.length; i++){
            File recordingfile = new File(path + File.separator + listFiles[i] + "-double" + ".bin");
            new DetectionAlgorithm(recordingfile).doDetection();
        }
        
    }
    
}
