/* 
 * Creation : 29 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.uibutton;



/**
 * <h1>ImgButton</h1>
 * <p>
 * public class ImgButton<br/>
 * extends UiButton
 * </p>
 * <p>
 * A button with basic image for default, click, pressed, released, entered and 
 * exited. Could be decorated with a UiButtonDecorator. See this class 
 * for further informations.
 * </p>
 *
 * @since   Mar 29, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see UiButtonDecorator
 */
public class ImgButton extends UiButton{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create an UiImgButton with a unique image
     * @param pDef image to display
     */
    public ImgButton(int pDef){
        super(pDef);
        this.reloadUI();
    }
    
    
    /**
     * Create a UiImgButton with mouse hover image event. 
     * @param pDef      Default image
     * @param pPress    Pressed image
     * @param pHover    Mouse hover image
     */
    public ImgButton(int pDef, int pPress, int pHover){
        super(pDef, pPress, pHover);
        this.reloadUI();
    }
    
    
    /**
     * Create a new Simple button with basic images for default, clicked, pressed 
     * released, entered and exited
     * @param pDef      default image
     * @param pClick    clicked image
     * @param pPress    pressed image
     * @param pRe       released image
     * @param pEnt      entered image
     * @param pEx       exited image
     */
    public ImgButton(int pDef, int pClick, int pPress, int pRe, int pEnt, int pEx){
        super(pDef, pClick, pPress, pRe, pEnt, pEx);
        this.reloadUI();
    }
    
    
    @Override
    public void resetButton(){
        this.setIcon(img_default);
    }
}
