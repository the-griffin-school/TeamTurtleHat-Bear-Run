/*
Team-turtle-hat
Cho, David, Giles
March 2017
Controls the displaying of buildings and building stuff
*/
class Buildings extends Sprites {
  //is able to control the size of the building proportionally
  int building1Size = 140;
  int posY;
  int boundryHeight;
  int boundryWidth;

  //uses construcor of the sprites class
  Buildings(int posX, int typeOfSprite) {
    super(posX, typeOfSprite);
  }

  //displays a building based on typeOfSprite
  void display() {
    switch(typeOfSprite){
      case 1:
        //displays the first building type.
        posY = 350;
        shape(building1, posX, posY, building1Size, (building1.height * building1Size)/building1.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building1.height * building1Size)/building1.width);
        boundryWidth = building1Size;
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
    //tests for detection at the last possible moment to reduce load
    if(posX < 185) {
      //loops through y values of the building
      for (int i = posY; i < (posY + boundryHeight); i += 3) {
        //tests for detection along the left side of the building
        if(posX > 75 && i < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && i > player.posY) {
          destroyedStatus = true;
        }
      }
      //loops through x values of building
      for (int i = posX; i < posX + boundryWidth; i += 3) {
        //tests for detection along the top of the building
        if(posY < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && posY > player.posY && i > 75 && i < 185) {
          destroyedStatus = true;
        }
      }
    }
  }
}
