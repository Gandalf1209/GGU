package GGURaycaster.data.entity;

import GGURaycaster.render.Raycaster;
import game.universe.Universe;

import java.util.ArrayList;

public class EntityObject extends Entity{

    ArrayList<PositionElement> entities;
    double angle, size;
    boolean compile = true;

    public EntityObject(double x, double y, double z, double angle, double size) {
        super(null, x, y, z, 0, 0);
        this.angle = angle;
        this.size = size;
        this.entities = new ArrayList<PositionElement>();
    }

    //TODO remove universe call
    public void addStaticEntity(StaticEntity entity, double x, double y, double z){
        if(entity.getAdded() == true){
            Universe.getRaycaster().getEntityManager().remove(entity);
        }
        this.entities.add(new PositionElement(entity, true, x, y, z, entity.getAngle(), entity.getWidth(), entity.getHeight()));
        this.compile = true;
    }
    public void addEntity(Entity entity, double x, double y, double z){
        if(entity.getAdded() == true) {
            Universe.getRaycaster().getEntityManager().remove(entity);
        }
        this.entities.add(new PositionElement(entity, false, x, y, z, 0, entity.getWidth(), entity.getHeight()));
        this.compile = true;
    }

    public void compile(){
        for(PositionElement element : this.entities){

            Entity entity = element.getEntity();

            double xDif = ((element.getX() * Math.cos(angle)) + (-element.getZ() * Math.sin(angle))) * size;
            double zDif = ((element.getZ() * Math.cos(angle)) + (element.getX() * Math.sin(angle))) * size;
            double yDif = element.getY() * size;
            entity.setPosition(xDif + this.getX(), yDif + this.getY(), zDif + this.getZ());

            if(element.isStatic() == true) {
                StaticEntity staticEntity = (StaticEntity)entity;
                double newAngle = this.angle + element.getAngle();
                staticEntity.setAngle(newAngle);
            }

            double width = element.getWidth() * size;
            double height = element.getHeight() * size;

            entity.setWidth(width);
            entity.setHeight(height);

        }
    }

    public void setAngle(double angle){
        this.angle = angle;
        this.compile = true;
    }
    public double getAngle(){return this.angle;}
    public void setSize(double size){
        this.size = size;
        this.compile = true;
    }
    public double getSize(){return this.size;}
    @Override
    public void setPosition(double x, double y, double z){
        super.setPosition(x, y, z);
        this.compile = true;
    }

    @Override
    public void update(){
        if(compile == true){
            compile();
            this.compile = false;
        }
        this.setAngle(this.getAngle() + 0.01);
    }

    @Override
    public void render(Raycaster raycaster){
        for(PositionElement element : entities){

            Entity entity = element.getEntity();

            entity.update();
            entity.render(raycaster);

        }
    }

}
class PositionElement{

    Entity entity;
    boolean staticEntity;
    double x, y, z, angle, width, height;

    public PositionElement(Entity entity, boolean staticEntity, double x, double y, double z, double angle, double width, double height){
        this.entity = entity;
        this.staticEntity = staticEntity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.angle = angle;
        this.width = width;
        this.height = height;
    }
    public Entity getEntity(){
        return entity;
    }
    public boolean isStatic(){
        return this.staticEntity;
    }
    public double getX(){
        return this.x;
    }
    public double getY() {
        return y;
    }
    public double getZ() {
        return z;
    }
    public double getAngle(){
        return angle;
    }
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }

}