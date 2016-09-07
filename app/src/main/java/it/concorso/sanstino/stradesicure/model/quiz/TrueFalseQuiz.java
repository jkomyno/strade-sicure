package it.concorso.sanstino.stradesicure.model.quiz;

import android.annotation.SuppressLint;
import android.os.Parcel;

import it.concorso.sanstino.stradesicure.helper.ParcelableHelper;

@SuppressLint("ParcelCreator")
public final class TrueFalseQuiz extends Quiz<Boolean> {

    public TrueFalseQuiz(String question, Boolean answer, boolean solved) {
        super(question, answer, solved);
    }

    @SuppressWarnings("unused")
    public TrueFalseQuiz(Parcel in) {
        super(in);
        setAnswer(ParcelableHelper.readBoolean(in));
    }

    @Override
    public String getStringAnswer() {
        return getAnswer().toString();
    }

    @Override
    public QuizType getType() {
        return QuizType.TRUE_FALSE;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        ParcelableHelper.writeBoolean(dest, getAnswer());
    }
}
