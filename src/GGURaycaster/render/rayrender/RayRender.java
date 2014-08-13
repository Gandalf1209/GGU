package GGURaycaster.render.rayrender;

import GGURaycaster.render.RayIntersect;
import GGURaycaster.render.Raycaster;
import GGURaycaster.render.data.Camera;

public class RayRender {

    Raycaster raycaster;

    WallRender wallRender;
    FloorRender floorRender;
    EntityRender entityRender;
    EntityStaticRender entityStaticRender;

    public RayRender(Raycaster raycaster){

        this.raycaster = raycaster;

        wallRender = new WallRender(raycaster);
        floorRender = new FloorRender(raycaster);
        entityRender = new EntityRender(raycaster);
        entityStaticRender = new EntityStaticRender(raycaster);

    }

    public void update(){
        entityRender.updateAngle(raycaster.getCamera().getAngle());
        entityStaticRender.updateAngle(raycaster.getCamera().getAngle());
        Shader.setViewDistance(raycaster.getCamera().getViewDistance());
    }
    public void renderAll(){

        Camera camera = this.raycaster.getCamera();

        double x = camera.getX();
        double z = camera.getZ();

        double width = raycaster.getWidth();

        for(int i = 0; i < width; i++){

            double grad = ((double)i)/(width/2) - 1;
            double angle = Math.atan(grad/camera.getProjectionDistance()) + camera.getAngle();

            RayIntersect ray = raycaster.getWallfinder().fireRay(x, z, angle);

            if(ray == null){
                floorRender.renderPlanes(i, (raycaster.getHeight() / 2) - 1, (raycaster.getHeight() / 2) + 1);
                continue;
            }

            double distance = ray.getDistance() * Math.cos(angle - camera.getAngle());
            renderScreenSlice(ray, distance, i);

        }



    }

    public void renderScreenSlice(RayIntersect ray, double distance, int x){

        int[] pos = wallRender.projectWall(ray, ray.getWall().getWall(), distance, x);
        floorRender.renderPlanes(x, pos[0], pos[1]);

    }

    public WallRender getWallRender(){  return wallRender; }
    public FloorRender getFloorRender(){
        return floorRender;
    }
    public EntityRender getEntityRender(){
        return entityRender;
    }
    public EntityStaticRender getEntityStaticRender() { return entityStaticRender; };

}
