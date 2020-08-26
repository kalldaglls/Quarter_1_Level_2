public class Array {
    String arrayElement;

    /*public boolean checkArray (Array [] [] newArray) {
        if (newArray.length == 3) {
            return true;
        }
        return false;
    }

     */

    public Array(String arrayElement) {
        this.arrayElement = arrayElement;
    }



    public void showInfo () {
        System.out.printf("| %s |",arrayElement);
    }

    public void changeToInt () {

    }

}
