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
            arrays1.playShortArray(arrays1.getArr());

        }
    }


}

