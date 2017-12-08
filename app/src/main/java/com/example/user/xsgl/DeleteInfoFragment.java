package com.example.user.xsgl;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static com.example.user.xsgl.R.id.radio_add_b;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteInfoFragment extends Fragment {


    RadioButton radio_del_no,radio_del_name;
    EditText edit_del_no,edit_del_name;
    Button btn_del_do;
    Helper helper;
    Cursor cursor;
    String Sno="",Name="";

    public DeleteInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_info, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        radio_del_no = (RadioButton)getActivity().findViewById(R.id.radio_del_no);
        radio_del_name = (RadioButton)getActivity().findViewById(R.id.radio_del_name);
        edit_del_no = (EditText)getActivity().findViewById(R.id.edit_del_no);
        edit_del_name = (EditText)getActivity().findViewById(R.id.edit_del_name);
        btn_del_do = (Button)getActivity().findViewById(R.id.btn_del_do);
        btn_del_do.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(radio_del_no.isChecked())
                {
                    helper=new Helper(getActivity());
                    Sno=edit_del_no.getText().toString();
                    helper.deleteStudentBySno(Sno);
                    Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_LONG).show();
                    DataFresh();
                }
                if(radio_del_name.isChecked())
                {
                    helper=new Helper(getActivity());
                    Name=edit_del_name.getText().toString();
                    helper.deleteStudentByName(Name);
                    Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_LONG).show();
                    DataFresh();
                }
            }
        });
    }
    void DataFresh(){
        helper = new Helper(getActivity());
        cursor = helper.queryStudent();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.info, cursor,
                new String[]{"Sno", "Name", "Gender", "Like"},
                new int[]{R.id.tvSno, R.id.tvName, R.id.tvGender, R.id.tvLike});
        Main2Activity.list_info.setAdapter(adapter);
    }
}
