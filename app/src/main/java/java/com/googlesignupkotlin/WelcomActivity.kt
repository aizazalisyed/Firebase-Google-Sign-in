package java.com.googlesignupkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.w3c.dom.Text

class WelcomActivity : AppCompatActivity() {

    lateinit var gso : GoogleSignInOptions
    lateinit var gsc : GoogleSignInClient
    lateinit var name : TextView
    lateinit var email : TextView
    lateinit var signOut : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom)


        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        signOut = findViewById(R.id.signOut)

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this,gso)


        var acct = GoogleSignIn.getLastSignedInAccount(this)

        if(acct != null){
            name.text = acct.displayName
            email.text = acct.email
        }

        signOut.setOnClickListener {
            signOut()
        }

    }

    fun signOut(){
        gsc.signOut().addOnCompleteListener{
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}