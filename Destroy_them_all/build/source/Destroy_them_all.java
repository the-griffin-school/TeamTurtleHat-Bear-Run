import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Destroy_them_all extends PApplet {

/*
  Team-turtle-hat
 Wonseok Cho, David Klingler, Giles Fowles
 March 2017
 This is the main file that controls all the screens
 also includes keyPressed which controls the imput from the user
 */

 //Variables

Minim minim;
AudioPlayer backgroundMusic;

int pauseSelect = 0;

String gameState = "MAIN MENU";

//Game States
PlayGame playGame = new PlayGame();
MainMenu mainMenu = new MainMenu();
GameOver gameOver = new GameOver();
Highscore highscores = new Highscore();
Options options = new Options();
HowTo howTo = new HowTo();
Stats stats = new Stats();

//PShape sky;
PImage sky;
PShape bearSprite;
Bear player = new Bear();
PFont robotoCondensed;

int currentFrameRate;

public void setup() {
  
  background(0);
  textAlign(CENTER);

//Images/Sounds/Sprites loading variales
  sky = loadImage("Graphics/Environment/Sky/SkyImage.png");
  //bear = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0000.png");
  bearSprite = loadShape("Graphics/Bear/Bear.svg");
  robotoCondensed = loadFont("Fonts/RobotoCondensed-Bold-50.vlw");
  //loads sprites of buildings and traps
  loadSprites();
  playGame.addSprites();
  minim = new Minim(this);
  //loads sounds
  bearTrapSound = minim.loadFile("Sounds/Traps/bearTrap.wav", 2048);
  boom1 = minim.loadFile("Sounds/Buildings/boom1.mp3", 2048);
  boom2 = minim.loadFile("Sounds/Buildings/boom2.mp3", 2048);
  backgroundMusic = minim.loadFile("Sounds/Background/background1.mp3", 2048);
  //loads 45 images of bears used for animation
  loadBear();
  //plays background music
  backgroundMusic.loop();
}

//void draw: runs the various cases that display our functions.
public void draw() {
  switch(gameState) {
  case "MAIN MENU":
    mainMenu.display();
    break;
  case "OPTIONS":
    options.display();
    break;
  case "GAME START":
    playGame.display();
    break;
  case "GAME OVER":
    gameOver.display();
    break;
  case "PAUSE":
    paused();
    break;
  case "HOW TO":
    howTo.display();
    break;
  case "STATS":
    stats.display();
    break;
  }
  displayFrames();
}

//Displays frame rate counter at top right corner.
public void displayFrames() {
  currentFrameRate = PApplet.parseInt(frameRate);
  textAlign(CORNERS);
  fill(255);
  noStroke();
  textSize(20);
  text("Frame rate: " + currentFrameRate, 950, 40);
}

//USER INPUTS (key functions in various menus).
public void keyPressed() {
  switch(gameState) {
    case "MAIN MENU":
      if (key == ENTER) {
        switch(mainMenu.selectMenu) {
        case 0:
          mainMenu.startGame = true;
          playGame.nightTime = millis();
          break;
        case 1:
          gameState = "STATS";
          break;
        case 2:
          gameState = "OPTIONS";
          break;
        case 3:
          gameState = "HOW TO";
          break;
        }
      } else if (keyCode == UP) {
        mainMenu.selectMenu--;

        // From top selection to the bottom when pressed 'up'
        if (mainMenu.selectMenu < 0) mainMenu.selectMenu = 3;
      } else if (keyCode == DOWN) {
        mainMenu.selectMenu++;
        // From bottom selection to the top when pressed 'down'
        if (mainMenu.selectMenu > 3) mainMenu.selectMenu = 0;
      }
      break;
    case "GAME START":
      if (key == ' ') {
        if (!player.getJumping()) {
          player.setCounter(0);
        }
        player.setJump(true);
      } else if (keyCode == ENTER) {
      }
      if (key == 'p' || key == 'P') {
        gameState = "PAUSE";
      }
      break;


    case "GAME OVER":
      if (keyCode == UP) {
        gameOver.deathSelect--;
        if (gameOver.deathSelect < 0) {
          gameOver.deathSelect = 1;
        }
      }

      if (keyCode == DOWN) {
        gameOver.deathSelect ++;
        if (gameOver.deathSelect > 1) {
          gameOver.deathSelect = 0;
        }
      }
      if (key == ENTER) {
        player.health = 3;
        playGame.score = 0;
        playGame.setGameSpeed(15);
        playGame.nightSwitch = false;
        playGame.alpha2 = 0;
        playGame.adder = 1;
        for (int i = sprites.size() -1; i >= 0; i--) {
          sprites.remove(i);
        }
        if(gameOver.deathSelect == 0) {
          gameState = "GAME START";
        } else {
          mainMenu.startGame = false;
          gameState = "MAIN MENU";

        }
      }

      break;
    case "PAUSE":
      if (keyCode == UP) {
        pauseSelect--;
        if (pauseSelect < 0) {
          pauseSelect = 1;
        }
      }

      if (keyCode == DOWN) {
        pauseSelect ++;
        if (pauseSelect > 1) {
          pauseSelect = 0;
        }
      }

      if (keyCode == ENTER) {
        pauseOnce = false;
        if (pauseSelect == 0) {
          time = millis();
          gameState = "GAME START";
        } else if (pauseSelect == 1) {
          mainMenu.startGame = false;
          time = millis();
          gameState = "MAIN MENU";
        }
      }
        break;
      case "OPTIONS":
        if (keyCode == UP) {
          options.selectMenu--;

          // From top selection to the bottom when pressed 'up'
          if (options.selectMenu < 0) options.selectMenu = 1;
        } else if (keyCode == DOWN) {
          options.selectMenu++;
          // From bottom selection to the top when pressed 'down'
          if (options.selectMenu > 1) options.selectMenu = 0;
        }
        if (keyCode == ENTER) {
          gameState = "MAIN MENU";
        }
        if (keyCode == RIGHT && options.selectMenu == 0) {
          options.diffNum ++;
          if (options.diffNum > 2) {
            options.diffNum = 0;
          }
        }
        if (keyCode == LEFT && options.selectMenu == 0) {
          options.diffNum --;
          if (options.diffNum < 0) {
            options.diffNum = 2;
          }
        }
        if (keyCode == RIGHT && options.selectMenu == 1) {
          options.soundNum ++;
          if (options.soundNum > 1) {
            options.soundNum = 0;
          }
        }
        if (keyCode == LEFT && options.selectMenu == 1) {
          options.soundNum --;
          if (options.soundNum < 0) {
            options.soundNum = 1;
          }
        }
        break;
      case "HOW TO":
        if (keyCode == ENTER) {
          gameState = "MAIN MENU";
        }
        break;
      case "STATS":
          if (keyCode == ENTER) {
            gameState = "MAIN MENU";
          }
        break;
      }
  }
/*
Team-turtle-hat
<<<<<<< HEAD
David, Cho, Giles
March 2017
Class that controls the bear and bear stuff
*/
PImage bearWalk0, bearWalk1, bearWalk2, bearWalk3, bearWalk4, bearWalk5,
       bearWalk6, bearWalk7, bearWalk8, bearWalk9, bearWalk10, bearWalk11,
       bearWalk12, bearWalk13, bearWalk14, bearWalk15, bearWalk16, bearWalk17,
       bearWalk18, bearWalk19, bearWalk20, bearWalk21, bearWalk22, bearWalk23,
       bearWalk24, bearWalk25, bearWalk26, bearWalk27, bearWalk28, bearWalk29,
       bearWalk30, bearWalk31, bearWalk32, bearWalk33, bearWalk34, bearWalk35,
       bearWalk36, bearWalk37, bearWalk38, bearWalk39, bearWalk40, bearWalk41,
       bearWalk42, bearWalk43, bearWalk44, bearWalk45;

 //Bear and heart image variables
PImage bear;
PImage heart;

//Bear class
class Bear {
  float posY;
  int bearSize;
  int health;
  float jumpFactor;
  int counter;
  boolean jumping;
  int jumpDuration;
  int bearCounter;

//definitions for various variables related to Bear
  //construcor
  Bear() {
    posY = 400;
    bearSize = 110;
    health = 3;
    counter = 0;
    jumpFactor = 1.6f;
    jumpDuration = 27;
    bearCounter = 0;
  }

  //METHODS
  //display function for Bear
  public void display() {
    image(bearType(bearCounter), 75, posY, bearSize, (bearSprite.height * bearSize)/bearSprite.width);
    bearCounter++;
    if (bearCounter > 44) {
      bearCounter = 0;
    }
  }

//display function for health hearts.
  public void displayHealth() {
    int heartSize = 40;
    for (int i = 0; i < health; i++) {
      image(heart, 180 + (i * (heartSize + 5)), 10, heartSize, (heart.height * heartSize)/heart.width);
    }
  }

//jump stuff thing
  public void setCounter(int newCounter) {
    counter = newCounter;
  }

  public boolean getJumping() {
    return jumping;
  }

  public void setJump(boolean newBool) {
    jumping = newBool;
  }

//death boolean
  public boolean dead() {
    if (health == 0) {
      return true;
    } else {
      return false;
    }
  }

//jumping function
  public void jump() {
    if (jumping) {
      //counter is frame count for jump duration
      if (counter < jumpDuration) {
        posY = 400 - ((jumpFactor * counter) + (.5f * -3.6f * sq(counter + -13)) + 300);
        counter++;
      } else if (counter >= jumpDuration) {
        jumping = false;
        posY = 400;
      }
    }
  }

//image cycling for walk animation
  public PImage bearType(int num) {
    switch (num) {
    case 0:
      bear = bearWalk0;
      break;
    case 1:
      bear = bearWalk1;
      break;
    case 2:
      bear = bearWalk2;
      break;
    case 3:
      bear = bearWalk3;
      break;
    case 4:
      bear = bearWalk4;
      break;
    case 5:
      bear = bearWalk5;
      break;
    case 6:
      bear = bearWalk6;
      break;
    case 7:
      bear = bearWalk7;
      break;
    case 8:
      bear = bearWalk8;
      break;
    case 9:
      bear = bearWalk9;
      break;
    case 10:
      bear = bearWalk10;
      break;
    case 11:
      bear = bearWalk11;
      break;
    case 12:
      bear = bearWalk12;
      break;
    case 13:
      bear = bearWalk13;
      break;
    case 14:
      bear = bearWalk14;
      break;
    case 15:
      bear = bearWalk15;
      break;
    case 16:
      bear = bearWalk16;
      break;
    case 17:
      bear = bearWalk17;
      break;
    case 18:
      bear = bearWalk18;
      break;
    case 19:
      bear = bearWalk19;
      break;
    case 20:
      bear = bearWalk20;
      break;
    case 21:
      bear = bearWalk21;
      break;
    case 22:
      bear = bearWalk22;
      break;
    case 23:
      bear = bearWalk23;
      break;
    case 24:
      bear = bearWalk24;
      break;
    case 25:
      bear = bearWalk25;
      break;
    case 26:
      bear = bearWalk26;
      break;
    case 27:
      bear = bearWalk27;
      break;
    case 28:
      bear = bearWalk28;
      break;
    case 29:
      bear = bearWalk29;
      break;
    case 30:
      bear = bearWalk30;
      break;
    case 31:
      bear = bearWalk31;
      break;
    case 32:
      bear = bearWalk32;
      break;
    case 33:
      bear = bearWalk33;
      break;
    case 34:
      bear = bearWalk34;
      break;
    case 35:
      bear = bearWalk35;
      break;
    case 36:
      bear = bearWalk36;
      break;
    case 37:
      bear = bearWalk37;
      break;
    case 38:
      bear = bearWalk38;
      break;
    case 39:
      bear = bearWalk39;
      break;
    case 40:
      bear = bearWalk40;
      break;
    case 41:
      bear = bearWalk41;
      break;
    case 42:
      bear = bearWalk42;
      break;
    case 43:
      bear = bearWalk43;
      break;
    case 44:
      bear = bearWalk44;
      break;
    }
    return bear;
  }
}

public void loadBear() {
  bearWalk0 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0000.png");
  bearWalk1 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0001.png");
  bearWalk2 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0002.png");
  bearWalk3 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0003.png");
  bearWalk4 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0004.png");
  bearWalk5 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0005.png");
  bearWalk6 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0006.png");
  bearWalk7 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0007.png");
  bearWalk8 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0008.png");
  bearWalk9 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0009.png");
  bearWalk10 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0010.png");
  bearWalk11 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0011.png");
  bearWalk12 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0012.png");
  bearWalk13 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0013.png");
  bearWalk14 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0014.png");
  bearWalk15 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0015.png");
  bearWalk16 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0016.png");
  bearWalk17 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0017.png");
  bearWalk18 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0018.png");
  bearWalk19 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0019.png");
  bearWalk20 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0020.png");
  bearWalk21 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0021.png");
  bearWalk22 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0022.png");
  bearWalk23 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0023.png");
  bearWalk24 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0024.png");
  bearWalk25 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0025.png");
  bearWalk26 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0026.png");
  bearWalk27 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0027.png");
  bearWalk28 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0028.png");
  bearWalk29 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0029.png");
  bearWalk30 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0030.png");
  bearWalk31 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0031.png");
  bearWalk32 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0032.png");
  bearWalk33 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0033.png");
  bearWalk34 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0034.png");
  bearWalk35 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0035.png");
  bearWalk36 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0036.png");
  bearWalk37 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0037.png");
  bearWalk38 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0038.png");
  bearWalk39 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0039.png");
  bearWalk40 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0040.png");
  bearWalk41 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0041.png");
  bearWalk42 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0042.png");
  bearWalk43 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0043.png");
  bearWalk44 = loadImage("Graphics/Bear/Exports/PNG Export/Bear Animation.0044.png");
}
//hi
/*
Team-turtle-hat
 Cho, David, Giles
 March 2017
 Controls the displaying of buildings and building stuff
 */
AudioPlayer boom1;
AudioPlayer boom2;
boolean soundEffects = true;
int diff = 3;

class Buildings extends Sprites {
  //is able to control the size of the buildings proportionally
  int buildingSize;
  int smokeSize;
  float posY;

  //stores width and height of building for detection purposes
  int boundryHeight;
  int boundryWidth;
  //is able to change all of the buildings y value
  int changeBuildingY = 7;
  boolean once = false;
  int alpha;

  //uses construcor of the sprites class
  Buildings(float posX, int typeOfSprite) {
    super(posX, typeOfSprite);
    buildingSize = 140;
    smokeSize = 140;
    alpha = 255;
  }

  public void drawBuilding(PImage building, int newPosY) {
    posY = newPosY;
    buildingSize = 140;
    image(building, posX, posY, buildingSize, (building.height * buildingSize)/building.width);
    //defines boundries of the building for detection purposes
    boundryHeight = PApplet.parseInt((building.height * buildingSize)/building.width);
    boundryWidth = buildingSize;
  }
  public void drawBuildingEnlarged(PImage building, int newPosY) {
    posY = newPosY;
    buildingSize = 210;
    image(building, posX, posY, buildingSize, (building.height * buildingSize)/building.width);
    //defines boundries of the building for detection purposes
    boundryHeight = PApplet.parseInt((building.height * buildingSize)/building.width);
    boundryWidth = buildingSize;
  }

  //displays a building based on typeOfSprite
  public void display() {
    if (smokeSize < 400) {
      switch(typeOfSprite) {
      case 1:
        //displays the first building type.
        drawBuilding(building1, 347 + changeBuildingY);
        break;
      case 2:
        drawBuilding(building2, 304 + changeBuildingY);
        break;
      case 3:
        drawBuildingEnlarged(building3, 276 + changeBuildingY);
        break;
      case 4:
        drawBuildingEnlarged(building4, 231 + changeBuildingY);
        break;
      case 5:
        drawBuilding(building5, 304 + changeBuildingY);
        break;
      case 6:
        drawBuilding(building6, 263 + changeBuildingY);
        break;
      }
    }
  }

  public void detection() {
    //tests for detection at the last possible moment to reduce load
    if (posX < 185 && keyCode == 16) {
      //loops through y values of the building
      for (int i = PApplet.parseInt(posY); i < (posY + boundryHeight); i += 3) {
        //tests for detection along the left side of the building
        if (posX > 75 && i < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && i > player.posY) {
          destroyedStatus = true;
        }
      }
      //loops through x values of building
      for (int i = PApplet.parseInt(posX); i < posX + boundryWidth; i += 3) {
        //tests for detection along the top of the building
        if (posY < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && posY > player.posY && i > 75 && i < 185) {
          destroyedStatus = true;
        }
      }
    }
  }

  public void smoke() {
    if (destroyed()) {
      smoke.disableStyle();
      shapeMode(CENTER);
      fill(189, 189, 189, alpha);
      shape(smoke, posX + boundryWidth/2, posY + boundryHeight/2, smokeSize, (smoke.height * smokeSize)/smoke.width);
      if (smokeSize < 500) {
        smokeSize += 70;
      }
      if (smokeSize > 350) {
        alpha -= 40;
      }
      shapeMode(CORNER);
    }
  }

  public void addScore() {
    //only adds score if the building has been destroyed
    if (destroyed() && !once) {
      playGame.score += 10;
      //SOUNDS
      int randomSound = PApplet.parseInt(random(2));
      if (randomSound == 0 && soundEffects) {
        boom1.loop(0);
      } else if (soundEffects) {
        boom2.loop(0);
      }
      once = true;
      if (playGame.score % diff == 0) {
        playGame.setGameSpeed(playGame.gameSpeed + 1);
        println("cho sucks");
      }
    }
  }
}
/*
Team-turtle-hat
<<<<<<< HEAD
Giles, David, Cho
March 2017
Displays the game over screen and give the player the option of playing again.
It also displays the score.
*/

 //GameOver functions: dictates death screen looks and functions.
class GameOver {
  int randomMsg = PApplet.parseInt(random(6));
  String deathMsg;
  int deathSelect = 0;

  GameOver() {
  }

  //gives a random bear pun
  public void deathMsg() {
    switch(randomMsg) {
    case 0:
      deathMsg = "You're unBEARable";
      break;
    case 1:
      deathMsg = "You're worse than a barBEARian";
      break;
    case 2:
      deathMsg = "It is time for you to be BEARied";
      break;
    case 3:
      deathMsg = "You should be emBEARessed";
      break;
    case 4:
      deathMsg = "You're a BEARicade of progress";
      break;
    case 5:
      deathMsg = "Have you lost your BEARings?";
      break;
    }

     textAlign(CENTER);
     textSize(60);
     fill(255, 0, 0);
     text("GAME OVER", width/2, height/2 - height/6 - 40);
     fill(255);
     textSize(40);
     text(deathMsg, width/2, height/2 - 40);
     text("Score" + " " + playGame.getScore(), width/2, height/2 + height/6 - 50);
     text("Highscore" + " " + highscores.getHighscore(), width/2, height/2 + height/6);

   }

//Restart/Main Menu cases for Game Over.
  public void deathMenu(int num) {
    switch (num) {
      case 0:
        textSize(40);
        text("RESTART", width/2, height - 100);
        textSize(30);
        text("MAIN MENU", width/2, height - 50);
        break;
      case 1:
        textSize(30);
        text("RESTART", width/2, height - 100);
        textSize(40);
        text("MAIN MENU", width/2, height - 50);
        break;
    }
  }

//Game Over display function
  public void display() {
    background(0);
    deathMsg();
    highscores.saveHighscore();
    deathMenu(deathSelect);
  }
}

class Highscore {
  //Fields
  String[] rawHighscore = {};
  String[] saveScore = {" "};
  int[] highscores = {};
  int highscore;

  //Methods
  //function to write onto a text file
  public void saveHighscore() {
    highscore = highscores[0];
    rawHighscore = loadStrings("Highscore.txt");
    highscores = PApplet.parseInt(rawHighscore);
    for(int i = highscores.length -1; i >= 0; i--){
      highscore += (10^i * highscores[i])/10;
    }

    if(playGame.getScore() > highscore) {
      String[] scoreSaved = {str(playGame.getScore())};
      //saveScore[0] = str(playGame.getScore());
      saveStrings("Highscore.txt", scoreSaved);
      println(scoreSaved);
    }
  }

  //function to read from text file
  public int getHighscore() {
    highscores = PApplet.parseInt(loadStrings("Highscore.txt"));
    highscore = highscores[0];
    return highscore;
  }
}
class HowTo{

  HowTo() {
  }

//draw Sky background
  public void drawSky() {
    image(sky, 0, 0);
  }

//draw How To Play center title
  public void drawTitle() {
    fill(255);
    textAlign(CENTER);
    textFont(robotoCondensed);
    textSize(50);
    text("HOW TO PLAY", width/2, 150);
    stroke(255);
    strokeWeight(5);
    noFill();
    rectMode(CENTER);
    rect(width/2, 130, 500, 100);
  }

//returns to menu
  public void backToMenu() {
    textSize(40);
    text("Press Enter to Return to Main Menu", width/2, height - 70);
  }

////displays instruction text for How To Menu
  public void howTo() {
    textSize(30);
    text("Use arrow keys to navigate menus and enter to select the desired option", width/2, 300);
    text("Press space while playing to jump over traps and shift to destroy buildings", width/2, 350);
    text("Press p while playing to pause the game", width/2, 400);
  }

//How To display function
  public void display() {
    drawSky();
    drawTitle();
    backToMenu();
    howTo();
  }
}
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
    scaleFactor = 1.5f;
  }

  //Methods
  public void drawSky() {
    image(sky, 0, 0);
  }

//Draws Title at top center
  public void drawTitle() {
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
  public void display() {
    drawSky();
    drawTitle();
    menuSelection();
  }

//Main Menu selection cases
  public void menuSelection() {
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
/*
Team-turtle-hat
 Giles, David, Cho
 March 2017
 Display and controls the options page of the game
 */


class Options {
  //Fields
  int selectMenu;
  int diffNum;
  int soundNum;
  String difficulty;
  String sound;

  //Constructor
  Options() {
    diffNum = 1;
    soundNum = 0;
    selectMenu = 0;
    difficulty = "NORMAL";
    sound = "ON";
  }

  //Methods
  public void drawBackground() {
    image(sky, 0, 0);
  }

//draws title at center
  public void drawTitle() {
    rectMode(CENTER);
    stroke(255);
    fill(255, 255, 255, 0);
    rect(width/2, 130, 500, 100);
    fill(255);
    textAlign(CENTER);
    textSize(50);
    text("OPTIONS", width/2, 150);
    textSize(40);

    text("Press Enter to Return to Main Menu", width/2, height - height/6);
  }

//Options menu selection cases
  public void menuSelection() {
    textAlign(CENTER);
    // menu selection
    switch(selectMenu) {
    case 0:
      // DIFFICULTY
      textSize(40);
      text("DIFFICULTY: " + difficulty, width/2, 300);
      textSize(30);
      text("SOUND: " + sound, width/2, 350);
      break;
    case 1:
      //SOUND
      textSize(30);
      text("DIFFICULTY: " + difficulty, width/2, 300);
      textSize(40);
      text("SOUND: " + sound, width/2, 350);
      break;
    }
  }

//Difficulty cases and controls settings related to difficulty
  public void difficultyChange(int i) {
    switch(i) {
    case 0:
      difficulty = "EASY";
      diff = 8;
      playGame.genDiff = -1;
      playGame.genTime = 2000;
      break;
    case 1:
      difficulty = "NORMAL";
      diff = 3;
      playGame.genDiff = 0;
      playGame.genTime = 1500;
      break;
    case 2:
      difficulty = "HARD";
      diff = 4;
      playGame.genDiff = 1;
      playGame.genTime = 1100;
      break;
    }
  }

//Sound cases
  public void soundChange(int i) {
    switch(i) {
    case 0:
      sound = "ON";
      soundEffects = true;
      backgroundMusic.loop();
      break;
    case 1:
      sound = "OFF";
      soundEffects = false;
      backgroundMusic.pause();
      break;
    }
  }

//Options display functions
  public void display() {
    difficultyChange(diffNum);
    soundChange(soundNum);
    drawBackground();
    drawTitle();
    menuSelection();
  }
}
/*
Team-turtle-hat
 Giles, David, Cho
 March 2017
 Displays the pause screen and allows the player to unpause
 */
 //Variables
PImage pauseImage;
boolean pauseOnce = false;

//PauseMenu cases
public void pauseMenu(int num) {
switch (num) {
  case 0:
    textSize(40);
    text("RESUME", width/2, (height/2) + 100);
    textSize(30);
    text("MAIN MENU", width/2, (height/2)+ 150);
    break;
  case 1:
    textSize(30);
    text("RESUME", width/2, (height/2)+ 100);
    textSize(40);
    text("MAIN MENU", width/2, (height/2)+ 150);
    break;
  }
}

//pause function
public void paused() {
  if(!pauseOnce) {
    pauseImage = get();
    pauseOnce = true;
  }
  image(pauseImage, 0, 0);
  textAlign(CENTER);
  textSize(80);
  fill(0);
  text("PAUSED", width/2, height/2);
  pauseMenu(pauseSelect);
}
/*
Team-turtle-hat
 David, Cho, Giles
 March 2017
 Controls the actual gameplay of the game
 */

boolean playerJump = false;
float randomSprite;

ArrayList<Sprites> sprites = new ArrayList<Sprites>();
ArrayList<Float> grassList = new ArrayList<Float>();
ArrayList<Integer> mountainsBack = new ArrayList<Integer>();
ArrayList<Integer> mountainsFront = new ArrayList<Integer>();
ArrayList<Integer> clouds = new ArrayList<Integer>();
ArrayList<Integer> cloudsY = new ArrayList<Integer>();
ArrayList<Integer> cloudsType = new ArrayList<Integer>();
ArrayList<Integer> cloudsSlow = new ArrayList<Integer>();
ArrayList<Integer> cloudsSlowY = new ArrayList<Integer>();
ArrayList<Integer> cloudsSlowType = new ArrayList<Integer>();
//stores time;
int time = 0;

class PlayGame {
  //Fields
  float randomSprite;
  int time;
  int score;
  float gameSpeed;
  int grassWidth = 50;
  int mtsWidth = width;
  int genDiff = 0;
  int genTime = 1500;
  PShape shape;
  int counterNight = 0;
  int alpha2 = 0;
  int nightTime = 0;
  boolean nightSwitch = false;
  int adder = 1;

  //Constructor
  PlayGame() {
    playerJump = false;
    time = 0;
    score = 0;
    gameSpeed = 15;
  }

  //Methods

  //Function to retrieve score value
  public int getScore() {
    return score;
  }

  //Function to randomely determine when a tree is going to be placed
  public void generateSprites() {
    randomSprite = random(30, 50);
    //is going to determine if a sprite should be added. Then it will decide either building or trap.
    if (randomSprite < 45 && randomSprite > 40 && millis() - time > genTime) {
      if (randomSprite > 42.5f + genDiff) {
        //add buliding to arraylist
        sprites.add(new Buildings(width, PApplet.parseInt(random(7))));
        time = millis();
      } else if (randomSprite < 42.5f + genDiff) {
        //adds trap to arraylist
        sprites.add(new Traps(width, 1));
        time = millis();
      }
    }
  }

  //used to add a specific number of ints/floats to the arrays.
  //run in setup
  public void addSprites() {
    for (int i = 0; i <= width + grassWidth *2; i += grassWidth) {
      grassList.add(new Float(i));
    }
    for (int i = 0; i <= width; i += width) {
      mountainsBack.add(new Integer(i));
      mountainsFront.add(new Integer(i));
    }
    for (int i = 0; i <= width; i += 300) {
      clouds.add(new Integer(i));
      cloudsSlow.add(new Integer(i));
    }
    //stores y values for the clouds
    for (int i = 0; i < 10; i ++) {
      cloudsY.add(new Integer(PApplet.parseInt(random(40, 300))));
      cloudsSlowY.add(new Integer(PApplet.parseInt(random(40, 300))));
      cloudsType.add(new Integer(PApplet.parseInt(random(6))));
      cloudsSlowType.add(new Integer(PApplet.parseInt(random(6, 9))));
    }
  }

  //takes in a num from 0-9 and returns a cloud
  public PShape cloudType(int num) {
    switch (num) {
    case 0:
      shape = cloud1;
      break;
    case 1:
      shape = cloud2;
      break;
    case 2:
      shape = cloud3;
      break;
    case 3:
      shape = cloud4;
      break;
    case 4:
      shape = cloud5;
      break;
    case 5:
      shape = cloud6;
      break;
    case 6:
      shape = cloud7;
      break;
    case 7:
      shape = cloud8;
      break;
    case 8:
      shape = cloud9;
      break;
    }
    return shape;
  }

  //displays the clouds
  public void drawClouds() {
    //loops through all the clouds in the array
    for (int i = 0; i < clouds.size(); i++) {
      //moves the clouds left by two pixels
      clouds.set(i, clouds.get(i) - 2);
      //draws the clouds
      shape(cloudType(cloudsType.get(i)), clouds.get(i), cloudsY.get(i));
      //resets cloud once it goes off the scree
      if (clouds.get(i) < -400) {
        clouds.set(i, width);
      }
    }
    //loops through all the slow clouds
    for (int i = 0; i < cloudsSlow.size(); i++) {
      //moves the cloud left by one pixel
      cloudsSlow.set(i, cloudsSlow.get(i) - 1);
      //draws the clouds
      shape(cloudType(cloudsSlowType.get(i)), cloudsSlow.get(i), cloudsSlowY.get(i));
      //resets the clouds once they go offscreen
      if (cloudsSlow.get(i) < - 400) {
        cloudsSlow.set(i, width);
      }
    }
  }

  public void drawGrass() {
    //loops through all the grass in the array
    for (int i = 0; i < grassList.size(); i++) {
      //moves the grass left by a specific num
      grassList.set(i, grassList.get(i) - gameSpeed);
      //draws the grass
      shape(grass, grassList.get(i), 570, grassWidth, (grass.height * grassWidth)/grass.width);
      //resets the grass once it goes off screen
      if (grassList.get(i) < -grassWidth) {
        grassList.set(i, grassList.get(i) + width + grassWidth * 3);
      }
    }
  }

  public void drawMountains() {
    //loops through all the back mountains
    for (int i = 0; i < mountainsBack.size(); i++) {
      //moves the background mountains left by one pixel
      mountainsBack.set(i, mountainsBack.get(i) - 1);
      //draws the mountains
      shape(mtsBack, mountainsBack.get(i), 165, width, (mtsBack.height * width)/mtsBack.width);
      //resets mountains once it goes offscreen
      if (mountainsBack.get(i) < 2 - width) {
        mountainsBack.set(i, width);
      }
    }
    //loops through front mountains
    for (int i = 0; i < mountainsFront.size(); i++) {
      //moves front mountains by two pixels
      mountainsFront.set(i, mountainsFront.get(i) - 2);
      //draws mountains
      shape(mtsFront, mountainsFront.get(i), 170, width, (mtsFront.height * width)/mtsFront.width);
      //resets mountains once they go offscreen
      if (mountainsFront.get(i) < 2 - width) {
        mountainsFront.set(i, width);
      }
    }
  }

  //setting game speed from outside the class
  public void setGameSpeed(float newSpeed) {
    gameSpeed = newSpeed;
  }

  //returns gameSpeed
  public float getGameSpeed() {
    return gameSpeed;
  }

  public void displayScore() {
    textSize(30);
    fill(255);
    //displays the score and player health in the top left corner
    text("Score:" + " " + score, 40, 40);
    player.displayHealth();
  }

  public void checkAlive() {
    //if the player is dead it activates the game over screen
    if (player.dead()) {
      gameState = "GAME OVER";
    }
  }

  //removes object from ArrayList if it off the screen.
  public void clearSprite(int i) {
    //if the sprite has been destroyed or is off screen it is deleted from the array
    if (sprites.get(i).getX() < -500) {
      sprites.remove(i);
    }
  }

  public void process() {
    //loops through all objects in ArrayList
    for (int i = sprites.size() -1; i >= 0; i--) {
      //moves sprite from right to left
      sprites.get(i).move(getGameSpeed());
      //displays sprite
      sprites.get(i).display();

      //tests for detection of the sprite
      sprites.get(i).detection();
      //subtracs health from the player when it hits a trap
      sprites.get(i).subtractHealth();
      //checks to see if the player is still alive
      checkAlive();
      sprites.get(i).smoke();
      //adds score if a building is destoryed
      sprites.get(i).addScore();
      //removes a sprite if it is destroyed or goes off screen
      clearSprite(i);
    }
    //displays player
    player.jump();
    //displays the bear
    player.display();
  }

  // draw Sky
  public void drawSky() {
    image(sky, 0, 0);
  }

  // void night() {
  //   fill(0, 0, 0, alpha2);
  //   rectMode(CORNER);
  //   rect(0, 0, width, height);
  //
  //   if (millis() - nightTime > 45000) {
  //     nightSwitch = true;
  //   }
  //   if (nightSwitch) {
  //     if (counterNight % 3 == 0) {
  //       alpha2 += adder;
  //     }
  //     counterNight++;
  //     if(counterNight > 100) {
  //      counterNight = 0;
  //     }
  //     if (alpha2 > 180) {
  //       nightSwitch = false;
  //       adder *= -1;
  //       nightTime = millis();
  //     }
  //   }
  // }

  public void display() {
    //draw sky
    drawSky();
    //draws mts
    drawMountains();
    //draw Clouds
    drawClouds();
    //generate sprites
    generateSprites();
    //moves and displays
    process();
    //draws grass
    drawGrass();
    //displays score;
    displayScore();
  }
}
/*
Team-turtle-hat
 Cho, David, Giles
 March 2017
 Class that both buildings and traps inherit.
 */

//Building sprites
PImage building1, building2, building3, building4, building5, building6;
PShape bearTrap, bearTrapActivated;
PShape grass;
PShape mtsBack;
PShape mtsFront;
PShape cloud1, cloud2, cloud3, cloud4, cloud5, cloud6, cloud7, cloud8, cloud9;
PShape smoke;

//used to load building and trap sprites
public void loadSprites() {
  building1 = loadImage("Graphics/Buildings/Buildings_Artboard 1.png");
  building2 = loadImage("Graphics/Buildings/Buildings_Artboard 2.png");
  building3 = loadImage("Graphics/Buildings/Buildings_Artboard 3.png");
  building4 = loadImage("Graphics/Buildings/Buildings_Artboard 4.png");
  building5 = loadImage("Graphics/Buildings/Buildings_Artboard 5.png");
  building6 = loadImage("Graphics/Buildings/Buildings_Artboard 6.png");
  bearTrap = loadShape("Graphics/Traps/BearTrap.svg");
  bearTrapActivated = loadShape("Graphics/Traps/BearTrapActivated.svg");
  grass = loadShape("Graphics/Environment/Grass/Grass.svg");
  mtsFront = loadShape("Graphics/Environment/Mountains/Mountains Front.svg");
  mtsBack = loadShape("Graphics/Environment/Mountains/Mountains Back.svg");
  cloud1 = loadShape("Graphics/Environment/Sky/Clouds Master-01.svg");
  cloud2 = loadShape("Graphics/Environment/Sky/Clouds Master-02.svg");
  cloud3 = loadShape("Graphics/Environment/Sky/Clouds Master-03.svg");
  cloud4 = loadShape("Graphics/Environment/Sky/Clouds Master-04.svg");
  cloud5 = loadShape("Graphics/Environment/Sky/Clouds Master-05.svg");
  cloud6 = loadShape("Graphics/Environment/Sky/Clouds Master-06.svg");
  cloud7 = loadShape("Graphics/Environment/Sky/Clouds Master-07.svg");
  cloud8 = loadShape("Graphics/Environment/Sky/Clouds Master-08.svg");
  cloud9 = loadShape("Graphics/Environment/Sky/Clouds Master-09.svg");
  smoke = loadShape ("Graphics/Destruction/drawing.svg");
  heart = loadImage("Graphics/Health/heart.png");
}
//parent class to buildings and traps
class Sprites {
  float posX;
  float posY;
  // int boundryWidth;
  // int boudnryHeight;
  boolean destroyedStatus;
  boolean activatedStatus;
  //determines which type of builing/trap will be displayed.
  int typeOfSprite;

  Sprites(float posX, int typeOfSprite) {
    this.posX = posX;
    this.typeOfSprite = typeOfSprite;
  }

  //Methods

  //returns posX
  public float getX() {
    return posX;
  }

  //returns posY
  public float getY() {
    return posX;
  }

  //used to determine if a building should be destroyed
  public boolean destroyed() {
    return destroyedStatus;
  }

  //used to determine if a trap has been activated
  public boolean activated() {
    return activatedStatus;
  }

  //moves sprites from right to left, with input of game speed factor
  public void move(float gameSpeed) {
    posX -= gameSpeed;
  }

  public void display() {
  }

  public void detection() {
  }

  public void subtractHealth() {
  }

  public void smoke() {
  }

  public void addScore() {
  }
}
class Stats {

  Stats() {
  }

  public void display() {
    image(sky, 0, 0, width, height);

    //title
    rectMode(CENTER);
    stroke(255);
    fill(255, 255, 255, 0);
    rect(width/2, 130, 500, 100);
    fill(255);
    textAlign(CENTER);
    textSize(50);
    text("STATS", width/2, 150);

    fill(255);
    textAlign(CENTER);
    textSize(45);
    text("Highscore:" + " " + highscores.getHighscore(), width/2, height/2);
    text("Press Enter To Return to Main Menu", width/2, height - height/6);
  }
}
/*
Team-turtle-hat
 Cho, David, Giles
 March 2017
 Controls the displaying of traps including villagers and trap stuff
 */

//Trap noise
AudioPlayer bearTrapSound;

class Traps extends Sprites {
  //stores the width and heigt of the trap for detection purposes
  int boundryHeight;
  int boundryWidth;
  boolean once;
  boolean playOnce;

  //uses constructor of the sprites class
  Traps(int posX, int typeOfSprite) {
    super(posX, typeOfSprite);
    once = true;
  }


  public void display() {
    //typeOfSprite determines which building it will display
    switch(typeOfSprite) {
      case 1:
        //load image andd set posY;
        int trapSize = 100;
        posY = 520;

        //if not activated it displays the flat bear trap
        if (!activatedStatus) {
          shape(bearTrap, posX, posY, trapSize, (bearTrap.height * trapSize)/bearTrap.width);
        } else {
          //if not activated it displays the activated bear trap
          shape(bearTrapActivated, posX, posY, trapSize, (bearTrap.height * trapSize)/bearTrap.width);
        }

        //defines boundrys for detection
        boundryHeight = PApplet.parseInt((bearTrap.height * trapSize)/bearTrap.width);
        boundryWidth = trapSize;
        break;
    }
  }

  public void subtractHealth() {
    if (activated()) {
      //once makes it so it only subtracts from the player health once
      if (once) {
        player.health--;
        once = false;
      }
    }
  }

  public void detection() {
    //if a trap is less than 185 it begins to test for detection
    if (posX < 185) {
      //loops through the y values of the trap
      for (int i = PApplet.parseInt(posY); i <= (posY + boundryHeight); i += 3) {
        //tests for detection along the left side of the trap
        if (posX > 75 && i < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && i > player.posY) {
          activatedStatus = true;
          if (!playOnce && soundEffects) {
            //plays bear trap sound once
            bearTrapSound.loop(0);
            playOnce = true;
          }
        }
      }
      //loops through x values of trap
      for (int i = PApplet.parseInt(getX()); i < posX + boundryWidth; i += 3) {
        //tests for detection along the top of the trap
        if (posY < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && posY > player.posY && i > 75 && i < 185) {
          activatedStatus = true;
          if (!playOnce && soundEffects) {
            //plays bear trap sound once
            bearTrapSound.loop(0);
            playOnce = true;
          }
        }
      }
    }
  }
}
  public void settings() {  size(1100, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Destroy_them_all" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
