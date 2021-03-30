package com.daniel.result;


/**
 * @Package: com.daniel.result
 * @ClassName: DataResult
 * @Author: daniel
 * @CreateTime: 2021/3/17 11:02
 * @Description: 封装数据类
 */
public class DataResult<T> {
    /**
     * 业务响应代码
     */
    int code;

    /**
     * 响应信息
     */
    String msg;
    /**
     * 传送的数据
     */
    T data;

    /**
     * 构造方法
     * @param data 初始化构造方法需要传入的数据
     */
    public DataResult (T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    /**
     * 控制层成功时调用
     * @param data 数据
     * @param <T> 范型
     * @return 成功
     */
    public static <T> DataResult<T> success(T data) {
        return new DataResult<>(data);
    }

    /**
     * 失败时调用
     * @param codeMsg
     * @param <T>
     * @return
     */
    public static <T> DataResult<T> error(CodeMsg codeMsg) {
        return new DataResult<T>(codeMsg);
    }

    /**
     * 响应信息的构造方法
     * @param codeMsg
     */
    private DataResult (CodeMsg codeMsg) {
        if ( codeMsg == null ) {
            return ;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
