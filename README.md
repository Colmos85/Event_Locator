# Event Locator

1. Make a clone of the repository and unzip to a folder.
2. To run this application open a terminal and cd to the folder
3. enter> java app.jar

###### Requirements
Java to be installed on the system.  
Global variable for java should be added. 
 
 
 
 
###### Spiral Search follows a pattern of:  <br />
********************<br />
Left  - 1 movement <br />
Down  - 1 movement <br />
------------------- change sign & increment numMovements <br /> 
Right - 2 movements <br />
Up    - 2 movements <br />
******************** increment numMovements<br />
Left  - 3 movements <br />
Down  - 3 movements <br />
------------------- change sign & increment numMovements <br /> 
Right - 4 movements <br />
Up    - 4 movements <br />
******************** increment numMovements<br />
. <br />
. <br />
. <br />
******************** increment numMovements <br />
Left  - 19 movements <br />
Down  - 19 movements <br />
------------------- change sign & increment numMovements <br />
Right - 20 movements <br />
Up    - 20 movements <br />
******************** <br />
Final top row has special case in the algorithm <br />


<br />
<br />
## Most recent commit changed how search works <br />
The previous algorithm did not search uniformly at the same distance, so was flawed
Now, the pattern starts from where the user is and checks all coordinates at a distance of one away.
Then increments the distance and checks all next coordinates.
If the coordinate is outside of the range of the 2D map it will ignore this, to avoid index out of bounds exception.



