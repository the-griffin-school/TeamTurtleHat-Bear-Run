/*
Team-turtle-hat
 Cho, Giles, David
 March 2017
 Displays and controls the main menu of the game
 */


class MainMenu {
  //Fields
  boolean startGame;
  boolean options;
  int selectMenu;
  float scaleFactor;

  //Constructor
  MainMenu() {
    startGame = false;
    options = false;
    selectMenu = 0;
    scaleFactor = 1.5;
  }

  //Methods
  void drawSky() {
    image(sky, 0, 0);
  }

//Draws Title at top center
  void drawTitle() {
    fill(255);
    textAlign(CENTER);
    textFont(robotoCondensed);
    textSize(50);
    text("RIGHT TO BEAR ARMS", width/2, 150);
    stroke(255);
    strokeWeight(5);
    noFill();
    rectMode(CENTER);
    rect(width/2, 130, 500, 100);
  }

//display function and associated functions
  void display() {
    drawSky();
    drawTitle();
    menuSelection();
  }

//Main Menu selection cases
  void menuSelection() {
    // menu selection
    switch(selectMenu) {
    case 0:
      textSize(40);
      text("Play", width/2, 300);
      textSize(30);
      text("Stats", width/2, 350);
      text("Options", width/2, 400);
      text("How to Play", width/2, 450);
      break;
    case 1:
      textSize(40);
      text("Stats", width/2, 350);
      textSize(30);
      text("Play", width/2, 300);
      text("Options", width/2, 400);
      text("How to Play", width/2, 450);
      break;
    case 2:
      textSize(40);
      text("Options", width/2, 400);
      textSize(30);
      text("Play", width/2, 300);
      text("Stats", width/2, 350);
      text("How to Play", width/2, 450);
      break;
    case 3:
      textSize(40);
      text("How to Play", width/2, 450);
      textSize(30);
      text("Play", width/2, 300);
      text("Stats", width/2, 350);
      text("Options", width/2, 400);
      break;

    }
    //if user pressed ENTER
    if (startGame) {
      gameState = "GAME START";
    }
    if (options) {
      gameState = "OPTIONS";
    }
  }
}
