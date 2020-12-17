package apap.tugas.sipelatihan.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KaryawanBaruResponse {
    private int status;
    private String message;
    private List<KaryawanBaruDto> result;

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    public void setResult(List<KaryawanBaruDto> result) {
        this.result = result;
    }

    public List<KaryawanBaruDto> getResult() {
        return result;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}