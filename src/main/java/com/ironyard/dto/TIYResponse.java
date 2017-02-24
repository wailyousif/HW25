package com.ironyard.dto;

/**
 * Created by wailm.yousif on 2/23/17.
 */
public class TIYResponse
{
    boolean success;
    int number;

    public TIYResponse() { }

    public TIYResponse(boolean success, int number) {
        this.success = success;
        this.number = number;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
