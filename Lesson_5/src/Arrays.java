import static jdk.internal.misc.InnocuousThread.newThread;

public class Arrays{
    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];
    float [] a1 = new float[h];
    float [] a2 = new float[h];

    public Arrays() {
    }



    public void playArray () {
        java.util.Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        System.out.println("Долгий метод " + a);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        System.out.println("В конце долгого метода " + b);
        System.out.println(b - a);
    }

    public void playShortArray () {
        java.util.Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        //System.out.println(a);
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
            new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("Поток № 1 короткого метода");
                    for (int i = 0; i < a1.length; i++) {
                        a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

                    }

                }
            }).start();

            new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("Поток № 2 короткого метода");
                    for (int i = 0; i < a2.length; i++) {
                        a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

                    }
                    System.out.println(java.util.Arrays.toString(a2));
                }
            }).start();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println(java.util.Arrays.toString(arr));
        System.out.println("wait for it...");
        long b = System.currentTimeMillis();
        System.out.println("here is " + b);
        System.out.println(b - a);
    }

    @Override
    public String toString() {
        return "Arrays{" +
                "arr=" + java.util.Arrays.toString(arr) +
                '}';
    }

   public void showInfo () {
       java.util.Arrays.toString(arr);
   }

    public float[] getArr() {
        return arr;
    }

    public float[] getA1() {
        return a1;
    }

    public float[] getA2() {
        return a2;
    }



}
