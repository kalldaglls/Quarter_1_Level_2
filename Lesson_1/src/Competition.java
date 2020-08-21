import java.util.Random;

public class Competition {
    public static void main(String[] args) {
        Obstacle [] obstacles = {
                new RunningTrack("Hell Road"),
                new RunningTrack("Sacred Path"),
                new Wall("The Great Wall"),
                new Wall("The Cry Wall")
        };

        Wall wall = new Wall("Lowa");
        System.out.println(wall.getWallName());

        Obstacle [] obstacle = {
                new RunningTrack( 100),
                new RunningTrack(25),
                new Wall(40),
                new Wall(9)
        };

        Info [] competitor = {
                new Human("Man"),
                new Cat("Gav"),
                new Robot("R2D2")
        };

        Info [] competitors = {
                new Human("Achilles", 250, 10),
                new Human("Ilusha", 90,25),
                new Human("Geralt", 450, 45),
                new Cat ("Puss in Boots", 59, 70),
                new Robot("Bender", 500,500)
        };
        Obstacle coll = new Wall("BIGA");
        makeCompetition(competitor,obstacles);
        //doCompetition(competitors,obstacle);
        /*competitors1[0].showCompetitors();
        competitors1[1].showCompetitors();
        competitors1[2].showCompetitors();

         */
    }

    public static void makeCompetition (Info [] competitor, Obstacle [] obstacle) {
        for (int i = 0; i < competitor.length; i++) {
            for (int j = 0; j < obstacle.length; j++) {
                if (obstacle[j] instanceof RunningTrack) {
                    competitor[i].run(((RunningTrack) obstacle[j]).getTrackName());//Почему идея предложила поставить перед этим выражением (RunningTrack)?
                }//Можно ли как-то по-другому достать из obstacle[j] его TrackName?
                else {
                    competitor[i].jump(((Wall) obstacle[j]).getWallName());//Почему здесь не работает геттер?
                }
            }
        }
    }

    public static void testCompetition (Info [] competitor, Obstacle [] obstacle) {


    }


    /*
    public static void doCompetition (Info [] competitors, Obstacle [] obstacle) {
        Random random = new Random(1);
        competitors[0].run(competitors[0].,obstacle[0]);


    }

     */

    /*public static void showCompetitors (Human [] competitors1) {
        for (int i = 0; i < competitors1.length; i++) {
            System.out.println(competitors1[i].toString());
        }
    }

     */
}


/*Kirill Kachalov, [21.08.20 00:09]
Действие вызывает у спортсмена нужное
Kirill Kachalov, [21.08.20 00:09]
Передавая в него размер препятствия
Kirill Kachalov, [20.08.20 23:09]
На самом деле просто делается в интерфейсе препятствия метод и по разному
реализуется во всех типах препятствий
* У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения на бег и прыжки.
Если участник не смог пройти одно из препятствий, то дальше по списку он препятствий не идет.
 Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).
Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники должны выполнять соответствующие действия (бежать или прыгать), результат выполнения печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти этот набор препятствий.

 */



