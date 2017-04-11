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
  Wonseok Cho, David Klingler, Giles Fowles
  March 2017
  This is the main file that controls all the screens
*/

String gameState = "MAIN MENU";
PImage trees;
PImage trees2;
PImage sky;

PImage bearSprite;
Bear player = new Bear();

PFont robotoCondensed;


public void setup() {
  frameRate(500);
  
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);
  trees = loadImage("Graphics/Trees-01.png");
  trees2 = loadImage("Graphics/Trees-01.png");
  sky = loadImage("Graphics/Sky-01.png");
  robotoCondensed = loadFont("Fonts/RobotoCondensed-Bold-50.vlw");
  bearSprite = loadImage("Graphics/Bear.png");
  loadSprites();
}

public void draw() {
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

public void keyPressed() {
  switch(gameState) {
    case "MAIN MENU":
      if(key == ENTER) {
        switch(selectMenu) {
          case 0:
            startGame = true;
            break;
          case 1:
            break;
          case 2:
            break;
        }
      } else if (keyCode == UP) {
        selectMenu--;

        // From top selection to the bottom when pressed 'up'
        if (selectMenu < 0) selectMenu = 2;
      } else if (keyCode == DOWN) {
        selectMenu++;
        // From bottom selection to the top when pressed 'down'
        if (selectMenu > 2) selectMenu = 0;
      }
      break;
    case "GAME START":
      if(key == ' ') {
        playerJump = true;
      }
      break;
  }
}
/*
David, Cho, Giles
March 2017
Class that controls the bear and bear stuff
*/

class Bear {
  int posY = 400;
  //is used to control size of the bear
  int bearSize = 110;

  public void display() {
    image(bearSprite, 75, posY, bearSize, (bearSprite.height * bearSize)/bearSprite.width);

  }

  public void jump() {

  }
}
/*
Cho, David, Giles
March 2017
Controls the displaying of buildings and building stuff
*/
class Buildings extends Sprites {
  //is able to control the size of the building proportionally
  int building1Size = 140;
  int posY;

  //uses construcor of the sprites class
  Buildings(int posX, int typeOfSprite) {
    super(posX, typeOfSprite);
  }

  //displays a building based on typeOfSprite
  public void display() {
    switch(typeOfSprite){
      case 1:
        //displays the first building type.
        posY = 350;
        image(building1, posX, posY, building1Size, (building1.height * building1Size)/building1.width);
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
    }
  }

  public void detect() {
    
  }
}
/*
Giles, David, Cho
March 2017
Displays the game over screen and give the player the option of playing again.
It also displays the score.
*/
/*
David, Cho, Giles
March 2017
Controls the actual gameplay of the game
*/
boolean playerJump = false;
float randomSprite;
ArrayList<Sprites> sprites = new ArrayList<Sprites>();
float treesX = 0;
float trees2X = 800;
//stores time;
int time = 0;

public void gameStart() {
  //draw sky
  image(sky, 0, 0, width, height);

  //draw trees
  image(trees, treesX, 400, width, trees.height/(trees.width/800));
  image(trees2, trees2X, 400, width, trees.height/(trees.width/800));
  treesX-= 1;
  trees2X-= 1;
  //loops images
  if(treesX <= -800) {
    treesX = 800;
  }
  //loops trees
  if(trees2X <= -800) {
    trees2X = 800;
  }

  randomSprite = random(39, 46);
  //is going to determine if a sprite should be added. Then it will decide either building or trap.
  //millis() - time makes controls the least amount of time between the spawning of sprites

  if(randomSprite < 45 && randomSprite > 40 && millis() - time > 10000) {
    println("iwok");
    if(randomSprite > 43) {
      //add buliding to arraylist
      sprites.add(new Buildings(800, 1));
      //stores the time at which the building was spawned
      time = millis();
    } else if (randomSprite < 41) {
      //adds trap to arraylist
      sprites.add(new Traps(800, 1));
      //stores the time at which the trap was spawned
      time = millis();
    }
}
  //loops through all objects in ArrayList
  for(int i = 0; i < sprites.size(); i++) {
    //moves sprite from right to left
    sprites.get(i).move();
    //displays sprite
    sprites.get(i).display();
    //removes object from ArrayList if it off the screen.
    if(sprites.get(i).posX < -300) {
      sprites.remove(i);
    }
  }
  //displays player.
  player.display();
  if(playerJump) {
    player.jump();
  }
}
/*
Cho, Giles, David
March 2017
Displays and controls the main menu of the game
*/
boolean startGame = false;
int selectMenu = 0;

public void startMenu() {
  menuBackground();
}

public void menuBackground(){
  background(0);

  //draw sky
  pushMatrix();
  scale(1.5f);
  translate(0, -30);
  image(sky, 0, 0, width, height);


  //draw trees
  image(trees, 0, 400, width, trees.height/(trees.width/800));



  popMatrix();

  //draw title
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
/*
Giles, David, Cho
March 2017
Display and controls the options page of the game
*/


class Button {
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

  public boolean mouseOver() {
    if (mouseX >= RectX && mouseX <= RectX + RectW && mouseY >= RectY && mouseY <= RectY + RectH) {
      return true;
    } else {
      return false;
    }
  }

  public void display() { 
   fill(255);
    rect(RectX, RectY, RectW, RectH);
    fill(0);
   text (text, (RectX + (RectW/3)), (RectY + (RectH/3)));
   textSize(75);
  }
}
/*
Giles, David, Cho
March 2017
Displays the pause screen and allows the player to unpause
*/
/*
Cho, David, Giles
March 2017
Class that both buildings and traps inherit.
*/
PImage building1;
//used to load building and trap sprites
public void loadSprites() {
  building1 = loadImage("Graphics/building1.png");
}
//parent class to buildings and traps
class Sprites {
  int posX;
  int posY;
  //determines which type of builing/trap will be displayed.
  int typeOfSprite;

  Sprites(int posX, int typeOfSprite) {
    this.posX = posX;
    this.typeOfSprite = typeOfSprite;
  }
  //moves sprites from right to left
  public void move() {
    posX -= 1;
  }

  public void display() {
  }
}
/*
Cho, David, Giles
March 2017
Controls the displaying of traps including villagers and trap stuff
*/

class Traps extends Sprites {

  //uses constructor of the sprites class
  Traps(int posX, int typeOfSprite) {
    super(posX, typeOfSprite);
  }


  public void display() {
    //typeOfSprite determines which building it will display
    switch(typeOfSprite){
      case 1:
        //load image andd set posY;
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
    }
  }

}
  public void settings() {  size(800, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Destroy_them_all" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
