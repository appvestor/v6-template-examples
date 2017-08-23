## How to Integrate Native Views into Calldorado’s UI

### Introduction

This is a guide of how to integrate native views and actions into Calldorado’s UI. You should be familiar with the Calldorado SDK and have a basic working integration of Calldorado before proceeding. If you do not, please have a look at my.calldorado.com Integration and Documentation.

There are three areas for you to integrate or able to facilitate:
*	custom action for the WIC 
*	custom field for the WIC
*	custom field in the Aftercall screen

### Documentation

Calldorado SDK now provides a view called CalldoradoCustomView from where your classes can extend from, and have access to the methods listed below. 

| Gets  | Description  |
|---|---|
| String getPhoneNumber()  | phone number of who is calling or who the user is calling to  |
| String getContactName()  | contact name of who is calling or who the user is calling to  |
| Context getContext()  | context from your receiver  |
| Context getCalldoradoContext()  | context from Aftercall activity or WIC receiver  |
| RelativeLayout getRelativeViewGroup()  | returns a simple Relative Layout to use as View Group when inflating a layout resource  |
| LinearLayout getLinearViewGroup()  | returns a simple Linear Layout to use as View Group when inflating a layout resource  |
