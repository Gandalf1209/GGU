package GGU.utility.data.loaders.image;

import GGU.debug.Debug;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Utility class for anything to do with loading images or image data
 *
 * Use: used by a lot of things, including image list and OpenGLTexture, which requires the byte data of an image
 */
public class ImageLoader {

    public static BufferedImage getBufferedImage(String path){
        try{
            return ImageIO.read(new File(path));
        }catch(IOException e){
            Debug.error(e.getMessage() + " --- Error when reading '" + path + "'");
        }
        return null;
    }
    public static ByteBuffer getByteBuffer(BufferedImage image){

        int[] pixelArray = new int[image.getWidth()*image.getHeight()];

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                pixelArray[(y*image.getWidth()) + x] = image.getRGB(x, y);
            }
        }

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4);

        for(int i = 0; i < pixelArray.length; i++){

            int pix = pixelArray[i];

            buffer.put((byte)((pix >> 16) & 0xFF));
            buffer.put((byte)((pix >> 8) & 0xFF));
            buffer.put((byte)(pix & 0xFF));

            buffer.put((byte)((pix >> 24) & 0xFF));

        }

        buffer.flip();

        return buffer;

    }
}
