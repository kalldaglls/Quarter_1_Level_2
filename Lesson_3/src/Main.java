import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String[] words = new String[]{
                "Albatros", "Banana", "donat", "elephant", "donat", "fragile", "gears", "hero",
                "money", "money", "casual", "hero"
        };

        Set<String> uniqueWords = new HashSet<>();
        int a = 1;
        for (int i = 0; i < words.length; i++) {
            if (uniqueWords.contains(words[i])) {
                System.out.printf("Element %s already exists in the list", words[i]);
                System.out.println();
                a = a + 1;
                System.out.println(a);
                uniqueWords.add(words[i]);
            }
            else {
                uniqueWords.add(words[i]);
                System.out.println(words[i] + " " + 1);
            }
        }
        System.out.println(uniqueWords);

        Map<String,Integer> countWords = new HashMap<>();
        for (String w : words) {
            if (countWords.containsKey(w)) {
               Integer countWord =  countWords.get(w);
               countWord++;
               countWords.put(w, countWord);
            }else {
                countWords.put(w, 1);
            }
        }
        System.out.println(countWords);
    }
    }
/*1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
Посчитать сколько раз встречается каждое слово.
2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
В этот телефонный справочник с помощью метода add() можно добавлять записи.
С помощью метода get() искать номер телефона по фамилии.
Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
тогда при запросе такой фамилии должны выводиться все телефоны.
Желательно как можно меньше добавлять своего, чего нет в задании
(т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес),
делать взаимодействие с пользователем через консоль и т.д.).
Консоль желательно не использовать (в том числе Scanner),
тестировать просто из метода main() прописывая add() и get().
 */
