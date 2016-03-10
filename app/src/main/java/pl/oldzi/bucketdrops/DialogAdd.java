package pl.oldzi.bucketdrops;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
           switch (id) {
               case R.id.addItButton:
                   addAction();
                   break;}
            if(!realm.isClosed())  {
                realm.close();
            }
            dismiss();

        }
    };

    private void addAction() {
        String what = inputWhat.getText().toString();
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        long now = System.currentTimeMillis();
        Drop drop = new Drop(what, now, now, false);
        realm.copyToRealm(drop);
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

}
