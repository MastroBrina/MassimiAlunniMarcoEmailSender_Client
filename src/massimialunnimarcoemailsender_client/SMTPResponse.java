/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package massimialunnimarcoemailsender_client;

/**
 *
 * @author massimialunni.marco
 */
public class SMTPResponse {
    private int code;
    private String message;
    private String rawResponse;

    public SMTPResponse(int code, String message, String rawResponse) {
        this.code = code;
        this.message = message;
        this.rawResponse = rawResponse;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }
    
    public Boolean isSuccess(){
        Boolean isSuccessCode=false;
        if(code >= 200 && code < 300){
            isSuccessCode=true;
        }
        return isSuccessCode;
    }
    
    public Boolean isError(){
        Boolean isErrorCode=false;
        if(code >= 400){
            isErrorCode=true;
        }
        return isErrorCode;
    }
    
    public Boolean isIntermediate(){
        Boolean isIntermediateCode=false;
        if(code >= 300 && code < 400){
            isIntermediateCode=true;
        }
        return isIntermediateCode;
    }

    @Override
    public String toString() {
        return code +" "+ message +" "+ rawResponse;
    }

    public String toDetailedString(){
        return "Il codice del messaggio è: " + code + "\nIl messaggio è: " + message + "\nLa Risposta completa è: " + rawResponse;
    }
}
