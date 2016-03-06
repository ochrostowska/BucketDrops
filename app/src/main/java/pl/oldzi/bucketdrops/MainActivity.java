package pl.oldzi.bucketdrops;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    Toolbar dropsToolbar;
    Button addButton;
    RecyclerView dropsRecyclerView;

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
        dropsToolbar = (Toolbar) findViewById(R.id.toolbar);
        addButton = (Button) findViewById(R.id.button_add);
        //dropsRecyclerView = (RecyclerView) findViewById(R.id.dropsRecyclerView);
        //dropsRecyclerView.setAdapter(new AdapterDrops(this));
        addButton.setOnClickListener(addButtonListener);
        setSupportActionBar(dropsToolbar);
    }

    private void initBackgroundImage() {
        ImageView background = (ImageView) findViewById(R.id.main_background);
        Glide.with(this)
                .load(R.drawable.background)
                .centerCrop()
                .into(background);

    }
}
