package yeelion.bf.com.baselibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Kurt on 2018/7/20 上午11:18
 * 获取设备信息 设备工具类
 */

public class DeviceInfo {
    private static String sID = null;
    private static final String INSTALLATION = "INSTALLATION";
    private static final char SPLIT = '_';

    public synchronized static String id(Context context) {
        if (sID == null) {
            File installation = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!installation.exists())
                    writeInstallationFile(context, installation);
                sID = readInstallationFile(installation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeInstallationFile(Context context, File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String[] infos = getDeviceInfo(context);
        out.write(infos[0].getBytes());
        out.write(SPLIT);
        out.write(infos[1].getBytes());
        out.write(SPLIT);
        out.write(infos[2].getBytes());
        out.write(SPLIT);
        out.write(infos[3].getBytes());

        out.close();
    }

    public static final int DEVICE_INFO_DEVICEID = 0;
    public static final int DEVICE_INFO_SERIALNO = 1;
    public static final int DEVICE_INFO_MAC = 2;
    public static final int DEVICE_INFO_ANDROIDID = 3;

    /**
     * returns device info in order of deviceId(deviceid), serialNo(udid), mac, uuid
     *
     * @param context
     * @return
     */
    public static String[] getDeviceInfo(Context context) {
        String[] info = new String[4];

        info[DEVICE_INFO_DEVICEID] = getDeviceId(context);
        info[DEVICE_INFO_SERIALNO] = getSerialNo();
        info[DEVICE_INFO_MAC] = getMac(context);
        info[DEVICE_INFO_ANDROIDID] = getAndroidId(context);

        return info;
    }

    public static String getMac(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        @SuppressLint("MissingPermission") WifiInfo info = wifi.getConnectionInfo();
        String mac = info.getMacAddress();
        return TextUtils.isEmpty(mac) ? "" : mac.toLowerCase();
    }

    public static String getSerialNo() {
        if (Build.VERSION.SDK_INT < 9)
            return "";

        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            String serialNo = (String) (get.invoke(c, "ro.serialno", ""));
            return TextUtils.isEmpty(serialNo) ? "" : serialNo.toLowerCase();
        } catch (Exception ignored) {
            return "";
        }
    }

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String deviceId = telephonyManager.getDeviceId();
        return TextUtils.isEmpty(deviceId) || deviceId.contains("0000000000000") || deviceId.equals("0") ? "" : deviceId.toLowerCase();
    }

    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (androidId == null || androidId.equals("9774d56d682e549c") || androidId.length() < 15) {
            //if ANDROID_ID is null, or it's equals to the GalaxyTab generic ANDROID_ID or bad, generates a new one
            final SecureRandom random = new SecureRandom();
            androidId = new BigInteger(64, random).toString(16);
        }

        return androidId;
    }
}
