import java.time.LocalDate;

class Movement {
    private final String name;
    private final LocalDate date;
    private final double income;
    private final double expense;
    private final String mcc;

    public Movement(String name, LocalDate date, double income, double expense, String mcc) {
        this.name = name;
        this.date = date;
        this.income = income;
        this.expense = expense;
        this.mcc = mcc;
    }


    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getIncome() {
        return income;
    }

    public double getExpense() {
        return expense;
    }

    public String getMcc() {
        return mcc;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", income=" + income +
                ", expense=" + expense +
                ", mcc='" + mcc + '\'' +
                '}';
    }
}
