/*
David, Cho, Giles
March 2017
Controls the actual gameplay of the game
*/
boolean playerJump = false;
float randomSprite;
ArrayList<Sprites> sprites = new ArrayList<Sprites>();
float treesX = 0;
float trees2X = 800;

void gameStart() {
  //draw sky
  image(sky, 0, 0, width, height);

  //draw trees
  image(trees, treesX, 400, width, trees.height/(trees.width/800));
  image(trees2, trees2X, 400, width, trees.height/(trees.width/800));
  treesX-= 1;
  trees2X-= 1;
  //loops images
  if(treesX <= -800) {
    treesX = 800;
  }
  //loops trees
  if(trees2X <= -800) {
    trees2X = 800;
  }
  randomSprite = random(200);
  //is going to determine if a sprite should be added. Then it will decide either building or trap.
  if(randomSprite < 45 && randomSprite > 40) {
    if(randomSprite > 43) {
      //add buliding to arraylist
      sprites.add(new Buildings(800, 1));
    } else if (randomSprite < 41) {
      //adds trap to arraylist
      sprites.add(new Traps(800, 1));
    }
}
  //loops through all objects in ArrayList
  for(int i = 0; i < sprites.size(); i++) {
    //moves sprite from right to left
    sprites.get(i).move();
    //displays sprite
    sprites.get(i).display();
    //removes object from ArrayList if it off the screen.
    if(sprites.get(i).posX < -300) {
      sprites.remove(i);
    }
  }
  //displays player.
  player.display();
  if(playerJump) {
    player.jump();
  }
}