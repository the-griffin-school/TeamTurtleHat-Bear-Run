/*
Cho, David, Giles
March 2017
Controls the displaying of buildings and building stuff
*/
class Buildings extends Sprites {
  //is able to control the size of the building proportionally
  int building1Size = 140;
  int posY;

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
        image(building1, posX, posY, building1Size, (building1.height * building1Size)/building1.width);
        boundryWidth = building1Size;
        boudnryHeight = (building1.height * building1Size)/building1.width;
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
    }
  }

  void detect() {

  }
}
