# 🛸 Yassir Rick & Morty Compose App

A modern Rick and Morty character exploration app built using **Jetpack Compose**, **Ktor**, **Koin**, and **Clean Architecture**.  
=======


---

## 🎥 Demo

https://github.com/user-attachments/assets/8ee76281-6770-4d35-a2de-091a167b577f

---

## 🚀 Features

- 🔍 **Real-time Character Search** - Debounced search with instant results
- 📱 **Character List** - Paginated grid view with infinite scrolling
- 👤 **Character Details** - Comprehensive character information fetched from API
- 🎨 **Modern UI** - Beautiful Material 3 design with custom animations
- 🧠 **Clean Architecture** - Separation of concerns with data, domain, and presentation layers
- 🌐 **API Integration** - Direct API calls for character details with error handling
- ⚙️ **Dependency Injection** - Koin for clean dependency management
- 📊 **State Management** - Reactive UI with StateFlow and ViewModel
- 🔄 **Offline Support** - Graceful error handling and retry mechanisms

---

## 📦 Tech Stack

| Layer        | Tech                  |
|--------------|------------------------|
| UI           | Jetpack Compose, Material 3 |
| Architecture | Clean Architecture (MVI pattern) |
| State        | ViewModel, StateFlow, Channel |
| DI           | Koin                  |
| Network      | Ktor Client           |
| Language     | Kotlin                |
=======


---

## 🧩 Project Structure


app/src/main/java/com/imad/yassir/rickmorty/
├── core/                           # Core utilities and shared components
│   ├── data/networking/           # HTTP client and network utilities
│   ├── domain/util/               # Result wrapper and error handling
│   ├── navigation/                # Navigation components
│   └── presentation/util/         # UI utilities
├── di/                            # Dependency injection modules
├── rick_morty/                    # Main feature module
│   ├── data/                      # Data layer
│   │   ├── mapper/               # Data mappers (DTO ↔ Domain)
│   │   └── networking/           # API data sources
│   ├── domain/                   # Domain layer
│   │   ├── Character.kt          # Character domain model
│   │   ├── CharacterDetails.kt   # Character details domain model
│   │   └── CharacterDataSource.kt # Data source interface
│   └── presentation/             # Presentation layer
│       ├── character_list/       # Character list screen
│       └── character_details/    # Character details screen
└── MainActivity.kt               # Main activity


---

## 🔄 Recent Updates

### API-Driven Character Details
- **Transitioned from navigation-based data passing** to **direct API calls** for character details
- **Improved data consistency** - Always shows the latest information from the server
- **Better user experience** - No more stale data issues
- **Enhanced error handling** - Graceful fallbacks and retry mechanisms

### Enhanced UI Features
- **Expandable Episode List** - Click to view all episodes with beautiful card-based design
- **Comprehensive Character Information** - Shows species, type, origin, location, episode count, and creation date
- **Loading States** - Smooth loading indicators and error states
- **Responsive Design** - Optimized for different screen sizes

---

## 🎯 Key Features Explained

### Character List Screen
- **Infinite Scrolling** - Automatically loads more characters as you scroll
- **Real-time Search** - Debounced search with instant results
- **Grid Layout** - Beautiful card-based character grid
- **Loading States** - Smooth loading indicators

### Character Details Screen
- **API-Fetched Data** - All information comes directly from the Rick & Morty API
- **Expandable Episodes** - Click to see all episodes in a beautiful list
- **Rich Information Display** - Species, type, origin, location, and more
- **Error Handling** - Retry functionality for failed requests

---

## 🚀 Getting Started

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd YassirRickMortyApp
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the project directory

3. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio
   - Or use: `./gradlew assembleDebug`

---

## 🏗️ Architecture Overview

### Clean Architecture Implementation
- **Data Layer**: API calls, data mappers, and repositories
- **Domain Layer**: Business logic and data models
- **Presentation Layer**: UI components and state management

### State Management
- **ViewModel**: Manages UI state and business logic
- **StateFlow**: Reactive state streams
- **Channel**: One-time events (navigation, toasts)

### Navigation
- **Compose Navigation**: Type-safe navigation with arguments
- **SavedStateHandle**: Preserves state across configuration changes

---

## 🎨 UI/UX Features

### Design System
- **Material 3**: Modern design language
- **Custom Color Palette**: Rick & Morty themed colors
- **Responsive Layout**: Adapts to different screen sizes
- **Smooth Animations**: Delightful user interactions

### Character Cards
- **Gradient Borders**: Beautiful cyan glow effects
- **Status Indicators**: Color-coded status (Alive/Dead/Unknown)
- **Image Loading**: Smooth image loading with Coil

### Details Screen
- **Hero Image**: Large character image with rounded corners
- **Information Cards**: Organized information display
- **Expandable Sections**: Interactive episode list
- **Loading States**: Professional loading indicators

---

## 🔧 Technical Implementation

### API Integration
- **Ktor Client**: Modern HTTP client for API calls
- **Serialization**: Kotlinx.serialization for JSON parsing
- **Error Handling**: Comprehensive error handling with user-friendly messages
- **Caching**: Efficient data management

### Dependency Injection
- **Koin**: Lightweight dependency injection
- **Modular Design**: Clean separation of concerns
- **Testability**: Easy to mock dependencies for testing

### Performance Optimizations
- **Lazy Loading**: Efficient list rendering
- **Image Caching**: Coil for optimized image loading
- **Debounced Search**: Prevents excessive API calls
- **State Management**: Efficient UI updates

---


## 📱 Screenshots

*Screenshots will be added here*






---

## 🧪 Testing

Unit and UI tests are structured and modular (not included in this challenge submission but the architecture is test-friendly).

---

## 📄 License

This project is developed as part of the Yassir Android Engineer recruitment challenge.

---

## 🙏 Acknowledgments

- **Rick & Morty API** for providing the character data
- **Jetpack Compose** team for the amazing UI toolkit
- **Ktor** team for the excellent networking library
- **Koin** team for the lightweight DI framework
=======
This project is open-source and free to use under the MIT license.

