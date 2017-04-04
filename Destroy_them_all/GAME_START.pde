/*
David, Cho, Giles
March 2017
Controls the actual gameplay of the game
*/
boolean playerJump = false;
float randomSprite;
ArrayList<Sprites> sprites = new ArrayList<Sprites>();

void gameStart() {
  //draw sky
  image(sky, 0, 0, width, height);

  //draw trees
  image(trees, 0, 400, width, trees.height/(trees.width/800));
  player.display();
  if(playerJump) {
    player.jump();
  }

  randomSprite = random(100);
  //is going to determine if a sprite should be added. Then it will decide either building or trap.
  if(randomSprite < 45 && randomSprite > 40) {
    if(randomSprite > 42.5) {
      //add buliding
      sprites.add(new Buildings());
    } else if (randomSprite < 42.5) {
      //add trap
      sprites.add(new Traps());
    }
  }
  
}
