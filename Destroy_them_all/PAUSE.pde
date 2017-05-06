/*
Team-turtle-hat
 Giles, David, Cho
 March 2017
 Displays the pause screen and allows the player to unpause
 */
PImage pauseImage;
boolean pauseOnce = false;

void pauseMenu(int num) {
switch (num) {
  case 0:
    textSize(40);
    text("RESUME", width/2, (height/2) + 100);
    textSize(30);
    text("MAIN MENU", width/2, (height/2)+ 150);
    break;
  case 1:
    textSize(30);
    text("RESUME", width/2, (height/2)+ 100);
    textSize(40);
    text("MAIN MENU", width/2, (height/2)+ 150);
    break;
  }
}

void paused() {
  if(!pauseOnce) {
    pauseImage = get();
    pauseOnce = true;
  }
  image(pauseImage, 0, 0);
  textAlign(CENTER);
  textSize(80);
  fill(0);
  text("PAUSED", width/2, height/2);
  pauseMenu(pauseSelect);
}
