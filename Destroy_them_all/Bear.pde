/*
Team-turtle-hat
David, Cho, Giles
March 2017
Class that controls the bear and bear stuff
*/

class Bear {
  int posY;
  //is used to control size of the bear
  int bearSize;
  int health;

  Bear() {
    posY = 400;
    bearSize = 110;
    health = 3;
  }

  void display() {
    shape(bearSprite, 75, posY, bearSize, (bearSprite.height * bearSize)/bearSprite.width);
  }


  boolean dead() {
    if(health == 0) {
      return true;
    } else {
      return false;
    }
  }

  void jump() {

  }
}