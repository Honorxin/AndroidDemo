package com.ccx.demochenapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author chuangxin.chen
 */
public class MeFragment extends Fragment implements View.OnClickListener {

    private TextView name_text;
    private TextView phone_text;
    private TextView sex_choice;
    private TextView app_text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_me, container, false);
        Log.d("MeFragment", "onCreate: " + inflate);

        name_text = inflate.findViewById(R.id.name_text);
        phone_text = inflate.findViewById(R.id.phone_text);
        sex_choice = inflate.findViewById(R.id.sex_choice);
        app_text = inflate.findViewById(R.id.app_text);

        //获取共享信息
        SharedPreferences info = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
        String name = info.getString("name", "");
        String phone = info.getString("phoneNumber", "");
        String sex = info.getString("sex", "");
        name_text.setText(name);
        phone_text.setText(phone);
        sex_choice.setText(sex);
        name_text.setOnClickListener(this);
        phone_text.setOnClickListener(this);
        sex_choice.setOnClickListener(this);
        app_text.setOnClickListener(this);
        return inflate;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.name_text) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("请输入姓名");
            View dialogNameView = getLayoutInflater().inflate(R.layout.input_info, null);
            builder.setView(dialogNameView);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText input_name = dialogNameView.findViewById(R.id.input_name);
                    String name = input_name.getText().toString();
                    //获取SharedPreferences对象
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("name", name);
                    edit.apply();
                    name_text.setText(name);
                    Log.d("SP_name", name);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), "继续操作", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create().show();
        } else if (v.getId() == R.id.phone_text) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("请输入电话");
            View dialogNameView = getLayoutInflater().inflate(R.layout.input_phoneinfo, null);
            builder.setView(dialogNameView);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    EditText input_phone = dialogNameView.findViewById(R.id.input_phone);
                    String phoneNumber = input_phone.getText().toString();
                    //获取SharedPreferences对象
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();

                    edit.putString("phoneNumber", phoneNumber);
                    edit.apply();
                    phone_text.setText(phoneNumber);
                    Log.d("SP_phone", phoneNumber);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), "继续操作", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create().show();
        } else if (v.getId() == R.id.sex_choice) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("请选择性别");
            View dialogNameView = getLayoutInflater().inflate(R.layout.input_sexinfo, null);
            builder.setView(dialogNameView);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    RadioGroup sexRadioGroup = dialogNameView.findViewById(R.id.sexRadioGroup);
                    int checkedRadioButtonId = sexRadioGroup.getCheckedRadioButtonId();
                    String sex = "";
                    if (R.id.none == checkedRadioButtonId) {
                        sex = "保密";
                    } else if (R.id.boy == checkedRadioButtonId) {
                        sex = "男";
                    } else if (R.id.girl == checkedRadioButtonId) {
                        sex = "女";
                    } else {
                        Toast.makeText(getActivity(), "您未选择性别", Toast.LENGTH_SHORT).show();
                    }

                    //获取SharedPreferences对象
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();

                    edit.putString("sex", sex);
                    edit.apply();
                    sex_choice.setText(sex);
                    Log.d("SP_sex", sex);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), "继续操作", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create().show();
        } else if (v.getId() ==R.id.app_text) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("关于app");
            builder.setMessage("版本：1.0");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), "back", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create().show();
        }
    }
}