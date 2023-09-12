class Circle {
    protected int radius;

    public Circle(int r) {
        radius = r;
    }
}

public class Pizza extends Circle {
    private String kind;

    public Pizza(String k, int r) {
        super(r); // Call the constructor of the base class (Circle)
        kind = k;
    }

    public void print() {
        System.out.println(kind + "는 " + radius + "사이즈입니다.");
    }

    public static void main(String[] args) {
        Pizza obj = new Pizza("Pepperoni", 20);
        obj.print();
    }
}
