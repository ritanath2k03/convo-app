package com.example.chatsapp.Adaptes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.chatsapp.Calls_Fragment;
import com.example.chatsapp.Chat_Fragment;
import com.example.chatsapp.Status_Fragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch ((position)){
            case 0: return new Chat_Fragment();
            case 1: return new Status_Fragment();
            case 2: return new Calls_Fragment();
            default:return new Chat_Fragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        if(position==0){
            title="CHATS";
        }
        if(position==1){
            title="STATUS";
        }
        if(position==2){
            title="CALLS";
        }


        return title;
    }
}
