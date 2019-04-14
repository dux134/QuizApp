package in.a_comic.quizapp.exam_list_util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import in.a_comic.quizapp.R;

public class ExamListAdapter extends RecyclerView.Adapter<ExamListAdapter.ViewHolder> {
    private ExamListAdapter.RecyclerClickListener listener;
    private ArrayList<ExamModel> list;
    private Context context;

    public ExamListAdapter(RecyclerClickListener listener, ArrayList<ExamModel> list, Context context) {
        this.listener = listener;
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_exam_list,parent,false);
        ViewHolder holder = new ViewHolder(view, listener);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        Glide.with(context)
                .load(list.get(position).getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerClickListener {
        void onClick(View view, int adapterPosition);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView image;
        private TextView title;
        private ExamListAdapter.RecyclerClickListener lv;

        public ViewHolder(View itemView, RecyclerClickListener listener) {
            super(itemView);
            image = itemView.findViewById(R.id.exam_list_imageView);
            title = itemView.findViewById(R.id.exam_list_textView);

            lv = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            lv.onClick(view,getAdapterPosition());
        }
    }
}
