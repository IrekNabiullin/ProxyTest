import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {

        // Создаем оригинальный объект
        Man vasia = new Man("Вася", 30, "Санкт-Петербург", "Россия");

        // Получаем загрузчик класса у оригинального объекта
        ClassLoader vasiaClassLoader = vasia.getClass().getClassLoader();

        // Получаем все интерфейсы, которые реализует оригинальный объект
        Class[] interfaces = vasia.getClass().getInterfaces();

        //  Создание прокси объекта происходит на уровне интерфейсов, а не классов. Прокси создается для интерфейса.
        // Если попробуем создать прокси для класса, то есть поменяем тип ссылки и попытаемся сделать приведение к классу Man, у нас ничего не выйдет.
        
        // Создаем прокси нашего объекта vasia
        Person proxyVasia = (Person) Proxy.newProxyInstance(vasiaClassLoader, interfaces, new PersonInvocationHandler(vasia));

        //Вызываем у прокси объекта один из методов нашего оригинального объекта
        proxyVasia.introduce(vasia.getName());

    }
}
