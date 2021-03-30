package com.daniel.exception;

import com.daniel.result.CodeMsg;
import com.daniel.result.DataResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Package: com.daniel.exception
 * @ClassName: GlobalExceptionHandler
 * @Author: daniel
 * @CreateTime: 2021/3/30 21:21
 * @Description: 全局异常的处理逻辑
 */

@ResponseBody//将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据。
@ControllerAdvice//结合方法型注解@ExceptionHandler，用于捕获Controller中抛出的指定类型的异常，从而达到不同类型的异常区别处理的目的；
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public DataResult<String> exceptionHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        /**
         * 判断异常的类型
         */
        if ( e instanceof GlobalException ) {
            GlobalException globalException = (GlobalException) e;
            return DataResult.error(globalException.getCodeMsg());
        } else if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            List<ObjectError> errorList = bindException.getAllErrors();
            ObjectError objectError = errorList.get(0);
            String errorMsg = objectError.getDefaultMessage();
            return DataResult.error(CodeMsg.BIND_ERROR.fillArgs(errorMsg));
        } else {
            return DataResult.error(CodeMsg.SERVER_ERROR);
        }
    }
}
