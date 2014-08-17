package GGURaycaster.data.entity;

import GGURaycaster.render.Raycaster;
import game.universe.Universe;

import java.awt.image.BufferedImage;

public class Entity {

    BufferedImage render;
    double x, y, z;
    double width, height;
    boolean added, alphaEnabled;

    public Entity(BufferedImage render, double x, double y, double z, double width, double height){
        this.render = render;
        this.added = false;
        this.alphaEnabled = true;
        setPosition(x, y, z);
        setWidth(width);
        setHeight(height);
    }

    public void update(){}
    public void render(Raycaster raycaster){
        raycaster.getRayRender().getEntityRender().renderEntity(this);
    }

    //Getters & setters
    public boolean isAlphaEnabled(){
        return this.alphaEnabled;
    }
    public void setAlphaEnabled(boolean alphaEnabled){
        this.alphaEnabled = alphaEnabled;
    }

    public void setAdded(boolean added){
        this.added = added;
    }
    public boolean getAdded(){
        return added;
    }

    public BufferedImage getRender() {
        return render;
    }
    public void setRender(BufferedImage render) {
        this.render = render;
    }

    public void setPosition(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
}
