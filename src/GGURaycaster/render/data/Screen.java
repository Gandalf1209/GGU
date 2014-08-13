package GGURaycaster.render.data;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;

/**
 * Created by Admin on 11/07/2014.
 */
public class Screen {

    int width, height;
    int size;
    ByteBuffer data;
    double[][] depthBuffer;

    public Screen(int width, int height){
        this.width = width;
        this.height = height;

        this.size = width * height * 4;
        this.data = BufferUtils.createByteBuffer(size);
        this.depthBuffer = new double[width][height];

        clearScreen();
    }

    public void clearScreen(){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){

                setPixel(x, y, 0xFF000000, -1);

            }
        }
    }

    public void setPixel(int x, int y, int colour, double distance){

        if(x < 0 || x >= width || y < 0 || y >= height){
            return;
        }

        if(isNearer(x, y, distance) == false){
            return;
        }

        //int position = ((x * height) + y)*4;
        int position = ((y * width) + x)*4;

        byte alpha = (byte)((colour>>24) & 0xFF);
        byte red = (byte)((colour>>16) & 0xFF);
        byte green = (byte)((colour>>8) & 0xFF);
        byte blue = (byte)(colour & 0xFF);

        this.data.put(position, red);
        this.data.put(position + 1, green);
        this.data.put(position + 2, blue);
        this.data.put(position + 3, alpha);

        this.depthBuffer[x][y] = distance;

    }
    public boolean isNearer(int x, int y, double distance){
        if(distance < this.depthBuffer[x][y] || this.depthBuffer[x][y] < 0){
            return true;
        }
        return false;
    }
    public int[] getOnScreen(double[] point){
        return new int[]{
                (int)(((double)getWidth()/2) * point[0]) + getWidth()/2,
                (int)(((double)getHeight()/2) * point[1]) + getHeight()/2
        };
    }
    public double getDepth(int x, int y){
        return this.depthBuffer[x][y];
    }
    public ByteBuffer getData(){
        return data;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }

}
