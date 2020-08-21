package cn.edu.hziee.peisp.contoller;

import cn.edu.hziee.peisp.entity.Illegal;
import cn.edu.hziee.peisp.service.IllegalService;
import cn.edu.hziee.peisp.utils.FileUtil;
import cn.edu.hziee.peisp.utils.ServerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class IllegalController extends HttpServlet {
    @Autowired
    IllegalService illegalService;
    //文件保存路径
//    private static final String FILE_PATH_PREFIX = "D:\\CWD Project\\peisp\\src\\main\\resources\\static";
    private static final String FILE_PATH_PREFIX = "/root/tomcat";
    //读取路径
    private static final String REAL_PATH = "/uploadFile/";

    private static final String UPLOAD_IMAGE_SUCCESS = "1";

    @RequestMapping("/CameraPage")
    public String cameraPage(Model model){
        List<Illegal> illegals = illegalService.getMax5Illegal();
        model.addAttribute("illegals",illegals);





        return "CameraPage";
    }
    @RequestMapping("/History")
    public String history(Model model){
        List<Illegal> illegals = illegalService.getMax5Illegal();
        model.addAttribute("illegals",illegals);
        return "monitor_history";
    }

    @RequestMapping("/uploadImg")
    public void uploadImg(@RequestParam("imgFile")MultipartFile multipartFile,
                          @RequestParam("workerName")String workerName,
                          @RequestParam("reason")String reason,
                          @RequestParam("workerId")String workerId){
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Illegal illegal = new Illegal();
        illegal.setTime(time);
        illegal.setReason(reason);
        if (workerId==null||workerId.isEmpty()){
        }else {
            illegal.setWorkerId(workerId);
            illegal.setWorkerName(workerName);
        }
        illegal.setImgDir(upLoadImg(multipartFile,workerId,time));
        System.out.println("成功上传图片至:"+FILE_PATH_PREFIX+REAL_PATH);
        if (illegalService.insertSelective(illegal)==1){
            ServerManager.broadCast(UPLOAD_IMAGE_SUCCESS);
        }
    }

    public String upLoadImg(MultipartFile multipartFile,String workerId,Timestamp time){
        String fileName;
        if (workerId==null||workerId.isEmpty())
            fileName = "Unknow "+time.toString().replace(':','-') +multipartFile.getOriginalFilename();
        else{
            fileName = workerId + " " +time.toString().replace(':','-')+multipartFile.getOriginalFilename();;
        }
        try {
            FileUtil.uploadFile(multipartFile.getBytes(), FILE_PATH_PREFIX+REAL_PATH, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return REAL_PATH+fileName;
    }

}
