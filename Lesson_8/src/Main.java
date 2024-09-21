public class Main {

    public static void main(String[] args) {

        TwiceLinkedList twiceLinkedList = new TwiceLinkedList();
        twiceLinkedList.add("Toyota");
        twiceLinkedList.add("Honda");
        twiceLinkedList.add("Subaru");
        twiceLinkedList.add("Mazda");


        System.out.println(twiceLinkedList);

        twiceLinkedList.remove("Toyota");

        System.out.println(twiceLinkedList);


        /*
        SinglyLinkedList singlyLinkedList = new SinglyLinkedList();
        singlyLinkedList.add("LADA");
        singlyLinkedList.add("Zhiguli");
        singlyLinkedList.add("Kopeika");
        singlyLinkedList.add("VESTA");

        System.out.println(singlyLinkedList);

         */

        //singlyLinkedList.remove("Mazda");
        //System.out.println(twiceLinkedList);
    }
}
