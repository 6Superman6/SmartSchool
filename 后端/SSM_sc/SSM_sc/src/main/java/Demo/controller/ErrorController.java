package Demo.controller;

import Demo.utils.ResponseCode;
import Demo.utils.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/noLogin")
    public ServerResponse noLogin(){
        return ServerResponse.createByError(ResponseCode.PERMISSION_DENIED.getStatus(),"用户未登录");
    }

}
