/**
*类型:Modpe
*名称:线性布局
*Time:2020-12-19 20-48-44 */

//获取程序的Context入口
var ctx = com.mojang.minecraftpe.MainActivity.currentMainActivity.get();
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
//实例化LinearLayout线性布局
var linearlayout = new android.widget.LinearLayout(ctx);
//设置线性布局的排列方式:1为垂直,0为水平
linearlayout.setOrientation(1);
//循环创建Button,在开发中最好不要这么写,容易堵塞主线程
for(var i = 0;i < 5;i++){
//实例化按钮对象
var button = new android.widget.Button(ctx);
//设置按钮的显示文本
button.setText("我是第" + i + "个按钮");
//将按钮添加至LinearLayout线性布局
linearlayout.addView(button);
};
//实例化PopupWindow弹窗
var popupWindow = new android.widget.PopupWindow(ctx);
//设置宽
popupWindow.setWidth(getWidth());
//设置高
popupWindow.setHeight(getWidth());
//设置控件为LinearLayout线性布局。
popupWindow.setContentView(linearlayout);
//设置弹出动画,这个是Dialog的弹出效果
popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
//设置PopupWindow背景透明,通常是黑色的
popupWindow.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.argb(0,0,0,0))); 
//设置PopupWindow的显示位置,第3个参数为x,第四个是y
popupWindow.showAtLocation(ctx.getWindow().getDecorView(),android.view.Gravity.LEFT|android.view.Gravity.TOP,0,getWidth() - 130);
}}));

