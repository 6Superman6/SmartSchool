package Demo.service.impl;

import Demo.model.Trade;
import Demo.service.TradeService;
import Demo.service.base.BaseServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TradeServiceImpl extends BaseServiceImpl<Trade> implements TradeService {
    @Override
    public int getCountByTuid(String tuid) {
        return tradeMapper.getCountByTuid(tuid);
    }

    @Override
    public List<Trade> getMessageMJ(String tuid) {
        try
        {
            List<Trade> list = tradeMapper.getMessageMJ(tuid);
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
    public String insert(Trade trade) {

        try
        {
            if(trade.getTuid()==null||trade.getTuid().length()==0||trade.getTdes()==null||trade.getTdes().length()==0)
            {
                return "缺少参数";
            }
            if(trade.getTprice()==null||trade.getImage()==null||trade.getImage().length()==0||trade.getUdept()==null||trade.getUdept().length()==0)
            {
                return "缺少参数";
            }
            tradeMapper.insert(trade);
            return "成功添加一条二手物品信息";
        }catch (DataIntegrityViolationException dataIntegrityViolationException)
        {
            return "卖家账号不存在";
        }
        catch (Exception e)
        {
            return "添加失败";
        }
    }

    @Override
    public String delete(Integer tid) {
        try
        {
            int count = getCountById(tid);
            if(count==0)
            {
                int a = 10/0;
            }
            tradeMapper.deleteByPrimaryKey(tid);
            return "删除成功";
        }catch (Exception e)
        {
            return "删除错误，该交易编号不存在";
        }
    }

    @Override
    public String deleteByUUid(String uuid) {
        return null;
    }

    @Override
    public String update(Trade trade) {
        try
        {
            if(trade.getTdes()==null||trade.getTdes().length()==0)
            {
                return "缺少参数";
            }
            if(trade.getTprice()==null||trade.getUdept()==null||trade.getUdept().length()==0)
            {
                return "缺少参数";
            }
            int count = getCountById(trade.getTid());
            if(count==0)
            {
                int a = 10/0;
            }
            Trade trade1 = select(trade.getTid());
            if(trade.getTdes().equals(trade1.getTdes()))
            {
                if(trade.getTprice().equals(trade1.getTprice())&&trade.getImage().equals(trade1.getImage()))
                {
                    return "新数据和原来的数据一样，请重新输入数据";
                }
            }
            if(trade.getTdes()!=null)
            {
                trade1.setTdes(trade.getTdes());
            }
            if(trade.getTprice()!=null)
            {
                trade1.setTprice(trade.getTprice());
            }
            if(trade.getImage()!=null)
            {
                trade1.setImage(trade.getImage());
            }
            if(trade.getUdept()!=null)
            {
                trade1.setUdept(trade.getUdept());
            }
            tradeMapper.updateByPrimaryKey(trade1);
            return "更新成功";

        }catch (Exception e)
        {
            return "更新出错";
        }
    }

    @Override
    public Trade selectByUUid(String uuid) {
        return null;
    }

    @Override
    public Trade select(Integer tid) {
        try
        {
            Trade trade = tradeMapper.selectByPrimaryKey(tid);
            if(trade==null)
            {
                int a=10/0;
            }
            return trade;
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public Trade selectByUsername(String username) {
        return null;
    }

    @Override
    public List<Trade> findAll() {
        return null;
    }

    @Override
    public List<Trade> findAll(String udept) {
        try
        {
            List<Trade> list = tradeMapper.findAll(udept);
            if(list==null)
            {
                int a=10/0;
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
    public int getCountById(Integer tid) {
        return tradeMapper.getCountById(tid);
    }
}
