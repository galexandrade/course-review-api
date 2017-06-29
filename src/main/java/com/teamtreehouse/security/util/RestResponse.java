package com.teamtreehouse.security.util;
import java.util.Map;

public class RestResponse {

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";
    public static final String STATUS_FAIL = "fail";

    private String status;

    private Integer code;

    private Object data;

    private String message;

    private Map<String,String> errors;

    /**
     * Empty constructor
     */
    public RestResponse() {
        super();
    }

    /**
     * Constructor with the minimum parameters necessary to build a success
     * response
     *
     * @param status
     *            String representing the response status
     * @param data
     *            Object containing the response content
     */
    public RestResponse(String status, Object data) {
        super();
        this.status = status;
        this.data = data;
    }

    /**
     * Constructor with all class fields
     *
     * @param status
     *            String representing the response status
     * @param code
     *            Integer representing the response status
     * @param data
     *            Object containing the response content
     * @param message
     *            String representing a textual response, may be used as a
     *            summary of errors list
     * @param errors
     *            List containing a list of all errors
     */
    public RestResponse(String status, Integer code, Object data, String message,
                        Map<String,String> errors) {
        super();
        this.status = status;
        this.code = code;
        this.data = data;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Getter of status
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter of status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter of code
     * @return code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Setter of code
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Getter of data
     * @return data
     */
    public Object getData() {
        return data;
    }

    /**
     * Setter of data
     * @param data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Getter of message
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setter of message
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Getter of errors
     * @return errors
     */
    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Setter of errors
     * @param errors
     */
    public void setErrors(Map<String,String> errors) {
        this.errors = errors;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + ((errors == null) ? 0 : errors.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RestResponse other = (RestResponse) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        if (errors == null) {
            if (other.errors != null)
                return false;
        } else if (!errors.equals(other.errors))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PoolRestResponse [status=" + status + ", code=" + code + ", data=" + data + ", message=" + message
                + ", errors=" + errors + "]";
    }

}
