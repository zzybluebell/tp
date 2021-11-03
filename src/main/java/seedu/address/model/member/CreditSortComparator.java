package seedu.address.model.member;

import java.util.Comparator;

import seedu.address.commons.status.SortStatus;

/**
 * Tests that a {@code Member}'s {@code Email} matches any of the keywords given.
 */
public class CreditSortComparator implements Comparator<Member> {

    private final SortStatus sortStatus;

    /**
     * Constructs a {@code CreditSortComparator} with input {@code SortStatus}.
     *
     * @param sortStatus
     */
    public CreditSortComparator(SortStatus sortStatus) {
        this.sortStatus = sortStatus;
    }

    /**
     * Gets sort status.
     *
     * @return SortStatus
     */
    public SortStatus getSortStatus() {
        return sortStatus;
    }

    /**
     * Overrides comapre method.
     *
     * @param m1
     * @param m2
     * @return int
     */
    @Override
    public int compare(Member m1, Member m2) {
        if (sortStatus == SortStatus.DESC) {
            return Integer.compare(m2.getCredit().getIntValue(), m1.getCredit().getIntValue());
        } else {
            return Integer.compare(m1.getCredit().getIntValue(), m2.getCredit().getIntValue());
        }
    }

    /**
     * Overrides equals method.
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreditSortComparator // instanceof handles nulls
                && sortStatus.equals(((CreditSortComparator) other).sortStatus)); // state check
    }

}
