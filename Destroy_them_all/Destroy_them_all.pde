/*
  Team-turtle-hat
  Wonseok Cho, David Klingler, Giles Fowles
  March 2017
  This is the main file that controls all the screens
*/
import ddf.minim.*;
Minim minim;
AudioPlayer backgroundMusic;

String gameState = "MAIN MENU";
PlayGame playGame = new PlayGame();
MainMenu mainMenu = new MainMenu();

//PShape sky;
PImage sky;
PShape bearSprite;
Bear player = new Bear();
GameOver gameOver = new GameOver();
PFont robotoCondensed;

int currentFrameRate;

void setup() {
  size(1100, 600);
  background(0);
  textAlign(CENTER);

  sky = loadImage("Graphics/Environment/Sky/SkyImage.png");
  robotoCondensed = loadFont("Fonts/RobotoCondensed-Bold-50.vlw");
  bearSprite = loadShape("Graphics/Bear/Bear.svg");
  loadSprites();
  playGame.addSprites();
  minim = new Minim(this);
  bearTrapSound = minim.loadFile("Sounds/Traps/bearTrap.wav", 2048);
  boom1 = minim.loadFile("Sounds/Buildings/boom1.mp3", 2048);
  boom2 = minim.loadFile("Sounds/Buildings/boom2.mp3", 2048);
  backgroundMusic = minim.loadFile("Sounds/Background/background1.mp3", 2048);
  loadBear();
  backgroundMusic.loop();
}

void draw() {
  switch(gameState){
    case "MAIN MENU":
      mainMenu.display();
      break;
    case "OPTIONS":
      optionsMenuBackground();
      break;
    case "GAME START":
      playGame.display();
      break;
    case "GAME OVER":
      gameOver.display();
      break;
    case "PAUSE":
      pause();
      break;
  }
  displayFrames();
}

void displayFrames() {
  currentFrameRate = int(frameRate);
  textAlign(CORNERS);
  fill(255);
  noStroke();
  textSize(20);
  text("Frame rate: " + currentFrameRate, 950, 40);
}

//USER INPUTS
void keyPressed() {
  switch(gameState) {
    case "MAIN MENU":
      if(key == ENTER) {
        switch(mainMenu.selectMenu) {
          case 0:
            mainMenu.startGame = true;
            break;
          case 1:
            mainMenu.options = true;
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
        if(!player.getJumping()) {
          player.setCounter(0);
        }
        player.setJump(true);
      } else if(keyCode == ENTER) {

      }
      if(key == 'p' || key == 'P') {
        gameState = "PAUSE";
      }
      break;


    case "GAME OVER":
      if(key == ENTER) {
        player.health = 3;
        playGame.score = 0;
        playGame.setGameSpeed(15);
        for (int i = sprites.size() -1; i >= 0 ; i--) {
          sprites.remove(i);
        }
        mainMenu.startGame = false;
        gameState = "MAIN MENU";
      }
      break;
    case "PAUSE":
      if(key == 'p' || key == 'P') {
        gameState = "GAME START";
      }
  }
}
