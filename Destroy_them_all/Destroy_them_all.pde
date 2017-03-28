/*
  Wonseok Cho, David Klingler, Giles Fowles
  March 2017
  This is the main file that controls all the screens
*/

String gameState = "MAIN MENU";

void setup() {

}

void draw() {
  switch(gameState){
    case "MAIN MENU"
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
