package com.daniel.result;

/**
 * @Package: com.daniel.result
 * @ClassName: CodeMsg
 * @Author: daniel
 * @CreateTime: 2021/3/17 11:23
 * @Description: 封装的响应信息
 */
public class CodeMsg {
    /**
     * 响应的code
     */
    private int code;

    /**
     * 响应的描述信息
     */
    private String msg;

    /**
     * 自定义响应信息
     */
    public static CodeMsg SUCCESS = new CodeMsg(0,"success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100,"服务器异常");

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
