/*
  Wonseok Cho, David Klingler, Giles Fowles
  March 2017
  This is the main file that controls all the screens
*/

String gameState = "MAIN MENU";
PImage trees;
PImage sky;
PFont ubuntuCondensed;

void setup() {
  size(800, 600);
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);
  trees = loadImage("Graphics/Trees-01.png");
  sky = loadImage("Graphics/Sky-01.png");
  ubuntuCondensed = loadFont("Fonts/ubuntuCondensed-Regular-48.vlw");
}

void draw() {
  switch(gameState){
    case "MAIN MENU":
      startMenu();
      break;
    case "OPTIONS":
      break;
    case "GAME START":
      break;
    case "GAME OVER":
      break;
    case "PAUSE":
      break;
  }
}
