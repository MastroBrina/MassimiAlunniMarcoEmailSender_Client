/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package massimialunnimarcoemailsender_client;

import java.util.regex.Pattern;

/**
 *
 * @author massimialunni.marco
 */
public class SMTPResponseParser{
    public static SMTPResponse parse(String rawResponse){
        if (rawResponse == null || rawResponse.isEmpty()) {
            return new SMTPResponse(0, "Nessuna risposta", "");
        }
        int code = 0;
        String message = "";
        try {
            code = Integer.parseInt(rawResponse.substring(0, 3));
            if(rawResponse.length()>4){
                message =rawResponse.substring(4);
            }else{
                message = "";
            }
        } catch (Exception e) {
            message = "Errore nel parsing della risposta";
        }
        return new SMTPResponse(code, message, rawResponse);
    }
    
    public static SMTPResponse parseMultiLine(String rawResponse){
        String[] lines = rawResponse.split("\n");
        String allLines = "";
        int code = 0;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            allLines += line + "\n";

            if (line.length() >= 3) {
                try {
                    code = Integer.parseInt(line.substring(0, 3));
                } catch (NumberFormatException e) {
                    code = 0;
                }
            }
            // in caso fosse l'ultima riga
            if (line.length() > 3 && line.charAt(3) != '-') {
                break;
            }
        }
        return new SMTPResponse(code, "Risposta multi-linea", allLines);
    }
    
    public static Boolean isSuccessCode(int code){
        Boolean isSuccessCode=false;
        if(code >= 200 && code < 300){
            isSuccessCode=true;
        }
        return isSuccessCode;
    }
    
    public static Boolean isErrorCode(int code){
        Boolean isErrorCode=false;
        if(code >= 400){
            isErrorCode=true;
        }
        return isErrorCode;
    }
    
    public static Boolean isIntermediateCode(int code){
        Boolean isIntermediateCode=false;
        if(code >= 300 && code < 400){
            isIntermediateCode=true;
        }
        return isIntermediateCode;
    }
    
    public static String getResponseType(int code){
        if (isSuccessCode(code)){ 
            return "Successo";
        }
        else if (isIntermediateCode(code)){
            return "Intermedio";
        }
        else if (isErrorCode(code)){ 
            return "Errore";
        }
        
        return "Sconosciuto";
    }
}
