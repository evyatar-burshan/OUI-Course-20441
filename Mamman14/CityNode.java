/**
 * Write a description of class CityNode here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CityNode
{
    private City _city;
    private CityNode _next;
    
    /**
     * Constrctor for a CityNode recieving only a city
     * 
     * @param c Specifies the city of the node
     */
    public CityNode (City c) {
        _city = c;
        _next = null;
    }
    
    /**
     * Constrctor for a CityNode recieving a city and the next city
     * 
     * @param c Specifies the city of the node
     * @param next Specifies the next city
     */
    public CityNode (City c, CityNode next) {
        _city = new City(c);
        _next = next;
    }
    
    /**
     * Copy Constrctor for a CityNode
     * 
     * @param c Specifies the City Node to copy
     */
    public CityNode (CityNode c) {
        _city = new City(c._city);
        _next = c._next;
    }
    
    /**
     * Returns the city of a city node
     * 
     * @return The city of a city node
     */
    public City getCity() {
        return new City(_city);
    }
    
    /**
     * Returns the next node of the city node
     * 
     * @return The next node of the city node
     */
    public CityNode getNext() {
        return _next;
    }
    
    /**
     * Sets the city of a CityNode to a given city
     * 
     * @param c Specifies which city the CityNode's city will be updated to
     */
    public void setCity(City c) {
        _city = new City(c);
    }
    
    /**
     * Sets the next node of a CityNode to a given node
     * 
     * @param next Specifies which node the CityNode's node will be updated to
     */
    public void setNext (CityNode next) {
        _next = next;
    }
}
