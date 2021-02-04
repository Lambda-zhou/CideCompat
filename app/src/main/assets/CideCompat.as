((window, undefined)=>{
window.window = window.self = window.this = window;
var window = window,
undefined = undefined;
const prop_extends = function(prop, source){
  for(var i in source){
    prop[i] = source[i];
  };
};

const define = function(obj, name, val, isHide) {
  if(isHide && val != null){
    Object.defineProperty(obj, name, {
    configurable:false,
    enumerable:false,
    value:val,
    writable:false
    });
  }else{
    obj[name] = val;
  }
};

const defines = function(obj, names, values, isHides) {
  for(var i in names){
     for(var j in values){
        for(var n in isHides){
          if(isHides[n] && val != null){
          Object.defineProperty(obj, names[i], {
          configurable:false,
          enumerable:false,
          writable:false,
          value:values[j]
          });
          }else{
          obj[names[i]] = values[j];
          }
        };
     };
  };
};

const makeObjects = function(cout) {
  var array = [];
  for(let i = 0;i < cout;i++){
    array.push(new Object());
  };
  return array;
};

var ctx = __javaContext__;
define(window, "activity", ctx);

/**
*全局函数
*/

//print
//@params ... Object
define(window, "print", function() {
var message = [];
for(var i = 0;i < arguments.length;i++){
  message.push(String(arguments[i]));
}
message = message.join(",");
ctx.runOnUiThread(function() {
  android.widget.Toast.makeText(ctx, message, 0).show();
});}, false);

//runOnUiThread
//@params runnable function
define(window, "runOnUiThread", runnable=>ctx.runOnUiThread(runnable));

//mdPrint
//@params ... Object
define(window, "mdPrint", function() {
var message = [];
for(var i = 0;i < arguments.length;i++){
message.push(String(arguments[i]));
}
message = message.join(",");
ctx.runOnUiThread(function() {
var toast_tb = 47;

var card_layout = new android.support.v7.widget.CardView(ctx);
card_layout.setId(toast_tb);
card_layout.setCardElevation(2);
card_layout.setRadius(6);
card_layout.setCardBackgroundColor(android.graphics.Color.parseColor("#ff00Da7C"));

var text_view = new android.widget.TextView(ctx);
var params = new android.widget.LinearLayout.LayoutParams(-1, -1);
params.setMargins(14, 14, 14, 14);
params.gravity = android.view.Gravity.CENTER;
text_view.setLayoutParams(params);
text_view.setText(message);
text_view.setTextSize(14);
text_view.setTextColor(android.graphics.Color.parseColor("#ffffff"));
card_layout.addView(text_view);
var toast = android.widget.Toast.makeText(ctx, "", 0);
toast.setView(card_layout);
toast.show();
});
}, false);

define(window, "newActivity", function(activity_name) {
  var array = [].slice.call(arguments, 1);
  runOnUiThread(function() {
    try{
    ctx.newActivity(new java.io.File(__javaPath__).getParent() + "/" + activity_name + ".as", activity_name, array);
    }catch(e){
    mdPrint(e);
    }
  });
}, false);

define(window, "startActivity", function(activity_name){
  var array = [].slice.call(arguments, 1);
  runOnUiThread(function() {
    try{
    ctx.newActivity(new java.io.File(__javaPath__).getParent() + "/" + activity_name + ".as", activity_name, array);
    ctx.finish();
    }catch(e){
    mdPrint(e);
    }
  });
}, false);

define(window, "include", function() {
let argu = [].slice.call(arguments);
let _ = [];
for(let i in argu){
  if(argu[i] != null && typeof(argu[i]) == "string" || argu[i].construct === String){
     let str = "Packages['" + argu[i].split(".").join("']['") + "']";
     let obj = null;
     eval("obj = " + str);
     _.push(obj);
  }else if(argu[i] != null && typeof(argu[i]) == "object"){
     _.push(argu[i]);
  }else{
  throw new TypeError(argu[i] + "is not a java package or class.");
  };
};

return JavaImporter.apply(window, _);
});

/**
*全局常量
*/

//activity->android.support.v7.app.AppCompatActivity

/**
*主对象
*/
let CideCompat = new Object();
define(CideCompat, "toString", ()=>{
return "[object CideCompat]";
}, false);

define(window, "CideCompat", CideCompat, false);
})(this, undefined);

//newActivity("AndJSLibrary");
try{
//newActivity("CideCompat");
}catch(e){
mdPrint(e);
}