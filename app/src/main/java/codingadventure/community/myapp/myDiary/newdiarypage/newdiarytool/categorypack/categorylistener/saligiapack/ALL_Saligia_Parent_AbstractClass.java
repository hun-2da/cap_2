package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.saligiapack;

import jp.wasabeef.richeditor.RichEditor;



public abstract class ALL_Saligia_Parent_AbstractClass {
    RichEditor richEditor;
    String target;
    String question1;
    String question2;
    String question3;
    public enum SaligiaType {
        ENVY,
        GLUTTONY,
        GREED,
        LUST,
        PRIDE,
        SLOTH,
        WRATH
    }
    public ALL_Saligia_Parent_AbstractClass(SaligiaType type, String target, RichEditor richEditor) {
        this.richEditor = richEditor;
        this.target = target;

        switch (type) {
            case ENVY:
                this.question1 = "오늘 나를 질투하게 만든 구체적인 상황이나 사건은 무엇인가요?";
                this.question2 = "그 상황에서 나는 무엇을 보거나 듣고 질투를 느꼈나요?";
                this.question3 = "나는 그 질투의 감정을 어떻게 관리할 수 있었을까요? 앞으로 비슷한 상황에서 어떻게 대처할 수 있을까요?";
                break;
            case GLUTTONY:
                this.question1 = "오늘 폭식을 하게 된 구체적인 상황은 무엇이었나요?";
                this.question2 = "폭식을 유발하는 상황이나 감정을 인식하는 데 있어 어떤 패턴을 발견할 수 있나요?";
                this.question3 = "폭식을 통해 충족하려고 한 욕구나 필요는 무엇이었나요? 이를 건강한 방식으로 충족시킬 다른 방법은 무엇일까요?";
                break;
            case GREED:
                this.question1 = "오늘 나는 무엇을 갖고 싶어 했나요? 그것이 왜 나에게 중요한가요?";
                this.question2 = "원하는 것을 얻기 위해 어떤 생각이나 행동을 했나요?";
                this.question3 = "나의 탐욕이 나의 결정이나 행동에 어떤 영향을 미쳤나요?";
                break;
            case LUST:
                this.question1 = "오늘 내가 색욕을 느낀 상황은 무엇이었나요?";
                this.question2 = "이러한 감정이 나의 행동에 어떤 영향을 미쳤나요?";
                this.question3 = "앞으로 색욕을 더 건강하게 관리하기 위해 어떤 방법을 시도해 볼 수 있을까요?";
                break;
            case PRIDE:
                this.question1 = "오늘 나는 어떤 것에 대해 오만함을 느꼈나요?";
                this.question2 = "내 오만함이 나의 생각이나 행동에 어떤 영향을 미쳤나요?";
                this.question3 = "내 오만함이 다른 사람들과의 관계에 어떤 영향을 미쳤나요?";
                break;
            case SLOTH:
                this.question1 = "오늘 나는 어떤 상황에서 나태를 느꼈나요?";
                this.question2 = "나태가 나의 일상이나 목표 달성에 어떤 영향을 미쳤나요?";
                this.question3 = "나태를 극복하기 위해 어떤 노력을 했나요? 더 효과적인 방법이 있었을까요?";
                break;
            case WRATH:
                this.question1 = "오늘 나를 화나게 만든 구체적인 사건이 무엇인가요?";
                this.question2 = "나는 그 상황에서 어떻게 반응했나요? 내 반응이 상황을 더 나아지게 했나요, 아니면 더 악화시켰나요?";
                this.question3 = "나는 분노를 느꼈을 때 어떻게 대처할 수 있었나요?";
                break;
        }

        setEditor();
    }

    void setEditor() {
        String htmlContent = "<div>" +
                "<h2>" + target + "</h2>" +
                "<p><strong>" + question1 + ":</strong><br><br> " + "</p>" +
                "<p><strong>" + question2 + ":</strong><br><br> " + "</p>" +
                "<p><strong>" + question3 + ":</strong><br><br> " + "</p>" +
                "</div>";

        richEditor.setHtml(htmlContent);
    }
}