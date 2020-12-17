package com.jh.mall;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jh.mall.product.entity.PmsBrandEntity;
import com.jh.mall.product.service.PmsBrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest(classes = {ProductMain.class})
public class Test01 {
    @Autowired
    PmsBrandService pmsBrandService;

    @Test
    public void m1() {
        System.out.println("aaaaaa");
        System.out.println("pmsBrandService = " + pmsBrandService);
        PmsBrandEntity pmsBrandEntity = new PmsBrandEntity();
        pmsBrandEntity.setDescript("收到了封建士大夫");
        pmsBrandEntity.setName("name1");
        pmsBrandService.save(pmsBrandEntity);

        List<PmsBrandEntity> list = pmsBrandService.list(new QueryWrapper<PmsBrandEntity>().eq("name", "name1"));
        for (PmsBrandEntity brandEntity : list) {
            System.out.println("brandEntity = " + brandEntity);
        }
        System.out.println("++++++++++++++++++");
    }
    @Autowired(required = false)
    OSSClient ossClient;
    @Test
    public void testUpload() {
        System.out.println("ossClient = " + ossClient);
        // endpoint为自己阿里云bucket中的地域节点
//        String endpoint = "oss-cn-beijing.aliyuncs.com";
//        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。此处创建并使用RAM账号进行API访问或日常运维，
//        // 登录 https://ram.console.aliyun.com 创建RAM账号，并把accessKeyId和accessKeySecret复制出来
//        String accessKeyId = "LTAI4GAyW48eEaEcj4KgkvWT";
//        String accessKeySecret = "1UeO0J45By8GWuUsE24XepaPjNG4YR";
//
//            // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest("mall1208", "image005000.jpg", new File("C:\\Users\\jinha\\Downloads\\image005.jpg"));
//
//        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
//        // ObjectMetadata metadata = new ObjectMetadata();
//        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
//        // metadata.setObjectAcl(CannedAccessControlList.Private);
//        // putObjectRequest.setMetadata(metadata);
//
//        // 上传文件。
        ossClient.putObject(putObjectRequest);
//
//        // 关闭OSSClient。
        ossClient.shutdown();
        System.out.println("+++++++++++++++++++++++++");
    }
}
