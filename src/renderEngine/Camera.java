package renderEngine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

    private static final int LEFT_BUTTON_MOUSE = 0;
    private static final int RIGHT_BUTTON_MOUSE = 0;

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    private static final float SPEED = 0.2f;

    public Camera() {
    }

    public void move() {
        calculatePitch();
        calculateYaw();

        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            position.z -= SPEED;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.z += SPEED;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.x += SPEED;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            position.x -= SPEED;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            position.y += SPEED;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            position.y -= SPEED;
        }
    }

    private void calculatePitch() {
        if (Mouse.isButtonDown(RIGHT_BUTTON_MOUSE)) {
            float pitchChange = Mouse.getDY() * 0.2f;
            pitch -= pitchChange;
        }
    }

    private void calculateYaw() {
        if (Mouse.isButtonDown(LEFT_BUTTON_MOUSE)) {
            float angleChange = Mouse.getDX() * 0.2f;
            yaw += angleChange;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
