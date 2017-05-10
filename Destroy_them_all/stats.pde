class Stats {

  Stats() {
  }

  void display() {
    image(sky, 0, 0, width, height);

    //title
    rectMode(CENTER);
    stroke(255);
    fill(255, 255, 255, 0);
    rect(width/2, 130, 500, 100);
    fill(255);
    textAlign(CENTER);
    textSize(50);
    text("STATS", width/2, 150);

    fill(255);
    textAlign(CENTER);
    textSize(45);
    text("Highscore:" + " " + highscores.getHighscore(), width/2, height/2);
    text("Press Enter To Return to Main Menu", width/2, height - height/6);
  }
}
