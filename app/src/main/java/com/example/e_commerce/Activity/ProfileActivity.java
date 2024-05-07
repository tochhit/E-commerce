package com.example.e_commerce.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerce.databinding.ActivityProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

//        setVarialbe();
        bottomNavigation();
        setupUI();

        binding.backBtn.setOnClickListener(v -> finish());
        binding.logoutBtn.setOnClickListener(v -> logoutUser());

    }


    private void setupUI() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            DocumentReference docRef = firestore.collection("Users").document(user.getUid());
            docRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String userName = documentSnapshot.getString("name");
                    String email = documentSnapshot.getString("email");
                    binding.userName.setText(userName);
                    binding.emailTxt.setText(email);
                    binding.logoutBtn.setText("Log Out");
                } else {
                    Log.d("ProfileActivity", "No such document");
                }
            }).addOnFailureListener(e -> Log.e("ProfileActivity", "Error fetching user data", e));
        } else {
            Log.d("ProfileActivity", "User is not signed in");
            binding.logoutBtn.setText("Login Your Account");
        }
    }


    private void bottomNavigation() {
        binding.myorderTxt.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, MyOrderActivity.class)));
        binding.wishlistTxt.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, WishlistActivity.class)));

    }

    private void logoutUser() {
        auth.signOut();
        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
        finish();
    }

}
