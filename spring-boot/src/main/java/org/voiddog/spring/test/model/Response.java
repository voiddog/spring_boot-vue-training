package org.voiddog.spring.test.model;

public class Response<T> {

    public static Response error(int status, String message){
        Response response = new Response();
        response.status = status;
        response.message = message;
        return response;
    }

    public static <T> Response<T> success(T data){
        Response<T> response = new Response<>();
        response.status = Constants.STATUS_SUCCESS;
        response.data = data;
        response.message = "success";
        return response;
    }

    private int status;

    private String message;

    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
