package it.concorso.sanstino.stradesicure.widget.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.concorso.android.stradesicure.R;
import it.concorso.sanstino.stradesicure.model.Category;
import it.concorso.sanstino.stradesicure.model.quiz.PickerQuiz;

@SuppressLint("ViewConstructor")
public final class PickerQuizView extends AbsQuizView<PickerQuiz> {

    private static final String KEY_ANSWER = "ANSWER";

    private TextView mCurrentSelection;
    private SeekBar mSeekBar;
    private int mStep;
    private int mMin;
    private int mProgress;

    public PickerQuizView(Context context, Category category, PickerQuiz quiz) {
        super(context, category, quiz);
    }

    @Override
    protected View createQuizContentView() {
        initStep();
        mMin = getQuiz().getMin();
        ScrollView layout = (ScrollView) getLayoutInflater().inflate(
                R.layout.quiz_layout_picker, this, false);
        mCurrentSelection = (TextView) layout.findViewById(R.id.seekbar_progress);
        mCurrentSelection.setText(String.valueOf(mMin));
        mSeekBar = (SeekBar) layout.findViewById(R.id.seekbar);
        mSeekBar.setMax(getSeekBarMax());
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setCurrentSelectionText(mMin + progress);
                allowAnswer();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                /* no-op */
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                /* no-op */
            }
        });
        return layout;
    }

    private void setCurrentSelectionText(int progress) {
        mProgress = progress / mStep * mStep;
        mCurrentSelection.setText(String.valueOf(mProgress));
    }

    @Override
    protected boolean isAnswerCorrect() {
        return getQuiz().isAnswerCorrect(mProgress);
    }

    private void initStep() {
        int tmpStep = getQuiz().getStep();
        //make sure steps are never 0
        if (0 == tmpStep) {
            mStep = 1;
        } else {
            mStep = tmpStep;
        }
    }

    @Override
    public Bundle getUserInput() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_ANSWER, mProgress);
        return bundle;
    }

    @Override
    public void setUserInput(Bundle savedInput) {
        if (null == savedInput) {
            return;
        }
        mSeekBar.setProgress(savedInput.getInt(KEY_ANSWER) - mMin);
    }

    /**
     * Calculates the actual max value of the SeekBar
     */
    private int getSeekBarMax() {
        final int absMin = Math.abs(getQuiz().getMin());
        final int absMax = Math.abs(getQuiz().getMax());
        final int realMin = Math.min(absMin, absMax);
        final int realMax = Math.max(absMin, absMax);
        return realMax - realMin;
    }
}
