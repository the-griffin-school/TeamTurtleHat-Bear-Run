/*
Team-turtle-hat
 Cho, David, Giles
 March 2017
 Controls the displaying of buildings and building stuff
 */
AudioPlayer boom1;
AudioPlayer boom2;
boolean soundEffects = true;
int diff = 3;

class Buildings extends Sprites {
  //is able to control the size of the buildings proportionally
  int buildingSize;
  int smokeSize;
  float posY;

  //stores width and height of building for detection purposes
  int boundryHeight;
  int boundryWidth;
  //is able to change all of the buildings y value
  int changeBuildingY = 7;
  boolean once = false;
  int alpha;

  //uses construcor of the sprites class
  Buildings(float posX, int typeOfSprite) {
    super(posX, typeOfSprite);
    buildingSize = 140;
    smokeSize = 140;
    alpha = 255;
  }

  void drawBuilding(PImage building, int newPosY) {
    posY = newPosY;
    buildingSize = 140;
    image(building, posX, posY, buildingSize, (building.height * buildingSize)/building.width);
    //defines boundries of the building for detection purposes
    boundryHeight = int((building.height * buildingSize)/building.width);
    boundryWidth = buildingSize;
  }
  void drawBuildingEnlarged(PImage building, int newPosY) {
    posY = newPosY;
    buildingSize = 210;
    image(building, posX, posY, buildingSize, (building.height * buildingSize)/building.width);
    //defines boundries of the building for detection purposes
    boundryHeight = int((building.height * buildingSize)/building.width);
    boundryWidth = buildingSize;
  }

  //displays a building based on typeOfSprite
  void display() {
    if (smokeSize < 400) {
      switch(typeOfSprite) {
      case 1:
        //displays the first building type.
        drawBuilding(building1, 347 + changeBuildingY);
        break;
      case 2:
        drawBuilding(building2, 304 + changeBuildingY);
        break;
      case 3:
        drawBuildingEnlarged(building3, 276 + changeBuildingY);
        break;
      case 4:
        drawBuildingEnlarged(building4, 231 + changeBuildingY);
        break;
      case 5:
        drawBuilding(building5, 304 + changeBuildingY);
        break;
      case 6:
        drawBuilding(building6, 263 + changeBuildingY);
        break;
      }
    }
  }

  void detection() {
    //tests for detection at the last possible moment to reduce load
    if (posX < 185 && keyCode == 16) {
      //loops through y values of the building
      for (int i = int(posY); i < (posY + boundryHeight); i += 3) {
        //tests for detection along the left side of the building
        if (posX > 75 && i < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && i > player.posY) {
          destroyedStatus = true;
        }
      }
      //loops through x values of building
      for (int i = int(posX); i < posX + boundryWidth; i += 3) {
        //tests for detection along the top of the building
        if (posY < (player.posY + (bearSprite.height * player.bearSize)/bearSprite.width) && posY > player.posY && i > 75 && i < 185) {
          destroyedStatus = true;
        }
      }
    }
  }

  void smoke() {
    if (destroyed()) {
      smoke.disableStyle();
      shapeMode(CENTER);
      fill(189, 189, 189, alpha);
      shape(smoke, posX + boundryWidth/2, posY + boundryHeight/2, smokeSize, (smoke.height * smokeSize)/smoke.width);
      if (smokeSize < 500) {
        smokeSize += 70;
      }
      if (smokeSize > 350) {
        alpha -= 40;
      }
      shapeMode(CORNER);
    }
  }

  void addScore() {
    //only adds score if the building has been destroyed
    if (destroyed() && !once) {
      playGame.score += 10;
      //SOUNDS
      int randomSound = int(random(2));
      if (randomSound == 0 && soundEffects) {
        boom1.loop(0);
      } else if (soundEffects) {
        boom2.loop(0);
      }
      once = true;
      if (playGame.score % diff == 0) {
        playGame.setGameSpeed(playGame.gameSpeed + 1);
      }
    }
  }
}
