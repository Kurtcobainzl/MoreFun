package com.more.fun.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.more.fun.R;
import com.more.fun.dialog.AlertDialog;

import yeelion.bf.com.baselibrary.permissions.PermissionsManager;
import yeelion.bf.com.baselibrary.permissions.PermissionsResult;
import yeelion.bf.com.baselibrary.permissions.PermissionsResultAction;


public class PermissionsChecker {

    private static PermissionsChecker permissionsChecker;
    private PermissionsManager permissionsManager;

    private PermissionsChecker() {
        permissionsManager = PermissionsManager.getInstance();
    }

    public synchronized static PermissionsChecker instance() {
        if (null == permissionsChecker) {
            permissionsChecker = new PermissionsChecker();
        }
        return permissionsChecker;
    }

    private boolean hasPermission(Context context, String permission) {
        return permissionsManager.hasPermission(context, permission);
    }

    private void requestPermission(final Activity activity, String permission, final PermissionsResult permissionsResult, final boolean perimissiionDeniedDefaultDialog) {
        permissionsManager.requestPermissionsIfNecessaryForResult(activity, new String[]{permission},
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        if (null != permissionsResult) {
                            permissionsResult.onGranted();
                        }
                    }

                    @Override
                    public void onDenied(String permission) {
                        if (null != permissionsResult) {
                            permissionsResult.onDenied(permission);
                        }
                        if (perimissiionDeniedDefaultDialog) {
                            showGoToSettingsDialog(activity, permission);
                        }
                    }
                });
    }

    public void requestPermission(final Fragment fragment, final String permission, final PermissionsResult permissionsResult, final boolean perimissiionDeniedDefaultDialog) {
        permissionsManager.requestPermissionsIfNecessaryForResult(fragment, new String[]{permission},
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        if (null != permissionsResult) {
                            permissionsResult.onGranted();
                        }
                    }

                    @Override
                    public void onDenied(String permission) {
                        if (null != permissionsResult) {
                            permissionsResult.onDenied(permission);
                        }
                        if (perimissiionDeniedDefaultDialog) {
                            showGoToSettingsDialog(fragment.getActivity(), permission);
                        }
                    }
                });
    }

    public boolean checkoutPerimissionRationable(Activity activity, String perimission) {
        return PermissionsManager.getInstance().checkoutPerimissionRationable(activity, perimission);
    }

    public boolean requestWriteReadExternalStoragePermissions(Activity activity, final PermissionsResult permissionsResult, final boolean perimissiionDeniedDefaultDialog) {
        boolean hasReadExternalStorage = PermissionsChecker.instance().hasPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        boolean hasWriteExternalStorage = PermissionsChecker.instance().hasPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (!hasReadExternalStorage) {
            PermissionsChecker.instance().requestPermission(activity,Manifest.permission.READ_EXTERNAL_STORAGE, permissionsResult, perimissiionDeniedDefaultDialog);
        }
        if (!hasWriteExternalStorage) {
            PermissionsChecker.instance().requestPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, permissionsResult, perimissiionDeniedDefaultDialog);
        }
        return hasReadExternalStorage || hasWriteExternalStorage;
    }

    public boolean hasWriteReadExternalStoragePermissions(Context activity) {
        boolean hasReadExternalStorage = PermissionsChecker.instance().hasPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        boolean hasWriteExternalStorage = PermissionsChecker.instance().hasPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return hasReadExternalStorage || hasWriteExternalStorage;
    }


    public void requestLocationPermission(Activity activity, final PermissionsResult permissionsResult, final boolean perimissiionDeniedDefaultDialog) {
        boolean hasPermissionFineLocation = PermissionsChecker.instance().hasPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                || PermissionsChecker.instance().hasPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (!hasPermissionFineLocation) {
            if (!PermissionsChecker.instance().hasPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                PermissionsChecker.instance().requestPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION, permissionsResult, perimissiionDeniedDefaultDialog);
            } else if (!PermissionsChecker.instance().hasPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                PermissionsChecker.instance().requestPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION, permissionsResult, perimissiionDeniedDefaultDialog);
            }
        }
    }

    public boolean hasFineLocationPermission(Context activity) {
        boolean hasPermissionFineLocation = PermissionsChecker.instance().hasPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        return hasPermissionFineLocation;
    }

    public boolean hasLocationPermission(Context activity) {
        boolean hasPermissionFineLocation = PermissionsChecker.instance().hasPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                || PermissionsChecker.instance().hasPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        return hasPermissionFineLocation;
    }

    public boolean hasCoarseLocation(Context activity) {
        boolean hasPermissionCoarseocation = PermissionsChecker.instance().hasPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        return hasPermissionCoarseocation;
    }

    public void requestCoarseLocation(Activity activity, final PermissionsResult permissionsResult, final boolean perimissiionDeniedDefaultDialog) {
        PermissionsChecker.instance().requestPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION, permissionsResult, perimissiionDeniedDefaultDialog);
    }

    public boolean hasCallPhonePermission(Context activity) {
        boolean hasPermission = PermissionsChecker.instance().hasPermission(activity, Manifest.permission.CALL_PHONE);
        return hasPermission;
    }

    public void requestCallPhonePermission(Activity activity, final PermissionsResult permissionsResult, final boolean perimissiionDeniedDefaultDialog) {
        PermissionsChecker.instance().requestPermission(activity, Manifest.permission.CALL_PHONE, permissionsResult, perimissiionDeniedDefaultDialog);
    }

    public boolean hasCameraPermission(Context activity) {
        boolean hasPermission = PermissionsChecker.instance().hasPermission(activity, Manifest.permission.CAMERA);
        return hasPermission;
    }

    public void requestCameraPermission(Activity activity, final PermissionsResult permissionsResult, final boolean perimissiionDeniedDefaultDialog) {
        PermissionsChecker.instance().requestPermission(activity, Manifest.permission.CAMERA, permissionsResult, perimissiionDeniedDefaultDialog);
    }

    public boolean hasSmsReadWritePermission(Context activity) {
        return PermissionsChecker.instance().hasPermission(activity, Manifest.permission.READ_SMS);
    }

    public void requestSmsReadWritePermission(Activity activity, final PermissionsResult permissionsResult, final boolean perimissiionDeniedDefaultDialog) {
        PermissionsChecker.instance().requestPermission(activity, Manifest.permission.READ_SMS, permissionsResult, perimissiionDeniedDefaultDialog);
    }

    public boolean hasReadPhoneStatusPermission(Context activity) {
        return PermissionsChecker.instance().hasPermission(activity, Manifest.permission.READ_PHONE_STATE);
    }

    public void requestReadPhoneStatusPermission(Activity activity, final PermissionsResult permissionsResult, final boolean perimissiionDeniedDefaultDialog) {
        PermissionsChecker.instance().requestPermission(activity, Manifest.permission.READ_PHONE_STATE, permissionsResult, perimissiionDeniedDefaultDialog);
    }

    private void showGoToSettingsDialog(final Activity activity, String permission) {
        AlertDialog builder = new AlertDialog(activity).builder();
        builder.setTitle(activity.getString(R.string.notice_str));
        builder.setOutTouchCancelable(false);
        String message = "";
        if (Manifest.permission.CALL_PHONE.equalsIgnoreCase(permission)) {
            message = activity.getString(R.string.perimission_call_phone_open_notice_str);
        }
        if (Manifest.permission.ACCESS_FINE_LOCATION.equalsIgnoreCase(permission)) {
            message = activity.getString(R.string.perimission_fine_location_open_notice_str);
        }
        if (Manifest.permission.ACCESS_COARSE_LOCATION.equalsIgnoreCase(permission)) {
            message = activity.getString(R.string.perimission_network_location_open_notice_str);
        }
        if (Manifest.permission.CAMERA.equalsIgnoreCase(permission)) {
            message = activity.getString(R.string.perimission_camera_open_notice_str);
        }
        if (Manifest.permission.READ_SMS.equalsIgnoreCase(permission)) {
            message = activity.getString(R.string.perimission_sms_read_write_open_notice_str);
        }
        if (Manifest.permission.READ_EXTERNAL_STORAGE.equalsIgnoreCase(permission)) {
            message = activity.getString(R.string.perimission_sdcard_read_open_notice_str);
        }
        if (Manifest.permission.WRITE_EXTERNAL_STORAGE.equalsIgnoreCase(permission)) {
            message = activity.getString(R.string.perimission_sdcard_write_open_notice_str);
        }
        if (Manifest.permission.READ_PHONE_STATE.equalsIgnoreCase(permission)) {
            message = activity.getString(R.string.perimission_read_phone_status_open_notice_str);
        }
        if (TextUtils.isEmpty(message)) {
            return;
        }
        builder.setMsg(message);
        builder.setNegativeButton(activity.getResources().getString(R.string.cancle_str), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        builder.setPositiveButton(activity.getString(R.string.go_to_settings_str), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(getAppDetailSettingIntent(activity));
            }
        }).show();
    }

    public Intent getAppDetailSettingIntent(Activity activity) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", activity.getPackageName());
        }
        return localIntent;
    }
}
