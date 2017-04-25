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
PShape grass;
PShape mtsBack;
PShape mtsFront;
PShape cloud1, cloud2, cloud3, cloud4, cloud5, cloud6, cloud7, cloud8, cloud9;
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
  grass = loadShape("Graphics/Environment/Grass/Grass.svg");
  mtsFront = loadShape("Graphics/Environment/Mountains/Mountains Front.svg");
  mtsBack = loadShape("Graphics/Environment/Mountains/Mountains Back.svg");
  cloud1 = loadShape("Graphics/Environment/Sky/Clouds Master-01.svg");
  cloud2 = loadShape("Graphics/Environment/Sky/Clouds Master-02.svg");
  cloud3 = loadShape("Graphics/Environment/Sky/Clouds Master-03.svg");
  cloud4 = loadShape("Graphics/Environment/Sky/Clouds Master-04.svg");
  cloud5 = loadShape("Graphics/Environment/Sky/Clouds Master-05.svg");
  cloud6 = loadShape("Graphics/Environment/Sky/Clouds Master-06.svg");
  cloud7 = loadShape("Graphics/Environment/Sky/Clouds Master-07.svg");
  cloud8 = loadShape("Graphics/Environment/Sky/Clouds Master-08.svg");
  cloud9 = loadShape("Graphics/Environment/Sky/Clouds Master-09.svg");
}
//parent class to buildings and traps
class Sprites {
  float posX;
  float posY;
  // int boundryWidth;
  // int boudnryHeight;
  boolean destroyedStatus;
  boolean activatedStatus;
  //determines which type of builing/trap will be displayed.
  int typeOfSprite;

  Sprites(float posX, int typeOfSprite) {
    this.posX = posX;
    this.typeOfSprite = typeOfSprite;
  }

  //Methods

  //returns posX
  float getX() {
    return posX;
  }

  //returns posY
  float getY() {
    return posX;
  }

  //used to determine if a building should be destroyed
  boolean destroyed() {
    return destroyedStatus;
  }

  //used to determine if a trap has been activated
  boolean activated() {
    return activatedStatus;
  }

  //moves sprites from right to left, with input of game speed factor
  void move(float gameSpeed) {
    posX -= gameSpeed;
  }
  
  void display() {
  }

  void detection() {
  }

  void subtractHealth() {

  }
}
