package cn.edu.cqupt.mis.colorfullcloud.util;

import cn.edu.cqupt.mis.colorfullcloud.common.excepction.ServerException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author :DengSiYuan
 * @date :2019/10/9 18:11
 * @desc :
 */
public interface UploadUtil {
    String uploadFile(MultipartFile multipartFile) throws ServerException;

    String uploadFile(String filePath, MultipartFile multipartFile) throws ServerException;

    String uploadFile(MultipartFile multipartFile, String fileName) throws ServerException;

    String uploadFile(MultipartFile multipartFile, String fileName, String filePath) throws ServerException;

    String uploadFile(File file) throws ServerException;

    String uploadFile(String filePath, File file) throws ServerException;

    String uploadFile(File file, String fileName) throws ServerException;

    String uploadFile(File file, String fileName, String filePath) throws ServerException;

    String uploadFile(byte[] data) throws ServerException;

    String uploadFile(String filePath, byte[] data) throws ServerException;

    String uploadFile(byte[] data, String fileName) throws ServerException;

    String uploadFile(byte[] data, String fileName, String filePath) throws ServerException;

    void deleteFile(String key, String bucket)throws ServerException;
}
