class Highscore {
  //Fields
  String[] rawHighscore = {};
  String[] saveScore = {" "};
  int[] highscores = {};
  int highscore;

  //constructor
  Highscore() {

  }

  //Methods
  //function to write onto a text file
  void saveHighscore() {
    rawHighscore = loadStrings("Highscore.txt");
    highscores = int(rawHighscore);
    for(int i = highscores.length -1; i >= 0; i--){
      highscore += (10^i * highscores[i])/10;
    }

    if(playGame.getScore() > highscore) {
      saveScore[0] = str(playGame.getScore());
      saveStrings("Highscore.txt", saveScore);
      println(saveScore);
    }
  }

  //function to read from text file
  int getHighscore() {
    highscores = int(loadStrings("Highscore.txt"));
    highscore = highscores[0];
    return highscore;
  }

}
