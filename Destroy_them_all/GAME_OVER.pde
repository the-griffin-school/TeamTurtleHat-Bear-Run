/*
Team-turtle-hat
 <<<<<<< HEAD
 Giles, David, Cho
 March 2017
 Displays the game over screen and give the player the option of playing again.
 It also displays the score.
 */

//GameOver functions: dictates death screen looks and functions.
class GameOver {
  int randomMsg = int(random(6));
  String deathMsg;
  int deathSelect = 0;

  GameOver() {
  }

  //gives a random bear pun
  void deathMsg() {
    switch(randomMsg) {
    case 0:
      deathMsg = "You're unBEARable";
      break;
    case 1:
      deathMsg = "You're worse than a barBEARian";
      break;
    case 2:
      deathMsg = "It is time for you to be BEARied";
      break;
    case 3:
      deathMsg = "You should be emBEARessed";
      break;
    case 4:
      deathMsg = "You're a BEARicade of progress";
      break;
    case 5:
      deathMsg = "Have you lost your BEARings?";
      break;
    }

    textAlign(CENTER);
    textSize(60);
    fill(255, 0, 0);
    text("GAME OVER", width/2, height/2 - height/6 - 40);
    fill(255);
    textSize(40);
    text(deathMsg, width/2, height/2 - 40);
    text("Score" + " " + playGame.getScore(), width/2, height/2 + height/6 - 50);
    text("Highscore" + " " + highscores.getHighscore(), width/2, height/2 + height/6);
  }

  //Restart/Main Menu cases for Game Over.
  void deathMenu(int num) {
    switch (num) {
    case 0:
      textSize(40);
      text("RESTART", width/2, height - 100);
      textSize(30);
      text("MAIN MENU", width/2, height - 50);
      break;
    case 1:
      textSize(30);
      text("RESTART", width/2, height - 100);
      textSize(40);
      text("MAIN MENU", width/2, height - 50);
      break;
    }
  }

  //Game Over display function
  void display() {
    background(0);
    deathMsg();
    highscores.saveHighscore();
    deathMenu(deathSelect);
  }
}