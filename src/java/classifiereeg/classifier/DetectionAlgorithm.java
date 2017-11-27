/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifiereeg.classifier;

import classifiereeg.analyzer.Detector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Jorge
 */
public class DetectionAlgorithm {
    private File recordingFile;
    private int fm = 512;                   
    private double gain = 2.559375;
    private double[] signal;
    
    
    public DetectionAlgorithm(File recordingFile){
        this.recordingFile = recordingFile;
    }
    
    public void doDetection(){
        //JSONParser parser = new JSONParser();
        if(recordingFile!=null){
            FileManager fm = new FileManager(recordingFile);
            signal = fm.readFile();
            if(signal!=null){
                //for(int i=0;i<256;i++)
                //    System.out.print(signal[i]+", ");
                System.out.println("tamaño:: "+signal.length);
                classify();
                
            }
        }
        else{
            System.out.println("El archivo no existe");
        }
    }
    
    private void classify(){
        int[] waveType = new int[(int)recordingFile.length()/fm];
        int h=0;
        for(int j=0;j<recordingFile.length()-fm;j+=fm){
            double[] second = new double[fm];
            for(int i=0;i<fm;i++){
                second[i]=signal[i+j]/gain;
            }

            for(int i=0;i<fm;i++)
                second[i] = second[i] - Detector.getMean(second);
                //System.out.print(second[i]+",");
            
            double amplitude = Detector.getAmplitude(second);
            double frequency = Detector.getFrequency(second,fm);
            //System.out.println("Amplitud: "+amplitude);
            //System.out.println("Frecuancia: "+frequency);
            //System.out.println("Tipo de onda: "+Detector.getWaveTypeString(frequency, amplitude));
            waveType[h] = Detector.getWaveType(frequency, amplitude);
            h++;
        }
        Map<Integer, Integer> percentage = Detector.getWaveTypePercentage(waveType);
        
        for(int f=0;f<9;f++){
            double percenta = percentage.get(f)*100/(recordingFile.length()/fm);
            System.out.printf(Detector.getWaveTypeString(f) + " Aparción: " + percentage.get(f) + " porcentaje: %f ", percenta);
            System.out.println();
        }
    }
}
