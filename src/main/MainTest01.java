package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Camera;
import renderEngine.Loader;
import renderEngine.Renderer;
import renderEngine.entities.Entity;
import renderEngine.objects.Cube;
import renderEngine.shaders.StaticShader;
import renderEngine.window.DisplayManager;

public class MainTest01 {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
        Camera camera = new Camera();

        Entity testCube = new Cube(loader, new Vector3f(0, 0, -2)).getEntity();

        while (!Display.isCloseRequested()) {
            testCube.increaseRotation(1, 1, 0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(testCube, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
