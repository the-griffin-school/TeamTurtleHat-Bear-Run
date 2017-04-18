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
<<<<<<< HEAD
} */

int selectOptions;

void optionsMenuBackground() { 
   background(0);

  //draw sky
  pushMatrix();
  scale(1.5);
  translate(0, -30);
  image(sky, 0, 0, width, height);

  popMatrix();
  
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
}
 
 switch (selectOptions) {
   case 0: 
  textAlign(LEFT);
   textSize(30);
   text("EASY", width/3, 250);
   textSize(20);
   text("MEDIUM", width/3, 300);
   text("HARD", width/3, 350);
   textAlign(RIGHT);
   
  
=======
}
>>>>>>> 0a0453e1d503d91d30268e352e11d4d1af37826a
