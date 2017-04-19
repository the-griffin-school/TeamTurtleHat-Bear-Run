/*
Team-turtle-hat
Cho, David, Giles
March 2017
Class that both buildings and traps inherit.
*/
PImage building1;
PShape bearTrap;
PShape bearTrapActivated;
//used to load building and trap sprites
void loadSprites() {
  building1 = loadImage("Graphics/building1.png");
  bearTrap = loadShape("Graphics/Traps/BearTrap.svg");
  bearTrapActivated = loadShape("Graphics/Traps/BearTrapActivated.svg");
}
//parent class to buildings and traps
class Sprites {
  int posX;
  int posY;
  // int boundryWidth;
  // int boudnryHeight;
  boolean destroyedStatus;
  boolean activated;
  //determines which type of builing/trap will be displayed.
  int typeOfSprite;

  Sprites(int posX, int typeOfSprite) {
    this.posX = posX;
    this.typeOfSprite = typeOfSprite;
  }

  //Methods
  boolean destroyed() {
    return destroyedStatus;
  }

  //moves sprites from right to left
  void move() {
    posX -= 5;
  }

  void display() {
  }

  void detection() {

  }
}
