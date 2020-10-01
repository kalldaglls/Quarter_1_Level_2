import java.util.Arrays;

public class SyncArrays {

        static final int size = 10000000;
        static final int h = size / 2;
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];

    public SyncArrays() {
    }

    public void playArraySync() {
            long a = System.currentTimeMillis();
            java.util.Arrays.fill(arr, 1);
            System.out.println("Долгий метод ");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            long b = System.currentTimeMillis() - a;
            System.out.println("В конце долгого метода " + b);
            // System.out.println(b - a);
        }

        public void playShortArraySync() {
            long a = System.currentTimeMillis();
            java.util.Arrays.fill(arr, 1);
            //System.out.println(a);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            System.arraycopy(arr, 0, a1, 0, h);
            System.arraycopy(arr, h, a2, 0, h);

            System.out.println(a1.length);
            System.out.println(a2.length);

             Thread firstThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    synchronized (a1) {
                        System.out.println(Thread.currentThread().getName());
                        System.out.println("Поток № 1 короткого метода");
                        for (int i = 0; i < a1.length; i++) {
                            a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

                        }


                    }
                }
            });

            Thread secondThread = new Thread(new Runnable() {

                @Override
                public void run() {
                    synchronized (a2) {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("Поток № 2 короткого метода");
                    for (int i = 0; i < a2.length; i++) {
                        a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

                    }
                    //System.out.println(java.util.Arrays.toString(a2));
                }
                }
            });

            firstThread.start();
            secondThread.start();

            /*
            try {
                firstThread.join();
                secondThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

             */
            //System.out.println(java.util.Arrays.toString(a1));
            // System.out.println(java.util.Arrays.toString(a2));
            System.arraycopy(a1, 0, arr, 0, h);
            System.arraycopy(a2, 0, arr, h, h);
            System.out.println(arr.length);
            synchronized (a1) {
                synchronized (a2) {
                    //System.out.println(java.util.Arrays.toString(arr));
                    System.out.println("wait for it...");
                    long b = System.currentTimeMillis() - a;
                    System.out.println("here is " + b);
                    //System.out.println(b - a);
                }
            }
        }

    @Override
    public String toString() {
        return "SyncArrays{" +
                "arr=" + Arrays.toString(arr) +
                ", a1=" + Arrays.toString(a1) +
                ", a2=" + Arrays.toString(a2) +
                '}';
    }





    }


