/*
Team-turtle-hat
 Cho, David, Giles
 March 2017
 Class used give credit to the best coders and most orgainized coders in the class
 */

 class Credits {

   Credits() {

   }

   void display() {
     image(sky, 0, 0, width, height);

     //title
     rectMode(CENTER);
     stroke(255);
     fill(255, 255, 255, 0);
     rect(width/2, 130, 500, 100);
     fill(255);
     textAlign(CENTER);
     textSize(50);
     text("CREDITS", width/2, 150);

     fill(255);
     textAlign(CENTER);
     textSize(45);
     text("Made By: Cho, David, Giles", width/2, height/2);
     text("Press Enter To Return to Main Menu", width/2, height - height/6);

   }
 }
