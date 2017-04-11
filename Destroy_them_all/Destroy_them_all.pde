/*
  Wonseok Cho, David Klingler, Giles Fowles
  March 2017
  This is the main file that controls all the screens
*/

String gameState = "MAIN MENU";
GameStart PlayGame = new PlayGame();

PImage trees;
PImage trees2;
PImage sky;

PImage bearSprite;
Bear player = new Bear();

PFont robotoCondensed;


void setup() {
  frameRate(240);
  size(800, 600);
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);
  trees = loadImage("Graphics/Trees-01.png");
  trees2 = loadImage("Graphics/Trees-01.png");
  sky = loadImage("Graphics/Sky-01.png");
  robotoCondensed = loadFont("Fonts/RobotoCondensed-Bold-50.vlw");
  bearSprite = loadImage("Graphics/Bear.png");
  loadSprites();
}

void draw() {
  switch(gameState){
    case "MAIN MENU":
      startMenu();
      break;
    case "OPTIONS":
      break;
    case "GAME START":
      PlayGame.gameStart();
      break;
    case "GAME OVER":
      break;
    case "PAUSE":
      break;
  }
}

void keyPressed() {
  switch(gameState) {
    case "MAIN MENU":
      if(key == ENTER) {
        switch(selectMenu) {
          case 0:
            startGame = true;
            break;
          case 1:
            break;
          case 2:
            break;
        }
      } else if (keyCode == UP) {
        selectMenu--;

        // From top selection to the bottom when pressed 'up'
        if (selectMenu < 0) selectMenu = 2;
      } else if (keyCode == DOWN) {
        selectMenu++;
        // From bottom selection to the top when pressed 'down'
        if (selectMenu > 2) selectMenu = 0;
      }
      break;
    case "GAME START":
      if(key == ' ') {
        playerJump = true;
      }
      break;
  }
}
