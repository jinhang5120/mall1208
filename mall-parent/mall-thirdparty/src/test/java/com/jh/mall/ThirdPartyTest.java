package com.jh.mall;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest(classes = {ThridpartyMain.class})
public class ThirdPartyTest {
    @Autowired(required = false)
    OSSClient ossClient;
    @Test
    public void testUpload() {
        System.out.println("ossClient = " + ossClient);
        PutObjectRequest putObjectRequest = new PutObjectRequest("mall1208", "image005-1.jpg", new File("C:\\Users\\jinha\\Downloads\\image005.jpg"));
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        System.out.println("+++++++++++++++++++++++++");
    }
}
