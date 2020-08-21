package cn.edu.hziee.peisp.contoller;

import cn.edu.hziee.peisp.netty.SimpleChatServerHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HarewareController {
    @RequestMapping("sendMSG")
    @ResponseBody
    public void getMSG(@RequestBody String sign){
        System.out.println(sign);
        SimpleChatServerHandler.sendMessage(sign);
    }
}
