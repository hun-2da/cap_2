package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack.editorlistenerPack;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.Bubble_ClickListener;
import jp.wasabeef.richeditor.RichEditor;


/**저장버튼, html코드를 가져오기위한 버튼*/

public class SaveButtonListener implements View.OnClickListener {
    RichEditor richEditor;
    EditText editText;

    /**계정 관련 파이어베이스*/
    FirebaseAuth mAuth;

    /**유저 캐시정보*/
    FirebaseUser currentUser;


    /**클라우드 스토리지 db접근용*/
    FirebaseFirestore firestore_db;


    /**캐시에 저장되어있는 아이디 string*/
    String fieldValue ="";


    public SaveButtonListener(RichEditor richEditor, EditText editText) {
        this.richEditor = richEditor;
        this.editText = editText;
    }

    @Override
    public void onClick(View v) {
        String title = editText.getText().toString();
        String content = richEditor.getHtml();

        Bubble_ClickListener.diaryDbWrite.setTitle(title);
        Bubble_ClickListener.diaryDbWrite.setContent(content);

        getDB();
        //getUserId();
        Log.e("범인은 누구",currentUser.getUid());

    }
    /**초기화용 메소드*/
    private void getDB(){
        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();
        firestore_db = FirebaseFirestore.getInstance();

        firestore_db.collection("User")
                .document(currentUser.getEmail())
                .collection("Diary")
                .document(Bubble_ClickListener.diaryDbWrite.getTitle()+Bubble_ClickListener.diaryDbWrite.getDiary_date())
                .set(Bubble_ClickListener.diaryDbWrite);    // 컬렉션에 저장을 위한 코드

        //if(mAuth == null || currentUser == null || firestore_db == null) Log.e("범인은 누구","ㅁㄴㅇㄹ ");

    }
    /*private void getUserId(){
        firestore_db.collection("User").document(currentUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    Log.e("문제업음","해당");
                    fieldValue = documentSnapshot.getString("user_id");
                    firestore_db.collection("User").document(fieldValue).set(Bubble_ClickListener.diaryDbWrite);    // 컬렉션에 저장을 위한 코드
                    Log.e("문제업음2","해당");
                    return;
                } else {
                    Log.e("문제발생2","해당 id 없");
                   return;
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Log.e("문제발생",e.toString());
            }
        });

    }*/

}
