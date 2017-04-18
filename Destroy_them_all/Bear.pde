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

  void display() {
    image(bearSprite, 75, posY, bearSize, (bearSprite.height * bearSize)/bearSprite.width);

  }

  void jump() {

  }

  // boolean detetion(Sprites sprite) {
  //   for(int i = 0; i < sprites.size(); i++) {
  //     if (sprite == sprites.get(i)) {
  //
  //     }
  //   }
  // }
}
