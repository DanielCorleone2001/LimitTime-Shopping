package com.daniel.exception;

import com.daniel.result.CodeMsg;

/**
 * @Package: com.daniel.exception
 * @ClassName: GlobalException
 * @Author: daniel
 * @CreateTime: 2021/3/30 21:20
 * @Description: 异常类的封装
 */
public class GlobalException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    /**
     * 通过异常信息来构造异常类
     * @param codeMsg 构造异常类的异常信息
     */
    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    /**
     * 获取异常信息
     * @return
     */
    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
