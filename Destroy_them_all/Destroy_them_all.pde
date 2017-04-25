/*
  Team-turtle-hat
  Wonseok Cho, David Klingler, Giles Fowles
  March 2017
  This is the main file that controls all the screens
*/
String gameState = "MAIN MENU";
PlayGame playGame = new PlayGame();
MainMenu mainMenu = new MainMenu();

//PShape sky;
PImage sky;
PShape bearSprite;
Bear player = new Bear();
GameOver gameOver = new GameOver();
PFont robotoCondensed;


void setup() {
  size(1100, 600);
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);

  sky = loadImage("Graphics/Environment/Sky/SkyImage.png");
  robotoCondensed = loadFont("Fonts/RobotoCondensed-Bold-50.vlw");
  bearSprite = loadShape("Graphics/Bear/Bear.svg");
  loadSprites();

}

void draw() {
  if (frameCount % 60 == 0) {
    println("Frame rate = " + frameRate);
  }
  switch(gameState){
    case "MAIN MENU":
      mainMenu.display();
      break;
    case "OPTIONS":
      break;
    case "GAME START":
      playGame.display();
      break;
    case "GAME OVER":
      gameOver.display();
      break;
    case "PAUSE":
      break;
  }
}

void keyPressed() {
  switch(gameState) {
    case "MAIN MENU":
      if(key == ENTER) {
        switch(mainMenu.selectMenu) {
          case 0:
            mainMenu.startGame = true;
            break;
          case 1:
            break;
          case 2:
          //optionsMenuBackground();
            break;
        }
      } else if (keyCode == UP) {
        mainMenu.selectMenu--;

        // From top selection to the bottom when pressed 'up'
        if (mainMenu.selectMenu < 0) mainMenu.selectMenu = 2;
      } else if (keyCode == DOWN) {
        mainMenu.selectMenu++;
        // From bottom selection to the top when pressed 'down'
        if (mainMenu.selectMenu > 2) mainMenu.selectMenu = 0;
      }
      break;
    case "GAME START":
      if(key == ' ') {
        playGame.playerJump = true;
      }
      break;
  }
}
