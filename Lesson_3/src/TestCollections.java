import java.util.LinkedList;
import java.util.List;

public class TestCollections {
    public static void main(String[] args) {
        List <Integer> numbers= new LinkedList<>();
        List <Integer> newNumbers = new LinkedList<>();
        numbers.add(2);
        numbers.add(3);
        newNumbers.add(1);
        newNumbers.add(9);
        newNumbers.add(9);
        newNumbers.add(9);
        newNumbers.add(9);
        System.out.println(numbers);
        System.out.println(newNumbers);
        numbers.addAll(newNumbers);
        System.out.println(numbers);
        System.out.println(newNumbers);
    }
}
