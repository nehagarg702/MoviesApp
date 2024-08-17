# MoviesApp

##Overview
The Movies App is an Android application developed using Kotlin and Jetpack Compose. It provides users with a modern, clean interface to browse and search for popular movies. The app uses a responsive design and leverages the latest Android development practices, including MVVM architecture, Koin for dependency injection, and Coil for image loading.

## Features
- Browse Movies: Explore a list of trending movies fetched from an online API.
- Search Movies: Search for movies by name and overview using the built-in search bar.
- View Movie Details: Tap on a movie to view detailed information, including a poster, title, and overview.
- Pull-to-Refresh: Refresh the list of movies by pulling down on the screen.
- Loading and Error States: Proper handling of loading and error states during network operations.
- Dependency Injection with Koin: Simplifies dependency management and reduces boilerplate code.
- Jetpack Compose: Utilizes a modern UI toolkit for building native UIs with a declarative approach.
- MVVM Architecture: Ensures a clean separation of concerns, making the codebase more maintainable.
- Coil Image Loading: Efficiently loads and displays images from URLs using Coil.

## Prerequisites
- Android Studio (latest version recommended)
- Gradle
- An Android device or emulator running API level 21 or higher
- TMDB API Key: You'll need an API key from The Movie Database (TMDB) to fetch movie data.

## Getting Started
Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Clone the Repository
git clone https://github.com/nehagarg702/MoviesApp.git

### Open the Project in Android Studio
1. Open Android Studio.
2. Click on File > Open....
3. Navigate to the directory where you cloned the project and select the build.gradle file to open the project.

### Get Your TMDB API Key
1. Visit TMDB's API documentation.
2. Create an account (if you don't have one).
3. Request an API key by following the steps in the API documentation.
4. Copy the API key to use in the project.

### Add TMDB API Key to local.properties
1. Open the local.properties file in your project's root directory.
2. Add the following line to include your TMDB API key:
   TMDB_API_KEY=your_api_key_here
Replace your_api_key_here with the actual API key you obtained from TMDB.

### Build the Project
1. Ensure you have a stable internet connection to download the necessary dependencies.
2. In Android Studio, click on the Sync Project with Gradle Files button.
3. Wait for the project to sync and build.

### Run the App
1. Connect your Android device via USB or start an Android emulator.
2. Click on the Run button in Android Studio or press Shift + F10.
3. Select the device or emulator where you want to install and run the app.

## Key Components
- **MVVM Architecture: The application is structured using the Model-View-ViewModel architecture to separate concerns and ensure a maintainable codebase.
- **TMDB API Integration: Fetches trending movies and movie details from The Movie Database (TMDB) API.
- **Jetpack Compose: Provides a modern toolkit for building native UIs with a declarative approach, ensuring a responsive and visually appealing interface.
- **Dependency Injection with Koin: Simplifies the injection of dependencies and reduces boilerplate code, making the app more modular and testable.
- **Search Feature: Users can search for movies by name and overview using a search bar integrated into the main movie list screen.
- **Coroutines for Background Tasks: Utilized to handle asynchronous operations and background tasks efficiently, ensuring smooth performance and responsiveness of the app.
- **Pull to Refresh: A feature allowing users to refresh the movie list by pulling down from the top of the screen, ensuring they always have the most up-to-date information.
