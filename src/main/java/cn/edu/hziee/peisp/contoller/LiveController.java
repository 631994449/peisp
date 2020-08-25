package cn.edu.hziee.peisp.contoller;

import cn.edu.hziee.peisp.entity.Illegal;
import cn.edu.hziee.peisp.model.Boxes;
import cn.edu.hziee.peisp.model.Result;
import cn.edu.hziee.peisp.service.IllegalService;
import cn.edu.hziee.peisp.service.WebSocketServer;
import cn.edu.hziee.peisp.utils.Base642ImgUtil;
import cn.edu.hziee.peisp.utils.ResultUtil;
import com.sun.corba.se.spi.activation.ServerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class LiveController {
    @Autowired
    IllegalService illegalService;

//    static String FILE_SAVE_PATH="C:\\Users\\merce\\Desktop\\img";
    static String FILE_SAVE_PATH="/uploadFile";
    static String ILLEGAL_REASON="未佩戴安全帽";

    @ResponseBody
    @PostMapping("/test")
    public void handleImg(@RequestBody Boxes boxes) {
        /*
        boxes parameter ex:
        [[141.8607], [169.83778], [253.20903], [269.2096], [183.09348], [686.54987], [253.90582], [752.73315], [179.92352], [529.33276], [252.73679], [606.4354], [147.91011], [395.01926], [239.71536], [469.7528]]
        (top, left, bottom, right)识别框四点坐标
         */

        //保存
        System.out.println(boxes.getBase64Info());
        String imgDir=Base642ImgUtil.decodeBase64(boxes.getBase64Info(),FILE_SAVE_PATH);

        //固化
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Illegal illegal = new Illegal();
        illegal.setTime(time);
        illegal.setReason(ILLEGAL_REASON);
        illegal.setImgDir(imgDir);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String str = dateFormat.format(time);
        if (illegalService.insertSelective(illegal)==1){
            System.out.println("handleImg传图中：输出路径："+imgDir);
            sendAllMessage(imgDir+"&"+str);
        }
    }

    @ResponseBody
    @GetMapping("/test_send_img")
    public void tttt() {
        sendOneMessage("C:\\Users\\merce\\Desktop\\img\\027daf63-d001-4d10-bcb7-6d2c2add6d11.png","0");

//        return "ok";
    }


    /**
     * 群发消息内容
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/sendAll", method= RequestMethod.GET)
    public void sendAllMessage(@RequestParam(required=true) String message){
        try {
            WebSocketServer.BroadCastInfo(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "ok";
    }

    /**
     * 指定会话ID发消息
     * @param message 消息内容
     * @param id 连接会话ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/sendOne", method=RequestMethod.GET)
    public void sendOneMessage(@RequestParam(required=true) String message,@RequestParam(required=true) String id){
        try {
            WebSocketServer.SendMessage(message,id);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "ok";
    }
}
