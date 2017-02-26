package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Camera;
import renderEngine.Renderer;
import renderEngine.entities.Entity;
import renderEngine.lights.Light;
import renderEngine.loaders.Loader;
import renderEngine.loaders.ObjLoader;
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
        Camera camera = new Camera();

        TexturedModel model = new TexturedModel(ObjLoader.loadObjModel("dragon", loader), new Texture(loader.loadTexture("silver")));
        model.getTexture().setShineDamper(1);
        model.getTexture().setReflectivity(10);
        Entity dragon = new Entity(model, new Vector3f(0, 0, -25), 0, 0, 0, 1);
        Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

        while (!Display.isCloseRequested()) {
            dragon.increaseRotation(0, 1, 0);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadLight(light);
            shader.loadViewMatrix(camera);
            renderer.render(dragon, shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
