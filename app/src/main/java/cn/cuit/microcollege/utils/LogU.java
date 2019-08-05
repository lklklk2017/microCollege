package cn.cuit.microcollege.utils;

import android.util.Log;

/**
 * @Author: Created by Rod Eden
 * @Date: 2019/4/16
 * @Descirption: LogUtil的简易封装
 */
public class LogU {
    private static boolean debug = true;
    private static String MARK_STRAT = "\n{\n=============================\n";
    private static String MARK_END = "\n=============================}\n";

    public static void i(String content,String mark) {

        if (debug) {
            String info = MARK_STRAT+content+MARK_END;
            String tag = " LogU message:"+mark;
            Log.i(tag,info);
        }
    }

    public static void d(String content,String mark) {

        if (debug) {
            String info = MARK_STRAT+content+MARK_END;
            String tag = " LogU message:"+mark;
            Log.d(tag,info);
        }
    }

    public static void e(String content,String mark) {

        if (debug) {
            String info = MARK_STRAT+content+MARK_END;
            String tag = " LogU message:"+mark;
            Log.e(tag,info);
        }
    }

}
