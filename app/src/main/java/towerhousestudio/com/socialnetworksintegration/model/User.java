package towerhousestudio.com.socialnetworksintegration.model;

import android.content.Intent;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.Profile;

import org.json.JSONObject;

import towerhousestudio.com.socialnetworksintegration.MembersAreaActivity;

/**
 * Created by lucia on 20/10/15.
 */
public class User {

    protected String email;
    protected String name;
    protected String facebook_id;
    protected static User currentUser;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacebookId() {
        return facebook_id;
    }

    public void setFacebookId(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public static User getCurrentUser()
    {
        return currentUser;
    }

    public static void setCurrentUser(User u)
    {
        currentUser = u;
    }

    public static void facebookUser(JSONObject userJson)
    {
        User user = new User();
        Profile userProfile = Profile.getCurrentProfile();
        user.setEmail(userJson.optString("email"));
        user.setName(userJson.optString("name"));
        user.setFacebookId(userJson.optString("id"));
        User.setCurrentUser(user);
    }
}
