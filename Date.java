/**
 * This class represents a Date object
 */
public class Date
{
    private int _day;
    private int _month;
    private int _year;
    private final int LONG_MONTH_LENGTH = 31;
    private final int REGULAR_MONTH_LENGTH = 30;
    private final int SHORT_MONTH_LENGTH = 28;
    private final int NUM_OF_MONTHS = 12;
    private final int LOWEST_TWO_DIGIT_NUM = 10;
    
    /**If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000**/
    public Date(int day, int month, int year) {
        if (day > 0 && day <= LONG_MONTH_LENGTH && month > 0 && month <= NUM_OF_MONTHS && year >= 0) { //checks if all inputs are valid numerically
            _month = month;
            _year = year;
            if (_month == 1 || _month == 3 || _month == 5 || _month == 7 || _month == 8 || _month == 10 || _month == 12) { //checks if the month is long (has 31 days)
                if (day <= LONG_MONTH_LENGTH) { // checks if the day number is valid according to month
                    _day = day;
                }
                else { //Sets the date to 01/01/2000 because the input wasn't valid
                _day = 1;
                _month = 1;
                _year = 2000;
                }
            }
            else if (_month == 2) { //check if the month is February (has 28 days)
                if (day <= SHORT_MONTH_LENGTH) { // checks if the day number is valid according to month
                    _day = day;
                }
                else { //Sets the date to 01/01/2000 because the input wasn't valid
                _day = 1;
                _month = 1;
                _year = 2000;
                }
            }
            else if  (day <= REGULAR_MONTH_LENGTH) { // checks if the day number is valid according to month
                _day = day;
            }
            else { //Sets the date to 01/01/2000 because the input wasn't valid
                _day = 1;
                _month = 1;
                _year = 2000;
            }
        }
        else { //Sets the date to 01/01/2000 because the input wasn't valid
            _day = 1;
            _month = 1;
            _year = 2000;
        }
    }
    
    /**Copy constructor**/
    public Date (Date other) {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }
    
    /**Gets the day**/
    public int getDay() {
        return _day;
    }
    
    /**Gets the month**/
    public int getMonth() {
        return _month;
    }
    
    /**Gets the year**/
    public int getYear() {
        return _year;
    }
    
    /**Set the day (only if date remains valid)**/
    public void setDay(int dayToSet) {
        if (dayToSet > 0) { //Checks if the new date is valid
            if (_month == 1 || _month == 3 || _month == 5 || _month == 7 || _month == 8 || _month == 10 || _month == 12) {
                if (dayToSet <= LONG_MONTH_LENGTH) {
                    _day = dayToSet;
                }
            }
            else if (_month == 2) {
                if (dayToSet <= SHORT_MONTH_LENGTH) {
                    _day = dayToSet;
                }
            }
            else if  (dayToSet <= REGULAR_MONTH_LENGTH) {
                _day = dayToSet;
            }
        }
    }
    
    /**Set the month (only if date remains valid)**/
    public void setMonth(int monthToSet) {
        if (monthToSet > 0 && monthToSet <= NUM_OF_MONTHS) { //Checks if the new date is valid
            if (monthToSet == 1 || monthToSet == 3 || monthToSet == 5 || monthToSet == 7 || monthToSet == 8 || monthToSet == 10 || monthToSet == 12) {
                if (_day <= LONG_MONTH_LENGTH) {
                    _month = monthToSet;
                }
            }
            else if (monthToSet == 2) {
                if (_day <= SHORT_MONTH_LENGTH) {
                    _month = monthToSet;
                }
            }
            else if (_day <= REGULAR_MONTH_LENGTH) {
                _month = monthToSet;
            }
        }
    }
    
    /**Sets the year (only if date remains valid)**/
    public void setYear(int yearToSet) {
        if (yearToSet >= 0) {
            _year = yearToSet;
        }
    }
    
    /**Check if two dates are the same**/
    public boolean equals (Date other) {
        if (_day == other._day && _month == other._month && _year == other._year) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**Check if this date is before other date**/
    public boolean before (Date other) {
        if (_year < other._year) {
            return true;
        }
        if (_year == other._year) {
            if (_month < other._month) {
                return true;
            }
            else if (_month == other._month) {
                if (_day < other._day) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**Check if this date is after other date**/
    public boolean after (Date other) {
        return other.before(this);
    }
    
    //Computes the day number since the beginning of the Christian counting of years
    private int calculateDate ( int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    } 
    
    /**Calculates the difference in days between this date and other date**/
    public int difference (Date other) {
        return Math.abs(calculateDate(_day, _month, _year) - calculateDate(other._day, other._month, other._year));
    }
    
    /**Returns a String that represents this date**/
    public String toString() {
        if (_day < LOWEST_TWO_DIGIT_NUM) { //Checks if the day is single-digit
            if (_month < LOWEST_TWO_DIGIT_NUM) { //Checks if the month is single-digit
                return "0" + _day + "/0" + _month + "/" + _year;
            }
            return "0" + _day + "/" + _month + "/" + _year;
        }
        if (_month < LOWEST_TWO_DIGIT_NUM) { //Checks if the month is single-digit
           return _day + "/0" + _month + "/" + _year; 
        }
        return _day + "/" + _month + "/" + _year;
    }
    
    /**Calculate the date of tomorrow**/
    public Date tomorrow() {
        if (_month == 1 || _month == 3 || _month == 5 || _month == 7 || _month == 8 || _month == 10 || _month == 12) { //Checks if the month is long (has 31 days)
            //Changes the date according to the current day and if it's December 31st
            if (_day+1 <= LONG_MONTH_LENGTH) {
                return new Date (_day+1, _month, _year);
            }
            else if (_month == 12) {
                return new Date (1, 1, _year+1);
            }
            else {
                return new Date (1, _month+1, _year);
            }
        }
        else if (_month == 2) { //Checks if the month is long (has 28 days)
            //Changes the date according to the current day
            if (_day+1 <= SHORT_MONTH_LENGTH) {
                return new Date (_day+1, _month, _year);
            }
            else {
                return new Date (1, _month+1, _year);
            }
        }
        //Changes the date according to the current day
        else if  (_day+1 <= REGULAR_MONTH_LENGTH) {
            return new Date (_day+1, _month, _year);
        }
        else {
                return new Date (1, _month+1, _year);
            }
    }
}
