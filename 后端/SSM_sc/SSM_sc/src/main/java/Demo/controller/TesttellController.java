package Demo.controller;

import Demo.model.Testtell;
import Demo.service.TesttellService;
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
@RequestMapping(path = "/testtell")
public class TesttellController {
    @Autowired
    private TesttellService testtellService;

//    查看所有的通知
    @RequestMapping(path = "/checkTell",method = RequestMethod.POST)
    public @ResponseBody ServerResponse checkTell(@RequestParam("udept") String udept){
        try{
            List<Testtell> list=testtellService.findAll(udept);
            if(list.isEmpty())
            {
                return ServerResponse.createByError("无考试报名通知") ;//-1
            }
            return ServerResponse.createBySuccess("查询考试通知成功，信息如下",list);//0
        }
        catch (Exception e)
        {
            return null;
        }

    }
//    管理员添加通知
    @RequestMapping(path = "/insertTell",method = RequestMethod.POST)
    public @ResponseBody ServerResponse insertTell(@RequestParam("testname") String testname, @RequestParam("testdate") Date testdate,
                                                   @RequestParam("testarea") String testarea, @RequestParam("udept") String udept,
                                                   HttpServletRequest request,MultipartFile image){
        Testtell testtell = new Testtell();
        testtell.setTestname(testname);
        testtell.setTestdate(testdate);
        testtell.setTestarea(testarea);
        testtell.setUdept(udept);
        String filename = ImaTool.Imagetool(request,image);
        if(filename!=null)
        {
            testtell.setImage("47.94.10.228/uploads/"+filename);
        }
        String temp=testtellService.insert(testtell);
        System.out.println("testtell : "+testtell+"image : "+image);
        if(temp.equals("添加成功"))
        {
            return ServerResponse.createBySuccessMsg("添加成功"); //0
        }
        else if(temp.equals("添加失败"))
        {
            return ServerResponse.createByError("添加失败");  //-1
        }
        else if(temp.equals("缺少参数"))
        {
            return ServerResponse.createByErrorMsg3("缺少参数");//-3
        }
        else
        {
            return ServerResponse.createByErrorMsg2("该考试已经存在");//-2
        }

    }
//    管理员修改通知
    @RequestMapping(path = "/updateTell",method = RequestMethod.POST)
    public @ResponseBody ServerResponse updateTell(@RequestParam("testid") int testid,@RequestParam("testname") String testname, @RequestParam("testdate") Date testdate,
                                                   @RequestParam("testarea") String testarea, @RequestParam("udept") String udept,
                                                   HttpServletRequest request, MultipartFile image){
        Testtell testtell = new Testtell();
        testtell.setTestid(testid);
        testtell.setTestname(testname);
        testtell.setTestdate(testdate);
        testtell.setTestarea(testarea);
        testtell.setUdept(udept);
        String filename = ImaTool.Imagetool(request,image);
        if(filename!=null)
        {
            testtell.setImage("47.94.10.228/uploads/"+filename);
        }
        String temp=testtellService.update(testtell);
        if(temp.equals("修改成功"))
        {
            return ServerResponse.createBySuccessMsg("修改成功"); //0
        }
        else if(temp.equals("修改失败"))
        {
            return ServerResponse.createByError("修改失败");  //-1
        }
        else if(temp.equals("该通知已经存在")){
            return ServerResponse.createByErrorMsg2("修改失败,该考试已经存在");//-2
        }
        else{
            return ServerResponse.createByErrorMsg3("缺少参数或没有相关的通知编号");//-3
        }
    }
//    管理员删除通知
    @RequestMapping(path = "/deleteTell",method = RequestMethod.POST)
    public  @ResponseBody ServerResponse  deleteTell(@Valid Integer testid){
        String temp=testtellService.delete(testid);
        if(temp.equals("删除成功"))
        {
            return ServerResponse.createBySuccessMsg("删除成功"); //0
        }
        else
        {
            return ServerResponse.createByError("删除失败，该通知编号不存在"); //-1
        }
    }
}
