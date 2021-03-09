package seedu.address.model.session;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.session.exceptions.IllegalArgumentException;

public class SessionDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        String dateValue = "2021-01-01";
        String timeValue = "23:59";
        assertThrows(NullPointerException.class, () -> new SessionDate(null, null));
        assertThrows(NullPointerException.class, () -> new SessionDate(dateValue, null));
        assertThrows(NullPointerException.class, () -> new SessionDate(null, timeValue));
    }

    @Test
    public void isValidSessionDate() {
        String correctTimeValue = "23:59";

        // checks for empty input
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("", correctTimeValue));

        // checks for invalid month
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("2021-13-01", correctTimeValue));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("2021-00-01", correctTimeValue));

        // checks for invalid day
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("2021-12-00", correctTimeValue));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("2021-12-32", correctTimeValue));

        // checks for leap year
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("2021-02-29", correctTimeValue));

        // checks for err in date format
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("2021-13-001", correctTimeValue));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("2021-013-01", correctTimeValue));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("02021-13-01", correctTimeValue));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("20210-013-01", correctTimeValue));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("2021/13/01", correctTimeValue));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate("2021.13.01", correctTimeValue));

    }



    @Test
    public void isValidSessionTime() {
        String correctDateValue = "2021-01-01";

        // checks for empty input
        assertThrows(IllegalArgumentException.class, () -> new SessionDate(correctDateValue, ""));

        // checks for correct time format
        assertThrows(IllegalArgumentException.class, () -> new SessionDate(correctDateValue, "24:00"));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate(correctDateValue, "23:60"));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate(correctDateValue, "30:99"));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate(correctDateValue, "0000"));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate(correctDateValue, "00-00"));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate(correctDateValue, "00.00"));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate(correctDateValue, "12pm"));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate(correctDateValue, "12:52pm"));
        assertThrows(IllegalArgumentException.class, () -> new SessionDate(correctDateValue, "09:32am"));
    }
}
