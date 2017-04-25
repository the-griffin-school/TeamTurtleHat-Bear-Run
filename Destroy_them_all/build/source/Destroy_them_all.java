import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

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


public void setup() {
  
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);

  sky = loadImage("Graphics/Environment/Sky/SkyImage.png");
  robotoCondensed = loadFont("Fonts/RobotoCondensed-Bold-50.vlw");
  bearSprite = loadShape("Graphics/Bear/Bear.svg");
  loadSprites();
  playGame.addGrass();

}

public void draw() {
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

public void keyPressed() {
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
/*
Team-turtle-hat
David, Cho, Giles
March 2017
Class that controls the bear and bear stuff
*/

class Bear {
  int posY = 400;
  //is used to control size of the bear
  int bearSize = 110;
  int health = 3;

  Bear() {

  }

  public void display() {
    shape(bearSprite, 75, posY, bearSize, (bearSprite.height * bearSize)/bearSprite.width);
  }


  public boolean dead() {
    if(health == 0) {
      return true;
    } else {
      return false;
    }
  }

  public void jump() {

  }
}
/*
Team-turtle-hat
Cho, David, Giles
March 2017
Controls the displaying of buildings and building stuff
*/
class Buildings extends Sprites {
  //is able to control the size of the building proportionally
  int buildingSize;
  float posY;
  int boundryHeight;
  int boundryWidth;

  //uses construcor of the sprites class
  Buildings(float posX, int typeOfSprite) {
    super(posX, typeOfSprite);
    buildingSize = 140;
  }

  //displays a building based on typeOfSprite
  public void display() {
    switch(typeOfSprite){
      case 1:
        //displays the first building type.
        posY = 350;
        shape(building1, posX, posY, buildingSize, (building1.height * buildingSize)/building1.width);
        //defines boundries of the building for detection purposes
        boundryHeight = PApplet.parseInt((building1.height * buildingSize)/building1.width);
        boundryWidth = buildingSize;
        break;
      case 2:
        posY = 307;
        shape(building2, posX, posY, buildingSize, (building2.height * buildingSize)/building2.width);
        //defines boundries of the building for detection purposes
        boundryHeight = PApplet.parseInt((building2.height * buildingSize)/building2.width);
        boundryWidth = buildingSize;
        break;
      case 3:
        posY = 383;
        shape(building3, posX, posY, buildingSize, (building3.height * buildingSize)/building3.width);
        //defines boundries of the building for detection purposes
        boundryHeight = PApplet.parseInt((building3.height * buildingSize)/building3.width);
        boundryWidth = buildingSize;
        break;
      case 4:
        posY = 354;
        shape(building4, posX, posY, buildingSize, (building4.height * buildingSize)/building4.width);
        //defines boundries of the building for detection purposes
        boundryHeight = PApplet.parseInt((building4.height * buildingSize)/building4.width);
        boundryWidth = buildingSize;
        break;
      case 5:
        posY = 307;
        shape(building5, posX, posY, buildingSize, (building5.height * buildingSize)/building5.width);
        //defines boundries of the building for detection purposes
        boundryHeight = PApplet.parseInt((building5.height * buildingSize)/building5.width);
        boundryWidth = buildingSize;
        break;
      case 6:
        posY = 266;
        shape(building6, posX, posY, buildingSize, (building6.height * buildingSize)/building6.width);
        //defines boundries of the building for detection purposes
        boundryHeight = PApplet.parseInt((building6.height * buildingSize)/building6.width);
        boundryWidth = buildingSize;
        break;
    }
  }

  public void detection() {
    //tests for detection at the last possible moment to reduce load
    if(posX < 185) {
      //loops through y values of the building
      for (int i = PApplet.parseInt(posY); i < (posY + boundryHeight); i += 3) {
        //tests for detection along the left side of the building
        if(posX > 75 && i < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && i > player.posY) {
          destroyedStatus = true;
        }
      }
      //loops through x values of building
      for (int i = PApplet.parseInt(posX); i < posX + boundryWidth; i += 3) {
        //tests for detection along the top of the building
        if(posY < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && posY > player.posY && i > 75 && i < 185) {
          destroyedStatus = true;
        }
      }
    }
  }
}
/*
Team-turtle-hat
Giles, David, Cho
March 2017
Displays the game over screen and give the player the option of playing again.
It also displays the score.
*/
class GameOver {
  int randomMsg = PApplet.parseInt(random(5));
  String deathMsg;

  GameOver() {
  }

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
     }

     textAlign(CENTER);
     textSize(60);
     fill(255, 0, 0);
     text("GAME OVER", width/2, height/2 - height/6);
     fill(255);
     textSize(40);
     text(deathMsg, width/2, height/2);
     text("Score" + " " + playGame.score, width/2, height/2 + height/6);
   }

   public void buttonDetection() {
      // stroke(255);
      // fill(0);
      // rectMode(CORNER);
      // rect(width/2 - width/3, height/2 + height/4, width/3 - width/12, height/6);
      // rect(width/2 + (width/2 -width/3 - width/12), height/2 + height/4, width/3 - width/12, height/6);
      // rectMode(CENTER);
      // fill(255);
      // text("Main Menu", (width/2 - width/3) + (width/3 - width/12)/2, (height/2 + height/4) + height/9);
   }

   public void display() {
    background(0);
    deathMsg();
    buttonDetection();
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
  int selectMenu;
  float scaleFactor;

  //Constructor
  MainMenu() {
    startGame = false;
    selectMenu = 0;
    scaleFactor = 1.5f;
  }

  //Methods
  public void drawSky() {
    image(sky, 0, 0);
  }
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


  public void display() {
    drawSky();
    drawTitle();
    drawTitle();
    menuSelection();
  }

  public void menuSelection(){
    // menu selection
    switch(selectMenu) {
      case 0:
        textSize(40);
        text("Play", width/2, 300);
        textSize(30);
        text("Stats", width/2, 350);
        text("Options", width/2, 400);
        break;
      case 1:
        textSize(40);
        text("Stats", width/2, 350);
        textSize(30);
        text("Play", width/2, 300);
        text("Options", width/2, 400);
        break;
      case 2:
        textSize(40);
        text("Options", width/2, 400);
        textSize(30);
        text("Play", width/2, 300);
        text("Stats", width/2, 350);
        break;
    }
    //if user pressed ENTER
    if(startGame) {
      gameState = "GAME START";
    }
  }
}
/*
Team-turtle-hat
Giles, David, Cho
March 2017
Display and controls the options page of the game
*/


/* class Button {
  int RectX;
  int RectY;
  int RectW;
  int RectH;
  String text;

  Button (int RectX, int RectY, int RectW, int RectH, String text) {
    this. RectX = RectX;
    this. RectY = RectY;
    this. RectW = RectW;
    this. RectH = RectH;
    this. text = text;
  }

  boolean mouseOver() {
    if (mouseX >= RectX && mouseX <= RectX + RectW && mouseY >= RectY && mouseY <= RectY + RectH) {
      return true;
    } else {
      return false;
    }
  }

  void display() {
   fill(255);
    rect(RectX, RectY, RectW, RectH);
    fill(0);
   text (text, (RectX + (RectW/3)), (RectY + (RectH/3)));
   textSize(75);
  }

} */

int selectOptions;

public void optionsMenuBackground() {
  background(0);
  //draw sky
  background(0xff00e4ff);

  fill(255);
  textAlign(CENTER);
  textFont(robotoCondensed);
  textSize(50);
  text("Options", width/2, 150);
  stroke(255);
  strokeWeight(5);
  noFill();

  textAlign(RIGHT);
  text("DIFFICULTY", width/3, 200);
  textAlign(LEFT);
  text("OPTIONS", width/3, 200);


  switch (selectOptions) {
    case 0:
      textAlign(LEFT);
      textSize(30);
      text("EASY", width/3, 250);
      textSize(20);
      text("MEDIUM", width/3, 300);
      text("HARD", width/3, 350);
      textAlign(RIGHT);
      break;
    }
}
/*
Team-turtle-hat
Giles, David, Cho
March 2017
Displays the pause screen and allows the player to unpause
*/
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
float treesX = 0;
float trees2X = 800;
//stores time;
int time = 0;
int grassPosX = 0;


class PlayGame {
  //Fields
  boolean playerJump;
  float randomSprite;
  ArrayList<Sprites> sprites = new ArrayList<Sprites>();
  //ArrayList<float> grass = new ArrayList<float>();
  float treesX;
  float trees2X;
  int time;
  int score;
  float gameSpeed;
  int grassWidth = 50;

  //Constructor
  PlayGame() {
    playerJump = false;
    treesX = 0;
    trees2X = 800;
    time = 0;
    score = 0;
    gameSpeed = 5;
  }

  //Methods

  //Function to randomely determine when a tree is going to be placed
  public void generateSprites() {
    randomSprite = random(35, 50);
    //is going to determine if a sprite should be added. Then it will decide either building or trap.
    if(randomSprite < 45 && randomSprite > 40 && millis() - time > 500) {
      if(randomSprite > 42.5f) {
        //add buliding to arraylist
        sprites.add(new Buildings(width, PApplet.parseInt(random(7))));
        time = millis();
      } else if (randomSprite < 41) {
        //adds trap to arraylist
        sprites.add(new Traps(width, 1));
        time = millis();
      }
    }
  }

  public void addGrass() {
    for (int i = 0; i <= width; i += grassWidth) {
      grassList.add(new Float(i));
    }
  }

  public void drawGrass() {
    for(int i = 0; i < grassList.size(); i++) {
      grassList.set(i, grassList.get(i) - 10);
      shape(grass, grassList.get(i), 570, grassWidth, (grass.height * grassWidth)/grass.width);
      if(grassList.get(i) < 2 - grassWidth) {
        grassList.set(i, PApplet.parseFloat(width));
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

  public void addScore() {
    score += 10;
  }

  public void displayScore() {
    text("Score:" + " " + score, 60, 30);
  }

  public void checkAlive() {
    if(player.dead()) {
      //gameState = "GAME OVER";
    }
  }

  //removes object from ArrayList if it off the screen.
  public void clearSprite(int i) {
    if(sprites.get(i).getX() < -500 || sprites.get(i).destroyed()) {
      addScore();
      sprites.remove(i);
    }
  }

  public void process() {
    //loops through all objects in ArrayList
    for(int i = 0; i < sprites.size(); i++) {
      //moves sprite from right to left
      sprites.get(i).move(getGameSpeed());
      //displays sprite
      sprites.get(i).display();
      sprites.get(i).detection();
      sprites.get(i).subtractHealth();
      checkAlive();
      clearSprite(i);
    }
    //displays player
    player.display();
    if(playerJump) {
      player.jump();
    }
  }

  // ##LAGS TOO MUCH GRADIENT ATTEMPT FAILED
  // sky gradient from (0, 228, 255) to (255, 255, 255)
  // void drawSky() {
  //   color skyColor = color(0, 228, 255);
  //   strokeWeight(1);
  //
  //   for (int i = 0; i < height; i++){
  //     stroke(lerpColor(skyColor, color(255), map(i, 0, height, 0, 1)));
  //     line(0, i-1, width, i+1);
  //   }
  // }

  // draw Sky
  public void drawSky() {
    image(sky, 0, 0);
  }

  // void drawGrass() {
  //   int grassWidth = 50;
  //   for(int i = 0; i < width; i += grassWidth) {
  //     shape(grass, grassPosX + i, 570, grassWidth, (grass.height * grassWidth)/grass.width);
  //   }
  //   grassPosX--;
  // }

  public void display() {
    //draw sky
    drawSky();
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
PShape building1;
PShape building2;
PShape building3;
PShape building4;
PShape building5;
PShape building6;
PShape bearTrap;
PShape bearTrapActivated;
PShape grass;
//used to load building and trap sprites
public void loadSprites() {
  building1 = loadShape("Graphics/Buildings/Building 1.svg");
  building2 = loadShape("Graphics/Buildings/Building 2.svg");
  building3 = loadShape("Graphics/Buildings/Building 3.svg");
  building4 = loadShape("Graphics/Buildings/Building 4.svg");
  building5 = loadShape("Graphics/Buildings/Building 5.svg");
  building6 = loadShape("Graphics/Buildings/Building 6.svg");
  bearTrap = loadShape("Graphics/Traps/BearTrap.svg");
  bearTrapActivated = loadShape("Graphics/Traps/BearTrapActivated.svg");
  grass = loadShape("Graphics/Environment/Grass/Grass.svg");
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
}
/*
Team-turtle-hat
Cho, David, Giles
March 2017
Controls the displaying of traps including villagers and trap stuff
*/

class Traps extends Sprites {
  int boundryHeight;
  int boundryWidth;
  boolean once;
  //uses constructor of the sprites class
  Traps(int posX, int typeOfSprite) {
    super(posX, typeOfSprite);
    once = true;
  }


  public void display() {
    //typeOfSprite determines which building it will display
    switch(typeOfSprite){
      case 1:
        //load image andd set posY;
        int trapSize = 100;
        posY = 520;
        if(!activatedStatus) {
          shape(bearTrap, posX, posY, trapSize, (bearTrap.height * trapSize)/bearTrap.width);
        } else {
          shape(bearTrapActivated, posX, posY, trapSize, (bearTrap.height * trapSize)/bearTrap.width);
        }
        //defines boundrys for detection
        boundryHeight = PApplet.parseInt((bearTrap.height * trapSize)/bearTrap.width);
        boundryWidth = trapSize;
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
    }
  }

  public void subtractHealth() {
    if(activated()) {
      if(once) {
        player.health--;
        once = false;
      }
    }
  }

  public void detection() {
    //if a trap is less than 185 it begins to test for detection
    if(posX < 185) {
      //loops through the y values of the trap
      for (int i = PApplet.parseInt(getY()); i < (posY + boundryHeight); i += 3) {
        //tests for detection along the left side of the trap
        if(posX > 75 && i < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && i > player.posY) {
          activatedStatus = true;
        }
      }
      //loops through x values of trap
      for (int i = PApplet.parseInt(getX()); i < posX + boundryWidth; i += 3) {
        //tests for detection along the top of the trap
        if(posY < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && posY > player.posY && i > 75 && i < 185) {
          activatedStatus = true;
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
