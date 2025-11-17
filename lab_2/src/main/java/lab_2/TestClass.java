package lab_2;

public class TestClass {
    
    @Repeat(runs = 3)
    public void showMessage(String text) {
        System.out.println("Message: " + text);
    }

    public void displayNumber(int num) {
        System.out.println("Number: " + num);
    }

    @Repeat(runs = 2)
    public void printData(String text, int num) {
        System.out.println("Data: " + text + ", " + num);
    }

    @Repeat(runs = 4)
    protected void securedMessage(String text) {
        System.out.println("Secured: " + text);
    }

    protected void securedNumber(int num) {
        System.out.println("Secured num: " + num);
    }

    @Repeat(runs = 1)
    protected void securedData(String text, int num) {
        System.out.println("Secured data: " + text + ", " + num);
    }

    @Repeat(runs = 4)
    private void hiddenMessage(String text) {
        System.out.println("Hidden: " + text);
    }

    private void hiddenNumber(int num) {
        System.out.println("Hidden num: " + num);
    }

    @Repeat
    private void hiddenData(String text, int num) {
        System.out.println("Hidden data: " + text + ", " + num);
    }
}