/*
Team-turtle-hat
David, Cho, Giles
March 2017
Class that controls the bear and bear stuff
*/

class Bear {
  float posY;
  //is used to control size of the bear
  int bearSize;
  int health;
  float jumpFactor;
  int counter;
  boolean jumping;
  int jumpDuration;

  Bear() {
    posY = 400;
    bearSize = 110;
    health = 3;
    counter = 0;
    jumpFactor = 1.6;
    jumpDuration = 27;
  }

  void display() {
    shape(bearSprite, 75, posY, bearSize, (bearSprite.height * bearSize)/bearSprite.width);
  }

  void setCounter(int newCounter) {
    counter = newCounter;
  }

  boolean getJumping() {
    return jumping;
  }

  void setJump(boolean newBool) {
    jumping = newBool;
  }

  boolean dead() {
    if(health == 0) {
      return true;
    } else {
      return false;
    }
  }

  void jump() {
    if (jumping){
      //counter is frame count for jump duration
      if (counter < jumpDuration) {
        println(counter);
        posY = 400 - ((jumpFactor * counter) + (0.5 * -3.6 * sq(counter + -13)) + 300);
        println(posY);
        counter++;

      } else if (counter >= jumpDuration){
        jumping = false;
        posY = 400;
      }
    }
  }
}
