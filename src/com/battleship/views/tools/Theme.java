/* 
 * Creation : Mar 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.tools;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;





/**
 * <h1>Theme</h1>
 * <p>public class Theme</p>
 * <p>
 * Theme is a library of image used for graphic interface of an application. It 
 * load and save temporary image for all boats, texture, application etc. 
 * </p>
 *
 * @date    Mar 14, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class Theme{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     String              path;
    private     String              name;
    private     File                theme;
    
    /*
     * Constants path for images 
     * Theme folder respect a spefific hierarchic. This constants are created 
     * from this.
     */
    private     final String        dirBoats            = "boats/";
    private     final String        dirAircraftCarrier  = dirBoats+"aircraft_carrier/";
    private     final String        dirBattleship       = dirBoats+"battleship/";
    private     final String        dirSubmarine        = dirBoats+"submarine/";
    private     final String        dirCruiser          = dirBoats+"cruiser/";
    private     final String        dirDestroyer        = dirBoats+"destroyer/";
    
    private     final String        dirUi               = "ui/";
    
    /**
     * Contains all image loaded The key is image constants name, THe value is 
     * an image loaded.<br/>
     * Key explanation : every image are saved (in theme folder) with a 
     * specific name. It represent a number xxxx. Thuz image name can be 
     * convert toward Integer value. (Obviously, this value is unique)
     * @HashMap Integer File 
     */
    private     HashMap<Integer, Image>     listImg;
    private     ArrayList<String>           listMissingImg;
    
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Theme. Theme's name is given in parameter (Must be the axtual 
     * theme folder name). If loading theme failed, return ExecError
     * @param pPath     theme position (Absolute or relative path)
     * @param pTheme    theme name
     * @throws ExecError thrown if unable load (Bad folder, missing img)
     */
    public Theme(String pPath, String pTheme) throws ExecError{
        DebugTrack.showInitMsg("Try to create new theme from : "+pPath+pTheme);
        try{
            this.theme              = new File(pPath+pTheme); //Could throw Exception
            this.name               = pTheme;
            this.path               = pPath+pTheme+"/";
            this.listImg            = new HashMap();
            this.listMissingImg     = new ArrayList();
            if(!this.theme.isDirectory()){
                DebugTrack.showErrMsg(pTheme+"is not a directory!");
                throw new ExecError(502, pTheme);
            }
            this.loadTheme(); //Throw ExecError if unable to load
        } 
        catch(NullPointerException ex){
            DebugTrack.showErrMsg("Unable to load theme, null pointer : "+ex.getMessage());
            throw new ExecError(502);
    	}
        catch(ExecError ex){
            DebugTrack.showErrMsg("Unable to load theme, missing files");
            throw new ExecError(503, this.listMissingImg);
        }
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Load theme. Every image will be loaded into and ready to be used. If theme 
     * cannot be loaded, thrown ExecError. It could happen if some image are 
     * missing, if some image have been renamed etc
     * @throws ExecError thrown if unable to load theme
     */
    private void loadTheme() throws ExecError{
        //Load destroyer img
        String pathDestroyer = this.path+this.dirDestroyer;
        this.loadImage(pathDestroyer, "0342.png");
        this.loadImage(pathDestroyer, "3784.png");
        
        if(!this.listMissingImg.isEmpty()){
            throw new ExecError();
        }
    }
    
    /**
     * Load one image in the theme, it will be added in list of loaded image.
     * If unable to load this image, img will be 
     * added in the list of unloaded image.
     * @param path where image to load is placed
     * @param name image name
     * @return img if loaded, otherwise, return null
     * @throws ExecError 
     */
    private void loadImage(String path, String name) throws ExecError{
        try {
            Image img = ImageIO.read(new File(path+name));
            this.listImg.put(0342, img);
        } catch(IOException ex) {
            this.listMissingImg.add(name);
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return this theme's name
     * @return name
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Get an specific image. Parameter is the image id (See html documentation 
     * for further informations). If this id doesn't exists, throw ExecError 
     * exception
     * @param pImageId image id 
     * @return Image
     * @throws ExecError
     */
    public Image getImg(int pImageId) throws ExecError{
        Image img =  this.listImg.get(pImageId);
        if(img==null){
            throw new ExecError();
        }
        return img;
    }
}