package renderEngine.window;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;

public class DisplayManager {

    private static long lastFrameTime;
    private static float delta;

    public static void createDisplay() {
        ContextAttribs attrib = new ContextAttribs(3, 2)
                .withForwardCompatible(true)
                .withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(RenderEngineConfig.WINDOW_WIDTH, RenderEngineConfig.WINDOW_HEIGHT));
            Display.create(new PixelFormat(), attrib);
            Display.setTitle(RenderEngineConfig.WINDOW_TITLE);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, RenderEngineConfig.WINDOW_WIDTH, RenderEngineConfig.WINDOW_HEIGHT);
        lastFrameTime = getCurrentTime();
    }

    public static void updateDisplay() {
        Display.sync(RenderEngineConfig.FPS_CAP);
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime) / 1000f;
        lastFrameTime = currentFrameTime;
    }

    public static void closeDisplay() {
        Display.destroy();
    }

    private static long getCurrentTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }

    public static float getFrameTimeSeconds() {
        return delta;
    }
}
