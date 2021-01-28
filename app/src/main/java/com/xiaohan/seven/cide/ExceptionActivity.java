package com.xiaohan.seven.cide;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import android.view.MenuItem;



public class ExceptionActivity extends BaseActivity {
	
    private final String STORAGE = android.os.Environment.getExternalStorageDirectory().getAbsoluteFile().toString() + "/";
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_error);
		
		Toolbar toolbar = findViewById(R.id.error_toolbar);
		setSupportActionBar(toolbar);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		String error = intent.getStringExtra("ERROR");
		
		//Thread error_thread = (Thread) TpObjects.values[0];
		
		//Throwable error_main = (Throwable) TpObjects.values[1];
        
		TextView textView = findViewById(R.id.activity_error_TextView);
		textView.setTextColor(Color.RED);
		textView.setText("<Error>" + error.replace("... 9 more", "").replace("... 11 more", ""));
		textView.setTextIsSelectable(true);
		
        final Button btn = findViewById(R.id.activity_errorButton);
        btn.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    new Thread(new Runnable(){

                            @Override
                            public void run() {
                                try{
                                final Boolean isDelete = deleteDirectory(STORAGE + "CideCompat");
                                    
                                     btn.post(new Runnable(){

                                        @Override
                                        public void run() {
                                        
                                if(isDelete){
                                Toast.makeText(ExceptionActivity.this, "删除成功,即将重启...", 0).show();
                                }else{
                                Toast.makeText(ExceptionActivity.this, "删除失败,正在重启...", 0).show();
                                }
                                }});
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {}
                                onBackPressed();
								}catch(Throwable e) {
								onBackPressed();
								}
                            }
                        }).start();
                }
            });
        
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {
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

	@Override
	public void onBackPressed() {
		android.os.Process.killProcess(android.os.Process.myPid());
		super.onBackPressed();
	}
    
}
