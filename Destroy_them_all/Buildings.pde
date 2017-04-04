/*
Cho, David, Giles
March 2017
Controls the displaying of buildings and building stuff
*/
class Buildings extends Sprites {

  //uses construcor of the sprites class
  Buildings(int posX, int typeOfSprite) {
    super(posX, typeOfSprite);
  }

  //displays a building based on typeOfSprite
  void display() {
    switch(typeOfSprite){
      case 1:
        //displays the first building type.
        image(building1, posX, 400, 200, 200);
        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
    }
  }

}
