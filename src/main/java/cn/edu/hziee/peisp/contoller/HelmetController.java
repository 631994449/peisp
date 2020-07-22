package cn.edu.hziee.peisp.contoller;

import cn.edu.hziee.peisp.entity.Helmet;
import cn.edu.hziee.peisp.service.HelmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelmetController {
    @Autowired
    HelmetService helmetService;
    @RequestMapping("/index")
    public String hahaha(){
        return "index";
    }
}
