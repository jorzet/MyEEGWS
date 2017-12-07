package entities;

/**
 * @author Jorge Zepeda Tinoco
 * @version 1.0
 * @created 02-Jul-2017 1:24:04 PM
 */
public class Grabacion {

    private int idGrabacion;
    private Cita cita;
    private Paciente paciente;
    private String nombreArchivo;
    
    public Grabacion(){

    }

   
    public int getIdGrabacion(){
        return this.idGrabacion;
    }

    public Cita getCita(){
        return this.cita;
    }
    
    public Paciente getPaciente(){
        return this.paciente;
    }

    public String getNombreArchivo(){
        return this.nombreArchivo;
    }
    /**
     *
     * @param idGrabacion
     */
    public void setIdGrabacion(int idGrabacion){
        this.idGrabacion = idGrabacion;
    }
    /**
     *
     * @param cita
     */
    public void setCita(Cita cita){
        this.cita = cita;
    }
    
    /**
     *
     * @param paciente
     */
    public void setPaciente(Paciente paciente){
        this.paciente = paciente;
    }
    /**
     *
     * @param nombreArchivo
     */
    public void setNombreArchivo(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
    }
}//end Grabaci�n