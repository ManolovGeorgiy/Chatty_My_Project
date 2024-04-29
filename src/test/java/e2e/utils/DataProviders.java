package e2e.utils;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> userCanNotLoginTest() {

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"tatara@abv.bg", "Manowar33246", "user_invalid_email"});
        list.add(new Object[]{"tatar@abv.bg", "Manowar333246", "user_invalid_password"});
        list.add(new Object[]{"", "", "user_no_fields_filled_in"});
        list.add(new Object[]{"tatatata", "Redddd233", "user_with_invalid_data"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> adminCanNotLoginTest() {

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"gpower@gmail.com", "GPower3333", "invalid_email"});
        list.add(new Object[]{"g.power@gmail.com", "Gpower3334", "invalid_password"});
        list.add(new Object[]{"", "", "no_fields_filled_in"});
        list.add(new Object[]{"gpower@gmail.com", "Gpower3334", "with_invalid_data"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userCanNotLoginApiTest() {

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"gpower@gmail.com", "GPower3333"});
        list.add(new Object[]{"g.power@gmail.com", "Gpower3334"});
        list.add(new Object[]{"gpower@gmail.com", "Gpower3334"});
        return list.iterator();
    }
}

