package com.example.xyzreader.utils;

import com.example.xyzreader.data.ArticlesRepository;
import com.example.xyzreader.ui.articlelist.ArticleListViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author Yassin Ajdi
 * @since 4/16/2019.
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ArticlesRepository repository;

    public static ViewModelFactory getInstance(ArticlesRepository repository) {
        return new ViewModelFactory(repository);
    }

    private ViewModelFactory(ArticlesRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ArticleListViewModel.class)) {
            //noinspection unchecked
            return (T) new ArticleListViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
