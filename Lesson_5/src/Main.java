public class Main {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
       Arrays arrays = new Arrays();
       arrays.playArray(arrays.getArr());
       //System.out.println(arrays.toString());
        new Thread(new TestThread()).start();
        new Thread(new TestThread()).start();
    }

    static class TestThread implements Runnable {

        @Override
        public void run() {
            Arrays arrays1 = new Arrays();
            Arrays arrays2 = new Arrays();
            arrays1.playShortArray(arrays1.getArr(),arrays1.getA1());
            arrays2.playShortArray(arrays2.getArr(),arrays2.getA2());

        }
    }


}

