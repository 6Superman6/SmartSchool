package Demo.controller;

import Demo.model.Record;
import Demo.model.Trade;
import Demo.service.TradeService;
import Demo.utils.ImaTool;
import Demo.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping(path = "/insertTo",method = RequestMethod.POST)
    public @ResponseBody ServerResponse insertTo(@RequestParam("tdes") String tdes,@RequestParam("tuid") String tuid,
            @RequestParam("tprice") double tprice,@RequestParam("udept") String udept, HttpServletRequest request, MultipartFile image) {
        Trade trade =new Trade();
        trade.setTdes(tdes);
        trade.setTuid(tuid);
        trade.setTprice(tprice);
        trade.setUdept(udept);
        String filename = ImaTool.Imagetool(request,image);
        if(filename!=null)
        {
            trade.setImage("localhost/uploads/"+filename);
        }
        System.out.println(trade);
        if(trade==null)
        {
            return ServerResponse.createByError("添加失败");    //添加失败
        }
        String message = tradeService.insert(trade);
        if(message.equals("成功添加一条二手物品信息"))
        {
            return ServerResponse.createBySuccessMsg("添加成功");
        }
        if(message.equals("该数据已存在"))
        {
            return ServerResponse.createByErrorMsg2("该数据已存在");
        }
        if(message.equals("卖家账号不存在"))
        {
            return ServerResponse.createByErrorMsg3("卖家账号不存在");
        }
        if(message.equals("缺少参数"))
        {
            return ServerResponse.createByErrorMsg3("缺少参数");
        }
        return ServerResponse.createByError("添加失败");    //添加失败  -1
    }

    @RequestMapping(path = "/updateTo",method = RequestMethod.POST)
    public @ResponseBody ServerResponse updateTo(@RequestParam("tid") int tid,@RequestParam("tdes") String tdes,
                                                 @RequestParam("tprice") double tprice,@RequestParam("udept") String udept, HttpServletRequest request, MultipartFile image) {
        Trade trade =new Trade();
        trade.setTid(tid);
        trade.setTdes(tdes);
        trade.setTprice(tprice);
        trade.setUdept(udept);
        String filename = ImaTool.Imagetool(request,image);
        if(filename!=null)
        {
            trade.setImage("localhost/uploads/"+filename);
        }
        if(trade==null)
        {
            return ServerResponse.createByError("修改失败");    //修改失败
        }
        String message = tradeService.update(trade);
        if(message.equals("更新成功"))
        {
            return ServerResponse.createBySuccessMsg("修改成功");
        }
        if(message.equals("新数据和原来的数据一样，请重新输入数据"))
        {
            return ServerResponse.createByErrorMsg2("请输入新的物品信息");
        }
        if(message.equals("缺少参数"))
        {
            return ServerResponse.createByErrorMsg3("缺少参数");
        }
        return ServerResponse.createByError("修改失败");   //更新失败   -1
    }

    @RequestMapping(path = "/deleteTo",method = RequestMethod.POST)
    public @ResponseBody ServerResponse deleteTo(@Valid Trade trade)
    {
        if(trade==null)
        {
            return ServerResponse.createByError("删除失败");    //删除失败   -1
        }
        String message = tradeService.delete(trade.getTid());
        if(message.equals("删除成功"))
        {
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByError("删除失败");    //删除失败  -1
    }

    /**
     * 查看所有二手物品信息------所有用户
     * @return
     */
    @RequestMapping(value = "/rmess",method = RequestMethod.POST)
    public @ResponseBody ServerResponse rmess(@RequestParam("udept") String udept)
    {
        List<Trade> list = tradeService.findAll(udept);
        if(!list.isEmpty())
        {
            return ServerResponse.createBySuccess("查询成功，所有二手物品信息如下",list);
        }
        return ServerResponse.createByError("无二手交易信息") ;   //  -1
    }

    /**
     * 查看自己发布得二手物品信息------卖家
     * @param trade
     * @return
     */
    @RequestMapping(value = "/rmessmy",method = RequestMethod.POST)
    public @ResponseBody ServerResponse rmessmy(@Valid Trade trade)
    {
        if(trade==null)
        {
            return ServerResponse.createByError("无二手交易信息");
        }
        List<Trade> list = tradeService.getMessageMJ(trade.getTuid());
        if(!list.isEmpty())
        {
            return ServerResponse.createBySuccess("查询成功，所有二手交易信息如下",list);
        }
        return ServerResponse.createByError("无二手交易信息");  //  -1
    }
}
