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

<<<<<<< HEAD
=======
public class Destroy_them_all extends PApplet {

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

PFont robotoCondensed;


public void setup() {
  frameRate(60);
  
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);
  trees = loadImage("Graphics/Trees-01.png");
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
  if(key == ENTER && gameState == "MAIN MENU") {
    startGame = true;
  }
  if(key == ' ' && gameState == "GAME START") {
    playerJump = true;
  }
}
/*
David, Cho, Giles
March 2017
Class that controls the bear and bear stuff
*/

class Bear {
  int posY = 400;
  int bearSize = 110;
  int upDown = -1;

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

  Buildings(int posX, int typeOfSprite) {
    super(posX, typeOfSprite);
  }

  public void display() {
    switch(typeOfSprite){
      case 1:
        //load image andd set posY;
        image(building1, posX, 400, 200, 200);
        println("iwok");
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

public void gameStart() {
  //draw sky
  image(sky, 0, 0, width, height);

  //draw trees
  image(trees, 0, 400, width, trees.height/(trees.width/800));
  player.display();
  if(playerJump) {
    player.jump();
  }

  randomSprite = random(100);
  //is going to determine if a sprite should be added. Then it will decide either building or trap.
  if(randomSprite < 45 && randomSprite > 40) {
    if(randomSprite > 42.5f) {
      //add buliding
      sprites.add(new Buildings(900, 1));
      println("addedbuilding");
    } else if (randomSprite < 42.5f) {
      //add trap
      sprites.add(new Traps(900, 1));
    }
  }
  //loops through all objects in ArrayList
  for(int i = 0; i < sprites.size(); i++) {
    sprites.get(i).move();
    sprites.get(i).display();
    //removes object from ArrayList if it off the screen.
    if(sprites.get(i).posX < -300) {
      sprites.remove(i);
    }
  }
}
/*
Cho, Giles, David
March 2017
Displays and controls the main menu of the game
*/
boolean startGame = false;

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
  textSize(58);
  text("BEAR RUN", width/2, 150);
  stroke(255);
  strokeWeight(5);
  noFill();
  rectMode(CENTER);
  rect(width/2, 130, 350, 100);

  //play button
  text("press enter to play", width/2, height/2);

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
public void loadSprites() {
  building1 = loadImage("Graphics/building1.png");
}
class Sprites {
  int posX;
  int posY;
  int typeOfSprite;

  Sprites(int posX, int typeOfSprite) {
    this.posX = posX;
    this.typeOfSprite = typeOfSprite;
  }

  public void move() {
    posX -= 10;
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


  Traps(int posX, int typeOfSprite) {
    super(posX, typeOfSprite);
  }


  public void display() {
    switch(typeOfSprite){
      case 1:
        //load image andd set posY;
        println("iwok");
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
>>>>>>> 3336df2100b35d696e04d2315350932b1f8ba5af
