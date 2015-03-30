/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.tools;

import com.battleship.uibutton.*;





/**
 * <h1>UiFactory</h1>
 * <p>public abstract class UiFactory</p>
 * <p>
 * Manage all UI element created for application
 * </p>
 *
 * @date    Mar 27, 2015
 * @author  Constantin MASSON
 */
public abstract class UiFactory {
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private static UiClickedAnimButton      butt0   = null;
    private static UiMessageButton          butt1   = null;
    private static UiClickedAnimButton      butt2   = null;
    private static UiImgButton              butt3   = null;
    private static UiClickedAnimButton      butt4   = null;
    private static UiMessageButton          butt5   = null;
    private static UiClickedAnimButton      butt6   = null;
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    
    
    public static UiClickedAnimButton loadButt6(){
        if(UiFactory.butt6 != null){
            return UiFactory.butt6;
        }
        else{
            UiClickedAnimButton b = new UiClickedAnimButton(
                    new UiMessageButton("ClickAnim", 0, 105001, 0, 0, 0, 0));
            return b;
        }
    }
    
    
    public static UiMessageButton loadButt5(){
        if(UiFactory.butt5 != null){
            return UiFactory.butt5;
        }
        else{
            UiMessageButton b = new UiMessageButton("Nothing");
            return b;
        }
    }
    
    
    public static UiClickedAnimButton loadButt4(){
        if(UiFactory.butt4 != null){
            return UiFactory.butt0;
        }
        else{
            UiClickedAnimButton b = new UiClickedAnimButton(
                                        new UiMessageButton("ErrorAnim"));
            return b;
        }
    }
    
    
    public static UiClickedAnimButton loadButt0(){
        if(UiFactory.butt0 != null){
            return UiFactory.butt0;
        }
        else{
            UiClickedAnimButton b = new UiClickedAnimButton(
                                        new UiMessageButton("Anim+default", 105001, 105001, 105008, 105011, 105010, 105001));
            return b;
        }
    }
    
    
    public static UiMessageButton loadButt1(){
        if(UiFactory.butt1 != null){
            return UiFactory.butt1;
        }
        else{
            UiMessageButton b = new UiMessageButton("Txt+Background", 105001, 105001, 105008, 105011, 105010, 105001);
            return b;
        }
    }
    
    
    public static UiClickedAnimButton loadButt2(){
        if(UiFactory.butt2 != null){
            return UiFactory.butt2;
        }
        else{
            UiClickedAnimButton b = new UiClickedAnimButton(
                                        new UiImgButton(105001, 105001, 105008, 105011, 105010, 105001));
            return b;
        }
    }
    
    
    public static UiImgButton loadButt3(){
        if(UiFactory.butt3 != null){
            return UiFactory.butt3;
        }
        else{
            UiImgButton b = new UiImgButton(105001, 105005, 105008, 105011, 105010, 105001);
            return b;
        }
    }
}
