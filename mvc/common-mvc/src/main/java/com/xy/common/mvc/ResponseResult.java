package com.xy.common.mvc;

import java.io.Serializable;

public class ResponseResult implements Serializable {

    /**
     * 状态。0：失败；2：成功
     */
    private int status;
    /**
     * 信息
     */
    private String msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
