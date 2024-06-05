
/**
 * Write a description of class Country here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Country
{
    CityNode _head;
    String _name;
    
    /**
     * Constructor method for country setting up the country's name.
     * 
     * @param name Specifies what the name of the country is.
     */
    public Country (String name) {
        _name = name;
    }
    
    /**
     * Adds a city to a country if the city is not already in the country
     * 
     * @param Specifies what the name of the city is.
     * @param dayEstablished Specifies what day the city was established.
     * @param monthEstablished Specifies what month the city was established.
     * @param yearEstablished Specifies what year the city was established.
     * @param centerX Specifies what is the X of the city center.
     * @param centerY Specifies what is the Y of the city center.
     * @param stationX Specifies what is the X of the central station.
     * @param stationY Specifies what is the Y of the central station.
     * @param numOfResidents Specifies the number of residents in the city.
     * @param noOfNeighborhoods Specifies the number of neighboorhoods in the city.
     * 
     * @return true if the city was added, false if it wasn't
     */
    public boolean addCity (String cityName, int dayEstablished, int monthEstablished, int yearEstablished, int centerX, int centerY, int stationX, int stationY, long numOfResidents, int noOfNeighborhoods) {
        City tmpCity = new City (cityName, dayEstablished, monthEstablished, yearEstablished, centerX, centerY, stationX, stationY, numOfResidents, noOfNeighborhoods);
        CityNode tmpNode = new CityNode(tmpCity);
        
        //If country is empty set the new city as the first one (_head)
        if (empty()) { 
            _head = new CityNode(tmpCity, null);
            return true;
        }
 
        CityNode i = _head;
        CityNode prev = null;
        Date tmpDate = tmpCity.getDateEstablished();
        
        //If the city already exists don't add it
        while (i != null) {
            if (i.getCity().getCityName().equals(tmpCity.getCityName())) {
                return false;
            }
            i = i.getNext();
        }

        i = _head;
        
        //Add the city in the right place chronologically
        if (i.getCity().getDateEstablished().after(tmpCity.getDateEstablished())) {
            tmpNode.setNext(_head);
            _head = tmpNode;
            return true;
                } 
                
        //If there is already a city established in that date add them in alphabetical order       
        if (i.getCity().getDateEstablished().equals(tmpCity.getDateEstablished())
            && i.getCity().getCityName().compareTo(tmpCity.getCityName()) > 0) {
                tmpNode.setNext(_head);
                _head = tmpNode;
                return true;
        }
        
        //Add the city in the right place chronologically
        while (i.getNext() != null && i.getNext().getCity().getDateEstablished().before(tmpCity.getDateEstablished())) {
                  i = i.getNext();
        }
        
        //If there is already a city established in that date add them in alphabetical order
        while (i.getNext() != null && i.getNext().getCity().getDateEstablished().equals(tmpCity.getDateEstablished()) && i.getNext().getCity().getCityName().compareTo(tmpCity.getCityName()) < 0) {
                  i = i.getNext();
        } 
        
        tmpNode.setNext(i.getNext());
        i.setNext(tmpNode);
        return true;
    }
    
    /**
     * Returns the number of residents in a country if it is not empty.
     * 
     * @return The number of residents in a country.
     */
    public long getNumOfResidents() {
        if (empty()) {
            return 0;
        }
        
        long total = 0;
        CityNode i = _head;
        while (i != null) {
            total += i.getCity().getNumOfResidents();
            i = i.getNext();
        }
        
        return total;
    }
    
    /**
     * Returns the longest distance between two cities city centers in a country if it has more than 2 cities.
     * 
     * @return the longest distance between two cities city centers in a country.
     */
    public double longestDistance() {
        if (empty()) {
            return 0;
        }
        if (_head.getNext() == null) {
            return 0;
        }
        
        double longestDis = 0;
        CityNode i = _head;
        
        //Iterate over the country's cities
        while (i.getNext() != null) {
          CityNode j = i.getNext();
          
          //Iterate over the country's cities
          while (j != null) {
              //Check if the distance between city i and city j is the longest
            if (j.getCity().getCityCenter().distance(i.getCity().getCityCenter()) > longestDis) {
                longestDis = j.getCity().getCityCenter().distance(i.getCity().getCityCenter());
            }
            j = j.getNext();
          } 
          
          i = i.getNext();
          j = i.getNext();
        } 
        
        return longestDis;
    }
    
    /**
     * Returns the number of cities north of a city in a country if that city exists and the country is not empty.
     * 
     * @param cityName The name of the city we're looking for cities north of.
     * 
     * @return -1 if the country is empty or the city was not found. Otherwise, returns the number of cities that's north of the city specified.
     */
    public int numCitiesNorthOf(String cityName) {
        if (empty()) {
            return -1;
        }
        
        CityNode i = _head;
        Point originalCityCenter = null;
        
        //Find the given city
        while (i != null) {
            if (cityName == i.getCity().getCityName()) {
                originalCityCenter = i.getCity().getCityCenter();
                break;
            }
            i = i.getNext();
        }
        
        //Check why we left the while loop and return -1 if it is because the city was not found
        if (i.getNext() == null && cityName != i.getCity().getCityName()) {
            return -1;
        }
        
        int total = 0;
        i = _head;
        
        //Iterate over the country
        while (i != null) {
            Point curCityCenter = i.getCity().getCityCenter();
            
            //Check if the city is north of the given city
            if (curCityCenter.isAbove(originalCityCenter)) {
                total++;
            }
            i = i.getNext();
        }
        
        return total;
    }
    
    /**
     * Returns the southernmost city in a country if the country is not empty.
     * 
     * @return null if the country is empty. Otherwise, returns a copy of the southernmost city.
     */
    public City southernmostCity() {
        if (empty()) {
            return null;
        }
        
        CityNode i = _head;
        City southernmostCity = i.getCity();
        Point southernmostCityCenter = i.getCity().getCityCenter();
        
        //Iterate over the country
        while (i != null) {
            Point curCityCenter = i.getCity().getCityCenter();
            
            //Check if the current city is south to the current southernmost city
            if (curCityCenter.isUnder(southernmostCityCenter)) {
                southernmostCityCenter = curCityCenter;
                southernmostCity = i.getCity();
            }
            
            //Check if the current city is at the same height and set the first one to be established as the southernmost
            else if (curCityCenter.getY() == southernmostCityCenter.getY() && i.getCity().getDateEstablished().before(southernmostCity.getDateEstablished())) {
                southernmostCityCenter = curCityCenter;
                southernmostCity = i.getCity();
            }
            i = i.getNext();
        }
        
        return new City (southernmostCity);
    }
    
    /**
     * Returns the name of the country.
     * 
     * @return The name of the country.
     */
    public String getCountryName () {
        return _name;
    }
    
    /**
     * Returns the number of cities in a country.
     * 
     * @return The number of cities in a country.
     */
    public int getNumOfCities() {
        if (empty()) {
            return 0;
        }
        
        CityNode i = _head;
        int numCities = 0;
        
        while (i != null) {
            numCities++;
            i = i.getNext();
        }
        
        return numCities;
    }
    
    /**
     * Calculates whether there is a city in a country were established between two given dates.
     * 
     * @param firstDate The first date the cities need to be established between.
     * @param secondDate The second date the cities need to be established between.
     * 
     * @return true if all cities were established between the dates, false if otherwise.
     */
    public boolean wereCitiesEstablishedBeforeOrAfter (Date firstDate, Date secondDate) {
        
        CityNode i = _head;
        
        //If the first date is before the second one check if there is a city established after it and before the other
        if (firstDate.before(secondDate)) {
            while (i != null) {
                if (i.getCity().getDateEstablished().before(firstDate) ||
                    i.getCity().getDateEstablished().after(secondDate)) {
                        return true;
                    }
                i = i.getNext();
            }
        }
        else {
            while (i != null) {
                if (i.getCity().getDateEstablished().after(firstDate) ||
                    i.getCity().getDateEstablished().before(secondDate)) {
                        return true;
                    }
                i = i.getNext();
            }
        }
        
        return false;
    }
    
    /**
     * Unifies two cities into one city and keeps the early one between the two. Unification is by: combining both names, early establishment date between the two
     * sum of residents, sum of neighboorhoods, middle of two city centers, west of the two central stations (early of the two if they are the same).
     * 
     * @param firstCityName The name of the first city to unify.
     * @param secondCityName The name of the second city to unify.
     * 
     * @return Returns a copy of the unified city.
     */
    public City unifyCities (String firstCityName, String secondCityName) {
        CityNode i = _head;
        CityNode firstCity = null;
        CityNode secondCity = null;
        
        //Find the first and second cities
        while (i != null) {
            if (firstCityName == i.getCity().getCityName()) {
                firstCity = i;
            }
            if (secondCityName == i.getCity().getCityName()) {
                secondCity = i;
            }
            i = i.getNext();
        }
        
        //Set the first city to be the first established city
        if (firstCity.getCity().getDateEstablished().after(secondCity.getCity().getDateEstablished())) {
            CityNode tmpCity = firstCity;
            firstCity = secondCity;
            secondCity = tmpCity;
        }
        
        String newName = firstCity.getCity().getCityName() + "-" + secondCity.getCity().getCityName();
        long newResidentCount = firstCity.getCity().getNumOfResidents() + secondCity.getCity().getNumOfResidents();
        int newNeighboorhoodCount = firstCity.getCity().getNumOfNeighborhoods() + secondCity.getCity().getNumOfNeighborhoods();
        Point newCenter = firstCity.getCity().getCityCenter().middle(secondCity.getCity().getCityCenter());
        Point newStation = null;
        
        //Check which Central Station is the farthest west
        if (firstCity.getCity().getCentralStation().isLeft(secondCity.getCity().getCentralStation())) {
            newStation = firstCity.getCity().getCentralStation();
        }
        else if (secondCity.getCity().getCentralStation().isLeft(firstCity.getCity().getCentralStation())) {
            newStation = secondCity.getCity().getCentralStation();
        }
        else if (firstCity.getCity().getCentralStation().getX() == secondCity.getCity().getCentralStation().getX()) {
            newStation = secondCity.getCity().getCentralStation();
        }
        
        City newCity = new City(newName, firstCity.getCity().getDateEstablished().getDay(), firstCity.getCity().getDateEstablished().getMonth(), firstCity.getCity().getDateEstablished().getYear(), newCenter.getX(), newCenter.getY(), newStation.getX(), newStation.getY(), newResidentCount, newNeighboorhoodCount);
        firstCity.setCity(newCity);
        
        //Remove the second city because it was unified
        i = _head;
        while (i != null) {
            if (i.getNext() == secondCity) {
                i.setNext(secondCity.getNext());
            }
            i = i.getNext();
        }
        
        return newCity;
    }
    
    /**
     * Returns The largest difference between two cities establishment dates in a country. If the country is empty or has less than 2 cities return -1 or 0 accordingly.
     * 
     * @return -1 if the country is empty. 0 if the country has only 1 city. Otherwise, the largest difference between two cities establishment dates in a country
     */
    public int establishMaxDiff() {
        if (empty()) {
            return -1;
        }
        if (_head.getNext() == null) {
            return 0;
        }
          
        CityNode i = _head;
        CityNode minCityI = i;
        CityNode maxCityI = i.getNext();
        
        //Iterate over the countrty
        while (i != null) {
          if (i.getCity().getDateEstablished().before(minCityI.getCity().getDateEstablished())) {
              minCityI = i;
          }
          if (i.getCity().getDateEstablished().after(maxCityI.getCity().getDateEstablished())) {
              maxCityI = i;
          }
          i = i.getNext();
        } 
    
        return minCityI.getCity().getDateEstablished().difference(maxCityI.getCity().getDateEstablished());
    }
    
    /**
     * Returns a String representation of the country that is easier to read.
     * 
     * @return returns a String representation of the country that is easier to read.
     */
    public String toString() {
        if (empty()) {
            return "There are no cities in this country.";
        }
        String str = "Cities of " + _name + ":\n\n";
        CityNode i = _head;
        while (i != null) {
            str += i.getCity().toString() + "\n\n";
            i = i.getNext();
        }
        return str;
    }
    
    //Checks whether a country is empty
    private boolean empty() {
        if (_head == null) {
            return true;
        }
        return false;
    }
}
