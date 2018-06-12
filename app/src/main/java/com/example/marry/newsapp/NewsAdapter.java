package com.example.marry.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<Article> {

    /**
     * constructor of the class
     * @param context the context
     * @param resource the resource
     * @param articles the list of articles
     */
    public NewsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Article> articles) {
        super(context, resource, articles);
    }

//    /**
//     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
//     */
//    private String formatDate(Date dateObject) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
//        return dateFormat.format(dateObject);
//    }




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
        TextView sectionView =  listItemView.findViewById(R.id.section);
        // Display the section of the current article in that TextView
        sectionView.setText(currentArticle.getSection());

        // Find the TextView with view ID title
        TextView titleView =  listItemView.findViewById(R.id.title);
        // Display the title of the current article in that TextView
        titleView.setText(currentArticle.getTitle());

        // Find the TextView with view ID date
        TextView dateView =  listItemView.findViewById(R.id.date);
        // Display the date of the current article in that TextView
        dateView.setText(currentArticle.getDate());

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}
