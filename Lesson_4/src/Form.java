import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {
    public Form() {
        setTitle("Java helper");//Тайтл
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Закрытие при нажатии на Х
        setBounds(400,400,400,400);//Бордерсы

        //setLayout(new GridLayout(1,2));

        setLayout(new BorderLayout());//Что значит это строка? Она нужна, если мы опять делаем бордер далее?

        JPanel [] jPanel = new JPanel[3];//Создаем объект класса JPanel
       // JTextField jTextField = new JTextField();//Создаем поле для вывода текста
        for (int i = 0; i < 3; i++) {
            jPanel[i] = new JPanel();
            add(jPanel[i]);
            jPanel[i].setBackground(new Color(100 + i * 40, 100 + i * 40, 100 + i * 40));
        }

        jPanel[0].setLayout(new BorderLayout());
        JTextField chatName = new JTextField();
        chatName.setEditable(false);
        jPanel[0].add(chatName,BorderLayout.NORTH);
        chatName.setText("Chat helper Liza");

        jPanel[1].setLayout(new BorderLayout());
        JTextField chatField = new JTextField();
        //JScrollPane jsp = new JScrollPane(jta);
        chatField.setEditable(false);
        jPanel[1].add(chatField,BorderLayout.CENTER);
        chatField.setText("Let's go!");

        jPanel[2].setLayout(new BorderLayout());
        JTextField chatEntryField = new JTextField();
        chatEntryField.setEditable(true);
        jPanel[2].add(chatEntryField,BorderLayout.SOUTH);

        setVisible(true);//Делаем окно видимым
    }
}

/*
1. Создать окно для клиентской части чата: большое текстовое поле для отображения переписки в центре окна.
Однострочное текстовое поле для ввода сообщений и кнопка для отсылки сообщений на нижней панели.
Сообщение должно отсылаться либо по нажатию кнопки на форме, либо по нажатию кнопки Enter.
При «отсылке» сообщение перекидывается из нижнего поля в центральное.
*2. Доработать перерысовку кнопок в калькуляторе (код в приложенных материалах)

Задачи:
1) Создать окно. Наследумся от JFrame?+
2) Создать поле для вывода текста посередине окна. Объект класса JPanel?
3) Создать поле для ввода текста
4) Вставить название чата в тайтлле
 */