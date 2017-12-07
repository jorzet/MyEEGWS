/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifiereeg.clasificadoreeg;

import Dao.Dao;
import classifiereeg.classifier.DetectionAlgorithm;
import entities.Cita;
import entities.Grabacion;
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
public class ClasificadorEEG implements Runnable{
    private String path;
    private String[] listFiles;
    Dao source;
    /**
     * @param args the command line arguments
     */
    public ClasificadorEEG(String[] args) {
        path = args[0];
        System.out.println("lista canales: "+ args[1]);
        listFiles = args[1].split(",");
        source = new Dao();
    }

    @Override
    public void run() {
        for(int i = 0;i<listFiles.length; i++){
            DataInputStream in = null;
            DataOutputStream out = null;
            try {
                in = new DataInputStream(new FileInputStream(path + File.separator + listFiles[i] +".bin"));
                File aux = new File(path + File.separator + listFiles[i] + "-double" + ".bin");
                if(!aux.exists())
                    aux.createNewFile();
                
                out = new DataOutputStream(new FileOutputStream(path + File.separator + listFiles[i] + "-double" + ".bin"));
                int j = 0;
                while (in.available()>0) {
                    double val = in.readByte();
                    double doubleVal = ((val*120)/255);
                    out.writeDouble(doubleVal);  
                    j++;
                }
                int index = 0;
                while (in.available()>0) {
                    double val = in.readDouble();
                    index++;
                }
                System.out.println("tamañooo: i: "+index+" j: "+j);
                
                    
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
            Grabacion grabacion = new Grabacion();
            Cita cita = new Cita();
            System.out.println("folioCita: " + path);
            cita.setFolioCita(Integer.parseInt(path));
            grabacion.setCita(cita);
            grabacion.setNombreArchivo(path + File.separator + listFiles[i] + "-double" + ".bin");
            
            // Calls procedure storeRecording and this returns the last inser ID
            int idGrabacion = source.registrarGrabacion(grabacion);
            
            new DetectionAlgorithm(recordingfile, listFiles[i], idGrabacion).doDetection();
        }
    }
    
}
