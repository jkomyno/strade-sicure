package it.concorso.sanstino.stradesicure.model.quiz;

import it.concorso.sanstino.stradesicure.model.JsonAttributes;

/**
 * Available types of quizzes.
 * Maps {@link JsonAttributes.QuizType} to subclasses of {@link Quiz}.
 */
public enum QuizType {
    MULTI_SELECT(JsonAttributes.QuizType.MULTI_SELECT, MultiSelectQuiz.class),
    PICKER(JsonAttributes.QuizType.PICKER, PickerQuiz.class),
    SINGLE_SELECT(JsonAttributes.QuizType.SINGLE_SELECT, SelectItemQuiz.class),
    SINGLE_SELECT_ITEM(JsonAttributes.QuizType.SINGLE_SELECT_ITEM, SelectItemQuiz.class),
    TOGGLE_TRANSLATE(JsonAttributes.QuizType.TOGGLE_TRANSLATE, ToggleTranslateQuiz.class),
    TRUE_FALSE(JsonAttributes.QuizType.TRUE_FALSE, TrueFalseQuiz.class);

    private final String mJsonName;
    private final Class<? extends Quiz> mType;

    QuizType(final String jsonName, final Class<? extends Quiz> type) {
        mJsonName = jsonName;
        mType = type;
    }

    public String getJsonName() {
        return mJsonName;
    }

    public Class<? extends Quiz> getType() {
        return mType;
    }
}
