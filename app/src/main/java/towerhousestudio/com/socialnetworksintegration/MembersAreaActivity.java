package towerhousestudio.com.socialnetworksintegration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.facebook.login.LoginManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import java.util.ArrayList;
import java.util.List;

import towerhousestudio.com.socialnetworksintegration.model.User;

public class MembersAreaActivity extends AppCompatActivity   implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_area);
        User currentUser = User.getCurrentUser();
        View view = this.findViewById(android.R.id.content);
        EditText nameText = (EditText) view.findViewById(R.id.member_name);
        nameText.setText(currentUser.getName());
        EditText emailText = (EditText) view.findViewById(R.id.member_email);
        emailText.setText(currentUser.getEmail());
        setLogoutButton();
        setTwitterButton();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .addScope(new Scope(Scopes.EMAIL))
                .build();
        mGoogleApiClient.connect();
    }

    protected void setTwitterButton() {
        View view = this.findViewById(android.R.id.content);
        Button t = (Button) view.findViewById(R.id.twitter);
        //final LoginActivity myContext = this;
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tw = new Intent(MembersAreaActivity.this, TimelineActivity.class);
                startActivity(tw);
            }
        });
    }

    protected void setLogoutButton() {
        View view = this.findViewById(android.R.id.content);
        Button facebooklogoutButton = (Button) view.findViewById(R.id.facebook_logout_button);
        //final LoginActivity myContext = this;
        facebooklogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                //Facebook
                LoginManager.getInstance().logOut();

                // Clear the default account so that GoogleApiClient will not automatically
                // connect in the future.
                if (MembersAreaActivity.this.mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                }

                Intent loginArea = new Intent(MembersAreaActivity.this, LoginActivity.class);
                startActivity(loginArea);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_members_area, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
