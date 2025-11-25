package yzr.tilemapping;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public Boolean upPressed = false;
    public Boolean downPressed = false;
    public Boolean leftPressed = false;
    public Boolean rightPressed = false;
    public Boolean shiftPressed = false;
    public Boolean spacePressed = false;
    public Boolean ctrlPressed = false;
    public String lastPressed = "";
    public int escTyped = -1;

    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //returns true if a key is pressed

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            lastPressed = "W";
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            lastPressed = "S";
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            lastPressed = "A";
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            lastPressed = "D";
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (code == KeyEvent.VK_CONTROL) {
            ctrlPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            escTyped = escTyped * -1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //returns false if a key is released

        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upPressed = false;
            lastPressed = "";
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
            lastPressed = "";
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
            lastPressed = "";
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
            lastPressed = "";
        }
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (code == KeyEvent.VK_CONTROL) {
            ctrlPressed = false;
        }
    }
}
