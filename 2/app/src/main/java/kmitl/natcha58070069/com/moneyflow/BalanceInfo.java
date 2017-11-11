package kmitl.natcha58070069.com.moneyflow;

import android.arch.persistence.room.ColumnInfo;

public class BalanceInfo {

    @ColumnInfo(name = "sum_income")
    private int sum_income;

    @ColumnInfo(name = "sum_expenses")
    private int sum_expenses;

    public int getSum_income() {
        return sum_income;
    }

    public void setSum_income(int sum_income) {
        this.sum_income = sum_income;
    }

    public int getSum_expenses() {
        return sum_expenses;
    }

    public void setSum_expenses(int sum_expenses) {
        this.sum_expenses = sum_expenses;
    }

    public int getBalance() {
        return sum_income - sum_expenses;
    }
}
