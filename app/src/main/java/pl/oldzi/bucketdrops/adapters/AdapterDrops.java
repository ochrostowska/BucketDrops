package pl.oldzi.bucketdrops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pl.oldzi.bucketdrops.R;

/**
 * Created by Oldzi on 04.03.2016.
 */
public class AdapterDrops extends RecyclerView.Adapter<AdapterDrops.DropHolder> {

    private LayoutInflater dropsLayoutInflater;
    private ArrayList<String> dropItems = new ArrayList<>();

    public AdapterDrops(Context context) {
        dropsLayoutInflater = LayoutInflater.from(context);
        dropItems = generateValues();
    }
    public static ArrayList<String> generateValues() {
        ArrayList<String> dummyValues = new ArrayList<>();
        for (int i=1; i<101; i++) {
            dummyValues.add("Item "+ i);
        }
        return dummyValues;
    }

    public static class DropHolder extends RecyclerView.ViewHolder {

        TextView textViewWhat;
        public DropHolder(View itemView) {
            super(itemView);
            textViewWhat = (TextView) itemView.findViewById(R.id.whatTV);
        }
    }

    @Override
    public DropHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = dropsLayoutInflater.inflate(R.layout.row_drop, parent, false);
        DropHolder holder = new DropHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DropHolder holder, int position) {
        holder.textViewWhat.setText(dropItems.get(position));

    }

    @Override
    public int getItemCount() {
        return 100;
    }

}
