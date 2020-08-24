
public class Loader {
    public static void main(String[] args) {
        Cat cat = new Cat();

        Cat[] cats = new Cat[7];
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat();
            System.out.println(cats[i].getWeight());
        }

        System.out.println("Всего кошек: " + Cat.getCount());

        cats[0].feed(50.0);
        cats[1].feed(85.0);
        System.out.println("Вес после кормления: " + cats[0].getWeight());
        System.out.println("Вес после кормления: " + cats[1].getWeight());


        while (!cats[3].getStatus().equals("Exploded")) {
            cats[3].feed(40.0);
            if (cats[3].getStatus().equals("Exploded")) {
                System.out.println("Я взорвался, мой вес: " + cats[3].getWeight());
            }
        }
        cats[3].drink(20.0);
        System.out.println("Осталось кошек: " + Cat.getCount());

        while (!cats[4].getStatus().equals("Dead")) {
            cats[4].meow();
            if (cats[4].getStatus().equals("Dead")) {
                System.out.println("Я мертва, мой вес: " + cats[4].getWeight());
            }
        }

        System.out.println("Осталось кошек: " + Cat.getCount());

        System.out.println(cats[5].getWeight());
        cats[5].pee();
        System.out.println(cats[5].getWeight());

        Cat kitten0 = getKitten();
        Cat kitten1 = getKitten();
        Cat kitten2 = getKitten();

        System.out.println("Вес котенка0: " + kitten0.getWeight());
        System.out.println("Сейчас живо: " + Cat.getCount());




        System.out.println(cat.getStatus());

    }

    private static Cat getKitten(){
        return new Cat(1100.0);
    }
}