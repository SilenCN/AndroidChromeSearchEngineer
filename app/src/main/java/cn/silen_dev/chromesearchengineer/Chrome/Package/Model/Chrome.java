package cn.silen_dev.chromesearchengineer.Chrome.Package.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by silen on 16-11-20.
 */

public class Chrome {
    private String name;

    private String packageName;
    private Drawable icon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
