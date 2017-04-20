/*
Team-turtle-hat
David, Cho, Giles
March 2017
Class that controls the bear and bear stuff
*/

class Bear {
  int posY = 400;
  //is used to control size of the bear
  int bearSize = 110;
  int health = 3;

  Bear() {

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
