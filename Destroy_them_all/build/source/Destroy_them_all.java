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
*/

Minim minim;
AudioPlayer bearTrapSound;

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
  minim = new Minim(this);
  bearTrapSound = minim.loadFile("Sounds/Traps/bearTrap.wav", 2048);
}

public void draw() {
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
      }
      break;
    case "GAME OVER":
      if(key == ENTER) {
        player.health = 3;
        playGame.score = 0;
        for (int i = sprites.size() -1; i >= 0 ; i--) {
          sprites.remove(i);
        }
        mainMenu.startGame = false;
        gameState = "MAIN MENU";
      }
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
        posY = 400 - ((jumpFactor * counter) + (.5f * -3.6f * sq(counter + -13)) + 300);
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
  //is able to control the size of the buildings proportionally
  int buildingSize;
  float posY;

  //stores width and height of building for detection purposes
  int boundryHeight;
  int boundryWidth;
  //is able to change all of the buildings y value
  int changeBuildingY = 7;

  //uses construcor of the sprites class
  Buildings(float posX, int typeOfSprite) {
    super(posX, typeOfSprite);
    buildingSize = 140;
  }

  public void drawBuilding(PImage building, int newPosY) {
      posY = newPosY;
      image(building, posX, posY, buildingSize, (building.height * buildingSize)/building.width);
      //defines boundries of the building for detection purposes
      boundryHeight = PApplet.parseInt((building.height * buildingSize)/building.width);
      boundryWidth = buildingSize;
  }

  //displays a building based on typeOfSprite
  public void display() {
    switch(typeOfSprite){
      case 1:
        //displays the first building type.
        drawBuilding(building1, 347 + changeBuildingY);
        break;
      case 2:
        drawBuilding(building2, 304 + changeBuildingY);
        break;
      case 3:
        drawBuilding(building3, 380+ changeBuildingY);
        break;
      case 4:
        drawBuilding(building4, 351+ changeBuildingY);
        break;
      case 5:
        drawBuilding(building5, 304 + changeBuildingY);
        break;
      case 6:
        drawBuilding(building6, 263 + changeBuildingY);
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

   public void display() {
    background(0);
    deathMsg();
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
    if(options) {
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
  PShape shape;

  //Constructor
  PlayGame() {
    playerJump = false;
    time = 0;
    score = 0;
    gameSpeed = 15;
  }

  //Methods

  //Function to randomely determine when a tree is going to be placed
  public void generateSprites() {
    randomSprite = random(30, 50);
    //is going to determine if a sprite should be added. Then it will decide either building or trap.
    if(randomSprite < 45 && randomSprite > 40 && millis() - time > 1500) {
      if(randomSprite > 42.5f) {
        //add buliding to arraylist
        sprites.add(new Buildings(width, PApplet.parseInt(random(7))));
        time = millis();
      } else if (randomSprite < 42.5f) {
        //adds trap to arraylist
        sprites.add(new Traps(width, 1));
        time = millis();
      }
    }
  }

  //used to add a specific number of ints/floats to the arrays.
  //run in setup
  public void addSprites() {
    for (int i = 0; i <= width; i += grassWidth) {
      grassList.add(new Float(i));
    }
    for(int i = 0; i <= width; i += width) {
      mountainsBack.add(new Integer(i));
      mountainsFront.add(new Integer(i));
    }
    for(int i = 0; i <= width; i += 300) {
      clouds.add(new Integer(i));
      cloudsSlow.add(new Integer(i));
    }
    //stores y values for the clouds
    for(int i = 0; i < 10; i ++) {
      cloudsY.add(new Integer(PApplet.parseInt(random(40, 300))));
      cloudsSlowY.add(new Integer(PApplet.parseInt(random(40, 300))));
      cloudsType.add(new Integer(PApplet.parseInt(random(6))));
      cloudsSlowType.add(new Integer(PApplet.parseInt(random(6,9))));
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
    for(int i = 0; i < clouds.size(); i++) {
      //moves the clouds left by two pixels
      clouds.set(i, clouds.get(i) - 2);
      //draws the clouds
      shape(cloudType(cloudsType.get(i)), clouds.get(i), cloudsY.get(i));
      //resets cloud once it goes off the scree
      if(clouds.get(i) < -400) {
        clouds.set(i, width);
      }
    }
    //loops through all the slow clouds
    for(int i = 0; i < cloudsSlow.size(); i++) {
      //moves the cloud left by one pixel
      cloudsSlow.set(i, cloudsSlow.get(i) - 1);
      //draws the clouds
      shape(cloudType(cloudsSlowType.get(i)), cloudsSlow.get(i), cloudsSlowY.get(i));
      //resets the clouds once they go offscreen
      if(cloudsSlow.get(i) < - 400) {
        cloudsSlow.set(i, width);
      }
    }
  }

  public void drawGrass() {
    //loops through all the grass in the array
    for(int i = 0; i < grassList.size(); i++) {
      //moves the grass left by a specific num
      grassList.set(i, grassList.get(i) - gameSpeed);
      //draws the grass
      shape(grass, grassList.get(i), 570, grassWidth + gameSpeed, (grass.height * grassWidth)/grass.width);
      //resets the grass once it goes off screen
      if(grassList.get(i) < 2 - grassWidth) {
        grassList.set(i, PApplet.parseFloat(width));
      }
    }
  }

  public void drawMountains() {
    //loops through all the back mountains
    for(int i = 0; i < mountainsBack.size(); i++) {
      //moves the background mountains left by one pixel
      mountainsBack.set(i, mountainsBack.get(i) - 1);
      //draws the mountains
      shape(mtsBack, mountainsBack.get(i), 165, width, (mtsBack.height * width)/mtsBack.width);
      //resets mountains once it goes offscreen
      if(mountainsBack.get(i) < 2 - width) {
        mountainsBack.set(i, width);
      }
    }
    //loops through front mountains
    for(int i = 0; i < mountainsFront.size(); i++) {
      //moves front mountains by two pixels
      mountainsFront.set(i, mountainsFront.get(i) - 2);
      //draws mountains
      shape(mtsFront, mountainsFront.get(i), 170, width, (mtsFront.height * width)/mtsFront.width);
      //resets mountains once they go offscreen
      if(mountainsFront.get(i) < 2 - width) {
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

  public void addScore(int i) {
    //only adds score if the building has been destroyed
    if(sprites.get(i).destroyed()) {
      score += 10;
    }
  }

  public void displayScore() {
    textSize(30);
    fill(255);
    //displays the score and player health in the top left corner
    text("Score:" + " " + score, 40, 40);
    text("Health:" + " " + player.health , 200, 40);
  }

  public void checkAlive() {
    //if the player is dead it activates the game over screen
    if(player.dead()) {
      gameState = "GAME OVER";
    }
  }

  //removes object from ArrayList if it off the screen.
  public void clearSprite(int i) {
    //if the sprite has been destroyed or is off screen it is deleted from the array
    if(sprites.get(i).getX() < -500 || sprites.get(i).destroyed()) {
      sprites.remove(i);
    }
  }

  public void process() {
    //loops through all objects in ArrayList
    for(int i = sprites.size() -1; i >= 0; i--) {
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
      //adds score if a building is destoryed
      addScore(i);
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


PImage building1, building2, building3, building4, building5, building6;
PShape bearTrap, bearTrapActivated;
PShape grass;
PShape mtsBack;
PShape mtsFront;
PShape cloud1, cloud2, cloud3, cloud4, cloud5, cloud6, cloud7, cloud8, cloud9;

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
    switch(typeOfSprite){
      case 1:
        //load image andd set posY;
        int trapSize = 100;
        posY = 520;

        //if not activated it displays the flat bear trap
        if(!activatedStatus) {
          shape(bearTrap, posX, posY, trapSize, (bearTrap.height * trapSize)/bearTrap.width);
        } else {
          //if not activated it displays the activated bear trap
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
      //once makes it so it only subtracts from the player health once
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
          if(!playOnce) {
            //plays bear trap sound once
            bearTrapSound.loop(0);
            playOnce = true;
          }
        }
      }
      //loops through x values of trap
      for (int i = PApplet.parseInt(getX()); i < posX + boundryWidth; i += 3) {
        //tests for detection along the top of the trap
        if(posY < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && posY > player.posY && i > 75 && i < 185) {
          activatedStatus = true;
          if(!playOnce) {
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
