package GGURaycaster.data.map;

import GGU.utility.data.GameData;

import java.awt.image.BufferedImage;

public class Tile {

    BufferedImage floor, ceiling;
    Wall top, bottom, left, right;

    int xCor, yCor;
    boolean solid = false;

    public Tile(boolean solid, int xCor, int yCor, BufferedImage ceiling, BufferedImage floor, BufferedImage top, BufferedImage bottom, BufferedImage left, BufferedImage right){

        this.top = new Wall(top);
        this.bottom = new Wall(bottom);
        this.left = new Wall(left);
        this.right = new Wall(right);
        this.floor = floor;
        this.ceiling = ceiling;

        this.solid = solid;
        this.xCor = xCor;
        this.yCor = yCor;
    }
/*
    public Wall getVertWall(int y){
        if(y == 1){
            return top;
        }
        if(y == -1){
            return bottom;
        }
        return null;
    }
    public Wall getHorizWall(double x){
        if(x == 0){
            return left;
        }
        if(x == 1){
            return right;
        }
        return null;
    }
    */

    public Wall getTop() {
        return top;
    }

    public Wall getBottom() {
        return bottom;
    }

    public Wall getLeft() {
        return left;
    }

    public Wall getRight() {
        return right;
    }

    public void setSolid(boolean solid){
        this.solid = solid;
    }

    public boolean isSolid(){
        return solid;
    }
    public BufferedImage getFloor(){
        return this.floor;
    }
    public BufferedImage getCeiling(){
        return this.ceiling;
    }
    public int getXCor(){
        return this.xCor;
    }
    public int getYCor(){
        return this.yCor;
    }
}
