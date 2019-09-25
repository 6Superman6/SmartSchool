package Demo.service;

import Demo.model.Record;
import Demo.service.base.IBaseService;

import java.util.List;

public interface RecordService extends IBaseService<Record> {

    public int getCountByRuid(String ruid);

    public int getCountByWid(String wid);

    public List<Record> getMessage(String udept);

    public List<Record> getRMessage(String ruid);   //报备人员查看自己发布的报备信息

    public List<Record> getWYMessage(String wid);   //维修人员查看已维修的报备信息

    public List<Record> getWWMessage(String udept);   //维修人员查看待维修的报备信息

}
