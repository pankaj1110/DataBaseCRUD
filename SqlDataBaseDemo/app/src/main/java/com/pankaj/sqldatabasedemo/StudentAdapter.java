package com.pankaj.sqldatabasedemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class StudentAdapter extends BaseAdapter {
    Context context;
    ArrayList<Student> studentList;
    LayoutInflater lf;
    Holder holder;

    public StudentAdapter(Context context, ArrayList<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
        lf= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null)
        {
            convertView=lf.inflate(R.layout.row_student,parent,false);
            holder=new Holder();
            holder.txtRoll= (TextView) convertView.findViewById(R.id.txtRoll);
            holder.txtName= (TextView) convertView.findViewById(R.id.txtName);
            holder.txtCity= (TextView) convertView.findViewById(R.id.txtCity);
            holder.imgDelete= (ImageView) convertView.findViewById(R.id.imgDelete);
            holder.imgEdit= (ImageView) convertView.findViewById(R.id.imgEdit);

            convertView.setTag(holder);
        }else
        {
            holder= (Holder) convertView.getTag();
        }

        Student S=studentList.get(position);
        holder.txtRoll.setText(""+S.getRoll());
        holder.txtName.setText(""+S.getName());
        holder.txtCity.setText(""+S.getCity());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Onclicked "+studentList.get(position).getName(),Toast.LENGTH_SHORT).show();
            }

        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setTitle("DELETE");
                alert.setMessage("Do you want to delete?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int roll=studentList.get(position).getRoll();
                        ((ShowActivity)context).deleteRecord(roll);
                        dialog.dismiss();
                    }
                });

                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();



            }
        });

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert=new AlertDialog.Builder(context);
                alert.setTitle("Edit");
                alert.setMessage("Do you want to edit?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int roll=studentList.get(position).getRoll();
                        String name=studentList.get(position).getName();
                        String city=studentList.get(position).getCity();

                        Intent i=new Intent(context,MainActivity.class);
                        i.putExtra("ROLL",roll);
                        i.putExtra("NAME",name);
                        i.putExtra("CITY",city);
                        i.putExtra("IsUpdate",true);
                        context.startActivity(i);

                        dialog.dismiss();
                    }
                });

                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();
            }
        });

        return convertView;
    }
    private class Holder {
        TextView txtRoll,txtName,txtCity;
        ImageView imgDelete,imgEdit;
    }
}
