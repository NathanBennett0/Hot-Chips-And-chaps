# Hot-Chips-And-Chaps

1. [Setup](https://gitlab.ecs.vuw.ac.nz/course-work/swen225/2022/project1/t9/hot-chips-and-chaps#game-setup)
2. [Game Info](https://gitlab.ecs.vuw.ac.nz/course-work/swen225/2022/project1/t9/hot-chips-and-chaps#game-info)
3. [Warnings](https://gitlab.ecs.vuw.ac.nz/course-work/swen225/2022/project1/t9/hot-chips-and-chaps#warnings)
4. [Gource](https://gitlab.ecs.vuw.ac.nz/course-work/swen225/2022/project1/t9/hot-chips-and-chaps#gource-video)
5. [Dependencies](https://gitlab.ecs.vuw.ac.nz/course-work/swen225/2022/project1/t9/hot-chips-and-chaps#dependencies)

## Game Setup
The program has been tested to run on the ECS machine. We recommend using Eclipse as the classpaths have already been configured. However, it can also run on VSC smoothly, and on IntelliJ if we import it through the "Import Project from Existing Sources".

**Set up**
1. Launch Eclipse  
2. Select `File > Open Projects from File System...` and then locate the project folder in the import source.
3. Click `Finish` and it should import all packages correctly.  
4. If ECS machine's Eclipse does not have JDK 17 already, download it [here](https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.tar.gz)

*Configure JavaSE-17 on Eclipse*
1. Go to `Window > Preferences`
2. Expand `Java` and go to `Installed JRE`
3. Click the `Add` button and choose `Standard VM`
4. Choose the JRE file that you've just downloaded and click `Finish`

**Running the Main**

Go to the `nz.ac.vuw.ecs.swen225.gp22.app` package then run the `Main.java` file as a Java Application.

## Game Info
We based the theme of our game on pixel art and went with a relaxing style. The cat is the chap player and needs to collect all the butterfly treasures to unlock his home gate. Bushes are obstacles he needs to cut down using scissors along the way. 

Our app implements all the key-bindings specified in the assignment. A pop-up dialog window has also been added when the game is paused.

Level 2 will automatically continue upon the completion of level one. However, when trying to jump to another level while playing, the app will ask whether or not you want to save the current state.

The game can be saved at any point. Saved games can be resumed by either loading the saved file, or pressing the 'Resume Game' option in the main menubar.

Recordings are made whenever a level is won or lost, then it can be replayed through the 'Recording' button in the Home panel.

Fuzz generates random input (enhanced with intelligence) for one minute to move the chap in the map. FuzzTest1() is used to test Level One and FuzzTest2() tests Level Two. The test ends after one minute, or if the chap dies. The chap often dies before reaching the minute timer in Level Two due to the random input walking it into water. 

## Warnings
When saving a game, persistency overrides the existing file and have the newest information in the xml file. However, the file itself doesn't automically refresh. Thus,  when trying to resume the saved game, it may not load the newest saved game.

**2 things can be done in this case:**
1. Refresh the xml file manually. This can be done by opening the file in the editor. The game will resume at the right place once the file is refreshed.
2. Close the application and re-run and resume the last saved game.

Our game has a very nice background music. However, it is also very loud so be warned when starting the application.

When replaying the recorder at a fast speed just after you've finished a game, you may get an error saying the sound files are too big. The recommended testing is to exit the game first and test the recording again.

## Gource Video
Gource.io video can be found [here](https://youtu.be/guo5vPugr4g). This doesn't include a few of the finishing-touch commits made during the grace period.

## Dependencies
Some breakpoints to highlight where the dependencies are:

**App**

**Recorder**
- Line 29, directionMove.java - Makes an instance of App
- Line 44, directionMove.java - Makes a Controller from App's instance
- Line 65, directionMove.java - returns the class' instance of App

**Domain**
- Line 13, Maze.java - Makes a new Level object from Persistency's package

**Fuzz**
- Line 43 & 64, FuzzTest.java - Makes an instance of App

**Persistency**
- Lines 56-63 Filereader.java - Initialized fields/arrays of the data types Tile, Locked, Key, Chap, InfoField, and Actor
- Line 215 Filereader.java - Creates instance of Key
- Line 223 Filereader.java - Creates instance of Locked
- Line 239 Filereader.java - Creates instance of InfoField
- Line 249 Filereader.java - Creates instance of tile
- Line 71 Filewriter.java - Used Chap.getChest()
- Lines 46-54 Level.java - Instances of Tile, Locked, Key, Chap, and Actor are passed in


**Renderer**
- Line 38, EndPanel.java - Used App.Width and App.Height
- Line 47, GamePanel.java - Instance of App is passed in
- Line 43, ScoreBoardPanel.java - Instance of Maze is passed in
- Line 27, StartPanel.java - Used App.Width and App.Height
