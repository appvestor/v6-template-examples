# Generic Template

Generic code samples.

## Get Started

### Step 1
Declare a new receiver in the manifest.
  
 ```
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

```
public class CustomViewReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("com.calldorado.android.intent.SEARCH") ||
                intent.getAction().equals("android.intent.action.PHONE_STATE")) {

	    Calldorado.setAftercallCustomView(
		    new AftercallCustomView(context));
					   
	    Calldorado.setWicDisplayCustomView(
		    new WicDisplayCustomView(context));					   
			
	    Calldorado.setWicActionCustomView(
		    new WicActionCustomView(context));					

        }
    }
}
```

### Step 3
Create a class that extends from CalldoradoCustomView and overrides the method getRootView(), from where you can inflate a layout resource or create the UI programmatically. Open the classes AftercallCustomView.java, WicDisplayCustomView.java or WicActionCustomView.java for samples.

## Images

See how the template looks like.

|  WIC (note and action)  |  Aftercall (add note)  |  Aftercall (latest note)  |
|---|---|---|
|  <img src="screenshots/wic_incoming_last_note.png" width="288" height="512">  |  <img src="screenshots/aftercall_save_note.png" width="288" height="512">  |  <img src="screenshots/aftercall_last_note.png" width="288" height="512">  |
 
