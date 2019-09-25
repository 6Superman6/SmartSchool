package Demo.service;

import Demo.model.Lostfound;
import Demo.service.base.IBaseService;

import java.util.List;

public interface LostFoundService extends IBaseService<Lostfound> {

    public List<Lostfound> getAllLost(String lflag,String udept);//查看所有的失物信息  -- 失主  功能6

    public List<Lostfound> getAllFound(String lflag,String udept);//查看所有的得物信息  -- 得主  功能7



    public String updateById(Lostfound lostfound);//根据id修改记录信息

    public List getAllThing(String uid);//根据id查看所有的自己信息
}
