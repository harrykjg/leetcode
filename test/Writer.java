package test;

abstract class Writer {
    public static void write() {
        System.out.println("Writing...");
    }
}
class Author extends Writer {
    public static void write() {
        System.out.println("Writing book");
    }
}
class Programmer extends Writer {
    public static void write() {
        System.out.println("Writing code");
    }
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder("Java");
        String s2 = "Love";
        s1.append(s2);
        s1.substring(4);
        int x = 10;
        int y = 2;
        try {
            for (int z = 2; z >= 0; z--) {
                int ans = x / z;
                System.out.print(ans+ " ");
            }
        }
        catch (ArithmeticException e1) {
            System.out.println("E2");
        }
        catch (Exception e1) {
            System.out.println("E1");
        }
    }
}
