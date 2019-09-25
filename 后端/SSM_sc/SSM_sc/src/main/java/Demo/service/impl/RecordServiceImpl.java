package Demo.service.impl;

import Demo.model.Record;
import Demo.service.RecordService;
import Demo.service.base.BaseServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecordServiceImpl extends BaseServiceImpl<Record> implements RecordService {


    @Override
    public String insert(Record record) {

        try
        {
            if(record.getRuid()==null||record.getRuid().length()==0||record.getRdate()==null||record.getRdes()==null||record.getRdes().length()==0)
            {
                return "缺少参数";
            }
            if(record.getRadr()==null||record.getRadr().length()==0||record.getWstatic()==null||record.getWstatic().length()==0)
            {
                return "缺少参数";
            }
            if(record.getImage()==null||record.getImage().length()==0)
            {
                return "缺少参数";
            }
            List<Record> list = findAll();
            if(list!=null)
            {
                for(Record r : list)
                {
                    if(r.getRuid().equals(record.getRuid())&&r.getRdes().equals(record.getRdes()))
                    {
                        if(r.getRadr().equals(record.getRadr())&&r.getRtype().equals(record.getRtype()))
                        {
                            if(r.getImage().equals(record.getImage()))
                            {
                                if(r.getWid()==null&&record.getWid()==null)
                                {
                                    if(r.getWdate()==null&&record.getWdate()==null)
                                    {
                                        if(r.getWstatic().equals("待维修")&&record.getWstatic().equals("待维修"))
                                        {
                                            return "该数据已存在";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            recordMapper.insert(record);
            return "报备数据添加成功";
        }catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            return "报备人员账号不存在";
        }
        catch (Exception e)
        {
            return "报备失败，数据有误";
        }

    }

    @Override
    public String delete(Integer rid) {

        try
        {
            int count = getCountById(rid);
            if(count==0)
            {
                int a = 10/0;
            }
            recordMapper.deleteByPrimaryKey(rid);
            return "删除成功";
        }catch (Exception e)
        {
            return "删除错误，该数据不存在";
        }
    }

    @Override
    public String deleteByUUid(String uuid) {
        return null;
    }

    @Override
    public String update(Record record) {

        try
        {
            int count = getCountById(record.getRid());
            if(count==0)
            {
                int a = 10/0;
            }
            Record record1 = select(record.getRid());
            if(record.equals(record1))
            {
                return "新数据和原来的数据一样，请重新输入数据";
            }
            if(record.getRadr()!=null)
            {
                record1.setRadr(record.getRadr());
            }
            if(record.getRtype()!=null)
            {
                record1.setRtype(record.getRtype());
            }
            if(record.getRdes()!=null)
            {
                record1.setRdes(record.getRdes());
            }
            if(record.getImage()!=null)
            {
                record1.setImage(record.getImage());
            }
            if(record.getWid()!=null)
            {
                record1.setWid(record.getWid());
            }
            if(record.getWstatic()!=null)
            {
                record1.setWstatic(record.getWstatic());
            }
            if(record.getWdate()!=null)
            {
                record1.setWdate(record.getWdate());
            }
            if(record.getUdept()!=null)
            {
                record1.setUdept(record.getUdept());
            }
            recordMapper.updateByPrimaryKey(record1);
            return "更新成功";

        }catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            return "维修人员账号不存在";
        }
        catch (Exception e)
        {
            return "更新出错";
        }
    }

    @Override
    public Record selectByUUid(String uuid) {
        return null;
    }

    @Override
    public Record select(Integer rid) {
        try
        {
            Record record = recordMapper.selectByPrimaryKey(rid);
            if(record==null)
            {
                int a = 10/0;
            }
            return record;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Record selectByUsername(String username) {
        return null;
    }

    @Override
    public List<Record> findAll() {

        try
        {
            List<Record> list = recordMapper.findAll();
            if(list==null)
            {
                int a = 10/0;
            }
            return list;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public int getCountByUUid(String uuid) {
        return 0;
    }

    @Override
    public int getCountById(Integer rid) {
        return recordMapper.getCountById(rid);
    }

    public int getCountByRuid(String ruid)
    {
        return recordMapper.getCountByRuid(ruid);
    }

    public int getCountByWid(String wid)
    {
        return recordMapper.getCountByWid(wid);
    }

    @Override
    public List<Record> getMessage(String udept) {
        try
        {
            List<Record> list = recordMapper.getMessage(udept);
            if(list==null)
            {
                int a = 10/0;
            }
            return list;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override        //报备人员查看自己发布的报备信息
    public List<Record> getRMessage(String ruid) {
        try
        {
            List<Record> list = recordMapper.getRMessage(ruid);
            return list;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override       //维修人员查看已维修的报备信息
    public List<Record> getWYMessage(String wid) {
        try
        {
            String wstatic = "已维修";
            List<Record> list = recordMapper.getWYMessage(wid,wstatic);
            return list;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override       //维修人员查看待维修的报备信息
    public List<Record> getWWMessage(String udept) {
        try
        {
            String wstatic = "待维修";
            List<Record> list = recordMapper.getWWMessage(wstatic,udept);
            return list;
        }catch (Exception e)
        {
            return null;
        }
    }
}
