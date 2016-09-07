package it.concorso.sanstino.stradesicure.widget.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.concorso.android.stradesicure.R;
import it.concorso.sanstino.stradesicure.model.Category;
import it.concorso.sanstino.stradesicure.model.quiz.TrueFalseQuiz;

@SuppressLint("ViewConstructor")
public class TrueFalseQuizView extends AbsQuizView<TrueFalseQuiz> {

    private static final String KEY_SELECTION = "SELECTION";
    private static final LinearLayout.LayoutParams LAYOUT_PARAMS =
            new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);

    static {
        LAYOUT_PARAMS.gravity = Gravity.CENTER;
    }

    private boolean mAnswer;
    private View mAnswerTrue;
    private View mAnswerFalse;

    public TrueFalseQuizView(Context context, Category category, TrueFalseQuiz quiz) {
        super(context, category, quiz);
    }

    @Override
    protected View createQuizContentView() {
        final ViewGroup container = (ViewGroup) getLayoutInflater().inflate(
                R.layout.quiz_radio_group_true_false, this, false);

        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.answer_true:
                        mAnswer = true;
                        break;
                    case R.id.answer_false:
                        mAnswer = false;
                        break;
                }
                allowAnswer();
            }
        };

        mAnswerTrue = container.findViewById(R.id.answer_true);
        mAnswerTrue.setOnClickListener(clickListener);
        mAnswerFalse = container.findViewById(R.id.answer_false);
        mAnswerFalse.setOnClickListener(clickListener);
        return container;
    }

    @Override
    protected boolean isAnswerCorrect() {
        return getQuiz().isAnswerCorrect(mAnswer);
    }

    @Override
    public Bundle getUserInput() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_SELECTION, mAnswer);
        return bundle;
    }

    @Override
    public void setUserInput(Bundle savedInput) {
        if (savedInput == null) {
            return;
        }
        final boolean tmpAnswer = savedInput.getBoolean(KEY_SELECTION);
        performSelection(tmpAnswer ? mAnswerTrue : mAnswerFalse);
    }

    private void performSelection(View selection) {
        selection.performClick();
        selection.setSelected(true);
    }
}
