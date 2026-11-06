package com.example.practice3;

public class Owner {

    public static final String DIGITS = "0123456789";
    public static final int CMND_LENGTH = 12;

    private final String cmnd; // no setter: the CMND must stay unique
    private String fullName;
    private String email;

    public Owner(String cmnd, String fullName, String email) {
        if (!isValidCmnd(cmnd)) {
            throw new IllegalArgumentException("CMND phai co dung 12 chu so");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email khong dung dinh dang");
        }
        this.cmnd = cmnd;
        this.fullName = fullName;
        this.email = email;
    }

    public String getCmnd() {
        return cmnd;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email khong dung dinh dang");
        }
        this.email = email;
    }

    // Exactly 12 characters, and every character is a digit
    public static boolean isValidCmnd(String cmnd) {
        if (cmnd.length() != CMND_LENGTH) {
            return false;
        }
        for (int i = 0; i < cmnd.length(); i++) {
            String ch = cmnd.substring(i, i + 1);
            if (!DIGITS.contains(ch)) {
                return false;
            }
        }
        return true;
    }

    // Simple rule: no space, exactly one '@', a non-empty part before it,
    // and a part after it that contains '.' but does not start or end with '.'
    public static boolean isValidEmail(String email) {
        if (email.contains(" ")) {
            return false;
        }

        int atCount = 0;
        int atIndex = -1;
        for (int i = 0; i < email.length(); i++) {
            if (email.substring(i, i + 1).equals("@")) {
                atCount++;
                atIndex = i;
            }
        }
        if (atCount != 1) {
            return false;
        }

        String local = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);
        if (local.isEmpty() || domain.isEmpty()) {
            return false;
        }
        if (!domain.contains(".")) {
            return false;
        }
        if (domain.startsWith(".")) {
            return false;
        }
        if (domain.substring(domain.length() - 1).equals(".")) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CMND: " + cmnd + ", Ho ten: " + fullName + ", Email: " + email;
    }
}
