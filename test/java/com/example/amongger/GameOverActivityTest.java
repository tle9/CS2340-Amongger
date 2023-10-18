package com.example.amongger;

import static org.junit.Assert.assertEquals;

import com.example.amongger.game.MockGameOverActivity;

import org.junit.Test;

/**
 * Sprint 4 Junit tests
 */
public class GameOverActivityTest {
    MockGameOverActivity activity = new MockGameOverActivity();
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
        assertEquals("GameOverActivity.java", activity.retrieveActivityState());
        assertEquals(false, activity.finish());
    }

    @Test
    public void testDisplayFinalScore() {
        activity.editScoreTest(100);

        int finalScore = activity.retrieveScore();
        assertEquals(finalScore, 100);
    }

    @Test
    public void testDisplayFinalScoreIfScoreIsZero() {
        activity.editScoreTest(0);

        int finalScore = activity.retrieveScore();
        assertEquals(finalScore, 0);
    }

    @Test
    public void testDisplayFinalScoreIfScoreIsNegativeVal() {
        activity.editScoreTest(-99);

        int finalScore = activity.retrieveScore();
        assertEquals(finalScore, 0);
    }

    @Test
    public void testScoreFromGameActNotSameScoreGameOverAct() {
        activity.editScoreFromGameAct(1000);
        activity.editScoreTest(100);
        assertEquals(false, activity.scoreRetriveFromGameAct());
    }

    @Test
    public void testScoreFromGameActSameScoreGameOverAct() {
        activity.editScoreFromGameAct(100);
        activity.editScoreTest(100);
        assertEquals(true, activity.scoreRetriveFromGameAct());
    }
}