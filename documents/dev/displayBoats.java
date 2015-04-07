public class displayBoats implements ObservableModel, UiElements {
   
   public displayBoats(){
   
   }
   
   public void placeBoat(Boat pBoat){
      int 			orientation = pBoat.getOrientation();
      BufferedImg 	img 		= ThemeManager.getTheme().getImg(int??);
      Point 		center 		= pBoat.getCenterImg();
      int 			h 			= img.getHeight();
      int 			w 			= img.getWidth();
      Point 		placeAt 	= new Point(center-(w/2), center-(h/2));
      //PaintComponent?
      this.loadUI();
   }
   
   @Override
    public void loadUI(){
        this.reloadUI();
    }


    @Override
    public void reloadUI(){
        for(int k=0; k<Sprite.NB_IMG; k++){
            this.img[k] = ThemeManager.getTheme().getImg(this.sprite.getImgId(k));
        }
    }
    
}