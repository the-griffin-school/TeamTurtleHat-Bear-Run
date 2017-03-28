/*
Cho, Giles, David
March 2017
Displays and controls the main menu of the game
*/

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
  textSize(58);
  text("BEAR RUN", width/2, 150);
  stroke(255);
  strokeWeight(5);
  noFill();
  rectMode(CENTER);
  rect(width/2, 130, 350, 100);
}
