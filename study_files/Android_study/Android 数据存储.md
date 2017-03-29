# 引论
```
app 需要不断的和数据打交道，例如，聊QQ 、看新闻、刷微博等等。
数据可以分为两种：瞬时数据、持久化数据。
瞬时数据：是指存储在内存中，有可能因为app的关闭或者其他原因导致内存被回收而丢失的数据。
持久化数据：是指可以人为的指定长期留存的数据。
```

# 数据持久化
```
数据持久化就是指将那些内存中的瞬时数据保存到存储设备中，保证即使在手机或电脑关机的情况下，这些数据仍然不会丢失。保存在内存中的数据是处于瞬时状态的，而保存在存储设备中的数据是处于持久状态的，持久化技术则是提供了一种机制可以让数据在瞬时状态和持久状态之间进行转换。

Android 系统中主要提供了三种方式用于简单地实现数据持久化功能，即文件存储、SharedPreference 存储以及数据库存储。
```
## 文件存储
```
它不对存储的内容进行任何的格式化处理，所有数据都是原封不动地保存到文件当中的，因而它比较适合用于存储一些简单的文本数据或二进制数据。如果你想使用文件存储的方式来保存一些较为复杂的文本数据，就需要定义一套自己的格式规范，这样方便于之后将数据从文件中重新解析出来。
```
### 将数据存储到文件中
```
Context 类中提供了一个 openFileOutput ()方法，可以用于将数据存储到指定的文件中。这个方法接收两个参数，第一个参数是文件名，在文件创建的时候使用的就是这个名称，注意,这里指定的文件名不可以包含路径，因为所有的文件都是默认存储到/data/data/<packagename>/files/ 目录下的 。第二个参数是文 件的操作模式,主要有两种模式可选，MODE_PRIVATE 和 MODE_APPEND。其中 MODE_PRIVATE 是默认的操作模式，表示当指定同样文件名的时候，所写入的内容将会覆盖原文件中的内容，而 MODE_APPEND 则表示如果该文件已存在就往文件里面追加内容，不存在就创建新文件。其实文件的操作模式本来还有另外两种,MODE_WORLD_READABLE 和 MODE_WORLD_WRITEABLE，这两种模式表示允许其他的应用程序对我们程序中的文件进行读写操作，不过由于这两种模式过于危险，很容易引起应用的安全性漏洞，现已在 Android 4.2 版本中被废弃。
openFileOutput ()方法返回的是一个 FileOutputStream 对象，得到了这个对象之后就可以使用 Java 流的方式将数据写入到文件中了。
```
简单应用：
```java
public void save() {
  String data = "I Love You, Android eco";
  FileOutputStream fos = null;
  BufferedWriter bw = null;
  try {
    fos = openFileOutput("data", Context.MODE_PRIVATE);
    bw = new BufferedWriter(new OutputStreamWriter(fos));
    bw.write(data);
  } catch (IOException e) {
    e.printStackTrace();
  } finally {
    try {
      if(bw != null) {
        bw.close();
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
  }
}
```

项目案例：
>需求：1）在当前的界面或者页面被回收之前，保存界面或者页面上 EditText 中输入的文本到文件中去。
```java
public class MainActivity extends Acticity {
  private EditText et;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    et = (EditText)findViewById(R.id.et);
  }
  
  /*
  重写了 onDestroy()方法，这样就可以保证在活动销毁之前一定会调用这个方法。在 onDestroy()方法中我们获取了 EditText 中输入的内容，并调用 save()方法把输入的内容存储到文件中，文件命名为 data_et。
  */
  @Override
  protected void onDestroy() {
    super.onDestroy();
    String inputText = et.getText().toString();
    save(inputText);
  }
  
  public void save(String inputText) {
    FileOutputStream fos = null;
    BufferedWriter bw = null;
    try {
      fos = openFileOutput("data_et", Context.MODE_PRIVATE);
      bw = new BufferedWriter(new OutputStreamWriter(fos));
      bw.write(inputText);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if(bw != null) {
          bw.close();
        }
      } catch(IOException e) {
        e.printStackTrace();
      }
    }
  }
}
```

