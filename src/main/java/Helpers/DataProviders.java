package Helpers;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;

public class DataProviders {
    private static final String REGISTRATION_DATA_FILE = "./src/main/resources/RegistrationData.csv";
    private static final String BAW_DATA_FILE = "./src/main/resources/bawcredits.csv";
    private static final String NUMBERS = "./src/main/resources/Numbers.csv";

    @DataProvider(name = "registrationData")
    public static Object[][] getRegistrationData() throws IOException {
        return getData(REGISTRATION_DATA_FILE, ",");
    }
    @DataProvider(name = "basicautorization")
    public static Object[][] getBAWData() throws IOException {
       return getData(BAW_DATA_FILE,",");
    }
//    @DataProvider(name = "links")
//    public static Object[][] getLinks() throws IOException {
//        return getData(LINKS_FILE, "/n");
//    }

    private static Object[][] getData(String path, String divider) throws IOException {
        List<String> data = Helpers.readAllLines(path);
        Object[][] dataRows = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataRows[i] = data.get(i).split(divider);
        }
        return dataRows;
    }

}



