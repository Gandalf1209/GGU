package GGURaycaster.data.entity;

import GGURaycaster.render.Raycaster;

import java.awt.image.BufferedImage;

public class StaticEntity extends Entity{

    double angle;
    double x1, z1, x2, z2;

    double prevX, prevY, prevZ, prevAngle;

    public StaticEntity(BufferedImage render, double x, double y, double z, double width, double height, double angle) {
        super(render, x, y, z, width, height);
        setAngle(angle);
        syncPreviousPosition();;
        updateRenderPosition();
    }
    public StaticEntity(BufferedImage render,double width, double height, double angle) {
        super(render, 0, 0, 0, width, height);
        setAngle(angle);
        syncPreviousPosition();;
        updateRenderPosition();
    }

    @Override
    public void update(){
        super.update();

        if(prevX != this.getX() || prevY != this.getY() || prevZ != this.getZ() || prevAngle != this.getAngle()){
            this.updateRenderPosition();
            this.syncPreviousPosition();
        }
    }

    @Override
    public void render(Raycaster raycaster){
        raycaster.getRayRender().getEntityStaticRender().renderStationaryEntity(this);
        //raycaster.getRayRender().getEntityRender().renderEntity(this);
    }

    private void syncPreviousPosition(){
        prevX = this.getX();
        prevY = this.getY();
        prevZ = this.getZ();
        prevAngle = this.getAngle();
    }
    private void updateRenderPosition(){
        double xDiff = (this.getWidth() * Math.cos(this.getAngle())) / 2;
        double zDiff = (this.getWidth() * Math.sin(this.getAngle())) / 2;

        this.x1 = x - xDiff;
        this.x2 = x + xDiff;
        this.z1 = z - zDiff;
        this.z2 = z + zDiff;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    public double getAngle(){
        return angle;
    }
    public double getX1(){
        return x1;
    }
    public double getX2() {
        return x2;
    }
    public double getZ2() {
        return z2;
    }
    public double getZ1() {
        return z1;
    }
}
