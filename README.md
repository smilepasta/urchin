# urchin
Android学习笔记总结

# 已实现的功能
- MVP+Retrofit+OkHttp+RxJava1.0网络请求
- 分页、通用的BasicAdapter、BasicListFragment、TextBarActivity
- 图片上传与预览、html在TextView中显示（文字、图片、超链接）、WebView、音乐播放
- 语言切换、清除缓存、应用升级、关于我（链接Blog）
- ProtoBuf使用
- faceu人脸核身(<a href="http://www.yskplus.com">http://www.yskplus.com</a>)

# 配置文件
将appkey等私密信息配置在gradle.properties文件，并且没有上传配置信息，需要自行配置：
``` java
# apk签名配置
STORE_PASSWORD=
KEY_PASSWORD=
KEY_ALIAS=
STORE_FILE=

# faceu 人脸核身
FACEU_APPKEY=
FACEU_SECRETKEY=

# qiniu 图片上传
QINIU_IMAGE_HOST=
QINIU_ACCESS_KEY=
QINIU_SECRET_KEY=
QINIU_BUCKET_NAME=
```

# 环境
部分接口使用Nodejs搭建简陋后端完成，<a href="https://github.com/smilepasta/NodejsDemo/blob/master/simple_demo/urchin.js">源码</a>
服务器来源于 <a href="https://bandwagonhost.com/vps-hosting.php">搬瓦工</a>
域名注册于 <a href="https://wanwang.aliyun.com/domain/com/?spm=5176.10695662.1158081.2.336944c2WMhRGy">阿里云</a>
