package seedu.address.model.member;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Tests that a {@code Member}'s {@code RegistrationTimestamp} within the given registration dates.
 */
public class RegistrationDateContainsKeywordsPredicate implements Predicate<Member> {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final List<String> keywords;

    public RegistrationDateContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Member member) {
        return keywords.stream().anyMatch(keyword -> {
            try {
                return DateTimeUtil.isDateContainsTimestamp(
                        DATE_FORMAT.parse(keyword), Long.parseLong(member.getRegistrationTimestamp().value));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RegistrationDateContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RegistrationDateContainsKeywordsPredicate) other).keywords)); // state check
    }

}