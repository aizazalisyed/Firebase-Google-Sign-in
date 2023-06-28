package java.com.googlesignupkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class MainActivity : AppCompatActivity() {

    lateinit var gso : GoogleSignInOptions
    lateinit var gsc : GoogleSignInClient
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var acct = GoogleSignIn.getLastSignedInAccount(this)
        if(acct != null){
            finish()
            startActivity(Intent(this,WelcomActivity::class.java))
        }

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this,gso)

        button = findViewById(R.id.button)

        button.setOnClickListener {
            signIn()
        }
    }

    fun signIn() {
        var sigInIntent  = gsc.signInIntent
        startActivityForResult(sigInIntent,1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1000){
            var task = GoogleSignIn.getSignedInAccountFromIntent(data)

            if(task.isSuccessful){

                finish()
                startActivity(Intent(this,WelcomActivity::class.java))
            }
            else {
                Toast.makeText(this, "not signed in", Toast.LENGTH_SHORT).show()
            }



        }
    }
}