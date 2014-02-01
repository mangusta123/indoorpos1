package com.example.filedialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AnimatedView extends ImageView{
        private Context mContext;
        
        Canvas cnv;
        
        static int lock=0;
        
        
        static int xscrll = 0;           //initial values (upper left corner of the map)
        static int yscrll = 0;
     
        private Handler h;
        private final int FRAME_RATE = 20;
        
        static int xMax = TestMapActivity.metrics.widthPixels;
        static int yMax = TestMapActivity.metrics.heightPixels;
        
        int tmpxMax = xMax/2; 
        int tmpyMax = yMax/2;
                
        public AnimatedView(Context context, AttributeSet attrs)  {
                super(context, attrs);
                mContext = context;
                h = new Handler();
      }
         private Runnable r = new Runnable() {
                         	     
                 public void run() {
                  	 
                    invalidate();
                                   
                 }//end run
         };
         protected void onDraw(Canvas c) {
                   	 
        	 BitmapDrawable buildg = (BitmapDrawable) Drawable.createFromPath("/sdcard/"+FileDialogFragment.mChosenMapFile);
             BitmapDrawable ball   = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.redcircle);
                                                  	          	   
             c.drawBitmap(buildg.getBitmap(), 0, 0, null);
        
           
          while(lock==1){}     
                    
          lock = 1; //receiver may want to modify these scrollings as well
          scrollTo(xscrll, yscrll);
          
          c.drawBitmap(ball.getBitmap(), tmpxMax+xscrll, tmpyMax+yscrll, null);  //always drawn in the canvas center

          
      /*    if(xscrll<1470)
         {
          xscrll+=20;
          c.drawBitmap(ball.getBitmap(), tmpxMax+xscrll-20-20, tmpyMax+yscrll, null);  //always drawn in the canvas center

         }
       
    else {	            
          yscrll+=20;
          c.drawBitmap(ball.getBitmap(), tmpxMax+xscrll, tmpyMax+yscrll-20-20, null);  //always drawn in the canvas center

         }
      */    
          lock = 0;
        	
          buildg.getBitmap().recycle();
                    
          h.postDelayed(r, FRAME_RATE);
        	         	       	 
      }
}