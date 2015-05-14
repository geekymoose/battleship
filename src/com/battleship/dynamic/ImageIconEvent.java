/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 * 
 * 
 * IMPORTANT NOTE
 * Event with image work directly with image name. For example, from constructor, 
 * pFirst is the name of the first image to display. CurrentImg the current image 
 * to display.
 * In order to display event, actionPerformed increment the currentImg value by 
 * one. It is important to understand that currentImg is also the name of this img!!! 
 * It is working only if img have a numeric! (Some update will maybe add name 
 * with ABC characters, but later)
 * 
 * 
 */
package com.battleship.dynamic;
import com.battleship.asset.ThemeManager;
import com.battleship.views.tools.UiElement;
import java.util.ArrayList;
import javax.swing.ImageIcon;



/**
 * <h1>ImageIconEvent</h1>
 * <p>
 * public class ImageIconEvent<br/>
 * extends EventApp<br/>
 * implements UiElement
 * </p>
 * 
 * <p>ImageIconEvent is an event with specific ImageIcon state element to display. 
 * It extends EventApp and implements UiElement</p>
 * 
 * 
 * @since   Mar 29, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see EventApp
 */
public class ImageIconEvent extends EventApp implements UiElement{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create an ImageIconEvent
     * 
     * @param pDelay        Delay to apply at this event
     * @param pFirst        position of the first state (Usually 0)
     * @param pEventType    set event type (LOOP_EVENT or STATIC_EVENT)
     * @param pCont         Where event is apply
     */
    public ImageIconEvent(int pDelay, int pFirst, int pEventType, UiEventApp pCont){
        super(pDelay,pFirst, pEventType, pCont);
        this.loadUI();
    }

    @Override
    public void loadUI(){
        this.reloadUI();
    }
    
    @Override
    public void reloadUI(){
        ArrayList<ImageIcon> list;
        list = ThemeManager.getTheme().getDynamicImgIcon(000/* Add img*/);
        super.setListStates(list);
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return current event image
     * @return ImageIcon to display at the moment
     */
    public ImageIcon getImgIcon(){
        return ((ImageIcon)super.getCurrentState());
    }
    
    /**
     * Return first event image
     * @return ImageIcon
     */
    public ImageIcon getFirstIcon(){
        return ((ImageIcon)super.getFirstState());
    }
}
