package com.whyzaa.vspringblog.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.whyzaa.vspringblog.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Description:
 * date: 2020/8/2 7:19
 *
 * @author whyzaa<br />
 */
@Slf4j
public class QiniuUpload {
    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "aAqIfnjv3O8eR0U3xmWKYNkB22KMkufQo6YBdTLf";
    private static final String SECRET_KEY = "ui-AHrvBziDcxWKlllbG6o3HvtCePcureNZcidF1";
    // 要上传的空间名称
    private static final String BUCKETNAME = "whyzaa-4";
    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    // 外链默认域名
    private static final String DOMAIN = "https://imgs.iazuresky.com";
    /**
     * 将图片上传到七牛云
     */
    public static String uploadQNImg(FileInputStream file, String key) {
        Configuration cfg = new Configuration(Zone.zone0());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        // 生成上传凭证，然后准备上传
        try {
            String upToken = auth.uploadToken(BUCKETNAME);
            try {
                Response response = uploadManager.put(file, key, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return DOMAIN + "/" + putRet.key;
            } catch (QiniuException ex) {
                throw new MyException(ex.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}