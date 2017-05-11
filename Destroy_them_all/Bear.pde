/*
Team-turtle-hat
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
    jumpFactor = 1.6;
    jumpDuration = 27;
    bearCounter = 0;
  }

  //METHODS
  //display function for Bear
  void display() {
    image(bearType(bearCounter), 75, posY, bearSize, (bearSprite.height * bearSize)/bearSprite.width);
    bearCounter++;
    if (bearCounter > 44) {
      bearCounter = 0;
    }
  }

  //display function for health hearts.
  void displayHealth() {
    int heartSize = 40;
    for (int i = 0; i < health; i++) {
      image(heart, 180 + (i * (heartSize + 5)), 10, heartSize, (heart.height * heartSize)/heart.width);
    }
  }

  //jump stuff thing
  void setCounter(int newCounter) {
    counter = newCounter;
  }

  boolean getJumping() {
    return jumping;
  }

  void setJump(boolean newBool) {
    jumping = newBool;
  }

  //death boolean
  boolean dead() {
    if (health == 0) {
      return true;
    } else {
      return false;
    }
  }

  //jumping function
  void jump() {
    if (jumping) {
      //counter is frame count for jump duration
      if (counter < jumpDuration) {
        posY = 400 - ((jumpFactor * counter) + (.5 * -3.6 * sq(counter + -13)) + 300);
        counter++;
      } else if (counter >= jumpDuration) {
        jumping = false;
        posY = 400;
      }
    }
  }

  //image cycling for walk animation
  PImage bearType(int num) {
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

void loadBear() {
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