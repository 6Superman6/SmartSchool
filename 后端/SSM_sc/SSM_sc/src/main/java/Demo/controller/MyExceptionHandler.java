package Demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice   //异常处理器
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler    // 如果后边不跟(*****.class) 默认是对所有的异常进行处理
//    @ExceptionHandler(UserNotExistException.class)  //支队UserNotExistException异常进行处理
    public Map<String,Object> handlerException(Exception e)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("status","-1");
        map.put("msg","发生未知错误");
        return map;
    }

}
