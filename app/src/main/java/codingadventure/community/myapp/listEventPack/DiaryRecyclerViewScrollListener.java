package codingadventure.community.myapp.listEventPack;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class DiaryRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    /**리스트의 끝에서 몇 개의 아이템이 남았을 때 데이터를 더 불러올지를 결정하는 임곗값 */
    private int visibleThreshold = 7;

    /** 이전에 로드된 총 아이템 수를 저장하는 변수*/
    private int previousTotalItemCount = 0;

    /**현재 데이터 로딩 중인지 아닌지를 나타내는 플래그*/
    private boolean loading = true;

    /**RecyclerView의 레이아웃 매니저로, 아이템이 어떻게 배치될지를 관리*/
    private RecyclerView.LayoutManager layoutManager;

    public DiaryRecyclerViewScrollListener(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    /**onScrolled 메소드는 스크롤 이벤트 발생 시 호출. dx, dy는 각각 수평 및 수직 스크롤의 변화량.*/
    public void onScrolled(RecyclerView view, int dx, int dy) {

        //totalItemCount는 현재 RecyclerView에 로드된 총 아이템 수
        int totalItemCount = layoutManager.getItemCount();

        //현재 화면에 보이는 마지막 아이템의 위치를 결정
        int lastVisibleItemPosition = 0;


        //이 코드 조각은 RecyclerView의 레이아웃 매니저가 LinearLayoutManager의 인스턴스인지 확인하고, 그렇다면 LinearLayoutManager의 메소드 findLastVisibleItemPosition()을 사용하여 현재 화면에 보이는 마지막 아이템의 위치를 가져오는 로직
        if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }

        //데이터셋이 갱신되어 총 아이템 수가 줄어든 경우, 로딩 상태를 재설정
        if (totalItemCount < previousTotalItemCount) {
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        //새 데이터가 로드되고 로딩이 완료된 경우, 로딩 상태를 false로 설정하고 아이템 수를 업데이트
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        //현재 로딩 중이 아니고, 스크롤 위치가 리스트 끝에 가까워지면 onLoadMore 메소드를 호출하여 추가 데이터를 로드
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            onLoadMore(totalItemCount, view);
            loading = true;
        }
    }

    // 페이지 추가 로드를 위한 추상 메서드
    public abstract void onLoadMore(int totalItemsCount, RecyclerView view);

}
