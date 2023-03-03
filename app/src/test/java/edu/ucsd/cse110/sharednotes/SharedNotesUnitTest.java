package edu.ucsd.cse110.sharednotes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import edu.ucsd.cse110.sharednotes.model.Note;
import edu.ucsd.cse110.sharednotes.model.NoteAPI;

@RunWith(AndroidJUnit4.class)
public class SharedNotesUnitTest {

    NoteAPI api;

    @Before
    public void createDb() {
        api = NoteAPI.provide();
    }

    @Test
    public void sampleTest() {
        Log.d("Hello", "World");
    }

    @Test
    public void pingTest() {
        String msg = "Hello, world!";
        String response = api.echo(msg);
        assertEquals(msg, response);
    }

    @Test
    public void putGetNoteTest() {
        var time = System.currentTimeMillis();

        var note = new Note("Test note 198", "This is a test note.", time);
        var result = api.putNote(note);
        assertNotNull(result);

        Note response = api.getNote(note.title);

        assertEquals(note.title, response.title);
        assertEquals(note.content, response.content);

        // Don't check timestamp cuz it's scuffed
    }

    @Test
    public void noteOutdatedTest() {
        Note n = new Note("Test note 199", "This is a test note.", System.currentTimeMillis());

        var put1 = api.putNote(n);
    }

}
