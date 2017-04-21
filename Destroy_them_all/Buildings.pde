/*
Team-turtle-hat
Cho, David, Giles
March 2017
Controls the displaying of buildings and building stuff
*/
class Buildings extends Sprites {
  //is able to control the size of the building proportionally
  int buildingSize = 140;
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
        shape(building1, posX, posY, buildingSize, (building1.height * buildingSize)/building1.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building1.height * buildingSize)/building1.width);
        boundryWidth = buildingSize;
        break;
      case 2:
        posY = 350;
        shape(building2, posX, posY, buildingSize, (building2.height * buildingSize)/building2.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building2.height * buildingSize)/building2.width);
        boundryWidth = buildingSize;
        break;
      case 3:
        posY = 350;
        shape(building3, posX, posY, buildingSize, (building3.height * buildingSize)/building3.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building3.height * buildingSize)/building3.width);
        boundryWidth = buildingSize;
        break;
      case 4:
        posY = 350;
        shape(building4, posX, posY, buildingSize, (building4.height * buildingSize)/building4.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building4.height * buildingSize)/building4.width);
        boundryWidth = buildingSize;
        break;
      case 5:
        posY = 350;
        shape(building5, posX, posY, buildingSize, (building5.height * buildingSize)/building5.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building5.height * buildingSize)/building5.width);
        boundryWidth = buildingSize;
        break;
      case 6:
        posY = 350;
        shape(building6, posX, posY, buildingSize, (building6.height * buildingSize)/building6.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building6.height * buildingSize)/building6.width);
        boundryWidth = buildingSize;
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
