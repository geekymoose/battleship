/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.behaviors;





/**
 * <h1>Sprite</h1>
 * <p>public interface Sprite</p>
 * 
 * <p>
 * A Sprite is a item which is placed on a BoxMap. It could be hit by a shot.
 * </p>
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * 
 * @see BoxMap
 */
public interface Sprite {
    public static int   NB_IMG                  = 10;
    
    public static int   IMG_0V_DEFAULT          = 0;
    public static int   IMG_1V_DESTROYE         = 1;
    public static int   IMG_2V_HOVER_DEF        = 2;
    public static int   IMG_4V_HOVER_INVALID    = 3;
    public static int   IMG_3V_HOVER_VALID      = 4;
    
    public static int   IMG_5H_DEFAULT          = 5;
    public static int   IMG_6H_DESTROYE         = 6;
    public static int   IMG_7H_HOVER_DEF        = 7;
    public static int   IMG_9H_HOVER_INVALID    = 8;
    public static int   IMG_8H_HOVER_VALID      = 9;
    
    /**
     * Hit sprite by a shot.
     * @return true if sprite hit successfully, otherwise, return false
     */
    public boolean hit();
    
    /**
     * Check if this sprite could be hit (For example, this sprite could have
     * already been hit.
     * @return true if can be hit, otherwise, return false
     */
    public boolean canBeHit();
    
    /**
     * Return the Sprite image identification. Image should be Loaded in an array. 
     * Sprite constants are linked with image position in this array. idImg should 
     * be a Sprite constants and automatically return image needed
     * @param idImg image to return (Use Sprite constants)
     * @return id image 
     */
    public int getImgId(int idImg);
    
    /**
     * Set all image id. If unknown value is given, return default image
     * @param pDef          default image
     * @param pDestroyed    destroyed image
     * @param pHover        hover image
     * @param pValid        valid image
     * @param pNovalid      not valid
     */
    public void setImg(int pDef, int pDestroyed, int pHover, int pNovalid, int pValid);
    
    /**
     * Set all image id for hidden mode. If unknown value is given, return default image
     * @param pDef          default image
     * @param pDestroyed    destroyed image
     * @param pHover        hover image
     * @param pValid        valid image
     * @param pNovalid      not valid
     */
    public void setHiddenImg(int pDef, int pDestroyed, int pHover, int pNovalid, int pValid);
    
    public int getId();
}
