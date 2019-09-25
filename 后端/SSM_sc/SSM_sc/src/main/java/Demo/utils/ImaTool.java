package Demo.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImaTool {

    public static String Imagetool(HttpServletRequest request, MultipartFile image)
    {
        try {
        // 使用fileupload组件完成文件上传
        // 1. 指定文件上传保存的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        if(image==null)
        {
            return null;
        }
        // 判断该路径是否存在
        File file = new File(path);
        if (!file.exists() || !file.isDirectory()){
            file.mkdirs();
        }
        // 打印一下文件保存的路径

        // 说明上传文件项
        // 2. 获取上传文件的名称
        String filename = image.getOriginalFilename();
        if(filename==null||filename.length()==0)
        {
            return null;
        }
        // 把文件的名称设置唯一值，uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;

        // 3.上传文件
            image.transferTo(new File(path,filename));
            return filename;
        } catch (IOException e) {
           return null;
        }
    }

}
