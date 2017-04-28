/*
Team-turtle-hat
David, Cho, Giles
March 2017
Class that controls the bear and bear stuff
*/
PImage bearWalk0, bearWalk1, bearWalk2, bearWalk3, bearWalk4, bearWalk5, bearWalk6, bearWalk7, bearWalk8, bearWalk9, bearWalk10;
PImage bearWalk11, bearWalk12, bearWalk13, bearWalk14, bearWalk15, bearWalk16, bearWalk17, bearWalk18, bearWalk19, bearWalk20;
PImage bearWalk21, bearWalk22, bearWalk23, bearWalk24, bearWalk25, bearWalk26, bearWalk27, bearWalk28, bearWalk29, bearWalk30;
PImage bearWalk31, bearWalk32, bearWalk33, bearWalk34, bearWalk35, bearWalk36, bearWalk37, bearWalk38, bearWalk39, bearWalk40;
PImage bearWalk41, bearWalk42, bearWalk43, bearWalk44, bearWalk45;
PImage bear;

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
    jumpFactor = 1.6;
    jumpDuration = 27;
  }

  void display() {
    image(bearWalk0, 75, posY, bearSize, (bearSprite.height * bearSize)/bearSprite.width);
  }

  void setCounter(int newCounter) {
    counter = newCounter;
  }

  boolean getJumping() {
    return jumping;
  }

  void setJump(boolean newBool) {
    jumping = newBool;
  }

  boolean dead() {
    if(health == 0) {
      return true;
    } else {
      return false;
    }
  }

  void jump() {
    if (jumping){
      //counter is frame count for jump duration
      if (counter < jumpDuration) {
        posY = 400 - ((jumpFactor * counter) + (.5 * -3.6 * sq(counter + -13)) + 300);
        counter++;

      } else if (counter >= jumpDuration){
        jumping = false;
        posY = 400;
      }
    }
  }

  PShape bearType(int num) {
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
      case 45:
        bear = bearWalk45;
        break;

    }
    return bear;
  }
}

void loadBear() {
  bearWalk0 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk0.png");
  bearWalk1 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk1.png");
  bearWalk2 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk2.png");
  bearWalk3 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk3.png");
  bearWalk4 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk4.png");
  bearWalk5 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk5.png");
  bearWalk6 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk6.png");
  bearWalk7 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk7.png");
  bearWalk8 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk8.png");
  bearWalk9 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk9.png");
  bearWalk10 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk10.png");
  bearWalk11 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk11.png");
  bearWalk12 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk12.png");
  bearWalk13 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk13.png");
  bearWalk14 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk14.png");
  bearWalk15= loadImage("Graphics/Bear/Exports/PNG Export/BearWalk15.png");
  bearWalk16 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk16.png");
  bearWalk17 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk17.png");
  bearWalk18 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk18.png");
  bearWalk19 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk19.png");
  bearWalk20 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk20.png");
  bearWalk21 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk21.png");
  bearWalk22 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk22.png");
  bearWalk23 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk23.png");
  bearWalk24 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk24.png");
  bearWalk25 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk25.png");
  bearWalk26 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk26.png");
  bearWalk27 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk27.png");
  bearWalk28 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk28.png");
  bearWalk29 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk29.png");
  bearWalk30 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk30.png");
  bearWalk31 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk31.png");
  bearWalk32 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk32.png");
  bearWalk33 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk33.png");
  bearWalk34 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk34.png");
  bearWalk35 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk35.png");
  bearWalk36 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk36.png");
  bearWalk37 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk37.png");
  bearWalk38 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk38.png");
  bearWalk39 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk39.png");
  bearWalk40 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk40.png");
  bearWalk41 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk41.png");
  bearWalk42 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk42.png");
  bearWalk43 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk43.png");
  bearWalk44 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk44.png");
  bearWalk45 = loadImage("Graphics/Bear/Exports/PNG Export/BearWalk45.png");
}
