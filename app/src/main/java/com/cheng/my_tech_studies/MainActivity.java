package com.cheng.my_tech_studies;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean  flag =isShortcutInstalled();//如果已经创建，则不需要在创建
        if(flag==false){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("是否为此应用创建桌面快捷方式");
            builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    addShortcutToDesktop();
                }
            });
            builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.create().show();
        }
    }

    private void addShortcutToDesktop() {
        Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重建
        shortcut.putExtra("duplicate", false);
        // 设置名字
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "加菲快捷");
        // 设置图标
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(this,
                        R.drawable.ic_launcher_round));
        // 设置意图和快捷方式关联程序
        Intent intent = new Intent(this, this.getClass());
        // 桌面图标和应用绑定，卸载应用后系统会同时自动删除图标
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        // 发送广播
        sendBroadcast(shortcut);
    }

    /**
     * 快捷方式信息是保存在com.android.launcher的launcher.db的favorites表中
     *
     * @return
     */
    public boolean isShortcutInstalled() {
        boolean isInstallShortcut = false;
        final ContentResolver cr = MainActivity.this
                .getContentResolver();
        // 2.2系统是”com.android.launcher2.settings”,网上见其他的为"com.android.launcher.settings"
        String AUTHORITY = null;
        /*
         * 根据版本号设置Uri的AUTHORITY
         */
        if(getSystemVersion()>=8){
            AUTHORITY = "com.android.launcher2.settings";
        }else{
            AUTHORITY = "com.android.launcher.settings";
        }

        Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI,
                new String[] { "title", "iconResource" }, "title=?",
                new String[] { getString(R.string.app_name) }, null);// XXX表示应用名称。
        if (c != null && c.getCount() > 0) {
            isInstallShortcut = true;
            System.out.println("已创建");
        }
        return isInstallShortcut;
    }

    /**
     * 获取系统的SDK版本号
     * @return
     */
    private int getSystemVersion(){
        return Build.VERSION.SDK_INT;
    }
}
