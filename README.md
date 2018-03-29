# Android Test
## Description

Create an Android app that shows a list of episodes from a serie’s season. Above that episodes list create a header that displays some informations about the serie (cover thumbnail) and also the season (banner and rating). It’s important to notice that both serie and season can be chosen as you like and can be fixed in the code, which means that you don’t have to create any other screen to make this choice dynamic.

In order to create this app you will use the [Trakt](https://trakt.tv) API (you must sign up in the website to have access to the API) and also the assets we provided.

This test was designed to be developed in at most 8 hours and you should use the mockup below as base for your development.

![grid Android 4.4](/images/device-2015-10-08-131549.png)
![list_Android_4.4](/images/device-2015-10-08-131453.png)
![grid Android 7.1.1](/images/device-2015-10-08-132258.png)
![list Android 7.1.1](/images/device-2015-10-08-132332.png)

## Instructions

All the placeholder images are inside of the project in the drawable(s) folders [mdpi, hdpi, xhdpi, etc.] you must use them if the images load slowly or incorrect way, the placeholder images will be shown.

One example of the the http calls is:
>https://api.trakt.tv/shows/game-of-thrones?extended=full

``headers -> Content-Type:application/json ; trackt-api-key:[api_key] ; trackt-api-version:2``

In order to obtain an API KEY you have to register in Trackt TV is completely free, if you don't want to do the registration, you can use this API KEY

>0b6fc75bf56b7dc8e7d02f802193b5e0cc0b0cb8ffa4eaa1cf00bda24c4edb07

## Requirements
* You must use Android Studio and Gradle
* Your app must work since Android 4.4 (API 19)
* It must be tested in the following device’s images (use native emulators or Genymotion to run your tests):
  * Google Nexus 6 - Android 8.0
  * Galaxy S5 - Android 5.0
  * Motorola Moto X - Android 4.4.4
  * Google Nexus 4 - Android 7.1.1
* It’s allowed to use external libraries

## Extras

* Handle error scenarios (no connectivity, server errors)
* Loading feedback
* Parallax effect on header scroll
* Transform the toolbar in a opaque color during scroll
* Automated tests
* Different layout for tablet or landscape orientation

## Submission

You have two options:  

1) Create a _pull request_ to our [__public__ repository](https://github.com/itexico/android_test). Note that your *fork* will be public for anyone to see.

2) In case you prefer some privacy, create a repository with the name **test-android** in your GitHub or Gitlab account, add the user @giovas17 for Github or @darkgeat for Gitlab as a collaborator (read-only access is enough) and send an email to fcerda@itexico.com to let us know.
