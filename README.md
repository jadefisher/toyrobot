Toy Robot Simulator
===================

Description
-----------
This is an implementation of the Toy Robot Simulator for a job application code test.

Requirements
------------
Java 8 is required run this application.

Running
-----

### Using Gradle
    ./gradlew run -q


> NOTE: On Windows use ```gradlew.bat``` instead of ```./gradlew```

### Building and running jar
    ./gradlew build
    java -jar build/libs/toy-robot.jar
    
### Running tests
    ./gradlew test

Using the application
---------------------
The application is a CLI that takes input from stdin. You can interactively run the 
application as described above, or pipe a text file in.
 
Sample Input:

    PLACE 0,0,NORTH
    MOVE
    REPORT

Expected output:

    0,1,NORTH

Or with a file:

    ./gradlew run -q < sample_input.txt

Expected output:

    3,3,NORTH
    
Design
------
The main classes are:

- ```ToyRobotRunner```
-- It includes the static main method that's invoked when you run the application. It also
acts as the runner object which creates the initial ```TableTopState```, and routes commands from
the input stream to the ```CommandDispatcher```.

- ```CommandDispatcher```
-- It parses the textual commands and converts them into actions on the ```TableTopState```.

- ```TableTopState```
-- It stores the size of the tabletop, and holds the ```RobotState```, once the robot is placed on
the table top. This class delegates actions relating to the robot to the ```RobotState```, and also
has the logic as to whether a given state is valid or not. For examle the state is invalid if
the robot is off the table.

- ```RobotState```
-- It just holds the robot's position and direction. It provides the behaviour of turning LEFT
and RIGHT, and also the behavior of MOVEing in the direction the robot is facing.


                                                                              
                                                                              
    +----------------------------+                                                
    |                            |                                                
    |                            |          Creates Initial State                 
    |        ToyRobotRunner      ------------------------------+                  
    |                            |                             |                  
    |                            |                             |                  
    +--------------|-------------+                             |                  
                   |                                           |                  
                   |Delegates stream input to                  |                  
                   |                                           |                  
    +--------------|-------------+              +--------------|-------------+    
    |                            |              |                            |    
    |                            |              |                            |    
    |      CommandDispatcher     ----------------       TableTopState        |    
    |                            |   Executes   |                            |    
    |                            |   commands   |                            |    
    +----------------------------+              +--------------|-------------+    
                                                               |                  
                                                               |                  
                                                               | Delegates actions
                                                               |                  
                                                               |                  
                                                +--------------|-------------+    
                                                |                            |    
                                                |                            |    
                                                |        RobotState          |    
                                                |                            |    
                                                |                            |    
                                                +----------------------------+    

