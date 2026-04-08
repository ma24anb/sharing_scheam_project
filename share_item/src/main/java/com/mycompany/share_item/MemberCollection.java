package com.mycompany.share_item;

import java.util.ArrayList;

public class MemberCollection {

    private ArrayList<Member> memberCollection = new ArrayList<>();

    public void addMember(Member member) {
        memberCollection.add(member);
    }

    public void removeMember(Member member) {
        memberCollection.remove(member);
    }

    public ArrayList<Member> searchMembers(String name) {
        ArrayList<Member> filteredMembers = new ArrayList<>();
        for (Member m : memberCollection) {
            String memberName = m.getName().toLowerCase();
            if (memberName.contains(name.toLowerCase())) {
                filteredMembers.add(m);
            }
        }
        return filteredMembers;
    }

    public Member getMemberByEmail(String email) {
        for (Member m : memberCollection) {
            if (email.toLowerCase().equals(m.getEmail().toLowerCase())) {
                return m;
            }
        }
        // return null if no results found
        return null;
    }

    public boolean isEmailReserved(String email) {
        boolean isReserved = false;
        for (Member m : memberCollection) {
            if (email.toLowerCase().equals(m.getEmail().toLowerCase())) {
                isReserved = true;
            }
        }
        // return null if no results found
        return isReserved;
    }

    public ArrayList<Member> getAllMembers() {
        return memberCollection;
    }

}
