package cn.edu.hziee.peisp.contoller;

import cn.edu.hziee.peisp.model.Result;
import cn.edu.hziee.peisp.utils.Base642ImgUtil;
import cn.edu.hziee.peisp.utils.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class LiveController {
    static String FILE_SAVE_PATH="C:\\Users\\merce\\Desktop\\img";

    @ResponseBody
    @PostMapping("/test")
    public Result handleImg(@RequestParam(value = "base64Info") String base64Info) {
        System.out.println(base64Info);
        Base642ImgUtil.decodeBase64(base64Info,FILE_SAVE_PATH);
        return ResultUtil.success();
    }

}
