package sample;

public class NUMBER {
    private static int number = 0;

    public NUMBER() {
    }

    public NUMBER(int number) {
        this.number = number;
    }

    public static void increase(){
        number++;
    }

    public static void setToZero(){
        number = 0;
    }

    public static int getNumber() {
        return number;
    }
}
