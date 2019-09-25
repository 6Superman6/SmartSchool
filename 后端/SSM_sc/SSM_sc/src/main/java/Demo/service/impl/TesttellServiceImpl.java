package Demo.service.impl;

import Demo.model.Testtell;
import Demo.service.TesttellService;
import Demo.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TesttellServiceImpl extends BaseServiceImpl<Testtell> implements TesttellService {

    @Override
    public List<Testtell> findAll(String udept) {
        try{
            List<Testtell> list=testtellMapper.findAll(udept);
            if(list.isEmpty())
            {
                return null;
            }
            else{
                return list;
            }
        }
        catch (Exception e)
        {
            return null;
        }
    }
    public String insert(Testtell testtell) {
        if(testtell.getTestname()==null||testtell.getTestname().length()==0||testtell.getTestdate()==null){
            return "缺少参数";
        }
        if(testtell.getTestarea()==null||testtell.getTestarea().length()==0||testtell.getImage()==null||testtell.getImage().length()==0){
            return "缺少参数";
        }
        try{
            List<Testtell> find=testtellMapper.findAll(testtell.getUdept());
            if(find.isEmpty())
            {
                testtellMapper.insert(testtell);
                return "添加成功";
            }
            for(Testtell t:find)
            {
                if(t.getTestname().equals(testtell.getTestname())&&t.getTestdate().equals(testtell.getTestdate())&&t.getTestarea().equals(testtell.getTestarea())&&t.getImage().equals(testtell.getImage()))
                {
                    return "该通知已经存在";
                }
            }
            testtellMapper.insert(testtell);
            return "添加成功";
        }
        catch (Exception e){
            return "添加失败";
        }
    }

    @Override
    public String delete(Integer testid) {
        try {
            if (testtellMapper.selectByPrimaryKey(testid) != null) {
                testtellMapper.deleteByPrimaryKey(testid);
                if (testtellMapper.selectByPrimaryKey(testid) == null) {
                    return "删除成功";
                } else {
                    return "删除失败，该通知编号不存在";
                }
            }
            else{
                return "删除失败，该通知编号不存在";
            }
        }
        catch (Exception e)
        {
            return "删除失败，该通知编号不存在";
        }
    }

    @Override
    public String deleteByUUid(String uuid) {
        return null;
    }

    @Override
    public String update(Testtell testtell) {
        if(testtell.getTestname()==null||testtell.getTestname().length()==0||testtell.getTestdate()==null){
            return "缺少参数";
        }
        if(testtell.getTestarea()==null||testtell.getTestarea().length()==0){
            return "缺少参数";
        }
        try{
            List<Testtell> find=testtellMapper.findAll(testtell.getUdept());
            if (testtellMapper.selectByPrimaryKey(testtell.getTestid()) != null) {
                for(Testtell t:find)
                {
                    if(t.getTestname().equals(testtell.getTestname())&&t.getTestdate().equals(testtell.getTestdate())&&t.getTestarea().equals(testtell.getTestarea())&&t.getImage().equals(testtell.getImage()))
                    {
                        return "该通知已经存在";
                    }
                }
                testtellMapper.update(testtell);
                return "修改成功";
            }
            else{
                return "修改失败,没有相关的通知编号";
            }
        }
        catch (Exception e){
            e.printStackTrace();  //待删
            return "修改失败";
        }
    }

    @Override
    public Testtell selectByUUid(String uuid) {
        return null;
    }

    @Override
    public Testtell select(Integer id) {
        return null;
    }

    @Override
    public Testtell selectByUsername(String username) {
        return null;
    }

    @Override
    public List<Testtell> findAll() {
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
}
