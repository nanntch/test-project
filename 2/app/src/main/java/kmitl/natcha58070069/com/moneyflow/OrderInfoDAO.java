package kmitl.natcha58070069.com.moneyflow;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface OrderInfoDAO {

    @Insert
    void insert(OrderInfo orderInfo);

    @Query("SELECT sum_income, sum_expenses FROM " +
            "(SELECT SUM(amount) AS sum_income FROM OrderInfo WHERE type = 'income')" +
            "JOIN" + "(SELECT SUM(amount) AS sum_expenses FROM OrderInfo WHERE type = 'expenses')")
    BalanceInfo getBalance();

    @Query("SELECT * FROM OrderInfo")
    List<OrderInfo> findAll();

    @Update
    void update(OrderInfo orderInfo);

    @Delete
    void delete(OrderInfo orderInfo);
}
