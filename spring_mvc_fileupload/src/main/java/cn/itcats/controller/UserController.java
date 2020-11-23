package cn.itcats.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/fileUpLoad1")
    public String fileUpLoad1(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        File file=new File(realPath);

        if(!file.exists()){
            file.mkdirs();
        }

        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            //判断是不是表单，如果不是为上传文件项
            if(fileItem.isFormField()){

            }else{
                String fileItemname = fileItem.getName();
                String replace = UUID.randomUUID().toString().replace("-", "");
                fileItemname=replace+"_" +fileItemname;
                fileItem.write(new File(realPath,fileItemname));
                fileItem.delete();
            }
        }
        System.out.println(realPath);
        return "success";
    }

    /**
     * spring mvc 上传
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileUpLoad2")
    public String fileUpLoad2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("spring文件上传");
        //获得服务器容器路径
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        File file=new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //获取文件名
        String fileItemname = upload.getOriginalFilename();
        String replace = UUID.randomUUID().toString().replace("-", "");
        fileItemname=replace+"_" +fileItemname;
        //上传文件
        upload.transferTo(new File(realPath,fileItemname));
        return "success";
    }

    /**
     * spring mvc跨服务器上传
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileUpLoad3")
    //upload要与页面的name一样name="upload"
    public String fileUpLoad3( MultipartFile upload) throws Exception {
        System.out.println("垮服务器文件上传");
        //服务器地址
        String path="http://localhost:8081/picServer_war/uploads/";
        //获取文件名
        String fileItemname = upload.getOriginalFilename();
        String replace = UUID.randomUUID().toString().replace("-", "");
        fileItemname=replace+"_" +fileItemname;
        //创建客户端对象
        Client client=Client.create();

        //和图片服务器连接
        WebResource webResource = client.resource(path + fileItemname);
        //上传文件
        webResource.put(upload.getBytes());

        return "success";


    }
}
