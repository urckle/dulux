package paint.batch.parser;


import org.apache.commons.io.FileUtils;
import paint.batch.domain.BatchOrder;
import paint.batch.domain.CustomerOrder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manninga on 26/3/16
 */
public class OrderParser {


    /**
     * @param fileName
     * @return
     * @throws IOException
     */
    public BatchOrder loadBatchOrder(String fileName) throws IOException {

        List<String> lines = FileUtils.readLines(new File(fileName));

        BatchOrder batch = new BatchOrder();
        batch.setUniqueColorsToProduce(Integer.parseInt(lines.get(0)));
        batch.setOrders(parseCustomerOrders(lines.subList(1, lines.size())));

        System.out.println("Submitted Batch Order for Processing: " + batch);
        return batch;
    }

    /**
     * @param list
     * @return
     */
    List<CustomerOrder> parseCustomerOrders(List<String> list) {

        List<CustomerOrder> orders = new ArrayList<CustomerOrder>();

        for (String orderString : list) {
            orders.add(new CustomerOrder(orderString));
        }
        return orders;
    }


}
