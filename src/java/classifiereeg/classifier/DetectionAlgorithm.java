/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifiereeg.classifier;

import Dao.Dao;
import classifiereeg.analyzer.Detector;
import entities.Grabacion;
import entities.ResultadosCanal;
import entities.ResultadosSegmento;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class DetectionAlgorithm {
    
    private final Dao source;
    
    private File recordingFile;
    private int mIdGrabacion;
    private int fm = 512;                   
    private double gain = 2.559375;
    private double[] signal;
    private String currentChannel;
    
    private int userAge = 23;
    
    private double percentageAlphaRithm = 0;
    private double percentageAlphaFrequency = 0;
    private double percentageBetaRithm = 0;
    private double percentageBetaFrequency = 0;
    private double percentageDeltaRithm = 0;
    private double percentageDeltaFrequency = 0;
    private double percentageThetaRithm = 0;
    private double percentageThetaFrequency = 0;
    private double percentageKnownSignal = 0;
    
    List<Double> alphaRithmAmplitudes = new ArrayList<>();
    List<Double> alphaRithFrequency = new ArrayList<>();
    List<Double> alphaFrequencyAmplitudes = new ArrayList<>();
    List<Double> alphaFrequencyFrequency = new ArrayList<>();
    
    List<Double> betaRithmAmplitudes = new ArrayList<>();
    List<Double> betaRithmFrequency = new ArrayList<>();
    List<Double> betaFrequencymAmplitudes = new ArrayList<>();
    List<Double> betaFrequencyFrequency = new ArrayList<>();
    
    List<Double> deltaRithmAmplitudes = new ArrayList<>();
    List<Double> deltaRithmFrequency = new ArrayList<>();
    List<Double> deltaFrequencymAmplitudes = new ArrayList<>();
    List<Double> deltaFrequencyFrequency = new ArrayList<>();
    
    List<Double> thetaRithmAmplitudes = new ArrayList<>();
    List<Double> thetaRithmFrequency = new ArrayList<>();
    List<Double> thetaFrequencyAmplitudes = new ArrayList<>();
    List<Double> thetaFrequencyFrequency = new ArrayList<>();
    
    List<Double> notAnalycedAmplitudes = new ArrayList<>();
    List<Double> notAnalycedFrequency = new ArrayList<>();
    
    public DetectionAlgorithm(File recordingFile, String currentChannel, int idGrabacion){
        this.recordingFile = recordingFile;
        this.currentChannel = currentChannel;
        this.mIdGrabacion = idGrabacion;
        this.source = new Dao();
    }
    
    public Map<String,Double> doDetection(){
        //JSONParser parser = new JSONParser();
        if(recordingFile!=null){
            FileManager fm = new FileManager(recordingFile);
            signal = fm.readFile();
            if(signal!=null){
                //for(int i=0;i<256;i++)
                //    System.out.print(signal[i]+", ");
                System.out.println("tamaño:: "+signal.length);
                Map<String,Double> resultadoCanal= classify();
                System.out.println("resultadoCanal "+resultadoCanal.size());
                return resultadoCanal;
            }
        }
        else{
            System.out.println("El archivo no existe");
        }
        return null;
    }
    
    private Map<String,Double> classify(){
        List<Integer> waveType = new ArrayList<>();
        List<Double> dominantFrequencys = new ArrayList<>();
        List<Double> channelAmplitudes = new ArrayList<>();
        int secondNumer=0;
        double mean = Detector.getMean(signal);
        System.out.println("Mean: " + mean);
        System.out.println("\n\nResultados por segundo: \n");
        
        for(int j=0;j<signal.length;j+=fm){
            double[] second = new double[fm];
            double[] originalSecond = new double[fm];
            for(int i=0;i<fm;i++){
                    //System.out.println("i: "+i+" j: "+j+" i+j: "+(i+j));
                    second[i]=signal[i+j];
                    originalSecond[i] = signal[i+j];
            }

            for(int i=0;i<fm;i++){
                second[i] = second[i] - mean;
                //second[i] = second[i] / gain;
            }
                //System.out.print(second[i]+",");
            
            double amplitude = Detector.getAmplitude(second);
            double frequency = Detector.getFrequency(second,fm);
            
            int wave = Detector.getWaveType2(frequency, amplitude);
            
            switch(wave){
                case classifiereeg.analyzer.Detector.ALPHA_RHYTHM:
                    alphaRithmAmplitudes.add(amplitude);
                    alphaRithFrequency.add(frequency);
                    break;
                case classifiereeg.analyzer.Detector.ALPHA_FREQUENCY:
                    alphaFrequencyAmplitudes.add(amplitude);
                    alphaFrequencyFrequency.add(frequency);
                    break;
                case classifiereeg.analyzer.Detector.BETA_RHYTHM:
                    betaRithmAmplitudes.add(amplitude);
                    betaRithmFrequency.add(frequency);
                    break;
                case classifiereeg.analyzer.Detector.BETA_FREQUENCY:
                    betaFrequencymAmplitudes.add(amplitude);
                    betaFrequencyFrequency.add(frequency);
                    break;
                case classifiereeg.analyzer.Detector.DELTA_RHYTHM:
                    deltaRithmAmplitudes.add(amplitude);
                    deltaRithmFrequency.add(frequency);
                    break;
                case classifiereeg.analyzer.Detector.DELTA_FREQUENCY:
                    deltaFrequencymAmplitudes.add(amplitude);
                    deltaFrequencyFrequency.add(frequency);
                    break;  
                case classifiereeg.analyzer.Detector.THETA_RHYTHM:
                    thetaRithmAmplitudes.add(amplitude);
                    thetaRithmFrequency.add(frequency);
                    break;
                case classifiereeg.analyzer.Detector.THETA_FREQUENCY:
                    thetaFrequencyAmplitudes.add(amplitude);
                    thetaFrequencyFrequency.add(frequency);
                    break;
                case classifiereeg.analyzer.Detector.NOT_ANALYZED:
                    notAnalycedAmplitudes.add(amplitude);
                    notAnalycedFrequency.add(frequency);
                    break;
            }
            System.out.println();
            System.out.println("Segundo: " + secondNumer + 1);
            
            System.out.print("Señal: ");
            String signalString = "";
            for(int i=0;i<originalSecond.length;i++){
                System.out.print(originalSecond[i] + ",");
                signalString = signalString + originalSecond[i] + ",";
            }
            System.out.println();
            System.out.println("tamaño senañ;"+ signalString.length());
            System.out.println("\nAmplitud: "+amplitude);
            System.out.println("Frecuancia: "+frequency);
            System.out.println("Tipo de onda: "+Detector.getWaveTypeString(wave));
            
            dominantFrequencys.add(frequency);
            channelAmplitudes.add(amplitude);
            waveType.add(wave);
            secondNumer++;
            
            ResultadosSegmento resultadosSegmento = new ResultadosSegmento();
            Grabacion grabacion = new Grabacion();
        
            grabacion.setIdGrabacion(mIdGrabacion);
            resultadosSegmento.setGrabacion(grabacion);
            resultadosSegmento.setSegundo(secondNumer);
            resultadosSegmento.setCanal(currentChannel);
            resultadosSegmento.setFrecuenciaDominante(frequency);
            resultadosSegmento.setTipoOnda(Detector.getWaveTypeString(wave));
            resultadosSegmento.setSenal(signalString);
            resultadosSegmento.setIsAnormal(Detector.isAnormal(amplitude, frequency, userAge));
            
            source.registrarResultadosSegmento(resultadosSegmento);
            
        }
        Map<Integer, Integer> percentage = Detector.getWaveTypePercentage(waveType);
        Map<String,Double> channelPercentage = new TreeMap<>();
        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.ALPHA_RHYTHM), 0.0);
        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.ALPHA_FREQUENCY), 0.0);
        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.BETA_RHYTHM), 0.0);
        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.BETA_FREQUENCY), 0.0);
        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.DELTA_RHYTHM), 0.0);
        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.DELTA_FREQUENCY), 0.0);
        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.THETA_RHYTHM), 0.0);
        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.THETA_FREQUENCY), 0.0);
        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.NOT_ANALYZED), 0.0);
        
        for(int waveTypeNum=0;waveTypeNum<10;waveTypeNum++){
            if(percentage.get(waveTypeNum) != null){ // check if the wave type number is contained into percentage HashMap
                double percenta = percentage.get(waveTypeNum);
               
                switch(waveTypeNum){
                    case classifiereeg.analyzer.Detector.ALPHA_RHYTHM:
                        percentageAlphaRithm = (percenta/secondNumer)*100;
                        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.ALPHA_RHYTHM), percentageAlphaRithm);
                        break;
                    case classifiereeg.analyzer.Detector.ALPHA_FREQUENCY:
                        percentageAlphaFrequency = (percenta/secondNumer)*100;
                        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.ALPHA_FREQUENCY), percentageAlphaFrequency);
                        break;
                    case classifiereeg.analyzer.Detector.BETA_RHYTHM:
                        percentageBetaRithm = (percenta/secondNumer)*100;
                        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.BETA_RHYTHM), percentageBetaRithm);
                        break;
                    case classifiereeg.analyzer.Detector.BETA_FREQUENCY:
                        percentageBetaFrequency = (percenta/secondNumer)*100;
                        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.BETA_FREQUENCY), percentageBetaFrequency);
                        break;
                    case classifiereeg.analyzer.Detector.DELTA_RHYTHM:
                        percentageDeltaRithm = (percenta/secondNumer)*100;
                        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.DELTA_RHYTHM), percentageDeltaRithm);
                        break;
                    case classifiereeg.analyzer.Detector.DELTA_FREQUENCY:
                        percentageDeltaFrequency = (percenta/secondNumer)*100;
                        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.DELTA_FREQUENCY), percentageDeltaFrequency);
                        break;  
                    case classifiereeg.analyzer.Detector.THETA_RHYTHM:
                        percentageThetaRithm = (percenta/secondNumer)*100;
                        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.THETA_RHYTHM), percentageThetaRithm);
                        break;
                    case classifiereeg.analyzer.Detector.THETA_FREQUENCY:
                        percentageThetaFrequency = (percenta/secondNumer)*100;
                        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.THETA_FREQUENCY), percentageThetaFrequency);
                        break;
                    case classifiereeg.analyzer.Detector.NOT_ANALYZED:
                        percentageKnownSignal = (percenta/secondNumer)*100;
                        channelPercentage.put(Detector.getWaveTypeString(classifiereeg.analyzer.Detector.NOT_ANALYZED), percentageKnownSignal);

                        break;
                }
            }
        }
        
        double averageAlphaRithmAmplitude = alphaRithmAmplitudes.isEmpty()? 0 : alphaRithmAmplitudes.stream().mapToDouble(Double::doubleValue).sum()/alphaRithmAmplitudes.size();
        double averageAlphaRithmFrequency = alphaRithFrequency.isEmpty()? 0 : alphaRithFrequency.stream().mapToDouble(Double::doubleValue).sum()/alphaRithFrequency.size();
        double averageAlphaFrequencyAmplitude = alphaFrequencyAmplitudes.isEmpty()? 0 : alphaFrequencyAmplitudes.stream().mapToDouble(Double::doubleValue).sum()/alphaFrequencyAmplitudes.size();
        double averageAlphaFrequencyFrequency = alphaFrequencyFrequency.isEmpty()? 0 : alphaFrequencyFrequency.stream().mapToDouble(Double::doubleValue).sum()/alphaFrequencyFrequency.size();
        
        double averageBetaRithmAmplitude = betaRithmAmplitudes.isEmpty()? 0 : betaRithmAmplitudes.stream().mapToDouble(Double::doubleValue).sum()/betaRithmAmplitudes.size();
        double averageBetaRithmFrequency = betaRithmFrequency.isEmpty()? 0 : betaRithmFrequency.stream().mapToDouble(Double::doubleValue).sum()/betaRithmFrequency.size();
        double averageBetaFrequencyAmplitude = betaFrequencymAmplitudes.isEmpty()? 0 : betaFrequencymAmplitudes.stream().mapToDouble(Double::doubleValue).sum()/betaFrequencymAmplitudes.size();
        double averageBetaFrequencyFrequency = betaFrequencyFrequency.isEmpty()? 0 : betaFrequencyFrequency.stream().mapToDouble(Double::doubleValue).sum()/betaFrequencyFrequency.size();
        
        double averageDeltaRithmAmplitude = deltaRithmAmplitudes.isEmpty()? 0 : deltaRithmAmplitudes.stream().mapToDouble(Double::doubleValue).sum()/deltaRithmAmplitudes.size();
        double averageDeltaRithmFrequency = deltaRithmFrequency.isEmpty()? 0 : deltaRithmFrequency.stream().mapToDouble(Double::doubleValue).sum()/deltaRithmFrequency.size();
        double averageDeltaFrequencyAmplitude = deltaFrequencymAmplitudes.isEmpty()? 0 : deltaFrequencymAmplitudes.stream().mapToDouble(Double::doubleValue).sum()/deltaFrequencymAmplitudes.size();
        double averageDeltaFrequencyFrequency = deltaFrequencyFrequency.isEmpty()? 0 : deltaFrequencyFrequency.stream().mapToDouble(Double::doubleValue).sum()/deltaFrequencyFrequency.size();
        
        double averageThetaRithmAmplitude = thetaRithmAmplitudes.isEmpty()? 0 : thetaRithmAmplitudes.stream().mapToDouble(Double::doubleValue).sum()/thetaRithmAmplitudes.size();
        double averageThetaRithmFrequency = thetaRithmFrequency.isEmpty()? 0 : thetaRithmFrequency.stream().mapToDouble(Double::doubleValue).sum()/thetaRithmFrequency.size();
        double averageThetaFrequencyAmplitude = thetaFrequencyAmplitudes.isEmpty()? 0 : thetaFrequencyAmplitudes.stream().mapToDouble(Double::doubleValue).sum()/thetaFrequencyAmplitudes.size();
        double averageThetaFrequencyFrequency = thetaFrequencyFrequency.isEmpty()? 0 : thetaFrequencyFrequency.stream().mapToDouble(Double::doubleValue).sum()/thetaFrequencyFrequency.size();
        
        double averageKnownAmplitude = notAnalycedAmplitudes.isEmpty()? 0 : notAnalycedAmplitudes.stream().mapToDouble(Double::doubleValue).sum()/notAnalycedAmplitudes.size();
        double averageKnownFrequencies = notAnalycedFrequency.isEmpty()? 0 : notAnalycedFrequency.stream().mapToDouble(Double::doubleValue).sum()/notAnalycedFrequency.size();

        double dominantFrequencyChannel = dominantFrequencys.isEmpty()? 0 : dominantFrequencys.stream().mapToDouble(Double::doubleValue).sum()/dominantFrequencys.size();
        double averageChannelAplitudes = channelAmplitudes.isEmpty()? 0 : channelAmplitudes.stream().mapToDouble(Double::doubleValue).sum()/channelAmplitudes.size();
        
        String dominantWaveTypeChannel = "";
        
        int max = 0;
        int maxValueInMap=(Collections.max(percentage.values()));  // This will return max value in the Hashmap
        for (Entry<Integer, Integer> entry : percentage.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                System.out.println("maxHashMap: " + entry.getKey());     // Print the key with max value
                max = entry.getKey();
            }
        }
        
        dominantWaveTypeChannel = Detector.getWaveTypeString(max);
        
        
        System.out.println("\n\nResultados por canal\n\n");
        
        System.out.println("Canal analizado:                   " + currentChannel.toUpperCase());
        System.out.println("frecuencia dominante del canal:    " + dominantFrequencyChannel);
        System.out.println("promedio de amplitud del canal:  " + averageChannelAplitudes);
        System.out.println("tipo de onda dominante del canal:  " + dominantWaveTypeChannel);
        
        System.out.println("*Ritmo alpha: ");
        System.out.println("    Apariciones:                   " + alphaRithmAmplitudes.size());
        System.out.println("    Porcentaje de aparicion:       " + percentageAlphaRithm + " %");
        System.out.println("    Promedio amplitud:             " + averageAlphaRithmAmplitude);
        System.out.println("    Promedio frecuencia dominante: " + averageAlphaRithmFrequency);
        
        System.out.println("*Ritmo beta:");
        System.out.println("    Apariciones:                   " + betaRithmAmplitudes.size());
        System.out.println("    Porcentaje de aparicion:       " + percentageBetaRithm + " %");
        System.out.println("    Promedio amplitud:             " + averageBetaRithmAmplitude);
        System.out.println("    Promedio frecuencia dominante: " + averageBetaRithmFrequency);
        
        System.out.println("*Ritmo delta:");
        System.out.println("    Apariciones:                   " + deltaRithmAmplitudes.size());
        System.out.println("    Porcentaje de aparicion:       " + percentageDeltaRithm + " %");
        System.out.println("    Promedio amplitud:             " + averageDeltaRithmAmplitude);
        System.out.println("    Promedio frecuencia dominante: " + averageDeltaRithmFrequency);
        
        System.out.println("*Ritmo theta:");
        System.out.println("    Apariciones:                   " + thetaRithmAmplitudes.size());
        System.out.println("    Porcentaje de aparicion:       " + percentageThetaRithm + " %");
        System.out.println("    Promedio amplitud:             " + averageThetaRithmAmplitude);
        System.out.println("    Promedio frecuencia dominante: " + averageThetaRithmFrequency);
        
        System.out.println("*Frecuecia alpha");
        System.out.println("    Apariciones:                   " + alphaFrequencyAmplitudes.size());
        System.out.println("    Porcentaje de aparicion:       " + percentageAlphaFrequency + " %");
        System.out.println("    Promedio amplitud:             " + averageAlphaFrequencyAmplitude);
        System.out.println("    Promedio frecuencia dominante: " + averageAlphaFrequencyFrequency);
        
        System.out.println("*Frecuecia beta:");
        System.out.println("    Apariciones:                   " + betaFrequencymAmplitudes.size());
        System.out.println("    Porcentaje de aparicion:       " + percentageBetaFrequency + " %");
        System.out.println("    Promedio amplitud:             " + averageBetaFrequencyAmplitude);
        System.out.println("    Promedio frecuencia dominante: " + averageBetaFrequencyFrequency);
        
        System.out.println("*Frecuecia delta:");
        System.out.println("    Apariciones:                   " + deltaFrequencymAmplitudes.size());
        System.out.println("    Porcentaje de aparicion:       " + percentageDeltaFrequency + " %");
        System.out.println("    Promedio amplitud:             " + averageDeltaFrequencyAmplitude);
        System.out.println("    Promedio frecuencia dominante: " + averageDeltaFrequencyFrequency);
        
        System.out.println("*Frecuecia theta:");
        System.out.println("    Apariciones:                   " + thetaFrequencyAmplitudes.size());
        System.out.println("    Porcentaje de aparicion:       " + percentageThetaFrequency + " %");
        System.out.println("    Promedio amplitud:             " + averageThetaFrequencyAmplitude);
        System.out.println("    Promedio frecuencia dominante: " + averageThetaFrequencyFrequency);
        
        System.out.println("*Desconocidas:");
        System.out.println("    Apariciones:                   " + notAnalycedAmplitudes.size());
        System.out.println("    Porcentaje de aparicion:       " + percentageKnownSignal + " %");
        System.out.println("    Promedio amplitud:             " + averageKnownAmplitude);
        System.out.println("    Promedio frecuencia dominante: " + averageKnownFrequencies);
        
        
        ResultadosCanal resultadosCanal = new ResultadosCanal();
        Grabacion grabacion = new Grabacion();
        
        grabacion.setIdGrabacion(mIdGrabacion);
        resultadosCanal.setGrabacion(grabacion);
        resultadosCanal.setCanal(currentChannel);
        resultadosCanal.setPromedioAmplitudesCanal(averageChannelAplitudes);
        resultadosCanal.setFrecuenciaDominanteCanal(dominantFrequencyChannel);
        resultadosCanal.setTipoOndaDominanteCanal(dominantWaveTypeChannel);
        
        resultadosCanal.setPorcentajeAparicionRitmoAlpha(percentageAlphaRithm);
        resultadosCanal.setPorcentajeAparicionRitmoBeta(percentageBetaRithm);
        resultadosCanal.setPorcentajeAparicionRitmoDelta(percentageDeltaRithm);
        resultadosCanal.setPorcentajeAparicionRitmoTheta(percentageThetaRithm);
        
        resultadosCanal.setPorcentajeAparicionFrecuenciaAlpha(percentageAlphaFrequency);
        resultadosCanal.setPorcentajeAparicionFrecuenciaBeta(percentageBetaFrequency);
        resultadosCanal.setPorcentajeAparicionFrecuenciaDelta(percentageDeltaFrequency);
        resultadosCanal.setPorcentajeAparicionFrecuenciaTheta(percentageThetaFrequency);
        
        resultadosCanal.setPromedioAmplitudesRitmoAlpha(averageAlphaRithmAmplitude);
        resultadosCanal.setPromedioAmplitudesRitmoBeta(averageBetaRithmAmplitude);
        resultadosCanal.setPromedioAmplitudesRitmoDelta(averageDeltaRithmAmplitude);
        resultadosCanal.setPromedioAmplitudesRitmoTheta(averageThetaRithmAmplitude);
        
        resultadosCanal.setPromedioFrecuenciasRitmoAlpha(averageAlphaRithmFrequency);
        resultadosCanal.setPromedioFrecuenciasRitmoBeta(averageBetaRithmFrequency);
        resultadosCanal.setPromedioFrecuenciasRitmoDelta(averageDeltaRithmFrequency);
        resultadosCanal.setPromedioFrecuenciasRitmoTheta(averageThetaRithmFrequency);
        
        resultadosCanal.setPromedioAmplitudesFrecuenciaAlpha(averageAlphaFrequencyAmplitude);
        resultadosCanal.setPromedioAmplitudesFrecuenciaBeta(averageBetaFrequencyAmplitude);
        resultadosCanal.setPromedioAmplitudesFrecuenciaDelta(averageDeltaFrequencyAmplitude);
        resultadosCanal.setPromedioAmplitudesFrecuenciaTheta(averageThetaFrequencyAmplitude);
        
        resultadosCanal.setPromedioFrecuenciasFrecuenciaAlpha(averageAlphaFrequencyFrequency);
        resultadosCanal.setPromedioFrecuenciasFrecuenciaBeta(averageBetaFrequencyFrequency);
        resultadosCanal.setPromedioFrecuenciasFrecuenciaDelta(averageDeltaFrequencyFrequency);
        resultadosCanal.setPromedioFrecuenciasFrecuenciaTheta(averageThetaFrequencyFrequency);
        
        resultadosCanal.setIsAnormal(Detector.isAnormal(averageChannelAplitudes, dominantFrequencyChannel, userAge));
        
        source.registrarResultadosCanal(resultadosCanal);
        
        System.out.println("channelPercentage: "+ channelPercentage);
       return channelPercentage;
    }
}
