# Android_example_View04
contentProvider 很好的基础模块
 # 读取联系人 并且使用ListView显示
1.获取 手机联系人，并添加危险权限的判断<br/>

2.把读取到的手机联系人显示在RecyclerView上面
    
 ### 简单的小demo 获取手机设备的联系人！<br/>
 
   第二个核心知识点->自定义contentProvider<br/>
  step:
    1.新建 MyContentProvider 使其继承 ContentProvider 重写 onCreate() CRUD 以及 getType()方法 <br/>
        这么做的好处是 任何一个应用程序 都可以使用 ContentProvider 来访问我们应用程序中的数据 <br/>
    2.使用静态代码块 UriMatcher 添加 URI  <br/>
    3.注意一个Content的 URI 格式 是以 content://com.example.app.provider/table 也就是head + authority + path 组成 <br/>
        需要注意的是 path 后面 可以添加通配符 <br/>
        现有两种通配符 作为使用 <br/>
        一种是/* <br/>
        其含义是 表示匹配任意长度的字符 例：head+authority+path+/* <br/>  
        一种是/# <br/>
        表示匹配任意长度的数字。  PS:不得不想起布莱叶盲文里面的# 的含义，# 代表的就是 数字的开始 <br/> 
     4.组成后的URI,在使用addUri的时候 需要传入三个参数，最后一个是自定义代码 我们可以添加自定义代码，在CRUD 里面获取到进行判断 <br/>
     5.getType需要注意的地方！不说废话，这东西少不了！ <br/>
      作用：它是获取Uri对象所对应的MIME类型，MIME类型是一个互联网标准，它扩展了电子邮件标准，使其能够支持 非ASCII字符标准；非文本格式附件（二进制.声音.图像等）->了解详情请查WIKI <br/>
      大家可能会奇怪 为什么要了解MIME类型？  <br/>
      因为getType返回的uri就是MIME类型的 ，一个MIME类型的URI主要有一下三部分组成 <br/>
      必须以 vnd 开头 <br/>
      如果内容 uri 以路径结尾，则后接 android.cursor.dir/，如果内容以 id 结尾，则后接 android.cursor.item/. <br/>
      例： <br/>
       Uri:content://com.example.app.provider/table1 这个内容URI，它所对应的MIME类型就是： <br/>
       vnd.android.cursor.dir/vnd.com.example.app.provider.table1 <br/>
         
     