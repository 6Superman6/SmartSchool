package Demo.service.base;

import Demo.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.spi.WebServiceFeatureAnnotation;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    //统一管理mapper

    @Autowired
    public LostfoundMapper lostfoundMapper;

    @Autowired
    public RecordMapper recordMapper;

    @Autowired
    public TestMapper testMapper;

    @Autowired
    public TesttellMapper testtellMapper;

    @Autowired
    public TradeMapper tradeMapper;

    @Autowired
    public UserMapper userMapper;

}
