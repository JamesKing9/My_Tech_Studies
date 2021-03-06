在用 idea 创建项目的时候，我们会看到一堆的目录，增加了很多eclipse里面不具备的目录，而且目录结构也改变了。一般我们之关注特定的一些目录，而忽略的其他，当出问题的时候基本会茫然若失不知道如何解决，所以特地学习下目录结构。

### **Project下的视图**

引用地址：[https://github.com/siyehua/Adnroid-Notes/tree/master/Android%20Studio/Directory%20Info](https://github.com/siyehua/Adnroid-Notes/tree/master/Android%20Studio/Directory%20Info)

![这里写图片描述](http://img.blog.csdn.net/20161031152424684)

图片中的链接 Gralde介绍:[http://stormzhang.com/devtools/2014/12/18/android-studio-tutorial4/](http://stormzhang.com/devtools/2014/12/18/android-studio-tutorial4/) 

[Git](http://lib.csdn.net/base/git)/github使用:[http://www.worldhello.net/gotgithub/](http://www.worldhello.net/gotgithub/) 

.gitignore文件说明:[http://www.html-js.com/article/2030](http://www.html-js.com/article/2030)

> module 说明：

[Android](http://lib.csdn.net/base/android) Studio 的 project 相当于 Eclipse 的 workspace 
Android Studio 的 module 相当于 Eclipse 的 project

------

#### **1.gitignore写法**

Android的Module推荐这么写.gitignore文件

```
/build
*.iml1212
```

Android的项目.gitignore文件推荐这么写

```
# Built application files
*.apk
*.ap_

# Files for the Dalvik VM
*.dex

# Java class files
*.class

# Generated files
bin/
gen/

# Gradle files
.gradle/
build/

# Local configuration file (sdk path, etc)
local.properties

# Proguard folder generated by Eclipse
proguard/

# Log Files
*.log12345678910111213141516171819202122232425261234567891011121314151617181920212223242526
```

------

#### **2. .gradle目录，.idea目录**

![这里写图片描述](http://img.blog.csdn.net/20161031153529160)

这些都是项目创建的时候自动生成的，一般情况不做修改，不需要纳入项目源代码管理中。

------

#### **3. module下build 目录**

![这里写图片描述](http://img.blog.csdn.net/20161031154354552)

我们编译最终生成的apk就在build/outputs/apk目录下，里面包含了app-debug.apk, app-debug-unaligned.apk,app-release-unaligned.apk三种apk。

------

#### **4. module下 src 目录**

![这里写图片描述](http://img.blog.csdn.net/20161031155402745)

**drawable 和 mipmap 的区别**

使用上没有任何区别,你把它当drawable用就好了。

但是用mipmap系统会在缩放上提供一定的性能优化。

------

[https://segmentfault.com/q/1010000002603418](https://segmentfault.com/q/1010000002603418) 学习资料

[mipmap 和 drawable 的区别](http://blog.csdn.net/zhaoyw2008/article/details/49096769)摘录：

> Mipmapping for drawables
>
> Using a mipmap as the source for your bitmap or drawable is a simple way to provide a quality image and various image scales, which can be particularly useful if you expect your image to be scaled during an animation.
>
> Android 4.2 (API level 17) added support for mipmaps in the Bitmap class—Android swaps the mip images in your Bitmap when you’ve supplied a mipmap source and have enabled setHasMipMap(). Now in Android 4.3, you can enable mipmaps for a BitmapDrawable object as well, by providing a mipmap asset and setting the android:mipMap attribute in a bitmap resource file or by calling hasMipMap().

Android 在 API level 17 加入了 mipmap 技术，对 bitmap 图片的渲染支持 mipmap 技术，来提高渲染的速度和质量。

mipmap 是一种很早就有的技术了，翻译过来就是纹理映射技术。android 中的 mipmap 技术主要为了应对图片大小缩放的处理，在android 中我们提供一个 bitmap 图片，由于应用的需要（比如缩放动画），可能对这个 bitmap 进行各种比例的缩小，为了提高缩小的速度和图片的质量，android 通过 mipmap 技术提前对按缩小层级生成图片预先存储在内存中，这样就提高了图片渲染的速度和质量。

api 中通过 Bitmap 的 public final void setHasMipMap (boolean hasMipMap) 方法可以让系统渲染器尝试开启 Bitmap 的 mipmap 技术。但是这个方法只能建议系统开启这个功能，至于是否正真开启，还是由系统决定。

res 目录下面 mipmap 和 drawable 的区别也就是上面这个设置是否开启的区别。mipmap 目录下的图片默认 setHasMipMap 为 true，drawable 默认 setHasMipMap 为 false。