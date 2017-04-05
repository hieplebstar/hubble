package com.cinatic.demo2.views.customs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import java.util.Arrays;

import com.cinatic.demo2.hubble.R;

public class ProgressButton extends AppCompatButton {

    private static final int MAX_LEVEL = 10000;
    private static final long ANIM_PERIOD = 3000;

    private boolean autoDisableClickable;
    private boolean loading;
    private Drawable loadingDrawable;

    private Drawable[] oldCompoundDrawables;
    private TextSelector textSelector;

    public ProgressButton(Context context) {
        super(context);
        init(context);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void checkState() {
        if (textSelector != null) {
            if (!loading) {
                setText(textSelector.getText(isSelected()));
            } else {
                setText(textSelector.getLoadingText());
            }
        }
    }

    public void disableLoadingState() {
        setText(textSelector.getText(isSelected()));
        if (oldCompoundDrawables != null) {
            setCompoundDrawablesWithIntrinsicBounds(oldCompoundDrawables[0],
                    oldCompoundDrawables[1], oldCompoundDrawables[2],
                    oldCompoundDrawables[3]);
        }
        if (autoDisableClickable) {
            setClickable(true);
        }
        loading = false;
    }

    public void enableLoadingState() {
        setText(textSelector.getLoadingText());
        oldCompoundDrawables = Arrays.copyOf(getCompoundDrawables(), 4);
        setCompoundDrawables(null, null, null, null);
        if (autoDisableClickable) {
            setClickable(false);
        }
        loading = true;
    }

    private void init(Context context) {
        textSelector = new TextSelector();
        loadingDrawable = null;
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProgressButton);

        if (a.hasValue(R.styleable.ProgressButton_loadingDrawable)) {
            loadingDrawable = a.getDrawable(R.styleable.ProgressButton_loadingDrawable);
        } else {
            loadingDrawable = getResources().getDrawable(R.drawable.apptheme_progress_medium_holo);
        }

        TextSelector simpleTextSelector = new TextSelector();
        simpleTextSelector.setLoadingText("");
        simpleTextSelector.setSelectedText(getText());
        simpleTextSelector.setUnselectedText(getText());

        if (a.hasValue(R.styleable.ProgressButton_selectedText)) {
            simpleTextSelector.setSelectedText(a.getString(R.styleable.ProgressButton_selectedText));
        }
        if (a.hasValue(R.styleable.ProgressButton_unselectedText)) {
            simpleTextSelector.setUnselectedText(a.getString(R.styleable.ProgressButton_unselectedText));
        }
        if (a.hasValue(R.styleable.ProgressButton_loadingText)) {
            simpleTextSelector.setLoadingText(a.getString(R.styleable.ProgressButton_loadingText));
        }
        if (a.hasValue(R.styleable.ProgressButton_loading)) {
            loading = a.getBoolean(R.styleable.ProgressButton_loading, false);
        }
        if (a.hasValue(R.styleable.ProgressButton_autoDisableClickable)) {
            autoDisableClickable = a.getBoolean(R.styleable.ProgressButton_autoDisableClickable, false);
        }

        textSelector = simpleTextSelector;
        a.recycle();

        if (loadingDrawable != null) {
            loadingDrawable.setBounds(0, 0, loadingDrawable.getIntrinsicWidth(), loadingDrawable.getIntrinsicHeight());
            if (loading) {
                enableLoadingState();
            } else {
                disableLoadingState();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (loadingDrawable != null && loading) {
            canvas.save();
            int left = getWidth() / 2 - loadingDrawable.getMinimumWidth() / 2;
            int top = getHeight() / 2 - loadingDrawable.getMinimumHeight() / 2;
            canvas.translate(left, top);
            long time = getDrawingTime();
            float scale = (float) (time % ANIM_PERIOD +1 ) / (float) ANIM_PERIOD;
            int level = (int) (MAX_LEVEL * scale);
            loadingDrawable.setLevel(level);
            loadingDrawable.draw(canvas);
            canvas.restore();
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        checkState();
    }
}