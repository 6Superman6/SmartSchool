package Demo.service;

import Demo.model.Test;
import Demo.service.base.IBaseService;

import java.util.List;

public interface TestService  extends IBaseService<Test> {
    public List<Test> findAll();//查找所有考试
    public String insert(Test test);//管理员添加考试
    public String delete(Integer tid);//管理员删除考试
    public String  update(Test test);//管理员修改考试
    public List<Test> selectByCollege(String tcollege);//按院校搜索考试
}

