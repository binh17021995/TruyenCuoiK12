package com.techja.truyencuoik12.viewmodel;

import android.util.Log;

import com.techja.truyencuoik12.model.StoryModel;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class M001MainVM extends BaseVM {
    private static final String TAG = M001MainVM.class.getName();
    private ArrayList<StoryModel> listStory;

    public ArrayList<StoryModel> getListStory() {
        return listStory;
    }

    public void readStoryFiles(InputStream in) {
        listStory = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            String name = null;
            StringBuilder content = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                if (name == null) {
                    name = line;
                } else if (line.contains("','0');")) {
                    StoryModel model = new StoryModel(name, content.toString());
                    listStory.add(model);
                    name = null;
                    content = new StringBuilder();
                } else if (!line.isEmpty()) {
                    content.append(line).append("\n");
                }
                line = reader.readLine();
            }
            reader.close();
            isr.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "Stories count: " + listStory.size());
    }
}
