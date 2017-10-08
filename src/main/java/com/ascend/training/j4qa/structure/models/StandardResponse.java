package com.ascend.training.j4qa.structure.models;


import com.ascend.training.j4qa.structure.constants.ResponseStatus;

public class StandardResponse {

    private ResponseStatus status;

    private Object data;

    public StandardResponse(ResponseStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
