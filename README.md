# SuperHero Android App

## Overview

The SuperHero app is a captivating Android application designed to engage users with the fascinating world of Marvel characters. The app utilizes the [Marvel API](https://developer.marvel.com) to fetch and display a vertical list of Marvel characters on the main screen, organized alphabetically. Users can seamlessly navigate through the vast array of heroes and select their favorites for further exploration.

## Screens

### 1. Main Screen

The main screen is the gateway to the Marvel universe, presenting users with a visually appealing vertical list of Marvel characters. Each character is displayed with an image and name, providing users with a quick overview. The alphabetical order enhances user navigation, allowing for a smooth browsing experience.

![Main Screen](https://i.imgur.com/P0ivZ7f.jpg)

### 2. Details Screen

Upon selecting a hero from the main screen, users are directed to the detailed view of the chosen character. This screen features an immersive display of the hero's image, name, and a captivating description. To add a touch of interactivity, a "Hire to Squad" button allows users to recruit their favorite heroes to their personal squad.

![Details Screen](https://i.imgur.com/eyYEpWF.jpg)

### 3. My Squad Section

The My Squad section is a dynamic horizontal list that showcases the heroes recruited by the user. Heroes added to the squad are seamlessly integrated into this section, providing users with a quick glance at their formidable team.

### 4. Remove from Squad

For heroes already in the squad, users can tap on them to access the details screen once again. This time, an additional option appears — the "Fire from Squad" button. Selecting this option removes the hero from the squad. Upon returning to the main screen, the hero is no longer featured in the My Squad section.

![Remove from Squad](https://i.imgur.com/mW90CwN.jpg)

## User Experience

The SuperHero app offers a delightful user experience by combining visually appealing design with intuitive navigation. Users can effortlessly explore Marvel characters, recruit them to their squad, and even make strategic decisions to optimize their team. With a seamless flow between screens, the app ensures that users remain engaged and immersed in the captivating world of superheroes.

## Libraries Used

The SuperHero app leverages a powerful set of libraries to enhance functionality, streamline development, and provide a seamless user experience.

1. **KSP (Kotlin Symbol Processing):** KSP is a Kotlin compiler plugin that facilitates the generation of code during compilation. It's instrumental in reducing boilerplate code and improving overall code generation.

2. **Coroutines-Test:** This library provides testing utilities for Kotlin coroutines, allowing for the efficient testing of asynchronous code in a controlled environment.

3. **MockK:** MockK is a mocking library for Kotlin that simplifies the process of creating mocks for testing. It enhances the ease of writing test cases and improves overall testability.

4. **Hilt:** Hilt is a dependency injection library that is built on top of Dagger. It simplifies the implementation of Dagger for Android apps, making dependency injection more straightforward.

5. **Retrofit:** Retrofit simplifies the process of making network requests. It seamlessly converts HTTP API calls to Kotlin functions.

6. **Moshi:** Moshi is a modern JSON library for Kotlin and Java. It facilitates the parsing of JSON data into Kotlin objects and vice versa, enhancing data serialization and deserialization.

7. **Room:** Room is a persistence library that provides an abstraction layer over SQLite. It simplifies database operations and allows for efficient data storage.

8. **Timber:** Timber is a logging library that simplifies the logging process. It enhances logging statements with additional information, making debugging more effective.

9. **Jetpack Compose:** Jetpack Compose is a modern UI toolkit for building native Android UIs. It simplifies UI development by using a declarative syntax and offers a more intuitive approach to UI design.

10. **Coil:** Coil is an image loading library that focuses on simplicity and performance. It efficiently loads images into ImageView components, optimizing memory usage.

11. **Paging:** Paging is a library from the Android Jetpack components that helps in loading large datasets gradually, providing a seamless user experience.

12. **Swipe-Refresh:** Swipe-Refresh allows users to trigger a refresh operation by swiping down on a view. It enhances the user experience by providing a simple and intuitive way to update content.

13. **Lottie-Compose:** Lottie-Compose is a library for playing Lottie animations in Jetpack Compose. Lottie animations are vector animations that can be easily integrated into Android applications.

14. **Android-Navigation-Compose:** This library simplifies navigation in Jetpack Compose applications, providing a declarative way to navigate between different screens.

15. **AndroidX-Arch-Core-Test:** This library provides testing utilities for AndroidX architecture components, allowing for the efficient testing of components like ViewModels and LiveData.

16. **AndroidX-Compose-Material3:** This library extends Jetpack Compose by providing Material Design 3 components, enhancing the visual and interactive aspects of the app's UI with the latest design principles.

## Core-Modules

### 1. core-resources

The `core-resources` module is crucial in a multimodal app as it encapsulates Android resources such as fonts, animations, string values, and XML icons. By segregating these resources into a dedicated module, the app achieves a clean separation of concerns. This modular approach not only enhances maintainability but also promotes reusability across different parts of the application. It simplifies resource management and allows for easy updates or customization, providing a consistent visual and branding experience throughout the app.

### 2. core-design-system

The `core-design-system` module houses Jetpack Compose UI components and an app-specific design system Compose Theme. This separation is essential for maintaining a consistent and cohesive user interface (UI) across the app. By centralizing UI components and design theming in a dedicated module, developers ensure design consistency and streamline the process of creating new components. The inclusion of components like `DesignSystemButtonPrimary`, `DesignSystemIcon`, `DesignSystemLoader`, `DesignSystemText`, and `DesignSystemTheme` provides a foundation for future developers to extend and create additional components seamlessly.

### 3. core-navigation

The `core-navigation` module plays a pivotal role in handling navigation events within the app. Using the `NavController`, this module manages navigation commands through a shared flow, allowing ViewModels or other components to emit commands for navigation. The code defines an abstract base class, `Navigator`, providing a clean and extensible structure for handling navigation. The separation of navigation logic into this module enhances code organization, and facilitates the decoupling of navigation concerns from other parts of the app.

### 4. core-model

The `core-model` encapsulates fundamental data structures and business entities essential to the application's functionality. Included in this module are the `ErrorModel` and `SuperHeroModel`.

### 5. core-network

The `core-network` module encapsulates all logic related to fetching data from the network. It provides a service interface as an abstraction for the `core-data` module and includes Data Transfer Objects (DTOs) such as `SuperHeroNetworkResponse` and mappers to transform data between network responses and the `SuperHeroModel`. This separation allows for a clean division of responsibilities, making it easier to modify or extend the networking logic without affecting other parts of the application.

### 6. core-database

The `core-database` module handles data storage logic and provides the `SuperHeroDao` as an abstraction for the `core-data` module. It includes Database Entities like `SuperHeroEntity` and mappers for transforming data between the `SuperHeroModel` and the database representation. By isolating database-related operations in this module, the app achieves separation of concerns and facilitates the replacement or upgrade of the database implementation without impacting other modules.

### 7. core-preferences

The `core-preferences` module serves as an abstraction for Preferences storage, providing a standardized approach to handle app preferences. This separation ensures that preference-related logic is encapsulated, promoting modularity and allowing for easy replacement or modification of the preference storage mechanism.

### 8. core-data

The `core-data` module acts as the orchestrator, utilizing mappers and abstractions from the `core-network`, `core-database`, and `core-preferences` modules. It creates the `HomeRepository` and `DetailsRepository` as abstractions used by the feature module. This module encapsulates the business logic associated with data retrieval, ensuring a clean separation of concerns and facilitating the maintenance and extension of data-related functionalities.

## Feature-Modules

### 9. feature-home

The `feature-home` module introduces the `HomeScreen` composed of Jetpack Compose UI components and the `HomeViewModel`. The ViewModel utilizes the `HomeRepository` to update data, providing a clean architecture that separates UI presentation from business logic. This modular approach enhances code organization, readability, and testability, allowing for independent development and testing of the home feature.

### 10. feature-details

The `feature-details` module mirrors the structure of `feature-home` and includes the `DetailsScreen` composed of Jetpack Compose UI components and the `DetailsViewModel`. Similar to the home feature, this module ensures a clean separation of concerns, allowing developers to work independently on the details feature. The ViewModel utilizes the `DetailsRepository` to update data, promoting a consistent and maintainable codebase.

# App Architecture

The architecture of the SuperHero app is designed following the principles outlined in the [Guide to app architecture](https://developer.android.com/topic/architecture) provided by Android developers. The chosen architecture ensures a clean separation of concerns, maintainability, and scalability.

## Repository Pattern

The app adopts the Repository pattern to manage data access. The Repository acts as a single source of truth for data, abstracting away the underlying data sources (Network, Database, and Preferences). It utilizes the abstractions provided by each module, namely `core-network`, `core-database`, and `core-preferences`. This approach simplifies the swapping of data sources and promotes modularity.

## Data Abstractions

Foundational interfaces within the core-network module facilitate data retrieval from the network, while those in the core-database module handle local storage operations. Additionally, interfaces in the core-preferences module are dedicated to managing user preferences. This modular design aligns with the principles of Clean Architecture, enabling each module to concentrate on its distinct responsibilities. By establishing these clear data abstractions, the application benefits from enhanced maintainability, scalability, and a separation of concerns.

## Mapper Integration

To seamlessly transform data between different layers of the app, the repository leverages mappers provided by each module. Mappers play a crucial role in ensuring that data is properly converted from network responses and database entities to the standardized models in the `core-model` module. This enhances maintainability by encapsulating data transformation logic within individual modules.

## ViewModel

The ViewModel interacts with the repository abstraction to fetch and manage data, ensuring that business logic is separated from the UI. The ViewModel is lifecycle-aware, preserving data across configuration changes and providing a smooth user experience.

## UI Architecture

The user interface, whether implemented using traditional Activities or Jetpack Compose, interacts with the ViewModel to retrieve and display data. This adheres to the recommended Model-View-ViewModel (MVVM) architecture, promoting a clear separation of concerns. The UI components observe changes in the ViewModel's data, ensuring a reactive and efficient UI.


## Architecture Diagram

![Architecture](https://i.imgur.com/gpupbMD.png)

The architecture diagram visually represents the relationships between different architectural components, emphasizing the clean separation of concerns and the flow of data within the app. This visual guide serves as a reference for developers working on the SuperHero app, promoting a shared understanding of the app's architecture.

## Choices Made

### Unidirectional Data Flow Pattern

The SuperHero app adopts the Unidirectional Data Flow pattern inspired by the official Android documentation on [Architecture](https://developer.android.com/topic/architecture#unidirectional-data-flow). In this pattern, UI state flows in a single direction—from the ViewModel to the UI—and events that modify the data flow in the opposite direction. The Repository serves as the single source of truth, and the ViewModel is responsible for managing communication between the Repository and the UI. This pattern enhances predictability, simplifies debugging, and ensures a more maintainable codebase.


### Android Jetpack Paging Library

For efficient and seamless pagination, the SuperHero app integrates the Android Jetpack Paging library, leveraging its capabilities without reinventing the wheel ([Paging Library Overview](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)). This library facilitates the loading and display of pages of data from a larger dataset, whether stored locally or retrieved over the network. The Paging library aligns with the recommended Android app architecture, seamlessly integrating with other Jetpack components and providing robust Kotlin support.

#### Key Features of the Paging Library:

- **In-Memory Caching:** The library incorporates in-memory caching to optimize system resources when working with paged data.

- **Request Deduplication:** Built-in support for request deduplication ensures efficient utilization of network bandwidth and system resources.

- **Kotlin Coroutines:** The library offers first-class support for Kotlin coroutines, simplifying asynchronous programming.

- **Error Handling:** The Paging library includes built-in support for error handling, incorporating refresh and retry capabilities.

## Efficient Data Passing between Screens

In the SuperHero app, rendering the DetailsScreen doesn't necessitate an extra server call since all the necessary data is already available from the HomeScreen. To ensure efficient data passing between the HomeScreen and DetailsScreen, the app adheres to Android's recommended approach of passing minimal necessary information between destinations ([Pass Data Between Destinations](https://developer.android.com/guide/navigation/use-graph/pass-data#supported_argument_types)).

When a user taps on a hero in the `HomeScreen`, the `HomeRepository` utilizes the `SuperHeroDataSource` as the single source of truth to store the superhero data. The `DetailsViewModel`, in turn, uses the `DetailsRepository`, which leverages the same SuperHeroDataSource to retrieve the SuperHero and subsequently cleans the stored data. Internally, the `SuperHeroDataSource` utilizes preferences, facilitated by the `PreferenceStorage` interface from the `core-preference` module. This approach enhances data consistency and simplifies the process of recreating UI data while avoiding unnecessary server calls.

As per documentation:

> **Caution:** Passing complex data structures over arguments is considered an anti-pattern. Each destination should be responsible for loading UI data based on the minimum necessary information, such as item IDs. This simplifies process recreation and avoids potential data inconsistencies.

These architectural choices collectively contribute to the SuperHero app's robustness, scalability, and adherence to best practices in Android development.

## Icons Attribution

The icons used in the SuperHero app are sourced from [icons8.com](https://icons8.com/icons/set/marvel-superheroes), and all rights are reserved to Icons8.
