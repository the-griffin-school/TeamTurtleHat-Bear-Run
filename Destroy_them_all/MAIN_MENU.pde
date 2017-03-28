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
  image(sky, 0, 0, width, height);

  //draw trees
  image(trees, 0, 400, width, trees.height/(trees.width/800));
}
