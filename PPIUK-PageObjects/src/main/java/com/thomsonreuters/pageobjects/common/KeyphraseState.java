package com.thomsonreuters.pageobjects.common;

/**
 * Created by Pavel_Ardenka on 08/12/2016.
 * Class represents the collection of key phrases and their state. For example, "not selected" phrase equals to false
 * state; "present" - it is true state, and so on. If you need some additional key phrase, please just add it to
 * {@link Keyphrase} enumeration below.
 * Usage example:
 * <pre>
 * Feature: test
 *  Scenario: Keyphrase example
 *      When some step executed
 *      Then checkbox is selected
 * </pre>
 * <p>
 * The Java code in the step definition method:
 * </p>
 * <pre>
 * &#064;Then("^checkbox is (selected|not selected)$")
 * public void verifyCheckboxState(KeyphraseState expectedState) throws Throwable {
 *     assertEquals(somePage.getSomeCheckbox().isSelected(), expectedState.state());
 * }
 * </pre>
 */
public class KeyphraseState {

    private enum Keyphrase {
        SELECTED("selected", true),
        NOT_SELECTED("not selected", false),
        PRESENT("present", true),
        NOT_PRESENT("not present", false),
        ABSENT("absent", false),
        DISPLAYED("displayed", true),
        OPENED("opened", true),
        CLOSED("closed", false),
        ABLE("able", true),
        NOT_ABLE("not able", false),
        NOT_DISPLAYED("not displayed", false);

        private String phrase;
        private boolean state;

        Keyphrase(String phrase, boolean state) {
            this.phrase = phrase;
            this.state = state;
        }

        private String getPhrase() {
            return phrase;
        }

        private boolean getState() {
            return state;
        }

        private static Keyphrase getByPhrase(String keyPhrase) {
            for (Keyphrase oneKeyphrase : Keyphrase.values()) {
                if (oneKeyphrase.getPhrase().equalsIgnoreCase(keyPhrase)) {
                    return oneKeyphrase;
                }
            }
            throw new IllegalArgumentException("Key phrase '" + keyPhrase + "' is not specified in Keyphrase enum");
        }
    }

    private final Keyphrase keyphrase;

    public KeyphraseState(String test) {
        this.keyphrase = Keyphrase.getByPhrase(test);
    }

    public boolean isTrue() {
        return keyphrase.getState();
    }

    public String getPhrase() {
        return keyphrase.getPhrase();
    }
}
