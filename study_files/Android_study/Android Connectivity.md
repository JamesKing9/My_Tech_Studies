| ![img](https://www.codeproject.com/KB/android/814814/logo01.png) | ![img](https://www.codeproject.com/KB/android/814814/logo02.png) |
| ---------------------------------------- | ---------------------------------------- |
|                                          |                                          |

## Introduction

Human's quest for communication and information sharing in different forms and situations has prompted the invention of many innovative connectivity technologies, such as Bluetooth and NFC, in addition to the standard network connections like Wi-Fi and 4G. Increasingly, users of mobile devices want to have the freedom and convenience of interacting and exchanging information directly with other devices without the need to go through the conventional network infrastructure.

In this respect, Android has provided a rich set of software libraries called APIs (Application Programming Interfaces) where your app can make use of to connect and interact with other devices in a variety of ways. In this article, you will have the opportunity to explore the basic mechanism of the following connectivity options in a hands-on approach:

- [Bluetooth](https://www.codeproject.com/Articles/814814/Android-Connectivity#bluetooth)
- [Wi-Fi](https://www.codeproject.com/Articles/814814/Android-Connectivity#wifi)
- [NFC](https://www.codeproject.com/Articles/814814/Android-Connectivity#nfc)
- [Mobile Data](https://www.codeproject.com/Articles/814814/Android-Connectivity#mobile)
- [SIP](https://www.codeproject.com/Articles/814814/Android-Connectivity#sip)
- [USB](https://www.codeproject.com/Articles/814814/Android-Connectivity#usb)

## Setting the Stage

First of all, you will get ready a new Android project as follows:

- Name the new project as "AndroidConnection".

- In the new project, create an Activity called "MainActivity". This will be the home page that provides the buttons to navigate to other pages.

- The "activity_main.xml" is shown below:

  Hide    Copy Code

  ```
  <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:paddingLeft="@dimen/activity_horizontal_margin"
      android:paddingRight="@dimen/activity_horizontal_margin"
      android:paddingTop="@dimen/activity_vertical_margin"
      android:paddingBottom="@dimen/activity_vertical_margin"
      tools:context=".MainActivity">

      <Button
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:text="@string/Bluetooth"
          android:id="@+id/btnBluetooth"
          android:layout_alignParentTop="true"
          android:layout_alignParentLeft="true"
          android:layout_alignParentStart="true" />

  </RelativeLayout>  
  ```

  The preview of "MainActivity" should look like that in Figure 1. To test run the project, you will need a real device.

  | ![img](https://www.codeproject.com/KB/android/814814/bluetooth00.png) |
  | ---------------------------------------- |
  | Figure 1: Preview of MainActivity        |

We will start with Bluetooth.

## Bluetooth

The Android platform includes Android Bluetooth APIs for Bluetooth connectivity, which allows an Android device to exchange data with other bluetooth enabled devices wirelessly. Through the Android Bluetooth APIs, an app can perform the following Bluetooth functionalities:

- Scan for other Bluetooth devices.
- Query for paired Bluetooth devices.
- Establish Connection between Bluetooth devices.
- Exchange data with other Bluetooth devices.

#### The Bluetooth Agenda

The complete Android Bluetooth APIs are available in the [android.bluetooth](http://developer.android.com/reference/android/bluetooth/package-summary.html) package. You will meet some of the classes in this package as you create an Activity to perform the following Bluetooth functionalities:

- Enable/disable Bluetooth.


- Make Bluetooth device discoverable by other Bluetooth devices.
- Discover other Bluetooth devices.
- List the names and MAC addresses of those found Bluetooth devices in a "ListView".
- Establish connection with one of the Bluetooth devices in the "ListView".

In the current project,

- Create a new Activity called "BluetoothActivity" using "RelativeLayout" as layout that contains a "ToggleButton" and a "ListView".

- The "activity_bluetooth.xml" is shown below:

  Hide    Shrink ![img](https://www.codeproject.com/images/arrow-up-16.png)    Copy Code

  ```
  <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:paddingLeft="@dimen/activity_horizontal_margin"
      android:paddingRight="@dimen/activity_horizontal_margin"
      android:paddingTop="@dimen/activity_vertical_margin"
      android:paddingBottom="@dimen/activity_vertical_margin"
      tools:context="com.peterleow.androidconnection.BluetoothActivity">

      <ToggleButton
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:id="@+id/toggleButton"
          android:textOn="Bluetooth On"
          android:textOff="Bluetooth Off"
          android:onClick="onToggleClicked"
          android:layout_alignParentTop="true"
          android:layout_alignParentLeft="true"
          android:layout_alignParentStart="true" />

      <ListView
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:id="@+id/listView"
          android:layout_centerHorizontal="true"
          android:layout_below="@+id/toggleButton" />

  </RelativeLayout>  
  ```

  The preview of "BluetoothActivity" should look like that in Figure 2.

  | ![img](https://www.codeproject.com/KB/android/814814/bluetooth01.png) |
  | ---------------------------------------- |
  | Figure 2: Preview of BluetoothActivity   |

Form this point onwards, you will focus on building up the code in the "BluetoothActivity.java" to implement the Bluetooth functionalities in the following sections.

#### Verifying Bluetooth Support

Before any attempt to use Bluetooth functionalities in an app, it must verify whether the device supports Bluetooth. To do that, we have to call upon the "BluetoothAdapter" class of the Android Bluetooth APIs.

"[BluetoothAdapter](http://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html)" is the most important class in the Android Bluetooth APIs. It represents the Bluetooth adapter of an Android device. Currently, Android only supports one Bluetooth adapter per device. "BluetoothAdapter" is the starting point for all Bluetooth operations. To begin any Bluetooth operation, the app will have to first call the static method "[getDefaultAdapter()](http://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html#getDefaultAdapter%28%29)" which returns a handle representing the default local Bluetooth adapter. If "getDefaultAdapter()" returns null, then the device does not support Bluetooth and that is the end of the road. A valid "BluetoothAdapter" handle allows you to scan for other Bluetooth devices, query a list of paired devices, instantiate a "[BluetoothDevice](http://developer.android.com/reference/android/bluetooth/BluetoothDevice.html)" that represents a remote Bluetooth device, and create a "[BluetoothServerSocket](http://developer.android.com/reference/android/bluetooth/BluetoothSocket.html)" to listen for connection requests from other devices. For example:

Hide    Copy Code

```
BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
if (bluetoothAdapter == null) {
    // Device does not support Bluetooth
} else {
    // Any valid Bluetooth operations
}
```

#### Enabling Bluetooth

Once the Bluetooth support on the device is affirmed, your next step is to ensure that Bluetooth is enabled. To do that, the app will call the "isEnabled()" method of the "BluetoothAdapter" handle to check whether Bluetooth is currently enabled. If this method returns false, then Bluetooth is disabled. To request that Bluetooth be enabled, call "startActivityForResult()" with the "ACTION_REQUEST_ENABLE" action Intent. For example:

Hide    Copy Code

```
if (!bluetoothAdapter.isEnabled()) {
    Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
    startActivityForResult(enableBluetoothIntent, ENABLE_BT_REQUEST_CODE);
}
```

A dialog will appear requesting user permission to enable Bluetooth, as shown in Figure 3.

| ![img](https://www.codeproject.com/KB/android/814814/bluetooth02.png) |
| ---------------------------------------- |
| Figure 3: Bluetooth Permission Request   |

If the user clicks "Yes," the system will proceed to enable Bluetooth. The "ENABLE_BT_REQUEST_CODE" constant passed to "[startActivityForResult()](http://developer.android.com/reference/android/app/Activity.html#startActivityForResult%28android.content.Intent,%20int%29)" is a locally defined integer which must be greater than 0 so that the system will pass back to your code in "[onActivityResult()](http://developer.android.com/reference/android/app/Activity.html#onActivityResult%28int,%20int,%20android.content.Intent%29)" implementation as the "requestCode" parameter.

If Bluetooth is successfully enabled, your activity will receives the "resultCode" of "[RESULT_OK](http://developer.android.com/reference/android/app/Activity.html#RESULT_OK)" in the "[onActivityResult()](http://developer.android.com/reference/android/app/Activity.html#onActivityResult%28int,%20int,%20android.content.Intent%29)" callback. If Bluetooth was not enabled either because of an error or the user clicks "No" then the "resultCode" is "[RESULT_CANCELED](http://developer.android.com/reference/android/app/Activity.html#RESULT_CANCELED)". For example:

Hide    Copy Code

```
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == ENABLE_BT_REQUEST_CODE) {
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Ha! Bluetooth has been enabled.",
                   Toast.LENGTH_SHORT).show();
        } else { // RESULT_CANCELED as user refuse or failed
            Toast.makeText(getApplicationContext(), "Bluetooth is not enabled.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
```

#### Making Bluetooth Discoverable

Once Bluetooth has been enabled, you will make it discoverable by other Bluetooth devices. To do this, call "startActivityForResult() with the "[ACTION_REQUEST_DISCOVERABLE](http://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html#ACTION_REQUEST_DISCOVERABLE)" action Intent. For example:

Hide    Copy Code

```
protected void makeDiscoverable(){
    // Make local device discoverable
    Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
    discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVERABLE_DURATION);
    startActivityForResult(discoverableIntent, DISCOVERABLE_BT_REQUEST_CODE);
}
```

The "DISCOVERABLE_BT_REQUEST_CODE" constant passed to "startActivityForResult()" is a locally defined integer which must be greater than 0 so that the system will pass back to your code in "onActivityResult()" implementation as the "requestCode" parameter.

By default, the device will become discoverable for 120 seconds. You can define a different duration by assigning a different integer value to the "[EXTRA_DISCOVERABLE_DURATION](http://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html#EXTRA_DISCOVERABLE_DURATION)" Intent extra. An app can set any integer between 0 and 3600 for this duration, any value outside of this range will be reverted to 120. In our example, I have use a local variable called "DISCOVERABLE_DURATION" that takes the value of 300.

A dialog will appear requesting user permission to enable Bluetooth discoverability, as shown in Figure 4.

| ![img](https://www.codeproject.com/KB/android/814814/bluetooth03.png) |
| ---------------------------------------- |
| Figure 4: Bluetooth Permission Requiest  |

If the user clicks "Yes," then the device will become discoverable for the specified duration. Your activity will receive the "resultCode" equal to the specified discoverable duration in the "onActivityResult()" callback. If the user clicks "No" or if an error occurrs, the "resultCode" is "RESULT_CANCELED". For example:

Hide    Copy Code

```
public void onActivityResult(int requestCode, int resultCode, Intent data) {

    if (requestCode == ENABLE_BT_REQUEST_CODE) {
        // other code     
    } else if (requestCode == DISCOVERABLE_BT_REQUEST_CODE){

        if (resultCode == DISCOVERABLE_DURATION){
            Toast.makeText(getApplicationContext(), "Your device is now discoverable by other devices for " +
                                DISCOVERABLE_DURATION + " seconds",
                        Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Fail to enable discoverability on your device.",
                        Toast.LENGTH_SHORT).show();
        }
     }
}
```

> Note:
>
> Enabling device discoverability will automatically enable Bluetooth if it has not been enabled on the device.

#### Discovering Remote Bluetooth Devices

Next, make the app discover remote Bluetooth devices. Any remote Bluetooth device within the range will respond to a discovery request if it has been enabled to be discoverable. To start the discovery process, the app will call the "[startDiscovery()](http://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html#startDiscovery%28%29)" method of the "BluetoothAdapter" handle. For example:

Hide    Copy Code

```
protected void discoverDevices(){
    // To scan for remote Bluetooth devices
    if (bluetoothAdapter.startDiscovery()) {
        Toast.makeText(getApplicationContext(), "Discovering other bluetooth devices...",
                Toast.LENGTH_SHORT).show();
    } else {
        Toast.makeText(getApplicationContext(), "Discovery failed to start.",
                Toast.LENGTH_SHORT).show();
    }
}
```

The process is asynchronous and the "startDiscovery()" method will immediately return with a boolean indicating whether discovery has successfully started. How can the app be notified of the outcome of the discovery process? Here comes the role of "[BroadcastReceiver](http://developer.android.com/reference/android/content/BroadcastReceiver.html)" class.

For each device discovered, the Android system will broadcast the "[ACTION_FOUND](http://developer.android.com/reference/android/bluetooth/BluetoothDevice.html#ACTION_FOUND)" Intent. This Intent always returns two bundles of data as follows:

- An "[EXTRA_DEVICE](http://developer.android.com/reference/android/bluetooth/BluetoothDevice.html#EXTRA_DEVICE)" containing a "[BluetoothDevice](http://developer.android.com/reference/android/bluetooth/BluetoothDevice.html)" that represents the remote Bluetooth device that is found; and
- An "[EXTRA_CLASS](http://developer.android.com/reference/android/bluetooth/BluetoothDevice.html#EXTRA_CLASS)" containing a "[BluetoothClass](http://developer.android.com/reference/android/bluetooth/BluetoothClass.html)" that describes the general characteristics and capabilities of that found device.

In order to capture this broadcast, you app must register a "BroadcastReceiver" for this "ACTION_FOUND" Intent. First, you create a "BroadcastReceiver" class like this:

Hide    Copy Code

```
private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // Whenever a remote Bluetooth device is found
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            // Get the BluetoothDevice object from the Intent
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            // Add the name and address to an array adapter to show in a ListView
            adapter.add(device.getName() + "\n" + device.getAddress());
        }
    }
};
```

then register this "BroadcastReceiver" class for the "ACTION_FOUND" Intent like this:

Hide    Copy Code

```
// Register the BroadcastReceiver for ACTION_FOUND
IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
this.registerReceiver(broadcastReceiver, filter);
```

> Note:
>
> As the app will not be receiving any Intent when paused, always unregister the "BroadcastReceiver" on "[Activity.onPause()](http://developer.android.com/reference/android/app/Activity.html#onPause%28%29)" and register it on "[Activity.onResume()](http://developer.android.com/reference/android/app/Activity.html#onResume%28%29)", and this will reduce unnecessary system overhead.

Whenever a new device is discovered, the app will be notified through the ""BroadcastReceiver" which in turn obtains this new device as a "BluetoothDevice" object from the Intent extra, and add its name and MAC address to the "ListView" via an "[ArrayAdapter](http://developer.android.com/reference/android/widget/ArrayAdapter.html)".

#### Disabling Bluetooth

To disable Bluetooth upon turning off the Toggle button, call the "[disable()](http://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html#disable%28%29)" method of the "BluetoothAdapter" handle as shown:

Hide    Copy Code

```
bluetoothAdapter.disable();

```

#### Interim Review

Up to this point, we have explored various topics that include enabling Bluetooth, making Bluetooth discoverable, discovering remote Bluetooth devices, displaying the names and MAC addresses of discovered devices, and disabling Bluetooth. In the "BluetoothActivity.java", all these activities will take place upon clicking the "ToggleButton" which triggers the "onToggleClicked" event handler. The "BluetoothActivity.java" is shown below:

Hide    Shrink ![img](https://www.codeproject.com/images/arrow-up-16.png)    Copy Code

```
// other code

public class BluetoothActivity extends Activity {

    // Create a BroadcastReceiver for ACTION_FOUND
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // Whenever a remote Bluetooth device is found
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                adapter.add(bluetoothDevice.getName() + "\n"
                        + bluetoothDevice.getAddress());
            }
        }
    };
    private BluetoothAdapter bluetoothAdapter;
    private ToggleButton toggleButton;
    private ListView listview;
    private ArrayAdapter adapter;
    private static final int ENABLE_BT_REQUEST_CODE = 1;
    private static final int DISCOVERABLE_BT_REQUEST_CODE = 2;
    private static final int DISCOVERABLE_DURATION = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        listview = (ListView) findViewById(R.id.listView);
 
        adapter = new ArrayAdapter
                (this,android.R.layout.simple_list_item_1);
        listview.setAdapter(adapter);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void onToggleClicked(View view) {

        adapter.clear();

        ToggleButton toggleButton = (ToggleButton) view;

        if (bluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(getApplicationContext(), "Oop! Your device does not support Bluetooth",
                    Toast.LENGTH_SHORT).show();
            toggleButton.setChecked(false);
        } else {

            if (toggleButton.isChecked()){ // to turn on bluetooth
                if (!bluetoothAdapter.isEnabled()) {
                    // A dialog will appear requesting user permission to enable Bluetooth
                    Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBluetoothIntent, ENABLE_BT_REQUEST_CODE);
                } else {
                    Toast.makeText(getApplicationContext(), "Your device has already been enabled." +
                                    "\n" + "Scanning for remote Bluetooth devices...",
                            Toast.LENGTH_SHORT).show();
                    // To discover remote Bluetooth devices
                    discoverDevices();
                    // Make local device discoverable by other devices
                    makeDiscoverable();
                }
            } else { // Turn off bluetooth

                bluetoothAdapter.disable();
                adapter.clear();
                Toast.makeText(getApplicationContext(), "Your device is now disabled.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ENABLE_BT_REQUEST_CODE) {

            // Bluetooth successfully enabled!
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Ha! Bluetooth is now enabled." +
                                "\n" + "Scanning for remote Bluetooth devices...",
                        Toast.LENGTH_SHORT).show();

                // Make local device discoverable by other devices
                makeDiscoverable(); 

               // To discover remote Bluetooth devices
                discoverDevices();

            } else { // RESULT_CANCELED as user refused or failed to enable Bluetooth
                Toast.makeText(getApplicationContext(), "Bluetooth is not enabled.",
                        Toast.LENGTH_SHORT).show();

                // Turn off togglebutton
                toggleButton.setChecked(false);
            }
        } else if (requestCode == DISCOVERABLE_BT_REQUEST_CODE){

            if (resultCode == DISCOVERABLE_DURATION){
                Toast.makeText(getApplicationContext(), "Your device is now discoverable by other devices for " +
                                DISCOVERABLE_DURATION + " seconds",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Fail to enable discoverability on your device.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    protected void discoverDevices(){
        // To scan for remote Bluetooth devices
        if (bluetoothAdapter.startDiscovery()) {
            Toast.makeText(getApplicationContext(), "Discovering other bluetooth devices...",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Discovery failed to start.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    protected void makeDiscoverable(){
        // Make local device discoverable
        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVERABLE_DURATION);
        startActivityForResult(discoverableIntent, DISCOVERABLE_BT_REQUEST_CODE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the BroadcastReceiver for ACTION_FOUND
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(broadcastReceiver);
    }

// other code
}
```

#### Testing 1, 2, 3...

Before you can test it on a real device, you will have to create navigation from the "MainActivity". Do these:

- In the "activity_main.xml", add the "android:onClick" attribute to the <Button> node as shown,

  Hide    Copy Code

  ```
  android:onClick="getBluetoothActivity"
  ```

- In the "MainActivity.java", add the "getBluetoothActivity()" method to navigate to the "BluetoothActivity" when the button is clicked.

  Hide    Copy Code

  ```
  public void getBluetoothActivity(View view) {
      Intent intent = new Intent(getApplicationContext(), BluetoothActivity.class);
      startActivity(intent);
  }
  ```

Launch the app on a real device, navigate to the "BluetoothActivity" page, and then click the "ToggleButton" to turn on Bluetooth. Oop! What happens? It crashes! (Figure 5) The cause of this is that the app has not been granted permission to access Bluetooth on the device.

| ![img](https://www.codeproject.com/KB/android/814814/bluetooth04.png) |
| ---------------------------------------- |
| Figure 5: App Crashes                    |

#### Granting Bluetooth Access

Before using Bluetooth in an app, you must declare the Bluetooth permission "[BLUETOOTH](http://developer.android.com/reference/android/Manifest.permission.html#BLUETOOTH)" in the app's manifest file. You need this permission to perform any Bluetooth operation, such as requesting a connection, accepting a connection, and exchanging data. In addition, if the app has to discover remote Bluetooth devices and perform pairing, then you must also declare the "[BLUETOOTH_ADMIN](http://developer.android.com/reference/android/Manifest.permission.html#BLUETOOTH_ADMIN)" permission. Note that in order to grant "BLUETOOTH_ADMIN" permission, you must have the "BLUETOOTH" permission first. Declare the Bluetooth permissions in the "AndroidManifiest.xml" of the app. For example:

Hide    Copy Code

```
<uses-permission android:name="android.permission.BLUETOOTH" />
<uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
```

#### Discovering and Being Discoverable

With the necessary permissions added, You app should be able to discovery all visible remote Bluetooth devices within range. (Figures 6 to 8).

| ![img](https://www.codeproject.com/KB/android/814814/bluetooth05.png) |
| ---------------------------------------- |
| Figure 6: Request Permission to Turn on Bluetooth |

| ![img](https://www.codeproject.com/KB/android/814814/bluetooth07.png) |
| ---------------------------------------- |
| Figure 7: Request Permission to Turn on Discoverability |

| ![img](https://www.codeproject.com/KB/android/814814/bluetooth08.png) |
| ---------------------------------------- |
| Figure 8: Discovered Devices and be Discoverable |

#### Connecting Devices

Discovering devices is only the first step to any Bluetooth operations, such as listening for incoming connection, requesting a connection, accepting a connection, and exchanging data. The connection mechanism follows that of the client-server model. In general, to implement a connection between two devices, one of them has to act as the server and the other the client. The server makes itself discoverable and waits for connection request from client. The client on the other hand initiates the connection using the server device's MAC address discovered during a discovery process.

We will walk through the process for setting up a connection between Android devices.

#### Setting Up a Listening Server

The device that acts as the server must hold an open "[BluetoothServerSocket](http://developer.android.com/reference/android/bluetooth/BluetoothServerSocket.html)" which is the server socket that listens for incoming connection requests. When the connection request is accepted, the server socket will return a connected "[BluetoothSocket](http://developer.android.com/reference/android/bluetooth/BluetoothSocket.html)" to be used for managing the connection. Once the "BluetoothSocket" is acquired, the "BluetoothServerSocket" can then be discarded. The process goes like this:

1. Call the "[listenUsingRfcommWithServiceRecord()"](http://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html#listenUsingRfcommWithServiceRecord%28java.lang.String,%20java.util.UUID%29) method of the "BluetoothAdapter" handle to return a secure RFCOMM (Radio Frequency Communication) "BluetoothServerSocket" with Service Record. For example:

   Hide    Copy Code

   ```
   BluetoothServerSocket  bluetoothServerSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord(getString(R.string.app_name), uuid);
   ```

   The first string parameter is the service name which could be the app name. The second parameter is a Universally Unique Identifier (UUID) which is a standardized 128-bit format for a string ID that is used to uniquely identify the Bluetooth service in the app. The system will automatically write the service name, UUID, and the RFCOMM channel to the Service Discovery Protocol (SDP) database on the device. Remote Bluetooth devices can then use this same UUID to query the SDP server and discover which channel and service to connect to. You can generate a UUID string using some online UUID generator, and then convert the string to a UUID like this:

   Hide    Copy Code

   ```
   private final static UUID uuid = UUID.fromString("fc5ffc49-00e3-4c8b-9cf1-6b72aad1001a");
   ```

2. Call the "[accept()](http://developer.android.com/reference/android/bluetooth/BluetoothServerSocket.html#accept%28%29)" method of the "BluetoothServerSocket" to start listening for connection requests. The call will block the current thread until a connection is accepted or an exception has occurred. The "BluetoothServerSocket" will only accept a connection request that has a matching UUID. Once a connection is accepted, "accept()" will return a "[BluetoothSocket](http://developer.android.com/reference/android/bluetooth/BluetoothSocket.html)" to manage the connection. Once the "BluetoothSocket" is acquired, you should close "BluetoothServerSocket" by calling its "[close()](http://developer.android.com/reference/android/bluetooth/BluetoothServerSocket.html#close%28%29)" method as it is no longer needed. For example:

   Hide    Copy Code

   ```
   BluetoothSocket bluetoothSocket = bluetoothServerSocket.accept();
   bluetoothServerSocket.close();
   ```

As the "accept()" will block any other interaction until a connection is accepted, it should not be run in the main UI thread. As a matter fact, it is a best practice to handle any operation that involves "BluetoothServerSocket" or "BluetoothSocket" in a new thread managed by the app. Here is an example of a thread that handles the setting up of a server on an Android device:

Hide    Shrink ![img](https://www.codeproject.com/images/arrow-up-16.png)    Copy Code

```
private class ListeningThread extends Thread {
    private final BluetoothServerSocket bluetoothServerSocket;

    public ListeningThread() {
        BluetoothServerSocket temp = null;
        try {
            temp = bluetoothAdapter.listenUsingRfcommWithServiceRecord(getString(R.string.app_name), uuid);

        } catch (IOException e) {
                e.printStackTrace();
        }
            bluetoothServerSocket = temp;
    }

    public void run() {
        BluetoothSocket bluetoothSocket;
        // This will block while listening until a BluetoothSocket is returned
        // or an exception occurs
        while (true) {
            try {
                bluetoothSocket = bluetoothServerSocket.accept();
            } catch (IOException e) {
                break;
            }
            // If a connection is accepted
            if (bluetoothSocket != null) {

                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "A connection has been accepted.",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                // Manage the connection in a separate thread

                try {
                    bluetoothServerSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    // Cancel the listening socket and terminate the thread
    public void cancel() {
        try {
            bluetoothServerSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Then call it from the main UI thread like this:

Hide    Copy Code

```
ListeningThread t = new ListeningThread();
t.start();
```

#### **Setting Up a Connecting Client**

Once you have set up a device as a discoverable server holding an open "BluetoothServerSocket" and listening for connection request, any other Bluetooth device within range can discover and initiate connection request to it as a client. For that to happen, this client device must know the MAC address of that server device and a matching UUID to that particular connection.

In the preceding section on "Discovering Remote Bluetooth Devices", you have learned to find remote Bluetooth devices and display their names and MAC addresses on a "ListView". You will select one of them to initiate a connection request. To do that, you will set a "setOnItemClickListener" to the "ListView" to listen for any click event performed on this "ListView", and when it happens, pick up the selected item from its "onItemClick()" event handler. In this way, the app will be able to pick up the selected MAC address, passing it as parameter to the "getRemoteDevice()" method of the "BluetoothAdapter" handle to obtain a "BluetoothDevice" object representing the remove server device. Your app will then pass this "BluetoothDevice" object to a new thread to initiate a connection request to this object. For example:

Hide    Copy Code

```
listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String  itemValue = (String) listview.getItemAtPosition(position);
        String MAC = itemValue.substring(itemValue.length() - 17);
        BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice(MAC);
        // Initiate a connection request in a separate thread
        ConnectingThread t = new ConnectingThread(bluetoothDevice);
        t.start();
    }
});
```

In the new thread, the process goes like this:

1. Using the "BluetoothDevice" object that represents the remove server device, the app will call the "[createRfcommSocketToServiceRecord(UUID)](http://developer.android.com/reference/android/bluetooth/BluetoothDevice.html#createRfcommSocketToServiceRecord%28java.util.UUID%29)" method to create an RFCOMM "[BluetoothSocket](http://developer.android.com/reference/android/bluetooth/BluetoothSocket.html)" ready to start a secure connection to this remote device using SDP lookup on the UUID parameter. The UUID passed here must match the UUID used by the open "BluetoothServerSocket" of the server device. For example:

   Hide    Copy Code

   ```
   BluetoothSocket bluetoothSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
   ```

2. Call the "[connect()](http://developer.android.com/reference/android/bluetooth/BluetoothSocket.html#connect%28%29)" method of the "BluetoothSocket" to initiate the connection request. Upon this call, the system will perform an SDP lookup on the remote device to find the service that matches the UUID. This call will block the current thread until a connection is accepted or an exception has occurred due to a connection error or a time-out after 12 seconds. If the lookup is successful and the connection requests is accepted, the "connect()", a connection over a shared RFCOMM channel will be established for subsequent communication. For example:

   Hide    Copy Code

   ```
   bluetoothSocket.connect();
   ```

As the "connect()" will block any other interaction until a connection is accepted, it should not be run in the main UI thread. Here is an example of a thread that handles the connection request of a client on an Android device:

Hide    Shrink ![img](https://www.codeproject.com/images/arrow-up-16.png)    Copy Code

```
private class ConnectingThread extends Thread {
    private final BluetoothSocket bluetoothSocket;
    private final BluetoothDevice bluetoothDevice;

    public ConnectingThread(BluetoothDevice device) {

        BluetoothSocket temp = null;
        bluetoothDevice = device;

        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            temp = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bluetoothSocket = temp;
    }

    public void run() {
        // Cancel any discovery as it will slow down the connection
        bluetoothAdapter.cancelDiscovery();

        try {
            // This will block until it succeeds in connecting to the device
            // through the bluetoothSocket or throws an exception
            bluetoothSocket.connect();
        } catch (IOException connectException) {
            connectException.printStackTrace();
            try {
                bluetoothSocket.close();
            } catch (IOException closeException) {
                closeException.printStackTrace();
            }
        }

        // Code to manage the connection in a separate thread
        /*
            manageBluetoothConnection(bluetoothSocket);
        */
    }

    // Cancel an open connection and terminate the thread
    public void cancel() {
        try {
            bluetoothSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

> Note:
>
> Always call "[cancelDiscovery()](http://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html#cancelDiscovery%28%29)" method of the "BluetoothAdapter" to cancel any device discovery activity before attempting any connection request lest it should slow down the process and cause time-out failure.

Then call it from the main UI thread like this:

Hide    Copy Code

```
ConnectingThread t = new ConnectingThread(bluetoothDevice);
t.start();
```

Once the connection is successfully establish, the two connected devices can then start exchanging data using their own "BluetoothSockets" in a new thread. The example below shows a dummy method "manageBluetoothConnection()" that will initiate the thread for transferring data.

Hide    Copy Code

```
// Code to manage the connection in a separate thread
/*
    manageBluetoothConnection(bluetoothSocket);
*/
```

#### Managing a Connection

Once a connection has been successfully established, the server device and the client device will each have a connected "BluetoothSocket". They can then share data using the "BluetoothSocket". Generally, it goes like this:

1. Obtain an [InputStream](http://developer.android.com/reference/java/io/InputStream.html) handle and an [OutputStream](http://developer.android.com/reference/java/io/OutputStream.html) handle from the "BluetoothSocket" by calling [getInputStream()](http://developer.android.com/reference/android/bluetooth/BluetoothSocket.html#getInputStream%28%29) and [getOutputStream()](http://developer.android.com/reference/android/bluetooth/BluetoothSocket.html#getOutputStream%28%29) respectively.
2. Start listening for incoming data via the "[read()](http://developer.android.com/reference/java/io/InputStream.html#read%28byte[]%29)" method of the "InputStream" handle.
3. Sending of data is done via the "[write()](http://developer.android.com/reference/java/io/OutputStream.html#write%28byte[]%29)" method of the "OutputStream" handle.

As both read() and write() are blocking calls, they should be run in separate threads. I shall leave the actual coding to you as homework. delve into the detail of the I/O stream operation as it is beyond the scope of this article. ;P

#### Testing Bluetooth

The complete code for the "BluetoothActivity.java" can be found in the download.

In the code, the app has set up the device as both a server and a client. Launch this app on different Android devices, and then turn on the Bluetooth using the "ToggleButton". Each device will open a server socket and start listening for connection while at the same time it can also initiate connection to other device that runs this app. They should be able to discover and display one another on the "ListView". When you click on the one of the device item on the "ListView", it will start to initiate connection request to this selected device. If the two devices have not been paired, the user will receive a pairing request dialog during the connection procedure, as shown in Figure 9. The connection attempt will block until the user has accepted the pairing, or will fail if the user rejects the pairing.

| ![img](https://www.codeproject.com/KB/android/814814/bluetooth09.png) |
| ---------------------------------------- |
| Figure 9: Request for Pairing            |

| ![img](https://www.codeproject.com/KB/android/814814/bluetooth10.png) |
| ---------------------------------------- |
| Figure 10: Bluetooth Connection Established |

## Wi-Fi

The Android platform provides "[WifiManager](http://developer.android.com/reference/android/net/wifi/WifiManager.html)" API to manage all aspects of Wi-Fi connectivity on an Android device. Through this API, an app can:

- Scan access points and obtain information, such as BSSID, SSID, frequency, key management, and encryption schemes.
- Establish or terminate a Wi-Fi connection.
- Configure networks.

"WifiManager" also defines various Intent actions that can be broadcasted upon any change in the Wi-Fi state, such as "SCAN_RESULTS_AVAILABLE_ACTION" and "NETWORK_STATE_CHANGED_ACTION".

#### The Wi-Fi Agenda

We will create an Activity to perform the following Wi-Fi functionalities:

- Enable/disable Wi-Fi.


- Discover access points.
- List the information of those found access points in a "ListView".

In the current project,

- Create a new Activity called "WiFiActivity" using "RelativeLayout" as layout that contains a "ToggleButton" and a "ListView".

- The "activity_wi_fi.xml" is shown below:

  Hide    Shrink ![img](https://www.codeproject.com/images/arrow-up-16.png)    Copy Code

  ```
  <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:paddingLeft="@dimen/activity_horizontal_margin"
      android:paddingRight="@dimen/activity_horizontal_margin"
      android:paddingTop="@dimen/activity_vertical_margin"
      android:paddingBottom="@dimen/activity_vertical_margin"
      tools:context="com.peterleow.androidconnection.WiFiActivity">

      <ToggleButton
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:id="@+id/toggleButton"
          android:textOn="Wi-Fi On"
          android:textOff="Wi-Fi Off"
          android:onClick="onToggleClicked"
          android:layout_alignParentTop="true"
          android:layout_alignParentLeft="true"
          android:layout_alignParentStart="true" />

      <ListView
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:id="@+id/listView"
          android:layout_centerHorizontal="true"
          android:layout_below="@+id/toggleButton" />

  </RelativeLayout>
  ```

  The preview of "WiFiActivity" should look like that in Figure 11.

  | ![img](https://www.codeproject.com/KB/android/814814/wifi01.png) |
  | ---------------------------------------- |
  | Figure 11: Preview of WiFiActivity       |

#### Granting Wi-Fi Access

Before using Wi-Fi in an app, you must declare the <user-permission>'s for "[ACCESS_WIFI_STATE](http://developer.android.com/reference/android/Manifest.permission.html#ACCESS_WIFI_STATE)" and "[CHANGE_WIFI_STATE](http://developer.android.com/reference/android/Manifest.permission.html#CHANGE_WIFI_STATE)" in the app's manifest file. You need this "ACCESS_WIFI_STATE" permission to access information about Wi-Fi networks and "CHANGE_WIFI_STATE" to change Wi-Fi connectivity state. For example:

Hide    Copy Code

```
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
```

#### Verifying Wi-Fi Support

Before any attempt to use Wi-Fi functionalities in an app, it must verify whether the device supports Wi-Fi. To do that, you have to get a handle of the "[WifiManager](http://developer.android.com/reference/android/net/wifi/WifiManager.html)" class by calling "[Context.getSystemService(Context.WIFI_SERVICE)](http://developer.android.com/reference/android/content/Context.html#getSystemService%28java.lang.String%29)". For example:

Hide    Copy Code

```
WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
if (wifiManager == null) {
    // Device does not support Wi-Fi
} else {
    // Any vaild Wi-Fi operations
}

```

#### Toggling Wi-Fi

Once a "WifiManager" handle is obtained, the app can toggle the on/off state of the Wi-Fi on the device by calling the "[setWifiEnabled()](http://developer.android.com/reference/android/net/wifi/WifiManager.html#setWifiEnabled%28boolean%29)" method of the "WifiManager" handle which takes a boolean parameter of "true" to turn on and "false" to turn off the Wi-Fi. For example:

Hide    Copy Code

```
wifiManager.setWifiEnabled(true); // To turn on the Wi-Fi
```

#### Discovering Access Points

Next, make the app discover access points by calling the "[startScan()](http://developer.android.com/reference/android/net/wifi/WifiManager.html#startScan%28%29)" method of the "WifiManager" handle. For example:

Hide    Copy Code

```
wifiManager.startScan();
```

The process is asynchronous and the "startScan()" method will immediately return with a boolean indicating whether discovery has successfully started. How can the app be notified of the outcome of the discovery process? Here comes the role of "[BroadcastReceiver](http://developer.android.com/reference/android/content/BroadcastReceiver.html)" class.

When the scan has completed, the Android system will broadcast the "[SCAN_RESULTS_AVAILABLE_ACTION](http://developer.android.com/reference/android/net/wifi/WifiManager.html#SCAN_RESULTS_AVAILABLE_ACTION)" Intent. The results of the scan can then be obtained by calling the "[getScanResults()](http://developer.android.com/reference/android/content/Context.html#getSystemService%28java.lang.String%29)" method of the "WifiManager" handle.

In order to capture this broadcast, you app must register a "BroadcastReceiver" for this "SCAN_RESULTS_AVAILABLE_ACTION" Intent. First, you create a "BroadcastReceiver" class like this:

Hide    Copy Code

```
class WiFiScanReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            List<ScanResult> wifiScanResultList = wifiManager.getScanResults();
            for(int i = 0; i < wifiScanResultList.size(); i++){
                String hotspot = (wifiScanResultList.get(i)).toString();
                adapter.add(hotspot);
            }
        }
    }
}
```

then register this "BroadcastReceiver" class for the "SCAN_RESULTS_AVAILABLE_ACTION" Intent like this:

Hide    Copy Code

```
IntentFilter filter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
registerReceiver(wifiReciever, filter);
```

> Note:
>
> As the app will not be receiving any Intent when paused, always unregister the "BroadcastReceiver" on "[Activity.onPause()](http://developer.android.com/reference/android/app/Activity.html#onPause%28%29)" and register it on "[Activity.onResume()](http://developer.android.com/reference/android/app/Activity.html#onResume%28%29)", and this will reduce unnecessary system overhead.

When a scan has completed, the app will be notified through the ""BroadcastReceiver". The app can then obtain the scan result by calling the "[getScanResults()](http://developer.android.com/reference/android/net/wifi/WifiManager.html#getScanResults%28%29)" method of the "WifiManager" handle and populate the result in the "ListView" via an "ArrayAdapter". Each discovered access point will be returned in the form of a "[ScanResult](http://developer.android.com/reference/android/net/wifi/ScanResult.html)" object that contains the following information about that access point:

- BSSID
- SSID
- Capabilities
- Frequency
- Level
- Timestamp

#### Testing Wi-Fi

The complete code for the "WiFiActivity.java" can be found in the download.

Before you can test it on a real device, you will have to create navigation from the "MainActivity". Do these:

- In the "activity_main.xml", add a second <Button> with the "android:onClick" attribute as shown:

  Hide    Copy Code

  ```
  <Button
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:text="@string/wifi"
      android:id="@+id/btnWifi"
      android:onClick="getWifiActivity"
      android:layout_below="@+id/btnBluetooth"
      android:layout_centerHorizontal="true" />
  ```

  The "MainActivity" page should look like that in Figure 12.

  | ![img](https://www.codeproject.com/KB/android/814814/wifi015.png) |
  | ---------------------------------------- |
  | Figure 12: Preview of MainActivity       |

- In the "MainActivity.java", add the "getBluetoothActivity()" method to navigate to the "WiFiActivity" when the button is clicked.

  Hide    Copy Code

  ```
  public void getWifiActivity(View view) {
      Intent intent = new Intent(getApplicationContext(), WiFiActivity.class);
      startActivity(intent);
  }
  ```

Launch the app on a real device, navigate to the "WiFiActivity", click on the "ToggleButton" to turn on the Wi-Fi, and it will start to scan for access points. When the scan is over, you will see a list of discovered access points in the "ListView". An example is shown in Figure 13.

| ![img](https://www.codeproject.com/KB/android/814814/wifi02.png) |
| ---------------------------------------- |
| Figure 13: Discovered Access Points      |

## NFC

NFC or Near Field Communication is a set of short-range wireless technologies. It allows you to share small amount of data between an NFC tag and an NFC-enabled device or between NFC-enabled devices within a short distance of no more than 4cm. NFC tags vary considerably in terms of capabilities and data formats. The capabilities could range from passive tags that offer just read and write by Android devices to more intelligent tags that can interact with code executing on the tags. Despite the many data formats, most of the Android framework APIs are based on this standard called "[NDEF](http://developer.android.com/reference/android/nfc/tech/Ndef.html)" (NFC Data Exchange Format). NDEF data is encapsulated inside a message implemented as an [NdefMessage](http://developer.android.com/reference/android/nfc/NdefMessage.html) object which in turn contains one or more records as [NdefRecord](http://developer.android.com/reference/android/nfc/NdefRecord.html) objects.

An Android app can make use of the NFC technologies to perform the following operations:

- Read and write to NFC tags.
- Perform peer-to-peer (P2P) data exchange between two NFC-enabled devices. This feature is called "Android Beam" and was introduced in API level 14. It allows the rapid short-range exchange of data like contact, bookmark, video, and many more.
- Turn an NFC-enabled device into an NFC card so that it can be accessed by an external NFC reader. For example, paying for your groceries by tapping your NFC-enabled Android device on a NFC point-of-sale terminal..

#### The NFC Agenda

We will create an Activity to implement the "Android Beam" feature to perform simple peer-to-peer exchange of "NDEF" message over NFC between two Android devices. The app that wants to beam data to another device must be in the foreground and the device that receives the data must not be locked. Unlike Bluetooth which requires manual discovery and pairing, NFC connection is automatically started when two devices come into range. At that point, the beaming device will display the "Touch to Beam" message. The user can then proceed to beam by touching the screen or just pull the devices away to cancel the beaming.

In the current project,

- Create a new Activity called "NfcActivity" using "RelativeLayout" as layout that contains an "EditText" text field.

- The "activity_nfc.xml" is shown below:

  Hide    Copy Code

  ```
  <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:paddingLeft="@dimen/activity_horizontal_margin"
      android:paddingRight="@dimen/activity_horizontal_margin"
      android:paddingTop="@dimen/activity_vertical_margin"
      android:paddingBottom="@dimen/activity_vertical_margin"
      tools:context="com.peterleow.androidconnection.NfcActivity">

      <EditText
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:textAppearance="?android:attr/textAppearanceLarge"
          android:id="@+id/editText"
          android:hint="Type something..."
          android:layout_alignParentTop="true"
          android:layout_centerHorizontal="true"
          android:layout_marginTop="39dp" />

  </RelativeLayout>
  ```

  The preview of "NfcActivity" should look like that in Figure 14.

  | ![img](https://www.codeproject.com/KB/android/814814/nfc01.png) |
  | ---------------------------------------- |
  | Figure 14: Preview of NfcActivity        |

#### Granting NFC Access

Before using NFC in an app, you must declare the <user-permission> for "[NFC](http://developer.android.com/reference/android/Manifest.permission.html#NFC)" in the app's manifest file. You need this "NFC" permission to perform I/O operations over NFC as shown:

Hide    Copy Code

```
<uses-permission android:name="android.permission.NFC" />
```

As not all Android devices provide NFC support, to make sure that the app only shows up in Google Play for those devices that have NFC Hardware, add the <uses-feature> in the app's manifest** **file like this:

Hide    Copy Code

```
<uses-feature android:name="android.hardware.nfc" android:required="true" />
```

#### Enabling Android Beam

Before any attempt to use NFC in an app, it must verify whether the device supports NFC. To do that, you have to get a handle of the "[NfcAdapter](http://developer.android.com/reference/android/nfc/NfcAdapter.html)" class by calling "[NfcAdapter.getDefaultAdapter(Context)](http://developer.android.com/reference/android/nfc/NfcAdapter.html#getDefaultAdapter%28android.content.Context%29)". For example:

Hide    Copy Code

```
// Check for NFC support
NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
if (nfcAdapter == null) {
    // Device does not support NFC
}
```

#### Preparing to Beam...

To beam an NDEF message, an app must first register the "[setNdefPushMessageCallback()](http://developer.android.com/reference/android/nfc/NfcAdapter.html#setNdefPushMessageCallback%28android.nfc.NfcAdapter.CreateNdefMessageCallback,%20android.app.Activity,%20android.app.Activity...%29)" method with the "[NfcAdapter](http://developer.android.com/reference/android/nfc/NfcAdapter.html)" handle. This method accepts a callback that contains a "[createNdefMessage()](http://developer.android.com/reference/android/nfc/NfcAdapter.CreateNdefMessageCallback.html#createNdefMessage%28android.nfc.NfcEvent%29)" which will be called when a device comes into range to beam data to. It allows the app to generate NDEF message on the fly. This method should be registered during the Activity's [onCreate()](http://developer.android.com/reference/android/app/Activity.html#onCreate%28android.os.Bundle%29)" process. For example:

Hide    Copy Code

```
nfcAdapter.setNdefPushMessageCallback(this, this);
```

and an example of a callback that generates the NDEF message to send:

Hide    Copy Code

```
public NdefMessage createNdefMessage(NfcEvent event) {
    EditText editText = (EditText) findViewById(R.id.editText);
    String text = editText.getText().toString();
    NdefMessage ndefMessage = new NdefMessage(
                        new NdefRecord[] { createMime(
                               "application/vnd.com.peterleow.androidconnection", text.getBytes())
                      });
    return ndefMessage;
}
```

The "createNdefMessage()" create an "NdefRecord" object to contain the text from the "EditText" as payload and the custom MIME type of "application/vnd.com.peterleow.androidconnection". This "NdefRecord" is then placed inside an "NdefMessage" object. An "NdefMessage" object could have contained multiple "NdefMessage" objects with the first "NdefRecord" containing a TNF (Type Name Field) for mapping the NDEF message to a MIME type or URI. If successful, it encapsulates that information inside an [ACTION_NDEF_DISCOVERED](http://developer.android.com/reference/android/nfc/NfcAdapter.html#ACTION_NDEF_DISCOVERED) Intent along with the actual payload for beaming.

#### Preparing to Receive...

To start an app to handle an NDEF message that is beamed from another device, the app has to filter for the "[ACTION_NDEF_DISCOVERED](http://developer.android.com/reference/android/nfc/NfcAdapter.html#ACTION_NDEF_DISCOVERED)" Intent in the manifest file. The following example filters for "ACTION_NDEF_DISCOVERED" Intent with a MIME type of "application/vnd.com.peterleow.androidconnection" matching that in the "NdefMessage".

Hide    Copy Code

```
<activity
    android:name=".NfcActivity"
    android:label="@string/title_activity_nfc"
    android:launchMode="singleTask" >
        <intent-filter>
            <action android:name="android.nfc.action.NDEF_DISCOVERED"/>
            <category android:name="android.intent.category.DEFAULT"/>
            <data android:mimeType="application/vnd.com.peterleow.androidconnection"/>
        </intent-filter>
```

The Activity that responds to the NFC beam should check for the [ACTION_NDEF_DISCOVERED](http://developer.android.com/reference/android/nfc/NfcAdapter.html#ACTION_NDEF_DISCOVERED) Intent and retrieves the NDEF message from the "[EXTRA_NDEF_MESSAGES](http://developer.android.com/reference/android/nfc/NfcAdapter.html#EXTRA_NDEF_MESSAGES)" Intent Extra. For example:

Hide    Copy Code

```
public void onResume() {
    super.onResume();
    // Check to see that the Activity started due to an Android Beam
    Intent intent = getIntent();
    if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
        Parcelable[] ndefMessageArray = intent.getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
        NdefMessage ndefMessage = (NdefMessage) ndefMessageArray[0];
        Toast.makeText(this, new String(ndefMessage.getRecords()[0].getPayload()), Toast.LENGTH_LONG).show();
    }
}
```

#### Testing Android Beam

The complete code for the "NfcActivity.java" can be found in the download.

Before you can test it on a real device, you will have to create navigation from the "MainActivity". Do these:

- In the "activity_main.xml", add a third <Button> with the "android:onClick" attribute as shown:

  Hide    Copy Code

  ```
  <Button
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:text="@string/nfc"
      android:id="@+id/btnNfc"
      android:onClick="getNfcActivity"
      android:layout_below="@+id/btnWifi"
      android:layout_centerHorizontal="true" />
  ```

  The "MainActivity" page should look like that in Figure 15.

  | ![img](https://www.codeproject.com/KB/android/814814/nfc02.png) |
  | ---------------------------------------- |
  | Figure 15: Preview of MainActivity       |

- In the "MainActivity.java", add the "getNfcActivity()" method to navigate to the "NfcActivity" when the button is clicked.

  Hide    Copy Code

  ```
  public void getNfcActivity(View view) {
      Intent intent = new Intent(getApplicationContext(), NfcActivity.class);
      startActivity(intent);
  }
  ```

Deploy the app to two real Android devices, called them Sender and Receiver respectively. Follow these steps to test:

1. Turn on NFC on both devices.
2. Launch the app on both devices.
3. On Sender, navigate to the "NfcActivity" from the "ManiActivity", type something into the text field.
4. On Receiver, navigate away from the app so that it does not show in the foreground.
5. Put the two devices back to back. When they are close enough, the Sender will display a "Touch to beam" message like that in Figure 16. Touch the screen and the text that you have entered in the text field will be beamed over to the Receiver.
6. On the Receiver side, the "NfcActivity" will show up in the foreground and display the received text as shown in Figure 17.

| ![img](https://www.codeproject.com/KB/android/814814/nfc03.png) | ![img](https://www.codeproject.com/KB/android/814814/nfc04.png) |
| ---------------------------------------- | ---------------------------------------- |
| Figure 16: The Sender                    | Figure 17: The Receiver                  |

Well done! you have successfully implemented an Android Beam to transfer data between two Android devices using NFC technology.

## Mobile Data

The Android platform provides "[ConnectivityManager](http://developer.android.com/reference/android/net/ConnectivityManager.html#getActiveNetworkInfo%28%29)" API that allows an app to query the state of network connectivity, including mobile data. Let's create a simple Activity to enable/disable mobile data.

- Create a new Activity called "MobileDataActivity" using "RelativeLayout" as layout that contains a "ToggleButton".

- The "activity_mobile_data.xml" is shown below:

  Hide    Copy Code

  ```
  <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:paddingLeft="@dimen/activity_horizontal_margin"
      android:paddingRight="@dimen/activity_horizontal_margin"
      android:paddingTop="@dimen/activity_vertical_margin"
      android:paddingBottom="@dimen/activity_vertical_margin"
      tools:context="com.peterleow.androidconnection.MobileDataActivity">

      <ToggleButton
          android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:id="@+id/toggleButton"
          android:textOn="Mobile Data On"
          android:textOff="Mobile Data Off"
          android:onClick="onToggleClicked"
          android:layout_alignParentTop="true"
          android:layout_alignParentLeft="true"
          android:layout_alignParentStart="true"
          android:checked="false" />

  </RelativeLayout>
  ```

  The preview of "MobileDataActivity" should look like that in Figure 18.

  | ![img](https://www.codeproject.com/KB/android/814814/mobile_01.png) |
  | ---------------------------------------- |
  | Figure 18: Preview of MobileDataActivity |

#### Granting Network Access

Before accessing and modify network state in an app, you must declare the <user-permission> for "[ACCESS_NETWORK_STATE](http://developer.android.com/reference/android/Manifest.permission.html#ACCESS_NETWORK_STATE)" in the app's manifest file. You need this "[ACCESS_NETWORK_STATE](http://developer.android.com/reference/android/Manifest.permission.html#ACCESS_NETWORK_STATE)" permission to access information about networks. For example:

Hide    Copy Code

```
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

```

#### Toggling Mobile Data

The app will not modify the mobile data connectivity directly. Instead it will invoke the system UI (Figure 19) through "startActivityForResult()" to let the user make the selection whenever the toggle button is clicked.

| ![img](https://www.codeproject.com/KB/android/814814/mobile_02.png) |
| ---------------------------------------- |
| Figure 19: System UI for Data Usage      |

The code to do this is shown below:

Hide    Copy Code

```
public void onToggleClicked(View view) {
    Intent intent = new Intent();
    intent.setClassName("com.android.settings",
            "com.android.settings.Settings$DataUsageSummaryActivity");
    startActivityForResult(intent, 1);
}
```

When the system UI is finished, your activity be notified through the "onActivityResult()" callback. In the "onActivityResult()", it will first obtain an instance of "[ConnectivityManager](http://developer.android.com/reference/android/net/ConnectivityManager.html#getActiveNetworkInfo%28%29)" by calling "[Context.getSystemService(Context.CONNECTIVITY_SERVICE)](http://developer.android.com/reference/android/content/Context.html#getSystemService%28java.lang.String%29)". For example:

Hide    Copy Code

```
ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
```

Obtain a "[NetworkInfo](http://developer.android.com/reference/android/net/NetworkInfo.html)" object which contains the status of the network connection by calling "[getActiveNetworkInfo()](http://developer.android.com/reference/android/net/ConnectivityManager.html#getActiveNetworkInfo%28%29)" of the "ConnectivityManager" instance. For example:

Hide    Copy Code

```
NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
```

If the "[NetworkInfo](http://developer.android.com/reference/android/net/NetworkInfo.html)" object is not null, that set the state of the toggle button to be the same as to the connection state of the network by calling "[isConnected()](http://developer.android.com/reference/android/net/NetworkInfo.html#isConnected%28%29)" of the "[NetworkInfo](http://developer.android.com/reference/android/net/NetworkInfo.html)" object. For example:

Hide    Copy Code

```
if (networkInfo != null) {
    toggleButton.setChecked(networkInfo.isConnected());
}
```

#### Testing Mobile Data

The complete code for the "MobileDataActivity.java" can be found in the download.

Before you can test it on a real device, you will have to create navigation from the "MainActivity". Do these:

- In the "activity_main.xml", add a fourth <Button> with the "android:onClick" attribute as shown:

  Hide    Copy Code

  ```
  <Button
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:text="@string/mobile_data"
      android:id="@+id/btnMobileData"
      android:onClick="getMobileDataActivity"
      android:layout_below="@+id/btnNfc"
      android:layout_centerHorizontal="true" />
  ```

  The "MainActivity" page should look like that in Figure 20.

  | ![img](https://www.codeproject.com/KB/android/814814/mobile_00.png) |
  | ---------------------------------------- |
  | Figure 20: Preview of MainActivity       |

- In the "MainActivity.java", add the "getMobileDataActivity()" method to navigate to the "MobileDataActivity" when the button is clicked.

  Hide    Copy Code

  ```
  public void getMobileDataActivity(View view) {
      Intent intent = new Intent(getApplicationContext(), MobileDataActivity.class);
      startActivity(intent);
  }
  ```

## SIP

SIP or Session Initiation Protocol is a protocol that enables an application to make outgoing and incoming voice calls over the computer network. SIP can be used to establish sessions for audio/video conferencing, instant messaging, file transfer, online gaming, and call forwarding over IP networks.

Android provides an API that supports the full SIP protocol that your app can use to incorporate SIP-based internet telephony features. SIP provides the following major functions:

- Manage call.
- Establish user location.
- Negotiate features to be supported among the call participants.

#### Development Requirements

Before developing a SIP app, you must get ready the following ingredients in order to test the app:

- At least two real Android devices that are running Android 2.3 ((API level 9) ) or higher.
- Each device must have an Internet connection. That is the reason why you cannot test a SIP app on emulator.
- Each participant in the SIP session must have a valid SIP account. There are many SIP providers that offer free account, just consult Google.

#### SIP API Overview

The [Android SIP API](http://developer.android.com/reference/android/net/sip/package-summary.html) consists of nine classes and one interface as summarized in Table 1.

| Class/Interface                          | Description                              |
| ---------------------------------------- | ---------------------------------------- |
| [SipAudioCall](http://developer.android.com/reference/android/net/sip/SipAudioCall.html) | Provides methods to handle audio calls over SIP. |
| [SipAudioCall.Listener](http://developer.android.com/reference/android/net/sip/SipAudioCall.Listener.html) | Represents a listener to keep track of events relating to a SIP call, and call the corresponding event handlers when certain events occur. For example, "[onRinging()](http://developer.android.com/reference/android/net/sip/SipAudioCall.Listener.html#onRinging%28android.net.sip.SipAudioCall,%20android.net.sip.SipProfile%29)" is called when a new call comes in. |
| [SipErrorCode](http://developer.android.com/reference/android/net/sip/SipErrorCode.html) | Contains error codes for SIP actions.    |
| [SipManager](http://developer.android.com/reference/android/net/sip/SipManager.html) | Provides methods to access APIs classes for performing the various SIP tasks, such as creating a SIP session, making calls, and receiving calls. |
| [SipProfile](http://developer.android.com/reference/android/net/sip/SipProfile.html) | Represents a SIP profile, including a SIP account, domain and server information. |
| [SipProfile.Builder](http://developer.android.com/reference/android/net/sip/SipProfile.Builder.html) | Represents a helper class for creating a SipProfile. |
| [SipSession](http://developer.android.com/reference/android/net/sip/SipSession.html) | Represents a SIP session.                |
| [SipSession.Listener](http://developer.android.com/reference/android/net/sip/SipSession.Listener.html) | Represents a listener to keep track of events relating to a SIP session, and call the corresponding event handlers when certain events occur. For example, "[onRegistering()](http://developer.android.com/reference/android/net/sip/SipSession.Listener.html#onRegistering%28android.net.sip.SipSession%29)" is called when a registration request is sent. |
| [SipSession.State](http://developer.android.com/reference/android/net/sip/SipSession.State.html) | Contains constants that represent the various SIP session states, such as "[INCOMING_CALL](http://developer.android.com/reference/android/net/sip/SipSession.State.html#INCOMING_CALL)", "[OUTGOING_CALL](http://developer.android.com/reference/android/net/sip/SipSession.State.html#OUTGOING_CALL)l". |
| [SipRegistrationListener](http://developer.android.com/reference/android/net/sip/SipRegistrationListener.html) | This is an interface that represents a listener to keep track of events relating to a SIP registration, and call the corresponding event handlers when certain events occur. For example, "[onRegistrationDone()](http://developer.android.com/reference/android/net/sip/SipRegistrationListener.html#onRegistrationDone%28java.lang.String,%20long%29)" is called when the registration succeeded. |

#### Learning from "SipDemo"

Our subsequent discussion on SIP will be based on a demo app called "[SipDemo](http://docs.huihoo.com/android/2.3/resources/samples/SipDemo/index.html)" developed by "The Android Open Source Project". The "SipDemo" demonstrates the various functionalities of the SIP API. It is an excellent learning material and I will borrow code excerpts from "SipDemo" to reinforce our discussion. You should get a copy of this "SipDemo" project and install it in your Android Studio and try it out.

The "SipDemo" is included in the "*sdk/samples/android-10/*" of the Android Studio directory. If you cannot find it, you can download it using the SDK Manager (Figure 21). In the Android Studio, click on **\*Tools*** > **\*Android*** > **\*SDK Manager***, then select the "SDK Platform" and "Samples for SDK" options under the Android 2.3.3 (API10) version to install.

| ![img](https://www.codeproject.com/KB/android/814814/sdk_mgr.png) |
| ---------------------------------------- |
| Figure 21: SDK Manager                   |

Next, import the "SipDemo" into Android Studio by choosing **File **>** Import Project**.

Once it is successfully set up, launch it on a real Android device and you should see these screens as shown in Figures 22, 23, and 24.

| ![img](https://www.codeproject.com/KB/android/814814/sipdemo01.png) |
| ---------------------------------------- |
| Figure 22: SipDemo Home                  |

| ![img](https://www.codeproject.com/KB/android/814814/sipdemo02.png) |
| ---------------------------------------- |
| Figure 23: Setting SIP Profile           |

| ![img](https://www.codeproject.com/KB/android/814814/sipdemo03.png) |
| ---------------------------------------- |
| Figure 24: Making a Call                 |

#### Granting SIP Access

Before using SIP in an app, you must declare the "<user-permission>" in the app's manifest file as shown:

Hide    Copy Code

```
<uses-permission android:name="android.permission.USE_SIP" />
<uses-permission android:name="android.permission.INTERNET" />
```

As not all Android devices provide SIP support, to make sure that the app only shows up in Google Play for those devices that support SIP, add the <uses-feature> in the app's manifest** **file like this:

Hide    Copy Code

```
<uses-feature android:name="android.hardware.sip.voip" android:required="true" />
```

#### Getting the API Manager

The most important class in the SIP API is the "[SipManager](http://developer.android.com/reference/android/net/sip/SipManager.html)". This is the manager for managing all the other SIP API classes. Before the app can use the SIP API to do any work, the app must first create a "SipManager" handle that will takes care of the following functions:

- Register and unregister with a SIP service provider.
- Initiate a session.
- Make calls.
- Receive calls.
- Verify session connectivity.

An app will create a "SipManager" handle using the "[newInstance()](http://developer.android.com/reference/android/net/sip/SipManager.html#newInstance%28android.content.Context%29)" method. See example in "SipDemo":

Hide    Copy Code

```
public SipManager manager = null;
// other code
if(manager == null) {
    manager = SipManager.newInstance(this);
}

```

#### Creating a Local SIP Profile

Create a "[SipProfile](http://developer.android.com/reference/android/net/sip/SipProfile.html)" object that defines a local SIP profile, including a SIP account, and domain and server information, using the "[SipProfile.Builder](http://developer.android.com/reference/android/net/sip/SipProfile.Builder.html)" helper class. For example:

Hide    Copy Code

```
public SipProfile me = null;
// other code
SipProfile.Builder builder = new SipProfile.Builder(username, domain);
builder.setPassword(password);
me sipProfile = builder.build();

```

#### Creating a PendingIntent

Create an "[Intent Action](http://developer.android.com/reference/android/content/Intent.html)" ("android.SipDemo.INCOMING_CALL" in "SipDemp") and assign it to a "[PendingIntent](http://developer.android.com/reference/android/app/PendingIntent.html)" via "[getBroadcast()](http://developer.android.com/reference/android/app/PendingIntent.html#getBroadcast%28android.content.Context,%20int,%20android.content.Intent,%20int%29)" which will perform a broadcast of this "Intent Action" across the system at the receiving device. A PendingIntent specifies an action to take in the future. It can be passed to another app to execute it. See example in "SipDemo":

Hide    Copy Code

```
Intent i = new Intent();
i.setAction("android.SipDemo.INCOMING_CALL");
PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, Intent.FILL_IN_DATA);
```

#### Registering with a SIP Server

Register and open this "SipProfile" object by calling the "[open()](http://developer.android.com/reference/android/net/sip/SipManager.html#open%28android.net.sip.SipProfile,%20android.app.PendingIntent,%20android.net.sip.SipRegistrationListener%29)" method of the "SipManager" handle and passing it the "SipProfile" object and the "PendingIntent" handle as parameters. This will open the local SIP profile for making and receiving SIP calls. See example in "SipDemo":

Hide    Copy Code

```
manager.open(me, pi, null);
```

#### Listening for Registration Events

Set up a "[SipRegistrationListener](http://developer.android.com/reference/android/net/sip/SipManager.html#setRegistrationListener%28java.lang.String,%20android.net.sip.SipRegistrationListener%29)" on the "SipManager" handle to keep track of the SIP registration progress and outcome. See example in "SipDemo":

Hide    Copy Code

```
manager.setRegistrationListener(me.getUriString(), new SipRegistrationListener() {
    public void onRegistering(String localProfileUri) {
        updateStatus("Registering with SIP Server...");
    }
  
    public void onRegistrationDone(String localProfileUri, long expiryTime) {
        updateStatus("Ready");
    }
  
    public void onRegistrationFailed(String localProfileUri, int errorCode, String errorMessage) {
        updateStatus("Registration failed.  Please check settings.");
    }
});
```

#### Setting up Intent Filter to Intercept Incoming Calls

When the "SigProfile" receives a new SIP call, the "PendingIntent" that is sent by the caller will perform a broadcast which will be intercepted by an "[Intent Filter](http://developer.android.com/guide/topics/manifest/intent-filter-element.html)" to filter the "Intent Action" ("android.SipDemo.INCOMING_CALL" in "SipDemo") and trigger a subclass ("callReceiver" in "SipDemo") of a "BroadcastReceiver" ("IncomingCallReceiver" in "SipDemo") that is registered to respond to this "Intent Action". The "BroadcastReceiver" class is covered in the next section. You can either specify this "Intent Filter" in the app's manifest file or in code. In the "SipDemo", this was created in code as shown:

Hide    Copy Code

```
public IncomingCallReceiver callReceiver;
// other code
IntentFilter filter = new IntentFilter();
filter.addAction("android.SipDemo.INCOMING_CALL");
callReceiver = new IncomingCallReceiver();
this.registerReceiver(callReceiver, filter);
```

#### Receiving Calls

In order to receive and respond to a call, the app must:

1. Create a "BroadcastReceiver" class ("IncomingCallReceiver" in "SipDemo") that is capable of responding to an "Intent Action" ("android.SipDemo.INCOMING_CALL") indicating an incoming call. See code excerpt from "SipDemo":	

   Hide    Shrink ![img](https://www.codeproject.com/images/arrow-up-16.png)    Copy Code

   ```
   /**
    * Listens for incoming SIP calls, intercepts and hands them off to WalkieTalkieActivity.
    */
   public class IncomingCallReceiver extends BroadcastReceiver {
       /**
        * Processes the incoming call, answers it, and hands it over to the
        * WalkieTalkieActivity.
        * @param context The context under which the receiver is running.
        * @param intent The intent being received.
        */
       @Override
       public void onReceive(Context context, Intent intent) {
           SipAudioCall incomingCall = null;
           try {
               SipAudioCall.Listener listener = new SipAudioCall.Listener() {
                   @Override
                   public void onRinging(SipAudioCall call, SipProfile caller) {
                       try {
                           call.answerCall(30);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               };
               WalkieTalkieActivity wtActivity = (WalkieTalkieActivity) context;
               incomingCall = wtActivity.manager.takeAudioCall(intent, listener);
               incomingCall.answerCall(30);
               incomingCall.startAudio();
               incomingCall.setSpeakerMode(true);
               if(incomingCall.isMuted()) {
                   incomingCall.toggleMute();
               }
               wtActivity.call = incomingCall;
               wtActivity.updateStatus(incomingCall);
           } catch (Exception e) {
               if (incomingCall != null) {
                   incomingCall.close();
               }
           }
       }
   }
   ```

2. Declare a <receiver> for the previously created "BroadcastReceiver" class in the app's manifest file. See example in "SipDemo":

   Hide    Copy Code

   ```
   <receiver android:name=".IncomingCallReceiver" android:label="Call Receiver"/>
   ```

#### Making an Audio Call

For the app to make a SIP call, the app must

1. First, set up a "[SipAudioCall.Listener](http://developer.android.com/reference/android/net/sip/SipAudioCall.Listener.html)" to take care of all the events relating to the call, such as "[onRinging()](http://developer.android.com/reference/android/net/sip/SipAudioCall.Listener.html#onRinging%28android.net.sip.SipAudioCall,%20android.net.sip.SipProfile%29)" when a new call comes in. See code snippet in "SipDemo":	Hide    Copy Code`SipAudioCall.Listener listener = new SipAudioCall.Listener() {    @Override    public void onCallEstablished(SipAudioCall call) {        call.startAudio();        call.setSpeakerMode(true);        call.toggleMute();        updateStatus(call);    }    @Override    public void onCallEnded(SipAudioCall call) {        updateStatus("Ready.");    }};`	

2. Once the "SipAudioCall.Listener" is set up, the app can proceed to make the call by calling the "[makeAudioCall()l](http://developer.android.com/reference/android/net/sip/SipManager.html#makeAudioCall%28java.lang.String,%20java.lang.String,%20android.net.sip.SipAudioCall.Listener,%20int%29)" method of the "SipManager" by passing the local SIP profile (the caller), the peer SIP profile (the callee), the "SipAudioCall.Listener", and a timeout integer in seconds as parameters. See example in "SipDemo":

   Hide    Copy Code

   ```
   call = manager.makeAudioCall(me.getUriString(), sipAddress, listener, 30);
   ```

#### Closing and Unregistering Profile

When the profile is no longer need, such as during the "onDestroy()" of the Activity, it should be close to free up memory and unregister from the server. This is how it is done in "SipDemo":

Hide    Copy Code

```
public void closeLocalProfile() {
    if (manager == null) {
        return;
    }
    try {
        if (me != null) {
            manager.close(me.getUriString());
        }
    } catch (Exception ee) {
        Log.d("WalkieTalkieActivity/onDestroy", "Failed to close local profile.", ee);
    }
}
```

## USB

Android supports a variety of USB peripherals in two modes - USB Host and USB Accessory.

- **USB Host** - The Android-powered device acts as the host and powers the bus. Examples of such USB devices include keyboards and game controllers.
- **USB Accessory **- The external USB hardware acts as the host and powers the bus. This mode allows an Android device to communicate with USB hardware whose capabilities it does not have. For example, USB hardware like musical keyboards, exercise machines, medical equipment, robotic controllers, card readers and many more. In order for this USB hardware to work with Android devices, it must adhere to the [Android accessory communication protocol](http://accessories.android.com/demokit).

Both the USB Host and Accessory modes are supported on Android 3.1 (API level 12) and newer platforms through the API package "[android.hardware.usb](http://developer.android.com/reference/android/hardware/usb/package-summary.html)".

For older platforms, the "Google APIs add-on library" has provided the "com.android.future.usb" package to support USB Accessory mode in Android 2.3.4.

#### USB APIs Overview

The "[android.hardware.usb](http://developer.android.com/reference/android/hardware/usb/package-summary.html)" package provides a total of eight classes that an app can use in order to communicate with USB hardware peripherals. They are summarized in Table 2.

| Class                                    | Description                              |
| ---------------------------------------- | ---------------------------------------- |
| [UsbManager](http://developer.android.com/reference/android/hardware/usb/UsbManager.html) | Allows an app to enumerate and communicate with connected USB devices. |
| [UsbDevice](http://developer.android.com/reference/android/hardware/usb/UsbDevice.html) | Represents an attached USB device in USB host mode. |
| [UsbAccessory](http://developer.android.com/reference/android/hardware/usb/UsbAccessory.html) | Represents an attached USB device in USB accessory mode. |
| [UsbInterface](http://developer.android.com/reference/android/hardware/usb/UsbInterface.html) | Represents an interface on a "UsbDevice" object which defines a set of functionality and has one or more "UsbEndPoints" for communication. |
| [UsbEndpoint](http://developer.android.com/reference/android/hardware/usb/UsbEndpoint.html) | Represents a communication channel for a "UsbInterface" object through which the host transfers data with the device. An interface can have one or more endpoints, for example the input and output endpoints for two-way communication. |
| [UsbDeviceConnection](http://developer.android.com/reference/android/hardware/usb/UsbDeviceConnection.html) | Represents a connection for transferring data back and forth synchronously or asynchronously. |
| [UsbRequest](http://developer.android.com/reference/android/hardware/usb/UsbRequest.html) | Represents a USB request packet that can be used for reading and writing data over a "UsbDeviceConnection". |
| [UsbConstants](http://developer.android.com/reference/android/hardware/usb/UsbConstants.html) | Contains constants for the USB protocol that correspond to definitions in linux/usb/ch9.h in the linux kernel. |

Let's walkthrough the steps of setting up a communication with a pseudo USB device in the two modes respectively. We will start with the USB Host mode.

## USB Host

#### Setting up Android Manifest

Before an app can work with the USB host APIs, it needs to set up the manifest file as listed:

- Declare a "<uses-feature>" element that indicates that the app needs the USB host APIs support. For example:

  Hide    Copy Code

  ```
  <uses-feature android:name="android.hardware.usb.host" />  
  ```

- Set the minimum SDK version of the app to API level 12. For example:

  Hide    Copy Code

  ```
  <uses-sdk android:minSdkVersion="12" />      
  ```

- Create an XML resource file, e.g. "usb_devices.xml", in the "res/xml/" location that declares the profiles of a list of USB devices that the app want to access in the "<usb-device>" element. For example:

  Hide    Copy Code

  ```
  <resources>
      <usb-device vendor-id="xxxx" product-id="yyyy" />
  </resources>
  ```

  Each "<usb-device>" element can contain these attributes - "vendor-id", "product_id", of "vendor-id", "class", "subclass", and "protocol". Use "vendor-id" and "product_id" to select a specific USB device. To select a group of USB devices, add attributes of "class", "subclass", and "protocol". If the "sub-device>" element does not specify any attribute, it selects all USB devices that are available.

- In order for your app to be notified when a USB device is attached, in the main Activity of the app, specify an "<intent-filter>" to filter the "[android.hardware.usb.action.USB_DEVICE_ATTACHED](http://developer.android.com/reference/android/hardware/usb/UsbManager.html#ACTION_USB_DEVICE_ATTACHED)" Intent and a "<meta-data>" that points to the XML resource file that declares the profile of the USB device to connect.	Hide    Copy Code`<intent-filter>    <action android:name="android.hardware.usb.action.USB_DEVICE_ATTACHED" /></intent-filter><meta-data android:name="android.hardware.usb.action.USB_DEVICE_ATTACHED" android:resource="@xml/usb_devices" />`	

#### Discovering USB Device

An app can discover USB devices either via a notification from an Intent Filter when they are attached or by looping through the devices that are already connected before the app is launched.

If you have already set up a device profile in an XML resource file and an Intent Filter to filter this device in the manifest file, the app can obtain the "[UsbDevice](http://developer.android.com/reference/android/hardware/usb/UsbDevice.html)" that represents the attached device from the Intent like this:

Hide    Copy Code

```
UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
```

On the other hand, you can obtain a specific "UsbDevice" from a list of already connected devices through enumeration like this:

Hide    Copy Code

```
UsbManager usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
UsbDevice usbDevice = deviceList.get("deviceName");
```

#### Obtaining Permission

If the app uses an intent filter to detect USB devices, when users connect a USB device that matches the device filter, they will be presented with a dialog asking them if they want to start the app. If users accept, the app is automatically granted permission to access the USB device until it is disconnected.

On the other hand, if the app detects a USB device through enumeration, it has to explicitly obtain permission from the users in order to communicate with the USB device. To do this, the app will call [requestPermission()](http://developer.android.com/reference/android/hardware/usb/UsbManager.html#requestPermission%28android.hardware.usb.UsbAccessory,%20android.app.PendingIntent%29) to display a dialog to the user asking for permission to connect to the device. For example:

Hide    Copy Code

```
private static final String ACTION_USB_PERMISSION = "com.peterleow.USB_PERMISSION";
UsbDevice usbDevice;
PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
usbManager.requestPermission(usbDevice, pi);
```

When users reply to the dialog, the result will be returned as an Intent that contains the "[EXTRA_PERMISSION_GRANTED](http://developer.android.com/reference/android/hardware/usb/UsbManager.html#EXTRA_PERMISSION_GRANTED)" extra which is either true or false. The app will only connect to the device when the value of this extra is true. This Intent will be broadcasted and must be captured by a "BroadcastReceiver". For example, create a BroadcastReceiver" like this:

Hide    Copy Code

```
private static final String ACTION_USB_PERMISSION = "com.peterleow.USB_PERMISSION";
// other code
private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ACTION_USB_PERMISSION.equals(action)) {
            synchronized (this) {
                // Obtain a USB device
                UsbDevice usbDevice = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);

                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                    if(usbDevice != null){
                      // permission granted
                   }
                } 
                else {
                    // permission denied
                }
            }
        }
    }
};
```

then register the "BroadcastReceiver" like this:

Hide    Copy Code

```
IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
registerReceiver(usbReceiver, filter);

```

#### Conducting Communication

Communication with a USB device should take place on a new thread so that it does not block the UI thread. To set up a communication, the app must first obtain the appropriate "[UsbInterface](http://developer.android.com/reference/android/hardware/usb/UsbInterface.html)" and "[UsbEndpoint](http://developer.android.com/reference/android/hardware/usb/UsbEndpoint.html)" of the USB device that you want to communicate, open a "[UsbDeviceConnection](http://developer.android.com/reference/android/hardware/usb/UsbDeviceConnection.html)" on that endpoint, and then supply the data for transmission on the end point using the "[bulkTransfer()](http://developer.android.com/reference/android/hardware/usb/UsbDeviceConnection.html#bulkTransfer%28android.hardware.usb.UsbEndpoint,%20byte[],%20int,%20int%29)" or "[controlTransfer()](http://developer.android.com/reference/android/hardware/usb/UsbDeviceConnection.html#controlTransfer%28int,%20int,%20int,%20int,%20byte[],%20int,%20int%29)" method. For example:

Hide    Copy Code

```
private Byte[] bytes;

UsbInterface usbInterface = device.getInterface(0);
UsbEndpoint usbEndpoint = usbInterface.getEndpoint(0);
UsbDeviceConnection usbDeviceConnection  = usbManager.openDevice(usbDevice); 
usbDeviceConnection.claimInterface(usbInterface, true);
usbDeviceConnection.bulkTransfer(usbEndpoint, bytes, bytes.length, 0);
```

#### Terminating Communication

When a connected USB device is detached or a communication session with a USB device is no longer needed, the app should close the "UsbInterface" and "UsbDeviceConnection" by calling"[releaseInterface()](http://developer.android.com/reference/android/hardware/usb/UsbDeviceConnection.html#releaseInterface%28android.hardware.usb.UsbInterface%29)" and "[close()](http://developer.android.com/reference/android/hardware/usb/UsbDeviceConnection.html#close%28%29)". To listen for detached events, create and register a "BroadcastReceiver" like the previous example.

## USB Accessory

The procedure in setting up a communication with a USB accessory in USB Accessory mode is very similar to that in USB Host mode with minor difference. For example, in USB Host mode, the USB device is represented as a "UsbDevice" object, whereas in USB Accessory mode, it is represented as a "[UsbAccessory](http://developer.android.com/reference/android/hardware/usb/UsbAccessory.html)" object.

If you use the add-on library to support Android 2.3.4, you will be glad to know that the classes that support the USB accessory feature in the add-on library are similar to those in "[android.hardware.usb](http://developer.android.com/reference/android/hardware/usb/package-summary.html)", which means you can use the documentation for the "android.hardware.usb" if you are using the add-on library. However, they do have two differences as follows:

- To create a "UsbManager" handle using the add-on library, do this:

  Hide    Copy Code

  ```
  UsbManager usbManager = UsbManager.getInstance(this);
  ```

  To create a "UsbManager" handle using the "android.hardware.usb" package, do this:

  Hide    Copy Code

  ```
  UsbManager usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
  ```

- To create a "UsbAccessory" object using the add-on library, do this:

  Hide    Copy Code

  ```
  UsbAccessory usbAccessory = UsbManager.getAccessory(intent);
  ```

  To create a "UsbAccessory" handle using the "android.hardware.usb" package, do this:

  Hide    Copy Code

  ```
  UsbAccessory usbAccessory = ((UsbAccessory)intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
  ```

#### Setting up Android Manifest

Before an app can work with the USB accessory APIs, it needs to set up the manifest file as listed:

- Declare a "<uses-feature>" element that indicates that the app needs the USB accessory APIs support. For example:

  Hide    Copy Code

  ```
  <uses-feature android:name="android.hardware.usb.accessory" />  
  ```

- Set the minimum SDK version of the app to API level 12 if you are using "android.hardware.usb" package or 10 if you are using the add-on library. For example:

  Hide    Copy Code

  ```
  <uses-sdk android:minSdkVersion="12" />      
  ```

- Create an XML resource file, e.g. "usb_accessory.xml", in the "res/xml/" location that declares the profiles of a list of USB accessories that the app want to access in the "<usb-accessory>" element. For example:

  Hide    Copy Code

  ```
  <resources>
      <usb-accessory model="xxxx" manufacturer="yyyy" version="1.0" />
  </resources>  
  ```

- In order for your app to be notified when a USB accessory is attached, in the main Activity of the app, specify an "<intent-filter>" to filter the "[android.hardware.usb.action.USB_ACCESSORY_ATTACHED](http://developer.android.com/reference/android/hardware/usb/UsbManager.html#ACTION_USB_ACCESSORY_ATTACHED)" Intent and a "<meta-data>" that points to the XML resource file that declares the profile of the USB accessory to connect.

  Hide    Copy Code

  ```
  <intent-filter>
      <action android:name="android.hardware.usb.action.USB_ACCESSORY_ATTACHED" />
  </intent-filter>

  <meta-data android:name="android.hardware.usb.action.USB_ACCESSORY_ATTACHED" android:resource="@xml/usb_accessory" />
  ```

#### Discovering USB Device

An app can discover USB accessories either via a notification from an Intent Filter when they are attached or by looping through the accessories that are already connected before the app is launched.

If you have already set up a device profile in an XML resource file and an Intent Filter to filter this accessory in the manifest file, the app can obtain the "[UsbAccessory](http://developer.android.com/reference/android/hardware/usb/UsbAccessory.html)" that represents the attached accessory from the Intent in either way:

- Using the add-on library:	

  Hide    Copy Code

  ```
  UsbAccessory usbAccessory = UsbManager.getAccessory(intent);
  ```

- Using the "android.hardware.usb" package:	

  Hide    Copy Code

  ```
  UsbAccessory usbAccessory = ((UsbAccessory)intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
  ```

On the other hand, you can obtain a specific "UsbAccessory" from a list of already connected accessories through enumeration like this:

Hide    Copy Code

```
UsbAccessory[] usbAccessoryList = usbManager.getAcccessoryList();
```

#### Obtaining Permission

If the app uses an intent filter to detect USB accessories, when users connect a USB device that matches the accessory filter, they will be presented with a dialog asking them if they want to start the app. If users accept, the app is automatically granted permission to access the USB accessory until it is disconnected.

On the other hand, if the app detects a USB accessory through enumeration, it has to explicitly obtain permission from the users in order to communicate with the USB accessory. To do this, the app will call [requestPermission()](http://developer.android.com/reference/android/hardware/usb/UsbManager.html#requestPermission%28android.hardware.usb.UsbAccessory,%20android.app.PendingIntent%29) to display a dialog to the user asking for permission to connect to the accessory. For example:

Hide    Copy Code

```
private static final String ACTION_USB_PERMISSION = "com.peterleow.USB_PERMISSION";
UsbAccessory usbAccessory;
PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
usbManager.requestPermission(usbAccessory, pi);
```

When users reply to the dialog, the result will be returned as an Intent that contains the "[EXTRA_PERMISSION_GRANTED](http://developer.android.com/reference/android/hardware/usb/UsbManager.html#EXTRA_PERMISSION_GRANTED)" extra which is either true or false. The app will only connect to the accessory when the value of this extra is true. This Intent will be broadcasted and must be captured by a "BroadcastReceiver". For example, create a BroadcastReceiver" like this:

Hide    Copy Code

```
private static final String ACTION_USB_PERMISSION = "com.peterleow.USB_PERMISSION";
// other code
private final BroadcastReceiver usbReceiver = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ACTION_USB_PERMISSION.equals(action)) {
            synchronized (this) {
                // Obtain a USB device
                 UsbAccessory usbAccessory = (UsbAccessory) intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);

                if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                    if(usbAccessory != null){
                      // permission granted
                   }
                } 
                else {
                    // permission denied
                }
            }
        }
    }
};
```

then register the "BroadcastReceiver" like this:

Hide    Copy Code

```
IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
registerReceiver(usbReceiver, filter);

```

#### Conducting Communication

Communication with a USB accessory should take place on a new thread so that it does not block the UI thread. You can communicate with the accessory by first obtaining a file descriptor from the "UsbManager", and then set up input and output streams to read and write data using the file descriptor. You can interpret a file descriptor as a communication end point of a USB accessory. For example:

Hide    Copy Code

```
FileInputStream inputStream;
FileOutputStream outputStream;
ParcelFileDescriptor fileDescriptor = usbManager.openAccessory(usbAccessory);

if fileDescriptor != null) {
    FileDescriptor fileDescriptor = mFileDescriptor.getFileDescriptor();
    inputStream = new FileInputStream(fileDescriptor );
    outputStream = new FileOutputStream(fileDescriptor );
    Thread t = new Thread(null, this, "USB AccessoryThread");
    t.start();
}
```

#### Terminating Communication

When a connected USB accessory is detached or a communication session with a USB accessory is no longer needed, the app should close the file descriptor by calling "[close()](http://developer.android.com/reference/android/hardware/usb/UsbDeviceConnection.html#close%28%29)". To listen for detached events, create and register a "BroadcastReceiver" like the previous example.

## Best Practices

I have rounded up some best practices with regards to implementing connectivity in Android.

- Declare <user-feature> in the app's manifest file where applicable as a way to prevent incompatible devices that do not support certain connectivity option from accessing the app that implements that connectivity option.
- Always execute I/O related operations in a new thread so as not to block the user's interaction on the main UI thread.
- Always unregister the "BroadcastReceiver" on "Activity.onPause()" and register it on "Activity.onResume()" so as to minimize system overhead.

## Summary

You have learned the basic connectivity options in Android in a hands-on approach. I hope this journey will entice you to explore these options further and incorporate them into your next Android app. For easy reference, I have put them together into a quick link list for a quick jump to the respective options in this article.

- [Bluetooth](https://www.codeproject.com/Articles/814814/Android-Connectivity#bluetooth)
- [Wi-Fi](https://www.codeproject.com/Articles/814814/Android-Connectivity#wifi)
- [NFC](https://www.codeproject.com/Articles/814814/Android-Connectivity#nfc)
- [Mobile Data](https://www.codeproject.com/Articles/814814/Android-Connectivity#mobile)
- [SIP](https://www.codeproject.com/Articles/814814/Android-Connectivity#sip)
- [USB](https://www.codeproject.com/Articles/814814/Android-Connectivity#usb)

It is time to disconnect and take a breather. See you.

## Reference

- [Android Connectivty](http://developer.android.com/guide/topics/connectivity/index.html)

## License

This article, along with any associated source code and files, is licensed under [The Code Project Open License (CPOL)](http://www.codeproject.com/info/cpol10.aspx)



## About the Author

​         ![img](https://www.gravatar.com/avatar/eea82bb3367b38051f5304d4d3114f95.jpg?d=identicon&s=150&r=pg)    

​          **Peter Leow**                                        	            [![Twitter](https://www.codeproject.com/images/twitter32.png)](http://www.twitter.com/peterleowblog) 	            	            [![LinkedIn](https://www.codeproject.com/images/linkedin-32.png)](https://www.linkedin.com/in/peterleow)                                                                         Instructor / Trainer	                                                                                        Singapore ![Singapore](https://www.codeproject.com/script/Geo/Images/SG.gif)                                

​        Stop by my website at http://www.peterleowblog.com/
Check out my publications at http://www.amazon.com/author/peterleow
Follow me on twitter at https://twitter.com/peterleowblog
“Live as if you were to die tomorrow. Learn as if you were to live forever.”
― Mahatma Gandhi
子曰："三人行，必有我师焉；择其善者而从之，其不善者而改之."