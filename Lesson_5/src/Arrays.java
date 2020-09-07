public class Arrays {
    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];
    float [] a1 = new float[h];
    float [] a2 = new float[h];

    public void playArray (float[] arr) {
        java.util.Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        //System.out.println(a);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    public void playShortArray (float[] arr) {
        java.util.Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        //System.out.println(a);
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        for (int i = 0; i < a1.length; i++) {
            a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        System.out.println(System.currentTimeMillis() - a);
    }

    @Override
    public String toString() {
        return "Arrays{" +
                "arr=" + java.util.Arrays.toString(arr) +
                '}';
    }

   // public void showInfo ()

    public float[] getArr() {
        return arr;
    }
}
