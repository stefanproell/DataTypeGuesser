package at.stefanproell;


import at.stefanproell.API.DataTypeDetectorAPI;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.ComparisonFailure;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefan on 19.04.16.
 */
public class ApiTest extends TestCase{

    private List<String> listOfIntegers;
    private List<String> listOfDoubles;
    private List<String> listOfStrings;
    private List<String> listOfDates;


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ApiTest( String testName )
    {

        super( testName );
        listOfIntegers = new ArrayList<String>();
        listOfIntegers.add("0");
        listOfIntegers.add("-2147483648");
        listOfIntegers.add("2147483647");
        listOfIntegers.add("1000");
        listOfIntegers.add("12345");

        listOfDoubles = new ArrayList<String>();
        listOfDoubles.add("0.0");
        listOfDoubles.add("-17.1234567");
        listOfDoubles.add("1234567890.123456789");
        listOfDoubles.add("-9999.9");
        listOfDoubles.add("1000000000.999999999");

        listOfStrings = new ArrayList<String>();
        listOfStrings.add("");
        listOfStrings.add("a");
        listOfStrings.add("aBcDeFgHiJkLmNoPQrStUvWxYz");
        listOfStrings.add("aaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\naaaaaaaaaaaa");
        listOfStrings.add("                           ");
        listOfStrings.add("a1b2c3");

        listOfDates = new ArrayList<String>();
        listOfDates.add("1996-04-22");
        listOfDates.add("2016 10 31");
        listOfDates.add("17 05 2016");
        listOfDates.add("25.03.2016");
        listOfDates.add("11/05/2016");


    }

    /**
     * @return the suite of tests being tested
     */
    public static junit.framework.Test suite()
    {
        return new TestSuite( ApiTest.class );
    }

    @Test
    public void testShouldBeIntegers(){
        DataTypeDetectorAPI api = new DataTypeDetectorAPI();
        for(String s:listOfIntegers){
            try{
                assertTrue(api.isInteger(s));
            } catch(ComparisonFailure e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void testShouldNotBeIntegers(){
        DataTypeDetectorAPI api = new DataTypeDetectorAPI();
        for(String s:listOfDoubles){
            try{
                assertFalse(api.isInteger(s));
            } catch(ComparisonFailure e) {
                System.out.println(e.getMessage());
            }
        }
        for(String s:listOfStrings){
            try{
                assertFalse(api.isInteger(s));
            } catch(ComparisonFailure e) {
                System.out.println(e.getMessage());
            }
        }
        for(String s:listOfDates){
            try{
                assertFalse(api.isInteger(s));
            } catch(ComparisonFailure e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Test
    public void testShouldBeDoubles(){
        DataTypeDetectorAPI api = new DataTypeDetectorAPI();
        for(String s:listOfDoubles){

            try{
                assertTrue(api.isDouble(s));
            } catch(ComparisonFailure e) {
                System.out.println(e.getMessage());
            }

        }
    }

    @Test
    public void testShouldNotBeDoubles(){
        DataTypeDetectorAPI api = new DataTypeDetectorAPI();
        for(String s:listOfDates){
            try{
                assertFalse(api.isDouble(s));
            } catch(ComparisonFailure e) {
                System.out.println(e.getMessage());
            }
        }

        for(String s:listOfStrings){
            try{
                assertFalse(api.isDouble(s));
            } catch(ComparisonFailure e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * All integers are also doubles
         */
        for(String s:listOfIntegers){
            try{
                assertTrue(api.isDouble(s));
            } catch(ComparisonFailure e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
