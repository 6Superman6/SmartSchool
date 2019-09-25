package Demo.service;


import Demo.model.Testtell;
import Demo.service.base.IBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface TesttellService extends IBaseService<Testtell> {
    public List<Testtell> findAll(String udept);//查找所有通知
    public String insert(Testtell testtell);//管理员添加通知
    public String delete(Integer testid);//管理员删除通知
    public String  update(Testtell testtell);//管理员修改通知
}
