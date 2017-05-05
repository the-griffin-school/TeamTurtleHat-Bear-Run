/*
Team-turtle-hat
 Giles, David, Cho
 March 2017
 Display and controls the options page of the game
 */

class Options {
  //Fields
  int selectMenu;
  int diffNum;
  int soundNum;
  String difficulty;
  String sound;

  //Constructor
  Options() {
    diffNum = 1;
    soundNum = 0;
    selectMenu = 0;
    difficulty = "NORMAL";
    sound = "ON";
  }

  //Methods
  void drawBackground() {
    image(sky, 0, 0);
  }

  void drawTitle() {
    rectMode(CENTER);
    stroke(255);
    fill(255, 255, 255, 0);
    rect(width/2, 130, 500, 100);
    fill(255);
    textAlign(CENTER);
    textSize(50);
    text("OPTIONS", width/2, 150);
    textSize(40);
    text("Press Enter to Return to Main Menu", width/2, height - height/6);
  }

  void menuSelection() {
    textAlign(CENTER);
    // menu selection
    switch(selectMenu) {
    case 0:
      // DIFFICULTY
      textSize(40);
      text("DIFFICULTY: " + difficulty, width/2, 300);
      textSize(30);
      text("SOUND: " + sound, width/2, 350);
      break;
    case 1:
      //SOUND
      textSize(30);
      text("DIFFICULTY: " + difficulty, width/2, 300);
      textSize(40);
      text("SOUND: " + sound, width/2, 350);
      break;
    }
  }

  void difficultyChange(int i) {
    switch(i) {
    case 0:
      difficulty = "EASY";
      diff = 2;
      playGame.genDiff = -1;
      playGame.genTime = 2000;
      break;
    case 1:
      difficulty = "NORMAL";
      diff = 3;
      playGame.genDiff = 0;
      playGame.genTime = 1500;
      break;
    case 2:
      difficulty = "HARD";
      diff = 4;
      playGame.genDiff = 1;
      playGame.genTime = 1100;
      break;
    }
  }

  void soundChange(int i) {
    switch(i) {
    case 0:
      sound = "ON";
      soundEffects = true;
      backgroundMusic.loop();
      break;
    case 1:
      sound = "OFF";
      soundEffects = false;
      backgroundMusic.pause();
      break;
    }
  }

  void display() {
    difficultyChange(diffNum);
    soundChange(soundNum);
    drawBackground();
    drawTitle();
    menuSelection();
  }
}
