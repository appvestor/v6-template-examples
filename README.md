# Calldorado Templates

A collection of native templates that can be used with the Calldorado SDK to integrate your own functionality in the Caller ID. The templates provided here is meant as a starting point, we highly encourage you to built on top of the code we give you here.

If you don't already have a Calldorado account make sure you sign up [here](https://my.calldorado.com/login/signup).

## Introduction

This is a guide of how to integrate native views and actions into Calldorado’s UI. You should be familiar with the Calldorado SDK and have a basic working integration of Calldorado before proceeding. If you do not, please have a look [here](https://my.calldorado.com/) under Integration and Documentation.

There are three areas where you can provide your own functionality. Two of those areas are part of the WIC - the floating box that appears during a call to identify the caller - and the last one is part of the Aftercall screen - the details page that comes after the call ends.

*	Custom action on the WIC; allow users to perform custom actions during a call
*	Custom UI on the WIC; show the user content from your app during a call
*	Custom UI in the Aftercall screen; show content on the call details page

## Documentation

Calldorado SDK now provides a view called CalldoradoCustomView from where your classes can extend from, and have access to the methods listed below.

### CalldoradoCustomView Methods

| Gets  | Description  |
|---|---|
| String getPhoneNumber()  | Phone number of who is calling or who the user is calling to  |
| String getContactName()  | Contact name of who is calling or who the user is calling to  |
| Context getContext()  | Context from your receiver  |
| Context getCalldoradoContext()  | Context from Aftercall activity or WIC receiver  |
| RelativeLayout getRelativeViewGroup()  | Returns a simple Relative Layout to use as View Group when inflating a layout resource  |
| LinearLayout getLinearViewGroup()  | Returns a simple Linear Layout to use as View Group when inflating a layout resource  |

| Utils  | Description  |
|---|---|
| void saveItem (String key, Object newValue)  | Saves object of any simple type into shared preferences  |
| Object findItem (String key, Object defaultValue)   | Finds object of any simple type from shared preferences  |
| int convertDpToPixel (int dp)  | Converts dp (density independent pixels) units to equivalent pixels, depending on device density  |
| void showFeedbackMessage (String message)  | Shows a toast message on the bottom if from the WIC or a snack bar on the top if from the aftercall screen  |

| @Override  | Description  |
|---|---|
| View getRootView()  | Initialize your view here with all the UI elements and logic necessary, then return it.  |
| void executeOnStart()  | Place here the code to be executed on start of the aftercall activity (not valid for the WIC)  |
| void executeOnResume()  | Place here the code to be executed on resume of the aftercall activity (not valid for the WIC)  |
| void executeOnPause()  | Place here the code to be executed on pause of the aftercall activity (not valid for the WIC)  |
| void executeOnStop()  | Place here the code to be executed on stop of the aftercall activity (not valid for the WIC)  |
| void executeOnDestroy()  | Place here the code to be executed on destroy of the aftercall activity (not valid for the WIC)  |

## Get Started


