# 2048
Project for 335 by Apurbo Barua, Ashutosh Dayal, Jacob Dority, Pri Vaghela

# Organization and Functionality
This project is separated into 3 packages: backend, frontend, and the default package.

Backend:
Contains the backend information that the frontend relies on. GameLogic.java describes all of the core functionality of the game, including how moving in each direction is calculated, how tile merging is calculated, which conditions lead to a lost game, how new tiles are added and where they are added, as well as the overall grid model that the game relies on. GameState.java stores the game information into a single object that tracks the grid's data throughout the game's runtime. ScoreManager manages the score calculations, providing a controller for the model set up in GameLogic.java.

This package also contains the test cases for each one of these classes, entitled GameLogicTest.java, GameStateTest.java, and ScoreManagerTest.java. Each of these test cases get at least 90% coverage for the entirety of the backend logic.

Additionally, this package contains a console-based UI for the 2048 game, stored in MainBack.java. It is very simple, allowing for quick testing during backend development without needing to interact with the GUI, effectively ensuring our frontend and backend are modular.

Frontend:
Contains all of the GUI used for the game. Tile.java represents a GUI tile that stores an integer and changes color depending on the value of the tile. This also provides a simple setter to set the integer's value. InputHandler.java contains the interpretation for each input that the player uses to play the game. As mentioned previously, the game is played through the keyboard's arrow keys. This class interprets this information and interfaces with gameLogic.java in the backend package to provide functionality to the game. Additionally, this package contains GameUI.java, which creates and manages each panel used in the end-product GUI, including the grid panel, the score panel, the refresh functionality, and the reset button.

Default Package:
The default package includes Main.java, which is used in order to run the program. All it does is create a new GameUI() object, that will start the game process.
