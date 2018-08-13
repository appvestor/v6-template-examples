# Blocker Template

Code sample for a call blocker app. Note that we are not providing the code to actually block contacts, the existing logic only simulates the template's flow.

## Get Started

### Step 1
Declare a new receiver in the manifest.
  
 ```xml
<receiver
    android:name=".receiver.CustomViewReceiver"
    android:enabled="true">
    <intent-filter android:priority="101">
        <action android:name="com.calldorado.android.intent.SEARCH" />
        <action android:name="android.intent.action.PHONE_STATE" />
    </intent-filter>
</receiver>
```

### Step 2
Your receiver is where you set your views so that Calldorado can access them on runtime. You can provide up to three different views to be used in different places.

```java
public class CustomViewReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("com.calldorado.android.intent.SEARCH") ||
                intent.getAction().equals("android.intent.action.PHONE_STATE")) {

            Calldorado.setAftercallCustomView(
                       new AftercallCustomView(context));
					   
            Calldorado.setWicActionCustomView(
                       new WicActionCustomView(context));					   

        }
    }
}
```

### Step 3
Create a class that extends from CalldoradoCustomView and overrides the method getRootView(), from where you can inflate a layout resource or create the UI programmatically. Open the class AftercallCustomView.java or WicActionCustomView.java for samples.


## Images

See how the template looks like.

|  WIC (block action) |  Aftercall (not blocked)  | Aftercall (blocked)
|---|---|---|
|  <img src="screenshots/wic_block_action.png" width="288" height="512">  |  <img src="screenshots/aftercall_not_blocked.png" width="288" height="512">  | <img src="screenshots/aftercall_number_blocked.png" width="288" height="512"> |
