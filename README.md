# Project Description:
Application that manages NBA player details. Collect, parse and process. 
Spring Boot java application with mvn.

**In this project I implemented below:**
1.	MVC Protocol, web server.R
2.	Caching handler.
3.	Web Socket.

**Run the project:**
1.	Import the project.
2.	Run mvn clean install in the root folder.

There is an input file called players.csv with the basic details about 9 players.

**End point:** http://localhost:8080/player

Reading the existing csv file, call to balldontlie API to fetch additional details of the player.
Creating new playersOutput.csv file which contains all the information about the players.
Before calling balldontlie API we will check if the player exist in the Json file(cache), if yes, we will fetch it from the cache, else we will call balldontlie API and will save the new player in the cache.
This behavior will be executed every 15 min since the first time it executed manually(by calling the end point).  

**End point: http://localhost:8080/ ->WebSocket.**

End point: http://localhost:8080/ ->WebSocket.

![image](https://user-images.githubusercontent.com/104754112/166199424-1528872b-f7ba-4678-be5f-13dcee70e2d1.png)

Implemented as a chat architecture: 
1.	 send message to server and get response.
2.	Send message from server to client.

On every change in the cache file(players details are changed), we will send a message to client, and client will display it on the screen.

**For Testing:**  
Open 2 browsers:
1.	On http://localhost:8080/ : Start the web socket connection:  

![image](https://user-images.githubusercontent.com/104754112/166199865-b63f027b-9415-417d-b6f4-21e47887ec65.png)

2.	Open http://localhost:8080/player 
3.	Change the input csv file(resources/CSVFiles/players.csv), add new player/change player id.

In the next executing of player the server will send message to client and notify that there was a change, client will print the change time on the screen.

![image](https://user-images.githubusercontent.com/104754112/166199936-b96951a7-61f7-4666-969c-9964819ad40c.png)



