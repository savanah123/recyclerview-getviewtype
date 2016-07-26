package safe.highwin.zgs.recyclerviewtype;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private List<Integer> mList;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        mList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(this, mList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
        initData();

        Observable.interval(1,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.d("MainActivity", "aLong:" + aLong);
                mList.clear();
                initData();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            int integer = new Random().nextInt(2);
            mList.add(integer);
        }
        adapter.notifyDataSetChanged();
    }

    public void click(View v) {
        mList.clear();
        initData();
    }
}
