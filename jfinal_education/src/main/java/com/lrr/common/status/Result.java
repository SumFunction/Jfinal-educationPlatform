package com.lrr.common.status;


import java.util.HashMap;
import java.util.Map;

public class Result {
    private boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap<>();
    private Result(){};
    public static Result ok(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }
    public static Result error(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(StatusCode.ERROR);
        result.setMessage("失败");
        return result;
    }
    public static Result frontNoLogin(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(StatusCode.FrontNoLogin);
        result.setMessage("请先登录");
        return result;
    }
    public static Result noAuthority(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(StatusCode.NoAuthority);
        result.setMessage("你当前权限不足，不能进行操作！");
        return result;
    }
    public Result success(boolean success){
        this.setSuccess(success);
        return this;
    }
    public Result code(Integer code){
        this.setCode(code);
        return this;
    }
    public Result message(String message){
        this.setMessage(message);
        return this;
    }
    public Result data(String key,Object value){
        this.getData().put(key,value);
        return this;
    }
    public Result data(Map<String,Object> data){
        this.setData(data);
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
