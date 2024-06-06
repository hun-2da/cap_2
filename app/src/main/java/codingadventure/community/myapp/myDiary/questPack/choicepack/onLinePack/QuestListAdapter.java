package codingadventure.community.myapp.myDiary.questPack.choicepack.onLinePack;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.FirebasePack.ObjectPack.DiaryCommentWrite;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserQuestWrite;
import codingadventure.community.myapp.FirebasePack.QueryPack.UserQuestQuery;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.listEventPack.OnItemClickListener;
import codingadventure.community.myapp.myDiary.Diary_Main;
import codingadventure.community.myapp.myDiary.diartlistPage.DiaryList_Main;
import codingadventure.community.myapp.myDiary.questPack.Quest_DiaryAdapter;

public class QuestListAdapter extends RecyclerView.Adapter<QuestListAdapter.ViewHolder>{
    ArrayList<DiaryCommentWrite> questBox = new ArrayList<>();
    String DocumentID;
    public static int count = 0;



    public QuestListAdapter( ArrayList<DiaryCommentWrite> questBox){
      this.questBox = questBox;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.quset_choice_resource,parent,false);



        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DiaryCommentWrite item = questBox.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return  questBox.size();
    }
    public void setItems(ArrayList<DiaryCommentWrite> questBox){
        this.questBox = questBox;
    }
    public void clearBox(){
        questBox.clear();
    }
    public void setDocumentID(String DocumentID){
        this.DocumentID = DocumentID;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView TitleText;
        TextView ContentText;
        Button OKButton;
        TextView DateText;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TitleText = itemView.findViewById(R.id.onlineResource_Title_TextView);
            ContentText = itemView.findViewById(R.id.onlineResource_Content_TextView);
            DateText = itemView.findViewById(R.id.onlineResource_Date_TextView);
            imageView = itemView.findViewById(R.id.onlineResource_quest_imageView);

            OKButton = itemView.findViewById(R.id.onlineResource_OK_Button);
            OKButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DocumentReference documentReference = UserQuestQuery.getDiaryPath();



                    Map<String, Object> map = new HashMap<>();

                    map.put(FirebaseDBNameClass.USER_DIARY_QUEST,FirebaseDBNameClass.DIARY_QUEST_ONLINE);
                    map.put(FirebaseDBNameClass.COMMENT_MissionProgress,false);


                    showDialog(v.getContext(),documentReference,map);

                }
            });


        }
        public void setItem(DiaryCommentWrite item){
            TitleText.setText(item.getCommentWriterName());
            ContentText.setText(item.getContent());
            DateText.setText(setDate(item.getDate()));

            int i_res = 0;
            switch(count){
                case 0: i_res = R.drawable.quest_yellow; break;
                case 1: i_res = R.drawable.quest_red;  break;
                case 2: i_res = R.drawable.quest_blue; break;
            }
            imageView.setImageResource(i_res);
            count++;
        }

        private void showDialog(Context context,DocumentReference documentReference,Map<String, Object> map) {

            String Name = TitleText.getText().toString();
            String Content = ContentText.getText().toString();

            map.put(FirebaseDBNameClass.COMMENT_Writer_NAME, Name);
            map.put(FirebaseDBNameClass.COMMENT_CONTENT,Content);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(Name + context.getString(R.string.QuestListAdapter_Dialog_name))
                    .setMessage(Content)
                    .setPositiveButton(context.getString(R.string.QuestListAdapter_Dialog_ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            UserQuestQuery.setQuest(documentReference,map,context);
                            DiaryList_Main.blurLayout.setVisibility(View.INVISIBLE);
                            DiaryList_Main.questLinearLayout.setVisibility(View.INVISIBLE);

                        }
                    })
                    .setNegativeButton(context.getString(R.string.QuestListAdapter_Dialog_no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }


        private String setDate(Date date){
            String pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.format(date);
        }


    }

}
