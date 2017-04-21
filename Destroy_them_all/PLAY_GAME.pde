/*
Team-turtle-hat
David, Cho, Giles
March 2017
Controls the actual gameplay of the game
*/

boolean playerJump = false;
float randomSprite;
ArrayList<Sprites> sprites = new ArrayList<Sprites>();
float treesX = 0;
float trees2X = 800;
//stores time;
int time = 0;


class PlayGame {
  //Fields
  boolean playerJump;
  float randomSprite;
  ArrayList<Sprites> sprites = new ArrayList<Sprites>();
  float treesX;
  float trees2X;
  int time;
  int score;

  //Constructor
  PlayGame() {
    playerJump = false;
    treesX = 0;
    trees2X = 800;
    time = 0;
    score = 0;
  }

  //Methods

  //Function to randomely determine when a tree is going to be placed
  void generateSprites() {
    randomSprite = random(40, 50);
    //is going to determine if a sprite should be added. Then it will decide either building or trap.
    if(randomSprite < 45 && randomSprite > 40 && millis() - time > 5000) {
      if(randomSprite > 43) {
        //add buliding to arraylist
        sprites.add(new Buildings(width, int(random(7))));
        time = millis();
      } else if (randomSprite < 42) {
        //adds trap to arraylist
        sprites.add(new Traps(width, 1));
        time = millis();
      }
    }
  }

  void addScore() {
    score += 10;
  }

  void displayScore() {
    text("Score:" + " " + score, 60, 30);
  }

  void move() {
    //loops through all objects in ArrayList
    for(int i = 0; i < sprites.size(); i++) {
      //moves sprite from right to left
      sprites.get(i).move();
      //displays sprite
      sprites.get(i).display();
      sprites.get(i).detection();
      sprites.get(i).subtractHealth();
      if(player.dead()) {
       println("dead");
      }
      //removes object from ArrayList if it off the screen.
      if(sprites.get(i).posX < -500 || sprites.get(i).destroyed()) {
        addScore();
        sprites.remove(i);
      }
    }
  }

  void displaySprites() {
    //displays player
    player.display();
    if(playerJump) {
      player.jump();
    }
  }

  void display() {
    background(0);
    //draw sky
    shape(sky, 0, 0, width, height);
    //generate sprites
    generateSprites();
    //move sprites
    move();
    //display sprites
    displaySprites();
    //displays score;
    displayScore();
  }
}
