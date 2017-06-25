package main;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.Camera;
import renderEngine.entities.Entity;
import renderEngine.lights.Light;
import renderEngine.loaders.Loader;
import renderEngine.loaders.ObjLoader;
import renderEngine.models.TexturedModel;
import renderEngine.render.MasterRenderer;
import renderEngine.terrain.Terrain;
import renderEngine.textures.Texture;
import renderEngine.window.DisplayManager;

public class MainTest01 {

    public static void main(String[] args) {

        DisplayManager.createDisplay();
        Loader loader = new Loader();
        Camera camera = new Camera();

        TexturedModel model = new TexturedModel(ObjLoader.loadObjModel("dragon", loader), new Texture(loader.loadTexture("silver")));
        model.getTexture().setShineDamper(1);
        model.getTexture().setReflectivity(2);
        Entity dragon = new Entity(model, new Vector3f(0, 0, -25), 0, 0, 0, 1);
        Light light = new Light(new Vector3f(20000, 20000, 2000), new Vector3f(1, 1, 1));

        Terrain terrain = new Terrain(0, -1, loader, "heightmap", new Texture(loader.loadTexture("grass3")));

        MasterRenderer renderer = new MasterRenderer();
        while (!Display.isCloseRequested()) {
            dragon.increaseRotation(0, 1, 0);
            camera.move();

            renderer.processTerrain(terrain);
            renderer.processEntity(dragon);

            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
