package com.example.navigationview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.navigationview.R;
import com.example.navigationview.entity.Question;


import java.util.List;

public class QuestionAdapter extends BaseAdapter {
    private Context mContext;
    private List<Question> mData;

    public QuestionAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void refreshUi(List<Question> data){
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mData == null){
            return 0;
        }
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.listitem_question, null);
            holder = new ViewHolder();
            holder.tv_title = view.findViewById(R.id.tv_title);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        Question question = mData.get(i);
        holder.tv_title.setText(question.getTitle());
        return view;
    }

    private class ViewHolder{
        private TextView tv_title;
    }
}
