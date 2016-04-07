package paint.batch.domain;

import java.util.List;

/**
 * Created by manninga on 22/3/14
 */
public class BatchOrder {

    private int uniqueColorsToProduce;
    private List<CustomerOrder> orders;

    /**
     *
     * @return
     */
    public List<CustomerOrder> getOrders() {
        return orders;
    }

    /**
     *
     * @param orders
     */
    public void setOrders(List<CustomerOrder> orders) {
        this.orders = orders;
    }

    /**
     *
     * @return
     */
    public int getUniqueColorsToProduce() {
        return uniqueColorsToProduce;
    }

    /**
     *
     * @param uniqueColorsToProduce
     */
    public void setUniqueColorsToProduce(int uniqueColorsToProduce) {
        this.uniqueColorsToProduce = uniqueColorsToProduce;
    }

    @Override
    public String toString() {
        return "\nBatchOrder{\n" +
                " - uniqueColorsToProduce=" + uniqueColorsToProduce +
                ",\n - orders=" + orders +
                '}';
    }
}
