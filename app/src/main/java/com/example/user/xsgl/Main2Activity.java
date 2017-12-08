package com.example.user.xsgl;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {

    private FrameLayout layoutContent;
    private FragmentTabHost tabTabHost;
    public static ListView list_info;
    Helper helper;
    Cursor cursor;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        addTab();
        setListener();

        helper = new Helper(this);
        cursor = helper.queryStudent();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.info, cursor,
                new String[]{"Sno", "Name", "Gender", "Like"},
                new int[]{R.id.tvSno, R.id.tvName, R.id.tvGender, R.id.tvLike});
        list_info.setAdapter(adapter);

    }

    private void initView() {

        layoutContent = (FrameLayout) findViewById(R.id.layout_Content);
        tabTabHost = (FragmentTabHost) findViewById(R.id.tab_TabHost);
        list_info = (ListView) findViewById(R.id.list_info);
        tabTabHost.setup(Main2Activity.this, getSupportFragmentManager(), R.id.layout_Content);//为TabHost进行初始化设置


    }


    /**
     * 为TabHost添加布局
     */
    private void addTab() {
        String[] tag = {"tab1", "tab2", "tab3"};//标签的ID
        Class[] cls = {AddInfoFragment.class, UpdataInfoFragment.class, DeleteInfoFragment.class};
        View[] view = {getTabView("增加"), getTabView("更新"), getTabView("删除")};
        //以上三个标签有几个，他们就有几个
        for (int i = 0; i < tag.length; i++) {
            tabTabHost.addTab(tabTabHost.newTabSpec(tag[i]).setIndicator(view[i]), cls[i], null);//为tab_TabHost部件添加布局且关联显示的界面
        }
        //下面三句是设置TabHost默认选择的界面
        setBold(0, true);
    }

    /**
     * 为底部标签设置文字信息
     *
     * @param tab
     * @return
     */
    private View getTabView(String tab) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab, null);//获取底部标签的布局
        TextView textView = (TextView) view.findViewById(R.id.txt_Content);
        textView.setText(tab);
        return view;
    }
    private void setListener() {
        //添加TabHost选择变换事件
        tabTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //判断选择的是哪个标签并改变标签的样式
                if (tabId.equals("tab1")) {
                    setBold(0, true);
                    setBold(1, false);
                    setBold(2, false);
                } else if (tabId.equals("tab2")) {
                    setBold(0, false);
                    setBold(1, true);
                    setBold(2, false);
                } else if (tabId.equals("tab3")) {
                    setBold(0, false);
                    setBold(1, false);
                    setBold(2, true);
                }
            }
        });
    }
    private void setBold(int item, boolean is) {
        if (is) {
            TextView t1 = (TextView) tabTabHost.getTabWidget().getChildTabViewAt(item).findViewById(R.id.txt_Content);
            t1.setTextSize(38);
            t1.getPaint().setFakeBoldText(true);
        } else {
            TextView t1 = (TextView) tabTabHost.getTabWidget().getChildTabViewAt(item).findViewById(R.id.txt_Content);
            t1.setTextSize(35);
            t1.getPaint().setFakeBoldText(false);
        }
    }

}


