
public class Cat {
    private double originWeight;
    private double weight;

    private double minWeight;
    private double maxWeight;

    private double countFeed;

    private boolean isAlive;

    private static int count = 0;

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;
        isAlive = true;
        count++;

    }

    public void meow() {
        if (isAlive) {
            weight = weight - 1;
        System.out.println("Meow");
            checkIsAlive();
        }
        else {
            System.out.println("The cat dead");
        }
    }


    public void feed(Double amount) {
        if (isAlive) {
            weight = weight + amount;
            countFeed = countFeed + amount;
            checkIsAlive();
        }
        else {
            System.out.println("The cat dead");
        }
    }

    public void pee() {
        //Кошку жалко ).
        if (isAlive) {
            if (weight > weight - 50.0) {
                weight = weight - 50.0;
                System.out.println("Хозяин убери лоток");
            } else {
                System.out.println("Меня надо больше кормить");
            }
        }
        else {
            System.out.println("The cat dead");
        }
    }

    public void drink(Double amount) {
        if (isAlive) {
            weight = weight + amount;
            checkIsAlive();
        }
        else {
            System.out.println("The cat dead");
        }
    }

    public Double getWeight() {
        return weight;
    }

    public Double getCountFeed() {
        return countFeed;
    }

    public static int getCount() {
        return count;
    }

    private void checkIsAlive() {
        if (weight < minWeight || weight > maxWeight) {
            isAlive = false;
            count--;
        }
    }

    public String getStatus() {
        if (weight < minWeight) {
            return "Dead";
        } else if (weight > maxWeight) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }
}