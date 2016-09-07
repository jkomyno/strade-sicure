package it.concorso.sanstino.stradesicure.widget.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.concorso.android.stradesicure.R;
import it.concorso.sanstino.stradesicure.adapter.OptionsQuizAdapter;
import it.concorso.sanstino.stradesicure.helper.AnswerHelper;
import it.concorso.sanstino.stradesicure.model.Category;
import it.concorso.sanstino.stradesicure.model.quiz.ToggleTranslateQuiz;

@SuppressLint("ViewConstructor")
public class ToggleTranslateQuizView extends AbsQuizView<ToggleTranslateQuiz> {

    private static final String KEY_ANSWERS = "ANSWERS";

    private boolean[] mAnswers;
    private ListView mListView;

    public ToggleTranslateQuizView(Context context, Category category, ToggleTranslateQuiz quiz) {
        super(context, category, quiz);
        initAnswerSpace();
    }

    private void initAnswerSpace() {
        mAnswers = new boolean[getQuiz().getOptions().length];
    }

    @Override
    protected View createQuizContentView() {
        mListView = new ListView(getContext());
        mListView.setDivider(null);
        mListView.setSelector(R.drawable.selector_button);
        mListView.setAdapter(new OptionsQuizAdapter(getQuiz().getReadableOptions(),
                R.layout.item_answer));
        mListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toggleAnswerFor(position);
                if (view instanceof CompoundButton) {
                    ((CompoundButton) view).setChecked(mAnswers[position]);
                }

                allowAnswer();
            }
        });
        return mListView;
    }

    @Override
    protected boolean isAnswerCorrect() {
        final SparseBooleanArray checkedItemPositions = mListView.getCheckedItemPositions();
        final int[] answer = getQuiz().getAnswer();
        return AnswerHelper.isAnswerCorrect(checkedItemPositions, answer);
    }

    @Override
    public Bundle getUserInput() {
        Bundle bundle = new Bundle();
        bundle.putBooleanArray(KEY_ANSWERS, mAnswers);
        return bundle;
    }

    @Override
    public void setUserInput(Bundle savedInput) {
        if (savedInput == null) {
            return;
        }
        mAnswers = savedInput.getBooleanArray(KEY_ANSWERS);
        if (mAnswers == null) {
            initAnswerSpace();
            return;
        }
        ListAdapter adapter = mListView.getAdapter();
        for (int i = 0; i < mAnswers.length; i++) {
            mListView.performItemClick(mListView.getChildAt(i), i, adapter.getItemId(i));
        }
    }

    private void toggleAnswerFor(int answerId) {
        mAnswers[answerId] = !mAnswers[answerId];
    }
}
