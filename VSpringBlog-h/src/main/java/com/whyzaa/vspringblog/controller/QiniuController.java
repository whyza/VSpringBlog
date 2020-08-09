package com.whyzaa.vspringblog.controller;

import com.whyzaa.vspringblog.entity.vo.imageVo;
import com.whyzaa.vspringblog.util.QiniuUpload;
import com.whyzaa.vspringblog.util.ResponseData;
import com.whyzaa.vspringblog.util.ResponseDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Description:
 * date: 2020/8/2 8:04
 *
 * @author whyzaa<br />
 */
@RestController
@Slf4j
public class QiniuController {

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    private ResponseData postUserInforUpDate(MultipartFile multipartFile) throws IOException {

        // 用来获取其他参数
        if (!multipartFile.isEmpty()) {
            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
            String path = QiniuUpload.uploadQNImg(inputStream, UUID.randomUUID() + multipartFile.getOriginalFilename()); // KeyUtil.genUniqueKey()生成图片的随机名
            log.info("七牛云返回的图片链接:" + path);
            return ResponseDataUtil.success("上传成功", new imageVo(multipartFile.getOriginalFilename(), path));
        }
        return ResponseDataUtil.failure("上传失败,请稍后重试");
    }
}
