/* Giles Fowler, David Klingler, Wonseok Cho.
Team Turtlehat
User Interface
Screens: Start Menu (Credits, Settings (sound, difficulty)), Pause Menu (resume button, quit to main menu), You're Dead Menu (Restart, quit)
*/

Button button;

void setup() {
  background(255);
  fullScreen();
  button = new Button (width/3, height/3, width/3, height/3, "Hello!");
}

void draw() {
  button.display();
}
  
void mouseClicked() {
  if (button.mouseOver()) {
    background(0);
  }
  else {
    background(255);
  }
}