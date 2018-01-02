/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class ResultadosCanal {
    private int idResultadosCanal;
    private Grabacion grabacion;
    private String canal;
    private String tipoOndaDominanteCanal;
    private double frecuenciaDominanteCanal;
    private double promedioAmplitudesCanal;
    
    private double porcentajeAparicionRitmoAlpha;
    private double porcentajeAparicionRitmoBeta;
    private double porcentajeAparicionRitmoDelta;
    private double porcentajeAparicionRitmoTheta;
    private double porcentajeAparicionFrecuenciaAlpha;
    private double porcentajeAparicionFrecuenciaBeta;
    private double porcentajeAparicionFrecuenciaDelta;
    private double porcentajeAparicionFrecuenciaTheta;
    private double promedioAmplitudesRitmoAlpha;
    private double promedioAmplitudesRitmoBeta;
    private double promedioAmplitudesRitmoDelta;
    private double promedioAmplitudesRitmoTheta;
    private double promedioAmplitudesFrecuenciaAlpha;
    private double promedioAmplitudesFrecuenciaBeta;
    private double promedioAmplitudesFrecuenciaDelta;
    private double promedioAmplitudesFrecuenciaTheta;
    private double promedioFrecuenciasRitmoAlpha;
    private double promedioFrecuenciasRitmoBeta;
    private double promedioFrecuenciasRitmoDelta;
    private double promedioFrecuenciasRitmoTheta;
    private double promedioFrecuenciasFrecuenciaAlpha;
    private double promedioFrecuenciasFrecuenciaBeta;
    private double promedioFrecuenciasFrecuenciaDelta;
    private double promedioFrecuenciasFrecuenciaTheta;
    private boolean anormal;
    
    public ResultadosCanal(){}
    
    public int getIdResultadosCanal(){
        return this.idResultadosCanal;
    }
    
    public Grabacion getGrabacion(){
        return this.grabacion;
    }
    
    public String getCanal(){
        return this.canal;
    }
    
    public String getTipoOndaDominanteCanal(){
        return this.tipoOndaDominanteCanal;
    }
    
    public double getFrecuenciaDominanteCanal(){
        return this.frecuenciaDominanteCanal;
    }
    
    public double getPromedioAmplitudesCanal(){
        return this.promedioAmplitudesCanal;
    }
    
    public double getPorcentajeAparicionRitmoAlpha(){
        return this.porcentajeAparicionRitmoAlpha;
    }
    
    public double getPorcentajeAparicionRitmoBeta(){
        return this.porcentajeAparicionRitmoBeta;
    }
    
    public double getPorcentajeAparicionRitmoDelta(){
        return this.porcentajeAparicionRitmoDelta;
    }
    
    public double getPorcentajeAparicionRitmoTheta(){
        return this.porcentajeAparicionRitmoTheta;
    }
    
    public double getPorcentajeAparicionFrecuenciaAlpha(){
        return this.porcentajeAparicionFrecuenciaAlpha;
    }
    
    public double getPorcentajeAparicionFrecuenciaBeta(){
        return this.porcentajeAparicionFrecuenciaBeta;
    }
    
    public double getPorcentajeAparicionFrecuenciaDelta(){
        return this.porcentajeAparicionFrecuenciaDelta;
    }
    
    public double getPorcentajeAparicionFrecuenciaTheta(){
        return this.porcentajeAparicionFrecuenciaTheta;
    }
    
    public double getPromedioAmplitudesRitmoAlpha(){
        return this.promedioAmplitudesRitmoAlpha;
    }
    
    public double getPromedioAmplitudesRitmoBeta(){
        return this.promedioAmplitudesRitmoBeta;
    }
    
    public double getPromedioAmplitudesRitmoDelta(){
        return this.promedioAmplitudesRitmoDelta;
    }
    
    public double getPromedioAmplitudesRitmoTheta(){
        return this.promedioAmplitudesRitmoTheta;
    }
    
    public double getPromedioAmplitudesFrecuenciaAlpha(){
        return this.promedioAmplitudesFrecuenciaAlpha;
    }
    
    public double getPromedioAmplitudesFrecuenciaBeta(){
        return this.promedioAmplitudesFrecuenciaBeta;
    }
    
    public double getPromedioAmplitudesFrecuenciaDelta(){
        return this.promedioAmplitudesFrecuenciaDelta;
    }
    
    public double getPromedioAmplitudesFrecuenciaTheta(){
        return this.promedioAmplitudesFrecuenciaTheta;
    }
    
    public double getPromedioFrecuenciasRitmoAlpha(){
        return this.promedioFrecuenciasRitmoAlpha;
    }
    
    public double getPromedioFrecuenciasRitmoBeta(){
        return this.promedioFrecuenciasRitmoBeta;
    }
    
    public double getPromedioFrecuenciasRitmoDelta(){
        return this.promedioFrecuenciasRitmoDelta;
    }
    
    public double getPromedioFrecuenciasRitmoTheta(){
        return this.promedioFrecuenciasRitmoTheta;
    }
    
    public double getPromedioFrecuenciasFrecuenciaAlpha(){
        return this.promedioFrecuenciasFrecuenciaAlpha;
    }
    
    public double getPromedioFrecuenciasFrecuenciaBeta(){
        return this.promedioFrecuenciasFrecuenciaBeta;
    }
    
    public double getPromedioFrecuenciasFrecuenciaDelta(){
        return this.promedioFrecuenciasFrecuenciaDelta;
    }
    
    public double getPromedioFrecuenciasFrecuenciaTheta(){
        return this.promedioFrecuenciasFrecuenciaTheta;
    }
    
    public boolean isAnormal(){
        return this.anormal;
    }
    
    /**
     *
     * @param idResultadosCanal
     */
    public void setIdResultadosCanal(int idResultadosCanal){
        this.idResultadosCanal = idResultadosCanal;
    }
    
    /**
     *
     * @param grabacion
     */
    public void setGrabacion(Grabacion grabacion){
        this.grabacion = grabacion;
    }
    
    /**
     *
     * @param canal
     */
    public void setCanal(String canal){
        this.canal = canal;
    }
    
    /**
     *
     * @param tipoOndaDominanteCanal
     */
    public void setTipoOndaDominanteCanal(String tipoOndaDominanteCanal){
        this.tipoOndaDominanteCanal = tipoOndaDominanteCanal;
    }
    
    /**
     *
     * @param frecuenciaDominanteCanal
     */
    public void setFrecuenciaDominanteCanal(double frecuenciaDominanteCanal){
        this.frecuenciaDominanteCanal = frecuenciaDominanteCanal;
    }
    
    /**
     *
     * @param promedioAmplitudesCanal
     */
    public void setPromedioAmplitudesCanal(double promedioAmplitudesCanal){
        this.promedioAmplitudesCanal = promedioAmplitudesCanal;
    }
    
    /**
     *
     * @param porcentajeAparicionRitmoAlpha
     */
    public void setPorcentajeAparicionRitmoAlpha(double porcentajeAparicionRitmoAlpha){
        this.porcentajeAparicionRitmoAlpha = porcentajeAparicionRitmoAlpha;
    }
    
    /**
     *
     * @param porcentajeAparicionRitmoBeta
     */
    public void setPorcentajeAparicionRitmoBeta(double porcentajeAparicionRitmoBeta){
        this.porcentajeAparicionRitmoBeta = porcentajeAparicionRitmoBeta;
    }
    
    /**
     *
     * @param porcentajeAparicionRitmoDelta
     */
    public void setPorcentajeAparicionRitmoDelta(double porcentajeAparicionRitmoDelta){
        this.porcentajeAparicionRitmoDelta = porcentajeAparicionRitmoDelta;
    }

    /**
     *
     * @param porcentajeAparicionRitmoTheta
     */
    public void setPorcentajeAparicionRitmoTheta(double porcentajeAparicionRitmoTheta){
        this.porcentajeAparicionRitmoTheta = porcentajeAparicionRitmoTheta;
    }
    
    /**
     *
     * @param porcentajeAparicionFrecuenciaAlpha
     */
    public void setPorcentajeAparicionFrecuenciaAlpha(double porcentajeAparicionFrecuenciaAlpha){
        this.porcentajeAparicionFrecuenciaAlpha = porcentajeAparicionFrecuenciaAlpha;
    }
    
    /**
     *
     * @param porcentajeAparicionFrecuenciaBeta
     */
    public void setPorcentajeAparicionFrecuenciaBeta(double porcentajeAparicionFrecuenciaBeta){
        this.porcentajeAparicionFrecuenciaBeta = porcentajeAparicionFrecuenciaBeta;
    }
    
    /**
     *
     * @param porcentajeAparicionFrecuenciaDelta
     */
    public void setPorcentajeAparicionFrecuenciaDelta(double porcentajeAparicionFrecuenciaDelta){
        this.porcentajeAparicionFrecuenciaDelta = porcentajeAparicionFrecuenciaDelta;
    }
    
    /**
     *
     * @param porcentajeAparicionFrecuenciaTheta
     */
    public void setPorcentajeAparicionFrecuenciaTheta(double porcentajeAparicionFrecuenciaTheta){
        this.porcentajeAparicionFrecuenciaTheta = porcentajeAparicionFrecuenciaTheta;
    }
    
    /**
     *
     * @param promedioAmplitudesRitmoAlpha
     */
    public void setPromedioAmplitudesRitmoAlpha(double promedioAmplitudesRitmoAlpha){
        this.promedioAmplitudesRitmoAlpha = promedioAmplitudesRitmoAlpha;
    }
            
    /**
     *
     * @param promedioAmplitudesRitmoBeta
     */
    public void setPromedioAmplitudesRitmoBeta(double promedioAmplitudesRitmoBeta){
        this.promedioAmplitudesRitmoBeta = promedioAmplitudesRitmoBeta;
    }
    
    /**
     *
     * @param promedioAmplitudesRitmoDelta
     */
    public void setPromedioAmplitudesRitmoDelta(double promedioAmplitudesRitmoDelta){
        this.promedioAmplitudesRitmoDelta = promedioAmplitudesRitmoDelta;
    }
    
    /**
     *
     * @param promedioAmplitudesRitmoTheta
     */
    public void setPromedioAmplitudesRitmoTheta(double promedioAmplitudesRitmoTheta){
        this.promedioAmplitudesRitmoTheta = promedioAmplitudesRitmoTheta;
    }
    
    /**
     *
     * @param promedioAmplitudesFrecuenciaAlpha
     */
    public void setPromedioAmplitudesFrecuenciaAlpha(double promedioAmplitudesFrecuenciaAlpha){
        this.promedioAmplitudesFrecuenciaAlpha = promedioAmplitudesFrecuenciaAlpha;
    }
    
    /**
     *
     * @param promedioAmplitudesFrecuenciaBeta
     */
    public void setPromedioAmplitudesFrecuenciaBeta(double promedioAmplitudesFrecuenciaBeta){
        this.promedioAmplitudesFrecuenciaBeta = promedioAmplitudesFrecuenciaBeta;
    }
    
    /**
     *
     * @param promedioAmplitudesFrecuenciaDelta
     */
    public void setPromedioAmplitudesFrecuenciaDelta(double promedioAmplitudesFrecuenciaDelta){
        this.promedioAmplitudesFrecuenciaDelta = promedioAmplitudesFrecuenciaDelta;
    }
    
    /**
     *
     * @param promedioAmplitudesFrecuenciaTheta
     */
    public void setPromedioAmplitudesFrecuenciaTheta(double promedioAmplitudesFrecuenciaTheta){
        this.promedioAmplitudesFrecuenciaTheta = promedioAmplitudesFrecuenciaTheta;
    }
    
    /**
     *
     * @param promedioFrecuenciasRitmoAlpha
     */
    public void setPromedioFrecuenciasRitmoAlpha(double promedioFrecuenciasRitmoAlpha){
        this.promedioFrecuenciasRitmoAlpha = promedioFrecuenciasRitmoAlpha;
    }
            
    /**
     *
     * @param promedioFrecuenciasRitmoBeta
     */
    public void setPromedioFrecuenciasRitmoBeta(double promedioFrecuenciasRitmoBeta){
        this.promedioFrecuenciasRitmoBeta = promedioFrecuenciasRitmoBeta;
    }
    
    /**
     *
     * @param promedioFrecuenciasRitmoDelta
     */
    public void setPromedioFrecuenciasRitmoDelta(double promedioFrecuenciasRitmoDelta){
        this.promedioFrecuenciasRitmoDelta = promedioFrecuenciasRitmoDelta;
    }
    
    /**
     *
     * @param promedioFrecuenciasRitmoTheta
     */
    public void setPromedioFrecuenciasRitmoTheta(double promedioFrecuenciasRitmoTheta){
        this.promedioFrecuenciasRitmoTheta = promedioFrecuenciasRitmoTheta;
    }
   
    /**
     *
     * @param promedioFrecuenciasFrecuenciaAlpha
     */
    public void setPromedioFrecuenciasFrecuenciaAlpha(double promedioFrecuenciasFrecuenciaAlpha){
        this.promedioFrecuenciasFrecuenciaAlpha = promedioFrecuenciasFrecuenciaAlpha;
    }
    /**
     *
     * @param promedioFrecuenciasFrecuenciaBeta
     */
    public void setPromedioFrecuenciasFrecuenciaBeta(double promedioFrecuenciasFrecuenciaBeta){
        this.promedioFrecuenciasFrecuenciaBeta = promedioFrecuenciasFrecuenciaBeta;
    }
    /**
     *
     * @param promedioFrecuenciasFrecuenciaDelta
     */
    public void setPromedioFrecuenciasFrecuenciaDelta(double promedioFrecuenciasFrecuenciaDelta){
        this.promedioFrecuenciasFrecuenciaDelta = promedioFrecuenciasFrecuenciaDelta;
    }
    /**
     *
     * @param promedioFrecuenciasFrecuenciaTheta
     */
    public void setPromedioFrecuenciasFrecuenciaTheta(double promedioFrecuenciasFrecuenciaTheta){
        this.promedioFrecuenciasFrecuenciaTheta = promedioFrecuenciasFrecuenciaTheta;
    }
    
    public void setIsAnormal(boolean anormal){
        this.anormal = anormal;
    }
    
}
