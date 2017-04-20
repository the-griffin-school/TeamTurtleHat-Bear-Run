/*
Team-turtle-hat
Giles, David, Cho
March 2017
Displays the game over screen and give the player the option of playing again.
It also displays the score.
*/
int randomMsg;
String deathMsg;

class GameOver {

  GameOver() {
    randomMsg = int(random(5));
  }


  // void deathMsg() {
  //   switch(randomMsg) {
  //     case 0:
  //       deathMsg = "You're unBEARable";
  //       break;
  //     case 1:
  //       deathMsg = "You're worse than a barBEARian";
  //       break;
  //     case 2:
  //       deathMsg = "It is time for you to be BEARied";
  //       break;
  //     case 3:
  //       deathMsg = "You should be emBEARessed";
  //       break;
  //     case 4:
  //       deathMsg = "You're a BEARicade of progress";
  //       break;
  //   }
  //   textAlign(CENTER);
  //   text(deathMsg, width/2, height/2);
  // }
}
