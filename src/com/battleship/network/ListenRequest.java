/* 
 * Creation : 5 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.network;

/**
 * @since   5 mai 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ListenRequest implements Runnable {
    //**************************************************************************
    // 
    //**************************************************************************
    private     Thread          t;
    private     Network         nc;
    private     boolean         flag = true;

    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ListenRequest(Network nc) {
        t = new Thread(this, "ListenMessage");
        this.nc = nc;
        t.start();
    }
    
    public void run() {
        do {
            try {
                Capsule capsuleReceived = (Capsule) nc.getInput().readObject();
                nc.execRequest(capsuleReceived);
                if (capsuleReceived.getRequest() == Request.DISCONNECT) {
                    flag = false;
                }
            } catch (Exception e) {
            
            }

        } while (flag);
    }

}
