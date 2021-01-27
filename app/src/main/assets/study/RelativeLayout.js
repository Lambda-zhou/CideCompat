/**
 *类型:Modpe
 *名称:RelativeLayout
 *Time:2020-12-21 18-46-50 */

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
ctx.runOnUiThread(new java.lang.Runnable({run:function(){try{
//实例化FrameLayout帧布局
var relativelayout = new android.widget.RelativeLayout(ctx);
//创建第一个Button
var button = new android.widget.Button(ctx);
//设置这个Button的显示文本
button.setText("我是第一个Button");
//设置第一个Button的宽高,参数传入LayoutParams, 第一个参数为宽,第二个是高
button.setLayoutParams(android.widget.RelativeLayout.LayoutParams(-2, -2));
//设置Button的Id,用随机数来设置
button.setId(Math.floor(Math.random() * 150 + 2));
//设置Button的排列方式,使用centerInParent的方式
button.getLayoutParams().addRule(android.widget.RelativeLayout.CENTER_IN_PARENT);
//将这个Button添加到帧布局中
relativelayout.addView(button);
//创建第二个Button
var button2 = new android.widget.Button(ctx);
//设置第二个Button的显示文本
button2.setText("我是第二个Button");
//设置第二个Button的布局参数:传入RelativeLayout.LayoutParams对象->参数1:控件的宽,参数2:控件的高
//相当于是设置控件的宽高
//添加第二个Button到帧布局中
relativelayout.addView(button2);
//设置button2的布局方式,这里是toRightOf, 第二个参数是控件的id,这里是button.getId(),就是前面设置的那一个id
button2.getLayoutParams().addRule(android.widget.RelativeLayout.LEFT_OF, button.getId());
//实例化PopupWindow弹窗
var popupWindow = new android.widget.PopupWindow(ctx);
//设置宽
popupWindow.setWidth(getWidth());
//设置高
popupWindow.setHeight(getHeight());
//设置控件为LinearLayout线性布局
popupWindow.setContentView(relativelayout);
//设置弹出动画,这个是Dialog的弹出效果
popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
//设置PopupWindow背景透明,通常是黑色的
popupWindow.setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.argb(0,0,0,0))); 
//设置PopupWindow的显示位置,第3个参数为x,第四个是y
popupWindow.showAtLocation(ctx.getWindow().getDecorView(),android.view.Gravity.LEFT|android.view.Gravity.TOP, 0,0);
}catch(e){
print(e);
}
}}));

