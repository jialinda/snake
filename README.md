# snake
CIS120 Final Projec: tInteractive Snake Game

Implemented an interactive snake game using left, right, down & up arrows
Data structures: 2D arrays, JUnit Testable Component, Recursion,
 Inheritance & Subtyping
 
  1) Apple - this class is the "food" that the Snake eats, which is represented by a red dot. The
  whole functionality of this class is to simply randomly generate an "apple" on this 30 x 30 game board.

  2) Direction - this class remains unchanged from the starter code, it just stores the directions
  that are used in GameObj.

  3) GameCourt - this class is where all the main game functions come into play. for example, this class holds
  the implementation for when the snake hits a wall, eats an apple, or collides into itself. this records the keyboard
  as well and directs the snake based on what key is pressed.

  4) GameObj - this is the abstract class for all the objects in the Snake game. I did not change
  anything to this Class, I used the same one as the Mushroom game starter code. Essentially, this class
  allows all the other classes in this game to use its get methods for certain variables.

  5) Snake - this class represents the snake that is shown in our game. The snake moves when the right, left, up, or
  down arrow is pressed, and the rest of its body will follow suit. this class includes other important methods as well,
  such as the "grow" method (which is called when the snake intersects the apple), hitItself method (which ends the game
  if the Snake hits itself), hits a wall, etc.

  6) WelcomeSnake - this class specifies the frame and widgets of the GUI. it introduces the player when the game is
  started with a welcome message. other than that, no other changes were made to the starter code that was given.

  7) Poison - this class is the bad apple that the Snake eats, which is represented by a yellow dot. The
     whole functionality of this class is to simply randomly generate an "bad apple" on this 30 x 30 game board.
