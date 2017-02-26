package sysu.lwt.contacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12136 on 2017/2/25.
 */

public class MainActivity extends AppCompatActivity {
    String[] pName, pAttribution, pColor, pNumber;
    ListView listView;
    List<Information> list;
    MyAdapter myAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        informs();
        padding();
        click_handling();
    }

    public void informs() {
        pName = new String[]{"Aaron", "Elvis", "David", "Edwin", "Frank",
                "Joshua", "Ivan", "Mark", "Joseph", "Phoebe"};
        pNumber = new String[]{"17715523654", "18825653224", "15052116654", "18854211875",
                "13955188541", "13621574410", "15684122771", "17765213579", "13315466578", "17895466428"};
        pAttribution = new String[]{"手机 江苏苏州电信", "手机 广东揭阳移动", "手机 江苏无锡移动",
                "手机 山东青岛移动", "手机 安徽合肥移动", "手机 江苏苏州移动", "手机 山东烟台联通",
                "手机 广东珠海电信", "手机 河北石家庄电信", "手机 山东东营移动"};
        pColor = new String[]{"#BB4C3B", "#c48d30", "#4469b0", "#20A17B", "#BB4C3B",
                "#c48d30", "#4469b0", "#20A17B", "#BB4C3B", "#c48d30"};
    }

    public void padding() {
        // 首先找到对应的ListView，新建一个ArrayList容器，将每个名字对应的信息放在一起
        listView = (ListView) findViewById(R.id.lv);
        list = new ArrayList<>();
        for (int i = 0; i < pName.length; i++) {
            list.add(new Information(pName[i], pNumber[i], pAttribution[i], pColor[i]));
        }
        // 自定义的Adapter，给ListView填充数据
         myAdapter = new MyAdapter(this, list);
        listView.setAdapter(myAdapter);
    }

    // ListView的点击和长按事件处理
    public void click_handling() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // 单击事件处理
                // 跳转到联系人详情页面
                // 使用Serializable实现数据在两个Activity之间的传递
                Intent intent = new Intent(MainActivity.this, Particulars.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("information", list.get(i));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                // 长按事件处理
                // 长按弹出对话框，可实现删除功能
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("MOXIMOXI");
                // 这里为view.findViewById的原因是name这个id并不在当前activity的页面中
                // 需要使用view来调用，否则取出来的是空的或者乱序
                TextView textView = (TextView) view.findViewById(R.id.name);
                builder.setMessage("是否删除 " + textView.getText() + " ?");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        list.remove(i);
                        myAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                builder.show();
                // 如果设置成false，不能区分长按和点击，也就是说长按的时候点击效果也会出现
                return true;
            }
        });
    }
}
