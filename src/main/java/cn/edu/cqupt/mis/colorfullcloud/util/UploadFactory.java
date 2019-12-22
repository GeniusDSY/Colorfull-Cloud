package cn.edu.cqupt.mis.colorfullcloud.util;

import com.qiniu.util.Auth;

/**
 * @author :DengSiYuan
 * @date :2019/10/10 15:57
 * @desc :
 */
public class UploadFactory {

    public static UploadUtil createUpload(String accessKey, String secretKeySpec, String bucketHostName, String bucketName) {
        Auth auth = Auth.create(accessKey, secretKeySpec);
        return new QiniuUtil(bucketHostName, bucketName, auth);
    }

    public static UploadUtil deleteFile(String accessKey, String secretKeySpec, String bucketHostName, String bucketName) {
        Auth auth = Auth.create(accessKey, secretKeySpec);
        return new QiniuUtil(bucketHostName, bucketName, auth);
    }

}
