# Event Locator

1. Make a clone of the repository and unzip to a folder.
2. To run this application open a terminal and cd to the folder
3. enter> java -jar app.jar

###### Requirements
Java to be installed on the system.  
Global variable for java should be added. 
 
 

## Most recent commit changed how search works <br />
The previous algorithm did not search uniformly at the same distance, so was flawed.  

Now, the pattern starts from where the user is and checks all coordinates at a distance of one away.
Then increments the distance and checks all next coordinates.
If the coordinate is outside of the range of the 2D map it will ignore this, to avoid index out of bounds exception.



