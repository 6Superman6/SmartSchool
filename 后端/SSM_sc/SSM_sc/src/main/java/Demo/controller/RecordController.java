package Demo.controller;

import Demo.model.Record;
import Demo.service.RecordService;
import Demo.utils.ImaTool;
import Demo.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping(path = "/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping(path = "/insertTo",method = RequestMethod.POST)
    public @ResponseBody ServerResponse insertTo(@RequestParam("ruid") String ruid, @RequestParam("rdate")Date rdate,
            @RequestParam("radr") String radr,@RequestParam("rtype") String rtype,@RequestParam("wstatic") String wstatic,
            @RequestParam("rdes") String rdes,@RequestParam("udept") String udept, HttpServletRequest request, MultipartFile image) {
        Record record =new Record();
        record.setRuid(ruid);
        record.setRdate(rdate);
        record.setRadr(radr);
        record.setRtype(rtype);
        record.setWstatic(wstatic);
        record.setRdes(rdes);
        record.setUdept(udept);
        String filename = ImaTool.Imagetool(request,image);
        if(filename!=null)
        {
            record.setImage("localhost/uploads/"+filename);
        }
        if(record==null)
        {
            return ServerResponse.createByError("报备失败，数据有误");    //报备失败，数据有误  -1
        }
        String message = recordService.insert(record);
        if(message.equals("报备数据添加成功"))
        {
            return ServerResponse.createBySuccessMsg("报备数据添加成功");  // 0
        }
        if(message.equals("报备人员账号不存在"))
        {
            return ServerResponse.createByErrorMsg2("报备人员账号不存在");  // -2
        }
        if(message.equals("该数据已存在"))
        {
            return ServerResponse.createByErrorMsg3("该数据已存在");   // -3
        }
        if(message.equals("缺少参数"))
        {
            return ServerResponse.createByErrorMsg2("缺少参数");
        }
        return ServerResponse.createByError("报备失败，数据有误");    //报备失败，数据有误  -1
    }

    @RequestMapping(path = "/updateTo",method = RequestMethod.POST)
    public @ResponseBody ServerResponse updateTo(@RequestParam("rid") int rid,@RequestParam("radr") String radr,@RequestParam("rtype") String rtype,
                                                 @RequestParam("rdes") String rdes,@RequestParam("udept") String udept ,HttpServletRequest request, MultipartFile image) {
//        System.out.println(record);
        Record record =new Record();
        record.setRid(rid);
        record.setRadr(radr);
        record.setRtype(rtype);
        record.setRdes(rdes);
        record.setUdept(udept);
        String filename = ImaTool.Imagetool(request,image);
        if(filename!=null)
        {
            record.setImage("localhost/uploads/"+filename);
        }
        if(record==null)
        {
            return ServerResponse.createByError("更新出错");    //报备失败，数据有误  -1
        }
        String message = recordService.update(record);
        if(message.equals("更新成功"))
        {
            return ServerResponse.createBySuccessMsg("更新成功");   // 0
        }
        if(message.equals("新数据和原来的数据一样，请重新输入数据"))
        {
            return ServerResponse.createByErrorMsg2("新数据和原来的数据一样，请重新输入数据");  // -2
        }
        return ServerResponse.createByError("更新出错");   //更新出错
    }

    @RequestMapping(path = "/deleteTo",method = RequestMethod.POST)
    public @ResponseBody ServerResponse deleteTo(@Valid Record record)
    {
        if(record==null)
        {
            return ServerResponse.createByError("删除失败，报备编号不存在");    //删除失败，报备编号不存在  -1
        }
        String message = recordService.delete(record.getRid());
        if(message.equals("删除成功"))
        {
            return ServerResponse.createBySuccessMsg("删除成功");   // 0
        }
        return ServerResponse.createByError("删除失败，报备编号不存在");   //删除错误，该数据不存在
    }

    /**
     * 修改报备表中的故障维修信息（维修）---维修人员
     * @param record
     * @return
     */
    @RequestMapping(path = "/delByW",method = RequestMethod.POST)
    public @ResponseBody ServerResponse deleteByW(@Valid Record record)
    {
        if(record==null)
        {
            return ServerResponse.createByError("维修失败");    //维修失败  -1
        }
        String message = recordService.update(record);
        if(message.equals("更新成功"))   //维修成功
        {
            return ServerResponse.createBySuccessMsg("维修成功");   // 0
        }
        if(message.equals("新数据和原来的数据一样，请重新输入数据"))
        {
            return ServerResponse.createByErrorMsg2("请输入新的维修信息");  // -2
        }
        if(message.equals("维修人员账号不存在"))
        {
            return ServerResponse.createByErrorMsg3("维修人员账号不存在");   // -3
        }
        return ServerResponse.createByError("维修失败");    //维修失败  -1
    }

    /**
     * 维修人员查看故障信息
     * @return
     */
    @RequestMapping(path = "/rmess",method = RequestMethod.POST)
    public @ResponseBody ServerResponse rmess(@RequestParam("udept") String udept)
    {
        List<Record> list = recordService.getMessage(udept);
        if(!list.isEmpty())
        {
            return ServerResponse.createBySuccess("查询故障信息成功，信息如下",list);
        }
        return ServerResponse.createByError("无设备故障信息") ;   //  -1
    }

    /**
     * 报备人员查看自己发布的报备信息
     * @param record
     * @return
     */
    @RequestMapping(path = "/rmessmy",method = RequestMethod.POST)
    public @ResponseBody ServerResponse rmessmy(@Valid Record record)
    {
        if(record==null)
        {
            return ServerResponse.createByError("无设备故障信息");    //无设备故障信息  -1
        }
        List<Record> list = recordService.getRMessage(record.getRuid());
        if(!list.isEmpty())
        {
            return ServerResponse.createBySuccess("查询报备信息成功，信息如下",list);
        }
        return ServerResponse.createByError("无设备故障信息") ;   //  -1
    }

    /**
     * 维修人员查看已维修的报备信息
     * @param record
     * @return
     */
    @RequestMapping(path = "/wymessmy",method = RequestMethod.POST)
    public @ResponseBody ServerResponse wymessmy(@Valid Record record)
    {
        if(record==null)
        {
            return ServerResponse.createByError("无已维修信息");    //无已维修信息  -1
        }
        List<Record> list = recordService.getWYMessage(record.getWid());
        if(!list.isEmpty())
        {
            return ServerResponse.createBySuccess("查询已维修信息成功，信息如下",list);
        }
        return ServerResponse.createByError("无已维修信息") ;   //  -1
    }

    /**
     * 维修人员查看待维修的报备信息
     * @param
     * @return
     */
    @RequestMapping(path = "/wwmessmy",method = RequestMethod.POST)
    public @ResponseBody ServerResponse wwmessmy(@RequestParam("udept") String udept)
    {
        List<Record> list = recordService.getWWMessage(udept);
        if(!list.isEmpty())
        {
            return ServerResponse.createBySuccess("查询待维修信息成功，信息如下",list);
        }
        return ServerResponse.createByError("无待维修信息");   //  -1
    }

    @RequestMapping(path = "/inter",method = RequestMethod.POST)
    public @ResponseBody ServerResponse inter()
    {
        return ServerResponse.createByError("无待维修信息") ;
    }






}
