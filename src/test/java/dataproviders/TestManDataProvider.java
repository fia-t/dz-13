package dataproviders;
import org.testng.annotations.DataProvider;
import com.person.Man;
import com.person.Woman;

public class TestManDataProvider {
    @DataProvider(name = "manData")
    public static Object[][] provideWomanData() {
        return new Object[][] {
                {"John", "Doe", 35},
                {"Robert", "Wilson", 42},
                {"Michael", "Brown", 28}
        };
    }

    @DataProvider(name = "partnerData")
    public Object[][] providePartnerData() {
        return new Object[][] {
                {new Woman("Jane", "Smith", 35)},
                {new Woman("Alice", "Johnson", 42)},
                {new Woman("Emily", "Davis", 39)}
        };
    }

    @DataProvider(name = "isNotRetired")
    public static Object[][] provideIsNotRetiredData() {
        return new Object[][] {
                {1},
                {25},
                {64}
        };
    }

    @DataProvider(name = "isRetired")
    public static Object[][] provideIsRetiredData() {
        return new Object[][] {
                {65},
                {100},
                {150}
        };
    }

    @DataProvider(name = "partnerDeregistrationData")
    public Object[][] providePartnerDeregistrationData() {
        return new Object[][] {
                {new Woman("Jane", "Smith", 60), true},
                {new Woman("Alice", "Johnson", 55), true}
        };
    }

}
