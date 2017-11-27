/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifiereeg.analyzer;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class Detector {
    private static final String DELTA_RHYTHM_STR = "DELTA RHYTHM";
    private static final String DELTA_FREQUENCY_STR = "DELTA FREQUENCY";
    private static final String THETA_RHYTHM_STR = "THETA RHYTHM";
    private static final String THETA_FREQUENCY_STR = "THETA FREQUENCY";
    private static final String ALPHA_RHYTHM_STR = "ALPHA RHYTHM";
    private static final String ALPHA_FREQUENCY_STR = "ALPHA FREQUENCY";
    private static final String BETA_RHYTHM_STR = "BETA RHYTHM";
    private static final String BETA_FREQUENCY_STR = "BETA FREQUENCY";
    private static final String GAMMA_RHYTHM_STR = "GAMMA RHYTHM";
    private static final String GAMMA_FREQUENCY_STR = "GAMMA FREQUENCY";
    private static final String NOT_ANALYZED_STR = "NOT ANALYZED";
    
    private static final int DELTA_RHYTHM = 0;
    private static final int DELTA_FREQUENCY = 1;
    private static final int THETA_RHYTHM = 2;
    private static final int THETA_FREQUENCY = 3;
    private static final int ALPHA_RHYTHM = 4;
    private static final int ALPHA_FREQUENCY = 5;
    private static final int BETA_RHYTHM = 6;
    private static final int BETA_FREQUENCY = 7;
    private static final int GAMMA_RHYTHM = 9;
    private static final int GAMMA_FREQUENCY = 10;
    private static final int NOT_ANALYZED = 8;
    
    public static double getAmplitude(double[] data){
        //System.out.println("abs:");
        double[] abs = getAbs(data);
        //for(int i=0;i<abs.length;i++)
        //    System.out.println(abs[i]+",");
        double max = getMean(abs);
        //System.out.println("max: "+ max);
        
        return max * 2;
    }
    
    public static double getFrequency(double[] data, int fm){
        double[] vF = Filter.hanning(data);
       // System.out.println("vf");
        //for(int i=0;i<vF.length;i++)
            //System.out.println(vF[i]+", ");
        Complex[] x = new Complex[vF.length];
        //System.out.println("Complex");
        for(int i=0;i<vF.length;i++){
            x[i] = new Complex(vF[i], 0);
            //System.out.println(x[i]+",");
        }
        //System.out.println("FFT ");
        Complex[] V = FFT.fft(x);
        //for(int i=0;i<V.length;i++)
           // System.out.println(V[i]+",");
        
        //System.out.println("ABS ");
        double[] absV = new double[V.length];
        for(int i=0;i<V.length;i++){
            absV[i]=V[i].abs();
            //System.out.println(absV[i]+",");
        }
        int L = data.length;
        double[] f = new double[L]; 
        for(int i=1;i<L;i++)
            f[i] = (i*(fm/2))/(L-1);
        
        int pos = getMaxPos(absV);
        
        return f[pos];
        //return getMax(absV);
    }
    public static String getWaveTypeString(int data){
        switch(data){
            case 0:
                return DELTA_RHYTHM_STR;
            case 1:
                return DELTA_FREQUENCY_STR;
            case 2:
                return THETA_RHYTHM_STR;
            case 3:
                return THETA_FREQUENCY_STR;
            case 4:
                return ALPHA_RHYTHM_STR;
            case 5: 
                return ALPHA_FREQUENCY_STR;
            case 6:
                return BETA_RHYTHM_STR;
            case 7:
                return BETA_FREQUENCY_STR;
            default:
                return NOT_ANALYZED_STR;
        }
    }
    public static int getWaveType(double frequency, double amplitude){
        if ((frequency > 0 && frequency < 4) && (amplitude > 50 && amplitude < 100))
            return DELTA_RHYTHM; //Ritmo Delta
        else if ((frequency > 0 && frequency < 4) && (amplitude < 50 || amplitude > 100))
            return DELTA_FREQUENCY; //Frecuencia Delta
        else if ((frequency >= 4 && frequency < 7) && (amplitude > 50 && amplitude < 75))
            return THETA_RHYTHM; //Ritmo Theta
        else if ((frequency >= 4 && frequency < 7) && (amplitude < 50 || amplitude > 75))
            return THETA_FREQUENCY; //Frecuencia Theta
        else if ((frequency >= 8 && frequency < 13) && ((amplitude > 20 && amplitude < 60) || (amplitude > 100 && amplitude < 200)) )
            return ALPHA_RHYTHM; //'Ritmo Alpha'
        else if ((frequency >= 8 && frequency < 13) && ((amplitude < 20) || (amplitude > 60 && amplitude < 100) || (amplitude > 200)) )
            return ALPHA_FREQUENCY; //Frecuencia Alpha
        else if ((frequency >= 13 && frequency < 30) && ((amplitude > 5 && amplitude < 10) || (amplitude > 15 && amplitude < 25)))
            return BETA_RHYTHM; //Ritmo Beta
        else if ((frequency >= 13 && frequency < 30) && ((amplitude < 5) || (amplitude > 10 && amplitude < 15) || (amplitude > 25)))
            return BETA_FREQUENCY; //Frecuencia Beta
        else
            return NOT_ANALYZED; //No Analizada
    }
    
    public static double getMean(double[] data) {
        double sum = 0.0;
        if (data.length == 0) 
            return sum;
        
        for (int i = 0; i != data.length; ++i) 
            sum += data[i];
        
        return sum / data.length;
    }
    
    private static double getMax(double[] data){
        return Arrays.stream(data).max().getAsDouble();
    }
    
    private static double getMin(double[] data){
        return Arrays.stream(data).min().getAsDouble();
    }
    
    private double getAverage(double[] data){
        return Arrays.stream(data).average().getAsDouble();
    }
    
    private static double[] getAbs(double[] data){
        double[] res = new double[data.length];
        for(int i=0;i<data.length;i++)
            res[i] = Math.abs(data[i]);
        return res;
    }
    public static int getMaxPos(double[] inputArray){ 
        double maxValue = inputArray[0]; 
        int index = 0;
        for(int i=1;i < inputArray.length;i++){ 
          if(inputArray[i] > maxValue){ 
             maxValue = inputArray[i]; 
             index=i;
          } 
        } 
        return index; 
    }
    
    public static Map<Integer, Integer> getWaveTypePercentage(int[] data){
        Map<Integer, Integer> counter = new TreeMap<Integer, Integer>();
        for(int i=0;i<data.length;i++){
            counter.put(data[i],counter.get(data[i]) == null ? 1 : counter.get(data[i]) + 1);
        }
        
        //for(int i=0;i<9;i++)
        //    counter.put(i,counter.get(i)/data.length);
        
        return counter;
    }
}