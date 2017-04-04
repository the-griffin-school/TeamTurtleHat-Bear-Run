/*
Cho, Giles, David
March 2017
Displays and controls the main menu of the game
*/
boolean startGame = false;
int selectMenu = 0;

void startMenu() {
  menuBackground();
}

void menuBackground(){
  background(0);

  //draw sky
  pushMatrix();
  scale(1.5);
  translate(0, -30);
  image(sky, 0, 0, width, height);


  //draw trees
  image(trees, 0, 400, width, trees.height/(trees.width/800));



  popMatrix();

  //draw title
  fill(255);
  textAlign(CENTER);
  textFont(robotoCondensed);
  textSize(50);
  text("RIGHT TO BEAR ARMS", width/2, 150);
  stroke(255);
  strokeWeight(5);
  noFill();
  rectMode(CENTER);
  rect(width/2, 130, 500, 100);

  // menu selection
  switch(selectMenu) {
    case 0:
      textSize(40);
      text("Play", width/2, 300);
      textSize(30);
      text("Stats", width/2, 350);
      text("Options", width/2, 400);
      break;
    case 1:
      textSize(40);
      text("Stats", width/2, 350);
      textSize(30);
      text("Play", width/2, 300);
      text("Options", width/2, 400);
      break;
    case 2:
      textSize(40);
      text("Options", width/2, 400);
      textSize(30);
      text("Play", width/2, 300);
      text("Stats", width/2, 350);
      break;
  }

  //if user pressed ENTER
  if(startGame) {
    gameState = "GAME START";
  }
}