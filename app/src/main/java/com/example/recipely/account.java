package com.example.recipely;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.L;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import org.w3c.dom.Text;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link account#newInstance} factory method to
 * create an instance of this fragment.
 */
public class account extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public account() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account.
     */
    // TODO: Rename and change types and number of parameters
    public static account newInstance(String param1, String param2) {
        account fragment = new account();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String emailUser = "";
        String usernameUser = "";

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_account, container, false);
        LinearLayout AccountPage = (LinearLayout) view.findViewById(R.id.AccountPage);
        LinearLayout clearFridge = (LinearLayout) view.findViewById(R.id.clearFridge);
        LinearLayout feedback = (LinearLayout) view.findViewById(R.id.feedback);
        TextView username = (TextView) view.findViewById(R.id.username);

        //set user email on page
        user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        for (UserInfo profile : user.getProviderData()){
            emailUser = profile.getEmail();
            usernameUser = profile.getDisplayName();
        }
        TextView email = (TextView) view.findViewById(R.id.emailAddress);
        email.setText(emailUser);

        //set user username on page
        if (!Objects.equals(usernameUser, "")){
            username.setText(usernameUser);
        } else {
            username.setText("Set username");
        }

        username.setOnClickListener(v ->{
            Intent myAccountPage = new Intent(getActivity(), Account_account_node.class);
            startActivity(myAccountPage);
        });

        //Intent to account page
        AccountPage.setOnClickListener(v ->{
            Intent myAccountPage = new Intent(getActivity(), Account_account_node.class);
            startActivity(myAccountPage);

         });

        //Delete intent to delete all the item in database
        clearFridge.setOnClickListener(v ->{
            Intent clearFridgePage = new Intent(getActivity(), clearFridge.class);
            startActivity(clearFridgePage);
        });

        //Intent to goto feedback page
        feedback.setOnClickListener(v ->{
            Intent feedbackPage = new Intent(getActivity(), feedback.class);
            startActivity(feedbackPage);
        });

        return view;
    }
}