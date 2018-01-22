/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classifiereeg.analyzer;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class Detector {
    private static final String DELTA_RHYTHM_STR = "Ritmo-delta";
    private static final String DELTA_FREQUENCY_STR = "Frecuencia-delta";
    private static final String THETA_RHYTHM_STR = "'Ritmo-theta";
    private static final String THETA_FREQUENCY_STR = "Frecuencia-theta";
    private static final String ALPHA_RHYTHM_STR = "Ritmo-alpha";
    private static final String ALPHA_FREQUENCY_STR = "Frecuencia-alpha";
    private static final String BETA_RHYTHM_STR = "Ritmo-beta";
    private static final String BETA_FREQUENCY_STR = "Frecuencia-beta";
    private static final String GAMMA_RHYTHM_STR = "Ritmo-gamma";
    private static final String GAMMA_FREQUENCY_STR = "Frecuencia-gamma";
    private static final String NOT_ANALYZED_STR = "No-asignado";
    
    public static final int DELTA_RHYTHM = 0;
    public static final int DELTA_FREQUENCY = 1;
    public static final int THETA_RHYTHM = 2;
    public static final int THETA_FREQUENCY = 3;
    public static final int ALPHA_RHYTHM = 4;
    public static final int ALPHA_FREQUENCY = 5;
    public static final int BETA_RHYTHM = 6;
    public static final int BETA_FREQUENCY = 7;
    public static final int GAMMA_RHYTHM = 9;
    public static final int GAMMA_FREQUENCY = 10;
    public static final int NOT_ANALYZED = 8;
    
    public static double getAmplitude(double[] data){
        //System.out.println("abs:");
        double[] abs = getAbs(data);
        for(int i=0;i<abs.length;i++)
            System.out.print(abs[i]+",");
        System.out.println();
        double max = getMax(abs);
            System.out.println("max: "+ max);
        
        return max;
    }
    
    public static double getFrequency(double[] sampleData, int sampleRate){
        /* sampleData + zero padding */
        DoubleFFT_1D fft = new DoubleFFT_1D(sampleData.length + 24 * sampleData.length);
        double[] a = new double[(sampleData.length + 24 * sampleData.length) * 2];

        System.arraycopy(Filter.applyWindow(sampleData), 0, a, 0, sampleData.length);
        fft.realForward(a);

        /* find the peak magnitude and it's index */
        double maxMag = Double.NEGATIVE_INFINITY;
        int maxInd = -1;

        for(int i = 0; i < a.length / 2; ++i) {
            double re  = a[2*i];
            double im  = a[2*i+1];
            double mag = Math.sqrt(re * re + im * im);

            if(mag > maxMag) {
                maxMag = mag;
                maxInd = i;
            }
        }

        /* calculate the frequency */
        return (double)sampleRate * maxInd / (a.length / 2);
    }
    
    public static boolean isAnormal(double amplitude, double frequency, int age){
        return false;
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
    
    
    
    // this is a variation f
    public static int getWaveType2(double frequency, double amplitude){
        if ((frequency > 0 && frequency < 4) && (amplitude > (63.75/2) && amplitude < (127.5/2)))
            return DELTA_RHYTHM; //Ritmo Delta
        else if ((frequency > 0 && frequency < 4) && (amplitude < (63.75/2) || amplitude > (127.5/2)))
            return DELTA_FREQUENCY; //Frecuencia Delta
        else if ((frequency >= 4 && frequency < 7) && (amplitude > (63.75/2) && amplitude < (95.625/2)))
            return THETA_RHYTHM; //Ritmo Theta
        else if ((frequency >= 4 && frequency < 7) && (amplitude < (63.75/2) || amplitude > (95.625/2)))
            return THETA_FREQUENCY; //Frecuencia Theta
        else if ((frequency >= 8 && frequency < 13) && ((amplitude > (25.5/2) && amplitude < (76.5/2)) || (amplitude > (127.5/2) && amplitude < (255/2))) )
            return ALPHA_RHYTHM; //'Ritmo Alpha'
        else if ((frequency >= 8 && frequency < 13) && ((amplitude < (25.5/2)) || (amplitude > (76.5/2) && amplitude < (127.5/2)) || (amplitude > (255/2))) )
            return ALPHA_FREQUENCY; //Frecuencia Alpha
        else if ((frequency >= 13 && frequency < 30) && ((amplitude > (6.375/2) && amplitude < (12.75/2)) || (amplitude > (19.125/2) && amplitude < (31.875/2))))
            return BETA_RHYTHM; //Ritmo Beta
        else if ((frequency >= 13 && frequency < 30) && ((amplitude < (6.375/2)) || (amplitude > (12.75/2) && amplitude < (19.125/2)) || (amplitude > (31.875/2))))
            return BETA_FREQUENCY; //Frecuencia Beta
        else
            return NOT_ANALYZED; //No Analizada
    }
    
    // this is a variation f
    public static int getWaveType3(double frequency, double amplitude){
        if ((frequency > 0 && frequency < 4) && (amplitude > (63.75/2) && amplitude < (127.5/2)))
            return DELTA_RHYTHM; //Ritmo Delta
        else if ((frequency > 0 && frequency < 4) && (amplitude < (63.75/2) || amplitude > (127.5/2)))
            return DELTA_FREQUENCY; //Frecuencia Delta
        else if ((frequency >= 4 && frequency < 7) && (amplitude > (63.75/2) && amplitude < (95.625/2)))
            return THETA_RHYTHM; //Ritmo Theta
        else if ((frequency >= 4 && frequency < 7) && (amplitude < (63.75/2) || amplitude > (95.625/2)))
            return THETA_FREQUENCY; //Frecuencia Theta
        else if ((frequency >= 8 && frequency < 13) && ((amplitude > (25.5/2) && amplitude < (76.5/2)) || (amplitude > (127.5/2) && amplitude < (255/2))) )
            return ALPHA_RHYTHM; //'Ritmo Alpha'
        else if ((frequency >= 8 && frequency < 13) && ((amplitude < (25.5/2)) || (amplitude > (76.5/2) && amplitude < (127.5/2)) || (amplitude > (255/2))) )
            return ALPHA_FREQUENCY; //Frecuencia Alpha
        else if ((frequency >= 13 && frequency < 30) && ((amplitude > (6.375/2) && amplitude < (12.75/2)) || (amplitude > (19.125/2) && amplitude < (31.875/2))))
            return BETA_RHYTHM; //Ritmo Beta
        else if ((frequency >= 13 && frequency < 30) && ((amplitude < (6.375/2)) || (amplitude > (12.75/2) && amplitude < (19.125/2)) || (amplitude > (31.875/2))))
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
    
    public static Map<Integer, Integer> getWaveTypePercentage(List<Integer> data){
        Map<Integer, Integer> counter = new TreeMap<>();
        
        for(int i=0;i<data.size();i++)
                counter.put(data.get(i),counter.get(data.get(i)) == null? 1 : counter.get(data.get(i)) + 1);

        return counter;
    }
}
