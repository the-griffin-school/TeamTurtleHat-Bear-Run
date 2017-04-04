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
//PFont ubuntuCondensed;


void setup() {
  size(800, 600);
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);
  trees = loadImage("Graphics/Trees-01.png");
  sky = loadImage("Graphics/Sky-01.png");
<<<<<<< HEAD
  ubuntuCondensed = loadFont("Fonts/ubuntuCondensed-Regular-48.vlw");
=======
  bearSprite = loadImage("Graphics/Bear.png");
  //ubuntuCondensed = loadFont("UbuntuCondensed-Regular-48.vlw");
>>>>>>> 35b57afc1d861781a64523a07251945c24265db8
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
