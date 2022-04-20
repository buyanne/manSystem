package entityClass;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : Message //ÀàÃû
 * @Author : ß²ÑÔÄØ
 * @Data : 2021/12/16
 */
public class Message implements Serializable {


    private String controller;

    private int code;

    private String message;

    private Map<String, Object> params = new HashMap<>();

    private Object data;

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
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

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getParameter(String key) {
        return params.get(key);
    }

    public void setParameter(String key, Object value) {
        params.put(key, value);
    }
}
