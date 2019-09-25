package Demo.service.impl;

import Demo.mapper.LostfoundMapper;
import Demo.model.Lostfound;
import Demo.model.Record;
import Demo.model.User;
import Demo.service.LostFoundService;
import Demo.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LostFoundServiceImpl extends BaseServiceImpl<Lostfound> implements LostFoundService {


    @Override
    public String insert(Lostfound lostfound) {
   //     System.out.println("业务层： lostfound  插入一条记录");

        if(lostfound.getDes()==null||lostfound.getDes().length()==0||lostfound.getTime()==null)
        {
            return "缺少参数";
        }
        if(lostfound.getUid()==null||lostfound.getUid().length()==0||lostfound.getLflag()==null||lostfound.getLflag().length()==0)
        {
            return "缺少参数";
        }
        if(lostfound.getLstatic()==null||lostfound.getLstatic().length()==0||lostfound.getImage()==null||lostfound.getImage().length()==0)
        {
            return "缺少参数";
        }
        if(lostfound.getUdept()==null&&lostfound.getUdept().length()==0)
        {
            return "缺少参数";
        }

        List<Lostfound> lostfounds = lostfoundMapper.findAll();
        for(Lostfound lostfound1:lostfounds){
            if(lostfound1.getUid().equals(lostfound.getUid()) && lostfound1.getLstatic().equals(lostfound.getLstatic())
                && lostfound1.getDes().equals(lostfound.getDes()) && lostfound1.getImage().equals(lostfound.getImage())
                && lostfound1.getLflag().equals(lostfound.getLflag())){
                return "该数据已存在不要重复添加";
            }
        }
        try{
            List<User> list = userMapper.findAll();
            for(User user:list){
                if(user.getUid().equals(lostfound.getUid())){
                   // System.out.println("业务层：找到对应的人员，进行插入");
                    lostfoundMapper.insert(lostfound);
                    return "数据添加成功";
                }
            }
            return "人员账号不存在";
        }catch (DataIntegrityViolationException dataIntegrityViolationException) {
            return "人员账号不存在";
        }catch (Exception e) {
            return "添加失败";
        }
    }

    @Override
    public String delete(Integer id) {
       // System.out.println("业务层：删除数据..");
        try{
            int count = getCountById(id);
           // System.out.println(count);
            if(count==0){
                int a = 10/0;
            }
            lostfoundMapper.deleteByPrimaryKey(id);
            //System.out.println("---");
            return "删除成功";
        }catch(Exception e){
           // System.out.println("+++++");
            return "删除失败数据不存在";
        }
    }

    @Override
    public String updateById( Lostfound lostfound) {
        /*
        System.out.println("业务层：根据id更新一条记录。。。。");
        List<Lostfound> list  = lostfoundMapper.findAll();
        for(Lostfound lostfound1:list) {
            if (lostfound1.getId().equals(lostfound.getId())) {
                System.out.println("找到对应的id。。。。");
                lostfoundMapper.updateById(lostfound.getId());
                return "数据修改成功";
            }
        }
        return "数据修改失败";
        */
        return null;
    }

    @Override
    public String deleteByUUid(String uuid) {
        return null;
    }

    @Override
    public String update(Lostfound lostfound) {
        //System.out.println("业务层：：：实现更新记录");
        //System.out.println(lostfound);
        try{
            if(lostfound.getDes()==null||lostfound.getDes().length()==0||lostfound.getTime()==null)
            {
                return "缺少参数";
            }
            if(lostfound.getLstatic()==null||lostfound.getLstatic().length()==0||lostfound.getUdept()==null||lostfound.getUdept().length()==0)
            {
                return "缺少参数";
            }
            int count = getCountById(lostfound.getId());
            //System.out.println("count ;" +count);
            if(count==0){
                int a  = 10/0 ;
            }
            Lostfound lostfound1 = select(lostfound.getId());
            //System.out.println(lostfound1);
            System.out.println(lostfound1+" sdfsdf "+lostfound);
            if(lostfound1.equals(lostfound))
                return "数据没有发生变化重新输入";
            if(lostfound.getLflag()!=null){
                lostfound1.setLflag(lostfound.getLflag());
            }
            if(lostfound.getImage()!=null){
                lostfound1.setImage(lostfound.getImage());
            }
            if(lostfound.getDes()!=null){
                lostfound1.setDes(lostfound.getDes());
            }
            if(lostfound.getLstatic()!=null){
                lostfound1.setLstatic(lostfound.getLstatic());
            }
            if(lostfound.getUid()!=null){
                lostfound1.setUid(lostfound.getUid());
            }
            if(lostfound.getTime()!=null){
                lostfound1.setTime(lostfound.getTime());
            }
            lostfoundMapper.updateByPrimaryKey(lostfound1);
            return "数据更新成功";
        }catch(DataIntegrityViolationException dataIntegrityViolationException){
            return "人员账号不存在";
        }catch(Exception e){
            return "更新出错";
        }
    }

    @Override
    public Lostfound selectByUUid(String uuid) {
        return null;
    }

    @Override
    public Lostfound select(Integer id) {
        try{
            Lostfound lostfound = lostfoundMapper.selectByPrimaryKey(id);
            if(lostfound==null){
                return null;
            }
            return lostfound;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Lostfound selectByUsername(String username) {
        return null;
    }

    @Override
    public List<Lostfound> findAll() {
        return null;
    }

    @Override
    public int getCountByUUid(String uuid) {
        return 0;
    }

    @Override
    public int getCountById(Integer id) {
        return lostfoundMapper.getCountById(id);
    }

    @Override
    public List<Lostfound> getAllLost(String lflag,String udept) {
        //System.out.println("业务层：查看所有的失物信息----失主");
        List<Lostfound> allLost = lostfoundMapper.getAllLost(lflag,udept);
        return allLost;
    }

    @Override
    public List<Lostfound> getAllFound(String lflag,String udept) {
        //System.out.println("业务层：查看所有的得物信息----得主");
        List<Lostfound> allFound = lostfoundMapper.getAllFound(lflag,udept);
        return allFound;
    }

    @Override
    public List<Lostfound> getAllThing(String uid) {
        //System.out.println("业务层：查看所有的得物信息----得主");
        List<Lostfound> allThing = lostfoundMapper.getAllThing(uid);
        return allThing;
    }
}
