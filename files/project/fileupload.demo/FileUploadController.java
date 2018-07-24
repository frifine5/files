package com.gomain.makeseal.demo;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * This is a demo for file upload in restful
 * WangChengYu
 */
@RestController
public class FileUploadController {
    //处理文件上传
    @RequestMapping(value = "/testuploading", method = RequestMethod.POST)
    public @ResponseBody
    Object uploadImg(@RequestParam("file") MultipartFile file,
                     HttpServletRequest request) {
        System.out.println(request.getCharacterEncoding());
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
        String filePath = "temp";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回json
        return "upload success";
    }

}

class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}

