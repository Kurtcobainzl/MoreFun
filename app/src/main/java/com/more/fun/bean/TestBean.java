package com.more.fun.bean;

import java.io.Serializable;

/**
 * 作者：Kurt on 2018/7/3 15:39
 * 邮箱：876506231@qq.com
 */
public class TestBean implements Serializable {
    private String status;
    private String isShow;
    private String message;
    private String data;
    private String success;
    private String time;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "TestBean{" + "status='" + status + '\'' + ", isShow='" + isShow + '\'' + ", message='" + message + '\'' + ", data='" + data + '\'' + ", success='" + success + '\'' + ", time='" + time + '\'' + '}';
    }
}
