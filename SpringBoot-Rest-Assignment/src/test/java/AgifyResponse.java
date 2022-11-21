public class AgifyResponse {
    private String name;
    private int quantity;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int limit) {
        this.quantity = quantity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AgifyResponse{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", count=" + count +
                '}';
    }
}
