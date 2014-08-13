package GGURaycaster.render.data;

public class Camera {

    //Projection values
    double width, height;
    double projectionDistance;
    double FOV;
    double aspectRatio;

    //Position in 3D world
    double x, y, z;
    double angle;
    double viewDistance;

    public Camera(double width, double height, double FOV, double viewDistance){

        this.width = width;
        this.height = height;
        this.FOV = FOV;
        this.aspectRatio = width/height;
        this.viewDistance = viewDistance;

        setY(0.5);

        calculateProjectionDistance();
    }

    public void calculateProjectionDistance(){
        this.projectionDistance = width / (Math.tan(FOV/2));
    }

    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public double getProjectionDistance() {
        return projectionDistance;
    }
    public double getFOV() {
        return FOV;
    }
    public double getAspectRatio(){
        return this.aspectRatio;
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public double getZ() { return this.z; }

    public void setAngle(double angle){
        this.angle = angle;
    }
    public double getAngle(){
        return this.angle;
    }

    public double getViewDistance() {
        return viewDistance;
    }
    public void setViewDistance(double viewDistance) {
        this.viewDistance = viewDistance;
    }


}
