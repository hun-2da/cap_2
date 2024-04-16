package codingadventure.community.myapp.myDiary.diartlistPage.choicetype.listTypePack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import codingadventure.community.myapp.R;
import codingadventure.community.myapp.myDiary.Diary_db_Write;
import jp.wasabeef.richeditor.RichEditor;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder>{
    ArrayList<Diary_db_Write> diaryBox = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.diarylist_recycleritem_resource,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Diary_db_Write item = diaryBox.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return diaryBox.size();
    }

    /**item을 하나씩(객체를 하나씩 저장하는 메소드 _ 현재의 경우 가져와서 사용함으로 굳이 필요 x) */
    public void addItem(Diary_db_Write item){
        diaryBox.add(item);
    }
    /**해당 메소드를 사용하여 list를 초기화 및 사용할 예정*/
    public void setItems(ArrayList<Diary_db_Write> diaryBox){
        this.diaryBox = diaryBox;
    }
    public Diary_db_Write getItem(int position){
        return diaryBox.get(position);
    }
    /**다이어리 변경 (사용 예정 없)*/
    public void setItem(int position,Diary_db_Write item){
        diaryBox.set(position, item);
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        RichEditor richEditor;
        TextView textView;
        Switch switch_Button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.listCycler_category_imageView);
            richEditor = itemView.findViewById(R.id.listCycler_content_RecyclerRichEditor);
            textView = itemView.findViewById(R.id.listCycler_title_TextView);
            switch_Button = itemView.findViewById(R.id.listCycler_choice_switchButton);

        }
        public void setItem(Diary_db_Write item){
            String category = item.getCategory();
            imageView.setImageResource(getCategory_res(category));

            richEditor.setHtml(item.getContent());

            textView.setText(item.getTitle());

            switch_Button.setChecked(item.isUser_choice());


        }
        private int getCategory_res(String category){
            int category_id = 0;
            if(category.equals("PRIDE")){
                category_id = R.drawable.icon7_pride;
            }else if(category.equals("GREED")){
                category_id = R.drawable.icon7_greed;
            }else if(category.equals("LUST")){
                category_id = R.drawable.icon7_lust;
            }
            else if(category.equals("ENVY")){
                category_id = R.drawable.icon7_envy;
            }
            else if(category.equals("GLUTTONY")){
                category_id = R.drawable.icon7_glutth;
            }
            else if(category.equals("WRATH")){
                category_id = R.drawable.icon7_lath;
            }
            else if(category.equals("SLOTH")){
                category_id = R.drawable.icon7_sloth;
            }
            return category_id;
        }
    }
}
