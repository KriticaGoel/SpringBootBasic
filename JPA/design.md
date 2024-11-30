1. SocialUser and SocialProfile class
   
Database changes
```
   SocialUser
   Columns - user_id(pk),username,password
   SocialProfile
   Columns - profile_id(pk),name,age
```
SocialUsers class
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
SocialProfile class
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
2. SocialUser and SocialProfile have one-to-one mapping 

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

Annotation
```
@OneToMany
@ManyToOne
```
Database changes
```
SocialUser 
    Columns - user_id(pk)
Post
    Columns - post_id(pk),user_id(FK-SocialUser),post
```
Added data in SocialUser class to tell SocialUser about multiple data of post
```java
@OneToMany(mappedBy = "socialUser")
    private List<Post> posts= new ArrayList<Post>();
```
Create new column in post class which is a foreign key of social user class.
```java
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "social_user_id")
    private SocialUsers socialUser;
    
    private String post;

   
}
```
3. Many-to-Many mapping
One use can join multiple groups and one group can be joined by multiple users.
In a group, users are unique—that means one use can join one group max once; but he/she is eligible to join another group too.
In user groups should be unique 