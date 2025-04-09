# weather-checker

An activity for summarizing temperatures for a given area! You must make frequent commits as you work to receive credit for this assignment.

## Wave 1: Setup and fetching data
1. Fork and clone this repository. MAKE SURE TO FORK BEFORE YOU CLONE! Use your terminal to open VS Code. Feel free to review previous assignments for how to do this. MAKE SURE WHEN YOU OPEN IT IN VS CODE YOU ARE IN THE WEATHER-CHECKER DIRECTORY, NOT THE DIRECTORY ABOVE IT.
1. We will use the provided WeatherFetcher class to get the weather data from the last 30 days. You do not need to understand how this class works or make any modifications to it! It uses advanced techniques beyond what you have likely learned so far. Optionally eel free to look at it for learning purposes. To use the class, we will first compile it:
    ```
    javac src/WeatherFetcher.java
    ```
1. Next, run the weather fetcher to download the last 30 days of max temperatures for a given area and save them in a file named `temps`. Provide the latitude and longitude of the area you are interested in. For example, to fetch the data for Auburn, WA, use this command:
    ```
    java -cp src WeatherFetcher 47.3073 -122.2285 > temps
    ```
1. This will create a new file named `temps`. Open it in VS Code and verify that it holds the temperatures. Add, commit, and push this file with git.

## Wave 2