/*
Team-turtle-hat
Giles, David, Cho
March 2017
Displays the game over screen and give the player the option of playing again.
It also displays the score.
*/
class GameOver {
  int randomMsg = int(random(5));
  String deathMsg;

  GameOver() {
  }

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
     }

     textAlign(CENTER);
     textSize(60);
     fill(255, 0, 0);
     text("GAME OVER", width/2, height/2 - height/6);
     fill(255);
     textSize(40);
     text(deathMsg, width/2, height/2);
     text("Score" + " " + playGame.score, width/2, height/2 + height/6);
   }

   void buttonDetection() {
      // stroke(255);
      // fill(0);
      // rectMode(CORNER);
      // rect(width/2 - width/3, height/2 + height/4, width/3 - width/12, height/6);
      // rect(width/2 + (width/2 -width/3 - width/12), height/2 + height/4, width/3 - width/12, height/6);
      // rectMode(CENTER);
      // fill(255);
      // text("Main Menu", (width/2 - width/3) + (width/3 - width/12)/2, (height/2 + height/4) + height/9);
   }

   void display() {
    background(0);
    deathMsg();
    buttonDetection();
  }
}
