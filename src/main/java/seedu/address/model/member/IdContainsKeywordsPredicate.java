package seedu.address.model.member;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Member}'s {@code Id} matches any of the keywords given.
 */
public class IdContainsKeywordsPredicate implements Predicate<Member> {
    private final List<String> keywords;

    /**
     * Constructs a {@code IdContainsKeywordsPredicate} with input {@code List<String>}.
     */
    public IdContainsKeywordsPredicate(List<String> keywords) {
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
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(member.getId().value, keyword));
    }

    /**
     * Overrides equals method.
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((IdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
