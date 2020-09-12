import java.util.*;

public class BeautifulNumbers {

    // Здесь кирилица
    private static final String[] letterArray = new String[]{"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};

    public void run() {
        HashSet<String> hashSet = new HashSet<>();
        getEliteNumber(hashSet);
        getAestheticNumber(hashSet);
        othersBeautyNumbers(hashSet);
        ArrayList<String> arrayList = new ArrayList<>(hashSet);
        TreeSet<String> treeSet = new TreeSet<>(hashSet);
        Collections.sort(arrayList);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println(System.lineSeparator() + "Введите номер для поиска в базе, Внимание кирилица!!!"
                        + System.lineSeparator() + "Для выхода введите - exit");
                String line;
                line = scanner.nextLine();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                }
                print(searchNumber(hashSet, arrayList, treeSet, line));
            }
        }
    }

    public LinkedList<String> searchNumber(HashSet<String> hashSet, ArrayList<String> arrayList, TreeSet<String> treeSet, String line) {
        LinkedList<String> result = new LinkedList<>();
        long start;
        long duration;
        boolean isResult;

        start = System.nanoTime();
        isResult = arrayList.contains(line);
        duration = System.nanoTime() - start;
        if (isResult) {
            result.add("Поиск перебором: номер найден, поиск занял - " + duration);
        } else {
            result.add("Поиск перебором: номер не найден, поиск занял - " + duration);
        }

        start = System.nanoTime();
        isResult = Collections.binarySearch(arrayList, line) > 0;
        duration = System.nanoTime() - start;
        if (isResult) {
            result.add("Бинарный поиск: номер найден, поиск занял - " + duration);
        } else {
            result.add("Бинарный поиск: номер не найден, поиск занял - " + duration);
        }

        start = System.nanoTime();
        isResult = hashSet.contains(line);
        duration = System.nanoTime() - start;
        if (isResult) {
            result.add("Поиск в HashSet: номер найден, поиск занял - " + duration);
        } else {
            result.add("Поиск в HashSet: номер не найден, поиск занял - " + duration);
        }

        start = System.nanoTime();
        isResult = treeSet.contains(line);
        duration = System.nanoTime() - start;
        if (isResult) {
            result.add("Поиск в TreeSet: номер найден, поиск занял - " + duration);
        } else {
            result.add("Поиск в TreeSet: номер не найден, поиск занял - " + duration);
        }
        return result;
    }

    //Не используется
    private void printResult(HashMap<String, String> result) {
        System.out.println(result.get("Поиск перебором"));
        System.out.println(result.get("Бинарный поиск"));
        System.out.println(result.get("HashSet"));
        System.out.println(result.get("TreeSet"));
    }

    private void print(LinkedList<String> list) {
        for (String element : list) {
            System.out.println(element);
        }
    }

    public void othersBeautyNumbers(HashSet<String> hashSet) {
        // Добем до 2 000 000 просто совпадают три цифры
        for (int i = 111; i <= 999; i += 111) {
            for (String s : letterArray) {
                for (String value : letterArray) {
                    for (String item : letterArray) {
                        for (int k = 1; k < 200; k++) {
                            String reg = "" + k;
                            if (k < 10) {
                                reg = 0 + reg;
                            }
                            String num =
                                    s + i + value + item + reg;
                            if (hashSet.size() < 2000000) {
                                hashSet.add(num);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Базу заполнена до 2 000 000 остальными номерами \"BeautyNumbers\".");
    }

    public void getEliteNumber(HashSet<String> hashSet) {
        for (String s1 : letterArray) {
            for (int j = 0; j < 10; j++) {
                // Супер элитные номера (ММ010ММ120, К700КК04, Х005ХХ17)
                if (j > 0) {
                    for (int n = 0; n < 3; n++) {
                        int[] tm = new int[]{0, 0, 0};
                        tm[n] = j;
                        for (int k = 1; k < 200; k++) {
                            String reg = "" + k;
                            if (k < 10) {
                                reg = 0 + reg;
                            }
                            String num = s1 + tm[0] + tm[1] + tm[2] +
                                    s1 + s1 +
                                    reg;
                            hashSet.add(num);
                        }
                    }
                }
                // Формат М444ММ25, Р494PP37
                for (int m = 1; m < 10; m++) {
                    for (int n = 0; n < 3; n++) {
                        int[] tm = new int[]{j, j, j};
                        tm[1] = m;
                        for (int k = 1; k < 200; k++) {
                            String reg = "" + k;
                            if (k < 10) {
                                reg = 0 + reg;
                            }
                            String num = s1 + tm[0] + tm[1] + tm[2] +
                                    s1 + s1 +
                                    reg;
                            hashSet.add(num);
                        }
                    }
                }
            }
        }
        System.out.println("В базу внесены элитные номера.");
        System.out.println("Количество элиты в стране составляет - " + hashSet.size() + " персоны");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Ошибка - " + e);
        }
    }

    // тоже красивае Формат М123ММ25, C789CC102, Р765PP37
    public void getAestheticNumber(HashSet<String> hashSet) {
        int size = hashSet.size();
        for (String element : letterArray) {
            for (int j = 0; j < 8; j++) {
                for (int n = 0; n < 3; n++) {
                    int[] tm = new int[]{(j), (j + 1), (j + 2)};
                    for (int k = 1; k < 200; k++) {
                        String reg = "" + k;
                        if (k < 10) {
                            reg = 0 + reg;
                        }
                        String num = element + tm[0] + tm[1] + tm[2] +
                                element + element +
                                reg;
                        hashSet.add(num);
                        num = element + tm[2] + tm[1] + tm[0] +
                                element + element +
                                reg;
                        hashSet.add(num);
                    }
                }
            }
        }
        System.out.println("В базу внесены эстетичные номера еще - " + (hashSet.size() - size));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Ошибка - " + e);
        }
    }
}


