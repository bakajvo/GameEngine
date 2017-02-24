package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Loader;
import renderEngine.Renderer;
import renderEngine.entities.Entity;
import renderEngine.models.RawModel;
import renderEngine.models.TexturedModel;
import renderEngine.shaders.StaticShader;
import renderEngine.textures.Texture;
import renderEngine.window.DisplayManager;

public class MainTest01 {

    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

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
        Entity entity = new Entity(model, new Vector3f(0, 0, -1), 0, 0, 0, 1);

        while (!Display.isCloseRequested()) {

            entity.increasePosition(new Vector3f(0, 0, -0.002f));

            renderer.prepare();
            shader.start();
            renderer.render(entity, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
