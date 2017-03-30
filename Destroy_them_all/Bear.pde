/*
David, Cho, Giles
March 2017
Class that controls the bear and bear stuff
*/

class Bear {
  int posY = 400;

  void display() {
    image(bearSprite, 75, posY, bearSprite.width/15, bearSprite.height/15);
    println(bearSprite.width + " " + bearSprite.height);
  }

  void jump() {
    //if(/*jump boolean*/) {
    // posY--;
    //}
  }
}
