# Services App

## Project Setup

### Clone and install

Clone this repository and import into Android Studio
```
git clone https://github.com/pi34/Services.git
```
### Default configuration:
```
Package Name: "com.serviceapps.shopping"
Minimu SDK version: 21
Target SDK version: 31
```

### Configuration
#### Configuring firebase
- The project requires Firebase. So follow the steps given [here (Add Firebase to Android Project)](https://firebase.google.com/docs/android/setup) to add firebase to your android project.
- Download the firebase config file `google-services.json`
- Move the config file to module directory `(app)` of the project.

#### Configuring cloud firestore
- All the products and user information is being stored in the cloude store. 
  Quick start guide: https://firebase.google.com/docs/firestore/quickstart

#### Configuring Firebase storage
- All the product images will be stored in the firebase storage. 
Quick start to getting started with the storage: https://cloud.google.com/storage/docs/quickstart-console

## Built With
- Kotlin
- Firebase
- Material
- Glide
