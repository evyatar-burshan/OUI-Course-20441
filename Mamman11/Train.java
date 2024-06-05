import java.util.Scanner;

/*
 * This program takes 4 inputs from the user: velocity and time of train 1 & velocity and time of train 2.
 * Afterwards, it calculates the distance between the two trains and prints it to the user.
 */
class Train {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // The following 5 lines request input for the speed and time of train 1
        System.out.println("Please enter 4 integers ");
        System.out.println("Please enter the speed of train 1:");
        int train1Velocity = scan.nextInt();
        System.out.println("Please enter the time of train 1:");
        int train1Time = scan.nextInt();
        double newTrain1Time = (double)train1Time/60; // Transfering the time from minutes to hours
        double train1Distance = newTrain1Time*train1Velocity; // Calculating the distance train 1 passed

        // The following 4 lines request input for the speed and time of train 1
        System.out.println("Please enter the speed of train 2:");
        int train2Velocity = scan.nextInt();
        System.out.println("Please enter the time of train 2:");
        int train2Time = scan.nextInt();
        double newTrain2Time = (double)train2Time/60; // Transfering the time from minutes to hours
        double train2Distance = newTrain2Time*train2Velocity; // Calculating the distance train 2 passed
        double distanceBetweenTrains = train2Distance-train1Distance; // subtracting the distance of the trains from each other to know how far away they are from each other
        distanceBetweenTrains = Math.abs(distanceBetweenTrains); // making the distance positive so it will be "legal"

        // The following lines output the results to the user.
        System.out.print("The distance between the trains is ");
        System.out.print(distanceBetweenTrains);
        System.out.println(" km.");

    } // end of method main
} // end of class Train
