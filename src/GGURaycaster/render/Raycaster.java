package GGURaycaster.render;

import GGU.utility.config.Configuration;
import GGU.utility.config.ConfigurationLoader;
import GGU.utility.data.opengl.OpenGLTexture;
import GGU.utility.display.OpenGLHandler;
import GGU.utility.display.OpenGLUtility;
import GGU.utility.math.TrigTable;
import GGURaycaster.RaycasterConfiguration;
import GGURaycaster.data.entity.EntityManager;
import GGURaycaster.data.map.Map;
import GGURaycaster.render.data.Camera;
import GGURaycaster.render.data.Screen;
import GGURaycaster.render.raycaster.WallFinder;
import GGURaycaster.render.rayrender.RayRender;
import sun.security.krb5.Config;

import java.util.Random;

public class Raycaster {

    RaycasterConfiguration configuration;
    int width, height, trigPrecision;

    Screen screen;
    OpenGLTexture rendered;
    Camera camera;
    Map map;
    WallFinder wallfinder;
    RayRender rayRender;
    TrigTable table;
    EntityManager entityManager;

    public static Random random;

    public Raycaster(String configurationPath){

        ConfigurationLoader.setConfigurationPaths(configurationPath + "_default", configurationPath);
        this.configuration = ((RaycasterConfiguration)ConfigurationLoader.getConfiguration(new RaycasterConfiguration()).getVariables());
        this.loadConstants(this.configuration);

        this.table = new TrigTable(trigPrecision);
        this.screen = new Screen(width, height);
        this.rendered = new OpenGLTexture(screen.getData(), width, height);
        this.camera = new Camera(1, OpenGLHandler.getHeight(), Math.PI/2, 4);
        this.rayRender = new RayRender(this);
        this.random = new Random();
        this.entityManager = new EntityManager();

    }
    public void loadConstants(RaycasterConfiguration config){
        this.width = configuration.RESOLUTION_X;
        this.height = configuration.RESOLUTION_Y;
        this.trigPrecision = configuration.TRIGPRECISION;
    }

    public OpenGLTexture render(){
        if(map == null){
            return null;
        }
        screen.clearScreen();
        rayRender.update();
        rayRender.renderAll();
        entityManager.update();
        entityManager.render(this);
        OpenGLUtility.updateTexture(this.rendered.getID(), this.screen.getData(), this.screen.getWidth(), this.screen.getHeight());
        return rendered;
    }

    public void setMap(Map map){
        this.map = map;
        this.wallfinder = new WallFinder(this);
    }

    public Screen getBuffer(){ return screen; }
    public Camera getCamera(){
        return camera;
    }
    public Map getMap(){ return map; }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public WallFinder getWallfinder() {
        return wallfinder;
    }
    public RayRender getRayRender(){
        return rayRender;
    }
    public TrigTable getTable(){
        return this.table;
    }
    public EntityManager getEntityManager(){
        return entityManager;
    }
}
