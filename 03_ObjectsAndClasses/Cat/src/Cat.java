
public class Cat implements Cloneable{
    public static final int COUNT_EYES = 2;
    public static final double MIN_WEIGHT = 1000.0;
    public static final double MAX_WEIGHT = 9000.0;
    private double originWeight;
    private double weight;
    private Color color;

//    private double minWeight;
//    private double maxWeight;

    private double countFeed;

    private boolean isAlive;

    private static int count = 0;

    public Cat() {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
//        minWeight = 1000.0;
//        maxWeight = 9000.0;
        isAlive = true;
        int num = (int) ( Math.random() * 7 );
        color = Color.values()[num];
        count++;

    }

    public Cat(double weight){
        this.weight = weight;
        this.originWeight = weight;
        this.isAlive = true;
        count++;
        int num = (int) ( Math.random() * 7 );
        this.color = Color.values()[num];

    }

    public Cat (Cat other){
        this.weight = other.getWeight();
        this.originWeight = other.getOriginWeight();
        this.isAlive = other.isAlive();
        this.color = other.getColor();
        this.countFeed = other.getCountFeed();
        count++;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getOriginWeight() {
        return originWeight;
    }

    public void setOriginWeight(double originWeight) {
        this.originWeight = originWeight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCountFeed(double countFeed) {
        this.countFeed = countFeed;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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
        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
            isAlive = false;
            count--;
        }
    }

    public String getStatus() {
        if (weight < MIN_WEIGHT) {
            return "Dead";
        } else if (weight > MAX_WEIGHT) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }

    public static Cat copyCat(Cat catOther){
        Cat cat = new Cat();
        cat.setAlive(catOther.isAlive());
        cat.setWeight(catOther.getWeight());
        cat.setColor(catOther.getColor());
        cat.setCountFeed(catOther.getCountFeed());
        cat.setOriginWeight(catOther.getOriginWeight());
        return cat;
    }


    public Cat clone() throws CloneNotSupportedException{
        return (Cat) super.clone();
    }
}