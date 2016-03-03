# Sample Code for Datto

This sample project was created with Gradle. The Gradle Wrapper has been included in this project so that some one can build and execute
the project even if Gradle is not previously installed on their system. The Gradle Wrapper will download and install the required version of Gradle for this project. 

However, this project requires *JDK 1.8 to build and execute*.

## Source Code
The source code for this exercies is located in the following file:


    ./src/main/java/Parser.java


The method to call is `findWordWithMaxRepeatingLetter(String inputFileName)`


## Test Case
A JUnit test class has been provided to cover test cases. It uses input data files located under the `./input/` directory.


To run:

    ./gradlew test


Test results are written to `./build/reports/tests/classes/ParserTest.html`