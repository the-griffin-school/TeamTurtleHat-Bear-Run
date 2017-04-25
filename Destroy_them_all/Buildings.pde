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

  void drawBuilding(PShape building, int newPosY) {
      posY = newPosY;
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
        drawBuilding(building1, 347);
        break;
      case 2:
        drawBuilding(building2, 304);
        break;
      case 3:
        drawBuilding(building3, 380);
        break;
      case 4:
        drawBuilding(building4, 351);
        break;
      case 5:
        drawBuilding(building5, 304);
        break;
      case 6:
        drawBuilding(building6, 263);
        println(581 - boundryHeight);
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
