package com.example.javagametestrun;

//Other parts of the android libraries that we use
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class TheGame extends GameThread{

    private BaseBall mBall = new BaseBall();
    private BaseBall mPaddle = new BaseBall(0,100,0,0);


    //Will store the image of the smiley ball (score ball)
    private BaseBall mSmileyBall = new BaseBall(-100,-100,10,0);

    //Will store the image of the smiley ball (score ball)
    private Bitmap mSadBall;

    //The X and Y position of the SadBalls on the screen
    //The point is the top left corner, not middle, of the balls
    //Initially at -100 to avoid them being drawn in 0,0 of the screen
    private float[] mSadBallX = {-100,-100,-100};
    private float[] mSadBallY = new float[3];

    //This will store the min distance allowed between a big ball and the small ball
    //This is used to check collisions
    private float mMinDistanceBetweenBallAndPaddle = 0;

    //This is run before anything else, so we can prepare things here
    public TheGame(GameView gameView) {
        //House keeping
        super(gameView);
Bitmap temp;
        //Prepare the image so we can draw it on the screen (using a canvas)
       temp = (Bitmap) BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                      R.drawable.small_red_ball);//  R.drawable.alien3);
        mBall.setbImage(Bitmap.createScaledBitmap(temp, 32, 32, false ));
        temp = null;
        //Prepare the image of the paddle so we can draw it on the screen (using a canvas)
        temp = BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                        R.drawable.starship);
                        // R.drawable.yellow_ball);

        mPaddle.setbImage(Bitmap.createScaledBitmap(temp, 150, 150, false ));
        temp = null;
        //Prepare the image of the SmileyBall so we can draw it on the screen (using a canvas)
        temp =  BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                        R.drawable.alien2);
        mSmileyBall.setbImage(Bitmap.createScaledBitmap(temp, 96, 128, false ));
        //Prepare the image of the SadBall(s) so we can draw it on the screen (using a canvas)

        temp = null;
        temp = BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                        R.drawable.alien);
        mSadBall = Bitmap.createScaledBitmap(temp, 128, 96, false );
        //ChangeLater? 
    }

    //This is run before a new game (also after an old game)
    @Override
    public void setupBeginning() {
        //Initialise speeds
        //mCanvasWidth and mCanvasHeight are declared and managed elsewhere
        mBall.setvXY( mCanvasWidth / 3, mCanvasHeight / 3);

        //Place the ball in the middle of the screen.
        //mBall.Width() and mBall.getHeight() gives us the height and width of the image of the ball
        mBall.setpXY(mCanvasWidth / 2, mCanvasHeight / 2);

        //Place Paddle in the middle of the screen
        mPaddle.setpX(mCanvasWidth / 2);
        mPaddle.setpY(mCanvasHeight - 100);

        //Place SmileyBall in the top middle of the screen
        mSmileyBall.setpX(mCanvasWidth / 2);
        mSmileyBall.setpY(mSmileyBall.getbImage().getHeight()/2);

        //Place all SadBalls forming a pyramid underneath the SmileyBall
        mSadBallX[0] = mCanvasWidth / 3;
        mSadBallY[0] = mCanvasHeight / 3;

        mSadBallX[1] = mCanvasWidth - mCanvasWidth / 3;
        mSadBallY[1] = mCanvasHeight / 3;

        mSadBallX[2] = mCanvasWidth / 2;
        mSadBallY[2] = mCanvasHeight / 5;

        //Get the minimum distance between a small ball and a bigball
        //We leave out the square root to limit the calculations of the program
        //Remember to do that when testing the distance as well
        mMinDistanceBetweenBallAndPaddle = (mPaddle.getbImage().getWidth() / 2 + mBall.getbImage().getWidth() / 2) * (mPaddle.getbImage().getWidth() / 2 + mBall.getbImage().getWidth() / 2);
    }

    @Override
    protected void doDraw(Canvas canvas) {
        //If there isn't a canvas to do nothing
        //It is ok not understanding what is happening here
        if(canvas == null) return;

        //House keeping
        super.doDraw(canvas);

        //canvas.drawBitmap(bitmap, x, y, paint) uses top/left corner of bitmap as 0,0
        //we use 0,0 in the middle of the bitmap, so negate half of the width and height of the ball to draw the ball as expected
        //A paint of null means that we will use the image without any extra features (called Paint)

        //draw the image of the ball using the X and Y of the ball
     //   canvas.drawBitmap(mBall.getbImage(), mBall.getpX() - (mBall.getbImage().getWidth() / 2), mBall.getpY() - (mBall.getbImage().getHeight() / 2), null);
        showBall(canvas,mBall);
        //Draw Paddle using X of paddle and the bottom of the screen (top/left is 0,0)
    //    canvas.drawBitmap(mPaddle.getbImage(), mPaddle.getpX() - mPaddle.getbImage().getWidth() / 2, mCanvasHeight - mPaddle.getbImage().getHeight() / 2, null);
        showBall(canvas,mPaddle);
        //Draw SmileyBall
      //  canvas.drawBitmap(mSmileyBall.getbImage(), mSmileyBall.getpX() - mSmileyBall.getbImage().getWidth() / 2, mSmileyBall.getpY() - mSmileyBall.getbImage().getHeight() / 2, null);
        showBall(canvas,mSmileyBall);
        //Loop through all SadBall
        for(int i = 0; i < mSadBallX.length; i++) {
            //Draw SadBall in position i
            canvas.drawBitmap(mSadBall, mSadBallX[i] - mSadBall.getWidth() / 2, mSadBallY[i] - mSadBall.getHeight() / 2, null);
        }
    }


    //This is run whenever the phone is touched by the user
    @Override
    protected void actionOnTouch(float x, float y) {
        //Move the ball to the x position of the touch
        mPaddle.setpXY(x,y);
    }

    //This is run whenever the phone moves around its axises
    @Override
    protected void actionWhenPhoneMoved(float xDirection, float yDirection, float zDirection) {
        //Change the paddle speed
         mPaddle.setvX(mPaddle.getvX() + 70f * xDirection);

        //If paddle is outside the screen and moving further away
        //Move it into the screen and set its speed to 0
        if( mPaddle.getpX() <= 0 &&  mPaddle.getvX() < 0) {
             mPaddle.setvX(0);
            mPaddle.setpX(0);
        }
        if( mPaddle.getpX() >= mCanvasWidth &&  mPaddle.getvX() > 0) {
             mPaddle.setvX(0);
            mPaddle.setpX(mCanvasWidth);
        }

    }

protected void showBall(Canvas canvas, BaseBall mToShow ){
    canvas.drawBitmap(mToShow.getbImage(), mToShow.getpX() - (mToShow.getbImage().getWidth() /2), mToShow.getpY() - (mToShow.getbImage().getHeight() / 2), null);
}

    //This is run just before the game "scenario" is printed on the screen
    @Override
    protected void updateGame(float secondsElapsed) {
        //If the ball moves down on the screen
        if(mBall.getvY() > 0) {
            //Check for a paddle collision
           // updateBallCollision( mPaddle.getpX(), mCanvasHeight);
            updateBallyCollision(mPaddle, mBall);
         //   updateBallCollision( mPaddle.getpX(), mPaddle.getpY());
        }
        mSmileyBall.updatePosition();

        //Move the ball's X and Y using the speed (pixel/sec)
        
        mBall.setpX(mBall.getpX() + secondsElapsed * mBall.getvX());
        mBall.setpY(mBall.getpY() + secondsElapsed * mBall.getvY());

        //Move the paddle's X and Y using the speed (pixel/sec)
        mPaddle.setpX( mPaddle.getpX() + secondsElapsed *  mPaddle.getvX());

    invertDirectionCheck(mSmileyBall);
    invertDirectionCheck(mBall);

        //Check if the ball hits either the left side or the right side of the screen
        //But only do something if the ball is moving towards that side of the screen
        //If it does that => change the direction of the ball in the X direction

        //Check for SmileyBall collision
        if(updateBallyCollision(mSmileyBall,mBall)){//updateBallCollision( mSmileyBall.getpX(), mSmileyBall.getpY())) {
            //Increase score
            updateScore(1);
        }

        //Loop through all SadBalls
        for(int i = 0; i < mSadBallX.length; i++) {
            //Perform collisions (if necessary) between SadBall in position i and the red ball
            updateBallCollision(mSadBallX[i], mSadBallY[i]);

        }

        //If the ball goes out of the top of the screen and moves towards the top of the screen =>
        //change the direction of the ball in the Y direction
        if(mBall.getpY() <= mBall.getbImage().getWidth() / 2 && mBall.getvY() < 0) {
            mBall.setvY(-mBall.getvY());
        }

        //If the ball goes out of the bottom of the screen => lose the game
        if(mBall.getpY() >= mCanvasHeight) {
            setState(GameThread.STATE_LOSE);
        }

    }

    private void invertDirectionCheck(AbstractBaseline aBall){
        if((aBall.getpX() <= aBall.getbImage().getWidth() / 2 && aBall.getvX() < 0) || (aBall.getpX() >= mCanvasWidth - aBall.getbImage().getWidth() / 2 && aBall.getvX() > 0) ) {
            aBall.setvX(-aBall.getvX());
        }}


        //Collision control between mBall and another big ball
    private boolean updateBallCollision(float x, float y) {
        //Get actual distance (without square root - remember?) between the mBall and the ball being checked
        float distanceBetweenBallAndPaddle = ((x - mBall.getpX()) * (x - mBall.getpX()) + (y - mBall.getpY()) *(y - mBall.getpY()));

        //Check if the actual distance is lower than the allowed => collision
        if(mMinDistanceBetweenBallAndPaddle >= distanceBetweenBallAndPaddle) {
            //Get the present speed (this should also be the speed going away after the collision)
            float speedOfBall = (float) Math.sqrt(mBall.getvX()*mBall.getvX() + mBall.getvY()*mBall.getvY());

            //Change the direction of the ball
            mBall.setvX(mBall.getpX() - x);
            mBall.setvY(mBall.getpY() - y);

            //Get the speed after the collision
            float newSpeedOfBall = (float) Math.sqrt(mBall.getvX()*mBall.getvX() + mBall.getvY()*mBall.getvY());

            //using the fraction between the original speed and present speed to calculate the needed
            //velocities in X and Y to get the original speed but with the new angle.
            mBall.setvX(mBall.getvX() * speedOfBall / newSpeedOfBall);
            mBall.setvY(mBall.getvY() * speedOfBall / newSpeedOfBall);

            return true;
        }

        return false;
    }

    private boolean updateBallyCollision(AbstractBaseline x, AbstractBaseline y) {
        //Get actual distance (without square root - remember?) between the mBall and the ball being checked
        float distanceBetweenBallAndPaddle = ((x.getpX() - y.getpX()) * (x.getpX() - y.getpX()) + (x.getpY() - y.getpY()) *(x.getpY() - y.getpY()));

        //Check if the actual distance is lower than the allowed => collision
        if(mMinDistanceBetweenBallAndPaddle >= distanceBetweenBallAndPaddle) {
            //Get the present speed (this should also be the speed going away after the collision)
            float speedOfBall = (float) Math.sqrt(y.getvX()*y.getvX() + y.getvY()*y.getvY());

            //Change the direction of the ball
            y.setvX(y.getpX() - x.getpX());
            y.setvY(y.getpY() - x.getpY());

            //Get the speed after the collision
            float newSpeedOfBall = (float) Math.sqrt(y.getvX()*y.getvX() + y.getvY()*y.getvY());

            //using the fraction between the original speed and present speed to calculate the needed
            //velocities in X and Y to get the original speed but with the new angle.
            y.setvX(y.getvX() * speedOfBall / newSpeedOfBall);
            y.setvY(y.getvY() * speedOfBall / newSpeedOfBall);

            return true;
        }

        return false;
    }

    //Collision control between mBall and another big ball
    private boolean updateBallyxCollision(AbstractBaseline aBall, AbstractBaseline bBall ) {
        //Get actual distance (without square root - remember?) between the mBall and the ball being checked
        float distanceBetweenBallAndbBall= ((bBall.getpX() - aBall.getpX()) * (bBall.getpX() - aBall.getpX()) + (bBall.getpY() - aBall.getpY()) *(bBall.getpY() - aBall.getpY()));

        //Check if the actual distance is lower than the allowed => collision
        if(mMinDistanceBetweenBallAndPaddle >= distanceBetweenBallAndbBall) {
            //Get the present speed (this should also be the speed going away after the collision)
            float speedOfBall = (float) Math.sqrt(aBall.getvX()*aBall.getvX() + aBall.getvY()*aBall.getvY());

            //Change the direction of the ball
            aBall.setvX(aBall.getpX() - bBall.getpX());
            aBall.setvY(aBall.getpY() - bBall.getpY());

            //Get the speed after the collision
            float newSpeedOfBall = (float) Math.sqrt(aBall.getvX()*aBall.getvX() + aBall.getvY()*aBall.getvY());

            //using the fraction between the original speed and present speed to calculate the needed
            //velocities in X and Y to get the original speed but with the new angle.
            aBall.setvX(aBall.getvX() * speedOfBall / newSpeedOfBall);
            aBall.setvY(aBall.getvY() * speedOfBall / newSpeedOfBall);

            return true;
        }

        return false;
    }
private float calcMinDistance(AbstractBaseline aBall, AbstractBaseline bBall){
    return ((aBall.getbImage().getWidth() / 2 + bBall.getbImage().getWidth() / 2) * (aBall.getbImage().getWidth() / 2 + bBall.getbImage().getWidth() / 2));
}
}

// This file is part of the course "Begin Programming: Build your first mobile game" from futurelearn.com
// Copyright: University of Reading and Karsten Lundqvist
// It is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// It is is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
//
// You should have received a copy of the GNU General Public License
// along with it.  If not, see <http://www.gnu.org/licenses/>.
