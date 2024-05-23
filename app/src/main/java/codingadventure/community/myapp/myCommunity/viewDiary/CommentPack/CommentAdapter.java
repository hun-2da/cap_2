package codingadventure.community.myapp.myCommunity.viewDiary.CommentPack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;

import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.ObjectPack.DiaryCommentWrite;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myCommunity.CommunityTool.CommunityAdapter;
import codingadventure.community.myapp.myCommunity.GetCategory;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    ArrayList<DiaryCommentWrite> commentBox;

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void setItem(DiaryCommentWrite item) {

        }
    }

}
