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
                contextColorResourceId = R.color.Sport_section;
                break;
            case "Books":
                contextColorResourceId = R.color.Books_section;
                break;
            case "Media":
                contextColorResourceId = R.color.Media_section;
                break;
            case "Technology":
                contextColorResourceId = R.color.Technology_section;
                break;
            case "Opinion":
                contextColorResourceId = R.color.Opinion_section;
                break;
            case "Life and style":
                contextColorResourceId = R.color.Life_and_style_section;
                break;
            case "News":
                contextColorResourceId = R.color.News_section;
                break;
            case "US news":
                contextColorResourceId = R.color.US_news_section;
                break;
            case "World news":
                contextColorResourceId = R.color.World_news_section;
                break;
            case "Games":
                contextColorResourceId = R.color.Games_section;
                break;
            case "Science":
                contextColorResourceId = R.color.Science_section;
                break;
            case "Education":
                contextColorResourceId = R.color.Education_section;
                break;
            case "Music":
                contextColorResourceId = R.color.Music_section;
                break;
            case "Fashion":
                contextColorResourceId = R.color.Fashion_section;
                break;
            case "Television and radio":
                contextColorResourceId = R.color.Television_and_radio_section;
                break;
            case "Football":
                contextColorResourceId = R.color.Football_section;
                break;
            default:
                contextColorResourceId = R.color.other_sections;
                break;
        }
        //returns the contextColorResourceId as an integer, so it can be used and displayed in GradientDrawable,
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
