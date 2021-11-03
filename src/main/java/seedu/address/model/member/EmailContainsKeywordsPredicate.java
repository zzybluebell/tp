package seedu.address.model.member;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Member}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate implements Predicate<Member> {
    private final List<String> keywords;

    /**
     * Constructs {@code EmailContainsKeywordsPredicate} with input {@code List<String>}.
     *
     * @param keywords
     */
    public EmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Overrides test method.
     *
     * @param member
     * @return boolean
     */
    @Override
    public boolean test(Member member) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(member.getEmail().value, keyword));
    }

    /**
     * Overrides equals method.
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EmailContainsKeywordsPredicate) other).keywords)); // state check
    }

}
