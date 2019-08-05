package cn.cuit.microcollege.utils;

/**
 * Created by Administrator on 2018/4/1.
 */
public class DBManager {
//
//    //    private final static String dbName = "private_db";
//    private final static String dbName = "test_db";
//    private static DBManager mInstance;
//    private DaoMaster.DevOpenHelper openHelper;
//    private DaoSession mSession;
//    private Database mDb;
//    private DaoMaster mDaoMaster;
//
//    private DBManager(Context context) {
//        init(context);
//    }
//
//    /**
//     * 初始化数据库配置
//     */
//    private void init(Context context) {
//        /**
//         * 获取帮助类
//         * 创建销毁数据库等
//         * dev：开发者模式
//         */
//        openHelper = new DaoMaster.DevOpenHelper(context, dbName);
//        //获取可写数据库
//        mDb = openHelper.getWritableDb();
//        //获取数据库对象(针对数据库中任意的表的操作对象)
//        mDaoMaster = new DaoMaster(mDb);
//        //获取Dao对象管理(针对表字段操作的一系列操作)
//        mSession = mDaoMaster.newSession();
//    }
//
//    /**
//     * 获取master对象
//     *
//     * @return
//     */
//    private DaoMaster getDaoMaster() {
//        return mDaoMaster;
//    }
//
//    /**
//     * 获取单例引用
//     *
//     * @return
//     */
//    public static DBManager getInstance() {
//        if (null == mInstance) {
//            synchronized (DBManager.class) {
//                if (null == mInstance) {
//                    return mInstance = new DBManager(MyApplication.getMyContext());
//                }
//            }
//        }
//        return mInstance;
//    }
//
//    /**
//     * 获取Sessioin对象
//     *
//     * @return
//     */
//    public DaoSession getSessionInstance() {
//        return mSession;
//    }
//
//    /**
//     * 获取数据库对象
//     *
//     * @return
//     */
//    public Database getDataBase() {
//        return mDb;
//    }
//
//    /**
//     * 清空并重新创建所有的表
//     * 先drop 后create
//     *
//     * @param oldVersion
//     * @param newVersion
//     */
//    public void reCreate(int oldVersion, int newVersion) {
//
//        openHelper.onUpgrade(getDataBase(), oldVersion, newVersion);
//    }
//
//    /**
//     * 单独重新创建所有的表
//     * 手动调用创建所有的表
//     * 不走生命周期
//     *
//     * @param ifNotExist
//     */
//    public void creatTable(boolean ifNotExist) {
//        getDaoMaster().createAllTables(getDataBase(), ifNotExist);
//    }

}
