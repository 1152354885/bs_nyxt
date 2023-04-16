package com.example.navigationview;

        import android.app.Application;

        import org.xutils.x;

public class MyApplication extends Application {

    public String selectbypageurl="http://192.168.139.253:8080/API/v1.0/moniter/";

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        //x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
