package Demo.service;

import Demo.model.Trade;
import Demo.service.base.IBaseService;

import java.util.List;

public interface TradeService extends IBaseService<Trade> {

    List<Trade> findAll(String udept);

    int getCountByTuid(String tuid);  //获取卖家的个数

    List<Trade> getMessageMJ(String tuid);    //查看自己发布得二手物品信息------卖家

}
