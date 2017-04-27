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
int grassPosX = 0;



class PlayGame {
  //Fields
  float randomSprite;
  int time;
  int score;
  float gameSpeed;
  int grassWidth = 50;
  int mtsWidth = width;
  PShape shape;

  //Constructor
  PlayGame() {
    playerJump = false;
    time = 0;
    score = 0;
    gameSpeed = 15;
  }

  //Methods

  //Function to randomely determine when a tree is going to be placed
  void generateSprites() {
    randomSprite = random(30, 50);
    //is going to determine if a sprite should be added. Then it will decide either building or trap.
    if(randomSprite < 45 && randomSprite > 40 && millis() - time > 1500) {
      if(randomSprite > 42.5) {
        //add buliding to arraylist
        sprites.add(new Buildings(width, int(random(7))));
        time = millis();
      } else if (randomSprite < 42.5) {
        //adds trap to arraylist
        sprites.add(new Traps(width, 1));
        time = millis();
      }
    }
  }

  void addSprites() {
    for (int i = 0; i <= width; i += grassWidth) {
      grassList.add(new Float(i));
    }
    for(int i = 0; i <= width; i += width) {
      mountainsBack.add(new Integer(i));
      mountainsFront.add(new Integer(i));
    }
    for(int i = 0; i <= width; i += 300) {
      clouds.add(new Integer(i));
      cloudsSlow.add(new Integer(i));
    }
    for(int i = 0; i < 10; i ++) {
      cloudsY.add(new Integer(int(random(40, 300))));
      cloudsSlowY.add(new Integer(int(random(40, 300))));
      cloudsType.add(new Integer(int(random(6))));
      cloudsSlowType.add(new Integer(int(random(6,9))));
    }
  }

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

  void drawClouds() {
    for(int i = 0; i < clouds.size(); i++) {
      clouds.set(i, clouds.get(i) - 2);
      shape(cloudType(cloudsType.get(i)), clouds.get(i), cloudsY.get(i));
      if(clouds.get(i) < 0 - 400) {
        clouds.set(i, width);
      }
    }
    for(int i = 0; i < cloudsSlow.size(); i++) {
      cloudsSlow.set(i, cloudsSlow.get(i) - 1);
      shape(cloudType(cloudsSlowType.get(i)), cloudsSlow.get(i), cloudsSlowY.get(i));
      if(cloudsSlow.get(i) < - 400) {
        cloudsSlow.set(i, width);
      }
    }
  }

  void drawGrass() {
    for(int i = 0; i < grassList.size(); i++) {
      grassList.set(i, grassList.get(i) - 10);
      shape(grass, grassList.get(i), 570, grassWidth, (grass.height * grassWidth)/grass.width);
      if(grassList.get(i) < 2 - grassWidth) {
        grassList.set(i, float(width));
      }
    }
  }

  void drawMountains() {
    for(int i = 0; i < mountainsBack.size(); i++) {
      mountainsBack.set(i, mountainsBack.get(i) - 1);
      shape(mtsBack, mountainsBack.get(i), 165, width, (mtsBack.height * width)/mtsBack.width);
      if(mountainsBack.get(i) < 2 - width) {
        mountainsBack.set(i, width);
      }
    }
    for(int i = 0; i < mountainsFront.size(); i++) {
      mountainsFront.set(i, mountainsFront.get(i) - 2);
      shape(mtsFront, mountainsFront.get(i), 170, width, (mtsFront.height * width)/mtsFront.width);
      if(mountainsFront.get(i) < 2 - width) {
        mountainsFront.set(i, width);
      }

    }
    //shape(mtsFront, 0, 0, width, (mtsFront.height * width)/mtsFront.width);
  }

  //setting game speed from outside the class
  void setGameSpeed(float newSpeed) {
    gameSpeed = newSpeed;
  }

  //returns gameSpeed
  float getGameSpeed() {
    return gameSpeed;
  }

  void addScore(int i) {
    if(sprites.get(i).destroyed()) {
      score += 10;
    }
  }

  void displayScore() {
    textSize(30);
    fill(255);
    text("Score:" + " " + score, 40, 40);
    text("Health:" + " " + player.health , 200, 40);
  }

  void checkAlive() {
    if(player.dead()) {
      gameState = "GAME OVER";
    }
  }

  //removes object from ArrayList if it off the screen.
  void clearSprite(int i) {
    if(sprites.get(i).getX() < -500 || sprites.get(i).destroyed()) {
      sprites.remove(i);
    }
  }

  void process() {
    //loops through all objects in ArrayList
    for(int i = 0; i < sprites.size(); i++) {
      //moves sprite from right to left
      sprites.get(i).move(getGameSpeed());
      //displays sprite
      sprites.get(i).display();
      sprites.get(i).detection();
      sprites.get(i).subtractHealth();
      checkAlive();
      addScore(i);
      clearSprite(i);
    }
    //displays player
    player.jump();
    player.display();
  }

  // ##LAGS TOO MUCH GRADIENT ATTEMPT FAILED
  // sky gradient from (0, 228, 255) to (255, 255, 255)
  // void drawSky() {
  //   color skyColor = color(0, 228, 255);
  //   strokeWeight(1);
  //
  //   for (int i = 0; i < height; i++){
  //     stroke(lerpColor(skyColor, color(255), map(i, 0, height, 0, 1)));
  //     line(0, i-1, width, i+1);
  //   }
  // }

  // draw Sky
  void drawSky() {
    image(sky, 0, 0);
  }

  void display() {
    //draw sky
    drawSky();
    //draws mts
    drawMountains();
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
