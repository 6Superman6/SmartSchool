package Demo.controller;

import Demo.model.Lostfound;
import Demo.service.LostFoundService;
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

@Controller
@RequestMapping("/lf")
public class LFController {

    @Autowired
    private LostFoundService lostFoundService;

    /*
    添加一条失物招领的信息---失主  功能1
     */
    @RequestMapping(path = "/testInsertTo",method = RequestMethod.POST)
    public @ResponseBody ServerResponse testInsertTo(@RequestParam("des") String des,@RequestParam("time") Date time,
            @RequestParam("uid") String uid,@RequestParam("lflag") String lflag,@RequestParam("lstatic") String lstatic
            ,@RequestParam("udept") String udept, HttpServletRequest request, MultipartFile image){
//        System.out.println("添加一条失物招领的信息。。。失主。。");
//        System.out.println(lostfound);
        Lostfound lostfound = new Lostfound();
        lostfound.setDes(des);
        lostfound.setTime(time);
        lostfound.setUid(uid);
        lostfound.setLflag(lflag);
        lostfound.setLstatic(lstatic);
        lostfound.setUdept(udept);
        String filename = ImaTool.Imagetool(request,image);
        if(filename!=null)
        {
            lostfound.setImage("47.94.10.228/uploads/"+filename);
        }
        if(lostfound==null)
            return ServerResponse.createByError("数据添加失败");//   -2

        String message = lostFoundService.insert(lostfound);
        if(message.equals("数据添加成功")){
            return ServerResponse.createBySuccessMsg("数据添加成功");
            //return 0;
        }else if(message.equals("人员账号不存在")){
            return ServerResponse.createByErrorMsg2("人员账号不存在");
            //return -2;
        }else if(message.equals("该数据已存在不要重复添加")){
            return ServerResponse.createByErrorMsg3("该数据已存在不要重复添加");
            //return -3;
        }
        else if(message.equals("缺少参数"))
        {
            return ServerResponse.createByErrorMsg2("缺少参数");
        }else{
            return ServerResponse.createByError("数据添加失败");
            //return -1;
        }
    }



    /*
 修改一条记录 根据id
  */
    @RequestMapping(path = "/testUpdateTo",method = RequestMethod.POST)
    public @ResponseBody ServerResponse testUpdateTo(@RequestParam("id") int id,@RequestParam("des") String des,@RequestParam("time") Date time,
                                                     @RequestParam("lstatic") String lstatic,@RequestParam("udept") String udept
            , HttpServletRequest request, MultipartFile image){
      //  System.out.println("修改一条记录 。。。。");
//        System.out.println(lostfound);
        Lostfound lostfound = new Lostfound();
        lostfound.setId(id);
        lostfound.setDes(des);
        lostfound.setTime(time);
        lostfound.setLstatic(lstatic);
        lostfound.setUdept(udept);
        String filename = ImaTool.Imagetool(request,image);
        if(filename!=null)
        {
            lostfound.setImage("47.94.10.228/uploads/"+filename);
        }
        if(lostfound==null){
            return ServerResponse.createByError("更新出错");    //报备失败，数据有误  -1
        }
        String message = lostFoundService.update(lostfound);
        if(message.equals("数据更新成功"))
        {
            return ServerResponse.createBySuccessMsg("数据更新成功");   // 0
        }
        if(message.equals("数据没有发生变化重新输入"))
        {
            return ServerResponse.createByErrorMsg3("数据没有发生变化重新输入");  // -3
        }
        if(message.equals("人员账号不存在")){
            return ServerResponse.createByErrorMsg2("人员账号不存在"); //-2
        }
        if(message.equals("缺少参数"))
        {
            return ServerResponse.createByErrorMsg2("缺少参数");
        }
        return ServerResponse.createByError("更新出错");   //更新出错 -1
    }

    /*
    删除一条记录 根据id
     */
    @RequestMapping("/testDeleteTo")
    public @ResponseBody ServerResponse testDeleteTo(@Valid String id){
      //  System.out.println("删除一条记录。。。byid");
        //System.out.println(id);
        int idd = Integer.parseInt(id);
        String mseeage = lostFoundService.delete(idd);
      //  System.out.println(mseeage);
        if(mseeage.equals("删除成功")){
            return ServerResponse.createBySuccessMsg("删除成功");
        }
        return ServerResponse.createByError("删除失败数据不存在");
    }



     /*
  查看所有失物招领的信息。。。失主。。 功能6
   */
    @RequestMapping(path = "/testAllLost",method = RequestMethod.POST)
    public @ResponseBody ServerResponse testAllLost(@RequestParam("lflag") String lflag,@RequestParam("udept") String udept){
      //  System.out.println("查看所有失物招领的信息。。。失主。。");
       // System.out.println(lflag);
        List<Lostfound> list = lostFoundService.getAllLost(lflag,udept);
     //   System.out.println(list);
        if(!list.isEmpty()) {
            return ServerResponse.createBySuccess("查询失物信息成功如下表", list);
        }
        return ServerResponse.createByError("无失物信息") ;   //  -1
    }

    /*
  查看所有得主招领的信息。。。得主。。 功能7
   */
    @RequestMapping(path = "/testAllFound",method = RequestMethod.POST)
    public @ResponseBody ServerResponse testAllFound(@RequestParam("lflag") String lflag,@RequestParam("udept") String udept){
       // System.out.println("查看所有捡到东西招领的信息。。。得主。。");
        //System.out.println(lflag);
        List<Lostfound> list = lostFoundService.getAllFound(lflag,udept);
        //System.out.println(list);
        //System.out.println(list.isEmpty());
        if(!list.isEmpty()) {
            return ServerResponse.createBySuccess("查询捡到信息成功如下表", list);
        }
        return ServerResponse.createByError("无捡到物品信息") ;   //  -1
    }

//
    @RequestMapping(path = "/testAllThing",method = RequestMethod.POST)
    public @ResponseBody ServerResponse testAllThing(@Valid String uid){
        // System.out.println("自己查看自己的所有信息");
        //System.out.println(lflag);
        List<Lostfound> list = lostFoundService.getAllThing(uid);
        if(!list.isEmpty()) {
            return ServerResponse.createBySuccess("查询自己信息成功如下表", list);
        }
        return ServerResponse.createByError("无发布信息信息") ;   //  -1
    }



}
