# ChristmasBingo
## Compiling and Running 

A Christmas bingo master board game. This is to be used along side a physical board and markers. The program can be run in two ways. 

1. Default Way: This uses the shell script. This uses the default game size of 75 bingo balls. Run
      ./ChristmasBingo 
  
2. Hardcode: This lets the user choose the game size. Compile the program from the src/ folder using the command 
      javac*.java  
   After using this command type 
      java StartGame <gameSize> 

## User Interface 
The user interface is pretty intuative and not a complicated design. A random number within the set of the game size will be generated and displayed each time you press the "Roll Bingo Ball" button. You need to press the associated ball in the grid to keep track of which balls have already been played. At the end you can reset the game by pressing the "reset game" button. 
