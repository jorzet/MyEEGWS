package entities;

import java.util.Date;

/**
 * @author Jorge Zepeda Tinoco
 * @version 1.0
 * @created 02-Jul-2017 1:24:04 PM
 */
public class Cita {
        private int folioCita;
        private Paciente paciente;
	private String fecha;
	private String hora;
        private String duracion;
	private String observaciones;
        private String[] electrodos;

	public Cita(){

	}

        public int getFolioCita(){
            return this.folioCita;
        }
        
        public Paciente getPaciente(){
            return this.paciente;
        }
        
	public String getFecha(){
		return this.fecha;
	}

	public String getHora(){
		return this.hora;
	}
        
        public String getDuracion(){
            return this.duracion;
        }

	public String getObservaciones(){
		return this.observaciones;
	}

        public String[] getElectrodos(){
            return this.electrodos;
        }

        public void setFolioCita(int folioCita){
            this.folioCita=folioCita;
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
	 * @param fecha
	 */
	public void setFecha(String fecha){
		this.fecha = fecha;
	}

	/**
	 * 
	 * @param hora
	 */
	public void setHora(String hora){
		this.hora = hora;
	}
        /**
	 * 
	 * @param duracion
	 */
        public void setDuracion(String duracion){
            this.duracion = duracion;
        }
	/**
	 * 
	 * @param observaciones
	 */
	public void setObservaciones(String observaciones){
            this.observaciones = observaciones;
	}
        
        public void setElectrodos(String[] electrodos){
            this.electrodos = electrodos;
        }

}//end Cita