package com.example.daily_act_listview;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListViewActivity extends AppCompatActivity {
    ImageButton addbutton;
    ListView listView;
    TextInputEditText work,time;
    MaterialButton add;
    ArrayList<itemClass> users;
    ListveiwAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        addbutton=findViewById(R.id.addbutton);
        listView=findViewById(R.id.mylistview);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialoguebox();
            }
        });
        users =new ArrayList<>();
        adapter=new ListveiwAdapter(this,users);
        listView.setAdapter(adapter);

    }

    void showDialoguebox(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ListViewActivity.this);
        View DialogueView = getLayoutInflater().inflate(R.layout.material,null);

        work=DialogueView.findViewById(R.id.work);
        time=DialogueView.findViewById(R.id.time);
        add=DialogueView.findViewById(R.id.add);

        builder.setView(DialogueView);
        final AlertDialog alertDialog=builder.create();
        alertDialog.setCanceledOnTouchOutside(true);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String worktext=String.valueOf(work.getText());
                String timetext=String.valueOf(time.getText());
                View focusview=null;
                boolean isok=true;
                if(TextUtils.isEmpty(worktext)){
                    work.setError("Field n Not Be Empty!");
                    focusview=work;
                    isok=false;
                }else if(TextUtils.isEmpty(timetext)){
                    time.setError("Field Can Not Be Empty!");
                    focusview=time;
                    isok=false;
                }
                if(isok){
                    users.add(new itemClass(worktext,timetext));
                    adapter.notifyDataSetChanged();
                    alertDialog.dismiss();
                }else {
                    focusview.requestFocus();
                    isok=true;

                }
            }
        });
        alertDialog.show();
    }
    class itemClass{
        String work;
        String time;
        public itemClass(String work,String time){
            this.work=work;
            this.time=time;
        }
    }

    class ListveiwAdapter extends ArrayAdapter<itemClass>{

        public ListveiwAdapter(@NonNull Context context,@NonNull ArrayList<itemClass> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null){
                itemClass itemClass=getItem(position);
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.customlistview, parent, false);
                TextView todotext=convertView.findViewById(R.id.work);
                TextView timetext=convertView.findViewById(R.id.time);
                todotext.setText(itemClass.work);
                timetext.setText(itemClass.time);
            }


            return convertView;
        }
    }
}

