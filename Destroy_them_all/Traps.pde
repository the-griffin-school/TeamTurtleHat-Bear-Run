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


  void display() {
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
        boundryHeight = int((bearTrap.height * trapSize)/bearTrap.width);
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

  void subtractHealth() {
    if(activated()) {
      //once makes it so it only subtracts from the player health once
      if(once) {
        player.health--;
        once = false;
      }
    }
  }

  void detection() {
    //if a trap is less than 185 it begins to test for detection
    if(posX < 185) {
      //loops through the y values of the trap
      for (int i = int(posY); i <= (posY + boundryHeight); i += 3) {
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
      for (int i = int(getX()); i < posX + boundryWidth; i += 3) {
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
