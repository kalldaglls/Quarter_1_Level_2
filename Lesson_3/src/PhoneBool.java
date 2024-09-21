import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PhoneBool {
    //private String surname;
   // private String phoneNumber;
    private Map<String, Set<String>> book = new HashMap<>();

    public Set<String> get (String name) {
        if (book.containsKey(name)) {
            return book.get(name);
        }
        return new HashSet<>();
    }
}


/*2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
В этот телефонный справочник с помощью метода add() можно добавлять записи.
С помощью метода get() искать номер телефона по фамилии.
Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
тогда при запросе такой фамилии должны выводиться все телефоны.

 */