package yeelion.bf.com.baselibrary.permissions;

/**
 * Created by Kurt on 2018/7/19 下午6:43
 */

public abstract class PermissionsResult {
    public void onGranted(){};
    public void onDenied(String permission){};
}