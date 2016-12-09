package cn.silen_dev.chromesearchengineer.Chrome.Engineer.Server;

import java.util.List;

import cn.silen_dev.chromesearchengineer.Chrome.Engineer.Model.ShortEngineer;
import cn.silen_dev.chromesearchengineer.Chrome.Package.Model.Chrome;

/**
 * Created by silen on 16-11-20.
 */

public class EngineerServer {
    public static boolean create=false;
    public static List<ShortEngineer> shortEngineerList;
    public static void setShortEngineerList(List<ShortEngineer> list){
        shortEngineerList=list;
    }
    public static List<ShortEngineer> getShortEngineerList(){
        return shortEngineerList;
    }
    public static ShortEngineer shortEngineer;
}
