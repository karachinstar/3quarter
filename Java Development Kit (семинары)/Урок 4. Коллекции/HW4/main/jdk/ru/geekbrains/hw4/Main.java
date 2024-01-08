package jdk.ru.geekbrains.hw4;
/*
Создать справочник сотрудников
Необходимо:
Создать класс справочник сотрудников, который содержит внутри коллекцию сотрудников -
каждый сотрудник должен иметь следующие атрибуты:
- Табельный номер
- Номер телефона
- Имя
- Стаж

1. Добавить метод, который ищет сотрудника по стажу (может быть список)
2. Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
3. Добавить метод, который ищет сотрудника по табельному номеру
4. Добавить метод добавление нового сотрудника в справочник
 */
public class Main {
    public static void main(String[] args) {
        StaffDirectory dir = new StaffDirectory();

        Staff staff1 = new Staff(1,123451,"Natalia",2);
        Staff staff2 = new Staff(2,45353,"Egor",3);
        Staff staff3 = new Staff(3,933333,"Stepan",3);
        Staff staff4 = new Staff(4,22222,"Natalia",7);

        dir.addStaff(staff1);
        dir.addStaff(staff2);
        dir.addStaff(staff3);
        dir.addStaff(staff4);
        System.out.println(dir + "\n");
        //System.out.println();

//      1. Добавить метод, который ищет сотрудника по стажу (может быть список)
        System.out.println("1. Поиск сотрудника по стажу.");
        System.out.println(dir.searchByExperience(5));

//      2. Добавить метод, который выводит номер телефона сотрудника по имени (может быть список)
        System.out.println();
        System.out.println("2. Поиск номера телефона по имени: ");
        System.out.println(dir.getPhoneByName("Natalia"));

//      3. Добавить метод, который ищет сотрудника по табельному номеру
        System.out.println();
        System.out.println("3. Поиск сотрудника по табельному номеру.");
        System.out.println(dir.searchByServiceNumber(3));

//      4. Добавить метод добавление нового сотрудника в справочник
        System.out.println();
        System.out.println("4. Добавляем сотрудника.");
        Staff staff5 = new Staff(5,345345345,"Igor",23);
        dir.addStaff(staff5);

        System.out.println(dir);
    }
}
