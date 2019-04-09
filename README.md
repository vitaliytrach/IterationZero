# IterationZero

Iterations Zero is an Isometric style game where the player can roam various maps, kill monsters, purchase new items 
with gold collected, and much more. Currently the game is in the early state of development, so be cautious that 
not all the features have been implemented, and right now it's being built for single player only.

Install:
    1. Clone master using "git clone https://github.com/vitaliytrach/IterationZero.git"
            - If you are using SSH, "git clone git@github.com:vitaliytrach/IterationZero.git"
    2. Importing project into IntelliJ IDEA.
            - If you do not have IntelliJ on your machine, you can download it 
              at the JetBrains website, "https://www.jetbrains.com/idea/"
                    - Make sure you include the gradle extension, or as always
                      you can use "brew install gradle"
            - Open IntelliJ
            - Select "Import Project"
            - Locate the project you cloned in the finder, and select "build.gradle"
            - Selecting "Use default gradle wrapper" is recommended, and select "ok"
                    - Sidenote: Make sure "Gradle JVM" leads to the correct path
    3. Configuring the Run/Debug Configuration
            - On the top right of IntelliJ, left of the green play button is a drop down menu.
                    - Click the drop down menu and choose "Edit Configuration"
            - The Run/Debug Configuration window should appear,
                    - On the top left corner of the window, click the "+" icon and 
                      add a new "Application" configuration.
            - For the "Main class," find "com.mygdx.game.desktop.DesktopLauncher"
            - For "Working Directory," locate that assets folder.
                    - It should be under "../IterationZero/core/assets"
            - Lastly, select "Desktop" as the "Use Classpath of Module"
            - Click Apply -> Ok
    4. Build, and run the program
    
Game Mechanis
    The player moves with the left, right, up, and down arrow keys.
            

Framework: LibGDX, Java wrapper for openGL graphics rendering
Architecture: Entity Component System, this architecture prioritizes composition over inheritence. 

