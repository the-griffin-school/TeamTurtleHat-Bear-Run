/*
Cho, David, Giles
March 2017
Class that both buildings and traps inherit.
*/
PImage building1;
//used to load building and trap sprites
void loadSprites() {
  building1 = loadImage("Graphics/building1.png");
}
//parent class to buildings and traps
class Sprites {
  int posX;
  int posY;
  int boundryWidth;
  int boudnryHeight;
  //determines which type of builing/trap will be displayed.
  int typeOfSprite;

  Sprites(int posX, int typeOfSprite) {
    this.posX = posX;
    this.typeOfSprite = typeOfSprite;
  }
  //moves sprites from right to left
  void move() {
    posX -= 1;
  }

  void display() {
  }
}
