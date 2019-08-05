package cn.cuit.microcollege.utils;

import android.graphics.Color;
import android.os.Environment;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/5/2
 * @Descirption: 项目所有的配置参数
 */
public class Config {
    //文件根路径
    public static final String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    //照片存放位置
    public static final String PHOTO_FILE_PATH = ROOT_PATH + "/MicroCollege/PhotoFile";

    //swip 颜色集
    public static final int[] SWIP_COLORS = new int[]{Color.YELLOW, Color.RED, Color.GREEN, Color.BLUE};

    /************************************标记******************************************/
    public static final String FLAG_PUBLISH = "from publish activity";//from publish

    //appkey-mob
    public static final String APPKEY_MOB = "29562cf266e90";

    //appkey-juhe：短信验证
    public static final String APPKEY_JUHE_SMS = "cff0b86df5017d1f62621f5574418f5f";

    /***********************************mark********************************************/
    //error：bad internet
    public static final String ERROR_BAD_NET = "微校：网络连接不稳定，请检查网络连接状态";

    /***********************************local host*****************************************/
//    public static final String BASE_HOST = "192.168.1.2";
    public static final String BASE_HOST = "192.168.43.185";

    /***********************************短信模板id*****************************************/
    public static final Integer TEMP_ID = 156331;

    /***********************************开发者模式 开启url的编码 用于上传表情包  没有修复 不建议开启 *****************************************/
    public static boolean DEBUG_TEXT = true;
}