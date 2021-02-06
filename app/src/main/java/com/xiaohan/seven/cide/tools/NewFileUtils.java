package com.xiaohan.seven.cide.tools;
import android.content.Context;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.google.gson.Gson;
import com.xiaohan.seven.cide.data.FileData;
import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.BufferedOutputStream;
import com.xiaohan.seven.cide.data.AndroidData;

public abstract class NewFileUtils {
    
     
    public static String newAndJSProject(Context context, String name, String path, String packageName, String versionCode, String versionName, Boolean hook, Boolean cide, Boolean request) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());
        
        new File(path + "/" + name).mkdirs();
		File file = new File(path + "/" + name + "/" + name + ".as");
        File cideFile = new File(path + "/" + name + "/" + "CideCompat" + ".as");
        File httpRequestFile = new File(path + "/" + name + "/" + "HttpRequest" + ".as");
        
        try {
            
            file.createNewFile();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write("/**\n" +
            " *类型:AndJS" + "\n" +
            " *名称:" + name + "\n" +
            " *Time:" + formatDate.format(new Date()) +
            " */\n");
            bw.flush();
            
            File iconFile = new File(path + "/" + name + "/" + "icon.png");
            iconFile.createNewFile();
            
            Bitmap picture = ApplicationUtils.getAssetsPicture("html/icon.png", context);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            picture.compress(Bitmap.CompressFormat.PNG, 100, baos);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(iconFile));
            bos.write(baos.toByteArray());
			bos.close();
            
            
            String[] size = new String[4];
            
            size[0] = ApplicationUtils.getPrintSize(file.length());
            
            if(cide){
                String result = "";
                try {
                    InputStream is = context.getAssets().open("CideCompat.as");
                    int lenght = is.available();  
                    byte[]  buffer = new byte[lenght];  
                    is.read(buffer);
                    result = new String(buffer, "UTF8"); 
                    BufferedWriter bw2 = new BufferedWriter(new FileWriter(cideFile, true));
                    bw2.write(result);
                    bw2.close();
                    size[1] = ApplicationUtils.getPrintSize(cideFile.length());
                } catch (IOException e) {}
            }
            
            if(request){
                String result = "";
                try {
                    InputStream is = context.getAssets().open("HttpRequest.as");
                    int lenght = is.available();  
                    byte[]  buffer = new byte[lenght];  
                    is.read(buffer);  
                    result = new String(buffer, "UTF8"); 
                    BufferedWriter bw3 = new BufferedWriter(new FileWriter(httpRequestFile, true));
                    bw3.write(result);
                    bw3.close();
                    String httpSize = ApplicationUtils.getPrintSize(httpRequestFile.length());
                    if(size[1] == null){
                        size[1] = httpSize;
                    }else{
                        size[2] = httpSize;
                    }
                } catch (IOException e) {}
            }
            
            size[size.length - 1] = ApplicationUtils.getPrintSize(iconFile.length());
            
            File fileType =  new File(path + "/" + name + "/" + "build.json");
			fileType.createNewFile();
            
            AndroidData mAndroidData = new AndroidData();
            mAndroidData.setName(name);
            mAndroidData.setActivities(new String[]{name});
            mAndroidData.setType("andjs");
            mAndroidData.setSize(size);
            mAndroidData.setTime(new String[]{formatDate.format(new Date()), formatDate.format(new Date())});
            mAndroidData.setPaths(new String[]{file.toString(), iconFile.toString()});
            mAndroidData.setPackageName(packageName);
			mAndroidData.setVersionCode(versionCode);
			mAndroidData.setVersionName(versionName);
            BufferedWriter bwJson = new BufferedWriter(new FileWriter(fileType, true));
            bwJson.write(new Gson().toJson(mAndroidData).toString());
			bwJson.close();
            
            if(hook){
                bw.write("function TimeTick(){\n\n};\n");
                bw.flush();
            }
            
            
            
            
        } catch (IOException e) {}
        
        return file.toString();
    }
    
    public static String newModpeProject(Context context, String name, String path, Boolean ctx) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());

        new File(path + "/" + name).mkdirs();
		File file = new File(path + "/" + name + "/" + name + ".js");
        try {
            
            file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write("/**\n" +
                     " *Name: " + name + "\n" +
                     " *Time: " + formatDate.format(new Date()) + "\n" +
                     " *Type: " + "AndJS" + "\n" +
                     " */\n");
            bw.flush();

            if(ctx){
                bw.write("var ctx = com.mojang.minecraftpe.MainActivity.currentMainActivity.get();\n");
                bw.flush();
            }

            
        } catch (IOException e) {}

        return file.toString();
    }
	
	public static String newHtmlProject(Context context, String name, String path) {
		
		new File(path + "/" + name).mkdirs();
		
		File htmlFile = new File(path + "/" + name + "/" + "index.html");
		
		try {
			
			
			File iconFile = new File(path + "/" + name + "/" + "icon.png");
			iconFile.createNewFile();
			
			File fileType =  new File(path + "/" + name + "/" + "build.json");
			fileType.createNewFile();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(htmlFile, true));
			bw.write(ApplicationUtils.getAssetsFileText("html/$index.html", context));
			bw.close();
			
			Bitmap picture = ApplicationUtils.getAssetsPicture("html/icon.png", context);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			picture.compress(Bitmap.CompressFormat.PNG, 100, baos);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(iconFile));
			bos.write(baos.toByteArray());
			bos.close();
			
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());
			
			FileData mFileType = new FileData();
			mFileType.setName(name);
			mFileType.setType("html");
			mFileType.setPaths(new String[]{htmlFile.toString(), iconFile.toString()});
			mFileType.setSize(new String[]{ApplicationUtils.getPrintSize(htmlFile.length()), ApplicationUtils.getPrintSize(iconFile.length())});
			mFileType.setTime(new String[]{formatDate.format(new Date()), formatDate.format(new Date())});
			
			BufferedWriter bwJson = new BufferedWriter(new FileWriter(fileType, true));
			bwJson.write(new Gson().toJson(mFileType).toString());
			bwJson.close();
			
		} catch (IOException e) {}

		return htmlFile.toString();
	}
    
    public static String  newAnmlProject(Context context, String name, String path, String packageName, String versionCode, String versionName, Boolean jquary, Boolean vue, Boolean mdui) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.getDefault());

        new File(path + "/" + name).mkdirs();
        File file = new File(path + "/" + name + "/index.html");
        File cideFile = new File(path + "/" + name + "/" + "CideCompat" + ".js");
        
        try {

            file.createNewFile();
            
            String text = ApplicationUtils.getAssetsFileText("html/$index.anml", context);
            text.replace("$create_project_name$", name);
            text.replace("$create_message$", "\n" + 
            "Name: " + name + "\n" +
            "Time: " + formatDate.format(new Date()) + "\n" +
            "Type: " + "Anml" + "\n"
            );
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(text);
            bw.flush();

            File iconFile = new File(path + "/" + name + "/" + "icon.png");
            iconFile.createNewFile();

            Bitmap picture = ApplicationUtils.getAssetsPicture("html/icon.png", context);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            picture.compress(Bitmap.CompressFormat.PNG, 100, baos);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(iconFile));
            bos.write(baos.toByteArray());
            bos.close();


            String[] size = new String[4];

            size[0] = ApplicationUtils.getPrintSize(file.length());

            if(jquary){
                
            }

            if(vue){
                
            }

            size[size.length - 1] = ApplicationUtils.getPrintSize(iconFile.length());

            File fileType =  new File(path + "/" + name + "/" + "build.json");
            fileType.createNewFile();
            
            cideFile.createNewFile();
            
            AndroidData mAndroidData = new AndroidData();
            mAndroidData.setName(name);
            mAndroidData.setActivities(new String[]{name});
            mAndroidData.setType("anml");
            mAndroidData.setSize(size);
            mAndroidData.setTime(new String[]{formatDate.format(new Date()), formatDate.format(new Date())});
            mAndroidData.setPaths(new String[]{file.toString(), iconFile.toString()});
            mAndroidData.setPackageName(packageName);
            mAndroidData.setVersionCode(versionCode);
            mAndroidData.setVersionName(versionName);
            BufferedWriter bwJson = new BufferedWriter(new FileWriter(fileType, true));
            bwJson.write(new Gson().toJson(mAndroidData).toString());
            bwJson.close();

            if(mdui){
                file.delete();
                ApplicationUtils.unZip(context, "html/MDUI.zip", path + "/" + name);
            }




        } catch (IOException e) {}

        return file.toString();
    }
    
    
    
}
