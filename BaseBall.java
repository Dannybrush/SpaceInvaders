package com.example.javagametestrun;

import android.graphics.Bitmap;

public class BaseBall extends AbstractBaseline{
    BaseBall(){
       this(-100,-100,0,0);
    }
    BaseBall(int ax, int ay, int bx, int by, Bitmap aImg){ }
    BaseBall(int ax, int ay, int bx, int by){
        super(ax, ay, bx, by);
    }

// protected void setbImage(Bitmap bImage){

 //}
}
