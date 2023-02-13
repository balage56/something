public class Main {
    public static void main(String[] args) {

        Lawn lawn = new Lawn(7, 7);
        Mower mower = new Mower("Mw", lawn);
        int[] base;

        try {
            lawn.lawnInit();
            lawn.draw();

            base = mower.dropAtRandom(lawn);

            mower.process(lawn);

            mower.returnToBase(lawn, base);

        } catch (InterruptedException e) {
            System.out.println(e.toString());
        }
    }
}

