package com.springboot.insurtechbackend.model;


import java.io.Serializable;

/**
 * Used to package the backend to return the frontend data template/format
 */
public class ResultInfo implements Serializable {
    private String flag;//returns a normal is true，An exception  return false
    private Object data;// data come form the backend
    private String errorMsg;//error message

    //construction method no parameter
    public ResultInfo() {
    }
    public ResultInfo(String flag) {
        this.flag = flag;
    }
    /**
     * construction method
     * @param flag
     * @param errorMsg
     */
    public ResultInfo(String flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }
    /**
     *  construction method
     * @param flag
     * @param data
     * @param errorMsg
     */
    public ResultInfo(String flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public String isFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getFlag() {
        return flag;
    }
}
