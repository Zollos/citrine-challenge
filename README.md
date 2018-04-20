# citrine-challenge
Code challenge exercise for Citrine

## Requirements

* [Gradle](http://gradle.org) 2.14 or greater
* Java 1.8 or greater

## Usage

* Build with `gradle xcodebuild`
* Run the unit tests with `gradle test`
* Perform a device build and upload it to hockeyapp with `gradle integration`. Here you need to specify your sign settings first (see [Signing](Documentation/Parameters.md#sign-settings) ). Open the build.gradle file an follow the instructions.
* Perform an appstore build with `gradle appstore`. (Also the sign settings are needed).

