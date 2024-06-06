package codingadventure.community.myapp.myCommunity.CommunityTool;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.R;
import codingadventure.community.myapp.listEventPack.OnItemClickListener;
import codingadventure.community.myapp.FirebasePack.ObjectPack.UserDiaryWrite;
import codingadventure.community.myapp.myCommunity.GetCategory;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder>{
    ArrayList<UserDiaryWrite> diaryBox = new ArrayList<>();
    OnItemClickListener onItemClickListener;

    public CommunityAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.communitylist_recycleritem_resource,parent,false);

        return new CommunityAdapter.ViewHolder(itemView);
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

    public void clearData() {
        diaryBox.clear();
        //notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        TextView title_textView;
        TextView content_textView;
        TextView limit_textView;
        TextView year_textView;
        TextView month_textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.CommunityResource_category_imageView);
            title_textView = itemView.findViewById(R.id.CommunityResource_title_textView);
            content_textView = itemView.findViewById(R.id.CommunityResource_content_textView);
            limit_textView = itemView.findViewById(R.id.CommunityResource_limit_textView);
            year_textView = itemView.findViewById(R.id.CommunityResource_year_textView);
            month_textView = itemView.findViewById(R.id.CommunityResource_month_textView);

        }

        public void setItem(UserDiaryWrite item) {
            String category = item.getCategory();
            imageView.setImageResource(GetCategory.getCategory_res(category));
            title_textView.setText(item.getTitle());

            String content = item.getContent();
            if(content !=null)
                content = Jsoup.parse(content).text();

            content_textView.setText(content);


            setDate(item.getDiaryDate());

            limit_textView.setText(item.getLimit()+"%");


        }


        private void setDate(Date date) {
            Calendar calendar = Calendar.getInstance();  // 캘린더 인스턴스 생성
            calendar.setTime(date);  // Date 객체를 Calendar로 설정

            int year = calendar.get(Calendar.YEAR);  // 연도 추출
            int month = calendar.get(Calendar.MONTH) + 1;  // 월 추출, 1을 더해 실제 월로 조정

            year_textView.setText(String.valueOf(year));
            month_textView.setText(String.valueOf(month));

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
