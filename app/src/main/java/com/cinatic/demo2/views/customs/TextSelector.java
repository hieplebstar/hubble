package com.cinatic.demo2.views.customs;


import android.content.Context;

public class TextSelector {

    private CharSequence checkedText;
    private CharSequence loadingText;
    private CharSequence uncheckedText;

    public TextSelector() {
    }

    public TextSelector(CharSequence checkedText, CharSequence loadingText, CharSequence uncheckedText) {
        super();
        this.checkedText = checkedText;
        this.loadingText = loadingText;
        this.uncheckedText = uncheckedText;
    }

    public TextSelector(Context context, int checkedText, int uncheckedText) {
        super();
        this.checkedText = context.getText(checkedText);
        this.uncheckedText = context.getText(uncheckedText);
    }

    public TextSelector(Context context, int checkedText, int uncheckedText, int loadingText) {
        super();
        this.loadingText = context.getText(loadingText);
        this.checkedText = context.getText(checkedText);
        this.uncheckedText = context.getText(uncheckedText);
    }

    public CharSequence getSelectedText() {
        return checkedText;
    }

    public CharSequence getLoadingText() {
        return loadingText;
    }

    public CharSequence getText(boolean selected) {
        return selected ? getSelectedText() : getUnselectedText();
    }

    public CharSequence getUnselectedText() {
        return uncheckedText;
    }

    public void setLoadingText(CharSequence loadingText) {
        this.loadingText = loadingText;
    }

    public void setSelectedText(CharSequence checkedText) {
        this.checkedText = checkedText;
    }

    public void setUnselectedText(CharSequence uncheckedText) {
        this.uncheckedText = uncheckedText;
    }
}
