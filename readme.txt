README

To run:
java -jar JAR_NAME_HERE.jar 

	default jar name: maze-kata-client-0.0.1-SNAPSHOT-jar-with-dependencies.jar 


Technical Notes:
MazeKata.java = main.

Connector class which handles requests and returns string representation of the response.

Model class created for easy manipulation.

Builder classes created to create models from simple input. 

Client class which will run like a kiosk until condition met.

Solver class for auto solving depending on each hero power.

Given 4 operations as shown http://ir-interviews.herokuapp.com/mazeClient.html, connector class should be able to handle all use cases. With start operation, the maze is initialized and persona is created. It makes sense to only advance after checking allowed. Allowed check can be done locally from initial map where given dimension and hero power and movement can be used to calculate. Or, we can calculate next position in terms of x and y and use allowed operations to perform the check. For Flash, who has quickFeet, check should be used twice so tiles are not skipped.

It is only possible to turn once per turn. this is useful for calculating shortest path since turns also add to max count. When facing north, it is only possible to go west or east. this behavior can be used to calculate next path. However, we want to minimize the turns since turns also add to the count as mentioned, therefore shortest path might not lead to smallest count.

When checking for hero ability, quick feet is included in the abilities when true. Therefore, it can be disregarded from the original response from start operation.

All interactions are done on command line, so no need for logging. remove slf4j.


TODO:
* test cases
* refractor
** instead of double take, solve while figuring out the path
** refractor out each hero solvers
* Flash auto solve
* Implement Dijkstras for shortest path
** Currently using some github dfs
* Map visualization for auto solve



