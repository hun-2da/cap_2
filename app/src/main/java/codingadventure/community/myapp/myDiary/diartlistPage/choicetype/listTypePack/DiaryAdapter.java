package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;

import java.util.ArrayList;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.listEventPack.OnItemClickListener;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;


public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder>{
    ArrayList<UserDiaryWrite> diaryBox = new ArrayList<>();
    OnItemClickListener onItemClickListener;

    public DiaryAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.diarylist_recycleritem_resource,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserDiaryWrite item = diaryBox.get(position);
        holder.setItem(item);

        holder.bind(diaryBox.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return diaryBox.size();
    }

    /**item을 하나씩(객체를 하나씩 저장하는 메소드 _ 현재의 경우 가져와서 사용함으로 굳이 필요 x) */
    public void addItem(UserDiaryWrite item){
        diaryBox.add(item);
    }
    /**해당 메소드를 사용하여 list를 초기화 및 사용할 예정*/
    public void setItems(ArrayList<UserDiaryWrite> diaryBox){
        this.diaryBox = diaryBox;
    }
    public UserDiaryWrite getItem(int position){
        return diaryBox.get(position);
    }
    /**다이어리 변경 (사용 예정 없)*/
    public void setItem(int position, UserDiaryWrite item){
        diaryBox.set(position, item);
    }





    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView categoryImageView;
        ImageView borderLineImageView;

        View touchView;
        TextView contentTextView;
        //RichEditor richEditor;
        TextView textView;
        Switch switch_Button;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImageView = itemView.findViewById(R.id.listCycler_category_imageView);
            borderLineImageView = itemView.findViewById(R.id.listCycler_borderline_imageView);
            touchView = itemView.findViewById(R.id.listCycler_touch_View);

            contentTextView = itemView.findViewById(R.id.listCycler_content_cTextView);
            //richEditor = itemView.findViewById(R.id.listCycler_content_RecyclerRichEditor);
            textView = itemView.findViewById(R.id.listCycler_title_TextView);
            switch_Button = itemView.findViewById(R.id.listCycler_choice_switchButton);

        }

        public void setItem(UserDiaryWrite item){
            String category = item.getCategory();
            categoryImageView.setImageResource(getCategory_res(category));

            getBorderLine(item.getQuest());

            //richEditor.setHtml(item.getContent());
            String content = item.getContent();
            if(content !=null)
                content = Jsoup.parse(item.getContent()).text();
            contentTextView.setText(content);
            //richEditor.setEnabled(false);


            textView.setText(item.getTitle());

            boolean user_publicityStatus = item.isPublicityStatus();
            switch_Button.setText(
                    user_publicityStatus
                            ?
                            switch_Button.getContext().getString(R.string.diarylist_publicityStatus_on)
                            :
                            switch_Button.getContext().getString(R.string.diarylist_publicityStatus_off)
            );
            switch_Button.setChecked(user_publicityStatus);




        }
        private void getBorderLine(int questNum){
            switch (questNum){
                case FirebaseDBNameClass.DIARY_QUEST_ONLINE:
                   borderLineImageView.setImageResource(R.drawable.diarylist_item_borderline_red);
                   break;
            }

        }
        private int getCategory_res(String category){
            int category_id = 0;
            if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_PRIDE)){
                category_id = R.drawable.icon7_pride;
            }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_GREED)){
                category_id = R.drawable.icon7_greed;
            }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_LUST)){
                category_id = R.drawable.icon7_lust;
            }
            else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_ENVY)){
                category_id = R.drawable.icon7_envy;
            }
            else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_GLUTTONY)){
                category_id = R.drawable.icon7_glutth;
            }
            else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_WRATH)){
                category_id = R.drawable.icon7_lath;
            }
            else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_SLOTH)){
                category_id = R.drawable.icon7_sloth;
            }
            return category_id;
        }

        public void bind(final UserDiaryWrite item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, getAdapterPosition());

                }
            });
        }



    }
}
