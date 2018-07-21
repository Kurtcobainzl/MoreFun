package yeelion.bf.com.baselibrary.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.PowerManager;

import java.net.InetSocketAddress;

/**
 * Created by Kurt on 2018/7/21 下午12:40
 */

public class NetworkUtil {

    private final Context mContext;
    private volatile static NetworkUtil INSTANCE;
    private volatile WifiManager.WifiLock mWifiLock;
    private volatile PowerManager.WakeLock mWakeLock;

    public static final int NETWORK_NONE = -1;
    public static final int NETWORK_WIFI = 1;
    public static final int NETWORK_MOBILE = 0;
    public static int NETWORK_TYPE = NETWORK_MOBILE;

    private NetworkUtil(Context context) {
        mContext = context;
    }

    public static NetworkUtil getInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (NetworkUtil.class) {
                if (INSTANCE == null)
                    INSTANCE = new NetworkUtil(context);
            }
        }

        return INSTANCE;
    }

    /**
     * Return -1 when there is no network available
     *
     * @return ConnectivityManager.TYPE_* or -1
     */
    public int getNetworkType() {
        final ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        int result = -1;
        final NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi != null && wifi.isConnected())
            result = ConnectivityManager.TYPE_WIFI;
        else if (mobile != null && mobile.isConnected())
            result = ConnectivityManager.TYPE_MOBILE;
        return result;
    }

    public boolean isNetworkOK() {
        return getNetworkType() != -1;
    }

    /**
     * Return null if there is no proxy specified in apn settings or current connection is not mobile connection.
     *
     * @return InetSocketAddress or null
     */
    public InetSocketAddress getAPNProxy() {
        if (getNetworkType() == ConnectivityManager.TYPE_MOBILE) {
            final Uri uri = Uri.parse("content://telephony/carriers/preferapn");
            final Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                final String address = cursor.getString(cursor.getColumnIndex("proxy"));
                final String port = cursor.getString(cursor.getColumnIndex("port"));
                if (address != null && address.trim().length() > 0)
                    return new InetSocketAddress(address, Integer.getInteger(port, 80));
            }
            if (cursor != null) {
                cursor.close();
            }
        }

        return null;
    }

    public void acquireWakeLock() {
        acquireWakeLock(true);
    }

    /**
     * Allows an application to keep awake, need to release it manually
     */
    public void acquireWakeLock(boolean isReferenceCounted) {
        synchronized (this) {
            if (mWakeLock == null) {
                final WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
                mWifiLock = wifiManager.createWifiLock("wifiLock");
                final PowerManager powerManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
                mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "wakelock");
            }
        }

        /*if (!mWifiLock.isHeld()) {
            mWifiLock.acquire();
            //mWifiLock.setReferenceCounted(true);
        }

        if (!mWakeLock.isHeld()) {
            mWakeLock.acquire();
            //mWakeLock.setReferenceCounted(true);
        }*/

        mWifiLock.setReferenceCounted(isReferenceCounted);
        mWakeLock.setReferenceCounted(isReferenceCounted);

        mWifiLock.acquire();
        mWakeLock.acquire();
    }

    public void releaseWakeLock() {
        //if (mWifiLock != null && mWifiLock.isHeld())
        if (mWifiLock != null)
            mWifiLock.release();

        // if (mWakeLock != null && mWakeLock.isHeld())
        if (mWakeLock != null)
            mWakeLock.release();
    }
}

