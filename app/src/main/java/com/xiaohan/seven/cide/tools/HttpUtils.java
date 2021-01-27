package com.xiaohan.seven.cide.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.xiaohan.seven.cide.interfaces.OnHttpGetFinishListener;
import com.xiaohan.seven.cide.interfaces.OnHttpPostFinishListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils
{
	public static final MediaType charset = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
	
	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			
			if (mNetworkInfo != null) {  
		    return mNetworkInfo.isAvailable();  
		    }  
			
		}
		return false;
	}
	
	public static boolean isWifiConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (mWiFiNetworkInfo != null) {
				return mWiFiNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	
	public static void GET(String url, final OnHttpGetFinishListener onFinish)
	{
		Request request = new Request.Builder()
		.url(url)
		.build();
		
		Call call = new OkHttpClient().newCall(request);
		
		//异步GET请求
		call.enqueue(new Callback(){

				@Override
				public void onFailure(Call p1, IOException p2)
				{
					// TODO: Implement this method
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					p2.printStackTrace(pw);
					onFinish.onGetError(sw.toString());
				}

				@Override
				public void onResponse(Call p1, Response p2) throws IOException
				{
					// TODO: Implement this method
					onFinish.onGetFinish(p2.body().string(), p2.code());
				}
			
		});
		
	}
	
	public static void POST(String url, Map<String,String> bodyMsg, final OnHttpPostFinishListener onFinish)
	{
		Map<String,String> formParams = bodyMsg;//传参 

		StringBuffer sb = new StringBuffer();
		//设置表单参数
		for (String key: formParams.keySet()) {                      
			sb.append(key+"="+formParams.get(key)+"&");
		}

		RequestBody body = RequestBody.create(charset, sb.toString());

		//创建请求
		Request request = new Request.Builder()
		.url(url)
		.post(body)
		.build();
		
		//异步POST请求
		Call call = new OkHttpClient().newCall(request);
		call.enqueue(new Callback(){

				@Override
				public void onFailure(Call p1, IOException p2)
				{
					// TODO: Implement this method
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					p2.printStackTrace(pw);
					onFinish.onPostError(sw.toString());
				}

				@Override
				public void onResponse(Call p1, Response p2) throws IOException
				{
					// TODO: Implement this method
					onFinish.onPostFinish(p2.body().string(), p2.code());
				}
			
		});
		
	}
}
