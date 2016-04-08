package paint.batch.domain;

/**
 * Created by manninga on 26/3/16
 */
public class PaintPref {

    private final int id;
    private final char finish;

    /**
     *
     * @param id
     * @param finish
     */
    public PaintPref(int id, char finish) {
        this.id = id;
        this.finish = finish;
    }

    /**
     *
     * @return
     */
    public char getFinish() {
        return finish;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PaintPref{" +
                "id=" + id +
                ", finish=" + finish +
                '}';
    }
}
