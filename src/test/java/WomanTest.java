import dataproviders.TestWomanDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.person.Man;
import com.person.Woman;

public class WomanTest {

    @Test(dataProvider = "womanData", dataProviderClass = TestWomanDataProvider.class)
    public void testWomanWithoutPartner(String firstName, String lastName, int age){
        Woman woman = new Woman(firstName, lastName, age);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNull(woman.getPartner());
        softAssert.assertEquals(firstName, woman.getFirstName(), "Correct First Name");
        softAssert.assertEquals(lastName, woman.getLastName(), "Correct Last Name");
        softAssert.assertEquals(age, woman.getAge(), "Correct age");

    }
    @Test(dataProvider = "partnershipData", dataProviderClass = TestWomanDataProvider.class)
    public void testWomanRegisterPartnership(Man man){
        Woman woman = new Woman("Jane", "Smith", 35);
        Assert.assertTrue(woman.registerPartnership(man), "Register Partnership");
    }

    @Test(dataProvider = "partnerData", dataProviderClass = TestWomanDataProvider.class)
    public void testSetPartner(Man man){
        Woman woman = new Woman("Jane", "Smith", 35);
        Assert.assertNotNull(woman.setPartner(man));

    }
    @Test(dataProvider = "partnerData", dataProviderClass = TestWomanDataProvider.class)
    public void testGetPartner(Man man){
        Woman woman = new Woman("Jane", "Smith", 35);
        //Man man = new Man("John", "Doe", 40);
        woman.setPartner(man);
        Assert.assertNotNull(woman.getPartner());

    }

    @Test(dataProvider = "isNotRetired", dataProviderClass = TestWomanDataProvider.class)
    public void testIsNotRetired(int age){
        Woman woman = new Woman("Jane", "Smith", age);
        Assert.assertFalse(woman.isRetired());
    }

    @Test(dataProvider = "isRetired", dataProviderClass = TestWomanDataProvider.class)
    public void testIsRetired(int age){
        Woman woman = new Woman("Jane", "Smith", age);
        Assert.assertTrue(woman.isRetired());
    }

    @Test(dataProvider = "previousLastNameData", dataProviderClass = TestWomanDataProvider.class)
    public void testSetPreviousLastName(String previousLastName){
        Woman woman = new Woman("Jane", "Doe", 60);
        Assert.assertEquals(woman.setPreviousLastName(previousLastName), previousLastName);
    }

    @Test(dataProvider = "partnerDeregistrationData", dataProviderClass = TestWomanDataProvider.class)
    public void testDeregisterPartnership(Woman woman, Man man, boolean isNullExpected){
        woman.setPartner(man);
        Assert.assertNull(woman.deregisterPartnership(isNullExpected));
    }

    @Test(dataProvider = "partnerDeregistrationData", dataProviderClass = TestWomanDataProvider.class)
    public void testNotDeregisterPartnership(Woman woman, Man man, boolean isNullExpected){
        woman.setPartner(man);
        Assert.assertEquals(woman.deregisterPartnership(isNullExpected), woman.getLastName());
    }

}
