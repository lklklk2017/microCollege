package cn.cuit.microcollege.utils;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.cuit.microcollege.utils.Config.BASE_HOST;
import static cn.cuit.microcollege.utils.Config.DEBUG_TEXT;

/**
 * Created by Administrator on 2018/3/21.
 * 编码转换工具集
 */

public class TransFormUtil {

    private static SimpleDateFormat sdf_default = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static char[] base64EncodeChars = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};

    /**
     * 将二进制数据数通过base64编码
     * 返回字符串
     *
     * @param data
     * @return
     */
    private static String base64Encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len) {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }

    /**
     * base64编码
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws UnsupportedEncodingException
     */
    public static String base64(String str, String charset) throws UnsupportedEncodingException {
        String encoded = base64Encode(str.getBytes(charset));
        return encoded;
    }

    /**
     * url 编码
     *
     * @param str
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * MD5加密
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    public static String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 电商Sign签名生成
     *
     * @param content  内容
     * @param keyValue Appkey
     * @param charset  编码方式
     * @return DataSign签名
     * @throws UnsupportedEncodingException ,Exception
     */
    @SuppressWarnings("unused")
    public static String encrypt(String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception {
        if (keyValue != null) {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }


    /**
     * px--dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, int pxVal) {

        //获取像素密度系数
        float density = context.getResources().getDisplayMetrics().density;

        return pxVal / density;
    }


    /**
     * dp--px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static float dp2px(Context context, int dpVal) {

        float density = context.getResources().getDisplayMetrics().density;

        return dpVal * density;
    }

    /**
     * 转换地址
     * http://localhost:8080/microCollege/upload/userAvatar/dynamic_two6b59d.png
     *
     * @param str
     * @return
     */
    public static String getLocalImageUrl(String str) {
        if (str != null && str.length() != 0) {
            String localhost = str.replace("localhost", BASE_HOST);
            return localhost;
        }
        return str;
    }

    public static String getRemoteImageUrl(String str){
        if (str != null && str.length() != 0) {
            String localhost = str.replace(BASE_HOST,"localhost");
            return localhost;
        }
        return str;
    }


    /**
     * 返回本地默认格式的时间对象
     *
     * @param date
     * @return
     */
    public static Date getLocalDate(String date) {

        try {
            return sdf_default.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 返回默认格式的时间字符串
     *
     * @param date
     * @return
     */
    public static String getLocalDateString(Date date) {

        return sdf_default.format(date);
    }

    /**
     * 返回utf-8解码过后的文字
     *
     * @param content
     * @return
     */
    public static String getDecodeWithUTF(String content) {

        if (content == null) {
            return null;
        }

        if (!DEBUG_TEXT) {
            try {
                return URLDecoder.decode(content, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 返回utf-8编码过后的文字
     *
     * @param content
     * @return
     */
    public static String getEncodeWithUTF(String content) {

        if (content == null) {
            return null;
        }

        if (!DEBUG_TEXT) {
            try {
                return URLEncoder.encode(content, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return content;
    }


    /**
     * 校验email
     *
     * @param email
     * @return
     */
    public static boolean checkIsEmail(String email) {
        String regEx =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Matcher matcher = Pattern.compile(regEx).matcher(email.toString().trim());
        if (matcher.matches()) {
            return true;
        }
        return false;
    }


}
