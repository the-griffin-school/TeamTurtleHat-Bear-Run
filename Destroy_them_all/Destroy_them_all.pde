/*
  Wonseok Cho, David Klingler, Giles Fowles
  March 2017
  This is the main file that controls all the screens
*/

String gameState = "MAIN MENU";
PIMage trees;
PImage sky;

void setup() {
  trees = loadImage("Graphics/Trees-01.png");
  sky = loadImage("Graphics/Sky-01.png");

}

void draw() {
  switch(gameState){
    case "MAIN MENU":
      startMenu();
      break;
    case "OPTIONS"
      break;
    case "GAME START"
      break;
    case "GAME OVER"
      break;
    case "PAUSE"
      break;
  }
}
