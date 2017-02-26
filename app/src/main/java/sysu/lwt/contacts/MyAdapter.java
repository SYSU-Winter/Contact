package sysu.lwt.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 12136 on 2017/2/25.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Information> list;

    public MyAdapter(Context context, List<Information> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    // 获取数据项列表的长度，也就是一共有多少个数据项
    public int getCount() {
        if (list == null) return 0;
        else return list.size();
    }

    @Override
    // 获得某一个数据项
    public Object getItem(int i) {
        if (list == null) return null;
        else return list.get(i);
    }

    @Override
    // 获得数据项的布局样式，
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // 新声明一个View变量和ViewHolder变量
        View convertView;
        ViewHolder viewHolder;

        // 当View为空时才加载布局，并且创建一个ViewHolder，获得布局中的两个控件
        if (view == null) {
            // 通过inflate方法加载布局，context这个参数需要使用这个adapter的Activity传入
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            viewHolder = new ViewHolder();
            viewHolder.first_name = (TextView) convertView.findViewById(R.id.first_name);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            // setTag方法是将处理好的viewHolder放入view中
            convertView.setTag(viewHolder);
        } else { // 否则，让convertView等于view, 然后从中取出ViewHolder即可
            convertView = view;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 从ViewHolder中取出对象，然后负责给他们
        viewHolder.first_name.setText(list.get(i).getFirst_name());
        viewHolder.name.setText(list.get(i).getName());
        return convertView;
    }

    private class ViewHolder {
        public TextView first_name;
        public TextView name;
    }
}
