package Demo.service.impl;

import Demo.model.User;
import Demo.service.UserService;
import Demo.service.base.BaseServiceImpl;
import Demo.utils.ServerResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Override
    public String insert(User user) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public String deleteByUUid(String uuid) {
        return null;
    }

    @Override
    public String update(User user) {
        return null;
    }

    @Override
    public User selectByUUid(String uuid) {
        return userMapper.select(uuid);
    }

    @Override
    public User select(Integer id) {
        return null;
    }

    @Override
    public User selectByUsername(String username) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public int getCountByUUid(String uuid) {
        return 0;
    }

    @Override
    public int getCountById(Integer id) {
        return 0;
    }

    @Override
    public String login(String uid, String upsd, boolean autoLogin) {
        //查询数据库并判断登录
        if(uid==null||uid==""||upsd==null||upsd=="")
            return "缺少参数";
        User user = userMapper.select(uid);
        if (user != null && upsd.equals(user.getUpsd())) {
            if (autoLogin) {
                //将自动登录凭证写入数据库和用户信息
                //System.out.println("llllllll");
                user.setTaken(registerAutoLogin(user).getData());
                //System.out.println("llllllll");
            }
            return "登录成功";
        }
        return "用户名或密码错误";
    }

    @Override
    public ServerResponse<String> registerAutoLogin(User user) {
        //System.out.println("222222");
        //构建凭证元数据
        String source = user.getUid() + user.hashCode() + System.currentTimeMillis();
       // System.out.println("222222");
        //编译自动登录凭证
        String taken = BCrypt.hashpw(source, BCrypt.gensalt());
        //System.out.println("222222");
        //将自动登录凭证写入数据库
        try {
            userMapper.registerAutoLogin(user.getUid(), taken);
        }catch (Exception e){

        }
        //System.out.println("222222");
        return ServerResponse.createBySuccess(taken);
    }

    @Override
    public String autoLogin(String taken) {
        //查询数据库并判断登录
        User user = userMapper.autoLogin(taken);
        if (user != null) {
            return ( "自动登录成功");
        }
        return ("登录失败");
    }

    @Override
    public User SelectTaken(String taken) {
        return userMapper.autoLogin(taken);
    }

    @Override
    public String register(User user) {
        if(user.getUid()==null||user.getUid()==""||user.getUdept()==null||user.getUemail()==null||user.getUgrade()==null||user.getUname()==null||user.getUpsd()==null||user.getUtel()==null)
        {
            if(user.getUpsd().length()==0||user.getUname().length()==0||user.getUemail().length()==0||user.getUtel().length()==0)
            {
                return "缺少参数";
            }

        }
       // System.out.println("-------");

        List<User> list = userMapper.findAll();
        //System.out.println(list);
        for (User luser:list) {
            if(luser.getUid().equals(user.getUid()))
                return "账号已经存在";
        }
        //System.out.println("service");
        userMapper.insert(user);
        return "注册成功";
    }

    @Override
    public String updateuser(User user) {
        //System.out.println("--");
        User user1 = null;
        user1 = userMapper.select(user.getUid());
        String password = user.getUpsd();
        String tel = user.getUtel();
        String name = user.getUname();
        String email = user.getUemail();
        String image = user.getUimage();
        String dept = user.getUdept();
        if(password==null||password.length()==0||tel==null||tel.length()==0||name==null||name.length()==0||email==null||email.length()==0)
            return "缺少参数";
        int s=0;
        if(user1==null)
        {
            return "该账号不存在";
        }
        if(password.equals(user1.getUpsd()))
        {
            user.setUpsd(null);
            s++;
        }
        //System.out.println("--");
        if(tel.equals(user1.getUtel()))
        {
            user.setUtel(null);s++;
        }

        if (name.equals(user1.getUname()))
        {
            user.setUname(null);s++;
        }
        if (email.equals(user1.getUemail()))
        {
            s++;
            user.setUemail(null);
        }
        if (image!=null&&image.length()!=0&&image.equals(user1.getUimage()))
        {
            s++;
            user.setUimage(null);
        }
        if (dept!=null&&dept.length()!=0&&dept.equals(user1.getUdept()))
        {
            s++;
            user.setUdept(null);
        }
        //System.out.println(user);
        //System.out.println(s);
        if (s==6)
            return "未发生更改";

        try {
            userMapper.updateUser(user,user.getUid());
        }
        catch (Exception e){

        }
        return "修改成功";
    }
    /*@Override
    public String updateuser(String uimage, String uname,String uid) {
        //System.out.println("获取用户");
        User user = userMapper.select(uid);
        //System.out.println(user);
        if(uimage==null&&uname==null&&uid==null)
        {
            return "缺少参数";
        }
        if(uimage!=null) {
            //System.out.println("即将修改图片");
            try {
                int a = userMapper.updateuimage(uimage,uid);
            }
            catch (Exception e){
                e.printStackTrace();
            }


        }

        if(uname!=null&&uname.equals(user.getUname()))
        {

            return "相同名称";
        }
        if (uname!=null)
            try {
                userMapper.updateuname(uname,uid);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        return "修改成功";
    }*/

}
