package codingadventure.community.myapp.myDiary.newdiarypage.newdiarytool.categorypack.categorylistener.saligiapack;

import jp.wasabeef.richeditor.RichEditor;

public abstract class ALL_Saligia_Parent_AbstractClass {
    RichEditor richEditor;
    String target;
    String question1;
    String question2;
    String question3;

    public ALL_Saligia_Parent_AbstractClass(String target, String question1, String question2, String question3, RichEditor richEditor) {
        this.richEditor = richEditor;
        this.target = target;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        setEditor();
    }

    void setEditor(){
        String htmlContent = "<div>" +
                "<h2>" + target + "</h2>" +
                "<p><strong>"+question1+":</strong> " + "</p>" +
                "<p><strong>"+question2+":</strong> " + "</p>" +
                "<p><strong>"+question3+":</strong> " + "</p>" +
                "</div>";

        richEditor.setHtml(htmlContent);
    }
}
