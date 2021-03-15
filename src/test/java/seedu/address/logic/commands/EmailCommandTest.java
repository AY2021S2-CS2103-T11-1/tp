package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;
import static seedu.address.testutil.TypicalStudents.getTypicalStudents;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EmailCommand.
 */
public class EmailCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_getEmails_success() throws CommandException {
        EmailCommand emailCommand = new EmailCommand();
        CommandResult commandResult = emailCommand.execute(model);
        String[] emails = commandResult.getFeedbackToUser().split(";");

        // first layer: check if length matches
        assertEquals(emails.length, getTypicalStudents().size());

        // second layer: check if all emails are in the response
        for (Student stu : getTypicalStudents()) {
            assertTrue(Arrays.asList(emails).contains(stu.getEmail().toString()));
        }

        // third layer: check final message returned to user
        String intendedMessage = Arrays.stream(emails).reduce("", (acc, el) -> acc + el + ";");
        assertCommandSuccess(new EmailCommand(), model, intendedMessage, model);
    }
}
