package com.example.marry.newsapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends ArrayAdapter<Article> {

    private static final String DATE_SEPARATOR = "T";


    /**
     * constructor of the class
     *
     * @param context  the context
     * @param resource the resource
     * @param articles the list of articles
     */
    public NewsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Article> articles) {
        super(context, resource, articles);
    }

    private int getSectionColor(String section) {
        int contextColorResourceId;
        switch (section) {
            case "Sport":
                contextColorResourceId = R.color.sport_section;
                break;
            default:
                contextColorResourceId = R.color.other_sections;
                break;
        }
        //returneaza magnitudeCoolorResourceId ca un integer ca sa poata fi folosit si afisat in GradientDrawable,
        return ContextCompat.getColor(getContext(), contextColorResourceId);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_adapter, parent, false);
        }


        // Find the article at the given position in the list
        Article currentArticle = getItem(position);


        // Find the TextView with view ID section
        TextView sectionView = listItemView.findViewById(R.id.section);
        // Display the section of the current article in that TextView
        sectionView.setText(currentArticle.getSection());

        // Find the TextView with view ID title
        TextView titleView = listItemView.findViewById(R.id.title);
        // Display the title of the current article in that TextView
        titleView.setText(currentArticle.getTitle());

        //retains the date after being split
        String date;

        String originalDate = currentArticle.getDate();
        // Find the TextView with view ID date
        TextView dateView = listItemView.findViewById(R.id.date);

        if (originalDate.contains(DATE_SEPARATOR)) {
            String[] parts = originalDate.split(DATE_SEPARATOR);
            date = parts[0];
            // Display the date of the current article in that TextView
            dateView.setText(date);
        } else {
            // Display the date of the current article in that TextView
            dateView.setText(currentArticle.getDate());
        }

        // Find the TextView with view ID author
        TextView authorView = listItemView.findViewById(R.id.author);
        // Display the author of the current article in that TextView
        authorView.setText(currentArticle.getAuthor());

        // Set the proper background color on the section circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable sectionCircle = (GradientDrawable) sectionView.getBackground();

        // Get the appropriate background color based on the current section
        int sectionColor = getSectionColor(currentArticle.getSection());

        // Set the color on the section circle
        sectionCircle.setColor(sectionColor);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}
