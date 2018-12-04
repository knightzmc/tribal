package me.bristermitten.tribal.game.matchmaking;

import lombok.AllArgsConstructor;
import lombok.val;

import java.util.*;

@AllArgsConstructor
public class Party {

    private List<String> members = new ArrayList<>();

    public Party() {
    }

    public Party addMember(UUID uuid) {
        val s = uuid.toString();
        if (!members.contains(s))
            members.add(s);
        return this;
    }


    public List<String> getMembers() {
        return this.members;
    }

    public void setMembers(Set<String> members) {
        this.members = new ArrayList<>(members);
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Party)) return false;
        Party other = (Party) o;
        if (!other.canEqual(this)) return false;
        Object this$members = this.getMembers();
        Object other$members = other.getMembers();
        return Objects.equals(this$members, other$members);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Party;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $members = this.getMembers();
        result = result * PRIME + ($members == null ? 43 : $members.hashCode());
        return result;
    }

    public String toString() {
        return "Party(members=" + this.getMembers() + ")";
    }
}
