package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Member;
import seedu.address.model.person.UniqueMemberList;

/**
 * Wraps all data at the ezFoodie level
 * Duplicates are not allowed (by .isSameMember comparison)
 */
public class EzFoodie implements ReadOnlyEzFoodie {

    private final UniqueMemberList members;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        members = new UniqueMemberList();
    }

    public EzFoodie() {}

    /**
     * Creates an ezFoodie using the Members in the {@code toBeCopied}
     */
    public EzFoodie(ReadOnlyEzFoodie toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the member list with {@code members}.
     * {@code members} must not contain duplicate members.
     */
    public void setMembers(List<Member> members) {
        this.members.setMembers(members);
    }

    /**
     * Resets the existing data of this {@code EzFoodie} with {@code newData}.
     */
    public void resetData(ReadOnlyEzFoodie newData) {
        requireNonNull(newData);

        setMembers(newData.getMemberList());
    }

    //// member-level operations

    /**
     * Returns true if a member with the same identity as {@code member} exists in the ezFoodie.
     */
    public boolean hasMember(Member member) {
        requireNonNull(member);
        return members.contains(member);
    }

    /**
     * Adds a member to the ezFoodie.
     * The member must not already exist in the ezFoodie.
     */
    public void addMember(Member p) {
        members.add(p);
    }

    /**
     * Replaces the given member {@code target} in the list with {@code editedMember}.
     * {@code target} must exist in the ezFoodie.
     * The member identity of {@code editedMember} must not be the same as another existing member in the ezFoodie.
     */
    public void setMember(Member target, Member editedMember) {
        requireNonNull(editedMember);

        members.setMember(target, editedMember);
    }

    /**
     * Removes {@code key} from this {@code EzFoodie}.
     * {@code key} must exist in the ezFoodie.
     */
    public void removeMember(Member key) {
        members.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return members.asUnmodifiableObservableList().size() + " members";
        // TODO: refine later
    }

    @Override
    public ObservableList<Member> getMemberList() {
        return members.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EzFoodie // instanceof handles nulls
                && members.equals(((EzFoodie) other).members));
    }

    @Override
    public int hashCode() {
        return members.hashCode();
    }
}
