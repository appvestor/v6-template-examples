# Calldorado Templates

A collection of native templates that can be used with the Calldorado SDK to integrate your own functionality in the Caller ID. The templates provided here are meant as a starting point, we highly encourage you to built on top of the code we give you.

If you don't already have a Calldorado account make sure you sign up [here](https://my.calldorado.com/login/signup).

## Introduction

There are three areas where you can provide your own functionality. Two of those areas are part of the WIC - the floating box that appears during a call to identify the caller - and the third one is part of the Aftercall screen - the details page that comes after the call ends.

*	Custom action on the WIC; allow users to perform custom actions during a call
*	Custom UI on the WIC; show the user content from your app during a call
*	Custom UI in the Aftercall screen; show content on the call details page

You should be familiar with the Calldorado SDK and have a basic working integration of Calldorado before proceeding. If you do not, please have a look [here](https://my.calldorado.com/) under Integration and Documentation.

## Documentation

Find it in our wiki page [here](https://github.com/Calldorado-com/calldorado-template-examples/wiki).

## Getting Started

To get you started we have prepared five templates that you can use as a foundation for your native Calldorado integration. Each template contains sample code and a step by step guide to get you up and running with your own native integration.

### Generic Template

The [generic template](https://github.com/Calldorado-com/calldorado-template-examples/tree/master/generic_template) shows you how to add three different types of native experiences for any type of app. In the examples we use simple todo notes, but the possibilities for customization are many.

### Blocker Template

The [blocker template](https://github.com/Calldorado-com/calldorado-template-examples/tree/master/blocker_template) shows you how to add two different types of native experiences for a call blocker app. In the examples we show how to add a block action to the WIC and how to use layouts from resources on the aftercall.

### Email Template

The [email template](https://github.com/Calldorado-com/calldorado-template-examples/tree/master/email_template) shows you how to add one type of native experience for an email app. In the examples we show you how to add an adaptive layout which allows the user to write and send emails from the aftercall.

### Message Template

The [message template](https://github.com/Calldorado-com/calldorado-template-examples/tree/master/message_template) shows you how to add two different types of native experiences for a messaging app. In the examples we show how to use actions from your app in the aftercall as well as how to show your own UI on the WIC.

### Recorder Template

The [recorder template](https://github.com/Calldorado-com/calldorado-template-examples/tree/master/recorder_template) shows you how to add two different types of native experiences for a call recorder app. In the examples we show how to start an action during the call and how to finalize it in the aftercall.
