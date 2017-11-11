package kmitl.natcha58070069.com.moneyflow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<OrderInfo> data;
    private Context context;
    private MyAdapterListener listener;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MyAdapterListener getListener() {
        return listener;
    }

    public void setListener(MyAdapterListener listener) {
        this.listener = listener;
    }

    public MyAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public List<OrderInfo> getData() {
        return data;
    }

    public void setData(List<OrderInfo> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.item, null, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final OrderInfo orderInfo = data.get(position);

        //Holder
        if(orderInfo.getType().equals("income")){
            holder.textType.setText("+");
        } else {
            holder.textType.setText("-");
        }
        holder.textOrder.setText(orderInfo.getOrder());
        holder.textAmount.setText(String.valueOf(orderInfo.getAmount()));

        //If user want to edit must set on click in holder
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickInfoItem(orderInfo);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(data == null){
            data = new ArrayList<>();
        }
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textType;
        TextView textOrder;
        TextView textAmount;

        public ViewHolder(View itemView) {
            super(itemView);
            textType = itemView.findViewById(R.id.textType);
            textOrder = itemView.findViewById(R.id.textOrder);
            textAmount = itemView.findViewById(R.id.textAmount);
        }
    }

    public interface MyAdapterListener {
        void onClickInfoItem(OrderInfo orderInfo);
    }
}
