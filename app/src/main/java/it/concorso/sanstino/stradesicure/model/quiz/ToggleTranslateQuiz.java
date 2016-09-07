package it.concorso.sanstino.stradesicure.model.quiz;

import android.annotation.SuppressLint;
import android.os.Parcel;

import java.util.Arrays;

import it.concorso.sanstino.stradesicure.helper.AnswerHelper;

@SuppressLint("ParcelCreator")
public final class ToggleTranslateQuiz extends OptionsQuiz<String[]> {

    private String[] mReadableOptions;

    public ToggleTranslateQuiz(String question, int[] answer, String[][] options, boolean solved) {
        super(question, answer, options, solved);
    }

    @SuppressWarnings("unused")
    public ToggleTranslateQuiz(Parcel in) {
        super(in);
        setAnswer(in.createIntArray());
        setOptions((String[][]) in.readSerializable());
    }

    @Override
    public QuizType getType() {
        return QuizType.TOGGLE_TRANSLATE;
    }

    @Override
    public String getStringAnswer() {
        return AnswerHelper.getAnswer(getAnswer(), getReadableOptions());
    }

    public String[] getReadableOptions() {
        //lazily initialize
        if (null == mReadableOptions) {
            final String[][] options = getOptions();
            mReadableOptions = new String[options.length];
            //iterate over the options and create readable pairs
            for (int i = 0; i < options.length; i++) {
                mReadableOptions[i] = createReadablePair(options[i]);
            }
        }
        return mReadableOptions;
    }

    private String createReadablePair(String[] option) {
        // results in "Part one <> Part two"
        return option[0] + " <> " + option[1];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeIntArray(getAnswer());
        dest.writeSerializable(getOptions());
    }

    @SuppressWarnings("RedundantIfStatement")
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ToggleTranslateQuiz)) {
            return false;
        }

        ToggleTranslateQuiz that = (ToggleTranslateQuiz) o;

        if (!Arrays.equals(getAnswer(), that.getAnswer())) {
            return false;
        }

        if (!Arrays.deepEquals(getOptions(), that.getOptions())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(getOptions());
        return result;
    }
}
