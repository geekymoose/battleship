/* 
 * Creation : Mar 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.tools;

import com.battleship.asset.Config;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;





/**
 * <h1>Theme</h1>
 * <p>public class Theme</p>
 * <p>
 * Theme is a library of image used for graphic interface of an application. It 
 * load and save temporary image for all boats, texture, application etc. 
 * </p>
 * <p>
 * Theme use data as constants, image name to load and so one. These data are 
 * loaded from Config class (Which use constants xml file). See Config file 
 * for further information about constants used
 * </p>
 *
 * @since   Mar 14, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see Config
 */
public class Theme{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     String                  themeName;
    private     File                    themeFolder;
    
    private     ArrayList<String>       listMissingImg;
    private     ArrayList<String>       listPath;
    
    /**
     * Contains all image loaded The key is image constants name, THe value is 
     * an image loaded.<br/>
     * Key explanation : every image are saved (in theme folder) with a 
     * specific name. It represent a number xxxx. Thuz image name can be 
     * convert toward Integer value. (Obviously, this value is unique)
     * @HashMap Integer File
     */
    private     HashMap<Integer, Image> listImg;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Theme. Theme's name is given in parameter (Must be the actual 
     * theme folder name). If loading theme failed, return ExecError. Theme folder 
     * path is recovered from Config class
     * @param pTheme theme name
     * @throws ExecError thrown if unable load (Bad folder, missing img)
     * @see Config
     */
    public Theme(String pTheme) throws ExecError{
        DebugTrack.showInitMsg("Try to create new theme from : "+Config.getThemeFolderPath());
        try{
            this.themeName          = pTheme;
            this.themeFolder        = new File(Config.getThemeFolderPath()+pTheme);
            this.listImg            = new HashMap();
            this.listMissingImg     = new ArrayList();
            this.createPath();
            if(!this.themeFolder.isDirectory()){
                DebugTrack.showErrMsg(pTheme+" is not a directory!");
                throw new ExecError(502, pTheme);
            }
            this.loadTheme(); //Throw ExecError if unable to load
        } catch(NullPointerException ex){
            DebugTrack.showErrMsg("Null pointer");
            throw new ExecError(502);
    	}
    }
    
    
    /*
     * Create all path used by theme. It browse theme folder and create 
     * path.
     */
    private void createPath(){
        this.listPath = new ArrayList();
        this.addTree(this.themeFolder, this.listPath);
        DebugTrack.showInitData("Theme path", this.listPath);
    }
    
    /*
     * Add folder from the current directory to theme listPath
     * @param file current file position
     * @param all 
     */
    private void addTree(File file, ArrayList<String> pList) {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                if(child.isDirectory()){
                    pList.add(child.getPath()+"/");
                    this.addTree(child, pList);
                }
            }
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Load theme. Every image will be loaded and ready to be used. If theme 
     * cannot be loaded, thrown ExecError. It could happen if some image are 
     * missing, if some image have been renamed etc. <br/>
     * Image to load are read from file themeConstants.xml. This file save image 
     * name only, in order to load image, loadTheme function check if this image 
     * exists in one of the theme path (Initialized during the theme creation)
     * @throws ExecError thrown if unable to load theme
     */
    private void loadTheme() throws ExecError{
        //Load all static image and add in hashmap with image.
        HashMap<String, String> staticImg   = Config.getStaticImgNames();
        Iterator                it          = staticImg.entrySet().iterator();
        while(it.hasNext()){
            HashMap.Entry   pair        = (HashMap.Entry)it.next();
            String          imgName     = (String)pair.getKey();
            String          imgExt      = (String)pair.getValue();
            if(this.loadImage(imgName, imgExt) == -1){
                this.listMissingImg.add(imgName+"."+imgExt); //Unable to load image
            }
        }
        
        //load dynamic image
        HashMap<String, String> dynImg      = Config.getDynamicImgNames();
        Iterator                itdyn       = dynImg.entrySet().iterator();
        while (itdyn.hasNext()) {
            HashMap.Entry   pair        = (HashMap.Entry)itdyn.next();
            String          imgName     = (String)pair.getKey();
            String          imgExt      = (String)pair.getValue();
            if(this.loadEventImage(imgName, imgExt) == -1){
                this.listMissingImg.add(imgName+"."+imgExt);
            }
        }
        
        /*
         * After trying to load all image, if some are missing, theme is not loaded 
         * and a error is returned. Error message display name of missing files
         */
        if(!this.listMissingImg.isEmpty()){
            throw new ExecError(503, this.listMissingImg);
        }
    }
    
    
    /**
     * Load one image in the theme, it will be added in list of loaded image.
     * If unable to load this image, it will be added in the list of unloaded image.
     * @param path  where image to load is placed
     * @param name  image name (Without extension)
     * @param ext   image extension
     * @return 1 if loaded, otherwise, return -1
     */
    private int loadImage(String pName, String pExt){
        for(String path : this.listPath){
            try {
                Image img = ImageIO.read(new File(path+pName+"."+pExt));
                this.listImg.put(Integer.valueOf(pName), img);
                return 1;
            } catch(IOException ex) {
            }
        }
        return -1;
    }
    
    
    /**
     * Load an image event. Event is a succession of many image creating a 
     * dynamic event as a movement. The same animation could have different 
     * number image in theme1 and theme2, thus only the first event image is require, 
     * other one are loaded while there is one. (If 5 image are used, the first one 
     * is loaded and then, the 4 remaining but are not require). 
     * Event image are formated as a number sequence. <br/>
     * Example : thirst image is 1000, the next event image is 1001. 
     * Obviously, it means that a formated last numeric value must be not used 
     * by over image
     * @param path  where to find image
     * @param name  image name
     * @param ext   image extension
     * @return 1 if loaded, otherwise, return -1
     */
    private int loadEventImage(String pName, String pExt) throws ExecError{
        //First image has to be loaded!
        if(this.loadImage(pName, pExt) == -1){
            return -1;
        }
        int idNextImg = Integer.valueOf(pName);
        do{
            ++idNextImg;
        } while(this.loadImage(String.valueOf(idNextImg), pExt) != -1);
        return 1;
    }
    
    
    /**
     * Check if image exists. (Check from id image)
     * @param pImageId id of image to find
     * @return true if image exists, otherwise, return false
     */
    public boolean imgExists(int pImageId){
        Image img =  this.listImg.get(pImageId);
        return (img != null);
    }
    
    
    
    
    
    //**************************************************************************
    // Getters for image
    //**************************************************************************
    /**
     * Return image linked with pImageId value. If no image are linked with, 
     * return null and display JOptionPane warning message.<br/>
     * Note that a valid id should be given, if bad id given, no error will be 
     * thrown!
     * @param pImageId id value for image to display
     * @return image if exists, otherwise, return null
     */
    public Image getImg(int pImageId){
        Image img =  this.listImg.get(pImageId);
        if(img==null){
            DebugTrack.showErrMsg("Theme error : image id "+pImageId+" doens't exists!");
            UiDialog.showWarning("Unable to load image", "Image "+pImageId+" doesn't exists");
            return null;
        }
        return img;
        //return img.getScaledInstance(img.getWidth(null), -1, Image.SCALE_DEFAULT);
    }
    
    
    /**
     * Return imageIcon linked with pImageId value. If no image are linked with, 
     * return null and display JOptionPane warning message
     * @param pImageId id value for imageIcon to display
     * @return imageIcon if exists, otherwise, return null
     */
    public ImageIcon getImgIcon(int pImageId){
        Image img = this.getImg(pImageId);
        return (img == null) ? null : new ImageIcon(img);
    }
    
    
    /**
     * Return a Buffered image linked with pImageId value. If no image are linked with, 
     * return null
     * @param pImageId id of image to get
     * @return BufferedImage created
     */
    public BufferedImage getBufferedImg(int pImageId){
        Image img = this.getImg(pImageId);
        if(img == null){
            return null;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), 
                                                img.getHeight(null), 
                                                BufferedImage.TYPE_INT_ARGB);
        // Draw the image on to the buffered image
        Graphics2D g2 = bimage.createGraphics();
        g2.drawImage(img, 0, 0, null);
        g2.dispose();
        return bimage;
    }
    
    
    
    
    
    //**************************************************************************
    // Special getters for image
    //**************************************************************************
    /**
     * Return list of image used for dynamic event. ArrayList contains all 
     * event image, sorted. Parameter given is the first event image id, which 
     * is require, next image are automatically loaded (If event is composed with 
     * only one image, arrayList with only the first image is returned, in that 
     * case, it could be better using getImg)
     * @param pImageId first event image id
     * @return ArrayList with all event images, empty if no image
     */
    public ArrayList<Image> getDynamicImg(int pImageId){
        ArrayList<Image>    listStaticImg   = new ArrayList();
        Image               img             = this.listImg.get(pImageId);
        if(img == null){
            DebugTrack.showErrMsg("Theme error : image id "+pImageId+" doens't exists!");
            UiDialog.showWarning("Unable to load image", "Image "+pImageId+" doesn't exists");
        }else{
            listStaticImg.add(img);
            while((img = this.listImg.get(++pImageId)) != null){
                listStaticImg.add(img);
            }
        }
        return listStaticImg;
    }
    
    
    /**
     * Return list of imageIcon used for dynamic event. ArrayList contains all 
     * event image, sorted. Parameter given is the first event image id, which 
     * is require, next image are automatically loaded (If event is composed with 
     * only one image, arrayList with only the first image is returned, in that 
     * case, it could be better using getImg)
     * @param pImageId first event image id
     * @return ArrayList with all event imageIcon
     */
    public ArrayList<ImageIcon> getDynamicImgIcon(int pImageId){
        ArrayList<ImageIcon>    listDynamicImg  = new ArrayList();
        Image                   img             = this.listImg.get(pImageId);
        if(img==null){
            DebugTrack.showErrMsg("Theme error : image id "+pImageId+" doens't exists!");
            UiDialog.showWarning("Unable to load image", "Image "+pImageId+" doesn't exists");
        }
        else{
            listDynamicImg.add(new ImageIcon(img));
            while((img = this.listImg.get(++pImageId)) != null){
                listDynamicImg.add(new ImageIcon(img));
            }
        }
        return listDynamicImg;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return theme name
     * @return theme name
     */
    public String getThemeName(){
        return this.themeName;
    }
}