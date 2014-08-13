package GGURaycaster.data.map;

import java.awt.image.BufferedImage;

/**
 *
 */

public class Map {

    Tile[][] tile;
    int width, height;

    boolean hasCeiling;
    double ceilingHeight;

    public Map(int width, int height){

        this.width = width;
        this.height = height;
        this.tile = new Tile[width][height];
        this.hasCeiling = true;
        this.ceilingHeight = 1;

    }

    public Tile getTile(double x, double y){
        int tileX = (int)x;
        int tileY = (int)y;

        if(tileX < 0 || tileX >= width || tileY < 0 || tileY >= height){
            return null;
        }

        return tile[tileX][tileY];
    }

    public void initialiseTile(int xCor, int yCor, boolean solid, BufferedImage ceiling, BufferedImage floor, BufferedImage top, BufferedImage bottom, BufferedImage left, BufferedImage right){
        tile[xCor][yCor] = new Tile(solid, xCor, yCor, ceiling, floor, top, bottom, left, right);
    }
    public void setCeiling(boolean ceiling){
        this.hasCeiling = ceiling;
    }
    public void setCeilingHeight(double ceiling){
        this.ceilingHeight = ceiling;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public boolean hasCeiling(){
        return hasCeiling;
    }
    public double getCeilingHeight(){
        return this.ceilingHeight;
    }
}
