package codingadventure.community.myapp.CommentPack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import codingadventure.community.myapp.FirebasePack.ObjectPack.DiaryCommentWrite;
import codingadventure.community.myapp.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    ArrayList<DiaryCommentWrite> commentBox;
    public static int number = 0;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.community_comment_resource,parent,false);

        return new CommentAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiaryCommentWrite item = commentBox.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return commentBox.size();
    }

    /**item을 하나씩(객체를 하나씩 저장하는 메소드 _ 현재의 경우 가져와서 사용함으로 굳이 필요 x) */
    public void addItem(DiaryCommentWrite item){
        commentBox.add(item);
    }
    /**해당 메소드를 사용하여 list를 초기화 및 사용할 예정*/
    public void setItems(ArrayList<DiaryCommentWrite> diaryBox){
        this.commentBox = diaryBox;
        number = 0;
    }
    public DiaryCommentWrite getItem(int position){
        return commentBox.get(position);
    }
    /**다이어리 변경 (사용 예정 없)*/
    public void setItem(int position, DiaryCommentWrite item){
        commentBox.set(position, item);
    }

    public void clearData() {
        commentBox.clear();
        notifyDataSetChanged();

    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView content;
        TextView date;


        ImageView profile; // 나중에 선택에 따른 변경 또는 일괄


        ImageButton like;   // 이벤트 리스너 연결
        ImageButton dislike;    // 이벤트 리스너 연결

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.commentResource_Name_TextView);
            content = itemView.findViewById(R.id.commentResource_Content_TextView);
            date = itemView.findViewById(R.id.commentResource_Date_TextView);
            profile = itemView.findViewById(R.id.commentResource_profile_ImageView);

            like = itemView.findViewById(R.id.commentResource_Like_button);
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),itemView.getContext().getString(R.string.commentAdapter_like_snakbar),Toast.LENGTH_SHORT).show();
                }
            });
            dislike = itemView.findViewById(R.id.commentResource_Dislike_button);
            dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),itemView.getContext().getString(R.string.commentAdapter_dislike_snakbar),Toast.LENGTH_SHORT).show();
                }
            });

        }
        public void setItem(DiaryCommentWrite item) {
            name.setText(item.getCommentWriterName());
            content.setText(item.getContent());
            date.setText(getDate(item.getDate()));

            profile.setImageResource(setImage());
            number++;
        }
        private int setImage(){

            switch(number){
                case 0 : return R.drawable.icon7_envy;
                case 1 : return R.drawable.icon7_glutth;
                case 2: return R.drawable.icon7_greed;
                case 3 : return R.drawable.icon7_lath;
                case 4 : return R.drawable.icon7_lust;
                case 5: return R.drawable.icon7_pride;
                case 6: return R.drawable.icon7_sloth;
            }

            return R.drawable.icon7_basics;
        }
        private String getDate(Date Date){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String formattedDate = dateFormat.format(Date);
            return formattedDate;
        }
    }

}
