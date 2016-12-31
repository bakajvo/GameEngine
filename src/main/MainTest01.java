package main;

import org.lwjgl.opengl.Display;
import renderEngine.window.DisplayManager;

public class MainTest01 {

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        while (!Display.isCloseRequested()) {


            DisplayManager.updateDisplay();
        }

        DisplayManager.closeDisplay();
    }
}
