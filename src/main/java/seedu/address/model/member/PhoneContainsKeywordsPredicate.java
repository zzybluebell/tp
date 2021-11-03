package seedu.address.model.member;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Member}'s {@code Phone} matches any of the keywords given.
 */
public class PhoneContainsKeywordsPredicate implements Predicate<Member> {
    private final List<String> keywords;

    /**
     * Constructs a {@code PhoneContainsKeywordsPredicate} with input {@code List<String>}.
     *
     * @param keywords
     */
    public PhoneContainsKeywordsPredicate(List<String> keywords) {
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
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(member.getPhone().value, keyword));
    }

    /**
     * Overrides equals method.
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PhoneContainsKeywordsPredicate) other).keywords)); // state check
    }

}
