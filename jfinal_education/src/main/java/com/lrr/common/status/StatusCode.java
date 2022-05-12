package com.lrr.common.status;

public interface StatusCode {
    public static final Integer SUCCESS = 20000;//成功状态码
    public static final Integer ERROR = 40000;//失败状态码
    public static final Integer FrontNoLogin = 20010;
    public static final Integer NoAuthority = 20020;
}
