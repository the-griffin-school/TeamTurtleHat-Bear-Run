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
PImage sky;

public void setup() {
  
  background(0);
  textAlign(CENTER);
  text("Loading...", width/2, height/2);
  trees = loadImage("Graphics/Trees-01.png");
  sky = loadImage("Graphics/Sky-01.png");

}

public void draw() {
  switch(gameState){
    case "MAIN MENU":
      startMenu();
      break;
    case "OPTIONS":
      break;
    case "GAME START":
      break;
    case "GAME OVER":
      break;
    case "PAUSE":
      break;
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
/*
Cho, Giles, David
March 2017
Displays and controls the main menu of the game
*/

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
}
/*
Giles, David, Cho
March 2017
Display and controls the options page of the game
*/
/*
Giles, David, Cho
March 2017
Displays the pause screen and allows the player to unpause
*/
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
