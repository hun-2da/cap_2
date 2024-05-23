package codingadventure.community.myapp.myDiary.questPack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserQuestWrite;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.listEventPack.OnItemClickListener;

public class Quest_DiaryAdapter extends RecyclerView.Adapter<Quest_DiaryAdapter.ViewHolder>{
    ArrayList<UserQuestWrite> diaryBox = new ArrayList<>();
    OnItemClickListener onItemClickListener;
    public static Context context;

    public Quest_DiaryAdapter(Context context, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.diary_quest_resource,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserQuestWrite item = diaryBox.get(position);
        holder.setItem(item);

        holder.bind(diaryBox.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return diaryBox.size();
    }

    /**item을 하나씩(객체를 하나씩 저장하는 메소드 _ 현재의 경우 가져와서 사용함으로 굳이 필요 x) */
    public void addItem(UserQuestWrite item){
        diaryBox.add(item);
    }
    /**해당 메소드를 사용하여 list를 초기화 및 사용할 예정*/
    public void setItems(ArrayList<UserQuestWrite> diaryBox){
        this.diaryBox = diaryBox;
    }
    public UserQuestWrite getItem(int position){
        return diaryBox.get(position);
    }
    /**다이어리 변경 (사용 예정 없)*/
    public void setItem(int position, UserQuestWrite item){
        diaryBox.set(position, item);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView categoryTextView;
        TextView questTextView;
        LinearLayout round_backColor;
        TextView questProgress;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.questResource_category_imageView);
            categoryTextView = itemView.findViewById(R.id.questResource_category_TextView);
            questTextView = itemView.findViewById(R.id.questResource_quest_TextView);
            round_backColor = itemView.findViewById(R.id.questResource_roundBack_LinearLayout);
            questProgress = itemView.findViewById(R.id.questResource_progress_TextView);

        }

        public void setItem(UserQuestWrite item){
            String mission = item.getMission();
            if(mission != null)
                questTextView.setText(mission);
            getCategoryStyle(item);

            if(item.isMissionProgress()){
                questProgress.setText(itemView.getContext().getString(R.string.quest_list_true));
            }else{
                questProgress.setText(itemView.getContext().getString(R.string.quest_list_false));
            }
        }
        private void getCategoryStyle(UserQuestWrite item){
            String category = item.getCategory();
            int roundColorId = 0;
            int drawableId = 0;

            if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_PRIDE)){
                drawableId = R.drawable.community_diaryimage_pride;
                roundColorId = R.drawable.rounded_border_pride;
            }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_GREED)){
                drawableId = R.drawable.community_diaryimage_greed;
                roundColorId = R.drawable.rounded_border_greed;
            }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_LUST)){
                drawableId = R.drawable.community_diaryimage_lust;
                roundColorId = R.drawable.rounded_border_lust;
            }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_ENVY)){
                drawableId = R.drawable.community_diaryimage_envy;
                roundColorId = R.drawable.rounded_border_envy;
            }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_GLUTTONY)){
                drawableId = R.drawable.community_diaryimage_gluth;
                roundColorId = R.drawable.rounded_border_gluth;
            }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_WRATH)){
                drawableId = R.drawable.community_diaryimage_wrath;
                roundColorId = R.drawable.rounded_border_wrath;
            }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_SLOTH)){
                drawableId = R.drawable.community_diaryimage_sloth;
                roundColorId = R.drawable.rounded_border_sloth;
            }
            imageView.setImageResource(drawableId);
            round_backColor.setBackground(ContextCompat.getDrawable(context,roundColorId));
            if(category != null)
                categoryTextView.setText(category);

        }

        public void bind(final UserQuestWrite item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, getAdapterPosition());

                }
            });
        }



    }

}
