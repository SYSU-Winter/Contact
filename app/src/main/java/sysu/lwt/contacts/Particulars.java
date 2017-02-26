package sysu.lwt.contacts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 12136 on 2017/2/26.
 */

public class Particulars extends AppCompatActivity {
    RelativeLayout relativeLayout;
    ImageView back, star;
    TextView phone, attribution, title_name;
    ListView item_list;
    private boolean stars = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.particulars);
        find();
        processingMessage();
        details();
        More_process();
    }

    public void find() {
        relativeLayout = (RelativeLayout) findViewById(R.id.background_color);
        back = (ImageView) findViewById(R.id.back);
        star = (ImageView) findViewById(R.id.star);
        phone = (TextView) findViewById(R.id.phone_number);
        attribution = (TextView) findViewById(R.id.attribution);
        title_name = (TextView) findViewById(R.id.Title_name);
        item_list = (ListView) findViewById(R.id.list_item);
    }

    public void processingMessage() {
        // 接收数据
        Information information = (Information) this.getIntent().getSerializableExtra("information");
        // 处理数据
        relativeLayout.setBackgroundColor(Color.parseColor(information.getColor()));
        phone.setText(information.getPhone_number());
        attribution.setText(information.getAttribution());
        title_name.setText(information.getName());
    }

    // 处理星星和返回键
    public void details() {
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!stars) {
                    star.setImageResource(R.drawable.full_star);
                    stars = true;
                } else {
                    star.setImageResource((R.drawable.empty_star));
                    stars = false;
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void More_process() {
        String[] op = {"编辑联系人", "分享联系人", "加入黑名单", "删除联系人"};
        // 使用SimpleAdapter填充数据
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < op.length; i++) {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("op", op[i]);
            mapList.add(temp);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, mapList, R.layout.more,
                new String[]{"op"}, new int[]{R.id.more_to_do});
        item_list.setAdapter(simpleAdapter);
    }
}
