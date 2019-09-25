package Demo.controller;

import Demo.model.Test;
import Demo.service.TestService;
import Demo.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/test")
public class TestController {
    @Autowired
    private TestService testService;
//查看考试信息
    @RequestMapping(path = "/checktest",method = RequestMethod.POST)
    public @ResponseBody ServerResponse checktest(){
        List<Test> list=testService.findAll();
        if(!list.isEmpty())
        {
            return ServerResponse.createBySuccess("查询考试报名入口成功，信息如下",list);
        }
        return ServerResponse.createByError("无考试报名入口") ;   //  -1
    }
// 按院校查看考试信息
    @RequestMapping(path = "/checkcollege",method = RequestMethod.POST)
    public @ResponseBody ServerResponse checkcollege(@Valid Test test){
        List<Test> list=testService.selectByCollege(test.getTcollege());
        if(!list.isEmpty())
        {
            return ServerResponse.createBySuccess("查询此院校考试报名入口成功，信息如下",list);
        }
        return ServerResponse.createByError("此学院下无考试报名入口") ;   //  -1
    }
// 管理员插入考试信息
    @RequestMapping(path = "/insertTest",method = RequestMethod.POST)
    public  @ResponseBody ServerResponse insertTest(@Valid Test test){
        String temp=testService.insert(test);
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
        else {
            return ServerResponse.createByErrorMsg2("该考试已经存在");//-2
        }

    }
// 管理员更改考试信息
    @RequestMapping(path = "/updateTest",method = RequestMethod.POST)
    public  @ResponseBody ServerResponse updateTest(@Valid Test test){
        String temp=testService.update(test);
        if(temp.equals("修改成功"))
        {
            return ServerResponse.createBySuccessMsg("修改成功"); //0
        }
        else if(temp.equals("修改失败"))
        {
            return ServerResponse.createByError("修改失败");  //-1
        }
        else if(temp.equals("缺少参数"))
        {
            return ServerResponse.createByErrorMsg3("缺少参数");//-3
        }
        else{
            return ServerResponse.createByErrorMsg2("修改失败,该考试已经存在");//-2
        }
    }
//管理员删除考试信息
@RequestMapping(path = "/deleteTest",method = RequestMethod.POST)
    public  @ResponseBody ServerResponse deleteTest(@Valid Integer tid){
        String temp=testService.delete(tid);
        if(temp.equals("删除成功"))
        {
            return ServerResponse.createBySuccessMsg("删除成功"); //0
        }
        else
        {
            return ServerResponse.createByError("删除失败");  //-1
        }
    }

}
