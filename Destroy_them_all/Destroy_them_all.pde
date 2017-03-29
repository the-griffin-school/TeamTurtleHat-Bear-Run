/*
  Wonseok Cho, David Klingler, Giles Fowles
  March 2017
  This is the main file that controls all the screens
*/

String gameState = "MAIN MENU";
PImage trees;
PImage sky;
PImage bearPic;


void setup() {
  size(800, 600);
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);
  trees = loadImage("Graphics/Trees-01.png");
  sky = loadImage("Graphics/Sky-01.png");
  bearPic = loadImage("Graphics/Bear.png");
  bear = new Bear();

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