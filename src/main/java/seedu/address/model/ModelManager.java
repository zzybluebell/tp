package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.member.Member;
import seedu.address.model.member.Tier;
import seedu.address.model.transaction.Transaction;


/**
 * Represents the in-memory model of the ezFoodie data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Account account;
    private final EzFoodie ezFoodie;
    private final UserPrefs userPrefs;
    private final FilteredList<Member> filteredMembers;
    private final FilteredList<Member> filteredMembersForView;
    private final SortedList<Member> sortedMembers;

    /**
     * Initializes a ModelManager with the given account, ezFoodie and userPrefs.
     */
    public ModelManager(ReadOnlyAccount account, ReadOnlyEzFoodie ezFoodie, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(account, ezFoodie, userPrefs);

        logger.fine("Initializing with account: " + account + ", + ezFoodie: " + ezFoodie
                + " and user prefs " + userPrefs);

        this.account = new Account(account);
        this.ezFoodie = new EzFoodie(ezFoodie);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredMembers = new FilteredList<>(this.ezFoodie.getMemberList());
        filteredMembersForView = new FilteredList<>(this.ezFoodie.getMemberList());
        sortedMembers = new SortedList<>(filteredMembers); // Wrap the FilteredList in a SortedList
        sortedMembers.setComparator(COMPARATOR_SORT_MEMBERS_BY_ID_ASC);
    }

    public ModelManager() {
        this(new Account(), new EzFoodie(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAccountFilePath() {
        return userPrefs.getAccountFilePath();
    }

    @Override
    public void setAccountFilePath(Path accountFilePath) {
        requireNonNull(accountFilePath);
        userPrefs.setAccountFilePath(accountFilePath);
    }

    @Override
    public Path getEzFoodieFilePath() {
        return userPrefs.getEzFoodieFilePath();
    }

    @Override
    public void setEzFoodieFilePath(Path ezFoodieFilePath) {
        requireNonNull(ezFoodieFilePath);
        userPrefs.setEzFoodieFilePath(ezFoodieFilePath);
    }

    //=========== Account ====================================================================================

    @Override
    public void setAccount(ReadOnlyAccount account) {
        requireNonNull(account);
        this.account.resetData(account);
    }

    @Override
    public ReadOnlyAccount getAccount() {
        return account;
    }

    //=========== EzFoodie ===================================================================================

    @Override
    public void setEzFoodie(ReadOnlyEzFoodie ezFoodie) {
        this.ezFoodie.resetData(ezFoodie);
    }

    @Override
    public ReadOnlyEzFoodie getEzFoodie() {
        return ezFoodie;
    }

    @Override
    public boolean hasMember(Member member) {
        requireNonNull(member);
        return ezFoodie.hasMember(member);
    }

    @Override
    public boolean hasMember(Member member, Predicate<Member> predicate) {
        requireNonNull(member);
        return ezFoodie.hasMember(member, predicate);
    }

    @Override
    public void deleteMember(Member target) {
        ezFoodie.removeMember(target);
    }

    @Override
    public void addMember(Member member) {
        ezFoodie.addMember(member);
        updateFilteredMemberList(PREDICATE_SHOW_ALL_MEMBERS);
    }

    @Override
    public void setMember(Member target, Member editedMember) {
        requireAllNonNull(target, editedMember);

        ezFoodie.setMember(target, editedMember);
    }

    //=========== Updated Member List for display =============================================================


    /**
     * Returns an unmodifiable view of the list of {@code Member} backed by the internal list of
     * {@code versionedEzFoodie}
     */
    @Override
    public ObservableList<Member> getUpdatedMemberList() {
        return sortedMembers;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Member} backed by the internal list of
     * {@code versionedEzFoodie}
     * for viewCommand to use only
     */
    @Override
    public ObservableList<Member> getUpdatedMemberListForView() {
        return filteredMembersForView;
    }

    //=========== Summary display =============================================================

    @Override
    public int getNumberOfMembers() {
        return ezFoodie.getMemberList().size();
    }

    @Override
    public HashMap<String, Integer> getNumberOfMembersByTiers() {
        HashMap<String, Integer> tierCounts = new HashMap<>();
        int count;
        String curTier;

        for (String key : Tier.getAllKeys()) {
            tierCounts.put(key, 0);
        }

        for (Member member : ezFoodie.getMemberList()) {
            curTier = Tier.getTierByCredit(Integer.parseInt(member.getCredit().value));
            count = tierCounts.get(curTier);
            tierCounts.put(curTier, count + 1);
        }

        return tierCounts;
    }

    @Override
    public int getNumberOfTransactions() {
        int count = 0;

        for (Member member : filteredMembers) {
            count += member.getTransactions().size();
        }

        return count;
    }

    @Override
    public int getNumberOfTransactionsPastMonth() {
        // todo
        return (int) Math.random() + 32;
    }

    @Override
    public int getNumberOfTransactionsPastThreeMonth() {
        // todo
        return (int) Math.random() + 77;
    }

    @Override
    public int getNumberOfTransactionsPastSixMonth() {
        // todo
        return (int) Math.random() + 100;
    }

    @Override
    public double getTotalAmountOfTransactions() {
        double count = 0;

        for (Member member : filteredMembers) {
            for (Transaction transaction : member.getTransactions()) {
                count += Double.parseDouble(transaction.getBilling().value);
            }
        }

        return count;
    }

    @Override
    public double getTotalAmountOfTransactionsPastMonth() {
        // todo
        return Math.random() + 2000;
    }

    @Override
    public double getTotalAmountOfTransactionsPastThreeMonth() {
        // todo
        return Math.random() + 3000;
    }

    @Override
    public double getTotalAmountOfTransactionsPastSixMonth() {
        // todo
        return Math.random() + 6000;
    }

    //=========== Filtered Member List Accessors =============================================================

    @Override
    public void updateFilteredMemberList(Predicate<Member> predicate) {
        requireNonNull(predicate);
        filteredMembers.setPredicate(predicate);
    }

    @Override
    public void updateFilteredMemberListForView(Predicate<Member> predicate) {
        requireNonNull(predicate);
        filteredMembersForView.setPredicate(predicate);
    }

    //=========== Sorted Member List Accessors ===============================================================

    @Override
    public void updateSortedMemberList(Comparator<Member> comparator) {
        requireNonNull(comparator);
        sortedMembers.setComparator(comparator);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return ezFoodie.equals(other.ezFoodie)
                && userPrefs.equals(other.userPrefs)
                && filteredMembers.equals(other.filteredMembers)
                && sortedMembers.equals(other.sortedMembers);
    }

}
