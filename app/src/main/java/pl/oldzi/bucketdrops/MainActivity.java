package pl.oldzi.bucketdrops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import pl.oldzi.bucketdrops.adapters.AdapterDrops;
import pl.oldzi.bucketdrops.beans.Drop;
import pl.oldzi.bucketdrops.widgets.BucketRecyclerView;

public class MainActivity extends AppCompatActivity {

    Toolbar dropsToolbar;
    Button addButton;
    BucketRecyclerView dropsRecyclerView;
    Realm realmObject;
    RealmResults<Drop> dropRealmResults;
    View bucketEmptyView;
    AdapterDrops adapterDrops;
    private RealmChangeListener changeListener = new RealmChangeListener() {
        @Override
        public void onChange() {
            Log.d("otto", "onChange: was called");
            adapterDrops.update(dropRealmResults);
        }
    };

    private View.OnClickListener addButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDialogAdd();
        }
    };

    private void showDialogAdd() {
        DialogAdd dialogAdd = new DialogAdd();
        dialogAdd.show(getSupportFragmentManager(), "Add");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBackgroundImage();
        realmObject = Realm.getDefaultInstance();
        dropRealmResults = realmObject.where(Drop.class).findAllAsync();
        dropsToolbar = (Toolbar) findViewById(R.id.toolbar);
        bucketEmptyView = findViewById(R.id.empty_drops);
        addButton = (Button) findViewById(R.id.button_add);
        dropsRecyclerView = (BucketRecyclerView) findViewById(R.id.dropsRecyclerView);
        dropsRecyclerView.hideIfEmpty(dropsToolbar);
        dropsRecyclerView.showIfEmpty(bucketEmptyView);

        adapterDrops = new AdapterDrops(this, dropRealmResults);
        dropsRecyclerView.setAdapter(adapterDrops);
        addButton.setOnClickListener(addButtonListener);
        setSupportActionBar(dropsToolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dropRealmResults.addChangeListener(changeListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        dropRealmResults.removeChangeListener(changeListener);
    }

    private void initBackgroundImage() {
        ImageView background = (ImageView) findViewById(R.id.main_background);
        Glide.with(this)
                .load(R.drawable.background)
                .centerCrop()
                .into(background);
    }
}
