package kmitl.natcha58070069.com.moneyflow;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {OrderInfo.class}, version = 1)
public abstract class OrderDB extends RoomDatabase {
    public abstract OrderInfoDAO getOrderInfoDAO();
}