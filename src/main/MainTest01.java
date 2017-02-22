package main;

import org.lwjgl.opengl.Display;
import renderEngine.Loader;
import renderEngine.Renderer;
import renderEngine.StaticShader;
import renderEngine.models.RawModel;
import renderEngine.models.TexturedModel;
import renderEngine.textures.Texture;
import renderEngine.window.DisplayManager;

public class MainTest01 {

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();

        float[] vertices = {
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        float[] textureCords = {
                0, 0,
                0, 1,
                1, 0,
                1, 1
        };

        RawModel rawModel = loader.loadToVAO(vertices, textureCords, indices);
        Texture texture = new Texture(loader.loadTexture("texture_512x512"));
        TexturedModel model = new TexturedModel(rawModel, texture);

        while (!Display.isCloseRequested()) {
            renderer.prepare();
            shader.start();

            renderer.render(model);


            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
