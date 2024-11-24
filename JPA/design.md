1. SocialUser and SocialProfile class
   SocialUser
   Columns - user_id(pk),username,password
   SocialProfile
   Columns - profile_id(pk),name,age
```java
@Entity
public class SocialUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String username;
    private String password;


}
```
```java
@Entity
public class SocialProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
}
```
2. SocialUser and SocialProfile has one to one mapping 
Database changes
```
SocialUser 
    Columns - user_id(pk)
SocialProfile
    Columns - profile_id(pk),user_id(FK-SocialUser) 
```
Annotation
```
@OneToOne
```
Added code in SocialUser
```java
//Tell SocialUsers class that there is one to one mapping with SocialProfile class.
    //mappedBy should be the same as field name in SocialProfile class
    @OneToOne(mappedBy = "socialUser")
    private SocialProfile socialProfile;
```
Added code in SocialProfile
```java
//this will create foreign key of social user class in SocialProfile table
    @OneToOne
    @JoinColumn(name="social_user_id")
    private SocialUsers socialUser;
```
2. SocialUser and Post have one to many mapping
@OneToMany
@ManyToOne
3. adsd
