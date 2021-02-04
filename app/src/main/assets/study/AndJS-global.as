/**
 *类型:AndJS
 *名称:AndJS全局函数/属性
 *Time:2021-01-31 11-22-37
 *AndJS提供了一些全局属性(变量,函数)
 *本教程介绍和使用一下这些属性
 */
 
 /**
  * ꯭G꯭l꯭o꯭b꯭a꯭l꯭ ꯭F꯭u꯭n꯭c꯭t꯭i꯭o꯭n꯭s꯭
  *(全局函数)
  */
  
  //print函数
  /**
  *print函数用于输出,就是android.widget.Toast.makeText
  *这里是将其简化了
  *由于是Toast,在android高版本会有执行队列,因此可能需要等待一会.
  *@params Object...
  */
  //参数拆份(以,进行元素分割)
//  print("Hello", "World", "!");
  //单输出
//  print("Hello,World!");
  
  //mdPrint函数
  /**
  *mdPrint函数用于输出,就是android.widget.Toast.makeText
  *这里是将其简化了
  *与print函数相同,也是输出,只不过更加美观,通常用于应用显示用户消息
  *由于是Toast,在android高版本会有执行队列,因此可能需要等待一会.
  *@params Object...
  */
  //参数拆份(以,进行元素分割)
//  mdPrint("Hello", "World", "!");
  //单输出
//  mdPrint("Hello,World!")
  
  //newActivity函数
  /**
  *newActivity函数用于打开一个新的Activity(AndJS)页面
  *可以用来进行文件拆分,模块化,这样可以将代码写入多个文件中,更加清晰
  *@params string name
  *@params object ...
  */
//  newActivity("活动名称(as文件,无需加.as后缀)", 参数...);

  //startActivity
  /**
  *startActivity函数用于打开一个新的Activity(AndJS)页面
  *与newActivity不同的是这会将当前Activity关闭掉
  *可以用来进行文件拆分,模块化,这样可以将代码写入多个文件中,更加清晰
  *@params string name
  @params object ...
  */
//  startActivity("活动名称(as文件,无需加.as后缀)", 参数...);
   
   /**
   * ꯭M꯭a꯭g꯭i꯭c꯭ ꯭A꯭t꯭t꯭r꯭i꯭b꯭u꯭t꯭e꯭
   *(魔法属性)
   *介绍:魔法属性也是全局的,其通常的表现形式为:
   *__xxx__
   */
   
   /**
   *当前Activity的ClassLoder
   */
   //__javaLoader__
   
   /**
   *当前执行的AndJS路径
   */
   //__javaPath__
   
   /**
   *当前的Activity实例
   */
   //__javaContext__
   
   /**
   * ꯭特꯭殊꯭꯭
   *startActivity传过来的参数,如果是在主执行文件,其默认为null
   *此魔法属性为一个数组
   *__params__
   */
   //print(__params__);
