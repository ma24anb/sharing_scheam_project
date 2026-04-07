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
            String memberName = m.getName();
            if (memberName.contains(name)) {
                filteredMembers.add(m);
            }
        }
        return filteredMembers;
    }

    public Member getMemberByEmail(String email) {
        for (Member m : memberCollection) {
            String memberEmail = m.getEmail();
            // if the email string matches the member's email
            if (memberEmail == email) {
                return m;
            }
        }
        // return null if no results found
        return null;
    }

    public ArrayList<Member> getAllMembers() {
        return memberCollection;
    }

}
