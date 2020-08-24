package cn.edu.hziee.peisp.utils;

import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class Base642ImgUtil {
    private static final String FILE_PATH_PREFIX = "/root/tomcat";

    public static String decodeBase64(String base64Info, String filePath){
        base64Info = base64Info.replaceAll(" ", "+");

        if(StringUtils.isEmpty(base64Info)){
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();

        // 数据中：data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAABI4AAAEsCAYAAAClh/jbAAA ...  在"base64,"之后的才是图片信息
        String picPath = filePath+ "/"+ UUID.randomUUID().toString() +".png";
        try {
            byte[] buffer;
            if (!base64Info.contains("base64,")){
                buffer = decoder.decodeBuffer(base64Info);
            }else {
                String[] arr = base64Info.split("base64,");
                buffer = decoder.decodeBuffer(arr[1]);
            }
            OutputStream os = new FileOutputStream(FILE_PATH_PREFIX+picPath);
            os.write(buffer);
            os.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return picPath;
    }
}

