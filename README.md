WANDERWHEELS - CARAVAN RENTING APP
===================================

A modern, full-featured caravan renting mobile application built with Kotlin and Jetpack Compose.

FEATURES
--------
- User Authentication (Email/Password & Google Sign-In)
- Caravan Browsing with filters and search
- Booking System with date selection
- Payment Integration (Razorpay)
- Offline Support (Room Database)
- Real-time Updates (Firebase Firestore)
- Modern UI (Jetpack Compose + Material Design 3)

TECH STACK
----------
- Language: Kotlin
- UI: Jetpack Compose
- Architecture: MVVM
- Database: Room (Local), Firebase Firestore (Remote)
- Authentication: Firebase Auth
- Payments: Razorpay SDK
- Maps: Google Maps SDK
- Dependency Injection: Dagger Hilt

PREREQUISITES
-------------
- Android Studio Hedgehog or later
- Android SDK 34
- Java 11
- Firebase Account
- Google Cloud Account

INSTALLATION
------------
1. Clone the repository:
   git clone https://github.com/Amitgupta0001/WanderWheels.git

2. Open in Android Studio

3. Firebase Setup:
   - Create Firebase project
   - Enable Authentication (Email/Password & Google)
   - Enable Firestore Database
   - Download google-services.json and place in app/ folder

4. Google Maps Setup:
   - Get API key from Google Cloud Console
   - Add to local.properties as MAPS_API_KEY=your_api_key

5. Build and run the project

PROJECT STRUCTURE
-----------------
app/
├── data/           # Data models and repositories
├── ui/             # Compose screens and components
├── viewmodel/      # ViewModels
├── database/       # Room database
└── utils/          # Utility classes

KEY SCREENS
-----------
- Authentication (Login/Signup)
- Explore Caravans
- Caravan Details
- Booking Flow
- Wishlists
- User Profile

BUILD INSTRUCTIONS
------------------
1. Sync project with Gradle files
2. Build project (Ctrl+F9)
3. Run on emulator or device (Shift+F10)

CONFIGURATION FILES
-------------------
- app/build.gradle.kts - Dependencies and plugins
- android-manifest.xml - Permissions and configurations
- local.properties - API keys (NOT committed to git)

DEPENDENCIES
------------
- Jetpack Compose
- Firebase (Auth, Firestore, Storage)
- Room Database
- Dagger Hilt
- Coil (Image loading)
- Google Maps
- Razorpay

LICENSE
-------
MIT License - See LICENSE file for details

AUTHOR
------
GitHub: @Amitgupta0001
GitHub: @ChaitraLG77

VERSION
-------
1.0.0
