package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.editorpack;

import android.graphics.Rect;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.Toast;

public class Editor_KeyboardListener implements ViewTreeObserver.OnGlobalLayoutListener{
    FrameLayout bubble_layout;

    public Editor_KeyboardListener(FrameLayout bubble_layout) {
        this.bubble_layout = bubble_layout;
    }

    @Override
    public void onGlobalLayout() {
        Rect r = new Rect();
        //r을 화면의 가시 영역으로 설정
        bubble_layout.getWindowVisibleDisplayFrame(r);

        int screenHeight = bubble_layout.getRootView().getHeight();
        int keypadHeight = screenHeight - r.bottom;

        if (keypadHeight > screenHeight * 0.15) { // 키보드 높이가 화면 높이의 15% 이상이면 키보드가 열린 것으로 간주
            Toast.makeText(bubble_layout.getContext(), "Keyboard is open", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(bubble_layout.getContext(), "Keyboard is closed", Toast.LENGTH_SHORT).show();
        }
    }
}
