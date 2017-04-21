/*
Team-turtle-hat
Cho, David, Giles
March 2017
Class that both buildings and traps inherit.
*/
PShape building1;
PShape building2;
PShape building3;
PShape building4;
PShape building5;
PShape building6;
PShape bearTrap;
PShape bearTrapActivated;
//used to load building and trap sprites
void loadSprites() {
  building1 = loadShape("Graphics/Buildings/Building 1.svg");
  building2 = loadShape("Graphics/Buildings/Building 2.svg");
  building3 = loadShape("Graphics/Buildings/Building 3.svg");
  building4 = loadShape("Graphics/Buildings/Building 4.svg");
  building5 = loadShape("Graphics/Buildings/Building 5.svg");
  building6 = loadShape("Graphics/Buildings/Building 6.svg");
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
  boolean activatedStatus;
  //determines which type of builing/trap will be displayed.
  int typeOfSprite;

  Sprites(int posX, int typeOfSprite) {
    this.posX = posX;
    this.typeOfSprite = typeOfSprite;
  }

  //Methods

  //used to determine if a building should be destroyed
  boolean destroyed() {
    return destroyedStatus;
  }

  //used to determine if a trap has been activated
  boolean activated() {
    return activatedStatus;
  }

  //moves sprites from right to left
  void move() {
    posX -= 4;
  }

  void display() {
  }

  void detection() {
  }

  void subtractHealth() {

  }
}
