Toy Robot Simulator
===================

Description
-----------
This is an implementation of the Toy Robot Simulator for a job application code test.

Requirements
------------
Java 8 is required run this application...

Running
------

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
 
### Simple example input:

    PLACE 0,0,NORTH
    MOVE
    REPORT

Or run from supplied file:

    ./gradlew run -q < example_1.txt
    
Expected output:

    0,1,NORTH
    
### Example trying to move robot off table:

    REPORT
    PLACE 0,0,NORTH
    MOVE
    MOVE
    MOVE
    MOVE
    REPORT
    MOVE
    REPORT

Or run from supplied file:

    ./gradlew run -q < example_2.txt

Expected output:

    0,4,NORTH
    0,4,NORTH
    
>NOTE that the first REPORT command was ignored, and the last MOVE was also ignored

### Example with invalid PLACE, and subsequent overriding PLACE command:

    PLACE 5,0,NORTH
    REPORT
    PLACE 2,2,EAST
    LEFT
    LEFT
    REPORT
    PLACE 3,3,SOUTH
    REPORT

Or run from supplied file:

    ./gradlew run -q < example_3.txt

Expected output:

    2,2,WEST
    3,3,SOUTH
    
>NOTE that the first PLACE and REPORT commands were ignored, and the last PLACE command overrode
the current position

### Example showing all commands ignored until first valid PLACE:

    LEFT
    LEFT
    REPORT
    RIGHT
    RIGHT
    REPORT
    MOVE
    MOVE
    REPORT
    PLACE 2,2,EAST
    REPORT

Or run from supplied file:

    ./gradlew run -q < example_4.txt

Expected output:

    2,2,EAST

>NOTE that all commands are ignored until the first valid PLACE command
    
Design
------
The main classes are:

- ```ToyRobotRunner``` includes the static main method that's invoked when you run the application. It also
acts as the runner object which creates the initial ```TableTopState```, and routes commands from
the input stream to the ```CommandDispatcher```.

- ```CommandDispatcher``` parses the textual commands and converts them into actions on the ```TableTopState```. If an 
action results in an invalid state, the previous state is returned.

- ```TableTopState``` stores the size of the tabletop, and holds the ```RobotState```, once the robot is placed on
the table top. This class delegates actions relating to the robot to the ```RobotState```, and also
has the logic as to whether a given state is valid or not. For example the state is invalid if
the robot is off the table or not even placed.

- ```RobotState``` holds the robot's position and direction. It provides the behaviour of turning LEFT
and RIGHT, and also the behavior of MOVEing in the direction the robot is facing.

>Both ```TableTopState``` and ```RobotState``` are immutable, so changes to them result in new
states being created.

```
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
```
