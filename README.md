# Guessing Game in Java
This guessing game in Java was created as part of a course assignment for the "Object Oriented Programming" class given by B. Boigelot at the University of Liège. The game is adaptable as long as the .txt file follows the right convention.

## How to play

The game is simple: the program will ask you questions you can answer with yer or no to try to find what you are thinking about. If the game doesn't find what you were thinking about, it will add the new knowledge to its database in the .txt file.

## How to run the game

To run the game, first ensure that you have Java installed on your computer. Then, download the project files and navigate to the project directory in your terminal. Compile the program by running the command:
```
javac Game.java
```

Once the program is compiled, you can run the game by running the command:
```
java Game game-tree.txt
```


## Adapting the Game

If you want to adapt the game to use a different category, simply modify the contents of the .txt file following the convention described hereunder. The program will automatically adjust to the new data set.  
  
The first line must have the number of nodes + text prompted at the beginning.  
Then the questions must start with '?' and then be followed with n1 and n2, the indexes of the answers, before having the text of the question without question mark.  
The answers must start with '=' and be followed with the text of answer.  
