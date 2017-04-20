/*
Team-turtle-hat
Cho, David, Giles
March 2017
Controls the displaying of traps including villagers and trap stuff
*/

class Traps extends Sprites {
  int boundryHeight;
  int boundryWidth;
  //uses constructor of the sprites class
  Traps(int posX, int typeOfSprite) {
    super(posX, typeOfSprite);
  }


  void display() {
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

  void detection() {
    if(posX < 185) {
      for (int i = posY; i < (posY + boundryHeight); i += 3) {
        if(posX > 75 && i < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && i > player.posY) {
          activatedStatus = true;
        }
      }
      for (int i = posX; i < posX + boundryWidth; i += 3) {
        if(posY < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && posY > player.posY && i > 75 && i < 185) {
          activatedStatus = true;
        }
      }
    }
  }
}
