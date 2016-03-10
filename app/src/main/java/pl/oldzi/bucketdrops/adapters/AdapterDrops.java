package pl.oldzi.bucketdrops.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmResults;
import pl.oldzi.bucketdrops.R;
import pl.oldzi.bucketdrops.beans.Drop;

/**
 * Created by Oldzi on 04.03.2016.
 */
public class AdapterDrops extends RecyclerView.Adapter<AdapterDrops.DropHolder> {

    private LayoutInflater dropsLayoutInflater;
    private RealmResults<Drop> realmResults;

    public AdapterDrops(Context context, RealmResults<Drop> results) {
        dropsLayoutInflater = LayoutInflater.from(context);
        update(results);
    }

    public void update(RealmResults<Drop> results) {
        realmResults = results;
        notifyDataSetChanged();
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
        Log.d("TAG", "onCreateVH");
        return holder;
    }

    @Override
    public void onBindViewHolder(DropHolder holder, int position) {
        Drop drop = realmResults.get(position);
        holder.textViewWhat.setText(drop.getWhat());
        Log.d("TAG", "onBindVH" + position);

    }

    @Override
    public int getItemCount() {
        return realmResults.size();
    }

}
