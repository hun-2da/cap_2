package codingadventure.community.myapp.myCommunity;

import android.content.res.ColorStateList;
import android.graphics.Color;

import codingadventure.community.myapp.FirebasePack.FirebaseDBNameClass;
import codingadventure.community.myapp.R;

public class GetCategory {
    public static int getCategory_res(String category) {
        int category_id = 0;

        if (category.equals(FirebaseDBNameClass.DIARY_CATEGORY_PRIDE)) {
            category_id = R.drawable.community_diaryimage_pride;
        } else if (category.equals(FirebaseDBNameClass.DIARY_CATEGORY_GREED)) {
            category_id = R.drawable.community_diaryimage_greed;
        } else if (category.equals(FirebaseDBNameClass.DIARY_CATEGORY_LUST)) {
            category_id = R.drawable.community_diaryimage_lust;
        } else if (category.equals(FirebaseDBNameClass.DIARY_CATEGORY_ENVY)) {
            category_id = R.drawable.community_diaryimage_envy;
        } else if (category.equals( FirebaseDBNameClass.DIARY_CATEGORY_GLUTTONY)) {
            category_id = R.drawable.community_diaryimage_gluth;
        } else if (category.equals(FirebaseDBNameClass.DIARY_CATEGORY_WRATH)) {
            category_id = R.drawable.community_diaryimage_wrath;
        } else if (category.equals(FirebaseDBNameClass.DIARY_CATEGORY_SLOTH)) {
            category_id = R.drawable.community_diaryimage_sloth;
        }   else {

        }
        return category_id;
    }
    public static String getCategory_color(String category){
        String color="";
        int res = 0;

        if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_PRIDE)){
            color = FirebaseDBNameClass.CATEGORY_COLOR_PRIED;
        }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_GREED)){
            color = FirebaseDBNameClass.CATEGORY_COLOR_GREED;
        }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_LUST)){
            color = FirebaseDBNameClass.CATEGORY_COLOR_LUST;
        }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_ENVY)){
            color = FirebaseDBNameClass.CATEGORY_COLOR_ENVY;
        }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_GLUTTONY)){
            color = FirebaseDBNameClass.CATEGORY_COLOR_GLUTTONY;
        }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_WRATH)){
            color = FirebaseDBNameClass.CATEGORY_COLOR_WRATH;
        }else if(category.equals(FirebaseDBNameClass.DIARY_CATEGORY_SLOTH)){
            color = FirebaseDBNameClass.CATEGORY_COLOR_SLOTH;
        }
        return color;

    }
}
