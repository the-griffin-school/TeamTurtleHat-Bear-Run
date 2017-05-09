class Highscore {
  //Fields
  int[] highscores = {};
  int highscore;

  //constructor
  Highscore() {

  }

  //Methods
  //function to write onto a text file
  void saveHighscore() {
    highscores = loadStrings("Highscore.txt");
    highscore = highscores[0];
    if(playGame.getScore() > highscore) {
      saveStrings("Highscore.txt", playGame.getScore());
    }
  }

  //function to read from text file
  int getHighscore() {
    highscores = loadStrings("Highscore.txt");
    highscore = highscores[0];
    return highscore;
  }
}
