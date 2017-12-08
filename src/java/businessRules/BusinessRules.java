/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessRules;

import Dao.Dao;
import classifiereeg.clasificadoreeg.ClasificadorEEG;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import email.GoogleMail;
import entities.Administrador;
import entities.Cita;
import entities.Dispositivo;
import org.json.*;
import entities.Especialista;
import entities.Paciente;
import entities.ResultadosCanal;
import entities.ResultadosGenerales;
import entities.ResultadosSegmento;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import security.AccessToken;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class BusinessRules {
    
    /* variables for data acces and cast */
    private final Palabras words;
    private final Dao source;
    private final Gson gson;
    
    /* variables for business rules */
    private Administrador administrator;
    private Paciente patient;
    private Especialista spetialist;
    private Cita schedule;
    private ResultadosGenerales generalResults;
    private ResultadosSegmento segmentResults;
    private ResultadosCanal chanelResults;
    
    private static final String RECORDING_DATA_FILE = "RecordingData.txt";
    
    
    public BusinessRules(){
        source = new Dao();
        gson = new Gson();
        words = new Palabras();
    }
    
    /**
     *
     * @param jsonLogIn
     * @return
     */
    public String iniciarSesion(String jsonLogIn){
        String jsonAnswer = null;
        String email ;
        String hashPassword;
        try {
            JSONObject jsonObject = new JSONObject (jsonLogIn.replace("%7B", "{").replace("%7D", "}"));
            email = jsonObject.getString(words.EMAIL_USER);
            hashPassword = jsonObject.getString(words.PASSWORD_USER);
            String respuesta = source.obtenerDatosUsuario(email,hashPassword);
            if(respuesta != null){
                if(!respuesta.contains("Message") && !respuesta.contains("Error")){
                    JSONObject json = new JSONObject();
                    try {
                        json.put(Palabras.USER, respuesta);
                        json.put(Palabras.TOKEN, AccessToken.getAccessToken(respuesta));
                    } catch (JSONException ex) {
                        Logger.getLogger(JSONErrorBuilder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jsonAnswer = json.toString();
                }
                else
                    jsonAnswer = respuesta;
            }
            else
                jsonAnswer = words.ERROR_HAPPENED_A_PROBLEM;
        } catch (JsonSyntaxException ex) {
            ex.printStackTrace();
            jsonAnswer = words.ERROR_FROM_JSON;
        } catch (JSONException ex) {
            ex.printStackTrace();
            jsonAnswer = words.SYNTAX_ERROR_FROM_JSON;
        }
        return jsonAnswer;
    }
    
    /**
     *
     * @param jsonRegistroPaciente
     * @return
     */
    public String registrarAdministrador(String jsonRegistroAdmin){
        String respuesta;
        String jsonAnswer;
        administrator = new Administrador();
        JSONObject json;
        
        try {
            administrator = gson.fromJson(jsonRegistroAdmin, Administrador.class);
            jsonAnswer = gson.toJson(source.registrarAdministrador(administrator));
        } catch (JsonSyntaxException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    /**
     *
     * @param jsonRegistroPaciente
     * @return
     */
    public String registrarPaciente(String jsonRegistroPaciente){
        String respuesta;
        String jsonAnswer;
        patient = new Paciente();
        JSONObject json;
        
        try {
            patient = gson.fromJson(jsonRegistroPaciente, Paciente.class);
            jsonAnswer = gson.toJson(source.registrarPaciente(patient));
        } catch (JsonSyntaxException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    /**
     *
     * @param jsonRegistroMedico
     * @return
     */
    public String registrarEspecialista(String jsonRegistroMedico){
        String jsonAnswer;
        spetialist = new Especialista();
        JSONObject json;
        try {
            spetialist = new Gson().fromJson(jsonRegistroMedico, Especialista.class);
            jsonAnswer = new Gson().toJson(source.registrarEspecialista(spetialist));
        } catch (JsonSyntaxException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    public String obtenerEspecialista(int idEspecialista){
        String jsonAnswer;
        try {
            Especialista especialista = source.mostrarDatosEspecialista(idEspecialista);
            if(especialista != null)
                jsonAnswer = new Gson().toJson(especialista);
            else
                jsonAnswer = words.ERROR_SPETIALIST_NOT_EXISTS;
        } catch (Exception ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    public String obtenerPaciente(int idPaciente){
        String jsonAnswer;
        try {
            Paciente paciente = source.mostrarDatosPaciente(idPaciente);
            if(paciente != null)
                jsonAnswer = new Gson().toJson(paciente);
            else
                jsonAnswer = words.ERROR_PATIENT_NOT_EXISTS;
        } catch (Exception ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    public String obtenerCitasDePaciente(int idPaciente){
        String jsonAnswer;
        try {
            ArrayList<Cita> respuesta = source.obtenerCitasDePaciente(idPaciente);
            if(respuesta!=null)
                jsonAnswer = new Gson().toJson(respuesta);
            else 
                jsonAnswer = words.ERROR_PATIENT_NOT_EXISTS;
        } catch (Exception ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    public String obtenerCitasDeEspecialista(int idEspecialista){
        String jsonAnswer;
        try {
            System.out.println("1");
            ArrayList<Cita> respuesta = source.obtenerCitasDeEspecialista(idEspecialista);
            System.out.println("2");
            if(respuesta!=null){
                System.out.println(respuesta.size());
                jsonAnswer = new Gson().toJson(respuesta);
            } else 
                jsonAnswer = words.ERROR_SCHEDULE_NOT_EXISTS;
        } catch (Exception ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    /*pendiente*/
    public String obtenerEstudioDePaciente(String jsonStudiesByPatient){
        String jsonAnswer;
        int idCita;
        int idPaciente;
        try {
            JSONObject jsonObject = new JSONObject (jsonStudiesByPatient);
            idCita = jsonObject.getInt(words.ID_SCHEDULE);
            idPaciente = jsonObject.getInt(words.ID_PATIENT);
            ResultadosGenerales respuesta = source.obtenerEstudioPaciente(idCita,idPaciente);
            if(respuesta!=null)
               jsonAnswer=new Gson().toJson(respuesta);
            else
               jsonAnswer=words.ERROR_STUDY_NOT_EXISTS;
            
        } catch (Exception ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    public String obtenerPacientesPorEspecialista(int idEspecialista){
        String jsonAnswer;
        try {
            ArrayList<Paciente> respuesta = source.obtenerPacientesPorEspecialista(idEspecialista);
            if(respuesta!=null)
                jsonAnswer = new Gson().toJson(respuesta);
            else
                jsonAnswer = words.ERROR_NOT_PATIENTS;
        } catch (Exception ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    

    public String obtenerTodosEspecialistas() {
        String jsonAnswer;
        try {
            ArrayList<Especialista> respuesta = source.obtenerEspecialistas();
            if(respuesta!=null)
                jsonAnswer = new Gson().toJson(respuesta);
            else
                jsonAnswer = words.ERROR_NOT_SPETIALISTS;
        } catch (Exception ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }

    public String obtenerCitaDePaciente(String jsonCitaDePaciente) {
        String jsonAnswer;
        int idCita;
        int idPaciente;
        try {
            JSONObject jsonObject = new JSONObject (jsonCitaDePaciente);
            idCita = jsonObject.getInt(words.ID_SCHEDULE);
            idPaciente = jsonObject.getInt(words.ID_PATIENT);
            Cita respuesta = source.obtenerCitaDePaciente(idCita, idPaciente);
            if(respuesta!=null)
                jsonAnswer = new Gson().toJson(respuesta);
            else
                jsonAnswer = words.ERROR_SCHEDULE_NOT_EXISTS;
        } catch (Exception ex) {
            ex.printStackTrace();
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }

    public String obtenerTodasCitasDePaciente(int idPaciente) {
        String jsonAnswer;
        try {
            ArrayList<Cita> respuesta = source.obtenerCitasDePaciente(idPaciente);
            if(respuesta!=null)
                jsonAnswer = new Gson().toJson(respuesta);
            else
                jsonAnswer = words.ERROR_NOT_SCHEDULES;
        } catch (Exception ex) {
            ex.printStackTrace();
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }

    public String obtenerResultadosTodosLosSegmentos(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String obtenerResultadosSegmentosPorIntervalo(String jsonResultadosSegmentosPorIntervalo) {
        String jsonAnswer;
        int idPaciente;
        int desdeSegundo;
        int hastaSegundo;
        try {
            JSONObject jsonObject = new JSONObject (jsonResultadosSegmentosPorIntervalo);
            idPaciente = jsonObject.getInt(words.ID_PATIENT);
            desdeSegundo = jsonObject.getInt(words.SINCE_SECOND);
            hastaSegundo = jsonObject.getInt(words.TO_SECOND);
            ArrayList<ResultadosSegmento> respuesta = source.obtenerResultadosVariosSegmentos(idPaciente, desdeSegundo, hastaSegundo);
            if(respuesta!=null)
                jsonAnswer = new Gson().toJson(respuesta);
            else
                jsonAnswer = words.ERROR_NOT_SCHEDULES;
        } catch (Exception ex) {
            ex.printStackTrace();
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }

    public String requestScheduleAppointment(String jsonSchedule) {
        String respuesta;
        String jsonAnswer;
        schedule = new Cita();
        JSONObject json;
        
        try {
            schedule = gson.fromJson(jsonSchedule, Cita.class);
            jsonAnswer = gson.toJson(source.registrarCita(schedule));
        } catch (JsonSyntaxException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    public String requestUpdateSchedule(String jsonUpdateSchedule){
        String response;
        schedule = new Cita();
        String jsonAnswer;
        try {
            schedule = gson.fromJson(jsonUpdateSchedule, Cita.class);
            jsonAnswer = gson.toJson(source.actualizarDatosCita(schedule));
        } catch (JsonSyntaxException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }

    public String requestStoreGeneralResults(String jsonGeneralResults) {
        String respuesta;
        String jsonAnswer;
        generalResults = new ResultadosGenerales();
        JSONObject json;
        
        try {
            generalResults = gson.fromJson(jsonGeneralResults, ResultadosGenerales.class);
            jsonAnswer = gson.toJson(source.registrarResultadosGenerales(generalResults));
        } catch (JsonSyntaxException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }

    public String requestStoreResultsBySegment(String jsonResultsBySegment) {
        String respuesta;
        String jsonAnswer;
        segmentResults = new ResultadosSegmento();
        JSONObject json;
        
        try {
            segmentResults = gson.fromJson(jsonResultsBySegment, ResultadosSegmento.class);
            jsonAnswer = gson.toJson(source.registrarResultadosSegmento(segmentResults));
        } catch (JsonSyntaxException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }

    public String requestStoreResultsByChanel(String jsonResultsByChanel) {
        String respuesta;
        String jsonAnswer;
        chanelResults = new ResultadosCanal();
        JSONObject json;
        
        try {
            chanelResults = gson.fromJson(jsonResultsByChanel, ResultadosCanal.class);
            jsonAnswer = gson.toJson(source.registrarResultadosCanal(chanelResults));
        } catch (JsonSyntaxException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    public String requestGetDevices(int idPatient){
        String jsonAnswer = "";
        try {
            ArrayList<Dispositivo> respuesta = source.obtenerDispositivosUsuario(idPatient);
            if(respuesta!=null)
                jsonAnswer = new Gson().toJson(respuesta);
            else
                jsonAnswer = words.ERROR_NOT_DEVICES;
        } catch (Exception ex) {
            ex.printStackTrace();
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    public String requestDeleteDevices(String jsonDevice){
        String jsonAnswer = "";
        int idPatient;
        int idDevice;
        JSONObject json;
        
        try {
            json = new JSONObject(jsonDevice);
            idPatient = json.getInt(words.ID_PATIENT);
            idDevice = json.getInt(words.ID_DEVICE);
            jsonAnswer = source.eliminarDispositivoUsuario(idPatient, idDevice);
            
        } catch (JsonSyntaxException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        } catch (JSONException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        return jsonAnswer;
    }
    
            
    public String requestStoreDevice(String jsonDevice){
        String jsonAnswer;
        int idPatient;
        String deviceName;
        String deviceMacAddress;
        JSONObject json;
        
        try {
            json = new JSONObject(jsonDevice);
            idPatient = json.getInt(words.ID_PATIENT);
            deviceName = json.getString(words.DEVICE_NAME);
            deviceMacAddress = json.getString(words.DEVICE_MAC_ADDRESS);
            jsonAnswer = source.almacenarDispositivoPorUsuario(idPatient, deviceName, deviceMacAddress);
            
        } catch (JsonSyntaxException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        } catch (JSONException ex) {
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        return jsonAnswer;
    }
         
    
    public String requestStoreFileRecording(String jsonFile){
        String jsonAnswer=null;
        String error = null;
        
        String dirFile;
        String dirScheduleId;
        String fecha;
        int idUser;
        long totalBytes;
        long currentPosition;
        long finalPosition = 0;
        byte[] fileFragment;
        
        String path;
        String pathScheduleId;
        String pathDate;
        String channelList;
        
        String dirOS = System.getProperty("user.home");
        String os = System.getProperty("os.name");
        
        System.out.println(dirOS);
        System.out.println(os);
        
        if(os.contains("Mac") || os.contains("Windows")){
            System.out.println("entra");
            try {
                JSONObject jsonObject = new JSONObject (jsonFile.replace("%7B", "{").replace("%7D", "}"));
                idUser = jsonObject.getInt(words.ID_USER);
                dirFile = jsonObject.getString(words.FILE_NAME);
                channelList = jsonObject.getString(words.CHANNEL_LIST);
                dirScheduleId = jsonObject.getString(words.SCHEDULE_ID);
                fecha = jsonObject.getString(words.DATE_RECORDING);
                totalBytes = jsonObject.getLong(words.TOTAL_BYTES);
                currentPosition = jsonObject.getLong(words.CURRENT_POSITION_FILE);
                JSONArray jArray = jsonObject.getJSONArray(words.BYTES_FILE);
                System.out.println("total: "+totalBytes);
                System.out.println("position: "+currentPosition);
                byte[] array = new byte[jArray.length()];
                System.out.println(dirFile);
                System.out.println(fecha);
                System.out.println(totalBytes);
                for(int i = 0; i < jArray.length(); i++) {
                    array[i] = (byte)jArray.getInt(i);
                    System.out.format("%02X ", array[i]);
                }
                
                path = dirOS + File.separator + "Documents" + File.separator + "Recordings";
                File directoryRecordings = new File(path);
                if (! directoryRecordings.exists()){
                    directoryRecordings.mkdir();
                    System.out.println("crea directorio");
                }
                
                pathDate = path + File.separator + fecha;
                File directoryDate = new File(pathDate);
                if (! directoryDate.exists()){
                    directoryDate.mkdir();
                    System.out.println("crea directorio fecha");
                }
                
                pathScheduleId = pathDate + File.separator + dirScheduleId;
                File directoryScheduleId = new File(pathScheduleId);
                String [] channels = channelList.split(",");
                if (! directoryScheduleId.exists()){
                    directoryScheduleId.mkdir();
                    File fileAux = new File(pathScheduleId + File.separator + RECORDING_DATA_FILE);
                    try {
                        
                        if(!fileAux.exists()){
                            fileAux.createNewFile();
                            System.out.println("crea archivos: " + pathScheduleId + File.separator + RECORDING_DATA_FILE);
                        }
                        
                        JSONObject finalAuxJson = new JSONObject ();
                        
                        JSONObject jsonAuxFile = new JSONObject ();
                        jsonAuxFile.put(Palabras.TOTAL_BYTES, 0);
                        jsonAuxFile.put(Palabras.FINAL_POSITION_FILE, 0);
                            
                        for(int i=0;i<channels.length;i++){
                            finalAuxJson.put(channels[i],jsonAuxFile);
                        }
                        
                        String jsonAnswerAuxFile = finalAuxJson.toString();
                        System.out.println("jsonFinal: "+jsonAnswerAuxFile);
                        FileWriter faux = new FileWriter(fileAux,true);
                        faux.write(jsonAnswerAuxFile);
                        
                        faux.close();
                    } catch (IOException ex) {
                        Logger.getLogger(BusinessRules.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                
                File fileRecording = new File(pathScheduleId + File.separator + dirFile + ".bin");
                File fileAux = new File(pathScheduleId + File.separator + RECORDING_DATA_FILE);
                BufferedReader br;
                int downloadStatus;
                try { 
                    fileRecording.createNewFile(); // create channel data file
                    br = new BufferedReader(new FileReader(fileAux));
                    String auxiliarValues = br.readLine();
                    br.close();
                    
                    JSONObject jsonauxiliarValues = new JSONObject ( auxiliarValues );
                    JSONObject channelData = jsonauxiliarValues.getJSONObject(dirFile);
                    System.out.println(channelData.toString());
                    
                    long currentAuxPosition = channelData.getLong(Palabras.FINAL_POSITION_FILE);
                    
                    
                    System.out.println("1: "+currentAuxPosition+"2: "+currentPosition);
                    if(currentAuxPosition == currentPosition){
                        System.out.println("entra if ----------------------");
                        // write the recording channel file
                        RandomAccessFile raf = new RandomAccessFile(fileRecording, "rw");
                        raf.seek(currentPosition);
                        raf.write(array);
                        finalPosition = fileRecording.length();
                        // overwrite the position and file size
                        fileAux.createNewFile();
                        FileWriter faux = new FileWriter(fileAux,false);
                        JSONObject newJsonChanelData = new JSONObject();
                        newJsonChanelData.put(Palabras.TOTAL_BYTES, totalBytes);
                        newJsonChanelData.put(Palabras.FINAL_POSITION_FILE, finalPosition);
                        jsonauxiliarValues.remove(dirFile);
                        jsonauxiliarValues.put(dirFile, newJsonChanelData);
                        String jsonAnswerAuxFile = jsonauxiliarValues.toString();
                        faux.write(jsonAnswerAuxFile);
                        faux.close();
                        raf.close();
                        
                    } else {
                        System.out.println("entra else ----------------------");
                        finalPosition = currentAuxPosition;
                        System.out.println("finalPos: "+finalPosition);
                        FileWriter faux = new FileWriter(fileAux,false);
                        JSONObject newJsonChanelData = new JSONObject();
                        newJsonChanelData.put(Palabras.TOTAL_BYTES, totalBytes);
                        newJsonChanelData.put(Palabras.FINAL_POSITION_FILE, finalPosition);
                        jsonauxiliarValues.remove(dirFile);
                        jsonauxiliarValues.put(dirFile, newJsonChanelData);
                        String jsonAnswerAuxFile = jsonauxiliarValues.toString();
                        faux.write(jsonAnswerAuxFile);
                        faux.close();

                    }
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BusinessRules.class.getName()).log(Level.SEVERE, null, ex);
                    error = JSONErrorBuilder.buildJson(Palabras.CODE_ERROR_FILE_NOT_FOUND, ex.getLocalizedMessage());
                } catch (IOException ex) {
                    Logger.getLogger(BusinessRules.class.getName()).log(Level.SEVERE, null, ex);
                    error = JSONErrorBuilder.buildJson(Palabras.CODE_ERROR_IO_EXCEPTION, ex.getLocalizedMessage());
                }
                
                downloadStatus = checkTransferStatusFromJson(fileAux, channels, pathScheduleId);
                JSONObject jsonAnswerRecording = new JSONObject ();
                jsonAnswerRecording.put(Palabras.TRANSFER_STATUS_RECORDING, downloadStatus);
                jsonAnswerRecording.put(Palabras.FINAL_POSITION_FILE, finalPosition);
                jsonAnswer = jsonAnswerRecording.toString();
                /*if(finalPosition == totalBytes){
                            fileAux.delete();
                        }*/
                return jsonAnswer;
            } catch (JSONException ex) {
                Logger.getLogger(BusinessRules.class.getName()).log(Level.SEVERE, null, ex);
                error = JSONErrorBuilder.buildJson(Palabras.CODE_ERROR_FROM_JSON, ex.getLocalizedMessage());
            }
        }
        return error;
    }
    
    private int checkTransferStatusFromJson(File file, String[] channels, String pathScheduleId){
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
        
            String auxiliarValues = br.readLine();
            br.close();
            
            JSONObject jsonauxiliarValues = new JSONObject ( auxiliarValues );
            int cont = 0;
            String channelList = "";
            for(int i =0; i<channels.length;i++){
                channelList = channelList + channels[i] + ",";
                JSONObject channelData = jsonauxiliarValues.getJSONObject(channels[i]);
                System.out.println(channelData.toString());

                long currentPosition = channelData.getLong(Palabras.FINAL_POSITION_FILE);
                long totalSize = channelData.getLong(Palabras.TOTAL_BYTES);
                if(currentPosition==totalSize){
                    cont++;
                } else
                    break;
            }
            if(cont==channels.length){
                //file.delete();
                String[] args = new String[2];
                args[0] = pathScheduleId;
                args[1] = channelList;
                System.out.println("lista: "+ channelList);
                
                Runnable processor = new ClasificadorEEG(args);
                new Thread(processor).start();
                
                return Palabras.CODE_SUCESSFULL_TRANSFER_FILES_COMPLETE;
            }
            return Palabras.CODE_SUCESSFULL_STORE_RECORDING;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BusinessRules.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(BusinessRules.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BusinessRules.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    public String requestDropSchedule(int idSchedule){
        String jsonAnswer = "";
        try {
            jsonAnswer = source.eliminarCita(idSchedule);
        } catch (Exception ex) {
            ex.printStackTrace();
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    public String requestDropPatient(int idPatient){
        String jsonAnswer = "";
        try {
            jsonAnswer = source.eliminarPaciente(idPatient);
        } catch (Exception ex) {
            ex.printStackTrace();
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    
    public String requestDropSpetialist(int idSpetialist){
        String jsonAnswer = "";
        try {
            jsonAnswer = source.eliminarPaciente(idSpetialist);
        } catch (Exception ex) {
            ex.printStackTrace();
            jsonAnswer = words.ERROR_FROM_JSON;
        }
        System.out.println(jsonAnswer);
        return jsonAnswer;
    }
    
    
    public String restorePassword (String email){
        String response = "";
        try {
            response = source.obtenerDatosUsuario(email);
            if(response != null){
                String[] res = response.split(",");
                GoogleMail.sendEmailAndPassword(res[0], res[1]);
                response = words.EMAIL_SENDED;
            } else {
                response = words.ERROR_USER_NOT_EXISTS;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response = words.ERROR_FROM_JSON;
        }
        System.out.println(response);
        return response;
    }
    
}
