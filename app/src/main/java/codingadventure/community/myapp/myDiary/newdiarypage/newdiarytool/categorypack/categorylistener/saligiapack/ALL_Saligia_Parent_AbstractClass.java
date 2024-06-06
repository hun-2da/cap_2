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
                this.question1 = "오늘 누구에게 질투를 느꼈고, 어떤 상황에서 그런 감정을 느꼈나요?";
                this.question2 = "이 질투로 인해 어떤 행동을 했나요?";
                this.question3 = "이 행동이 당신과 다른 사람들에게 어떤 영향을 미쳤나요?";
                break;
            case GLUTTONY:
                this.question1 = "오늘 누구와 함께 어떤 상황에서 폭식을 했나요?";
                this.question2 = "이 폭식으로 인해 어떤 행동을 했나요?";
                this.question3 = "이 행동이 당신의 일상에 어떤 영향을 미쳤나요?";
                break;
            case GREED:
                this.question1 = "오늘 누구에게 무엇을 강하게 원했나요?";
                this.question2 = "이 욕구를 충족시키기 위해 어떤 행동을 했나요?";
                this.question3 = "이 행동이 당신과 다른 사람들에게 어떤 영향을 미쳤나요?";
                break;
            case LUST:
                this.question1 = "오늘 누구에게 성적인 욕구를 느꼈고, 어떤 상황에서 그런 감정을 느꼈나요?";
                this.question2 = "이 욕구로 인해 어떤 행동을 했나요?";
                this.question3 = "이 행동이 당신과 다른 사람들에게 어떤 영향을 미쳤나요?";
                break;
            case PRIDE:
                this.question1 = "오늘 누구에게 자부심을 느꼈고, 어떤 상황에서 그런 감정을 느꼈나요?";
                this.question2 = "이 자부심으로 인해 어떤 행동을 했나요?";
                this.question3 = "이 행동이 당신과 다른 사람들에게 어떤 영향을 미쳤나요?";
                break;
            case SLOTH:
                this.question1 = "오늘 누구와 함께 어떤 상황에서 나태함을 느꼈나요?";
                this.question2 = "이 나태함으로 인해 어떤 행동을 했나요?";
                this.question3 = "이 행동이 당신의 일상과 목표에 어떤 영향을 미쳤나요?";
                break;
            case WRATH:
                this.question1 = "오늘 누구에게 화가 났고, 어떤 상황에서 그런 감정을 느꼈나요?";
                this.question2 = "이 화로 인해 어떤 행동을 했나요?";
                this.question3 = "이 행동이 당신과 다른 사람들에게 어떤 영향을 미쳤나요?";
                break;
        }

        setEditor();
    }

    void setEditor() {
        String htmlContent = "<div>" +
                "<h2>" + target + "</h2>" +
                "<p><strong>" + question1 + ":</strong><br><br><br><br>  " + "</p>" +
                "<p><strong>" + question2 + ":</strong><br><br><br><br>  " + "</p>" +
                "<p><strong>" + question3 + ":</strong><br><br><br><br>  " + "</p>" +
                "</div>";

        richEditor.setHtml(htmlContent);
    }
}