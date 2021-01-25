package ru.ashirobokov.library.response;

public class Response {

    private Object data;
    private String error;

    public Response() {
    }

    public Response(Object data) {
        this.data = data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
