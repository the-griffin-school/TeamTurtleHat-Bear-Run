/*
David, Cho, Giles
March 2017
Controls the actual gameplay of the game
*/

void gameStart() {
  //draw sky
  image(sky, 0, 0, width, height);

  //draw trees
  image(trees, 0, 400, width, trees.height/(trees.width/800));
  player.display();
}
