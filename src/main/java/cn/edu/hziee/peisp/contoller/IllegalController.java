package cn.edu.hziee.peisp.contoller;

import cn.edu.hziee.peisp.entity.Illegal;
import cn.edu.hziee.peisp.service.IllegalService;
import cn.edu.hziee.peisp.utils.FileUtil;
import cn.edu.hziee.peisp.utils.MyWebSocket;
import cn.edu.hziee.peisp.utils.ServerManager;
import org.apache.catalina.manager.ManagerServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.websocket.Session;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class IllegalController extends HttpServlet {
    @Autowired
    IllegalService illegalService;
    //文件保存路径
    private static final String FILE_PATH_PREFIX = "\\usr\\local";
    //读取路径
    private static final String REAL_PATH = "\\uploadFile\\";

    private static final String UPLOAD_IMAGE_SUCCESS = "1";

    @RequestMapping("/CameraPage")
    public String cameraPage(Model model){
        List<Illegal> illegals = illegalService.getMax5Illegal();
        model.addAttribute("illegals",illegals);
        return "/CameraPage";
    }
    @RequestMapping("/History")
    public String history(Model model){
        List<Illegal> illegals = illegalService.getMax5Illegal();
        model.addAttribute("illegals",illegals);
        return "/monitor_history";
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
        if (workerId==null)
            fileName = "Unknow "+time.toString().replace(':','-') +multipartFile.getOriginalFilename();
        else{
            fileName = workerId + " " +time.toString().replace(':','-')+multipartFile.getOriginalFilename();;
        }
        try {
            FileUtil.uploadFile(multipartFile.getBytes(), FILE_PATH_PREFIX+REAL_PATH, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return FILE_PATH_PREFIX+REAL_PATH+fileName;
    }

}
