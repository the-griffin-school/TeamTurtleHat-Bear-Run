/*
Team-turtle-hat
Cho, David, Giles
March 2017
Controls the displaying of buildings and building stuff
*/
class Buildings extends Sprites {
  //is able to control the size of the building proportionally
  int buildingSize;
  float posY;
  int boundryHeight;
  int boundryWidth;

  //uses construcor of the sprites class
  Buildings(float posX, int typeOfSprite) {
    super(posX, typeOfSprite);
    buildingSize = 140;
  }
  
  void drawBuilding(PShape building) {
    posY = 350;
      shape(building, posX, posY, buildingSize, (building.height * buildingSize)/building.width);
      //defines boundries of the building for detection purposes
      boundryHeight = int((building.height * buildingSize)/building.width);
      boundryWidth = buildingSize;
  }
  
  //displays a building based on typeOfSprite
  void display() {
    switch(typeOfSprite){
      case 1:
        //displays the first building type.
        drawBuilding(building1);
        break;
      case 2:
<<<<<<< HEAD
        drawBuilding(building2);
        break;
      case 3:
        drawBuilding(building3);
        break;
      case 4:
        posY = 350;
        drawBuilding(building4);
        break;
      case 5:
        posY = 350;
        drawBuilding(building5);
        break;
      case 6:
        posY = 350;
        drawBuilding(building6);
=======
        posY = 307;
        shape(building2, posX, posY, buildingSize, (building2.height * buildingSize)/building2.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building2.height * buildingSize)/building2.width);
        boundryWidth = buildingSize;
        break;
      case 3:
        posY = 383;
        shape(building3, posX, posY, buildingSize, (building3.height * buildingSize)/building3.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building3.height * buildingSize)/building3.width);
        boundryWidth = buildingSize;
        break;
      case 4:
        posY = 354;
        shape(building4, posX, posY, buildingSize, (building4.height * buildingSize)/building4.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building4.height * buildingSize)/building4.width);
        boundryWidth = buildingSize;
        break;
      case 5:
        posY = 307;
        shape(building5, posX, posY, buildingSize, (building5.height * buildingSize)/building5.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building5.height * buildingSize)/building5.width);
        boundryWidth = buildingSize;
        break;
      case 6:
        posY = 266;
        shape(building6, posX, posY, buildingSize, (building6.height * buildingSize)/building6.width);
        //defines boundries of the building for detection purposes
        boundryHeight = int((building6.height * buildingSize)/building6.width);
        boundryWidth = buildingSize;
>>>>>>> refs/remotes/origin/Develop
        break;
    }
  }

  void detection() {
    //tests for detection at the last possible moment to reduce load
    if(posX < 185) {
      //loops through y values of the building
      for (int i = int(posY); i < (posY + boundryHeight); i += 3) {
        //tests for detection along the left side of the building
        if(posX > 75 && i < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && i > player.posY) {
          destroyedStatus = true;
        }
      }
      //loops through x values of building
      for (int i = int(posX); i < posX + boundryWidth; i += 3) {
        //tests for detection along the top of the building
        if(posY < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && posY > player.posY && i > 75 && i < 185) {
          destroyedStatus = true;
        }
      }
    }
  }
}
