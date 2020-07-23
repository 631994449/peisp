package cn.edu.hziee.peisp.contoller;

import cn.edu.hziee.peisp.entity.Illegal;
import cn.edu.hziee.peisp.service.IllegalService;
import cn.edu.hziee.peisp.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Controller
public class IllegalController {
    @Autowired
    IllegalService illegalService;
    public static final String FILE_PATH = "D:\\2019-2020-2\\uploadImg\\";
    @RequestMapping("/uploadImg")
    public void uploadImg(@RequestParam("imgFile")MultipartFile multipartFile,
                          @RequestParam("workerName")String workerName,
                          @RequestParam("reason")String reason,
                          @RequestParam("workerId")String workerId){
        String fileName;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Illegal illegal = new Illegal();
        illegal.setTime(time);
        illegal.setReason(reason);
        System.out.println(time.toString());
        if (workerId==null)
            fileName = "Unknow "+time.toString().replace(':','-') +multipartFile.getOriginalFilename();
        else{
            fileName = workerId + " " +time.toString().replace(':','-')+multipartFile.getOriginalFilename();;
            illegal.setWorkerId(workerId);
            illegal.setWorkerName(workerName);
        }
            illegal.setImgDir(FILE_PATH+fileName);
        try {
            FileUtil.uploadFile(multipartFile.getBytes(), FILE_PATH, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("成功上传图片至:"+FILE_PATH);
        illegalService.insertSelective(illegal);
    }
}
