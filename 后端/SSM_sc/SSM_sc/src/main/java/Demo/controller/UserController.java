package Demo.controller;

import Demo.model.User;
import Demo.service.UserService;
import Demo.utils.ImaTool;
import Demo.utils.ResponseCode;
import Demo.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    public static final String SESSION_KEY = "LOGIN_USER";
    private static final String AUTO_LOGIN_KEY = "auto_login_taken";
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public @ResponseBody ServerResponse register(@Valid User user){

        if(user==null)
        {
            return ServerResponse.createByErrorMsg2("数据丢失");    //报备失败，数据有误  -1
        }
        //System.out.println("iop");
        String message = userService.register(user);
        //System.out.println(message);
        if(message.equals("注册成功"))
        {

            return ServerResponse.createBySuccessMsg("注册成功");  // 0
        }
        if(message.equals("缺少参数"))
        {
            return ServerResponse.createByErrorMsg2("缺少参数");  // -2
        }
        if(message.equals("账号已经存在"))
        {
            return ServerResponse.createByError("账号已经存在");   //账号已经存在   -1
        }
        return ServerResponse.createByError("注册失败，数据有误");    //报备失败，数据有误  -1

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody ServerResponse login(@RequestParam("uid") String uid,@RequestParam("upsd") String upsd,@RequestParam("autoLogin") boolean autoLogin, HttpServletResponse response, HttpSession session){
        if(uid==null||uid.length()==0||upsd==null||upsd.length()==0)
        {
            return ServerResponse.createByErrorMsg2("缺少参数");    //缺少参数  -2
        }
//        System.out.println(uid+"   "+upsd+"   "+autoLogin);
        String message = userService.login(uid,upsd,autoLogin);
        User user=userService.selectByUUid(uid);
        //ServerResponse<User> result = userServer.login(name, password, autoLogin);
        //System.out.println(uid+"   "+upsd+"   "+autoLogin);
        if(message.equals("缺少参数"))
        {
            //System.out.println(uid+"   "+upsd);
            return ServerResponse.createByErrorMsg2("缺少参数");  // -2
        }
        if(message.equals("用户名或密码错误"))
        {
            return ServerResponse.createByError("账号或密码错误");   //账号或密码错误   -1
        }
        ServerResponse serverResponse=null;
        if(message.equals("登录成功"))
        {
            serverResponse=ServerResponse.createBySuccess("登录成功",user);  // 0
            /*serverResponse=ServerResponse.createBySuccessMsg("登录成功");  // 0
            //记录会话信息
            serverResponse.setData(user);*/
            //System.out.println("   5464   "+serverResponse.getData());
            session.setAttribute(SESSION_KEY, serverResponse.getData());
            if (autoLogin) {
                //Cookie中写入自动登录凭证
                String taken = user.getTaken();
                Cookie cookie = new Cookie(AUTO_LOGIN_KEY, taken);
                cookie.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(cookie);
            }
            return serverResponse;
        }
        return ServerResponse.createByErrorMsg2("未知错误");  // -2
    }

    @RequestMapping(value = "/login/auto", method = RequestMethod.POST)
    public @ResponseBody ServerResponse autoLogin(HttpServletRequest request, HttpSession session) {
        //获取Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return ServerResponse.createByError(ResponseCode.MISSING_ARGUMENT.getStatus(), "未获取Cookie");//-3
        }
        //遍历查找凭证
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(AUTO_LOGIN_KEY)) {
                //调用Service方法自动登录
                //ServerResponse<User> autoLoginResult = null;
                String open= userService.autoLogin(cookie.getValue());
                if (open.equals("自动登录成功")) {
                    User user= userService.SelectTaken(cookie.getValue());
                    session.setAttribute(SESSION_KEY, user);
                    return ServerResponse.createBySuccess("登陆成功", user);
                }
            }
        }
        return ServerResponse.createByError("未找到凭证");//-1
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public @ResponseBody ServerResponse logout(HttpSession session,HttpServletResponse response){
        session.removeAttribute(SESSION_KEY);
        Cookie cookie = new Cookie(AUTO_LOGIN_KEY,null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ServerResponse.createBySuccessMsg("退出登录成功");
    }

   /* @RequestMapping(value = "/update",method = RequestMethod.POST)
    public @ResponseBody ServerResponse update(@RequestBody Map<String, String> params){
        //System.out.println("+++");
        String image=params.get("uimage");
        String name=params.get("uname");
        String id=params.get("uid");
        //System.out.println(image+"   "+name+"   "+id);
        String m=userService.updateuser(image,name,id);
        if(m.equals("缺少参数"))
            return ServerResponse.createByErrorMsg2("缺少参数");  // -2
        if (m.equals("相同名称"))
            return ServerResponse.createBySuccessMsg("注册成功");  // 0
        if (m.equals("修改成功"))
            return ServerResponse.createBySuccessMsg("修改成功");  // 0
        return ServerResponse.createByErrorMsg2("未知错误");  // -2
    }*/
   @RequestMapping(value = "/update",method = RequestMethod.POST)
   public @ResponseBody ServerResponse update(@RequestParam("uid") String uid,@RequestParam("upsd") String upsd,
           @RequestParam("uname") String uname,@RequestParam("uemail") String uemail,@RequestParam("utel") String utel,
           @RequestParam("udept") String udept, HttpServletRequest request, MultipartFile uimage){

       User user = new User();
       user.setUid(uid);
       user.setUpsd(upsd);
       user.setUname(uname);
       user.setUemail(uemail);
       user.setUtel(utel);
       user.setUdept(udept);
       String filename = ImaTool.Imagetool(request,uimage);
       if(filename!=null)
       {
           user.setUimage("localhost/uploads/"+filename);
       }
       String m=userService.updateuser(user);
       //System.out.println(m);
       if(m.equals("缺少参数"))
           return ServerResponse.createByErrorMsg2("缺少参数");  // -2
       if (m.equals("未发生更改"))
           return ServerResponse.createByError("未发生更改");  // -1
       if (m.equals("修改成功"))
       {
           return ServerResponse.createBySuccessMsg("修改成功");  // 0
       }
       return ServerResponse.createByErrorMsg3("该账号不存在");  // -3
   }

    /**
     * SpringMVC实现文件上传
     *      MultipartFile就是对文件的封装。
     */
    @RequestMapping("/fileupload2")
    public String fileupload2(HttpServletRequest request, MultipartFile upload)  {
        String s = ImaTool.Imagetool(request,upload);
        System.out.println(request.getContextPath()+"/uploads/"+s);
        return "success";
    }

}
