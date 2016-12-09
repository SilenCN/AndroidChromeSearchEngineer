package cn.silen_dev.chromesearchengineer.Chrome.Package.Server;

import java.util.ArrayList;
import java.util.List;

import cn.silen_dev.chromesearchengineer.Chrome.Package.Model.Chrome;

/**
 * Created by silen on 16-11-20.
 */

public class ChromePackageHelper {
    public static List<Chrome> list;
    public static int choose_chrome_position;
    public static List<Chrome> getList(){
        return list;
    }
    public static void setList(List<Chrome> chromeList){
        list=chromeList;
    }
    public static void addChromePackage(Chrome chrome){
        if (null==list){
            list=new ArrayList<>();
        }
        list.add(chrome);
        System.out.println(chrome.getPackageName());
    }
    public static void setChoose_chrome_position(int position){
        choose_chrome_position=position;
    }
    public static Chrome getChrome(){
        return list.get(choose_chrome_position);
    }
}
