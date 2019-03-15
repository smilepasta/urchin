# urchin
Android学习笔记总结

# 已实现的功能
- MVP+Retrofit+OkHttp+RxJava1.0网络请求
- 分页、通用的BasicAdapter、BasicListFragment、TextBarActivity
- 图片浏览、html在TextView中显示（文字、图片、超链接）、WebView、音乐播放
- 语言切换、清除缓存、应用升级、关于我（链接Blog）
- ProtoBuf使用
- faceu人脸核身(<a href="http://www.yskplus.com">http://www.yskplus.com</a>)

# 配置文件
项目的build.gradle文件所用到的<b>urchin.properties</b>配置文件与<b>urchin.jks</b>密钥文件都未上传，clone后需要自己定义。
urchin.properties文件内容如下：
``` java
storePassword='your_store_password'
keyPassword='your_key_password'
keyAlias='your_key_alias'
storeFile=your_store_file_path

faceuAppKey="your_faceu_appkey"
faceuSecretKey="your_faceu_secretkey"
```