package it.concorso.sanstino.stradesicure.model;

public interface JsonAttributes {

    String ANSWER = "answer";
    String END = "end";
    String ID = "id";
    String MAX = "max";
    String MIN = "min";
    String NAME = "name";
    String OPTIONS = "options";
    String QUESTION = "question";
    String QUIZZES = "quizzes";
    String START = "start";
    String STEP = "step";
    String THEME = "theme";
    String TYPE = "type";
    String SCORES = "scores";
    String SOLVED = "solved";

    interface QuizType {

        String ALPHA_PICKER = "alpha-picker";
        String FILL_BLANK = "fill-blank";
        String FILL_TWO_BLANKS = "fill-two-blanks";
        String FOUR_QUARTER = "four-quarter";
        String MULTI_SELECT = "multi-select";
        String PICKER = "picker";
        String SINGLE_SELECT = "single-select";
        String SINGLE_SELECT_ITEM = "single-select-item";
        String TOGGLE_TRANSLATE = "toggle-translate";
        String TRUE_FALSE = "true-false";
    }
}
