package renderEngine;

public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/renderEngine/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/renderEngine/shaders/fragmentShader.txt";

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes(0, "position");
    }
}
