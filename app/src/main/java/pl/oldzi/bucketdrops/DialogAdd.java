package pl.oldzi.bucketdrops;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import io.realm.Realm;
import pl.oldzi.bucketdrops.beans.Drop;

/**
 * Created by Oldzi on 04.03.2016.
 */
public class DialogAdd extends DialogFragment {

    private EditText inputWhat;
    private ImageButton closeButton;
    private DatePicker datePicker;
    private Button addItButton;
    private Realm realm;
    long a = 0;

    public DialogAdd() {

    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id==R.id.closeButton) {
            dismiss();
            } else if(id==R.id.addItButton) {

                addAction();
            }
        }
    };

    private void addAction() {
        realm.beginTransaction();
//        RealmConfiguration configuration = new RealmConfiguration.Builder(getActivity()).build();
//        Realm.setDefaultConfiguration(configuration);
 String what = inputWhat.getText().toString();
//        long now = ;
//        realm = Realm.getDefaultInstance();
//        Drop drop = new Drop(what, now, 0, false);
//        realm.beginTransaction();
        Time time = new Time();
        time.setToNow();
        long now = time.toMillis(false);
        Drop drop = realm.createObject(Drop.class);
        drop.setWhat(what);
        drop.setTime(now);
        drop.setCompleted(false);
        drop.setAdded(now);
        realm.commitTransaction();
        Toast.makeText(getContext(), "Added " +what, Toast.LENGTH_SHORT).show();
        a=a+1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inputWhat = (EditText) view.findViewById(R.id.dropEditText);
        closeButton = (ImageButton) view.findViewById(R.id.closeButton);
        datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        addItButton = (Button) view.findViewById(R.id.addItButton);
        closeButton.setOnClickListener(buttonClickListener);
        addItButton.setOnClickListener(buttonClickListener);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();
    }
}
