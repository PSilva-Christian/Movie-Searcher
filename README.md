🎬 Movie Catalog Searcher

A Spring Boot application that allows users to search for movie details. The app integrates with the OMDB API to fetch data and utilizes PostgreSQL for persistent storage.

🚀 Features

    Real-time API Integration: Fetches movie titles, years, genres, plot and posters directly from OMDB.

    Responsive UI: Built with Bootstrap for a clean, dark-themed movie catalog look.

    Modern Java Backend: Uses the new Spring 6 RestClient for fluent HTTP calls.

🛠️ Tech Stack

    Backend: Java 17+, Spring Boot 3.x

    Frontend: Thymeleaf, Bootstrap 5

    Database: PostgreSQL

    API: OMDB API

🏗️ Architecture

The project follows a standard MVC (Model-View-Controller) pattern:

    Controller: Handles incoming GET/POST requests and manages the Model attributes.

    Service: Contains the business logic and uses RestClient to communicate with OMDB.

    Model (DTO): A Java Record (FilmDetails) that maps JSON response keys (like Title and Poster) to Java fields using Jackson annotations.
