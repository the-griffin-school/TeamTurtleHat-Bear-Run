/*
  Wonseok Cho, David Klingler, Giles Fowles
  March 2017
  This is the main file that controls all the screens
*/

String gameState = "MAIN MENU";
PImage trees;
PImage sky;
PImage bearSprite;
Bear player = new Bear();

PFont robotoCondensed;


void setup() {
  frameRate(60);
  size(800, 600);
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);
  trees = loadImage("Graphics/Trees-01.png");
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
    gameStart();
      break;
    case "GAME OVER":
      break;
    case "PAUSE":
      break;
  }
}

void keyPressed() {
  if(key == ENTER && gameState == "MAIN MENU") {
    startGame = true;
  }
  if(key == ' ' && gameState == "GAME START") {
    playerJump = true;
  }
}
