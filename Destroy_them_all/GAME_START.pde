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
      //add buliding to arraylist
      sprites.add(new Buildings(900, 1));
    } else if (randomSprite < 42.5) {
      //adds trap to arraylist
      sprites.add(new Traps(900, 1));
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
}
