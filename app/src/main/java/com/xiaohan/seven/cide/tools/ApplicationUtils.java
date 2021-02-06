package com.xiaohan.seven.cide.tools;
import _0xf3fa7b._0xOo0aDd;
import _0xf3fa7b._0xcC0o_7;
import _0xf3fa7b._0xfc0Ooab;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import com.xiaohan.seven.cide.R;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public abstract class ApplicationUtils {
    //检测SD卡是否可用
    public static boolean SDisEnable(){
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }
    //检测SD卡总容量
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getSDSize(){
        try{
        if(SDisEnable()){
            StatFs sf = new StatFs(getSD());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                return sf.getBlockCountLong() * sf.getBlockSizeLong();
            }else{
                long  blockSize = sf.getBlockSize();
                long totalBlocks = sf.getBlockCount();
                return blockSize * totalBlocks;
            }
        }else{
            return 0;
        }
        }catch(Exception e){
            return 0;
        }
    }
    //获取剩余SD卡容量
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    public static long getFreeSDSize(){
        if (SDisEnable()){
            try{
            StatFs sf = new StatFs(getSD());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                return sf.getBlockSizeLong()*sf.getAvailableBlocksLong();}else{
                long  blockSize = sf.getBlockSize();
                long totalBlocks = sf.getBlockCount();
                
                return blockSize * totalBlocks;
            }
            }catch(Exception e){
                return 0;
            }
        }else{
            return 0;
        }
    }
    
    public static String getAssetsFileText(String fileName, Context context) {
		String result = "";
        try {
			InputStream is = context.getAssets().open(fileName);
			int lenght = is.available();  
            byte[]  buffer = new byte[lenght];  
            is.read(buffer);  
            result = new String(buffer, "UTF8"); 
			
		} catch (IOException e) {}  
		
		return result;
	}
	
	public static Bitmap getAssetsPicture(String fileName, Context context) {
		Bitmap bitmap = null;
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);//filename是assets目录下的图片名
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
		
		
		
	}
	
	public static int getWidth(AppCompatActivity context) {

        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return  metrics.widthPixels;

    }

    public static int getHeight(AppCompatActivity context) {

        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;

    }
    
	/**
     * 获取文件大小
     * 
     * @param size
     * @return
     */
    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            // 因为如果以MB为单位的话，要保留最后1位小数，
            // 因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }
	
	public static byte[] getAssetsLibFileText(String fileName, Context context) {
		byte[] text = null;
        try {
            //获取Assets里文件的FileInputStream输入流
            InputStream fis = context.getAssets().open(fileName);
            //创建文件总字节大小的byte数组
            text = new byte[fis.available()];
            //用输入流读取字节数组
            fis.read(text);
           
        } catch (IOException e) {}
        //返回字节
        return text;
	}
    
    public static String getTextFromSD(File file) {
        String text = "";
        try {
            //获取Assets里文件的FileInputStream输入流
            FileInputStream fis = new FileInputStream(file);
            //创建文件总字节大小的byte数组
            byte[] by = new byte[fis.available()];
            //用输入流读取字节数组
            fis.read(by);
            //创建为字符串
            text = new String(by);
        } catch (IOException e) {}
        //返回字符串
        return text;
    }
    
    public static void saveFileToSD(String path, String message) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(message);
            bw.close();
        } catch (IOException e) {
            
        }
    }
    
    public static void encodeString(String p1, String p2) throws IOException {
        FileInputStream fis = new FileInputStream(p1);
        byte[] b = new byte[fis.available()];

        fis.read(b);
        fis.close();
        
        for(int i = 0;i<b.length;i++){
            b[i] += (byte)1;
        }
        
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(p2));
        oos.writeObject(new _0xOo0aDd(new _0xfc0Ooab(), b, "" + System.currentTimeMillis()));
        oos.close();
    }
    
    public static byte[] decodeString(String p1) throws IOException, ClassNotFoundException {
        


            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(p1));
            _0xOo0aDd obj = (_0xOo0aDd)ois.readObject();
            
            List<_0xcC0o_7> list = obj.get_0xfa0oO();
            _0xcC0o_7 cc = list.get(0);
            byte[] by = cc.get_0xf0OolI();
            
            for(int i = 0;i<by.length;i++){
                by[i] -= ((byte)1);
            }
            
            ois.close();

            return by;
    }
    
    public static boolean deleteDirectory(String sPath) {
        Boolean flag = false;
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith("/")) {
            sPath = sPath + "/";
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
    
    //解压assets
    public static void unZip(Context context, String assetName,
                             String outputDirectory) throws IOException {
        //创建解压目标目录
        File file = new File(outputDirectory);
        //如果目标目录不存在，则创建
        if (!file.exists()) {
            file.mkdirs();
        }
        InputStream inputStream = null;
        //打开压缩文件
        inputStream = context.getAssets().open(assetName);
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        //读取一个进入点
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        //使用1Mbuffer
        byte[] buffer = new byte[1024 * 1024];
        //解压时字节计数
        int count = 0;
        //如果进入点为空说明已经遍历完所有压缩包中文件和目录
        while (zipEntry != null) {
            //如果是一个目录
            if (zipEntry.isDirectory()) {
                //String name = zipEntry.getName();
                //name = name.substring(0, name.length() - 1);
                file = new File(outputDirectory + File.separator + zipEntry.getName());
                file.mkdir();
            } else {
                //如果是文件
                file = new File(outputDirectory + File.separator
                                + zipEntry.getName());
                //创建该文件
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                while ((count = zipInputStream.read(buffer)) > 0) {
                    fileOutputStream.write(buffer, 0, count);
                }
                fileOutputStream.close();
            }
            //定位到下一个文件入口
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.close();
    }
    
    public static Boolean deleteFile(String path) {
        return new File(path).delete();
    }
    
    //获取SD卡目录
    public static String getSD(){
        return Environment.getExternalStorageDirectory().toString();
    }
    
    //获取工作目录路径
    public static String getWorkPath(Activity activity) {
        return Environment.getExternalStorageDirectory().getAbsoluteFile().toString() + "/" + activity.getString(R.string.app_name);
    }
    
    //获取工作目录名称
    public static String getWorkName(Activity activity) {
        return (activity.getString(R.string.app_name)).toString();
    }
    
}
