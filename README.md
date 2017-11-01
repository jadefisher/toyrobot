Toy Robot Simulator
===================

Description
-----------
This is an implementation of the REA Toy Robot Simulator for a job application code test.

Running
-----

### Using Gradle
    ./gradlew run -q

### Building and running jar
    ./gradlew build
    java -jar build/libs/rea-robot.jar
    
### Running tests
    ./gradlew test

Using the application
---------------------
The application is a CLI that takes input from stdin. You can interactively run the 
application or pipe a text file in.
 
    PLACE 0,0,NORTH
    MOVE
    REPORT

Expected output:

    0,1,NORTH

Or with a file:

    ./gradlew run -q < sample_input.txt

Design
------

    +-----------------------------+
    |       ToyRobotRunner        |
    |                             |         Creates Initial State
    |                             +---------------------+
    |                             |                     |
    +-----------------------------+                     |
                                                        |
                                                        |
                                                        |
    +-----------------------------+     +---------------v-------------+
    |       CommandDispatcher     |     |      TableTopState          |
    |                             |     |                             |
    |                             |     |                             |
    |                             |     |                             |
    +-----------------------------+     +-----------------------------+


                                        +-----------------------------+
                                        |      RobotState             |
                                        |                             |
                                        |                             |
                                        |                             |
                                        +-----------------------------+

