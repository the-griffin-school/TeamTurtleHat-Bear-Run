/*
Team-turtle-hat
 Cho, David, Giles
 March 2017
 Class used to read highscore and save new highscores
 */

class Highscore {
  //Fields
  String[] rawHighscore = {};
  String[] saveScore = {" "};
  int[] highscores = {};
  int highscore;

  //Methods
  //function to write onto a text file
  void saveHighscore() {
    highscore = highscores[0];
    rawHighscore = loadStrings("Highscore.txt");
    highscores = int(rawHighscore);
    for(int i = highscores.length -1; i >= 0; i--){
      highscore += (10^i * highscores[i])/10;
    }

    if(playGame.getScore() > highscore) {
      String[] scoreSaved = {str(playGame.getScore())};
      //saveScore[0] = str(playGame.getScore());
      saveStrings("Highscore.txt", scoreSaved);
      println(scoreSaved);
    }
  }

  //function to read from text file
  int getHighscore() {
    highscores = int(loadStrings("Highscore.txt"));
    highscore = highscores[0];
    return highscore;
  }
}
