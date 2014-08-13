package GGU.utility.data;

import GGU.utility.OutputUtility;
import GGU.utility.data.fonts.GameFontRender;
import GGU.utility.data.loaders.animation.AnimationList;
import GGU.utility.data.loaders.audio.AudioList;
import GGU.utility.data.loaders.image.ImageList;
import GGU.utility.data.loaders.spritesheet.SpriteSheetList;
import GGU.utility.data.loaders.texture.TextureLoader;

/**
 * Universally needed GameData
 *
 * Acts as an access point for needed images, audio, spritesheets and animations for anywhere in the program after the running state has been initialised;
 */
public class GameData {

    static ImageList image_list;
    static AudioList audio_list;
    static SpriteSheetList sheet_list;
    static AnimationList animation_list;

    public static void intialise(){

        image_list = new ImageList();
        audio_list = new AudioList();
        sheet_list = new SpriteSheetList();
        animation_list = new AnimationList();

    }
    public static void loadData(){

        OutputUtility.outputLine("-Loading Resources-");
        OutputUtility.increment();
        OutputUtility.outputLine("-Loading Images-");
        OutputUtility.increment();

        image_list.load();

        OutputUtility.deincrement();
        OutputUtility.outputLine("-Loading Audio-");
        OutputUtility.increment();

        audio_list.load();

        OutputUtility.deincrement();
        OutputUtility.deincrement();

        TextureLoader.load(image_list);

        sheet_list.load();
        animation_list.load();

        GameFontRender.load();
    }
    public static ImageList getImageList(){
        return image_list;
    }
    public static AudioList getAudioList(){
        return audio_list;
    }
    public static SpriteSheetList getSpriteSheetList(){
        return sheet_list;
    }
    public static AnimationList getAnimationList(){
        return animation_list;
    }

}
