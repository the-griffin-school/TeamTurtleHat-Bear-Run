/*
Team-turtle-hat
 David, Cho, Giles
 March 2017
 Controls the actual gameplay of the game
 */

boolean playerJump = false;
float randomSprite;

ArrayList<Sprites> sprites = new ArrayList<Sprites>();
ArrayList<Float> grassList = new ArrayList<Float>();
ArrayList<Integer> mountainsBack = new ArrayList<Integer>();
ArrayList<Integer> mountainsFront = new ArrayList<Integer>();
ArrayList<Integer> clouds = new ArrayList<Integer>();
ArrayList<Integer> cloudsY = new ArrayList<Integer>();
ArrayList<Integer> cloudsType = new ArrayList<Integer>();
ArrayList<Integer> cloudsSlow = new ArrayList<Integer>();
ArrayList<Integer> cloudsSlowY = new ArrayList<Integer>();
ArrayList<Integer> cloudsSlowType = new ArrayList<Integer>();
//stores time;
int time = 0;

class PlayGame {
  //Fields
  float randomSprite;
  int time;
  int score;
  float gameSpeed;
  int grassWidth = 50;
  int mtsWidth = width;
  int genDiff = 0;
  int genTime = 1500;
  PShape shape;
  int counterNight = 0;
  int alpha2 = 0;
  int nightTime = 0;
  boolean nightSwitch = false;
  int adder = 1;

  //Constructor
  PlayGame() {
    playerJump = false;
    time = 0;
    score = 0;
    gameSpeed = 15;
  }

  //Methods

  //Function to retrieve score value
  int getScore() {
    return score;
  }

  //Function to randomely determine when a tree is going to be placed
  void generateSprites() {
    randomSprite = random(30, 50);
    //is going to determine if a sprite should be added. Then it will decide either building or trap.
    if (randomSprite < 45 && randomSprite > 40 && millis() - time > genTime) {
      if (randomSprite > 42.5 + genDiff) {
        //add buliding to arraylist
        sprites.add(new Buildings(width, int(random(7))));
        time = millis();
      } else if (randomSprite < 42.5 + genDiff) {
        //adds trap to arraylist
        sprites.add(new Traps(width, 1));
        time = millis();
      }
    }
  }

  //used to add a specific number of ints/floats to the arrays.
  //run in setup
  void addSprites() {
    for (int i = 0; i <= width + grassWidth *2; i += grassWidth) {
      grassList.add(new Float(i));
    }
    for (int i = 0; i <= width; i += width) {
      mountainsBack.add(new Integer(i));
      mountainsFront.add(new Integer(i));
    }
    for (int i = 0; i <= width; i += 300) {
      clouds.add(new Integer(i));
      cloudsSlow.add(new Integer(i));
    }
    //stores y values for the clouds
    for (int i = 0; i < 10; i ++) {
      cloudsY.add(new Integer(int(random(40, 300))));
      cloudsSlowY.add(new Integer(int(random(40, 300))));
      cloudsType.add(new Integer(int(random(6))));
      cloudsSlowType.add(new Integer(int(random(6, 9))));
    }
  }

  //takes in a num from 0-9 and returns a cloud
  PShape cloudType(int num) {
    switch (num) {
    case 0:
      shape = cloud1;
      break;
    case 1:
      shape = cloud2;
      break;
    case 2:
      shape = cloud3;
      break;
    case 3:
      shape = cloud4;
      break;
    case 4:
      shape = cloud5;
      break;
    case 5:
      shape = cloud6;
      break;
    case 6:
      shape = cloud7;
      break;
    case 7:
      shape = cloud8;
      break;
    case 8:
      shape = cloud9;
      break;
    }
    return shape;
  }

  //displays the clouds
  void drawClouds() {
    //loops through all the clouds in the array
    for (int i = 0; i < clouds.size(); i++) {
      //moves the clouds left by two pixels
      clouds.set(i, clouds.get(i) - 2);
      //draws the clouds
      shape(cloudType(cloudsType.get(i)), clouds.get(i), cloudsY.get(i));
      //resets cloud once it goes off the scree
      if (clouds.get(i) < -400) {
        clouds.set(i, width);
      }
    }
    //loops through all the slow clouds
    for (int i = 0; i < cloudsSlow.size(); i++) {
      //moves the cloud left by one pixel
      cloudsSlow.set(i, cloudsSlow.get(i) - 1);
      //draws the clouds
      shape(cloudType(cloudsSlowType.get(i)), cloudsSlow.get(i), cloudsSlowY.get(i));
      //resets the clouds once they go offscreen
      if (cloudsSlow.get(i) < - 400) {
        cloudsSlow.set(i, width);
      }
    }
  }

  void drawGrass() {
    //loops through all the grass in the array
    for (int i = 0; i < grassList.size(); i++) {
      //moves the grass left by a specific num
      grassList.set(i, grassList.get(i) - gameSpeed);
      //draws the grass
      shape(grass, grassList.get(i), 570, grassWidth, (grass.height * grassWidth)/grass.width);
      //resets the grass once it goes off screen
      if (grassList.get(i) < -grassWidth) {
        grassList.set(i, grassList.get(i) + width + grassWidth * 3);
      }
    }
  }

  // void drawMountains() {
  //   //loops through all the back mountains
  //   for (int i = 0; i < mountainsBack.size(); i++) {
  //     //moves the background mountains left by one pixel
  //     mountainsBack.set(i, mountainsBack.get(i) - 1);
  //     //draws the mountains
  //     shape(mtsBack, mountainsBack.get(i), 165, width, (mtsBack.height * width)/mtsBack.width);
  //     //resets mountains once it goes offscreen
  //     if (mountainsBack.get(i) < 2 - width) {
  //       mountainsBack.set(i, width);
  //     }
  //   }
  //   //loops through front mountains
  //   for (int i = 0; i < mountainsFront.size(); i++) {
  //     //moves front mountains by two pixels
  //     mountainsFront.set(i, mountainsFront.get(i) - 2);
  //     //draws mountains
  //     shape(mtsFront, mountainsFront.get(i), 170, width, (mtsFront.height * width)/mtsFront.width);
  //     //resets mountains once they go offscreen
  //     if (mountainsFront.get(i) < 2 - width) {
  //       mountainsFront.set(i, width);
  //     }
  //   }
  // }

  //setting game speed from outside the class
  void setGameSpeed(float newSpeed) {
    gameSpeed = newSpeed;
  }

  //returns gameSpeed
  float getGameSpeed() {
    return gameSpeed;
  }

  void displayScore() {
    textSize(30);
    fill(255);
    //displays the score and player health in the top left corner
    text("Score:" + " " + score, 40, 40);
    player.displayHealth();
  }

  void checkAlive() {
    //if the player is dead it activates the game over screen
    if (player.dead()) {
      gameState = "GAME OVER";
    }
  }

  //removes object from ArrayList if it off the screen.
  void clearSprite(int i) {
    //if the sprite has been destroyed or is off screen it is deleted from the array
    if (sprites.get(i).getX() < -500) {
      sprites.remove(i);
    }
  }

  void process() {
    //loops through all objects in ArrayList
    for (int i = sprites.size() -1; i >= 0; i--) {
      //moves sprite from right to left
      sprites.get(i).move(getGameSpeed());
      //displays sprite
      sprites.get(i).display();
      //tests for detection of the sprite
      sprites.get(i).detection();
      //subtracs health from the player when it hits a trap
      sprites.get(i).subtractHealth();
      //checks to see if the player is still alive
      checkAlive();
      sprites.get(i).smoke();
      //adds score if a building is destoryed
      sprites.get(i).addScore();
      //removes a sprite if it is destroyed or goes off screen
      clearSprite(i);
    }
    //displays player
    player.jump();
    //displays the bear
    player.display();
  }

  // draw Sky
  void drawSky() {
    image(sky, 0, 0);
  }

  // void night() {
  //   fill(0, 0, 0, alpha2);
  //   rectMode(CORNER);
  //   rect(0, 0, width, height);
  //
  //   if (millis() - nightTime > 45000) {
  //     nightSwitch = true;
  //   }
  //   if (nightSwitch) {
  //     if (counterNight % 3 == 0) {
  //       alpha2 += adder;
  //     }
  //     counterNight++;
  //     if(counterNight > 100) {
  //      counterNight = 0;
  //     }
  //     if (alpha2 > 180) {
  //       nightSwitch = false;
  //       adder *= -1;
  //       nightTime = millis();
  //     }
  //   }
  // }

  void display() {
    //draw sky
    drawSky();
    //draws mts
    //drawMountains();
    //draw Clouds
    drawClouds();
    //generate sprites
    generateSprites();
    //moves and displays
    process();
    //draws grass
    drawGrass();
    //displays score;
    displayScore();
  }
}
