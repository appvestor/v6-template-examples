# Calldorado Templates

Code samples to integrate native views into Calldorado’s UI.

## Introduction

This is a guide of how to integrate native views and actions into Calldorado’s UI. You should be familiar with the Calldorado SDK and have a basic working integration of Calldorado before proceeding. If you do not, please have a look at my.calldorado.com Integration and Documentation.

There are three areas for you to integrate or able to facilitate:
*	custom action for the WIC 
*	custom field for the WIC
*	custom field in the Aftercall screen

Note: WIC is our floating UI that appears during a call to identify the caller.

## Documentation

Calldorado SDK now provides a view called CalldoradoCustomView from where your classes can extend from, and have access to the methods listed below. 

### CalldoradoCustomView Methods

| Gets  | Description  |
|---|---|
| String getPhoneNumber()  | phone number of who is calling or who the user is calling to  |
| String getContactName()  | contact name of who is calling or who the user is calling to  |
| Context getContext()  | context from your receiver  |
| Context getCalldoradoContext()  | context from Aftercall activity or WIC receiver  |
| RelativeLayout getRelativeViewGroup()  | returns a simple Relative Layout to use as View Group when inflating a layout resource  |
| LinearLayout getLinearViewGroup()  | returns a simple Linear Layout to use as View Group when inflating a layout resource  |

| Utils  | Description  |
|---|---|
| void saveItem (String key, Object newValue)  | Saves object of any simple type into shared preferences  |
| Object findItem (String key, Object defaultValue)   | Finds object of any simple type from shared preferences  |
| int convertDpToPixel (int dp)  | Converts dp (density independent pixels) units to equivalent pixels, depending on device density  |
| void showFeedbackMessage (String message)  | Shows a toast message on the bottom if from the WIC or a snack bar on the top if from the aftercall screen  |

| Override  | Description  |
|---|---|
| @Override
View getRootView()  | Initialize your view here with all the UI elements and logic necessary, then return it.  |
| @Override
void executeOnStart()  | Place here the code to be executed on start of the aftercall activity (not valid for the WIC)  |
| @Override
void executeOnResume()  | Place here the code to be executed on resume of the aftercall activity (not valid for the WIC)  |
| @Override
void executeOnPause()  | Place here the code to be executed on pause of the aftercall activity (not valid for the WIC)  |
| @Override
void executeOnStop()  | Place here the code to be executed on stop of the aftercall activity (not valid for the WIC)  |
| @Override
void executeOnDestroy()  | Place here the code to be executed on destroy of the aftercall activity (not valid for the WIC)  |