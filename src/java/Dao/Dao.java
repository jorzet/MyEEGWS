/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import ConnectionDB.DataSource;
import businessRules.JSONErrorBuilder;
import businessRules.Palabras;
import com.google.gson.Gson;
import entities.Administrador;
import entities.Cita;
import entities.Dispositivo;
import entities.Grabacion;
import entities.Especialista;
import entities.Paciente;
import entities.ResultadosCanal;
import entities.ResultadosGenerales;
import entities.ResultadosSegmento;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import security.AccessToken;
/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class Dao {
    Palabras words = new Palabras();
    DataSource origen = null;
    CallableStatement sp=null;
    Statement stmt = null;
    ResultSet rs = null;
    String SQL = null;
    ResultSetMetaData rmeta = null;
    String resultado = null;
    
    Administrador administrador;
    Especialista especialista;
    Paciente paciente;
    Cita cita;
    Grabacion grabacion;
    Dispositivo dispositivo;
    ResultadosGenerales resultadoGeneral;
    ResultadosSegmento resultadoSegmento;
    ResultadosCanal resultadoCanal;
    
    ArrayList<Especialista> especialistas;
    ArrayList<Paciente> pacientes;
    ArrayList<Cita> citas;
    ArrayList<Grabacion> grabaciones;
    ArrayList<Dispositivo> dispositivos;
    ArrayList<ResultadosGenerales> resultadosGenerales;
    ArrayList<ResultadosSegmento> resultadosSegmentos;
    ArrayList<ResultadosCanal> resultadosCanales;
    
    public Dao(){}
    /**
     *
     * @param paciente
     * @return
     */
    public String registrarAdministrador(Administrador administrador){
        String resultado = "";
        origen = new DataSource();
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call insertarAdmin (?,?,?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, administrador.getName());
                sp.setString(2, administrador.getFirstLastName());
                sp.setString(3, administrador.getSecondLastName());
                sp.setString(4, administrador.getEmail());
                sp.setString(5, administrador.getPassword());
                sp.setString(6, administrador.getGender());
                sp.registerOutParameter(7, java.sql.Types.VARCHAR);
                sp.execute();
                resultado = sp.getString(7);
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = e.getMessage().toString();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultado;
    }
    
    /**
     *
     * @param paciente
     * @return
     */
    public String registrarPaciente(Paciente paciente){
        String resultado = "";
        origen = new DataSource();
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call insertarPaciente (?,?,?,?,?,?,?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, paciente.getEspecialista().getId());
                sp.setString(2, paciente.getName());
                sp.setString(3, paciente.getFirstLastName());
                sp.setString(4, paciente.getSecondLastName());
                sp.setString(5, paciente.getPadecimiento());
                sp.setInt(6, paciente.getAge());
                sp.setString(7, paciente.getEmail());
                sp.setString(8, paciente.getPassword());
                sp.setString(9, paciente.getGender());
                sp.setBytes(10, paciente.getPrifilePhoto());
                sp.registerOutParameter(11, java.sql.Types.VARCHAR);
                sp.execute();
                resultado = sp.getString(11);
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = e.getMessage().toString();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultado;
    }
    
    public String registrarEspecialista(Especialista especialista){
        String resultado = "";
        origen = new DataSource();
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call insertarEspecialista (?,?,?,?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, especialista.getName());
                sp.setString(2, especialista.getFirstLastName());
                sp.setString(3, especialista.getSecondLastName());
                sp.setString(4, especialista.getEmail());
                sp.setString(5, especialista.getPassword());
                sp.setString(6, especialista.getGender());
                sp.setBytes(7, especialista.getPrifilePhoto());
                sp.registerOutParameter(8, java.sql.Types.NVARCHAR);
                sp.execute();
                resultado = sp.getString(8);
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = e.getMessage().toString();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultado;
    }
    
    public String almacenarDispositivoPorUsuario(int idPatient, String deviceName, String deviceMacAddress){
        String resultado = "";
        origen = new DataSource();
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call insertarDispositivo (?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idPatient);
                sp.setString(2, deviceName);
                sp.setString(3, deviceMacAddress);
                sp.registerOutParameter(4, java.sql.Types.NVARCHAR);
                sp.execute();
                resultado = sp.getString(4);
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = e.getMessage().toString();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultado;
    }
    
    public String registrarCita(Cita cita){
        String resultado = "";
        origen = new DataSource();
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call insertarCita (?,?,?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, cita.getPaciente().getId());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                
                java.sql.Date fecha = new java.sql.Date(format.parse(cita.getFecha()).getTime());
                sp.setDate(2, fecha);
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
                sp.setTime(3, new java.sql.Time(formatter.parse(cita.getHora()).getTime()));
                sp.setTime(4, new java.sql.Time(formatter.parse(cita.getDuracion()).getTime()));
                sp.setString(5, cita.getObservaciones());
                String electrodes = String.join(",", cita.getElectrodos());
                sp.setString(6, electrodes);
                sp.registerOutParameter(7, java.sql.Types.VARCHAR);
                sp.execute();
                resultado = sp.getString(7);
                System.out.println(resultado);
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = e.getMessage().toString();
        } catch (ParseException ex) {
            ex.printStackTrace();
            resultado = ex.getMessage().toString();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultado;
    }
    
    /**
     *
     * @param grabacion
     * @return
     */
    public int registrarGrabacion(Grabacion grabacion){
        
        origen = new DataSource();
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("nombreArchivo: "+grabacion.getNombreArchivo());
                SQL = "{call insertarGrabacion (?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, grabacion.getCita().getFolioCita());
                sp.setString(2, grabacion.getNombreArchivo());
                sp.registerOutParameter(3, java.sql.Types.INTEGER);
                sp.execute();
                return sp.getInt(3);
            } 
        }catch(SQLException e){
            e.printStackTrace();
            return -2;
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return -1;
    }
    
    
    public String registrarResultadosGenerales(ResultadosGenerales resultadosGenerales){
        String resultado = "";
        origen = new DataSource();
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call insertarResultadosGenerales (?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, resultadosGenerales.getCita().getFolioCita());
                sp.setString(2, resultadosGenerales.getZonaCerebral());
                sp.setString(3, resultadosGenerales.getTipoOndaDominante());
                sp.setDouble(4, resultadosGenerales.getPorcentajeTipoOnda());
                sp.registerOutParameter(5, java.sql.Types.VARCHAR);
                sp.execute();
                resultado = sp.getString(5);
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = e.getMessage().toString();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultado;
    }
    
    
    public String registrarResultadosSegmento(ResultadosSegmento resultadosSegmento){
        String resultado = "";
        origen = new DataSource();
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call insertarResultadosSegmento(?,?,?,?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                System.out.println("grabacion-------:   "+resultadosSegmento.getGrabacion().getIdGrabacion());
                sp.setInt(1, resultadosSegmento.getGrabacion().getIdGrabacion());
                sp.setInt(2, resultadosSegmento.getSegundo());
                sp.setString(3, resultadosSegmento.getCanal());
                sp.setDouble(4, resultadosSegmento.getFrecuenciaDominante());
                sp.setString(5, resultadosSegmento.getTipoOnda());
                sp.setString(6, resultadosSegmento.getSenal());
                sp.setBoolean(7, resultadosSegmento.isAnormal());
                sp.registerOutParameter(8, java.sql.Types.VARCHAR);
                sp.execute();
                resultado = sp.getString(8);
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = e.getMessage().toString();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultado;
    }
   
    
    public String registrarResultadosCanal(ResultadosCanal resultadosCanal){
        String resultado = "";
        origen = new DataSource();
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call insertarResultadosCanal (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, resultadosCanal.getGrabacion().getIdGrabacion());
                sp.setString(2, resultadosCanal.getCanal());
                sp.setString(3, resultadosCanal.getTipoOndaDominanteCanal());
                sp.setDouble(4, resultadosCanal.getFrecuenciaDominanteCanal());
                sp.setDouble(5, resultadosCanal.getPromedioAmplitudesCanal());
                
                sp.setDouble(6, resultadosCanal.getPorcentajeAparicionRitmoAlpha());
                sp.setDouble(7, resultadosCanal.getPorcentajeAparicionRitmoBeta());
                sp.setDouble(8, resultadosCanal.getPorcentajeAparicionRitmoDelta());
                sp.setDouble(9, resultadosCanal.getPorcentajeAparicionRitmoTheta());
                sp.setDouble(10, resultadosCanal.getPorcentajeAparicionFrecuenciaAlpha());
                sp.setDouble(11, resultadosCanal.getPorcentajeAparicionFrecuenciaBeta());
                sp.setDouble(12, resultadosCanal.getPorcentajeAparicionFrecuenciaDelta());
                sp.setDouble(13, resultadosCanal.getPorcentajeAparicionFrecuenciaTheta());
                
                sp.setDouble(14, resultadosCanal.getPromedioAmplitudesRitmoAlpha());
                sp.setDouble(15, resultadosCanal.getPromedioAmplitudesRitmoBeta());
                sp.setDouble(16, resultadosCanal.getPromedioAmplitudesRitmoDelta());
                sp.setDouble(17, resultadosCanal.getPromedioAmplitudesRitmoTheta());
                sp.setDouble(18, resultadosCanal.getPromedioAmplitudesFrecuenciaAlpha());
                sp.setDouble(19, resultadosCanal.getPromedioAmplitudesFrecuenciaBeta());
                sp.setDouble(20, resultadosCanal.getPromedioAmplitudesFrecuenciaDelta());
                sp.setDouble(21, resultadosCanal.getPromedioAmplitudesFrecuenciaTheta());
                
                sp.setDouble(22, resultadosCanal.getPromedioFrecuenciasRitmoAlpha());
                sp.setDouble(23, resultadosCanal.getPromedioFrecuenciasRitmoBeta());
                sp.setDouble(24, resultadosCanal.getPromedioFrecuenciasRitmoDelta());
                sp.setDouble(25, resultadosCanal.getPromedioFrecuenciasRitmoTheta());
                sp.setDouble(26, resultadosCanal.getPromedioFrecuenciasFrecuenciaAlpha());
                sp.setDouble(27, resultadosCanal.getPromedioFrecuenciasFrecuenciaBeta());
                sp.setDouble(28, resultadosCanal.getPromedioFrecuenciasFrecuenciaDelta());
                sp.setDouble(29, resultadosCanal.getPromedioFrecuenciasFrecuenciaTheta());
                
                sp.setBoolean(30, resultadosCanal.isAnormal());
                
                sp.registerOutParameter(31, java.sql.Types.VARCHAR);
                sp.execute();
                resultado = sp.getString(31);
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = e.getMessage().toString();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultado;
    }
    
    /**
     *
     * @param email
     * @param hashPassword
     * @return
     */
    public String obtenerDatosUsuario(String email, String hashPassword){
        String jsonQuery = "";
        paciente = new Paciente();
        especialista = new Especialista();
        administrador = new Administrador();
        int f = 0; 
        int tipoUsuario;
        origen = new DataSource();
        
        String message;
        int Error;
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call obtenerDatosUsuario (?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setString(1, email);
                sp.setString(2, hashPassword);
                sp.registerOutParameter(3, java.sql.Types.INTEGER);
                sp.execute();
                tipoUsuario = sp.getInt(3);
                
                rs = sp.getResultSet();
                switch (tipoUsuario) {
                    case Palabras.WITHOUT_ACOUNT:
                        jsonQuery = JSONErrorBuilder.buildJson(Palabras.CODE_ERROR_USER_NOT_EXISTS ,Palabras.ERROR_USER_NOT_EXISTS);
                        break;
                    case Palabras.ERROR_INCORRECT_PASSWORD:
                        jsonQuery = JSONErrorBuilder.buildJson(Palabras.CODE_ERROR_INCORRECT_EMAIL_OR_PASSWORD, Palabras.ERROR_INCORRECT_EMAIL_OR_PASSWORD);
                        break;
                    case Palabras.PATIENT_USER:
                        rs.beforeFirst();
                        while(rs.next()){
                            paciente.setId(rs.getInt(1));
                            paciente.setTipoUsuario(Palabras.PATIENT_TYPE);
                            especialista.setId(rs.getInt(2));
                            especialista.setName(rs.getString(3));
                            paciente.setEspecialista(especialista);
                            paciente.setName(rs.getString(4));
                            paciente.setFistLastName(rs.getString(5));
                            paciente.setSecondLastName(rs.getString(6));
                            paciente.setPadecimiento(rs.getString(7));
                            paciente.setAge(rs.getInt(8));
                            paciente.setEmail(rs.getString(9));
                            paciente.setGender(rs.getString(10));
                            paciente.setProfilePhoto(rs.getBytes(11));
                        }   
                        jsonQuery = new Gson().toJson(paciente);
                        break;
                    case Palabras.SPETIALIST_USER:
                        rs.beforeFirst();
                        while(rs.next()){
                            especialista.setId(rs.getInt(1));
                            especialista.setTipoUsuario(Palabras.SPETIALIST_TYPE);
                            especialista.setName(rs.getString(2));
                            especialista.setFistLastName(rs.getString(3));
                            especialista.setSecondLastName(rs.getString(4));
                            especialista.setEmail(rs.getString(5));
                            especialista.setGender(rs.getString(6));
                            especialista.setProfilePhoto(rs.getBytes(7));
                        }   jsonQuery = new Gson().toJson(especialista);
                        break;
                    case Palabras.ADMINISTRATOR_USER:
                        rs.beforeFirst();
                        while(rs.next()){
                            administrador.setId(rs.getInt(1));
                            administrador.setTipoUsuario(Palabras.ADMINISTRATOR_TYPE);
                            administrador.setName(rs.getString(2));
                            administrador.setFistLastName(rs.getString(3));
                            administrador.setSecondLastName(rs.getString(4));
                            administrador.setEmail(rs.getString(5));
                            administrador.setGender(rs.getString(7));
                        }   jsonQuery = new Gson().toJson(administrador);   
                        break;
                    default:
                        break;
                }
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = null;
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return jsonQuery;
    }
    
    /**
     *
     * @param idPaciente
     * @return
     */
    public Paciente mostrarDatosPaciente(int idPaciente){
        paciente = new Paciente();
        especialista = new Especialista();
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarDatosPaciente (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idPaciente);
                sp.registerOutParameter(2, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(2);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                    }
                    else{
                        rs.beforeFirst();
                        rs.next();
                        System.out.println("No es nulo"+rs);

                        paciente.setId(rs.getInt(1));
                        especialista.setId(rs.getInt(2));
                        paciente.setEspecialista(especialista);
                        paciente.setName(rs.getString(3));
                        paciente.setFistLastName(rs.getString(4));
                        paciente.setSecondLastName(rs.getString(5));
                        paciente.setPadecimiento(rs.getString(6));
                        paciente.setAge(rs.getInt(7));
                        paciente.setEmail(rs.getString(8));
                        paciente.setGender(rs.getString(9));
                        paciente.setProfilePhoto(rs.getBytes(10));
                        paciente.setPassword(rs.getString(11));
                    }
                }
                else 
                    return null;
                
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return paciente;
    }
    
    
    
    /**
     *
     * @param idEspecialita
     * @return
     */
    public Especialista mostrarDatosEspecialista(int idEspecialita){
        especialista = new Especialista();
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarDatosEspecialista (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idEspecialita);
                sp.registerOutParameter(2, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                boolean res = sp.getBoolean(2);
                while(rs.next()){
                    f = f+1;
                }
                if(f == 0){
                    System.out.println("Es nulo"+rs);
                }
                else{
                    rs.beforeFirst();
                    rs.next();
                    System.out.println("No es nulo"+rs);
                    especialista.setId(rs.getInt(1));
                    especialista.setName(rs.getString(2));
                    especialista.setFistLastName(rs.getString(3));
                    especialista.setSecondLastName(rs.getString(4));
                    especialista.setEmail(rs.getString(5));
                    especialista.setGender(rs.getString(6));
                    especialista.setProfilePhoto(rs.getBytes(7));
                    especialista.setPassword(rs.getString(8));
                }
                
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return especialista;
    }
    
    public ArrayList<Especialista> obtenerEspecialistas(){
        especialistas = new ArrayList<>();
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarEspecialistas (?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.registerOutParameter(1, java.sql.Types.BOOLEAN);
                rs = sp.executeQuery();
                resultado = sp.getString(1);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        System.out.println("No es nulo"+rs);
                        while(rs.next()){
                            especialista = new Especialista();
                            especialista.setId(rs.getInt(1));
                            especialista.setName(rs.getString(2));
                            especialista.setFistLastName(rs.getString(3));
                            especialista.setSecondLastName(rs.getString(4));
                            especialista.setEmail(rs.getString(5));
                            especialista.setGender(rs.getString(6));
                            especialista.setProfilePhoto(rs.getBytes(7));
                            especialistas.add(especialista);
                        }
                    }
                }else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return especialistas;
    }
    
    /**
     *
     * @param idEspecialista
     * @return
     */
    public ArrayList<Paciente> obtenerPacientesPorEspecialista(int idEspecialista){
        pacientes = new ArrayList<>();
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarPacientesDeEspecialista (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idEspecialista);
                sp.registerOutParameter(2, java.sql.Types.VARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(2);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        while(rs.next()){
                            System.out.println("Entro "+f);
                            paciente = new Paciente();
                            especialista = new Especialista();
                            
                            paciente.setId(rs.getInt(1));
                            especialista.setId(rs.getInt(2));
                            paciente.setEspecialista(especialista);
                            paciente.setName(rs.getString(3));
                            paciente.setFistLastName(rs.getString(4));
                            paciente.setSecondLastName(rs.getString(5));
                            paciente.setPadecimiento(rs.getString(6));
                            paciente.setAge(rs.getInt(7));
                            paciente.setEmail(rs.getString(8));
                            paciente.setGender(rs.getString(9));
                            paciente.setProfilePhoto(rs.getBytes(10));
                            pacientes.add(paciente);
                        }
                    }
                }
                else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return pacientes;
    }
    
    /**
     *
     * @param idCita
     * @param idPaciente
     * @return
     */
    public Cita obtenerCitaDePaciente(int idCita, int idPaciente){
        cita = new Cita();
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarCitaPaciente (?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idCita);
                sp.setInt(2, idPaciente);
                sp.registerOutParameter(3, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(3);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        while(rs.next()){
                            cita = new Cita();
                            cita.setFolioCita(rs.getInt(1));
                            paciente = new Paciente();
                            paciente.setId(rs.getInt(2));
                            cita.setPaciente(paciente);
                            Date date = rs.getDate(3);
                            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                            String fecha = df.format(date);
                            cita.setFecha(fecha);
                            cita.setHora(rs.getTime(4).toString());
                            cita.setDuracion(rs.getTime(5).toString());
                            cita.setObservaciones(rs.getString(6));
                            String[] electrodes = rs.getString(7).split(",");
                            cita.setElectrodos(electrodes);
                        }
                    }
                }
                else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return cita;
    }
    
    /**
     *
     * @param idPaciente
     * @return
     */
    public ArrayList<Cita> obtenerCitasDePaciente(int idPaciente){
        citas = new ArrayList<>();
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call obtenerCitasPaciente (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idPaciente);
                sp.registerOutParameter(2, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(2);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        while(rs.next()){
                            cita = new Cita();
                            cita.setFolioCita(rs.getInt(1));
                            paciente = new Paciente();
                            paciente.setId(rs.getInt(2));
                            cita.setPaciente(paciente);
                            Date date = rs.getDate(3);
                            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                            String fecha = df.format(date);
                            cita.setFecha(fecha);
                            cita.setHora(rs.getTime(4).toString());
                            cita.setDuracion(rs.getTime(5).toString());
                            cita.setObservaciones(rs.getString(6));
                            String[] electrodes = rs.getString(7).split(",");
                            cita.setElectrodos(electrodes);
                            citas.add(cita);
                        }
                    }
                }
                else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return citas;
    }
    
    public ArrayList<Cita> obtenerCitasDeEspecialista(int idEspecialista, boolean afterNow){
        citas = new ArrayList<>();
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                if(afterNow)
                    SQL = "{call obtenerProximasCitasPorEspecialista (?,?)}";
                else
                    SQL = "{call obtenerTodasCitasPorEspecialista (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idEspecialista);
                sp.registerOutParameter(2, java.sql.Types.VARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(2);
                System.out.println("resultado: "+resultado);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        System.out.println("entra 2");
                        rs.beforeFirst();
                        while(rs.next()){
                            cita = new Cita();
                            cita.setFolioCita(rs.getInt(1));
                            Date date = rs.getDate(3);
                            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                            String fecha = df.format(date);
                            cita.setFecha(fecha);
                            cita.setHora(rs.getTime(4).toString());
                            cita.setDuracion(rs.getTime(5).toString());
                            cita.setObservaciones(rs.getString(6));
                            String[] electrodes = rs.getString(7).split(",");
                            cita.setElectrodos(electrodes);
                            
                            paciente = new Paciente();
                            paciente.setId(rs.getInt(8));
                            especialista = new Especialista();
                            especialista.setId(rs.getInt(9));
                            paciente.setEspecialista(especialista);
                            paciente.setName(rs.getString(10));
                            paciente.setFistLastName(rs.getString(11));
                            paciente.setSecondLastName(rs.getString(12));
                            paciente.setPadecimiento(rs.getString(13));
                            paciente.setAge(rs.getInt(14));
                            paciente.setEmail(rs.getString(15));
                            paciente.setGender(rs.getString(16));
                            paciente.setProfilePhoto(rs.getBytes(17));
                            cita.setPaciente(paciente);
                            System.out.println("pasa ");
                            citas.add(cita);
                        }
                    }
                }
                else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return citas;
    }
    
    public ResultadosGenerales obtenerEstudioPaciente(int folioCita, int idPaciente){
        resultadoGeneral = new ResultadosGenerales();
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarEstudioPaciente (?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, folioCita);
                sp.setInt(2, idPaciente);
                sp.registerOutParameter(3, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(3);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        rs.next();
                        grabacion = new Grabacion();
                        cita = new Cita();
                        
                        
                        cita.setFolioCita(rs.getInt(1));
                        resultadoGeneral.setCita(cita);
                        resultadoGeneral.setIdResultadosGenerales(rs.getInt(2));
                        Date date = rs.getDate(3);
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String fecha = df.format(date);
                        cita.setFecha(fecha);
                        cita.setHora(rs.getTime(4).toString());
                        cita.setDuracion(rs.getTime(5).toString());
                        String[] electrodes = rs.getString(6).split(",");
                        cita.setElectrodos(electrodes);
                        resultadoGeneral.setZonaCerebral(rs.getString(7));
                        resultadoGeneral.setTipoOndaDominate(rs.getString(8));
                        resultadoGeneral.setPorcentajeTipoOnda(rs.getFloat(9));
                    }
                }
                else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultadoGeneral;
    }
    
    public ResultadosGenerales obtenerResultadosGenerales(int folioCita){
        resultadoGeneral = new ResultadosGenerales();
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarResultadosGenerales (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, folioCita);
                sp.registerOutParameter(2, java.sql.Types.VARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(2);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        rs.next();
                        resultadoGeneral = new ResultadosGenerales();
                        cita = new Cita();
                        resultadoGeneral.setIdResultadosGenerales(rs.getInt(1));
                        cita.setFolioCita(rs.getInt(2));
                        cita.setElectrodos(rs.getString(3).split(","));
                        cita.setDuracion(rs.getString(4));
                        resultadoGeneral.setCita(cita);
                        resultadoGeneral.setZonaCerebral(rs.getString(5));
                        resultadoGeneral.setTipoOndaDominate(rs.getString(6));
                        resultadoGeneral.setPorcentajeTipoOnda(rs.getDouble(7));
                    }
                }
                else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultadoGeneral;
    }
    
    
    public ResultadosSegmento obtenerResultadosSegmento(int idCita, String canal, int segundo){
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarResultadosPorSegmento (?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idCita);
                sp.setString(2, canal);
                sp.setInt(3, segundo);
                sp.registerOutParameter(4, java.sql.Types.VARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(4);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        rs.next();
                        
                        resultadoSegmento = new ResultadosSegmento();
                        resultadoSegmento.setIdResultadosSegmento(rs.getInt(1));
                        grabacion = new Grabacion();
                        grabacion.setIdGrabacion(rs.getInt(2));
                        resultadoSegmento.setGrabacion(grabacion);
                        resultadoSegmento.setSegundo(rs.getInt(3));
                        resultadoSegmento.setCanal(rs.getString(4));
                        resultadoSegmento.setFrecuenciaDominante(rs.getFloat(5));
                        resultadoSegmento.setTipoOnda(rs.getString(6));
                        resultadoSegmento.setSenal(rs.getString(7));
                        resultadoSegmento.setIsAnormal(rs.getBoolean(8));
                    }
                }
                else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultadoSegmento;
    }
    
    
    public ArrayList<ResultadosSegmento> obtenerResultadosIntervalo(int idCita, String canal, int sinceSecond, int toSecond){
        origen = new DataSource();
        resultadosSegmentos = new ArrayList<>();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarResultadosPorIntervalo (?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idCita);
                sp.setString(2, canal);
                sp.setInt(3, sinceSecond);
                sp.setInt(4, toSecond);
                sp.registerOutParameter(5, java.sql.Types.VARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(5);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        while(rs.next()) {
                        
                            resultadoSegmento = new ResultadosSegmento();
                            resultadoSegmento.setIdResultadosSegmento(rs.getInt(1));
                            grabacion = new Grabacion();
                            grabacion.setIdGrabacion(rs.getInt(2));
                            resultadoSegmento.setGrabacion(grabacion);
                            resultadoSegmento.setSegundo(rs.getInt(3));
                            resultadoSegmento.setCanal(rs.getString(4));
                            resultadoSegmento.setFrecuenciaDominante(rs.getFloat(5));
                            resultadoSegmento.setTipoOnda(rs.getString(6));
                            resultadoSegmento.setSenal(rs.getString(7));
                            resultadoSegmento.setIsAnormal(rs.getBoolean(8));

                            resultadosSegmentos.add(resultadoSegmento);
                        }
                    }
                }
                else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultadosSegmentos;
    }
    
    public ArrayList<ResultadosCanal> obtenerResultadosCanal(int idCita, String canal){
        origen = new DataSource();
        int f = 0;
        resultadosCanales = new ArrayList<>();
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarResultadosPorCanal (?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idCita);
                sp.setString(2, canal);
                sp.registerOutParameter(3, java.sql.Types.VARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(3);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        rs.next();
                        
                        resultadoCanal = new ResultadosCanal();
                        resultadoCanal.setIdResultadosCanal(rs.getInt(1));
                        Grabacion grabacion = new Grabacion();
                        grabacion.setIdGrabacion(rs.getInt(2));
                        resultadoCanal.setGrabacion(grabacion);
                        resultadoCanal.setCanal(rs.getString(3));
                        resultadoCanal.setTipoOndaDominanteCanal(rs.getString(4));
                        resultadoCanal.setFrecuenciaDominanteCanal(rs.getDouble(5));
                        resultadoCanal.setPromedioAmplitudesCanal(rs.getDouble(6));
                        resultadoCanal.setPorcentajeAparicionRitmoAlpha(rs.getDouble(7));
                        resultadoCanal.setPorcentajeAparicionRitmoBeta(rs.getDouble(8));
                        resultadoCanal.setPorcentajeAparicionRitmoDelta(rs.getDouble(9));
                        resultadoCanal.setPorcentajeAparicionRitmoTheta(rs.getDouble(10));
                        resultadoCanal.setPorcentajeAparicionFrecuenciaAlpha(rs.getDouble(11));
                        resultadoCanal.setPorcentajeAparicionFrecuenciaBeta(rs.getDouble(12));
                        resultadoCanal.setPorcentajeAparicionFrecuenciaDelta(rs.getDouble(13));
                        resultadoCanal.setPorcentajeAparicionFrecuenciaTheta(rs.getDouble(14));
                        resultadoCanal.setPromedioAmplitudesRitmoAlpha(rs.getDouble(15));
                        resultadoCanal.setPromedioAmplitudesRitmoBeta(rs.getDouble(16));
                        resultadoCanal.setPromedioAmplitudesRitmoDelta(rs.getDouble(17));
                        resultadoCanal.setPromedioAmplitudesRitmoTheta(rs.getDouble(18));
                        resultadoCanal.setPromedioAmplitudesFrecuenciaAlpha(rs.getDouble(19));
                        resultadoCanal.setPromedioAmplitudesFrecuenciaBeta(rs.getDouble(20));
                        resultadoCanal.setPromedioAmplitudesFrecuenciaDelta(rs.getDouble(21));
                        resultadoCanal.setPromedioAmplitudesFrecuenciaTheta(rs.getDouble(22));
                        resultadoCanal.setIsAnormal(rs.getBoolean(23));
                        
                        resultadosCanales.add(resultadoCanal);
                    }
                }
                else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return resultadosCanales;
    }
    
    
     public ArrayList<Dispositivo> obtenerDispositivosUsuario(int idPaciente){
        dispositivos = new ArrayList<>();
        origen = new DataSource();
        int f = 0;
        try{
            if (origen.iniciaConexion() != null) {
                System.out.println("Entra");
                SQL = "{call mostrarDispositivosPaciente (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20);
                sp.setInt(1, idPaciente);
                sp.registerOutParameter(2, java.sql.Types.NVARCHAR);
                rs = sp.executeQuery();
                resultado = sp.getString(2);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        while(rs.next()){
                            dispositivo = new Dispositivo();
                            dispositivo.setId(rs.getInt(1));
                            paciente = new Paciente();
                            paciente.setId(rs.getInt(2));
                            dispositivo.setPatient(paciente);
                            dispositivo.setDeviceName(rs.getString(3));
                            dispositivo.setDeviceMacAddres(rs.getString(4));
                            dispositivos.add(dispositivo);
                        }
                    }
                }
                else
                    return null;
            } 
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return dispositivos;
    }
     
     
     public String eliminarDispositivoUsuario(int idPaciente, int idDispositivo){
        String jsonQuery = "";
        int f = 0; 
        origen = new DataSource();
        
        String message;
        int Error;
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call eliminarDispositivoPaciente (?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20); 
                sp.setInt(1, idDispositivo);
                sp.setInt(2, idPaciente);
                sp.registerOutParameter(3, java.sql.Types.VARCHAR);
                sp.execute();
                jsonQuery = sp.getString(3);
               
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = null;
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return jsonQuery;
    }
     
     
     
     public String actualizarDatosCita(Cita cita){
        String jsonQuery = "";
        int f = 0; 
        origen = new DataSource();
        
        String message;
        int Error;
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call actualizarDatosCita (?,?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20); 
                
                sp.setInt(1, cita.getPaciente().getId());
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                java.sql.Date fecha = new java.sql.Date(format.parse(cita.getFecha()).getTime());
                sp.setDate(2, fecha);
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
                sp.setTime(3, new java.sql.Time(formatter.parse(cita.getHora()).getTime()));
                sp.setTime(4, new java.sql.Time(formatter.parse(cita.getDuracion()).getTime()));
                sp.setString(5, cita.getObservaciones());
                String electrodes = String.join(",", cita.getElectrodos());
                sp.setString(6, electrodes);
                sp.registerOutParameter(7, java.sql.Types.NVARCHAR);
                sp.execute();
                jsonQuery = sp.getString(7);
               
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = null;
        } catch (ParseException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return jsonQuery;
    }
     
    public String eliminarCita(int idSchedule){
        String jsonQuery = "";
        int f = 0; 
        origen = new DataSource();
        
        String message;
        int Error;
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call eliminarCita (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20); 
                
                sp.setInt(1, idSchedule);
                sp.registerOutParameter(2, java.sql.Types.VARCHAR);
                sp.execute();
                jsonQuery = sp.getString(2);
               
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = null;
        } finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return jsonQuery;
    }
    
    
    public String eliminarPaciente(int idPatient){
        String jsonQuery = "";
        int f = 0; 
        origen = new DataSource();
        
        String message;
        int Error;
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call eliminarPaciente (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20); 
                
                sp.setInt(1, idPatient);
                sp.registerOutParameter(2, java.sql.Types.VARCHAR);
                sp.execute();
                jsonQuery = sp.getString(2);
               
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = null;
        } finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return jsonQuery;
    }
    
    
    public String eliminarEspecialista(int idSpetialist){
        String jsonQuery = "";
        int f = 0; 
        origen = new DataSource();
        
        String message;
        int Error;
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call eliminarEspecialista (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20); 
                
                sp.setInt(1, idSpetialist);
                sp.registerOutParameter(2, java.sql.Types.VARCHAR);
                sp.execute();
                jsonQuery = sp.getString(2);
               
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = null;
        } finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return jsonQuery;
    }
    
    
    public String obtenerDatosUsuario(String email){
        String query = "";
        int f = 0; 
        origen = new DataSource();
        
        String message;
        int Error;
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call getEmailAndPassword (?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20); 
                
                sp.setString(1, email);
                sp.registerOutParameter(2, java.sql.Types.VARCHAR);
                sp.execute();
                query = sp.getString(2);
                
                rs = sp.executeQuery();
                resultado = sp.getString(2);
                if(resultado.equals(words.OK)){
                    while(rs.next()){
                        f = f+1;
                    }
                    if(f == 0){
                        System.out.println("Es nulo"+rs);
                        return null;
                    }
                    else{
                        rs.beforeFirst();
                        rs.next();
                       query = rs.getString(1) + "," + rs.getString(2);
                    }
                }
                else
                    return null;
               
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = null;
        } finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return query;
    }
    
    public String actualizarDatosEspecialista(Especialista spetialist){
        String jsonQuery = "";
        int f = 0; 
        origen = new DataSource();
        
        String message;
        int Error;
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call actualizarDatosEspecialista (?,?,?,?,?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20); 
                
                sp.setInt(1, spetialist.getId());
                sp.setString(2, spetialist.getName());
                sp.setString(3, spetialist.getFirstLastName());
                sp.setString(4, spetialist.getSecondLastName());
                sp.setString(5, spetialist.getEmail());
                sp.setString(6, spetialist.getPassword());
                sp.setString(7, spetialist.getGender());
                sp.setBytes(8, spetialist.getPrifilePhoto());
                sp.registerOutParameter(9, java.sql.Types.VARCHAR);
                sp.execute();
                jsonQuery = sp.getString(9);
               
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = null;
        } finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return jsonQuery;
    }
    
    
    public String actualizarDatosPaciente(Paciente patient){
        String jsonQuery = "";
        int f = 0; 
        origen = new DataSource();
        
        String message;
        int Error;
        try{
            if (origen.iniciaConexion() != null) {
                SQL = "{call actualizarDatosPaciente (?,?,?,?,?,?,?,?,?,?,?)}";
                sp = origen.conexion.prepareCall(SQL);
                sp.setEscapeProcessing(true);
                sp.setQueryTimeout(20); 
                
                sp.setInt(1, patient.getId());
                sp.setString(2, patient.getName());
                sp.setString(3, patient.getFirstLastName());
                sp.setString(4, patient.getSecondLastName());
                sp.setString(5, patient.getPadecimiento());
                sp.setInt(6, patient.getAge());
                sp.setString(7, patient.getEmail());
                sp.setString(8, patient.getPassword());
                sp.setString(9, patient.getGender());
                sp.setBytes(10, patient.getPrifilePhoto());
                
                sp.registerOutParameter(11, java.sql.Types.VARCHAR);
                sp.execute();
                jsonQuery = sp.getString(11);
               
            } 
        }catch(SQLException e){
            e.printStackTrace();
            resultado = null;
        } finally{
            try{
                origen.cerrarConexion();
                if(sp != null)
                    sp.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return jsonQuery;
    }
    
}


