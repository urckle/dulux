package paint.batch.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manninga on 26/3/16
 *
 * TODO: can I get away with char[]??
 *  e.g. char[] customer_1 = {'m','\u0000','g','\u0000','g'};
 */
public class CustomerOrder {

    private final List<PaintPref> customerPrefs;

    public CustomerOrder(String orderString) {

        customerPrefs = new ArrayList<PaintPref>();

        String[] t = orderString.split("\\s+");

        for(int i = 0; i < t.length; i=i+2) {

            PaintPref p = new PaintPref(Integer.parseInt(t[i]), t[i+1].charAt(0));
            customerPrefs.add(p);
        }
    }

    /**
     *
     * @return
     */
    public List<PaintPref> getCustomerPrefs() {
        return customerPrefs;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "customerPrefs=" + customerPrefs +
                "}";
    }
}
