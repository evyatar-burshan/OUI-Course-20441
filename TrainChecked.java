import java.util.Scanner;

/**
 * The program Train calculates the distance between 2 trains after they travelled.
 */
public class TrainChecked {
    /**
     * Get speed and traveled time of the 2 trains
     * Calculate the traveled distance of the 2 trains separately
     * Calculate the distance between the 2 trains
     */  
    public static void main(String[] args) {
        
        final int NUMBER_OF_MINUTES_PER_HOUR = 60;
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Please enter 4 integers ");
        System.out.println("Please enter the speed of train 1:");
        
        int train1Speed = scan.nextInt();
        
        System.out.println("Please enter the time of train 1:");
        
        int train1TraveledDuration = scan.nextInt();
        
        System.out.println("Please enter the speed of train 2:");
        
        int train2Speed = scan.nextInt();
        
        System.out.println("Please enter the time of train 2:");
        
        int train2TraveledDuration = scan.nextInt();
        
        double train1TraveledDistance = train1Speed * ((double) train1TraveledDuration / NUMBER_OF_MINUTES_PER_HOUR);
        double train2TraveledDistance = train2Speed * ((double) train2TraveledDuration / NUMBER_OF_MINUTES_PER_HOUR);
        
        double distanceBetweenTrain1Train2 = Math.abs(train1TraveledDistance - train2TraveledDistance);
        
        System.out.println("The distance between the trains is " + distanceBetweenTrain1Train2 + " Km.");
    }
}

