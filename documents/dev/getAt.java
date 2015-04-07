/////////////////////////////////////////////////////////////////////////
   public Point getUpperLeftCoordinates(Boat pBoat){
      if(pBoat.grid instanceof FleetGridSquare){
         return getUpperLeftCoordinatesSquare(Boat pBoat);
      }
      else{
         return getUpperLeftCoordinatesHexagon(Boat pBoat);
      }
   }
   
   public Point getCenterImg(Boat pBoat){
      if(pBoat.grid instanceof FleetGridSquare){
         return getCenterImgSquare(Boat pBoat);
      }
      else{
         return getCenterImgHexagon(Boat pBoat);
      }
   }
   
   public Point getFrontPositionImg(Boat pBoat){
      if(pBoat.grid instanceof FleetGridSquare){
         return getFrontPositionImgSquare(Boat pBoat);
      }
      else{
         return getFrontPositionImgHexagon(Boat pBoat);
      }
   }
///////////////////////////////////////////////////////////////////////////
   public Point getCenterImgSquare(Boat pBoat){
      //vertical boat
      if(pBoat.getOrientation()==1){
         return new Point(pBoat.getFrontPosition.getX()*40+20, 
                           pBoat.getFrontPosition.getY()*40+tabCompartment.length*40/2);
      }
      //horizontal boat
      else{
         return new Point(pBoat.getFrontPosition.getX()*40+tabCompartment.length*40/2, 
                           pBoat.getFrontPosition.getY(*40)+20);
      }
   }
   
   public Point getUpperLeftCoordinatesSquare(Boat pBoat){
      return new Point(pBoat.getFrontPosition.getX()*40, 
                       pBoat.getFrontPosition.getY()*40);
   }
   
   public Point getFrontPositionImgSquare(Boat pBoat){
      return new Point(pBoat.getFrontPosition.getX()*40+20, 
                       pBoat.getFrontPosition.getY()*40)+20;
   }
   
   ////////////////////////////////////////////////////////////////////////////////////////
   //This section is not up to date because I don't know how to calculate the coordinates//
   ////////////////////////////////////////////////////////////////////////////////////////
   public Point getCenterImgHexagon(Boat pBoat){
      //vertical boat
      if(pBoat.getOrientation()==1){
         return new Point(pBoat.getFrontPosition.getX()+20, 
                           pBoat.getFrontPosition.getY()+tabCompartment.length*40/2);
      }
      //horizontal boat
      else{
         //set to the right
         if(pBoat.getOrientation()==3){
            return new Point(pBoat.getFrontPosition.getX()+tabCompartment.length*40/2, 
                             pBoat.getFrontPosition.getY()+20);
         }
         //set to the left
         else{
            return new Point(pBoat.getFrontPosition.getX()-tabCompartment.length*40/2, 
                             pBoat.getFrontPosition.getY()+20);
         }
      }
   }
   
   public Point getUpperLeftCoordinatesHexagon(Boat pBoat){
      if(pBoat.getOrientation()==4){
         getFrontPosition en cas de bateau en diagonal vers la gauche
         retourne les coordonées de la boxmap en bas à gaueche?
         return new Point(??,??);
      }
      else{
         return new Point(pBoat.getFrontPosition.getX()*40, 
                          pBoat.getFrontPosition.getY()*40);
      }
   }