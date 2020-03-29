package com.example.javagametestrun;

import android.graphics.Bitmap;

public abstract class AbstractBaseline {

    private Bitmap bImage;                                          //Will store the image of a ball
    private float height = 64;
    private static final boolean alive = true;
    private static final boolean dead = false;
    private boolean living = alive;



    private float width = 64;
    //The X and Y position of the ball on the screen
    //The point is the top left corner, not middle, of the ball
    //Initially at -100 to avoid them being drawn in 0,0 of the screen
    private float pX;
    private float pY;
    //The speed (pixel/second) of the ball in direction X and Y
    private float vX;
    private float vY;

   AbstractBaseline(int ax, int ay, int bx, int by){
        pX = ax;
        pY = ay;
        vX = bx;
        vY = by;
    }
AbstractBaseline(){
   this(100,100,0,0);
}

protected void updatePosition(){
       this.setpXY(this.pX +this.vX, this.pY+this.vY);
}
























/*Getters & Setters*/
    public Bitmap getbImage() {
        return bImage;
    }
    public void setbImage(Bitmap bImage) {
        this.bImage = bImage;
    }
    public float getpX() {
        return pX;
    }
    public void setpX(float pX) {
        this.pX = pX;
    }
    public void setpXY(float pX, float pY){
        this.pX = pX;
        this.pY = pY;
    }
    public float getpY() {
        return pY;
    }
    public void setpY(float pY) {
        this.pY = pY;
    }
    public float getvX() {
        return vX;
    }
    public void setvX(float vX) {
        this.vX = vX;
    }
    public float getvY() {
        return vY;
    }
    public void setvY(float vY) {
        this.vY = vY;
    }
    public void setvXY(float vX, float vY){
        this.vX = vX;
        this.vY = vY;
    }
    public float getHeight() {
        return height;
    }
    public float getWidth() {
        return width;
    }
}
