/*
package at.stefanproell;

import at.stefanproell.Guesser.DateValidator;
import junit.framework.Assert;
import org.junit.BeforeClass;

*/
/**
 * Date format dd/mm/yyyy validator Testing
 * @author mkyong
 *
 *//*

public class DateValidatorTest {

    private DateValidator dateValidator;

    @BeforeClass
    public void initData(){
        dateValidator = new DateValidator();
    }

    @DataProvider
    public Object[][] ValidDateProvider() {
        return new Object[][]{
                new Object[] {"1/1/2010"}, new Object[] {"01/01/2020"},
                new Object[] {"31/1/2010"}, new Object[] {"31/01/2020"},
                new Object[] {"29/2/2008"}, new Object[] {"29/02/2008"},
                new Object[] {"28/2/2009"}, new Object[] {"28/02/2009"},
                new Object[] {"31/3/2010"}, new Object[] {"31/03/2010"},
                new Object[] {"30/4/2010"}, new Object[] {"30/04/2010"},
                new Object[] {"31/5/2010"}, new Object[] {"31/05/2010"},
                new Object[] {"30/6/2010"}, new Object[] {"30/06/2010"},
                new Object[] {"31/7/2010"}, new Object[] {"31/07/2010"},
                new Object[] {"31/8/2010"}, new Object[] {"31/08/2010"},
                new Object[] {"30/9/2010"}, new Object[] {"30/09/2010"},
                new Object[] {"31/10/2010"}, new Object[] {"31/10/2010"},
                new Object[] {"30/11/2010"}, new Object[] {"30/11/2010"},
                new Object[] {"31/12/2010"}, new Object[] {"31/12/2010"}
        };
    }

    @DataProvider
    public Object[][] InvalidDateProvider() {
        return new Object[][]{
                new Object[] {"32/1/2010"}, new Object[] {"32/01/2020"},
                new Object[] {"1/13/2010"}, new Object[] {"01/01/1820"},
                new Object[] {"29/2/2007"}, new Object[] {"29/02/2007"},
                new Object[] {"30/2/2008"}, new Object[] {"31/02/2008"},
                new Object[] {"29/a/2008"}, new Object[] {"a/02/2008"},
                new Object[] {"333/2/2008"}, new Object[] {"29/02/200a"},
                new Object[] {"31/4/2010"}, new Object[] {"31/04/2010"},
                new Object[] {"31/6/2010"}, new Object[] {"31/06/2010"},
                new Object[] {"31/9/2010"}, new Object[] {"31/09/2010"},
                new Object[] {"31/11/2010"}
        };
    }

    @Test(dataProvider = "ValidDateProvider")
    public void ValidDateTest(String date) {
        boolean valid = dateValidator.validate(date);
        System.out.println("Date is valid : " + date + " , " + valid);
        Assert.assertEquals(true, valid);
    }

    @Test(dataProvider = "InvalidDateProvider",
            dependsOnMethods="ValidDateTest")
    public void InValidDateTest(String date) {
        boolean valid = dateValidator.validate(date);
        System.out.println("Date is valid : " + date + " , " + valid);
        Assert.assertEquals(false, valid);
    }
}*/
