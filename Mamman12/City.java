/**
 * Represents a city.
 * City is represented by its name, the date it was established, center, central station,
 * number of residents(non negative) and number of neighborhoods(positive).
**/
public class City
{
    private String _cityName;
    private Date _dateEstablished;
    private Point _cityCenter;
    private Point _centralStation;
    private long _numOfResidents;
    private int _numOfNeighborhoods;
    
    /**Constructor of a city. Constructs a new city with name, specified establishment date, x y coordinates of city center, x y coordinates of central station, number of residents (non negative, if negative will be set to 0), number of neighborhoods (positive, if less than 1 will be set to 1.).**/
    public City (String cityName, int dayEstablished, int monthEstablished, int yearEstablished, int cityCenterX, int cityCenterY, int centralStationX, int centralStationY, long numOfResidents, int numOfNeighborhoods) {
        _cityName = cityName;
        _dateEstablished = new Date (dayEstablished, monthEstablished, yearEstablished);
        _cityCenter = new Point (cityCenterX, cityCenterY);
        _centralStation = new Point (centralStationX, centralStationY);
        //Checking the vadility of the residents and neighborhoods input.
        if (numOfResidents >= 0) {
        _numOfResidents = numOfResidents;
        }
        else {
            _numOfResidents = 0;
        }
        if (numOfNeighborhoods > 0) {
        _numOfNeighborhoods = numOfNeighborhoods;
        }
        else {
            _numOfNeighborhoods = 1;
        }
    }
    
    /**Copy constructor for cities. Construct a city with the same attributes as another city.**/
    public City (City other) {
        _cityName = other._cityName;
        _dateEstablished = new Date (other._dateEstablished);
        _cityCenter = new Point (other._cityCenter);
        _centralStation = new Point (other._centralStation);
        _numOfResidents = other._numOfResidents;
        _numOfNeighborhoods = other._numOfNeighborhoods;
    }
    
    /**Returns the city's name.**/
    public String getCityName() {
        return _cityName;
    }
    
    /**Returns a Date object that represents the city's established Date.**/
    public Date getDateEstablished() {
        return new Date (_dateEstablished);
    }
    
    /**Returns a Point object representing the city's center.**/
    public Point getCityCenter() {
        return new Point (_cityCenter);
    }
    
    /**Returns a Point object representing the city's central station.**/
    public Point getCentralStation() {
        return new Point (_centralStation);
    }
    
    /**Returns the number of residents in this city.**/
    public long getNumOfResidents() {
        return _numOfResidents;
    }
    
    /**Returns the number of neighborhoods in this city.**/
    public int getNumOfNeighborhoods() {
        return _numOfNeighborhoods;
    }
    
    /**Changes the city's name.**/
    public void setCityName (String cityName) {
        _cityName = cityName;
    }
    
    /**Changes the city's established date.**/
    public void setDateEstablished (Date dateEstablished) {
        _dateEstablished = new Date (dateEstablished);
    }
    
    /**Changes the city's center location.**/
    public void setCityCenter(Point cityCenter) {
        _cityCenter = new Point (cityCenter);
    }
    
    /**Changes the city's central station location.**/
    public void setCentralStation (Point centralStation) {
        _centralStation = new Point (centralStation);
    }
    
    /**Changes the city's number of residents.**/
    public void setNumOfResidents(long numOfResidents) {
         if (numOfResidents >= 0) {
        _numOfResidents = numOfResidents;
        }
    }
    
    /**Changes the city's number of neighborhoods.**/
    public void setNumOfNeighborhoods (int numOfNeighborhoods) {
         if (numOfNeighborhoods > 0) {
        _numOfNeighborhoods = numOfNeighborhoods;
        }
    }
    
    /**Return a string representation of this city.**/
    public String toString() {
        return "City name: " + _cityName +
               "\nDate established: " + _dateEstablished +
               "\nCity center: " + _cityCenter +
               "\nCentral station: " + _centralStation +
               "\nNumber of residents: " + _numOfResidents + 
               "\nNumber of neighborhoods: " + _numOfNeighborhoods;
    }
    
    /**Check if two cities are the same (checking all six instace variables).**/
    public boolean equals (City other) {
        if (_cityName.equals(other._cityName) && 
            _dateEstablished.equals(other._dateEstablished) && 
            _cityCenter.equals(other._cityCenter) && 
            _centralStation.equals(other._centralStation) &&
            _numOfResidents == other._numOfResidents && 
            _numOfNeighborhoods == other._numOfNeighborhoods ) //Checking if all 6 attributes of the cities are equal.
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**Add or subtract residents to this city.**/
    public boolean addResidents (long residentsUpdate) {
        if (_numOfResidents + residentsUpdate >= 0) {
            _numOfResidents += residentsUpdate;
            return true;
        }
        else {
            return false;
        }
    }
    
    /**Move the central station location.**/
    public void moveCentralStation(int deltaX, int deltaY) {
        _centralStation.move(deltaX, deltaY);
    }
    
    /**Calculate the distance between the city center and the central station.**/
    public double distanceBetweenCenterAndStation() {
        return _cityCenter.distance(_centralStation);
    }
    
    /**Returns a new city with a new name, established date which is a day after this city and a City center location dX and dY away from this city, with 0 residents and 1 neighborhood.**/
    public City newCity(String newCityName, int dX, int dY) {
        Point newCityCenter = new Point(_cityCenter);
        newCityCenter.move(dX, dY);
        int newCityCenterX = newCityCenter.getX();
        int newCityCenterY = newCityCenter.getY();
        
        Point newCentralStation = new Point(_centralStation);
        newCentralStation.move(dX, dY);
        int newCentralStationX = newCentralStation.getX();
        int newCentralStationY = newCentralStation.getY();
        
        int newDayEstablished = _dateEstablished.tomorrow().getDay();
        int newMonthEstablished = _dateEstablished.tomorrow().getMonth();
        int newYearEstablished = _dateEstablished.tomorrow().getYear();
        return new City (newCityName, newDayEstablished, newMonthEstablished, newYearEstablished, newCityCenterX, newCityCenterY, newCentralStationX, newCentralStationY, 0, 1);
    }
    
    /**Check if the city establish date is between two given dates (including these dates).**/
    public boolean cityEstablishedBetweenDates(Date date1, Date date2) {
        if ((_dateEstablished.before(date1) && _dateEstablished.after(date2)) ||
            (_dateEstablished.before(date2) && _dateEstablished.after(date1)) ||
             _dateEstablished.equals(date1) || _dateEstablished.equals(date2)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**This method calculates the difference in days between the establishment date of this city and the city given as a parameter.**/
    public int establishmentDateDiff (City other) {
        return _dateEstablished.difference(other._dateEstablished);
    }
}
