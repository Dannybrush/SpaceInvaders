//package com.example.javagametestrun;
//package RestartJava;
//
////GameDrone - MAIN DRONE
////ExtensionDrone1
///**
// * @author Br016005
// * The main Drone used;
// * inherits from BaseDrone;
// * Red;
// * Moves, Bounces;
// */
//public class Ex1Drone extends BaseDrone{											// Inherits From Parent "BaseDrone"
//    double bAngle, bSpeed ;
//
//
//
//    /**
//     * Overloaded (x5) Constructor
//     * @param ix - Initial XCoordinate
//     * @param iy - Initial YCoordinate
//     * @param ir - Initial Radius
//     * @param ia - Initial Angle
//     * @param is - Initial Speed
//     */
//    public Ex1Drone(double ix, double iy, double ir, double ia, double is) {
//
//        super(ix, iy, ir);																// SuperClass taken from
//        System.out.println(ix +" "+ iy +" "+ir );										// Debugging and testing purposes
//        bAngle = ia;
//        bSpeed = is;
//    }
//    /**Overloaded (x3) Constructor
//     * Calls the overloaded x5 Constructor with preset values of ia =45, is = 10;
//     * ia = angle, is = speed;
//     * @param ix - Initial XCoordinate
//     * @param iy - Initial YCoordinate
//     * @param ir - Initial Radius
//     */
//    public Ex1Drone(double ix, double iy, double ir){
//        this(ix, iy, ir, 45,10);
//    }
//
//    /**Default Constructor
//     * calls overloaded (x5) constructor
//     *  ix - 20 - Initial XCoordinate
//     *  iy - 20 - Initial YCoordinate
//     *  ir - 10 - Initial Radius
//     *  ia - 45 - Initial Angle
//     *  is - 10 - Initial Speed
//     *
//     */
//    public Ex1Drone(){
//        this(20.0, 20.0, 10.0, 45.0, 10.0);
//    }
//    /**OverRidden Inherited Abstract method
//     * used to check drones and change the angle
//     *  @see RestartJava.BaseDrone#checkDrone(RestartJava.DroneColosseum)
//     */
//
//    @Override
//    protected void checkDrone(DroneColosseum b) {
//        bAngle = b.CheckDroneAngle(x, y, rad, bAngle, DroneID);
//    }
//    /** OverRidden Inherited Abstract method
//     * used to adjust drones angle, and position based off the speed
//     * @see RestartJava.BaseDrone#adjustDrone()
//     */
//
//    @Override
//    protected void adjustDrone() {
//        double radAngle = bAngle*Math.PI/180;												// put angle in radians
//        x += bSpeed * Math.cos(radAngle);													// new X position
//        y += bSpeed * Math.sin(radAngle);													// new Y position
//    }
//
//    /** Returns "Main Drone" as the string for the type of drone
//     * @see RestartJava.BaseDrone#getStrType()
//     */
//    protected String getStrType() {
//        return "Main Drone";
//    }
//}
