package Demo.service.impl;

import Demo.model.Test;
import Demo.service.TestService;
import Demo.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TestServiceImpl extends BaseServiceImpl<Test> implements TestService {
    @Override
    public List<Test> selectByCollege(String tcollege) {
        try{
            List<Test> list=testMapper.selectByCollege(tcollege);
            if(list.isEmpty())
            {
                return null;
            }
            return list;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Test> findAll() {
        try{
            List<Test> list=testMapper.findAll();
            if(list.isEmpty())
            {
                return null;
            }
            return list;
        }
        catch (Exception e){
            return null;
        }
    }
    @Override
    public String insert(Test test) {
        if(test.getTname()==null||test.getTname().length()==0||test.getTurl()==null||test.getTurl().length()==0||test.getTcollege()==null||test.getTcollege().length()==0)
        {
            return "缺少参数";
        }
        try{
            List<Test> find=testMapper.findAll();
            if(find.isEmpty())
            {
                testMapper.insert(test);
                return "添加成功";
            }
            for(Test t:find)
            {
                if(t.getTname().equals(test.getTname())&&t.getTurl().equals(test.getTurl()))
                {
                    return "该考试已经存在";
                }
            }
            testMapper.insert(test);
            return "添加成功";
        }
        catch (Exception e){
            return "添加失败";
        }
    }

    @Override
    public String delete(Integer tid) {
        try {
            if (testMapper.selectByPrimaryKey(tid) != null) {
                testMapper.deleteByPrimaryKey(tid);
                if (testMapper.selectByPrimaryKey(tid) == null) {
                    return "删除成功";
                } else {
                    return "删除失败，该考试编号不存在";
                }
            }
            else{
                return "删除失败，该考试编号不存在";
            }
        }
        catch (Exception e)
        {
            return "删除失败，该考试编号不存在";
        }
    }
    @Override
    public String update(Test test) {
        if(test.getTname()==null||test.getTname().length()==0||test.getTurl()==null||test.getTurl().length()==0||test.getTcollege()==null||test.getTcollege().length()==0)
        {
            return "缺少参数";
        }
        try{
            List<Test> find=testMapper.findAll();
            if (testMapper.selectByPrimaryKey(test.getTid()) != null) {
                for(Test t:find)
                {
                    if(t.getTname().equals(test.getTname())&&t.getTurl().equals(test.getTurl()))
                    {
                        return "该考试已经存在";
                    }
                }
//                testMapper.updateByPrimaryKey(test);
                testMapper.update(test);


                return "修改成功";
            }
            else{
                return "修改失败";
            }
        }
        catch (Exception e){
                return "修改失败";
        }
    }
    @Override
    public String deleteByUUid(String uuid) {
        return null;
    }

    @Override
    public Test selectByUUid(String uuid) {
        return null;
    }

    @Override
    public Test select(Integer id) {
        return null;
    }

    @Override
    public Test selectByUsername(String username) {
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
