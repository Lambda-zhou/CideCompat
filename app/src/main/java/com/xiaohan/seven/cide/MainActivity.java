package com.xiaohan.seven.cide;

import _0xf3fa7b._0xfc0Ooab;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.tencent.smtt.sdk.WebView;
import com.xiaohan.seven.cide.adapter.MainProjectAdapter;
import com.xiaohan.seven.cide.adapter.MainViewAdapter;
import com.xiaohan.seven.cide.adapter.OtherAdapter;
import com.xiaohan.seven.cide.adapter.ProjectAdapter;
import com.xiaohan.seven.cide.data.AndroidData;
import com.xiaohan.seven.cide.data.FileData;
import com.xiaohan.seven.cide.dialog.AppCompatDialog;
import com.xiaohan.seven.cide.dialog.NewProject;
import com.xiaohan.seven.cide.error.ApplicationModifiedException;
import com.xiaohan.seven.cide.interfaces.OnCreateProjectListener;
import com.xiaohan.seven.cide.interfaces.OnHttpPostFinishListener;
import com.xiaohan.seven.cide.interfaces.WebViewJavaScriptInterface;
import com.xiaohan.seven.cide.language.ProjectLanguage;
import com.xiaohan.seven.cide.list.MainProject;
import com.xiaohan.seven.cide.list.Projects;
import com.xiaohan.seven.cide.list.Study;
import com.xiaohan.seven.cide.services.ApplicationService;
import com.xiaohan.seven.cide.settings.ApplicationGlobalSettings;
import com.xiaohan.seven.cide.tools.ApplicationUtils;
import com.xiaohan.seven.cide.tools.HttpUtils;
import com.xiaohan.seven.cide.tools.Permission;
import com.xiaohan.seven.cide.view.AppCompatToast;
import com.xiaohan.seven.cide.view.AppCompatWebView;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpEntity;
import android.graphics.drawable.Drawable;
import java.io.ByteArrayOutputStream;
import java.io.BufferedOutputStream;
import dalvik.system.DexClassLoader;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import java.io.FileWriter;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.RequiresApi;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeJavaObject;
import android.provider.Settings;

public class MainActivity extends BaseActivity implements OnItemLongClickListener {




    private final String STORAGE = android.os.Environment.getExternalStorageDirectory().getAbsoluteFile().toString() + "/";

    private DrawerLayout leftDrawer;
    private FrameLayout leftLayout;
    private FloatingActionButton fab;
    private TextView list_sd;
    private TextView list_title;
    private ProjectAdapter adapter;
    private ProjectAdapter features;
    private ListView list_files;
    private ListView project_nav;
    private ViewPager mainPagers;
    private TabLayout tabLayout;
    private int width;
    private int height;
    private ImageView folderMore;
    private Boolean onlyShowJsFile = false;
    private File workPath = new File(STORAGE + "CideCompat" + "/" + "projects");
    private float scrollX = 0;
    private float scrollY = 0;
    private SharedPreferences preferences;
    private _0xfc0Ooab _0xfb0cfOo;
    public static MainActivity _this;
	private MainProjectAdapter mainProjectAdapter;
	private List<MainProject> mainProjectItem = new ArrayList<MainProject>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("AppDatas", MODE_PRIVATE);
        
        _this = this;
	    mainProjectAdapter = new MainProjectAdapter(this, mainProjectItem);
		setContentView(R.layout.activity_main);
        ApplicationGlobalSettings.setContext(_this);
        
        
        //startActivity(new Intent(this, TestActivity.class));
        
        if(!(new File(STORAGE + ".CideCompat").exists() && new File(STORAGE + ".CideCompat").isDirectory())){
			new File(STORAGE + ".CideCompat").mkdirs();
		}else{
			
		}
        
        autoSetProperty();
        
        DisplayMetrics metrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        this.width = metrics.widthPixels;
        this.height = metrics.heightPixels;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        /*ColorPickerDialog dialog =  new ColorPickerDialog(this, "标题", new ColorPickerDialog.OnColorChangedListener(){

                @Override
                public void colorChanged(int color) {
                    
                }
                
        });

        dialog.show();*/
        //new AppCompatDialog.Builder(this).show();


        startService(new Intent(this, ApplicationService.class));
        leftDrawer = findViewById(R.id.DrawerLayout);
        leftLayout = findViewById(R.id.left_layout);
        list_sd = findViewById(R.id.folderpath);
        list_title = findViewById(R.id.path);
        fab = findViewById(R.id.fab);
        list_files = findViewById(R.id.list_files);
        project_nav = findViewById(R.id.projects_list);
        folderMore = findViewById(R.id.foldermore);
        mainPagers = findViewById(R.id.activity_main_ViewPager);
        tabLayout = findViewById(R.id.activity_main_TabLayout);
        
        ApplicationGlobalSettings.setOrientation(preferences.getBoolean("runtime_orientation", false));
        

        if (testPermission()) {
            createFiles();
        }

        init();
        
        /*new AppCompatInputDialog(this)
        .setTitle("AppCompatInputDialog - test")
        .setMessage("输入数据:")
		.addInputView()
        .show();*/
        if (!(ApplicationUtils.getWorkName(this).equals("CideCompat"))) {
            throw new ApplicationModifiedException();
        } else if (!(getPackageName().equals("com.xiaohan.seven.cide"))) {
            throw new ApplicationModifiedException("Application package name error:" + getPackageName());
        }
        //null
        
        if(ApplicationGlobalSettings.getData("app_theme", false) || ApplicationGlobalSettings.app_theme){
            mainPagers.setBackgroundColor(Color.parseColor("#303131"));
            ApplicationGlobalSettings.app_theme = true;
        }
        
        ApplicationGlobalSettings.isUseLocalhost = ApplicationGlobalSettings.getData("use_localhost", false);
        
        new Thread(new Runnable(){

                @Override
                public void run() {
                    final File file = new File(ApplicationUtils.getSD() + "/" + "error.txt");
                    if(file.exists() && file.isFile()){
                    final String error = ApplicationUtils.getTextFromSD(file);
                    
                        mainPagers.post(new Runnable(){

                                @Override
                                public void run() {
                                    final AppCompatDialog dialog = new AppCompatDialog(MainActivity.this);
                                    dialog.setTitle("主线程遗忘try - catch?");
                                    dialog.setTitleColor(Color.BLACK);
                                    dialog.setMessage(error);
                                    dialog.setPostiveButton("关闭", new OnClickListener(){

                                            @Override
                                            public void onClick(View p1) {
                                                ApplicationUtils.deleteFile(file.getAbsoluteFile().toString());
                                                dialog.dismiss();
                                            }
                                            
                                    });
                                    dialog.setHeight((int)(dialog.getHeight() * 0.85));
                                    dialog.show();
                                }
                        });
                    }
                }
        
        }).start();

	}
    
    public static SharedPreferences getSharedPreferences() {
        return _this.preferences;
    }

    private void autoSetProperty() {

        new Thread(new Runnable(){

                @Override
                public void run() {



                    try {
                        FileInputStream fis = openFileInput("storagePath");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        fis.close();

                        ApplicationGlobalSettings.storagePath = new File(new String(buffer));

                    } catch (IOException e) {

                    } 
                }

            }).start();
    }

    private void updateFiles() {
        try {
            File[] files = ApplicationGlobalSettings.storagePath.listFiles();

            /*if(files == null || files.length == 0){
             new Permission(this).CheckPermission();
             Toast.makeText(getApplication(), "无法读取SD卡, 请开启读写权限", Toast.LENGTH_SHORT).show();
             return;
             }*/

            final List<Projects> item = new ArrayList<>();

            list_title.setText(ApplicationGlobalSettings.storagePath.getAbsoluteFile().toString());

            for (File file : files) {
                String name = file.getName();
                if (!file.isDirectory()) {

                    if (onlyShowJsFile) {

                        if (name.endsWith(".js")) {
                            item.add(new Projects(file.getName(), R.mipmap.js_file, file));
                        }

                    } else {
                        if (name.endsWith(".js")) {
                            item.add(new Projects(file.getName(), R.mipmap.js_file, file));
                        } else {
                            item.add(new Projects(file.getName(), R.mipmap.unknow_file, file));
                        }
                    }

                } else {
                    item.add(new Projects(file.getName(), R.drawable.ic_folder_open, file));
                }


            }

            adapter = new ProjectAdapter(this, R.layout.nav_projects, item);

            list_files.setAdapter(adapter);
            list_files.setOnItemClickListener(new OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                        Projects project = item.get(p3);
                        if (project.getFile().isDirectory()) {
                            ApplicationGlobalSettings.storagePath = project.getFile();
                            updateFiles();

                        } else {
                            openProjectFile(project.getFile());
                        }
                    }

                });

            Animation animUtils = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
            LayoutAnimationController lac = new LayoutAnimationController(animUtils);
            lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
            lac.setDelay(0.15f);
            list_files.setLayoutAnimation(lac);



            //if(scrollX != 0 && scrollY != 0){
            //list_files.scrollTo(0, (int)scrollY);
            //}

            list_files.setOnItemLongClickListener(this);
            list_files.setOnScrollListener(new OnScrollListener(){

                    @Override
                    public void onScrollStateChanged(AbsListView p1, int p2) {
                        if (p2 == OnScrollListener.SCROLL_STATE_IDLE) {
                            scrollX = list_files.getX();
                            scrollY = list_files.getY();
                        }
                    }

                    @Override
                    public void onScroll(AbsListView p1, int p2, int p3, int p4) {

                    }

                });



            folderMore.setClickable(true);
            folderMore.setOnClickListener(new OnClickListener(){

                    @Override
                    public void onClick(View p1) {
                        PopupMenu menu = new PopupMenu(MainActivity.this, folderMore);
                        menu.getMenuInflater().inflate(R.menu.popup_menu_only_show_project_file, menu.getMenu());
                        menu.show();
                        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                                @Override
                                public boolean onMenuItemClick(MenuItem p1) {
                                    switch (p1.getTitle().toString()) {
                                        case "只显示JS文件":
                                            if (onlyShowJsFile) {
                                                onlyShowJsFile = false;

                                            } else {
                                                onlyShowJsFile = true;

                                            }
                                            updateFiles();
                                            break;
                                    }
                                    return false;
                                }

                            });
                    }

                });
        } catch (Exception e) {

        }
        
        updateProjects();
        
    }



	public int getLocalVersion() {
		int localVersion = 1;
		try {
			PackageInfo packageInfo = MainActivity.this.getApplicationContext()
                .getPackageManager()
                .getPackageInfo(getPackageName(), 0);
			localVersion = packageInfo.versionCode;

		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return localVersion;
	}

	private void showUpdateDialog(String appUpdateMessage, final String appUpdateUrl) {
		AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
		alert.setTitle("发现新版本");
		alert.setMessage(Html.fromHtml(appUpdateMessage));
		alert.setCancelable(false);
		alert.setNegativeButton("更新", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(appUpdateUrl));
                        startActivity(intent);
                        finish();
                    } catch (Throwable e) {
                        Toast.makeText(MainActivity.this, "跳转失败:没有支持蓝奏云的浏览器,请使用蓝奏云下载或手动更新,网址:" + appUpdateUrl + "\n错误:" +  e.getCause().toString() + ".", Toast.LENGTH_LONG);
                    }
				}
			});
		alert.create();
		alert.show();
	}
    
    private Bitmap bitmap;
    
    private void addOtherItems(final String file, final String elementTitle, final List<Study> listItem, final OtherAdapter otherAdapter) {
        new Thread(new Runnable(){

                @Override
                public void run() {
                    try {
                        //传入文件名：language.xml；用来获取流  
                        InputStream is = getAssets().open(file);  
                        //首先创造：DocumentBuilderFactory对象  
                        DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();  
                        //获取：DocumentBuilder对象  
                        DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();  
                        //将数据源转换成：document 对象  
                        Document document = dBuilder.parse(is);  
                        //获取根元素  
                        Element element = document.getDocumentElement();  
                        //获取子对象的数值 读取lan标签的内容  
                        NodeList nodeList = element.getElementsByTagName(elementTitle);  

                        for (int i = 0; i < nodeList.getLength(); i++) {  
                            //获取对应的对象  
                            final Element item = (Element) nodeList.item(i);  
                            //获取name标签下的内容
                            final String name = item.getElementsByTagName("name").item(0).getTextContent();
                            //获取msg标签下的内容  
                            final String msg = item.getElementsByTagName("msg").item(0).getTextContent();
                            //获取code标签下的内容
                            final String code = item.getElementsByTagName("code").item(0).getTextContent();

                            final String language = item.getElementsByTagName("language").item(0).getTextContent();
                            
                            final String imagePath = item.getElementsByTagName("img").item(0).getTextContent();

                            final InputStream code_is = getAssets().open(code);

                            final byte[] by = new byte[code_is.available()];

                            code_is.read(by);

                            code_is.close();
                            
                            
                            
                            

                            MainActivity.this.runOnUiThread(new Runnable(){

                                    @Override
                                    public void run() {
                                        
                                        
                                        
                                        if(!imagePath.equals("null")){

                                            try {
                                                bitmap = BitmapFactory.decodeStream(getAssets().open(imagePath));
                                            } catch (IOException e) {}
                                        }
										
                                        listItem.add(new Study(name, msg, bitmap, language, new String(by)));

                                        otherAdapter.notifyDataSetChanged();
                                    }
                                });

                        }

                    } catch (Throwable e) {
                        throw new RuntimeException(e.toString());
                    }
                }

            }).start();
    }
    
    private int lengthx = -1;
    
    private Bitmap image2;
    
    private void addHttpOtherItems(final List<Study> listItem, final OtherAdapter otherAdapter) {
      {
        new Thread(new Runnable(){

                @Override
                public void run() {
                    
                    
                    Map<String, String> map = new HashMap<>();
                    map.put("admin", "2632588830");
                    map.put("api", "code");
                    map.put("name", "length.txt");
                    HttpUtils.POST("http://1101dsw.top/ycwj_nr.php", map, new OnHttpPostFinishListener(){

                            @Override
                            public void onPostFinish(String message, int code) {
                                try{
                                lengthx = Integer.parseInt(message);
                                    
                                }catch(Exception e){
                                    AppCompatToast.makeText(MainActivity.this, "无法链接服务器:源码加载失败!", 1, 1);
                                    
                                }
                            }

                            @Override
                            public void onPostError(String error) {
                                AppCompatToast.makeText(MainActivity.this, "无法链接服务器:源码加载失败!", 1, 1);
                            }
                    });
                    
                    
                    String firstName = "other";
                    
                    for(int i = 1;i<=lengthx;i++){
                    
                    Map<String, String> map2 = new HashMap<>();
                    map2.put("admin", "2632588830");
                    map2.put("api", "code");
                    map2.put("name", firstName + i + ".txt");
                    HttpUtils.POST("http://1101dsw.top/ycwj_nr.php", map2, new OnHttpPostFinishListener(){

                            @Override
                            public void onPostFinish(final String message, int code) {
                                
                                
                                
                                MainActivity.this.runOnUiThread(new Runnable(){
                                    
                                        
                                    
                                        @Override
                                        public void run() {
                                            
                                            final String[] splitMsg = message.split("�", -1);
                                            
                                            new Thread(new Runnable(){

                                                    @Override
                                                    public void run() {
                                                        if(!splitMsg[2].equals("null")){
                                                            DefaultHttpClient httpClient = new DefaultHttpClient();
                                                            HttpGet get = new HttpGet(splitMsg[2]);
                                                            try{
                                                                HttpResponse resp = httpClient.execute(get);
                                                                if(HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()){
                                                                    HttpEntity entity = resp.getEntity();
                                                                    InputStream in = entity.getContent();
                                                                    image2 = BitmapFactory.decodeStream(in);
                                                                    // 向handler发送消息，执行显示图片操作

                                                                }
                                                            }catch(Throwable e) {

                                                            }
                                                        }

                                                        runOnUiThread(new Runnable(){

                                                                @Override
                                                                public void run() {
                                                                    listItem.add(new Study(splitMsg[0], splitMsg[1], image2, splitMsg[3], splitMsg[4]));
                                                                    otherAdapter.notifyDataSetChanged();
                                                                }
                                                                
                                                        });
                                                        
                                                    }
                                                    
                                            }).start();
                                            
                                            
                                            
                                            
                                            
                                        }
                                    });

                            
                            }

                            @Override
                            public void onPostError(String error) {

                            }
                    });
                    
                    }
                    
                    
                }
                
        }).start();
    }}

    private void showAlert(final String text) {

        final WebView brown = new AppCompatWebView.Builder(this).newInstance();
        brown.setBackgroundColor(0);
        brown.setLayerType(View.LAYER_TYPE_SOFTWARE, null);  
        String html = text;

        final AppCompatDialog dialog = new AppCompatDialog(MainActivity.this);
        dialog.setTitle("软件公告");
        dialog.setHeight((int)(dialog.getHeight() * 0.85));
        brown.addJavascriptInterface(new WebViewJavaScriptInterface(this, dialog), "CideCompat");
        brown.loadDataWithBaseURL(null, html.replace("$text", text), "text/html", "UTF-8", null);
        dialog.setContentView(brown);
        dialog.setCancelable(false);
        dialog.setPostiveButton("确定", new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("" + getLocalVersion(), "" + getLocalVersion());
                    editor.putString("save_msg", text);
                    editor.commit();
                    dialog.dismiss();
                }
        });
        dialog.show();
    }


    private void ifShow(String isShowTip, String text) {
        if (isShowTip.equals("nil")) {
            showAlert(text);
            return;
        }

        if (!isShowTip.equals(getLocalVersion())) {
            showAlert(text);
        }

    }
	
	public void updateProjects() {
		new Thread(new Runnable(){

				@Override
				public void run() {
					mainProjectItem.clear();
					try{
					File[] files = workPath.listFiles();
					for(File file : files){
						if(file.isDirectory()){
							
							for(File theFile : file.listFiles()){
								if(theFile.isFile() && theFile.getName().equals("build.json")){
									
									
									
									/*Bitmap image;
									
									try {
										BufferedInputStream bs = new BufferedInputStream(new FileInputStream(datas.getPaths()[1]));
										Bitmap image = BitmapFactory.decodeStream(bs);
										bs.close();
									} catch (IOException e) {
										
									}
									*/
									try{
                                    Gson gson = new Gson();
                                    FileData datas = gson.fromJson(ApplicationUtils.getTextFromSD(theFile), FileData.class);
									MainProject project = new MainProject(R.drawable.ic_launcher_foreground, datas.getName(), datas.getSize()[0] ,datas.getTime()[0], "html");
									project.setPaths(datas.getPaths());
									mainProjectItem.add(project);
									}catch(Throwable e2){
										Gson gson2 = new Gson();
                                        AndroidData datas2 = gson2.fromJson(ApplicationUtils.getTextFromSD(theFile), AndroidData.class);
										
                                        MainProject project2 = new MainProject(R.drawable.ic_launcher_foreground, datas2.getName(), datas2.getSize()[0], datas2.getTime()[0], "andjs");
                                        project2.setPaths(datas2.getPaths());
                                        mainProjectItem.add(project2);
									}
									
									
									runOnUiThread(new Runnable(){

											@Override
											public void run() {
												mainProjectAdapter.notifyDataSetChanged();
											}
											
								    });
								}
							}

						}
					}
					}catch(Throwable e){
						e.getMessage();
					}
				}
				
        }).start();
		
		
	}
    
    private String out;
    
    private String _out;
    
    private String rootDir;
    
    public void setOutPutPath(String path){
        this.out = path;
        _setOutPutPath(_getRootDir()+"/"+path);
    }
    public String getOutPutPath(){
        return "/storage/emulated/0/.CreateJS/data/Project/App_V7.cip";
    }
    
    public void _setOutPutPath(String out){//设置工程出口
        this._out = out;
    }
    
    public String _getOutPath(){//获取工程出口
        return "/storage/emulated/0/projects/a.apk";
    }
    
    public String _getRootDir(){
        return "/storage/emulated/0/projects";
    }

    private void init() {

        final String isShowTip = preferences.getString("" + getLocalVersion(), "nil");

        Map<String,String> tipBody = new HashMap<>();
        tipBody.put("file", "ycgg");
        tipBody.put("admin", "2632588830");
        tipBody.put("api", "code");

        HttpUtils.POST("http://1101dsw.top/echo.php", tipBody, new OnHttpPostFinishListener(){

                @Override
                public void onPostFinish(final String message, int code) {
                    if (!message.equals(preferences.getString("save_msg", "nil"))) {
                        runOnUiThread(new Runnable(){

                                @Override
                                public void run() {
                                    ifShow(isShowTip, message);
                                }
                            });


                    }

                }

                @Override
                public void onPostError(String error) {

                }
            });



        final ArrayList<View> pages = new ArrayList<View>();
        View view = getLayoutInflater().inflate(R.layout.page_1, null);
		final SwipeRefreshLayout srl1 = view.findViewById(R.id.page_1_SwipeRefreshLayout);
        srl1.setColorScheme(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
		
		Map<String,String> body = new HashMap<>();
		body.put("file", "ycgx");
		body.put("admin", "2632588830");
		body.put("api", "code");
		HttpUtils.POST("http://1101dsw.top/echo.php", body, new OnHttpPostFinishListener(){

				@Override
				public void onPostFinish(String message, int code) {
					if (code == 200) {
						String[] formatMessage = message.replace("软件版本:(", "").replace("更新内容:(", "").replace("更新链接:(", "").split("\\)");
						final String appVersionCode = formatMessage[0];
						final String appUpdateMessage = formatMessage[1];
						final String appUpdateUrl = formatMessage[2];

						MainActivity.this.runOnUiThread(new Runnable(){

								@Override
								public void run() {
									int versionCode = getLocalVersion();

									if (Integer.parseInt(appVersionCode) > versionCode) {
										AppCompatToast.makeText(MainActivity.this, "发现新版本", 1, 1);
										showUpdateDialog(appUpdateMessage, appUpdateUrl);

									}
								}
                            });



					}
				}

				@Override
				public void onPostError(String error) {

				}
            });

		final RecyclerView lv1 = view.findViewById(R.id.page_1ListView);
        lv1.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        //垂直方向
        mLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //给RecyclerView设置布局管理器
        lv1.setLayoutManager(mLayoutManager);
		mainProjectAdapter.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1) {
					int position = lv1.getChildAdapterPosition(p1);
					MainProject project = mainProjectItem.get(position);
					openProjectFile(new File(project.getPaths()[0]));
				}
				
		});
        mainProjectAdapter.setOnLongClickListener(new OnLongClickListener(){

                @Override
                public boolean onLongClick(final View p) {
                    try{
                    ((Vibrator)getSystemService(android.content.Context.VIBRATOR_SERVICE)).vibrate(40);
                    }catch(Exception e){
                        AppCompatToast.makeText(MainActivity.this, "没有震动权限:" + e.toString(), 1, 1);
                    }
                    PopupMenu menu = new PopupMenu(MainActivity.this, p);
                    menu.getMenuInflater().inflate(R.menu.project_popup_menu, menu.getMenu());
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                            
                            @Override
                            public boolean onMenuItemClick(MenuItem p1) {
                                switch(p1.getTitle().toString()){
                                    case "打包":
                                        //test code
                                        //如何生成 key，具体方法见KeyHelpe
										int _position = lv1.getChildAdapterPosition(p);
										final MainProject _project = mainProjectItem.get(_position);
										final AndroidData datas = new Gson().fromJson(ApplicationUtils.getTextFromSD(new File(new File(_project.getPaths()[0]).getParent() + "/build.json")), AndroidData.class);
										
                                        if(_project.getType().equals("andjs")){
										
										new Thread(new Runnable(){
											
											private String path = "";
											
												@Override
												public void run() {
													try {
														InputStream is = getAssets().open("classes.dex");
														byte[] msg = new byte[is.available()];
														is.read(msg);
														is.close();

														BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("/storage/emulated/0/.CideCompat/classes.dex"));
														bos.write(msg);
														bos.close();

														byte[] bytes = ApplicationUtils.getAssetsLibFileText("CideCompat-NoSigner_1.0.apk", MainActivity.this);
														BufferedOutputStream bos2 = new BufferedOutputStream(new FileOutputStream("/storage/emulated/0/.CideCompat/CideCompat-NoSigner_1.0.apk"));
														bos2.write(bytes);
														bos2.close();

														String str = "var ctx = __javaContext__;var a = new Packages.dalvik.system.DexClassLoader('/storage/emulated/0/.CideCompat/classes.dex',ctx.getDir('dex',0).getAbsolutePath(),null,ctx.getClassLoader()).loadClass('com.xiaohan.seven.apkpack.AndJSBuilder');var b = a.getConstructors()[0].newInstance( '" + datas.getName() + "',[";
														str += "'" + datas.getPaths()[0] + "',";
														for(File file : new File(_project.getPaths()[0]).getParentFile().listFiles()){
															//排除文件
															if(!file.getName().equals("CideCompat.as") && !file.getName().equals("main.as") && !file.getName().equals("icon.png") && !file.getName().equals("build.json") && !file.getName().equals(datas.getName() + ".as")){
																str += "'" + file.getAbsoluteFile().toString() + "',";
															}
														}


														str = str.substring(0, str.length() - 1);

														final Context ctx = Context.enter();
														Scriptable scope = ctx.initStandardObjects();
														ctx.setOptimizationLevel(-1);
														ctx.setLanguageVersion(Context.VERSION_ES6);
														ScriptableObject.putConstProperty(scope, "__javaContext__", Context.javaToJS(_this, scope));
														ScriptableObject.putConstProperty(scope, "__javaLoader__", Context.javaToJS(_this.getClass().getClassLoader(), scope));
														str += "], '" + datas.getPackageName() + "', '" + datas.getVersionCode() + "', '" + datas.getVersionName() + "');function getPath(){return String(b.getTempFile().toString());};";
														ctx.evaluateString(scope, str, "打包", 1, null);
														Object object = scope.get("getPath", scope);
														if(object instanceof Function){
															Function function = (Function)object;
															path = Context.toString(function.call(ctx, scope, scope, new Object[]{}));
														}
														ctx.exit();
														AppCompatToast.makeText(MainActivity.this, "打包成功,正在安装", 1, 1);
														runOnUiThread(new Runnable(){

																@Override
																public void run() {
																	startInstallActivity(new File(path));
																	
																}
																
														});
														
													} catch (Throwable e) {
														AppCompatToast.makeText(MainActivity.this, "打包失败:" + e.toString(), 1, 1);
													}
												}
												
										}).start();
										}else if(_project.equals("anml")){
                                            
                                        }else{
                                            AppCompatToast.makeText(MainActivity.this, "不支持的项目无法打包", 1, 1);
                                        }
                                        

                                    break;
                                    
                                    case "删除":
                                        int position = lv1.getChildAdapterPosition(p);
                                        MainProject project = mainProjectItem.get(position);
                                        String name = project.getTitle();
                                        File[] files = workPath.listFiles();
                                        for(File file : files){
                                            if(file.isDirectory() && file.getName().equals(name)){
                                                ApplicationUtils.deleteDirectory(file.toString());
                                                updateFiles();
                                                updateProjects();
                                            }
                                        }
                                    break;
                                }
                                return false;
                            }
                            
                    });
                    menu.show();
                    return false;
                }
        });
        updateProjects();
		lv1.setAdapter(mainProjectAdapter); 
        
		pages.add(view);
		
		srl1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

                @Override
                public void onRefresh() {
                    srl1.setRefreshing(true);
                    new Handler().postDelayed(new Runnable(){

                            @Override
                            public void run() {
                                updateProjects();
                                srl1.setRefreshing(false);
                            }

						}, 1400);
                }

			});
        
        View view2 = getLayoutInflater().inflate(R.layout.page_2, null);
        
        final SwipeRefreshLayout srl2 = view2.findViewById(R.id.page_2_SwipeRefreshLayout);
        srl2.setColorScheme(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
		final RecyclerView lv2 = view2.findViewById(R.id.page_2ListView);
        lv2.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(this);
        mLayoutManager2.setOrientation(OrientationHelper.VERTICAL);
        lv2.setLayoutManager(mLayoutManager2);
		final List<Study> items2 = new ArrayList<Study>();

        final OtherAdapter otherAdapter2 = new OtherAdapter(MainActivity.this, R.layout.nav_study_card, items2);
        otherAdapter2.setOnItemClickListener(new OtherAdapter.OnItemClickListener(){

        @Override
        public void onItemClick(Study p1, View p2, int p3) {
            Study study = p1;
            Intent intent = new Intent(MainActivity.this, StudyActivity.class);
            intent.putExtra("study_code", study.getCode());
            intent.putExtra("study_language", study.getLanguage());
            startActivity(intent);
        }
        
        });
        
		lv2.setAdapter(otherAdapter2);
		addOtherItems("code.xml", "item", items2, otherAdapter2);
        addHttpOtherItems(items2, otherAdapter2);
        pages.add(view2);
        
        
        srl2.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

                @Override
                public void onRefresh() {
                    srl2.setRefreshing(true);
                    new Handler().postDelayed(new Runnable(){

                            @Override
                            public void run() {
                                items2.clear();
                                addOtherItems("code.xml", "item", items2, otherAdapter2);
                                addHttpOtherItems(items2, otherAdapter2);
                                srl2.setRefreshing(false);
                            }
                            
                    }, 1400);
                }
                
        });
        
        

        View view3 = getLayoutInflater().inflate(R.layout.page_3, null);
		final SwipeRefreshLayout srl3 = view3.findViewById(R.id.page_3_SwipeRefreshLayout);
		srl3.setColorScheme(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_light);
		
        final RecyclerView lv3 = view3.findViewById(R.id.page_3ListView);
        lv3.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager3 = new LinearLayoutManager(this);
        mLayoutManager3.setOrientation(OrientationHelper.VERTICAL);
        lv3.setLayoutManager(mLayoutManager3);
        final List<Study> items3 = new ArrayList<Study>();

		final OtherAdapter otherAdapter3 = new OtherAdapter(MainActivity.this, R.layout.nav_study_card, items3);
        otherAdapter3.setOnItemClickListener(new OtherAdapter.OnItemClickListener(){

                @Override
                public void onItemClick(Study p1, View p2, int p3) {
                    Study study = items3.get(p3);
                    Intent intent = new Intent(MainActivity.this, StudyActivity.class);
                    intent.putExtra("study_code", study.getCode());
                    intent.putExtra("study_language", study.getLanguage());
                    startActivity(intent);
                }
                
        });
		lv3.setAdapter(otherAdapter3);
		addOtherItems("study.xml", "item", items3, otherAdapter3);
		
        pages.add(view3);
		srl3.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

				@Override
				public void onRefresh() {
					srl3.setRefreshing(true);
					new Handler().postDelayed(new Runnable(){

                            @Override
                            public void run() {
                                items3.clear();
                                addOtherItems("study.xml", "item", items3, otherAdapter3);
                                srl3.setRefreshing(false);
                            }

					}, 1400);
				}
				
		});


        ArrayList<String> pagesTitle = new ArrayList<String>();
        pagesTitle.add("项目");
        pagesTitle.add("源码");
        pagesTitle.add("教程");

        mainPagers.setAdapter(new MainViewAdapter(pages, pagesTitle));





        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    //tab被选的时候回调
                    mainPagers.setCurrentItem(tab.getPosition(), true);
					if(tab.getPosition() ==  0){
						updateProjects();
					}
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    //tab未被选择的时候回调
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    //tab重新选择的时候回调
                }
            });
        tabLayout.setupWithViewPager(mainPagers);

        leftDrawer.setScrimColor(Color.TRANSPARENT);
        if (ApplicationUtils.SDisEnable()) {

            list_sd.setText(
                String.format("剩余 %s GB / ", Formatter.formatFileSize(this, ApplicationUtils.getFreeSDSize()) + "") +
                String.format("总共 %s GB", Formatter.formatFileSize(this, ApplicationUtils.getSDSize()) + "")
            );

        } else {
            list_sd.setText(
                String.format("剩余 %s GB / ", "nil") +
                String.format("总共 %s GB", "nil")
            );
        }
        final List<Projects> navs = new ArrayList<>();
        navs.add(new Projects("返回上级", R.drawable.ic_launcher));
        navs.add(new Projects("新建工程", R.drawable.ic_launcher));
        features = new ProjectAdapter(this, R.layout.nav_projects, navs);
        project_nav.setAdapter(features);
        project_nav.setOnItemClickListener(new OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4) {
                    Projects nav = navs.get(p3);
                    switch (nav.getName()) {
                        case "返回上级":
                            //保存当前路径
                            File afterPath = ApplicationGlobalSettings.storagePath;
                            try {
                                //访问路径的父文件夹进行赋值
                                ApplicationGlobalSettings.storagePath = new File(ApplicationGlobalSettings.storagePath.getParent());
                                //更新文件显示
                                updateFiles();
                            } catch (Exception e) {
                                //权限拒绝就不进行访问
                                ApplicationGlobalSettings.storagePath = afterPath;
                                updateFiles();
                            }


                            break;

                        case "新建工程":
                            if (!ApplicationGlobalSettings.storagePath.toString().equals(workPath.toString())) {
                                Snackbar.make(getWindow().getDecorView(), "这个路径不是工作目录,文件可能丢失,您确定要在这个目录下创建项目吗?", Snackbar.LENGTH_LONG)
                                    .setAction("确定", new OnClickListener(){

                                        @Override
                                        public void onClick(View p1) {
                                            try {
                                                NewProject newProject =  new NewProject(MainActivity.this);
                                                newProject.setOnCreateProjectListener(new OnCreateProjectListener(){

                                                        @Override
                                                        public void onCreate(String path) {
                                                            updateFiles();
															updateProjects();
                                                            openProjectFile(ApplicationGlobalSettings.storagePath);
                                                        }

                                                    });
                                                newProject.show(ApplicationGlobalSettings.storagePath.toString());
                                            } catch (Exception e) {
                                                Intent intent = new Intent(MainActivity.this, ExceptionActivity.class);
                                                intent.putExtra("ERROR", e.toString());
                                                //startActivity(intent);
                                                Toast.makeText(MainActivity.this, "暂时不支持, 请手动创建项目", Toast.LENGTH_LONG).show();
                                            }
                                        }

                                    })
                                    .show();

                                return;
                            }
                            try {
                                NewProject project = new NewProject(MainActivity.this);
                                project.setOnCreateProjectListener(new OnCreateProjectListener(){

                                        @Override
                                        public void onCreate(String path) {
                                            updateFiles();
                                            openProjectFile(new File(path));
                                        }

                                    });
                                project.show(ApplicationGlobalSettings.storagePath.toString());


                            } catch (Exception e) {
                                Intent intent = new Intent(MainActivity.this, ExceptionActivity.class);
                                intent.putExtra("ERROR", e.toString());
                                //startActivity(intent);
                                Toast.makeText(MainActivity.this, "暂时不支持, 请手动创建项目", Toast.LENGTH_LONG).show();

                            }
                            break;
                    }
                }

            });
        //更新文件显示
        updateFiles();


        //设置侧滑监听
        leftDrawer.setDrawerListener(new DrawerLayout.DrawerListener(){

                @Override
                public void onDrawerSlide(View p1, float p2) {
                }

                @Override
                public void onDrawerOpened(View p1) {
                    //更新文件
                    //updateFiles();
                    //判断是否有内置SD卡
                    if (ApplicationUtils.SDisEnable()) {
                        //显示剩余与总共的空间
                        list_sd.setText(
                            String.format("剩余 %s GB / ", Formatter.formatFileSize(MainActivity.this, ApplicationUtils.getFreeSDSize()) + "") +
                            String.format("总共 %s GB", Formatter.formatFileSize(MainActivity.this, ApplicationUtils.getSDSize()) + "")
                        );

                    } else {
                        //否则显示nil
                        list_sd.setText(
                            String.format("剩余 %s GB / ", "nil") +
                            String.format("总共 %s GB", "nil")
                        );
                    }

                }

                @Override
                public void onDrawerClosed(View p1) {
                    updateFiles();
                }

                @Override
                public void onDrawerStateChanged(int p1) {
                }
            });
        //设置浮动按钮的点击监听事件
        fab.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1) {
                    //打开左侧侧滑栏
                    leftDrawer.openDrawer(Gravity.START);
                }


            });
    }



    /**
     *@return View
     *用于给父类提供一个布局,以便SnackBar与控件产生效果
     */
    @Override
    protected ViewGroup getSnackView() {
        return findViewById(R.id.main_layout);
    }

    //重写返回监听,判断侧滑栏是否打开,若打开则关闭,则进行双击判断
    @Override
    public void onBackPressed() {
        if (!leftDrawer.isDrawerOpen(Gravity.START)) {
            super.onBackPressed();
        } else {
            leftDrawer.closeDrawer(Gravity.START);
        }
    }

    /**
     *@return Boolean
     *用于测试软件是否有权限读写
     */
    public Boolean testPermission() {
        Boolean isPermission = false;
        try {

            File testPermission = new File(STORAGE + ".test");
            if (!testPermission.exists()) {
                testPermission.createNewFile();
                testPermission.delete();
                isPermission = true;
            } else {
                testPermission.delete();
                testPermission.createNewFile();
                testPermission.delete();
                isPermission = true;
            }

        } catch (IOException e) {
            new Permission(this)
                .CheckPermission();
        }
        return isPermission;
    }


    /**
     *创建一些基础文件,这里CIDE.config用于判断应用名称是否被修改,修改抛出异常
     */
    private void createFiles() {
        try {
            //创建projects文件夹对应的File
            File dir = new File(STORAGE + getString(R.string.app_name));
            //是否为一个文件夹,不是则创建一个
            if (!dir.isDirectory()) {
                dir.mkdir();
            }

            //获取projects的绝对路径
            String path = dir.getAbsoluteFile().toString() + "/";

            //列出其中所有文件对象
            File[] files = dir.listFiles();

            //判断是否有文件
            if (!(files == null || files.length == 0)) {
				
                Boolean isProjectDirNull = true;

                //DataInputStream读取到的是否为CideCompat,不是则抛出异常
                for (File file : files) {
                    if (file.isDirectory() && file.getName().equals("projects")) {
                        isProjectDirNull = false;
                    }
                }

                //没有projects目录则创建
                if (isProjectDirNull) {
                    new File(path + "projects").mkdir();
                }

            } else {
                File[] directories = new File[]{
                    pathToFile(path + "projects"),
                    pathToFile(path + "modlules")
                };

                for (File file : directories) {
                    file.mkdirs();
                }

                files = new File[]{
                    pathToFile(path + (ApplicationUtils.getWorkName(this) + ".config"))
                };


                for (File file : files) {
                    file.createNewFile();
                    if ((ApplicationUtils.getWorkName(this) + ".config").equals(file.getName())) {
                        DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(file.getAbsoluteFile().toString())));
                        dos.writeUTF("CideCompat");
                        dos.close();
                    }
                }

            }

        } catch (IOException e) {

        }
    }

    private void openProjectFile(File file) {
        String name = file.getName();
        ProjectLanguage language = ProjectLanguage.AndJS;

        if (name.endsWith(".as")) {
            language = ProjectLanguage.AndJS;
        } else if (name.endsWith(".js")) {
            language = ProjectLanguage.JavaScript;
        } else if (name.endsWith(".java")) {
            language = ProjectLanguage.Java;
        } else if (name.endsWith(".cpp")) {
            language = ProjectLanguage.C_Enhance;
        } else if (name.endsWith(".c")) {
            language = ProjectLanguage.C;
        } else if (name.endsWith(".cs")) {
            language = ProjectLanguage.Csharp;
        } else if (name.endsWith(".h")) {
            language = ProjectLanguage.Cpp_Head;
        } else if (name.endsWith(".m")) {
            language = ProjectLanguage.Objective_C;
        } else if (name.endsWith(".lua")) {
            language = ProjectLanguage.Lua;
        } else if (name.endsWith(".html")) {
			language = ProjectLanguage.HTML;
		} else if(name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg")) {
			Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsoluteFile().toString());
			AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
			dialog.setTitle("图片查看");
			ImageView image = new ImageView(this);
			dialog.setView(image);
			image.setImageBitmap(bitmap);
			dialog.setCancelable(false);
			dialog.setPositiveButton("隐藏", null);
			return;
		}

        Intent intent = new Intent(this, EditActivity.class);
        intent.putExtra("project_file", file.toString());
        intent.putExtra("project_name", file.getName());
        intent.putExtra("project_text", ApplicationUtils.getTextFromSD(file));
        intent.putExtra("project_language", language);
        startActivity(intent);
    }
    
	
	private void startInstallActivity(File file) {
        File apkFile = file;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //版本高于6.0，权限不一样
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = new Intent(Intent.ACTION_VIEW);
			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			String authority = "myProvider" + ".fileProvider";
			Uri fileUri = FileProvider.getUriForFile(this, authority, file);
			intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
            //兼容8.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                boolean hasInstallPermission = getPackageManager().canRequestPackageInstalls();
                if (!hasInstallPermission) {
                    startInstallPermissionSettingActivity();
                }
            }
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }

    /**
     * 跳转到设置-允许安装未知来源-页面
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
	

    @Override
    public boolean onItemLongClick(AdapterView<?> p1, View p2, int p3, long p4) {
        Projects project = adapter.getItem(p3);
        final File selectFile = project.getFile();
        PopupMenu menu = new PopupMenu(this, p2);
        menu.getMenuInflater().inflate(R.menu.popup_menu , menu.getMenu());
        menu.show();
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){

                @Override
                public boolean onMenuItemClick(MenuItem p1) {
                    switch (p1.getTitle().toString()) {

                        case "复制路径":
                            ClipboardManager manager = (ClipboardManager) getSystemService(MainActivity.this.CLIPBOARD_SERVICE);
                            manager.setText(selectFile.toString());
                            Toast.makeText(MainActivity.this, "复制成功", Toast.LENGTH_SHORT).show();
                            break;

                        case "删除":
                            if (selectFile.isDirectory()) {
                                ApplicationUtils.deleteDirectory(selectFile.toString());
                            } else {
                                ApplicationUtils.deleteFile(selectFile.toString());
                            }
                            updateFiles();
                            break;
                    }
                    return false;
                }

            });

        return false;
    }

    private static File pathToFile(String name) {
        return new File(name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String[] features = new String[]{
            "打开工程",
            "新建工程",
			"设置"
        };
        for (String feature : features) {
            menu.add(feature);
        }
        return super.onCreateOptionsMenu(menu);
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getTitle().toString()) {
			case "打开工程":
                leftDrawer.openDrawer(Gravity.START);
                break;

			case "新建工程":
                try {
                    new NewProject(MainActivity.this).show(ApplicationGlobalSettings.storagePath.toString());
                } catch (Exception e) {
                    Intent intent = new Intent(MainActivity.this, ExceptionActivity.class);
                    intent.putExtra("ERROR", e.toString());
                    //startActivity(intent);
                    Toast.makeText(MainActivity.this, "暂时不支持, 请手动创建项目", Toast.LENGTH_LONG).show();

                }
                break;

			case "设置":
                startActivity(new Intent(this, SettingsActivity.class));
                break;
		}
		return super.onOptionsItemSelected(item);
	}



}
