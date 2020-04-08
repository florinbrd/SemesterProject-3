package contract.client;

public class Value {

    private String text;
    private int id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Value{" +
                "text='" + text + '\'' +
                ", id=" + id +
                '}';
    }
}
