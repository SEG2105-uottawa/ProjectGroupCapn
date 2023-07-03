/**
 * Tutor is a subclass of User class that represents a tutor in the application.
 * The class provides constructors to initialize the tutor object, including name, email, and
 * password. The class also includes a constant field "role" that specifies as "Tutor"
 */

package com.example.tutronapp;

public class Tutor extends User{

    private final String role = "Tutor";
    private String shortDescription;
    private String native_languages;
    private String education_level;
    private Long timeSuspendedForInSeconds = null;

    public Tutor(){

    }

    public Tutor(String firstName, String lastName, String emailAddress, String password) {
        super(firstName, lastName, emailAddress, password,"Tutor");
    }

    public Tutor(String firstName, String lastName, String emailAddress, String password, String education_level, String native_languages, String shortDescription){
        super(firstName, lastName, emailAddress, password,"Tutor");
        this.education_level = education_level;

        this.native_languages = native_languages;

        this.shortDescription = shortDescription;
    }

    public String getRole() {
        return role;
    }


    public String getEducation_level() {return education_level;}

    public void setEducation_level(String education_level) {this.education_level = education_level;}

    public String getNative_languages() {return native_languages;}

    public void setNative_languages(String native_languages) {this.native_languages = native_languages;}

    public String getShortDescription() {return shortDescription;}

    public void setShortDescription(String shortDescription) {this.shortDescription = shortDescription;}

    public Long getTimeSuspendedForInSeconds() {
        return timeSuspendedForInSeconds;
    }

    public void setTimeSuspendedForInSeconds(Long suspendedForInSeconds) {
        this.timeSuspendedForInSeconds = suspendedForInSeconds;
    }
}
