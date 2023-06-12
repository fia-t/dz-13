package dataproviders;
import org.testng.annotations.DataProvider;
import com.person.Man;
import com.person.Woman;
public class TestWomanDataProvider {

    @DataProvider(name = "womanData")
    public static Object[][] provideWomanData() {
        return new Object[][] {
            {"Jane", "Smith", 35},
            {"Alice", "Johnson", 42},
            {"Emily", "Davis", 28}
        };
    }

    @DataProvider(name = "partnershipData")
    public static Object[][] providePartnershipData() {
        return new Object[][] {
                {new Man("John", "Doe", 40)},
                {new Man("Robert", "Wilson", 37)},
                {new Man("Michael", "Brown", 45)}
        };
    }

    @DataProvider(name = "partnerData")
    public static Object[][] providePartnerData() {
        return new Object[][] {
                {new Man("John", "Doe", 60)},
                {new Man("Robert", "Wilson", 20)},
                {new Man("Michael", "Brown", 45)}
        };
    }

    @DataProvider(name = "isNotRetired")
    public static Object[][] provideIsNotRetiredData() {
        return new Object[][] {
                {1},
                {25},
                {59}
        };
    }

    @DataProvider(name = "isRetired")
    public static Object[][] provideIsRetiredData() {
        return new Object[][] {
                {60},
                {100},
                {150}
        };
    }

    @DataProvider(name = "previousLastNameData")
    public static Object[][] providePreviousLastNameData() {
        return new Object[][] {
                {"Smith"},
                {"Johnson"},
                {"Davis"}
        };
    }

    @DataProvider(name = "partnerDeregistrationData")
    public static Object[][] providePartnerDeregistrationData() {
        Woman woman1 = new Woman("Jane", "Smith", 60);
        Woman woman2 = new Woman("Alice", "Johnson", 35);
        Woman woman3 = new Woman("Emily", "Davis", 42);

        Man man1 = new Man("John", "Doe", 40);
        Man man2 = new Man("Robert", "Wilson", 37);
        Man man3 = new Man("Michael", "Brown", 45);

        return new Object[][] {
                {woman1, man1, true},
                {woman2, man2, true},
                {woman3, man3, true}
        };
    }
}
