import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import shiffman.box2d.*; 
import org.jbox2d.common.*; 
import org.jbox2d.dynamics.joints.*; 
import org.jbox2d.collision.shapes.*; 
import org.jbox2d.collision.shapes.Shape; 
import org.jbox2d.common.*; 
import org.jbox2d.dynamics.*; 
import org.jbox2d.dynamics.contacts.*; 

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









// A reference to our box2d world
Box2DProcessing box2d;

//list of particles
ArrayList<Particle> particles;

String gameState = "MAIN MENU";
PlayGame playGame = new PlayGame();
MainMenu mainMenu = new MainMenu();

PImage trees;
PImage trees2;
PImage sky;

PImage bearSprite;
Bear player = new Bear();

PFont robotoCondensed;


public void setup() {
  frameRate(240);
  
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);
  trees = loadImage("Graphics/Trees-01.png");
  trees2 = loadImage("Graphics/Trees-01.png");
  sky = loadImage("Graphics/Sky-01.png");
  robotoCondensed = loadFont("Fonts/RobotoCondensed-Bold-50.vlw");
  bearSprite = loadImage("Graphics/Bear.png");
  loadSprites();

  // Initialize box2d physics and create the world
  box2d = new Box2DProcessing(this);
  box2d.createWorld();

  // Turn on collision listening!
  box2d.listenForCollisions();

  // Create the empty list
  particles = new ArrayList<Particle>();
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
    pushMatrix();
    scale(scaleFactor);
    translate(0, -30);
    image(sky, 0, 0, width, height);
    popMatrix();
  }
  public void drawTrees() {
    pushMatrix();
    scale(scaleFactor);
    image(trees, 0, 400, width, trees.height/(trees.width/800));
    popMatrix();
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
    drawTrees();
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


class PlayGame {
  //Fields
  boolean playerJump;
  float randomSprite;
  ArrayList<Sprites> sprites = new ArrayList<Sprites>();
  float treesX;
  float trees2X;
  int time;

  //Constructor
  PlayGame() {
    playerJump = false;
    treesX = 0;
    trees2X = 800;
    time = 0;
  }

  //Methods
  //Function that draws tree layer by stitching two images of trees
  public void drawTrees() {
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
  }

  //Function to randomely determine when a tree is going to be placed
  public void generateSprites() {
    randomSprite = random(50);
    //is going to determine if a sprite should be added. Then it will decide either building or trap.
    if(randomSprite < 45 && randomSprite > 40 && millis() - time > 10000) {
      println("iwok");
      if(randomSprite > 43) {
        //add buliding to arraylist
        sprites.add(new Buildings(800, 1));
        time = millis();
      } else if (randomSprite < 41) {
        //adds trap to arraylist
        sprites.add(new Traps(800, 1));
        time = millis();
      }
    }
  }

  public void move() {
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
  }

  public void displaySprites() {
    //displays player
    player.display();
    if(playerJump) {
      player.jump();
    }
  }

  public void display() {
    //draw sky
    image(sky, 0, 0, width, height);
    //draw trees
    drawTrees();
    //generate sprites
    generateSprites();
    //move sprites
    move();
    //display sprites
    displaySprites();
  }
}

public void beginContact(Contact cp) {
  // Get both shapes
  Fixture f1 = cp.getFixtureA();
  Fixture f2 = cp.getFixtureB();
  // Get both bodies
  Body b1 = f1.getBody();
  Body b2 = f2.getBody();

  // Get our objects that reference these bodies
  Object o1 = b1.getUserData();
  Object o2 = b2.getUserData();

  if (o1.getClass() == Particle.class && o2.getClass() == Particle.class) {
    Particle p1 = (Particle) o1;
    p1.change();
    Particle p2 = (Particle) o2;
    p2.change();
  }
}

// Objects stop touching each other
public void endContact(Contact cp) {
}
class Particle {

  // We need to keep track of a Body and a radius
  Body body;
  float r;
  float w;
  float h;
  int col;

  boolean delete = false;

  Particle(float x, float y, float w_, float h_) {
    w = w_;
    h = h_;
    // This function puts the particle in the Box2d world
    makeBody(new Vec2(x, y), w, h);
    body.setUserData(this);
    col = color(175);
  }

  // This function removes the particle from the box2d world
  public void killBody() {
    box2d.destroyBody(body);
  }

  public void delete() {
    delete = true;
  }

  // Change color when hit
  public void change() {
    col = color(255, 0, 0);
  }

  // Is the particle ready for deletion?
  // Is the particle ready for deletion?
  public boolean done() {
    // Let's find the screen position of the particle
    Vec2 pos = box2d.getBodyPixelCoord(body);
    // Is it off the bottom of the screen?
    if (pos.y > height+r*2 || delete) {
      killBody();
      return true;
    }
    return false;
  }
  //
   // Drawing the box
  public void display() {
    // We look at each body and get its screen position
    Vec2 pos = box2d.getBodyPixelCoord(body);

    rectMode(CENTER);
    pushMatrix();
    translate(pos.x, pos.y);
    rotate(0);
    fill(175);
    stroke(0);
    rect(0, 0, w, h);
    popMatrix();
  }

  // Here's our function that adds the particle to the Box2D world
 public void makeBody(Vec2 center, float w_, float h_) {

    // Define a polygon (this is what we use for a rectangle)
    PolygonShape sd = new PolygonShape();
    float box2dW = box2d.scalarPixelsToWorld(w_/2);
    float box2dH = box2d.scalarPixelsToWorld(h_/2);
    sd.setAsBox(box2dW, box2dH);

    // Define a fixture
    FixtureDef fd = new FixtureDef();
    fd.shape = sd;
    // Parameters that affect physics
    fd.density = 1;
    fd.friction = 0.3f;
    fd.restitution = 0.5f;

    // Define the body and make it from the shape
    BodyDef bd = new BodyDef();
    bd.type = BodyType.DYNAMIC;
    bd.position.set(box2d.coordPixelsToWorld(center));

    body = box2d.createBody(bd);
    body.createFixture(fd);
  }
}
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
