package com.example.amongger;

import static org.junit.Assert.assertEquals;

import com.example.amongger.game.MockWinActivity;

import org.junit.Test;

/**
 * Sprint 5 Junit tests
 */
public class WinActivityTest {
    MockWinActivity activity = new MockWinActivity();
    @Test
    public void testWhatIfContinueButtonClicked() {
        activity.contiButtonClicked();
        assertEquals("ConfigActivity.java", activity.retrieveActivityState());
        assertEquals(true, activity.finish());
    }

    @Test
    public void testWhatIfExitButtonClicked() {
        activity.exitButtonClicked();
        assertEquals("android.os.Process.killProcess(android.os.Process.myPid())", activity.retrieveActivityState());
        assertEquals(true, activity.finish());
    }


    @Test
    public void testWhatIfNoButtonWasClicked() {
        activity.noButtonClicked();
        assertEquals("WinActivity.java", activity.retrieveActivityState());
        assertEquals(false, activity.finish());
    }

    @Test
    public void testDisplayFinalScore() {
        activity.editScoreTest(100);

        int finalScore = activity.retrieveScore();
        assertEquals(finalScore, 100);
    }

    @Test
    public void testScoreFromGameActNotSameScoreWinAct() {
        activity.editScoreFromGameAct(1000);
        activity.editScoreTest(100);
        assertEquals(false, activity.scoreRetrieveFromGameAct());
    }

    @Test
    public void testScoreFromGameActSameScoreWinAct() {
        activity.editScoreFromGameAct(100);
        activity.editScoreTest(100);
        assertEquals(true, activity.scoreRetrieveFromGameAct());
    }

    @Test
    public void testDisplayFinalScoreIfScoreIsZero() {
        activity.editScoreTest(0);
        int finalScore = activity.retrieveScore();
        assertEquals(finalScore, 0);
    }

    @Test
    public void testTopScoreGreaterThanCurrentScore() {
        activity.displayTopScore(1000, 9000);
        assertEquals("Top Score: 9000", activity.retrieveTopScoreTextView());
        assertEquals("Your Score: 1000", activity.retrievePlayerScoreTextView());
    }

    @Test
    public void testTopScoreLessThanCurrentScore() {
        activity.displayTopScore(9999, 9000);
        assertEquals("Top Score: 9999", activity.retrieveTopScoreTextView());
        assertEquals(null, activity.retrievePlayerScoreTextView());
    }

    @Test
    public void testTopScoreEqualCurrentScore() {
        activity.displayTopScore(9, 9);
        assertEquals("Top Score: 9", activity.retrieveTopScoreTextView());
        assertEquals("Your Score: 9", activity.retrievePlayerScoreTextView());
    }

    @Test
    public void comprehensiveTopScoreTestIfScoreUpdateCorrectly() {
        activity.editScoreFromGameAct(1000);
        activity.editScoreTest(1000);
        activity.editTopScore(1000);
        assertEquals(true, activity.scoreRetrieveFromGameAct());
        assertEquals(true, activity.scoreRetrieveAndDisplayCorrectly());
    }

    @Test
    public void comprehensiveTopScoreTestIfScoreNotUpdateCorrectly() {
        activity.editScoreFromGameAct(0);
        activity.editScoreTest(900);
        activity.editTopScore(1000);
        assertEquals(false, activity.scoreRetrieveFromGameAct());
        assertEquals(false, activity.scoreRetrieveAndDisplayCorrectly());
    }
}