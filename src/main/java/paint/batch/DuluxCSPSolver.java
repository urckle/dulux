package paint.batch;

import paint.batch.domain.BatchOrder;
import paint.batch.domain.CustomerOrder;
import paint.batch.domain.PaintPref;
import paint.batch.parser.OrderParser;

import java.io.IOException;
import java.util.Arrays;


/**
 * Created by manninga on 26/3/16
 *
 * TODO: consider both a first and a best solution
 *
 * Caveats:
 *
 *  Things I am NOT doing here:
 *  - exhaustive validation (paint batch orders, dodgy characters etc.)
 *  - elaborate assertions
 *  - checking unique paint 'names'
 *  - encapsulating everything (e.g. char[] in some places etc.)
 *  - unit testing
 *  - <many others>
 *
 */
class DuluxCSPSolver {

    private static final char[] availableOptions = {'G', 'M'};
    private static final char UNASSIGNED = '_'; // could obviously use '\u0000' instead.
    private final BatchOrder batchOrder;
    private char[] viableConfig;


    public static void main(String[] args) throws IOException {

        // resolve dependencies here...
        BatchOrder batchOrder = new OrderParser().loadBatchOrder(args[0]);
        DuluxCSPSolver s = new DuluxCSPSolver(batchOrder);

        if (s.solve())
            System.out.println("\n\n" + s.getViableConfig());
        else
            System.out.println("\n\nNO SOLUTION EXISTS");
    }

    /**
     *
     * @param batchOrder
     */
    private DuluxCSPSolver(BatchOrder batchOrder) {
        this.batchOrder = batchOrder;
        viableConfig = new char[batchOrder.getUniqueColorsToProduce()];

        for (int i = 0; i < viableConfig.length; i++)
            viableConfig[i] = UNASSIGNED;
    }

    /*  kickoff for main solve(..) below. */
    boolean solve() {

        System.out.println("starting: \t paint batch config = " + Arrays.toString(viableConfig));
        return solve(viableConfig);
    }

    /**
     *  ... here's hoping!
     */
    String getViableConfig() {

        //return String.valueOf(viableConfig);

        StringBuilder viableString = new StringBuilder();
        for (char c : viableConfig) {
            viableString.append(c).append(" ");
        }
        return viableString.toString();
    }

    /**
     * This is the 'algorithm'
     *
     *      boolean SOLVE(configuration conf) {
     *
     *          if (no more choices) // BASE CASE
     *              return (check conf is goal state);
     *
     *          for (all available choices) {
     *              try one choice c;
     *              // solve from here, if works out, you're done
     *              if ( SOLVE (conf with choice c made))
     *                  return true;
     *              unmake choice c;
     *          }
     *
     *          return false; // tried all choices here, backtrack
     *      }
     *
     *  in this case:
     *  - Options/choices: 'M' or 'G'
     *  - Conf: n unique colors/solution vector
     *  - starting conf values: ['_']  -- UNASSIGNED
     *
     *
     * Note: dont need 'candidate' list here as passing ref to config 'vector' and position. The recur over positions
     *   removes need for exhaustive candidate options at each 'level' in our tree.
     *
     * @param colorOrderConfig
     * @return
     */
    private boolean solve(char[] colorOrderConfig) { // auto traverse..

        System.out.println("\n------------------------------------------------------ " +
                "\nin solve(): with config ["+ Arrays.toString(colorOrderConfig)+"]");

        if(allColorsConfigured(colorOrderConfig)) {
            viableConfig = colorOrderConfig;
            return true;
        }

        int color = nextColor(colorOrderConfig);
        if(color == -1) return false; // All full up!


        // Choose from our range of options: {'G', 'M'}
        for(char choice : availableOptions) {

            //if(isValid(colorOrderConfig, color, choice)) {
                colorOrderConfig[color] = choice;

                if(solve(colorOrderConfig)) {
                    return true;
                }
                // eh-Rrrrr! Undo assignment and try another.
                colorOrderConfig[color] = UNASSIGNED;
            //}
        }

        return false; // backtrack!
    }


    /*
        ---------------------------------------------
        Just helpers from here...
        ---------------------------------------------
     */

    // TODO: ====
    private boolean isViableColorConfig(char[] config) {
        System.out.println(" in isViableColorConfig(): with config ["+Arrays.toString(config)+"]");

        for (CustomerOrder cust : batchOrder.getOrders()) {
            if (customerLeftUnhappy(config, cust)) return false;
        }

        //System.out.println(" VIABLE! ["+ Arrays.toString(config)+"]");
        return true;
    }

    // TODO: ====
    private boolean customerLeftUnhappy(char[] config, CustomerOrder cust) {

        System.out.println("  in customerLeftUnhappy() with config ["+ Arrays.toString(config)+"] and CustomerOrder ["+cust+"]");

        for (PaintPref p : cust.getCustomerPrefs()) {
            int colorPosition = p.getId() - 1;

            if (config[colorPosition] == p.getFinish()) {
                System.out.println("\t - canSatisfyCustomer() : FALSE");
                return false;
            }
        }
        System.out.println("\t - customerLeftUnhappy() : TRUE");
        return true;
    }

    /*
     */
    private boolean allColorsConfigured(char[] colorConfig) {

        for (char aColorConfig : colorConfig) {
            if (aColorConfig == UNASSIGNED) return false;
        }
        return isViableColorConfig(colorConfig);
    }

    /*
     */
    private int nextColor(char[] colorConfig) {

        int nextColor = -1;
        for(int i = 0; i < colorConfig.length; i++) {
            if(colorConfig[i] == UNASSIGNED)
            {
                nextColor = i;
                break;
            }
        }

        System.out.println(" in nextColor() : returning ["+nextColor+"]");
        return nextColor;
    }

}
