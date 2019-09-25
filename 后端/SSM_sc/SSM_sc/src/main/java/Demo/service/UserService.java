package Demo.service;

import Demo.model.User;
import Demo.service.base.IBaseService;
import Demo.utils.ServerResponse;

public interface UserService extends IBaseService<User> {

    public String login(String uid,String upsd,boolean autoLogin);
    public ServerResponse<String> registerAutoLogin(User user);
    public String autoLogin(String taken);

    public User SelectTaken(String taken);
    public String register(User user);
    //public String updateuser(String image,String name,String id);
    public String updateuser(User user);
}

