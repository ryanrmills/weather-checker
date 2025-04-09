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

## Wave 2: Reading from System.in
Open `WeatherFetcher.java`. This is where you will be doing your coding for this assignment. For Wave 2, please use a Scanner to read doubles from System.in, and print each double on a new line. To run your code, have it read from the temps file your created in Wave 1. This command will compile your code and run it while directing the temps file into System.in.
```
javac src/WeatherSummary.java && java -cp src WeatherSummary < temps
```
Once your code works, please add, commit, and push it. Feel free to make smaller commits along the way as you progress!
> Hint: `nextDouble` and `hasNextDouble` will be helpful for this wave!

## Wave 3: Max and Min
In this wave, please edit your WeatherFetcher so that it does not print out every temperature. Instead, it should calculate the maximum and mininmum of the temperatures provided from System.in. Have it print out like so:
```
Max: 66.4
Min: 77.1
```
Your numbers will be different based on the actual weather. Remember to add commit and push!

## Wave 4:
Now add functionality to compute and print the average temperature as well:
```
Max: 66.4
Min: 77.1
Average: 72.03333333333333
```

Add commit, and push. Make a pull request and submit the link on Canvas. Great job!