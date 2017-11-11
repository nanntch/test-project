package kmitl.natcha58070069.com.moneyflow;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDetail extends MainActivity implements View.OnClickListener {

    private Button incomeBtn;
    private Button expensesBtn;
    private Button saveBtn;
    private Button deleteBtn;

    private EditText editOrder;
    private EditText editAmount;

    private OrderDB orderDB;
    private OrderInfo orderInfo;

    public String type;

    //for putParcel
//    private static final String orderString = "orderInfo";

    @Override
    public void onCreate(Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.add_detail);

        orderDB = Room.databaseBuilder(this, OrderDB.class, "ORDER_INFO")
                .fallbackToDestructiveMigration()
                .build();

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        incomeBtn = findViewById(R.id.incomeBtn);
        expensesBtn = findViewById(R.id.expensesBtn);

        editOrder = findViewById(R.id.editOrder);
        editAmount = findViewById(R.id.editAmount);

        orderInfo = getIntent().getParcelableExtra("OrderInfo");

        if(orderInfo.getId() != 0){
            if(orderInfo.getType().equals("income")){
                income();
            } else {
                expenses();
            }
            editOrder.setText(orderInfo.getOrder());
            editAmount.setText(orderInfo.getAmount());
        }
    }

    public void income(){
        incomeBtn.setBackgroundColor(Color.parseColor("#003f7f"));
        expensesBtn.setBackgroundColor(Color.parseColor("#7f7f7f"));
        type = "income";
    }
    
    public void expenses(){
        incomeBtn.setBackgroundColor(Color.parseColor("#7f7f7f"));
        expensesBtn.setBackgroundColor(Color.parseColor("#003f7f"));
        type = "expenses";
    }

    @Override
    public void onClick(View v) {
        //save button, delete button, income, expenses
        switch (v.getId()){
            case R.id.saveBtn:
                onSaveBtn();
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.deleteBtn:
                onDeleteBtn();
                Intent intent1 = new Intent();
                setResult(RESULT_OK, intent1);
                finish();
                break;
            case R.id.incomeBtn:
                income();
                break;
            case R.id.expensesBtn:
                expenses();
                break;
        }
    }

    private void onDeleteBtn() {
        new AsyncTask<Void, Void, OrderInfo>() {
            @Override
            protected OrderInfo doInBackground(Void... voids) {
                orderDB.getOrderInfoDAO().delete(orderInfo);
                return null;
            }
        }.execute();
    }

    private void onSaveBtn() {
        String order = editOrder.getText().toString();
        String amount = editAmount.getText().toString();

        if(editOrder.equals("") || editAmount.equals("")){
            Toast.makeText(this,"Please enter fully information", Toast.LENGTH_LONG).show();
        } else {
            orderInfo.setType(type);
            orderInfo.setOrder(order);
            orderInfo.setAmount(Integer.parseInt(amount));
        }

        //insert or update
        if(orderInfo.getId() == 0){
            new AsyncTask<Void, Void, OrderInfo>() {
                @Override
                protected OrderInfo doInBackground(Void... voids) {
                    orderDB.getOrderInfoDAO().insert(orderInfo);
                    return null;
                }
            }.execute();
        } else {
            new AsyncTask<Void, Void, OrderInfo>() {
                @Override
                protected OrderInfo doInBackground(Void... voids) {
                    orderDB.getOrderInfoDAO().update(orderInfo);
                    return null;
                }
            }.execute();
        }
    }
}
