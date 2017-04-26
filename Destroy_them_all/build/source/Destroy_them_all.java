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

int currentFrameRate;

public void setup() {
  
  background(0);
  textAlign(CENTER);

  sky = loadImage("Graphics/Environment/Sky/SkyImage.png");
  robotoCondensed = loadFont("Fonts/RobotoCondensed-Bold-50.vlw");
  bearSprite = loadShape("Graphics/Bear/Bear.svg");
  loadSprites();
  playGame.addSprites();
  playGame.cloudY();
}

public void draw() {
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
  displayFrames();

}

public void displayFrames() {
  currentFrameRate = PApplet.parseInt(frameRate);
  textAlign(CORNERS);
  fill(255);
  noStroke();
  textSize(20);
  text("Frame rate: " + currentFrameRate, 950, 40);
}

//USER INPUTS
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
        if(!player.getJumping()) {
          player.setCounter(0);
        }
        player.setJump(true);
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
  float posY;
  //is used to control size of the bear
  int bearSize;
  int health;
  float jumpFactor;
  int counter;
  boolean jumping;
  int jumpDuration;

  Bear() {
    posY = 400;
    bearSize = 110;
    health = 3;
    counter = 0;
    jumpFactor = 1.6f;
    jumpDuration = 27;
  }

  public void display() {
    shape(bearSprite, 75, posY, bearSize, (bearSprite.height * bearSize)/bearSprite.width);
  }

  public void setCounter(int newCounter) {
    counter = newCounter;
  }

  public boolean getJumping() {
    return jumping;
  }

  public void setJump(boolean newBool) {
    jumping = newBool;
  }

  public boolean dead() {
    if(health == 0) {
      return true;
    } else {
      return false;
    }
  }

  public void jump() {
    if (jumping){
      //counter is frame count for jump duration
      if (counter < jumpDuration) {
        println(counter);
        posY = 400 - ((jumpFactor * counter) + (.5f * -3.6f * sq(counter + -13)) + 300);
        println(posY);
        counter++;

      } else if (counter >= jumpDuration){
        jumping = false;
        posY = 400;
      }
    }
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

  public void drawBuilding(PShape building, int newPosY) {
      posY = newPosY - 1;
      shape(building, posX, posY, buildingSize, (building.height * buildingSize)/building.width);
      //defines boundries of the building for detection purposes
      boundryHeight = PApplet.parseInt((building.height * buildingSize)/building.width);
      boundryWidth = buildingSize;
  }

  //displays a building based on typeOfSprite
  public void display() {
    switch(typeOfSprite){
      case 1:
        //displays the first building type.
        drawBuilding(building1, 347);
        break;
      case 2:
        drawBuilding(building2, 304);
        break;
      case 3:
        drawBuilding(building3, 380);
        break;
      case 4:
        drawBuilding(building4, 351);
        break;
      case 5:
        drawBuilding(building5, 304);
        break;
      case 6:
        drawBuilding(building6, 263);
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
ArrayList<Integer> mountainsBack = new ArrayList<Integer>();
ArrayList<Integer> mountainsFront = new ArrayList<Integer>();
int[] cloudY = new int[4];
float treesX = 0;
float trees2X = 800;
//stores time;
int time = 0;
int grassPosX = 0;


class PlayGame {
  //Fields
  float randomSprite;
  float treesX;
  float trees2X;
  int time;
  int score;
  float gameSpeed;
  int grassWidth = 50;
  int mtsWidth = width;

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
    if(randomSprite < 45 && randomSprite > 40 && millis() - time > 5000) {
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

  public void addSprites() {
    for (int i = 0; i <= width; i += grassWidth) {
      grassList.add(new Float(i));
    }
    for(int i = 0; i <= width; i += width) {
      mountainsBack.add(new Integer(i));
    }
    for(int i = 0; i <= width; i += width) {
      mountainsFront.add(new Integer(i));
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

  public void cloudY() {
    cloudY[0] = PApplet.parseInt(random(50, 300));
    cloudY[1] = PApplet.parseInt(random(50, 300));
    cloudY[2] = PApplet.parseInt(random(50, 300));
    cloudY[3] = PApplet.parseInt(random(50, 300));
  }

  public int returnCloudY(int i) {
    return cloudY[i];
  }

  public PShape randCloud(PShape rand, int i) {
    switch(i){
      case 1:
      rand = cloud1;
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
      case 5:
        break;
      case 6:
        break;
      case 7:
        break;
      case 8:
        break;
      case 9:
        break;
    }
    return rand;
  }

  public void drawMountains() {
    for(int i = 0; i < mountainsBack.size(); i++) {
      mountainsBack.set(i, mountainsBack.get(i) - 1);
      shape(mtsBack, mountainsBack.get(i), 165, width, (mtsBack.height * width)/mtsBack.width);
      shape(cloud1, mountainsBack.get(i), returnCloudY(1));
      shape(cloud1, mountainsBack.get(i) + 400, returnCloudY(2));
      if(mountainsBack.get(i) < 2 - width) {
        mountainsBack.set(i, width);
      }
    }
    for(int i = 0; i < mountainsFront.size(); i++) {
      mountainsFront.set(i, mountainsFront.get(i) - 2);
      shape(mtsFront, mountainsFront.get(i), 170, width, (mtsFront.height * width)/mtsFront.width);
      shape(cloud1, mountainsFront.get(i), returnCloudY(3));
      shape(cloud1, mountainsFront.get(i) + 400, returnCloudY(0));
      if(mountainsFront.get(i) < 2 - width) {
        mountainsFront.set(i, width);
      }

    }
    //shape(mtsFront, 0, 0, width, (mtsFront.height * width)/mtsFront.width);
  }

  public void drawClouds() {
    // shape(cloud1, 0, 0);
    // shape(cloud2, 0, 200);
    // shape(cloud3, 200, 200);
    // shape(cloud4, 400, 0);
    // shape(cloud5, 400, 200);
    // shape(cloud6, 400, 400);
    // shape(cloud7, 800, 0);
    // shape(cloud8, 800, 200);
    // shape(cloud9, 800, 400);
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
    textSize(30);
    fill(255);
    text("Score:" + " " + score, 40, 40);
  }

  public void checkAlive() {
    if(player.dead()) {
      gameState = "GAME OVER";
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
    player.jump();
    player.display();
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
PShape building1;
PShape building2;
PShape building3;
PShape building4;
PShape building5;
PShape building6;
PShape bearTrap;
PShape bearTrapActivated;
PShape grass;
PShape mtsBack;
PShape mtsFront;
PShape cloud1, cloud2, cloud3, cloud4, cloud5, cloud6, cloud7, cloud8, cloud9;
PShape randCloud1, randCloud2, randCloud3, randCloud4;
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
      for (int i = PApplet.parseInt(posY); i <= (posY + boundryHeight); i += 3) {
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
