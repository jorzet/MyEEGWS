/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessRules;

/**
 *
 * @author Jorge Zepeda Tinoco
 */
public class Palabras {
    
    public static final int ERROR_INCORRECT_PASSWORD =                  0x00;
    public static final int WITHOUT_ACOUNT =                              -1;
    public static final int PATIENT_USER =                              0x01;
    public static final int SPETIALIST_USER =                           0x02;
    public static final int ADMINISTRATOR_USER =                        0x03;
    
    public static final int CODE_ERROR_NOT_SPETIALISTS =                0x04;
    public static final int CODE_ERROR_NOT_PATIENTS =                   0x05;
    public static final int CODE_ERROR_NOT_SCHEDULES =                  0x06;
    public static final int CODE_ERROR_FROM_JSON =                      0x07;
    public static final int CODE_SYNTAX_ERROR_FROM_JSON =               0x08;
    public static final int CODE_ERROR_HAPPENED_A_PROBLEM =             0x09;
    public static final int CODE_ERROR_SPETIALIST_NOT_EXISTS =          0x0A;
    public static final int CODE_ERROR_PATIENT_NOT_EXISTS =             0x0B;
    public static final int CODE_ERROR_USER_NOT_EXISTS =                0x0B;
    public static final int CODE_ERROR_SCHEDULE_NOT_EXISTS =            0x0D;
    public static final int CODE_ERROR_STUDY_NOT_EXISTS =               0x0E;
    public static final int CODE_ERROR_INCORRECT_EMAIL_OR_PASSWORD =    0x0F;
    public static final int CODE_ERROR_FILE_NOT_FOUND =                 0x10;
    public static final int CODE_ERROR_IO_EXCEPTION =                   0x11;
    
    public static final int CODE_SUCESSFULL_SING_IN =                   0x12;
    public static final int CODE_SUCESSFULL_SCHEDULE_APOINTMENT =       0x13;
    public static final int CODE_SUCESSFULL_STORE_RECORDING =           0x14;
    public static final int CODE_SUCESSFULL_STORE_GENERAL_RESULTS=      0x15;
    public static final int CODE_SUCESSFULL_STORE_CHANEL_RESULTS =      0xA0;
    public static final int CODE_SUCESSFULL_STORE_SEGMENT_RESULTS =     0xA1;
    public static final int CODE_SUCESSFULL_TRANSFER_FILES_COMPLETE =     0xA2;
    //public static final int CODE_SUCESSFULL_STORE_RECORDING =           0x16;
    
    
    /* Json */
    public static final String USER = "User";
    public static final String TOKEN = "Token";
    public static final String EMAIL_USER = "email";
    public static final String PASSWORD_USER = "password";
    public static final String USER_TYPE = "user_type";
    public static final String ID_USER = "id_user";
    public static final String ID_SCHEDULE = "id_schedule";
    public static final String ID_PATIENT = "id_patient";
    public static final String SINCE_SECOND = "since_second";
    public static final String TO_SECOND = "to_second";
    public static final String DEVICE_NAME = "device_name";
    public static final String DEVICE_MAC_ADDRESS = "device_mac_address";
    public static final String ID_DEVICE = "id_device";
    public static final String SECOND = "second";
    
    /* to store the recording file */
    public static final String FILE_NAME = "file_name";
    public static final String DATE_RECORDING = "date_recording";
    public static final String SCHEDULE_ID = "schedule_id";    
    public static final String CHANNEL_LIST = "channel_list";
    public static final String CHANNEL_NAME = "channel_name";
    public static final String DOWNLOAD_COMPLETE = "download_complete";
    public static final String TRANSFER_STATUS_RECORDING = "transfer_status_recording";
    public static final String TOTAL_BYTES = "total_bytes";
    public static final String BYTES_FILE = "bytes_file";
    public static final String CURRENT_POSITION_FILE = "current_position_file";
    public static final String FINAL_POSITION_FILE = "final_position_file";
    public static final String SUCESSFULL_STORE_RECORDING = "sucessfull_store_recording";
    
    /* Español */
    public static final String ERROR_NOT_DEVICES = "Error, la respuesta es nula, no existen dispositivos";
    public static final String ERROR_NOT_SPETIALISTS = "Error, la respuesta es nula, no existen especialistas por el momento";
    public static final String ERROR_NOT_PATIENTS = "Error, la respuesta es nula, no existen pacientes por el momento";
    public static final String ERROR_NOT_SCHEDULES = "Error, la respuesta es nula, no existen citas por el momento";
    public static final String ERROR_NOT_GENERAL_RESULTS = "Error, no existen los resultados generales de esta cita";
    public static final String ERROR_CHANNEL_RESULTS = "Error, no existen resultados del canal selaccionado";
    public static final String ERROR_FROM_JSON = "Ha ocurrido un error, vuelve a intentarlo";
    public static final String SYNTAX_ERROR_FROM_JSON = "Error de sintaxis, asegurate de enviar los datos en formato Json";
    public static final String ERROR_HAPPENED_A_PROBLEM = "Error, ocurrio un problema, intentalo mas tarde";
    public static final String ERROR_SPETIALIST_NOT_EXISTS = "Error, el especialista no existe";
    public static final String ERROR_PATIENT_NOT_EXISTS = "Error, el paciente no existe";
    public static final String ERROR_USER_NOT_EXISTS = "Error, no tienes una cuenta. ¡Registrate!";
    public static final String ERROR_SCHEDULE_NOT_EXISTS = "Error, la cita no existe";
    public static final String ERROR_STUDY_NOT_EXISTS = "Error, no existe el estudio correspondiente al usuario";
    public static final String ERROR_INCORRECT_EMAIL_OR_PASSWORD = "Error, el usuario y/o contraseña son invalidos";
    
    /* to send email */
    public static final String EMAIL_SINGUP_SUBJECT = "Registro de usuario";
    public static final String EMAIL_RESTART_PASSWORD_SUBJECT = "Recuperación de contraseña";
    public static final String YOUR_EMAIL_IS = "Tu correo electrónico es: ";
    public static final String YOUR_PASSWORD_IS = "Tu contraseña es: ";
    public static final String YOUR_CONFIRMATION_DODE_IS = "Tu codigo de confirmacion es: ";
    public static final String EMAIL_SENDED = "Se envio un email a tu correo electronico";
    
    public static final String OK ="OK";
    public static final String SUCESSFULL_SINGUP = "Usuario registrado correctamente";
    public static final String SUCESSFULL_SCHEDULE_DROP = "Datos eliminados correctamente";
    
    public static final String PATIENT_TYPE = "Paciente";
    public static final String SPETIALIST_TYPE = "Especialista";
    public static final String ADMINISTRATOR_TYPE = "Administrador";
    
    /* Ingles */
    
    public Palabras(){
    }
    
}
