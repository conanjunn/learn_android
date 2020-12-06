package com.xy.learn_android.ui.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.xy.learn_android.R;
import com.xy.learn_android.databinding.ActivityBindDataBinding;

public class bindDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_data);

        ActivityBindDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_bind_data);
        User user = new User("Test", "User");
        binding.setUser(user);
    }

    public static class User {
        private String firstName;
        private final String lastName;

        public User(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        public void setFirstName(String a) {
            this.firstName = a;
        }
    }

}