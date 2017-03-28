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
