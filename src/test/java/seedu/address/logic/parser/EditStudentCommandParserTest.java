package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStudentCommand;
import seedu.address.logic.commands.EditStudentCommand.EditStudentDescriptor;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditStudentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStudentCommand.MESSAGE_USAGE);

    private EditStudentCommandParser parser = new EditStudentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditStudentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB
            + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + STUDY_LEVEL_DESC_AMY
            + GUARDIAN_PHONE_DESC_AMY + RELATIONSHIP_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withStudyLevel(VALID_STUDY_LEVEL_AMY).withGuardianPhone(VALID_GUARDIAN_PHONE_AMY)
            .withRelationship(VALID_RELATIONSHIP_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditStudentCommand expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditStudentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
