package net.oostdam.kitecrew;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.identitytoolkit.GitkitClient;
import com.google.identitytoolkit.GitkitUser;
import com.google.identitytoolkit.IdToken;
import org.json.JSONObject;

/**
 * Created by Thijs on 3/18/2016.
 */
public class LoginActivity extends Activity {


    private GitkitClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Step 1: Create a GitkitClient.
        // The configurations are set in the AndroidManifest.xml. You can also set or overwrite them
        // by calling the corresponding setters on the GitkitClient builder.
        //

        client = GitkitClient.newBuilder(this, new GitkitClient.SignInCallbacks() {
            // Implement the onSignIn method of GitkitClient.SignInCallbacks interface.
            // This method is called when the sign-in process succeeds. A Gitkit IdToken and the signed
            // in account information are passed to the callback.
            @Override
            public void onSignIn(IdToken idToken, GitkitUser user) {

                Toast.makeText(LoginActivity.this, "Sign in success", Toast.LENGTH_LONG).show();

                // Now use the idToken to create a session for your user.
                // To do so, you should exchange the idToken for either a Session Token or Cookie
                // from your server.
                // Finally, save the Session Token or Cookie to maintain your user's session.
            }

            // Implement the onSignInFailed method of GitkitClient.SignInCallbacks interface.
            // This method is called when the sign-in process fails.
            @Override
            public void onSignInFailed() {
                Toast.makeText(LoginActivity.this, "Sign in failed", Toast.LENGTH_LONG).show();
            }
        }).build();

        setContentView(R.layout.login);
    }


    // Step 3: Override the onActivityResult method.
    // When a result is returned to this activity, it is maybe intended for GitkitClient. Call
    // GitkitClient.handleActivityResult to check the result. If the result is for GitkitClient,
    // the method returns true to indicate the result has been consumed.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (!client.handleActivityResult(requestCode, resultCode, intent)) {
            super.onActivityResult(requestCode, resultCode, intent);
        }
    }

    // Step 4: Override the onNewIntent method.
    // When the app is invoked with an intent, it is possible that the intent is for GitkitClient.
    // Call GitkitClient.handleIntent to check it. If the intent is for GitkitClient, the method
    // returns true to indicate the intent has been consumed.
    @Override
    protected void onNewIntent(Intent intent) {
        if (!client.handleIntent(intent)) {
            super.onNewIntent(intent);
        }
    }

    // Step 5: Respond to user actions.
    // If the user clicks sign in, call GitkitClient.startSignIn() to trigger the sign in flow.
    public void login(View v) {
        client.startSignIn();
    }
}
