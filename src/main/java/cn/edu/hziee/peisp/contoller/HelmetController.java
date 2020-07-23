package cn.edu.hziee.peisp.contoller;

import cn.edu.hziee.peisp.service.HelmetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelmetController {
    @Autowired
    HelmetService helmetService;
    @RequestMapping("/hello")
    public String hahaha(){
        return "redirect:/index.html";
    }
}
