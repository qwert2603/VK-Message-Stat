package com.qwert2603.vkmessagestat.mock

import android.content.*
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.UserHandle
import android.view.Display
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

class MockContext : Context() {
    override fun getApplicationContext(): Context? {
        throw UnsupportedOperationException()
    }

    override fun setWallpaper(p0: InputStream?) {
        throw UnsupportedOperationException()
    }

    override fun removeStickyBroadcastAsUser(p0: Intent?, p1: UserHandle?) {
        throw UnsupportedOperationException()
    }

    override fun checkCallingOrSelfPermission(p0: String?): Int {
        throw UnsupportedOperationException()
    }

    override fun setWallpaper(p0: Bitmap?) {
        throw UnsupportedOperationException()
    }

    override fun getClassLoader(): ClassLoader? {
        throw UnsupportedOperationException()
    }

    override fun checkCallingOrSelfUriPermission(p0: Uri?, p1: Int): Int {
        throw UnsupportedOperationException()
    }

    override fun getObbDir(): File? {
        throw UnsupportedOperationException()
    }

    override fun checkUriPermission(p0: Uri?, p1: Int, p2: Int, p3: Int): Int {
        throw UnsupportedOperationException()
    }

    override fun checkUriPermission(p0: Uri?, p1: String?, p2: String?, p3: Int, p4: Int, p5: Int): Int {
        throw UnsupportedOperationException()
    }

    override fun getExternalFilesDirs(p0: String?): Array<out File>? {
        throw UnsupportedOperationException()
    }

    override fun getPackageResourcePath(): String? {
        throw UnsupportedOperationException()
    }

    override fun deleteSharedPreferences(p0: String?): Boolean {
        throw UnsupportedOperationException()
    }

    override fun checkPermission(p0: String?, p1: Int, p2: Int): Int {
        throw UnsupportedOperationException()
    }

    override fun startIntentSender(p0: IntentSender?, p1: Intent?, p2: Int, p3: Int, p4: Int) {
        throw UnsupportedOperationException()
    }

    override fun startIntentSender(p0: IntentSender?, p1: Intent?, p2: Int, p3: Int, p4: Int, p5: Bundle?) {
        throw UnsupportedOperationException()
    }

    override fun getSharedPreferences(p0: String?, p1: Int): SharedPreferences? {
        throw UnsupportedOperationException()
    }

    override fun sendStickyBroadcastAsUser(p0: Intent?, p1: UserHandle?) {
        throw UnsupportedOperationException()
    }

    override fun getDataDir(): File? {
        throw UnsupportedOperationException()
    }

    override fun getWallpaper(): Drawable? {
        throw UnsupportedOperationException()
    }

    override fun isDeviceProtectedStorage(): Boolean {
        throw UnsupportedOperationException()
    }

    override fun getExternalFilesDir(p0: String?): File? {
        throw UnsupportedOperationException()
    }

    override fun sendBroadcastAsUser(p0: Intent?, p1: UserHandle?) {
        throw UnsupportedOperationException()
    }

    override fun sendBroadcastAsUser(p0: Intent?, p1: UserHandle?, p2: String?) {
        throw UnsupportedOperationException()
    }

    override fun getExternalCacheDir(): File? {
        throw UnsupportedOperationException()
    }

    override fun getDatabasePath(p0: String?): File? {
        throw UnsupportedOperationException()
    }

    override fun getFileStreamPath(p0: String?): File? {
        throw UnsupportedOperationException()
    }

    override fun stopService(p0: Intent?): Boolean {
        throw UnsupportedOperationException()
    }

    override fun checkSelfPermission(p0: String?): Int {
        throw UnsupportedOperationException()
    }

    override fun registerReceiver(p0: BroadcastReceiver?, p1: IntentFilter?): Intent? {
        throw UnsupportedOperationException()
    }

    override fun registerReceiver(p0: BroadcastReceiver?, p1: IntentFilter?, p2: String?, p3: Handler?): Intent? {
        throw UnsupportedOperationException()
    }

    override fun getSystemServiceName(p0: Class<*>?): String? {
        throw UnsupportedOperationException()
    }

    override fun getMainLooper(): Looper? {
        throw UnsupportedOperationException()
    }

    override fun enforceCallingOrSelfPermission(p0: String?, p1: String?) {
        throw UnsupportedOperationException()
    }

    override fun getPackageCodePath(): String? {
        throw UnsupportedOperationException()
    }

    override fun checkCallingUriPermission(p0: Uri?, p1: Int): Int {
        throw UnsupportedOperationException()
    }

    override fun getWallpaperDesiredMinimumWidth(): Int {
        throw UnsupportedOperationException()
    }

    override fun createDeviceProtectedStorageContext(): Context? {
        throw UnsupportedOperationException()
    }

    override fun openFileInput(p0: String?): FileInputStream? {
        throw UnsupportedOperationException()
    }

    override fun getCodeCacheDir(): File? {
        throw UnsupportedOperationException()
    }

    override fun bindService(p0: Intent?, p1: ServiceConnection?, p2: Int): Boolean {
        throw UnsupportedOperationException()
    }

    override fun deleteDatabase(p0: String?): Boolean {
        throw UnsupportedOperationException()
    }

    override fun getAssets(): AssetManager? {
        throw UnsupportedOperationException()
    }

    override fun getNoBackupFilesDir(): File? {
        throw UnsupportedOperationException()
    }

    override fun startActivities(p0: Array<out Intent>?) {
        throw UnsupportedOperationException()
    }

    override fun startActivities(p0: Array<out Intent>?, p1: Bundle?) {
        throw UnsupportedOperationException()
    }

    override fun getResources(): Resources? {
        throw UnsupportedOperationException()
    }

    override fun fileList(): Array<out String>? {
        throw UnsupportedOperationException()
    }

    override fun setTheme(p0: Int) {
        throw UnsupportedOperationException()
    }

    override fun unregisterReceiver(p0: BroadcastReceiver?) {
        throw UnsupportedOperationException()
    }

    override fun enforcePermission(p0: String?, p1: Int, p2: Int, p3: String?) {
        throw UnsupportedOperationException()
    }

    override fun openFileOutput(p0: String?, p1: Int): FileOutputStream? {
        throw UnsupportedOperationException()
    }

    override fun sendStickyOrderedBroadcast(p0: Intent?, p1: BroadcastReceiver?, p2: Handler?, p3: Int, p4: String?, p5: Bundle?) {
        throw UnsupportedOperationException()
    }

    override fun createConfigurationContext(p0: Configuration?): Context? {
        throw UnsupportedOperationException()
    }

    override fun getFilesDir(): File? {
        throw UnsupportedOperationException()
    }

    override fun sendBroadcast(p0: Intent?) {
        throw UnsupportedOperationException()
    }

    override fun sendBroadcast(p0: Intent?, p1: String?) {
        throw UnsupportedOperationException()
    }

    override fun sendOrderedBroadcastAsUser(p0: Intent?, p1: UserHandle?, p2: String?, p3: BroadcastReceiver?, p4: Handler?, p5: Int, p6: String?, p7: Bundle?) {
        throw UnsupportedOperationException()
    }

    override fun grantUriPermission(p0: String?, p1: Uri?, p2: Int) {
        throw UnsupportedOperationException()
    }

    override fun enforceCallingUriPermission(p0: Uri?, p1: Int, p2: String?) {
        throw UnsupportedOperationException()
    }

    override fun getCacheDir(): File? {
        throw UnsupportedOperationException()
    }

    override fun clearWallpaper() {
        throw UnsupportedOperationException()
    }

    override fun sendStickyOrderedBroadcastAsUser(p0: Intent?, p1: UserHandle?, p2: BroadcastReceiver?, p3: Handler?, p4: Int, p5: String?, p6: Bundle?) {
        throw UnsupportedOperationException()
    }

    override fun startActivity(p0: Intent?) {
        throw UnsupportedOperationException()
    }

    override fun startActivity(p0: Intent?, p1: Bundle?) {
        throw UnsupportedOperationException()
    }

    override fun getPackageManager(): PackageManager? {
        throw UnsupportedOperationException()
    }

    override fun openOrCreateDatabase(p0: String?, p1: Int, p2: SQLiteDatabase.CursorFactory?): SQLiteDatabase? {
        throw UnsupportedOperationException()
    }

    override fun openOrCreateDatabase(p0: String?, p1: Int, p2: SQLiteDatabase.CursorFactory?, p3: DatabaseErrorHandler?): SQLiteDatabase? {
        throw UnsupportedOperationException()
    }

    override fun deleteFile(p0: String?): Boolean {
        throw UnsupportedOperationException()
    }

    override fun startService(p0: Intent?): ComponentName? {
        throw UnsupportedOperationException()
    }

    override fun revokeUriPermission(p0: Uri?, p1: Int) {
        throw UnsupportedOperationException()
    }

    override fun moveDatabaseFrom(p0: Context?, p1: String?): Boolean {
        throw UnsupportedOperationException()
    }

    override fun startInstrumentation(p0: ComponentName?, p1: String?, p2: Bundle?): Boolean {
        throw UnsupportedOperationException()
    }

    override fun sendOrderedBroadcast(p0: Intent?, p1: String?) {
        throw UnsupportedOperationException()
    }

    override fun sendOrderedBroadcast(p0: Intent?, p1: String?, p2: BroadcastReceiver?, p3: Handler?, p4: Int, p5: String?, p6: Bundle?) {
        throw UnsupportedOperationException()
    }

    override fun unbindService(p0: ServiceConnection?) {
        throw UnsupportedOperationException()
    }

    override fun getApplicationInfo(): ApplicationInfo? {
        throw UnsupportedOperationException()
    }

    override fun getWallpaperDesiredMinimumHeight(): Int {
        throw UnsupportedOperationException()
    }

    override fun createDisplayContext(p0: Display?): Context? {
        throw UnsupportedOperationException()
    }

    override fun getTheme(): Resources.Theme? {
        throw UnsupportedOperationException()
    }

    override fun getPackageName(): String? {
        throw UnsupportedOperationException()
    }

    override fun getContentResolver(): ContentResolver? {
        throw UnsupportedOperationException()
    }

    override fun getObbDirs(): Array<out File>? {
        throw UnsupportedOperationException()
    }

    override fun enforceCallingOrSelfUriPermission(p0: Uri?, p1: Int, p2: String?) {
        throw UnsupportedOperationException()
    }

    override fun moveSharedPreferencesFrom(p0: Context?, p1: String?): Boolean {
        throw UnsupportedOperationException()
    }

    override fun getExternalMediaDirs(): Array<out File>? {
        throw UnsupportedOperationException()
    }

    override fun checkCallingPermission(p0: String?): Int {
        throw UnsupportedOperationException()
    }

    override fun getExternalCacheDirs(): Array<out File>? {
        throw UnsupportedOperationException()
    }

    override fun sendStickyBroadcast(p0: Intent?) {
        throw UnsupportedOperationException()
    }

    override fun enforceCallingPermission(p0: String?, p1: String?) {
        throw UnsupportedOperationException()
    }

    override fun peekWallpaper(): Drawable? {
        throw UnsupportedOperationException()
    }

    override fun getSystemService(p0: String?): Any? {
        throw UnsupportedOperationException()
    }

    override fun getDir(p0: String?, p1: Int): File? {
        throw UnsupportedOperationException()
    }

    override fun databaseList(): Array<out String>? {
        throw UnsupportedOperationException()
    }

    override fun createPackageContext(p0: String?, p1: Int): Context? {
        throw UnsupportedOperationException()
    }

    override fun enforceUriPermission(p0: Uri?, p1: Int, p2: Int, p3: Int, p4: String?) {
        throw UnsupportedOperationException()
    }

    override fun enforceUriPermission(p0: Uri?, p1: String?, p2: String?, p3: Int, p4: Int, p5: Int, p6: String?) {
        throw UnsupportedOperationException()
    }

    override fun removeStickyBroadcast(p0: Intent?) {
        throw UnsupportedOperationException()
    }
}