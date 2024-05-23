package codingadventure.community.myapp.FirebasePack.ObjectPack;

public class UserQuestWrite {
    private String Category;
    private int Quest;
    private String Mission;
    private String MissionWriter;
    private boolean MissionProgress;
    private String Content;

    public UserQuestWrite() {
    }

    public UserQuestWrite(String category, int quest, String mission, String missionWriter, boolean missionProgress, String content) {
        Category = category;
        Quest = quest;
        Mission = mission;
        MissionWriter = missionWriter;
        MissionProgress = missionProgress;
        Content = content;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getQuest() {
        return Quest;
    }

    public void setQuest(int quest) {
        Quest = quest;
    }

    public String getMission() {
        return Mission;
    }

    public void setMission(String mission) {
        Mission = mission;
    }

    public boolean isMissionProgress() {
        return MissionProgress;
    }

    public void setMissionProgress(boolean missionProgress) {
        MissionProgress = missionProgress;
    }

    public String getMissionWriter() {
        return MissionWriter;
    }

    public void setMissionWriter(String missionWriter) {
        MissionWriter = missionWriter;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
