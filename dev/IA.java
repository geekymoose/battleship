public class IA extends Joueur{

   protected BoxMap[] lastShot;
   
   public IA(int pGridWidth, int pGridHeight, int pGridType){
      super();
      this.setName("CPU");
      this.initFleetGrid(pGridWidth, pGridHeight, pGridType);
      this.lastShot=BoxMap[2];
      lastShot[0] = new Water();
      lastShot[1] = new Water();
   }
   
   public boolean playOneRoundIA{
      if(lastShot[0] instanceof Water){
         this.randomShot();
      } else {
         if(lastShot[1] instanceof Water){
            //boxAliveAround() a definir dans la class BoxMap
            BoxMap[] tab = lastShot[0].boxAliveAround();
            k = Mat.random()*tab.length();
            tab[k].hit();
            lastShot[1] = lastShot[0];
            lastShot[0] = tab[k];
         } else {
            BoxMap box = this.possibleShot(lastShot[0], lastShot[1]);
            if(box==null/* || box already hit */){
               this.randomShot();
            } else {
               box.hit();
               if(box instanceof Water){
                  tmp = lastshot[0];
                  lastShot[0] = lastShot[1];
                  lastShot[1] = tmp;
               } else {
                  lastShot[1] = lastShot[0];
                  lastShot[0] = tab[k];
               }
            }
         }
      }
   }
   
   public void randomShot(){
      x = Math.random*10;
      y = Math.random*10;
      fleetGrid[y][x].hit();
      lastShot[1] = lastShot[0];
      lastShot[0] = fleetGrid[y][x];
   }
   
   public BoxMap possibleShot(BoxMap pBox1, BoxMAp pBox2){
      if(fleetGrid instanceof FleetGridSquare){
         return this.possibleShotSquare(pBox1, pBox2);
      } else {
         return this.possibleShotHexagonal(pBox1, pBox2);
      }
   }
   
   public BoxMap possibleShotSquare(BoxMap pBox1, BoxMAp pBox2){
      int diffX = pBox1.getPosX()-pBox2.getPosX();
      int diffY = pBox1.getPosY()-pBox2.getPosY();
      if(Math.abs(diffX)==1){
         if(diffX>0){
            return this.fleetGrid[pBox1.getPosY()][pBox1.getPosX()+1];
         }
         else{
            return this.fleetGrid[pBox1.getPosY()][pBox1.getPosX()-1];
         }
      }
      if(Math.abs(diffY)==1){
         if(diffY>0){
            return this.fleetGrid[pBox1.getPosY()+1][pBox1.getPosX()];
         }
         else{
            return this.fleetGrid[pBox1.getPosY()-1][pBox1.getPosX()];
         }
      }
      return null;
   }
   
   public BoxMap possibleShotHexagonal(BoxMap pBox1, BoxMAp pBox2){
      int diffX = pBox1.getPosX()-pBox2.getPosX();
      int diffY = pBox1.getPosY()-pBox2.getPosY();
      if(Math.abs(diffX)==1){
         //BoxMap2 diagonale sur la gauche par rapport a BoxMap1
         if(diffX>0){
            if(pBox1.getPosX()==0){
               if(diffY==0){
                  return this.fleetGrid[Y][X-1];
               } else {
                  return this.fleetGrid[Y+1][X-1];
               }
            } else {
               if(diffY==0){
                  return this.fleetGrid[Y-1][X-1];
               } else {
                  return this.fleetGrid[Y][X-1];
               }
            }
         }
         //BoxMap2 diagonale sur la droite par rapport a BoxMap1
         else{
            if(pBox1.getPosX()==0){
               if(diffY==0){
                  return this.fleetGrid[Y][X+1];
               } else {
                  return this.fleetGrid[Y+1][X+1];
               }
            } else {
               if(diffY==0){
                  return this.fleetGrid[Y-1][X+1];
               } else {
                  return this.fleetGrid[Y][X+1];
               }
            }
         }
      } else {
         if(Math.abs(diffY)==1){
            if(diffY>0){
               return this.fleetGrid[pBox1.getPosY()+1][pBox1.getPosX()];
            }
            else{
               return this.fleetGrid[pBox1.getPosY()-1][pBox1.getPosX()];
            }
         }
      }
      return null;
  }
}