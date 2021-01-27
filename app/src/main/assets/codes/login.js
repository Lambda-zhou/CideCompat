/**
 *类型:Modpe
 *名称:简单好看的登录布局
 *Time:2020-12-21 19-52-53 */
const ctx = com.mojang.minecraftpe.MainActivity.currentMainActivity.get()
ctx.runOnUiThread(new java.lang.Runnable({run:function(){try{

//获取程序的Context入口
var ctx = com.mojang.minecraftpe.MainActivity.currentMainActivity.get();
var popupWindow;
//适配屏幕的函数
function dip2px(ctx,dips){
return Math.ceil(dips * ctx.getResources().getDisplayMetrics().density);
}
//获取屏幕的宽
function getWidth() {
var metrics = new android.util.DisplayMetrics();
ctx.getWindowManager().getDefaultDisplay().getMetrics(metrics);
return metrics.widthPixels;
};
//获取屏幕的高
function getHeight() {
var metrics = new android.util.DisplayMetrics();
ctx.getWindowManager().getDefaultDisplay().getMetrics(metrics);
return metrics.heightPixels;
};
//让代码运行在主线程,否则会报错
ctx.runOnUiThread(new java.lang.Runnable({run:function(){
var layout = new android.widget.LinearLayout(ctx);
layout.setOrientation(1);
layout.setGravity(android.view.Gravity.CENTER);

var user_layout = new android.widget.LinearLayout(ctx);
layout.addView(user_layout);

var user_textview = new android.widget.TextView(ctx);
user_textview.setText("用户名:");
user_layout.addView(user_textview);

var user_edittext = new android.widget.EditText(ctx);
user_edittext.setSingleLine(true);
user_edittext.setLayoutParams(android.widget.LinearLayout.LayoutParams(getWidth(), -2));
user_layout.addView(user_edittext)

var pass_layout = new android.widget.LinearLayout(ctx);
layout.addView(pass_layout);

var pass_textview = new android.widget.TextView(ctx);
pass_textview.setText("密码:");
pass_textview.setLayoutParams(user_textview.getLayoutParams());
pass_layout.addView(pass_textview);

var login_button = new android.widget.Button(ctx);
login_button.setText("登录");
login_button.setOnClickListener(new android.view.View.OnClickListener({onClick:function(view){
if(user_edittext.getText().toString() == "xiaohan" && pass_edittext.getText().toString() == "2632588830"){
print("登录成功");
popupWindow.dismiss();
}else{
print("登录失败");
}
}}));
layout.addView(login_button);

var pass_edittext = new android.widget.EditText(ctx);
pass_edittext.setSingleLine(true);
pass_edittext.setLayoutParams(android.widget.LinearLayout.LayoutParams(getWidth(), -2));
pass_layout.addView(pass_edittext)

//实例化PopupWindow弹窗
var popupWindow = new android.widget.PopupWindow(ctx);
//设置宽
popupWindow.setWidth(getWidth());
//设置高
popupWindow.setHeight(getHeight());
//设置控件为LinearLayout线性布局。
popupWindow.setContentView(layout);
//设置弹出动画,这个是Dialog的弹出效果
popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
//设置PopupWindow背景透明,通常是黑色的
popupWindow.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.argb(0,0,0,0)));
popupWindow.setFocusable(true);
//设置PopupWindow的显示位置,第3个参数为x,第四个是y
popupWindow.showAtLocation(ctx.getWindow().getDecorView(),android.view.Gravity.LEFT|android.view.Gravity.TOP,50,1000);
}}));
}catch(e){print(e);}}}));
