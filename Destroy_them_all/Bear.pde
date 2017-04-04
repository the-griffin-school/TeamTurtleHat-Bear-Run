/*
David, Cho, Giles
March 2017
Class that controls the bear and bear stuff
*/

class Bear {
  int posY = 400;
  int bearSize = 110;
  int upDown = -1;

  void display() {
    image(bearSprite, 75, posY, bearSize, (bearSprite.height * bearSize)/bearSprite.width);

  }

  void jump() {

  }
}
