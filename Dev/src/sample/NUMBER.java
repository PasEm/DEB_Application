package sample;

public class Number {
    private static int number = 0;

    public Number() {
    }

    public Number(int number) {
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
