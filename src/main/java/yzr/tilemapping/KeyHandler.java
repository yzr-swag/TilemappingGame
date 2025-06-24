package yzr.tilemapping;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public Boolean upPressed = false;
    public Boolean downPressed = false;
    public Boolean leftPressed = false;
    public Boolean rightPressed = false;
    public Boolean shiftPressed = false;
    public String lastPressed = "";

    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = true;
            lastPressed = "W";
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
            lastPressed = "S";
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
            lastPressed = "A";
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
            lastPressed = "D";
        }
        if(code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
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
    }
}
