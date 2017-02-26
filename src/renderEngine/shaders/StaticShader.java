package renderEngine.shaders;

import org.lwjgl.util.vector.Matrix4f;
import renderEngine.Camera;
import renderEngine.lights.Light;
import utils.Maths;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/renderEngine/shaders/shader/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/renderEngine/shaders/shader/fragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;

    private int location_lightPosition;
    private int location_lightColor;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");

        location_lightPosition = super.getUniformLocation("lightPosition");
        location_lightColor = super.getUniformLocation("lightColor");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes(0, "position");
        super.bindAttributes(1, "textureCoords");
        super.bindAttributes(2, "normal");
    }

    public void loadTransformationMatrix(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        super.loadMatrix(location_projectionMatrix, matrix);
    }

    public void loadViewMatrix(Camera camera) {
        super.loadMatrix(location_viewMatrix, Maths.createViewMatrix(camera));
    }

    public void loadLight(Light light) {
        super.loadVector(location_lightColor, light.getColor());
        super.loadVector(location_lightPosition, light.getPosition());
    }
}
