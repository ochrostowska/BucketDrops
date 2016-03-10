package pl.oldzi.bucketdrops.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oldzi on 09.03.2016.
 */
public class BucketRecyclerView extends RecyclerView {

    private List<View> nonEmptyViews = Collections.emptyList();
    private List<View> emptyViews = Collections.emptyList();
    private AdapterDataObserver dataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            toggleViews();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            toggleViews();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            toggleViews();
        }
    };
    //TODO extract these show/hide methods to actual methods XD = optimize
    private void toggleViews() {
        if(getAdapter()!=null && !emptyViews.isEmpty() && !nonEmptyViews.isEmpty()) {
            if(getAdapter().getItemCount()==0) {

                //show all the views which are meant to be shown
                for(View view : emptyViews) {
                    view.setVisibility(View.VISIBLE);
                }

                // hide the RecyclerView
                setVisibility(View.GONE);

                //hide all the views which are meant to be hidden
                for(View view : nonEmptyViews) {
                    view.setVisibility(View.GONE);
                }
            } else {

                for(View view : emptyViews) {
                    view.setVisibility(View.GONE);
                }

                setVisibility(View.VISIBLE);

                for(View view : nonEmptyViews) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public BucketRecyclerView(Context context) {
        super(context);
    }

    public BucketRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BucketRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if(adapter!=null){
            adapter.registerAdapterDataObserver(dataObserver);
        }
        dataObserver.onChanged();
    }

    public void hideIfEmpty(View ...views) {
        // views defined as nonEmpty in MainActivity
        nonEmptyViews = Arrays.asList(views);
    }

    public void showIfEmpty(View ...views) {
        // views defined as empty in MainActivity
        emptyViews = Arrays.asList(views);

    }
}
