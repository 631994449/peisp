package cn.edu.hziee.peisp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class UploadConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //映射图片保存地址
        registry.addResourceHandler("/uploadFile/**")
//                .addResourceLocations("file:/root/tomcat/uploadFile/");
//                .addResourceLocations("file:D:\\CWD Project\\peisp\\src\\main\\resources\\static\\uploadFile\\");
                .addResourceLocations("file:C:\\Users\\merce\\Desktop\\up_img\\uploadFile\\");

    }
}
