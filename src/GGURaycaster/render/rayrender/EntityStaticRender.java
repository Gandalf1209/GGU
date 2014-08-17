package GGURaycaster.render.rayrender;

import GGU.utility.math.Geometry;
import GGU.utility.math.LookupTable;
import GGURaycaster.data.entity.StaticEntity;
import GGURaycaster.render.Raycaster;
import GGURaycaster.render.data.Camera;
import GGURaycaster.render.data.Screen;

import java.awt.image.BufferedImage;

//TODO Optimise this class

public class EntityStaticRender {

    Raycaster raycaster;
    LookupTable table;

    double sin, cos;
    double clip = 0.2;

    public EntityStaticRender(Raycaster raycaster){
        this.raycaster = raycaster;
        this.table = raycaster.getTable();
        this.clip = raycaster.getConfig().STATIC_CLIP;
    }

    public void updateAngle(double angle){
        this.sin = table.sin(angle);
        this.cos = table.cos(angle);
    }


    public void renderStationaryEntity(StaticEntity entity){

        Camera camera = raycaster.getCamera();
        Screen screen = raycaster.getBuffer();

        double x0 = 0, x1 = 1;

        double[] rot1 = Geometry.rotatePoint(entity.getX1(), entity.getZ1(), camera.getX(), camera.getZ(), this.sin, this.cos);
        double[] rot2 = Geometry.rotatePoint(entity.getX2(), entity.getZ2(), camera.getX(), camera.getZ(), this.sin, this.cos);

        if(-rot1[1] < clip && -rot2[1] < clip){
            return;
        }
        if(-rot1[1] < clip){
            rot1 = this.clipPoint(rot1[0], rot1[1], rot2[0], rot2[1], -clip);
            x0 = 1 - rot1[2];
        }else{
            if(-rot2[1] < clip){
                rot2 = this.clipPoint(rot2[0], rot2[1], rot1[0], rot1[1], -clip);
                x1 = rot2[2];
            }
        }

        double[] point = transformPoint(camera, rot1[0], entity.getY() + entity.getHeight()/2, rot1[1]);
        double[] point2 = transformPoint(camera, rot2[0], entity.getY() + entity.getHeight()/2, rot2[1]);
        double[] point3 = new double[]{point2[0], point2[1] + ((entity.getHeight() / point2[2]) * camera.getAspectRatio()), point2[2]};
        double[] point4 = new double[]{point[0],  point[1]  + ((entity.getHeight()  / point[2]) * camera.getAspectRatio()), point[2]};

        if(point[0] < -1 && point2[0] < -1){
            return;
        }
        if(point[0] > 1 && point2[0] > 1){
            return;
        }

        int[] p1 = screen.getOnScreen(point);
        int[] p2 = screen.getOnScreen(point2);
        int[] p3 = screen.getOnScreen(point3);
        int[] p4 = screen.getOnScreen(point4);

        renderWall(p1, p2, p3, p4, point2[2] - point[2], point[2], point2[2], x0, x1, entity.getRender(), entity.isAlphaEnabled());

    }

    public void renderWall(int[] p1, int[] p2, int[] p3, int[] p4, double zDiff, double z0, double z1, double x0, double x1, BufferedImage image, boolean alphaEnabled){

        Screen screen = raycaster.getBuffer();

        double topYDiff = p2[1] - p1[1];
        double topXDiff = p2[0] - p1[0];

        double topGrad = topYDiff / topXDiff;

        double bottomYDif = p3[1] - p4[1];
        double bottomXDif = p3[0] - p4[0];

        double bottomGrad = bottomYDif / bottomXDif;

        int pX = p1[0];
        if(pX < 0){
            pX = 0;
        }
        int pX2 = p2[0];
        if(pX2 > screen.getWidth()){
            pX2 = screen.getWidth();
        }

        for(double xPos = pX; xPos < pX2; xPos++){

            double xPoint = xPos - p1[0];

            int y1 = p1[1] + (int)(xPoint * topGrad);
            int y2 = p4[1] + (int)(xPoint * bottomGrad);

            int pY1 = y1;
            int pY2 = y2;

            if(pY1 < 0){
                pY1 = 0;
            }
            if(pY2 > screen.getHeight()){
                pY2 = screen.getHeight();
            }

            double xGrad = xPoint / topXDiff;
            double xTexGrad = ((1 - xGrad) * x0) + (xGrad * x1);
            double zTexInterp = getXTexture(xTexGrad, z0, z1);
            int xTex = (int)(zTexInterp * ((double)image.getWidth()));

            double z = (zTexInterp * zDiff) + z0;

            double yDiff = pY2 - pY1;
            double yTexGrad = ((double)image.getHeight()) / (y2 - y1);

            for(int y = 0; y < yDiff; y++){

                int yTex = (int)(((double)y + (pY1 - y1)) * yTexGrad);

                int xPix = (int)xPos;
                int yPix = y + pY1;

                int tex = image.getRGB(xTex, yTex);

                int colour = 0;

                if(alphaEnabled == true) {
                    int alpha = (tex >> 24) & 0xFF;
                    colour = Shader.getColourBlend(Shader.getColour(tex, z), xPix, yPix, screen, table.alpha(alpha), this.table);
                }else{
                    colour = Shader.getColour(image.getRGB(xTex, yTex), z);
                }

                if(colour == 0){
                    continue;
                }

                screen.setPixel(xPix, yPix, colour, z);
            }

        }

    }

    //Perspective texture mapping
    public double getXTexture(double x, double z0, double z1){

        double negative = 1 - x;
        double a1z = x * (1 / z1);
        double na0z = negative * (1 / z0);

        return (a1z / (na0z + a1z));

        //return x;

    }
    public double[] transformPoint(Camera camera, double x, double y, double z){

        double newX = (x / -z) * camera.getProjectionDistance();
        double newY = ((y - camera.getY()) / z) * camera.getAspectRatio();
        double newZ = -z;

        return new double[]{newX, newY, newZ};

    }
    public double[] clipPoint(double x, double z, double refX, double refZ, double clip){

        double difX = x - refX;
        double difZ = z - refZ;

        double newDifZ = clip - refZ;
        double newDifX = (difX / difZ) * newDifZ;

        double newX = newDifX + refX;
        double newY = newDifZ + refZ;

        return new double[]{newX, newY, newDifX / difX};
    }

}
