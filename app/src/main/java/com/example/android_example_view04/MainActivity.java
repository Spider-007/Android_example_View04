package com.example.android_example_view04;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final int READ_PERMISSION_CODE = 1;
    private RecyclerView mRecyclerView;
    private List<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {

        //初始化 rv
        mRecyclerView = findViewById(R.id.mRecyclerView);
        //判断权限 对数据赋值
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, READ_PERMISSION_CODE);
        } else {
            ReadContactAll();
        }
    }

    private void ReadContactAll() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String mContactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String mContactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    mList.add("电话：" + mContactName + "\n" + "手机号：" + mContactNumber);
                }
                LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(new MyRvAdapter(mList));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.wtf("SpiderLine", "ExceptionInfo-> " + e.getMessage() + e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder> {

        private List<String> mList;

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new MyViewHolder(mView);
        }

        public MyRvAdapter(List<String> mList) {
            this.mList = mList;
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.userName.setText(mList.get(position));

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView userName;
            TextView userNumber;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                userName = itemView.findViewById(R.id.name);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ReadContactAll();
                } else {
                    Toast.makeText(this, "Authority authorization failed", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
