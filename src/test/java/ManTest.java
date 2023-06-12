import dataproviders.TestManDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.person.Man;
import com.person.Woman;

public class ManTest {


    @Test(dataProvider = "manData", dataProviderClass = TestManDataProvider.class)
    public void testRegisterPartnership(String firstName, String lastName, int age){
        Man man = new Man(firstName, lastName, age);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNull(man.getPartner());
        softAssert.assertEquals(firstName, man.getFirstName(), "Correct First Name");
        softAssert.assertEquals(lastName, man.getLastName(), "Correct Last Name");
        softAssert.assertEquals(age, man.getAge(), "Correct age");
    }

    @Test(dataProvider = "partnerData",  dataProviderClass = TestManDataProvider.class)
    public void testManRegisterPartnership(Woman woman){
        Man man = new Man("John", "Doe", 40);
        Assert.assertTrue(man.registerPartnership(woman), "Register Partnership");
    }

    @Test(dataProvider = "partnerData",  dataProviderClass = TestManDataProvider.class)
    public void testSetPartner(Woman woman){
        Man man = new Man("John", "Doe", 40);
        Assert.assertNotNull(man.setPartner(woman));

    }
    @Test(dataProvider = "partnerData",  dataProviderClass = TestManDataProvider.class)
    public void testGetPartner(Woman woman){
        Man man = new Man("John", "Doe", 40);
        man.setPartner(woman);
        Assert.assertNotNull(man.getPartner());

    }

    @Test(dataProvider = "isNotRetired", dataProviderClass = TestManDataProvider.class)
    public void testIsNotRetired(int age){
        Man man = new Man("John", "Doe", 64);
        Assert.assertFalse(man.isRetired());
    }

    @Test(dataProvider = "isRetired", dataProviderClass = TestManDataProvider.class)
    public void testIsRetired(int age){
        Man man = new Man("John", "Doe", 65);
        Assert.assertTrue(man.isRetired());
    }

    @Test(dataProvider = "partnerDeregistrationData", dataProviderClass = TestManDataProvider.class)
    public void testDeregisterPartnership(Woman woman, boolean expectedResult){
        Man man = new Man("John", "Doe", 40);
        man.setPartner(woman);
        Assert.assertNull(man.deregisterPartnership(expectedResult));
    }

    @Test(dataProvider = "partnerDeregistrationData", dataProviderClass = TestManDataProvider.class)
    public void testNotDeregisterPartnership(Woman woman, boolean expectedResult){
        Man man = new Man("John", "Doe", 40);
        man.setPartner(woman);
        Assert.assertEquals(man.deregisterPartnership(expectedResult), expectedResult ? null : woman.getLastName());
    }


}
