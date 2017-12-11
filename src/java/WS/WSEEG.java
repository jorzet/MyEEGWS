/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import businessRules.BusinessRules;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Jorge Zepeda Tinoco
 */
@Path("electroencephalography")
public class WSEEG {

    @Context
    private UriInfo context;

    /**
     * Crea una nueva instancia de WSEEG
     * Creates a new instance of WSEEG
     * Crée une nouvelle instance de WSEEG
     */
    public WSEEG() {
    }

    /**
     * Hecho, Done, terminé
     * Retorna un Json con los datos del usuario (Especialista o Paciente)
     * Returns a Json with the user data (Spetialist or Patient)
     * Retourne à Json avec des données d'utilisateur (Spécialiste ou Patient)
     * 
     * @param json
     * @return
     */
    @GET
    @Path("/singin/{json}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String singIn(@PathParam("json") String json) {
        BusinessRules rules = new BusinessRules();
        return rules.iniciarSesion(json);
    }
    
    /**
     * Hecho, Done, terminé
     * Retorna Palabras.SUCESSFULL_SINGUP en caso de registrar correctamente un administrador
     * Return Palabras.SUCESSFULL_SINGUP in case of sing-up correctly an administrator
     * Retourne Palabras.SUCESSFULL_SINGUP dans le cas où un administrateur s'inscrit correctement
     * 
     * @param jsonPatient
     * @return
     */
    @GET
    @Path("/singupadministrator/{jsonAdmin}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String singUpAdministrator(@PathParam("jsonAdmin") String jsonAdmin) {
        BusinessRules rules = new BusinessRules();
        return rules.registrarAdministrador(jsonAdmin);
    }
    
    
    /**
     * Hecho, Done, terminé
     * Retorna Palabras.SUCESSFULL_SINGUP en caso de registrar correctamente un paciente
     * Return Palabras.SUCESSFULL_SINGUP in case of sing-up correctly a patient
     * Retourne Palabras.SUCESSFULL_SINGUP dans le cas où un patient s'inscrit correctement
     * 
     * @param jsonPatient
     * @return
     */
    @GET
    @Path("/singuppatient/{jsonPatient}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String singUpPatient(@PathParam("jsonPatient") String jsonPatient) {
        BusinessRules rules = new BusinessRules();
        return rules.registrarPaciente(jsonPatient);
    }
    
    /**
     * Hecho, Done, terminé
     * Retorna Palabras.SUCESSFULL_SINGUP en caso de registrar correctamente un especialista
     * Return Palabras.SUCESSFULL_SINGUP in case of sing-up correctly a spetialist
     * Retourne Palabras.SUCESSFULL_SINGUP dans le cas où un spécialiste s'inscrit correctement
     * 
     * @param jsonSpetialist
     * @return
     */
    @GET
    @Path("/singupspetialist/{jsonSpetialist}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String singUpSpetialist(@PathParam("jsonSpetialist") String jsonSpetialist) {
        BusinessRules rules = new BusinessRules();
        return rules.registrarEspecialista(jsonSpetialist);
    }
    
    /**
     * Hecho, Done, terminé
     * Retorna un Json con la informacion del paciente
     * Return a Json with the patient data
     * Retourne un Json avec des données de patient
     * 
     * @param idPatient
     * @return
     */
    @GET
    @Path("/getpatientdata/{idPatient}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getPatientData(@PathParam("idPatient") String idPatient){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerPaciente(Integer.parseInt(idPatient));
    }
    
    /**
     * Hecho, Done, terminé
     * Retorna un Json con la informacion del especialista
     * Return a Json with the spetialist data
     * Retourne un Json avec des données de spécialiste
     *
     * @param idSpetialist
     * @return
     */
    @GET
    @Path("/getrestorepassword/{email}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getRestorePassword(@PathParam("email") String email){
        BusinessRules rules = new BusinessRules();
        return rules.restorePassword(email);
    }
    
    
    @GET
    @Path("/getspetialistdata/{idSpetialist}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getSpetialistData(@PathParam("idSpetialist") String idSpetialist){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerEspecialista(Integer.parseInt(idSpetialist));
    }
    
    /**
     * Hecho, Done, terminé
     * 
     * @return
     */
    @GET
    @Path("/getallspetialist/")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getAllSpetialist(){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerTodosEspecialistas();
    }
    
    /**
     * Hecho, Done, terminé
     * 
     * @param idSpetialist
     * @return
     */
    @GET
    @Path("/getpatientsbyspetialist/{idSpetialist}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getPatientsBySpetialist(@PathParam("idSpetialist") String idSpetialist){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerPacientesPorEspecialista(Integer.parseInt(idSpetialist));
    }
    
    /**
     * Hecho, Done, terminé
     * 
     * @param jsonPatientSchedule
     * @return
     */
    @GET
    @Path("/getpatientschedule/{jsonPatientSchedule}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getPatientSchedule(@PathParam("jsonPatientSchedule") String jsonPatientSchedule){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerCitaDePaciente(jsonPatientSchedule);
    }
    
    /**
     * Hecho, Done, terminé
     * 
     * @param idPatient
     * @return
     */
    @GET
    @Path("/getpatientschedules/{idPatient}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getPatientSchedules(@PathParam("idPatient") String idPatient){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerTodasCitasDePaciente(Integer.parseInt(idPatient));
    }
    
      @GET
    @Path("/getspetialistschedules/{idSpetialist}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getSpetialistSchedules(@PathParam("idSpetialist") String idSpetialist){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerCitasDeEspecialista(Integer.parseInt(idSpetialist));
    }
    
    /**
     * Hecho, Done, terminé
     * 
     * @param idPatient
     * @return
     */
    @GET
    @Path("/getstudybypatient/{jsonStudiesByPatient}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getStudiesByPatient(@PathParam("jsonStudiesByPatient") String jsonStudiesByPatient){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerEstudioDePaciente(jsonStudiesByPatient);
    }
    
    /**
     * ???????????????????????
     * 
     * @param jsonResultsAllSegments
     * @return
     */
    @GET
    @Path("/getresultsallsegments/{jsonResultsAllSegments}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getResultsAllSegments(@PathParam("jsonResultsAllSegments") String jsonResultsAllSegments){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerResultadosTodosLosSegmentos(Integer.parseInt(jsonResultsAllSegments));
    }
    
    /**
     * Hecho, Done, terminé
     * 
     * @param jsonResultSegmentsByInterval
     * @return
     */
    @GET
    @Path("/getresultsegmentbysecond/{jsonResultSegmentBySecond}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getResultsSegmentBySecond(@PathParam("jsonResultSegmentBySecond") String jsonResultSegmentBySecond){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerResultadosSegmentoPorSegundo(jsonResultSegmentBySecond);
    }
    
    
    /**
     * Hecho, Done, terminé
     * 
     * @param jsonResultSegmentsByInterval
     * @return
     */
    @GET
    @Path("/getgeneralresultsbyschedule/{idSchedule}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getGeneralResultsBySchedule(@PathParam("idSchedule") String idSchedule){
        BusinessRules rules = new BusinessRules();
        return rules.obtenerResultadosGenerales(Integer.parseInt(idSchedule));
    }
    
    /**
     * Hecho, Done, terminé
     * 
     * @param jsonSchedule
     * @return
     */
    @GET
    @Path("/scheduleappointment/{jsonSchedule}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String setScheduleAppointment(@PathParam("jsonSchedule") String jsonSchedule){
        BusinessRules rules = new BusinessRules();
        return rules.requestScheduleAppointment(jsonSchedule);
    }
    
    
    /**
     * Hecho, Done, terminé
     * 
     * @param jsonUpdateSchedule
     * @return
     */
    @GET
    @Path("/requestupdateschedule/{jsonUpdateSchedule}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String requestUpdateSchedule(@PathParam("jsonUpdateSchedule") String jsonUpdateSchedule){
        BusinessRules rules = new BusinessRules();
        return rules.requestUpdateSchedule(jsonUpdateSchedule);
    }
    
    
    
    /**
     * Hecho, Done, terminé
     * 
     * @param jsonGeneralResults
     * @return
     */
    @GET
    @Path("/storegeneralresults/{jsonGeneralResults}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String storeGeneralResults(@PathParam("jsonGeneralResults") String jsonGeneralResults){
        BusinessRules rules = new BusinessRules();
        return rules.requestStoreGeneralResults(jsonGeneralResults);
    }
    
    /**
     * Hecho, Done, terminé
     * 
     * @param jsonResultsBySegment
     * @return
     */
    @GET
    @Path("/storeresultsbysegment/{jsonResultsBySegment}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String storeResultsBySegment(@PathParam("jsonResultsBySegment") String jsonResultsBySegment){
        BusinessRules rules = new BusinessRules();
        return rules.requestStoreResultsBySegment(jsonResultsBySegment);
    }
    
    /**
     * Hecho, Done, terminé
     * 
     * @param jsonResultsByChanel
     * @return
     */
    @GET
    @Path("/storeresultsbychanel/{jsonResultsByChanel}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String storeResultsByChanel(@PathParam("jsonResultsByChanel") String jsonResultsByChanel){
        BusinessRules rules = new BusinessRules();
        return rules.requestStoreResultsByChanel(jsonResultsByChanel);
    }
    
    @GET
    @Path("/storefilerecording/{jsonFileRecording}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String storeFileRecording(@PathParam("jsonFileRecording") String jsonFileRecording){
        BusinessRules rules = new BusinessRules();
        return rules.requestStoreFileRecording(jsonFileRecording);
    }
    
    @GET
    @Path("/getdevicesbypatient/{idPatient}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getDevicesByPatient(@PathParam("idPatient") String idPatient){
        BusinessRules rules = new BusinessRules();
        return rules.requestGetDevices(Integer.parseInt(idPatient));
    }
    
    @GET
    @Path("/storedevicebypatient/{jsonDevice}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String storeDeviceByPatient(@PathParam("jsonDevice") String jsonDevice){
        BusinessRules rules = new BusinessRules();
        return rules.requestStoreDevice(jsonDevice);
    }
    
    
    
    @GET
    @Path("/deletedevicebypatient/{jsonDevice}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String deleteDeviceByPatient(@PathParam("jsonDevice") String jsonDevice){
        BusinessRules rules = new BusinessRules();
        return rules.requestDeleteDevices(jsonDevice);
    }
    //requestStoreDevice
    
    @GET
    @Path("/deleteschedulebyid/{idSchedule}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String deleteScheduleById(@PathParam("idSchedule") String idSchedule){
        BusinessRules rules = new BusinessRules();
        return rules.requestDropSchedule(Integer.parseInt(idSchedule));
    }
    
    
    @GET
    @Path("/deletepatientbyid/{idPatient}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String deletePatientById(@PathParam("idPatient") String idPatient){
        BusinessRules rules = new BusinessRules();
        return rules.requestDropPatient(Integer.parseInt(idPatient));
    }
    
    @GET
    @Path("/deletespetialistbyid/{idSpetialist}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String deleteSpetialistById(@PathParam("idSpetialist") String idSpetialist){
        BusinessRules rules = new BusinessRules();
        return rules.requestDropSpetialist(Integer.parseInt(idSpetialist));
    }
}
