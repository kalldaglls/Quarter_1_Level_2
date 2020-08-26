import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String [] words = new String [] {"155154", "1533848", "668325", "209355", "2230354"};
        Array [] [] array = new Array [4][4];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                int choose = random.nextInt(words[i].length() - 1);//Как сделать переменную choose всегда разной, чтобы не получить три одинаковые буквы?
                String forArray = Character.toString(words[i].charAt(choose));
                array[i][j] = new Array(forArray + forArray + forArray);
                //System.out.print(array[i][j].toString());
                array[i][j].showInfo();
            }
            System.out.println();
        }
        /*Array [] [] arrayTwo = new Array [4] [4]{
                {"2", "1", "4", "7"},
                {"2", "1", "4", "7"},
                {"2", "1", "4", "7"},
                {"2", "1", "4", "7"}
        };

         */

        String [] [] arrayTwo = new String[][]{
            {"2", "1", "4", "7"},
            {"2", "1", "b", "7"},
            {"2", "1", "4", "7"},
            {"2", "1", "4", "7"}
        };
        try {
            System.out.println(checkArray(arrayTwo));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println();
            e.printStackTrace();
        }

    }
    public static int checkArray(String [][] newArray) throws MyArraySizeException, MyArrayDataException {
        if (newArray.length == 4) {//Когда делаю array.length касательно двумерного массива, то что он выдает? Горизонталь? Вертикаль?
            int sum = 0;
            for (int i = 0; i < newArray.length; i++) {
                for (int j = 0; j < newArray.length; j++) {
                    //int newElement = Integer.parseInt(String.valueOf(newArray[i][j]));
                    //System.out.println(newElement);
                    if (newArray[i][j].contains("b")) {
                        throw new MyArrayDataException("Can't parse due to String content");
                    }
                    int a = Integer.parseInt(newArray[i][j]);
                    sum = sum + a;
                    System.out.println(sum);
                }
                }
            return sum;
        } else {
            throw new MyArraySizeException("Wrong array size!");
        }
    }

}
/*1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
должно быть брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и MyArrayDataException,
 и вывести результат расчета.
 */
