package cn.cuit.microcollege.utils;

import java.io.File;
import java.io.IOException;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/2
 * @Descirption:
 */
public class FileUtil {

    /**
     * 创建根路径
     *
     * @param rootPath
     */
    public File makeRootDirectory(String rootPath) {
        File file = null;

        file = new File(rootPath);
        if (!file.exists()) {
            LogU.i("不存在根路径，开始创建文件", getClass().getName());
            if (file.mkdirs()) {
                return file;
            }
        }
        return null;
    }

    /**
     * 创建文件 存在直接返回 不存在创建
     *
     * @param rootPath
     * @param fileName
     * @return
     */
    public static File makeFile(String rootPath, String fileName) {

        if (rootPath == null || fileName == null) {
            throw new NullPointerException("rootPath or fileName cannot be null");
        }
        File direct = new File(rootPath);

        if (!direct.exists()) {
            direct.mkdirs();//不存在、创建路径
            LogU.i("路径不存在开始创建目录:" + direct.getAbsolutePath(), "From FileUtil");
        }
        String filePath = direct.getAbsolutePath() + "/" + fileName;

        //开始创建文件
        File file = new File(filePath);

        if (file.exists()) {
            LogU.i("文件已存在！不重新创建：" + file.getAbsolutePath(), "From FileUtil");
            return file;
        }
        try {
            if (file.createNewFile()) {
                LogU.i("已创建文件：" + file.getAbsolutePath(), "From FileUtil");

            } else {
                LogU.i("创建文件失败：" + file.getAbsolutePath(), "From FileUtil");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }
}
